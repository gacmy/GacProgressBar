package com.gac.widiget.progressbar;

/**
 * Created by gacmy on 17-10-12.
 */

public class ProgressBarConfig {
    private float mPercent;//百分比
    private int textSize;//sp 字体尺寸
    private int textColor;//字体颜色
    private int mBackgroudColor;//背景色
    private int  mForegroundColor;//前景色
    private String mDescriptionText;//文本信息
    private boolean animation;//设置动画效果
    private boolean roundRect;

    private ProgressBarConfig(Builder builder){
        mPercent = builder.mPercent;
        textColor = builder.textColor;
        textSize = builder.textSize;
        mBackgroudColor = builder.mBackgroudColor;
        mForegroundColor = builder.mForegroundColor;
        mDescriptionText = builder.mDescriptionText;
        animation = builder.animation;
        this.roundRect = builder.roundRect;
    }

    public boolean isRoundRect() {
        return roundRect;
    }

    public boolean isAnimation() {
        return animation;
    }

    public float getPercent() {
        return mPercent;
    }

    public int getTextSize() {
        return textSize;
    }

    public int getTextColor() {
        return textColor;
    }

    public int getBackgroudColor() {
        return mBackgroudColor;
    }

    public int getForegroundColor() {
        return mForegroundColor;
    }

    public String getDescriptionText() {
        return mDescriptionText;
    }

    public  static class Builder{
        private float mPercent;
        private int textSize;//sp
        private int textColor;
        private int mBackgroudColor;
        private int  mForegroundColor;
        private String mDescriptionText;
        private int maxHeight;//dp;//暂时没做
        private int maxWidth; //dp 暂时没有需要
        private boolean animation = false;//是否设置动画效果

        private boolean roundRect = false;
        public ProgressBarConfig.Builder setPercent(float percent){
            mPercent = percent;
            return this;
        }
        public ProgressBarConfig.Builder setAnimation(boolean animation){
            this.animation = animation;
            return this;
        }
        public ProgressBarConfig.Builder setTextSize(int size){
            textSize = size;
            return this;
        }
        public ProgressBarConfig.Builder setTextColor(int color){
            textColor = color;//ContextCompat.getColor(getContext(), color);
            return this;
        }
        public ProgressBarConfig.Builder setBackgroudColor(int color){
            mBackgroudColor = color;//ContextCompat.getColor(getContext(), color);
            return this;
        }
        public ProgressBarConfig.Builder setForegroundColor(int color){
            mForegroundColor = color;//ContextCompat.getColor(getContext(), color);
            return this;
        }

        public ProgressBarConfig.Builder setDescriptionText(String text){
            mDescriptionText = text;
            return this;
        }
        public ProgressBarConfig.Builder setRoundRect(boolean roundRect){
            this.roundRect = roundRect;
            return this;
        }
        public ProgressBarConfig build(){
            return new ProgressBarConfig(this);
        }

    }
}
