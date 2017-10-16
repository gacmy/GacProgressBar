package com.gac.widiget.progressbar;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;



/**
 * Created by gacmy on 17-10-12.
 */

public class GacProgressBar extends View {
    private int maxWidth;
    private int maxHeight;
    private Paint textPaint;
    private int mBackgroudColor;
    private int mForegroundColor;
    private int textColor;
    private int textSize;
    private float mPercent;
    private String mDescriptionText;
    ValueAnimator animator;
    private int currentWidth;
    private boolean roundRect;
    public GacProgressBar(Context context) {
        super(context);
        init();
    }

    public GacProgressBar(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public GacProgressBar(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }
    private void init(){
        if(mBackgroudColor == 0){
            mBackgroudColor = Color.parseColor("#eeeeee");
        }
        if(mForegroundColor == 0){
            mForegroundColor = Color.parseColor("#feaaaa");
        }

        textPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        textColor = Color.parseColor("#000000");
        mDescriptionText = "剩余20%";
        textSize = 40;
        mPercent = 0.2f;

    }

    private void setBackgroundPaint(){
        textPaint.setColor(mBackgroudColor);
        textPaint.setStyle(Paint.Style.FILL);
    }
    private void setForegroundPaint(){
        textPaint.setColor(mForegroundColor);
        textPaint.setStyle(Paint.Style.FILL);
    }


    /*
      设置动画效果
    */
    private void setAnimation(){
        animator = ValueAnimator.ofFloat(0,1);
        //animator.ofFloat(0,1);
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float value = (float)animation.getAnimatedValue();
               // float width = (int)maxWidth*mPercent*value;

                currentWidth =(int)(maxWidth* value*mPercent);
               // Log.e("gac","value"+mPercent);
                invalidate();
            }
        });

        animator.setDuration(1000);
        animator.start();
    }
    private void setTextPaint(){
        textPaint.setColor(textColor);
        textPaint.setTextSize(textSize);
    }


    public void setProgressBarConfig(ProgressBarConfig config){
        mDescriptionText = config.getDescriptionText();
        if(config.getForegroundColor() == 0){
            mForegroundColor = Color.parseColor("#feaaaa");
        }else{
            mForegroundColor = convertColor(config.getForegroundColor());
        }

        if(config.getBackgroudColor() == 0){
            mBackgroudColor = Color.parseColor("#eeeeee");
        }else{
            mBackgroudColor = convertColor(config.getBackgroudColor());
        }

        mPercent = config.getPercent();


        if(config.getTextColor() == 0){
            textColor = Color.parseColor("000000");
        }else{
            textColor = convertColor(config.getTextColor());
        }

        if(config.getTextSize() == 0){
            textSize = convertsize(16);
        }else{
            textSize = convertsize(config.getTextSize());
        }
        roundRect = config.isRoundRect();
        //invalidate();
        if(config.isAnimation()){
            setAnimation();
        }else{
            invalidate();

            Log.e("maxWidth","maxWidth:"+maxWidth+" curWidth:"+currentWidth+" percent:"+mPercent);
        }


    }
    //资源id convert color rgb
    private int convertColor(int color){
        return ContextCompat.getColor(getContext(), color);
    }

    //dp to px
    private int convertsize(int size){
        return  (int)TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, size,
                getResources().getDisplayMetrics());
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        maxWidth = measureWidth(widthMeasureSpec);
        maxHeight = measureHeight(heightMeasureSpec);
        setMeasuredDimension(maxWidth, maxHeight);
        currentWidth = (int)(maxWidth *mPercent);
    }

    private int measureWidth(int measureSpec) {
        int specMode = MeasureSpec.getMode(measureSpec);
        int specSize = MeasureSpec.getSize(measureSpec);
        //设置一个默认值，就是这个View的默认宽度为500，这个看我们自定义View的要求
        int result = 500;
        if (specMode == MeasureSpec.AT_MOST) {//相当于我们设置为wrap_content
            result = specSize;
        } else if (specMode == MeasureSpec.EXACTLY) {//相当于我们设置为match_parent或者为一个具体的值
            result = specSize;
        }
        return result;
    }

    private int measureHeight(int measureSpec) {
        int specMode = MeasureSpec.getMode(measureSpec);
        int specSize = MeasureSpec.getSize(measureSpec);
        int result = 500;
        if (specMode == MeasureSpec.AT_MOST) {
            result = specSize;
        } else if (specMode == MeasureSpec.EXACTLY) {
            result = specSize;
        }
        return result;
    }

    @Override
    protected void onDraw(Canvas canvas) {
       if(!roundRect){
           drawRectProgress(canvas);
       }else{
           drawRoundRectProgress(canvas);
       }
    }

    //画矩形进度条
    private void drawRectProgress(Canvas canvas){
        drawBackground(canvas);
        drawForeground(canvas);
        drawText(canvas);
    }

    //画矩形圆角的进度条 圆角写死了 可以写成变量
    private void drawRoundRectProgress(Canvas canvas){
        setBackgroundPaint();
        RectF rect = new RectF(0,0,maxWidth,maxHeight);
        canvas.drawRoundRect(rect,15,15,textPaint);
        setForegroundPaint();
        RectF rectFore = new RectF(0,0,currentWidth,maxHeight);
        canvas.drawRoundRect(rectFore,15,15,textPaint);
        drawText(canvas);
    }
    //画背景
    private void drawBackground(Canvas canvas){
        setBackgroundPaint();
        canvas.drawRect(0,0,maxWidth,maxHeight,textPaint);
    }
    //画前景
    private void  drawForeground(Canvas canvas){
        setForegroundPaint();
        canvas.drawRect(0,0,currentWidth,maxHeight,textPaint);
    }
    //画文本
    private void drawText(Canvas canvas){
        if(TextUtils.isEmpty(mDescriptionText)){
            return;
        }
        setTextPaint();
        canvas.drawText(mDescriptionText,40,maxHeight,textPaint);
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        if(animator != null){
            animator.cancel();
        }
    }
}
