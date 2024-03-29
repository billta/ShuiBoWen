package com.example.billta.shuibowen;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import java.security.KeyStore;


public class WaveView extends View {

    private Paint paint;
    private float downX;
    private float downY;

    private int radio=5;

    public WaveView(Context context, AttributeSet attrs) {
        super(context,attrs);
        initView();

    }

    private void initView() {
        radio=5;
        paint=new Paint();
        paint.setColor(Color.RED);
        paint.setAntiAlias(true);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(radio/3);

    }
 private Handler handler=new Handler(){
     @Override
     public void handleMessage(Message msg) {
         super.handleMessage(msg);
         radio +=5;
         int alpha=paint.getAlpha();
         alpha -=5;
         if (alpha<0){
            alpha=0;
         }
         paint.setAlpha(alpha);

         paint.setStrokeWidth(radio/3);

         invalidate();//重绘
     }
 };

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (paint.getAlpha()>0&&downX>0&&downY>0){
            canvas.drawCircle(downX,downY,radio,paint);
            handler.sendEmptyMessageDelayed(0,50);
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
            case MotionEvent.ACTION_MOVE:
                downX=event.getX();
                downY=event.getY();
                initView();
                invalidate();
                break;
        }
        return super.onTouchEvent(event);
    }
}
