package com.example.vinsent_y.loverspace.view;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.drawable.GradientDrawable;
import android.os.Build;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.LinearInterpolator;
import android.widget.Button;

import androidx.annotation.RequiresApi;
import androidx.appcompat.widget.AppCompatButton;

import com.example.vinsent_y.loverspace.R;

/**
* FileName: ShowButton
* Author: Saber
* Date: 2019/5/10
* Description: 酷炫的按钮跳转效果
*/
public class ShowButton extends AppCompatButton {

    private int width;
    private int height;

    private GradientDrawable backDrawable;

    private boolean isMorphing;
    private int startAngle;

    private Paint paint;

    private ValueAnimator arcValueAnimator;

    public ShowButton(Context context) {
        super(context);
    }

    public ShowButton(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ShowButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    private void init(Context context){
        isMorphing=false;

        backDrawable=new GradientDrawable();
        int colorDrawable=context.getColor(R.color.colorPrimary);
        backDrawable.setColor(colorDrawable);
        backDrawable.setCornerRadius(120);
        setBackground(backDrawable);

        setText("登陆");

        paint=new Paint();
        paint.setColor(getResources().getColor(android.R.color.white));
        paint.setStrokeWidth(4);
        paint.setStyle(Paint.Style.STROKE);
        paint.setTextSize(2);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int widthMode=MeasureSpec.getMode(widthMeasureSpec);
        int widthSize=MeasureSpec.getSize(widthMeasureSpec);
        int heightMode=MeasureSpec.getMode(heightMeasureSpec);
        int heightSize=MeasureSpec.getSize(heightMeasureSpec);
        if (widthMode==MeasureSpec.EXACTLY){
            width=widthSize;
        }
        if (heightMode==MeasureSpec.EXACTLY){
            height=heightSize;
        }
    }

    public void startAnim(){
        isMorphing=true;

        setText("");
        ValueAnimator valueAnimator=ValueAnimator.ofInt(width,height);

        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                int value= (int) animation.getAnimatedValue();
                int leftOffset=(width-value)/2;
                int rightOffset=width-leftOffset;

                backDrawable.setBounds(leftOffset,0,rightOffset,height);
            }
        });
        ObjectAnimator objectAnimator=ObjectAnimator.ofFloat(backDrawable,"cornerRadius",120,height/2);

        AnimatorSet animatorSet=new AnimatorSet();
        animatorSet.setDuration(500);
        animatorSet.playTogether(valueAnimator,objectAnimator);
        animatorSet.start();

        //画中间的白色圆圈

        showArc();
    }

    public void gotoNew(){
        isMorphing=false;

        arcValueAnimator.cancel();
        setVisibility(GONE);

    }

    public void regainBackground(){
        setVisibility(VISIBLE);
        backDrawable.setBounds(0,0,width,height);
        backDrawable.setCornerRadius(24);
        setBackground(backDrawable);
        setText("登陆");
        isMorphing=false;
    }

    private void showArc() {
        arcValueAnimator=ValueAnimator.ofInt(0,1080);
        arcValueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                startAngle= (int) animation.getAnimatedValue();
                invalidate();
            }
        });
        arcValueAnimator.setInterpolator(new LinearInterpolator());
        arcValueAnimator.setRepeatCount(ValueAnimator.INFINITE);
        arcValueAnimator.setDuration(3000);
        arcValueAnimator.start();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        if (isMorphing){
            final RectF rectF=new RectF(getWidth()*5/12,getHeight()/7,getWidth()*7/12,getHeight()-getHeight()/7);
            canvas.drawArc(rectF,startAngle,270,false,paint);
        }
    }
}
