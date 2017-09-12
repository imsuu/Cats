package com.cats.weights;

import com.demo.cats.R;
import com.nineoldandroids.animation.Animator;
import com.nineoldandroids.animation.ObjectAnimator;
import com.nineoldandroids.view.ViewHelper;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RadialGradient;
import android.graphics.Rect;
import android.graphics.Shader;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.RelativeLayout;

public class RippleRelativeLayout extends RelativeLayout{

	private RippleRelativeLayout rippleLayout;
	
	public void setRippleLayout(RippleRelativeLayout rippleLayout) {
		this.rippleLayout = rippleLayout;
	}

	private int mRippleColor;
	private boolean mHover = true;
	private float mAlphaFactor;
	
	private float mDownX;
    private float mDownY;
    private float mDensity;
    private float mRadius;
    private RadialGradient mRadialGradient;
    private Paint mPaint;
    private Path mPath;
    private Rect mRect;
    private ObjectAnimator mRadiusAnimator;
    private boolean mAnimationIsCancel;
    private int height;
	
	public RippleRelativeLayout(Context context) {
		this(context, null);
	}
	public RippleRelativeLayout(Context context, AttributeSet attrs) {
		this(context, attrs, 0);
	}
	public RippleRelativeLayout(Context context, AttributeSet attrs,int defStyle) {
		super(context, attrs, defStyle);
		init(attrs);
	}

	private void init(AttributeSet attrs){
		setRippleColor(Color.BLACK, 0.2f);
		
		TypedArray ta = getContext().obtainStyledAttributes(attrs, R.styleable.MenuItemView);
		mRippleColor = ta.getColor(R.styleable.MenuItemView_rippleColor,mRippleColor);
	    mAlphaFactor = ta.getFloat(R.styleable.MenuItemView_alphaFactor,mAlphaFactor);
	    mHover = ta.getBoolean(R.styleable.MenuItemView_hover, mHover);
		ta.recycle();
		
		mDensity = getContext().getResources().getDisplayMetrics().density;
	    mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
	    mPaint.setAlpha(100);
	    mPath = new Path();
	}
	
	@Override
	protected void onSizeChanged(int w, int h, int oldw, int oldh) {
		super.onSizeChanged(w, h, oldw, oldh);
		height = getHeight();
	}
	
	@SuppressLint("ClickableViewAccessibility")
	@Override
	public boolean onTouchEvent(MotionEvent event) {
		boolean superResult = super.onTouchEvent(event);
		switch (event.getAction()) {
		case MotionEvent.ACTION_DOWN:
			if(this.isEnabled() && mHover){
				 mRect = new Rect(getLeft(), getTop(), getRight(), getBottom());
		            mAnimationIsCancel = false;
		            mDownX = event.getX();
		            mDownY = event.getY();
		            mRadiusAnimator = ObjectAnimator.ofFloat(rippleLayout != null? rippleLayout:this, "radius", 0, dp(height/4)).setDuration(200);
		            mRadiusAnimator.setInterpolator(new AccelerateDecelerateInterpolator());
		            mRadiusAnimator.start();
		            if (!superResult) {
		                return true;
		            }
			}
			break;
		case MotionEvent.ACTION_MOVE:
			 mDownX = event.getX();
	            mDownY = event.getY();
	            if (this.mAnimationIsCancel = !mRect.contains(getLeft() + (int) event.getX(), getTop() + (int) event.getY())) {
	                setRadius(0);
	            } else {
	                setRadius(dp(height/4));
	            }
	            if (!superResult) {
	                return true;
	            }
			break;
		default:
			mRadiusAnimator = ObjectAnimator.ofFloat(rippleLayout != null? rippleLayout:this, "radius", dp(height/4),0);
            mRadiusAnimator.setDuration(200);
            mRadiusAnimator .setInterpolator(new AccelerateDecelerateInterpolator());
            mRadiusAnimator.start();
            mRadiusAnimator.addListener(new Animator.AnimatorListener() {
                @Override
                public void onAnimationStart(Animator animator) {
                }

                @Override
                public void onAnimationEnd(Animator animator) {
                	setRadius(0);
                    ViewHelper.setAlpha(rippleLayout != null? rippleLayout:RippleRelativeLayout.this, 1);
                }

                @Override
                public void onAnimationCancel(Animator animator) {
                }
                @Override
                public void onAnimationRepeat(Animator animator) {
                }
            });
			
            if (!superResult) {
                return true;
            }
			break;
		}
		return superResult;
	}
	
	@Override
    protected void onDraw(final Canvas canvas) {
        super.onDraw(canvas);
        if (isInEditMode()) {
            return;
        }
        canvas.save(Canvas.CLIP_SAVE_FLAG);
        mPath.reset();
        mPath.addCircle(mDownX, mDownY, mRadius, Path.Direction.CW);
        canvas.clipPath(mPath);
        canvas.restore();
        canvas.drawCircle(mDownX, mDownY, mRadius, mPaint);
    }
	
	private int adjustAlpha(int color, float factor) {
        int alpha = Math.round(Color.alpha(color) * factor);
        int red = Color.red(color);
        int green = Color.green(color);
        int blue = Color.blue(color);
        return Color.argb(alpha, red, green, blue);
    }

    private void setRadius(final float radius) {
        mRadius = radius;
        if (mRadius > 0) {
            mRadialGradient = new RadialGradient(mDownX, mDownY, mRadius,
                    adjustAlpha(mRippleColor, mAlphaFactor), mRippleColor,
                    Shader.TileMode.MIRROR);
            mPaint.setShader(mRadialGradient);
        }
        invalidate();
    }
	
	public void setRippleColor(int rippleColor, float alphaFactor) {
        mRippleColor = rippleColor;
        mAlphaFactor = alphaFactor;
    }

	public void setHover(boolean enabled) {
        mHover = enabled;
    }
	
	private int dp(int dp) {
        return (int) (dp * mDensity + 0.5f);
    }
	
}
