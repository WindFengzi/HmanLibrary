package com.hzx.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import com.hzx.activity.R;

/**
 * 四个方向
 * Created by Hman on 2017/12/26.
 */

public class PositionView  extends View {

    private static final String TAG = "PositionView";
    private float x, y;
    // 线的长度
    private float r;
    private Paint whiteCirclePaint, middleCirclePaint, bigCirclePaint;

    private int white = Color.WHITE;
    private int blue = Color.rgb(5, 159, 247);
    private int deepBlue = Color.rgb(23, 129, 201);

    private Bitmap bitmapUp, bitmapDown, bitmapLeft, bitmapRight;

    private int iconWidth, iconHeight;

    public PositionView(Context context) {
        super(context);
        init();
    }

    public PositionView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public PositionView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        whiteCirclePaint  = new Paint(Paint.ANTI_ALIAS_FLAG);
        whiteCirclePaint.setColor(white);
        whiteCirclePaint.setStyle(Paint.Style.FILL_AND_STROKE);

        middleCirclePaint  = new Paint(Paint.ANTI_ALIAS_FLAG);
        middleCirclePaint.setColor(deepBlue);
        middleCirclePaint.setStyle(Paint.Style.FILL_AND_STROKE);

        bigCirclePaint  = new Paint(Paint.ANTI_ALIAS_FLAG);
        bigCirclePaint.setColor(blue);
        bigCirclePaint.setStyle(Paint.Style.FILL_AND_STROKE);

        bitmapUp = BitmapFactory.decodeResource(getResources(), R.mipmap.up_white);
        bitmapDown = BitmapFactory.decodeResource(getResources(), R.mipmap.down_white);

        bitmapLeft = BitmapFactory.decodeResource(getResources(), R.mipmap.left_white);
        bitmapRight = BitmapFactory.decodeResource(getResources(), R.mipmap.right_white);

        iconWidth = bitmapUp.getWidth();
        iconHeight = bitmapUp.getHeight();

    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        this.r = Math.min(h, w)/2*0.9f;
        this.x = w/2;
        this.y = h/2;
        postInvalidate();
        super.onSizeChanged(w, h, oldw, oldh);
    }

    float upX, upY, downX, downY, leftX, leftY, rightX, rightY;
    int bigR = 200, middleR = 120, smallR = 50;
    Canvas canvas;

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        this.canvas = canvas;
        upX = (x - iconWidth/2);
        upY = (y - (middleR + (bigR - middleR)/2)) - iconHeight/2;
        downX = x - iconWidth/2;
        downY = (y + (middleR + (bigR - middleR)/2)) - iconHeight/2;
        leftX = (x - (middleR + (bigR - middleR)/2)) - iconWidth/2;
        leftY = y - iconHeight/2;
        rightX = (x + (middleR + (bigR - middleR)/2)) - iconWidth/2;
        rightY = y - iconHeight/2;

        canvas.drawCircle(x, y, bigR, bigCirclePaint); // 大的外圈

//        bitmapUp = BitmapFactory.decodeResource(getResources(), R.mipmap.up_blue);
        canvas.drawBitmap(bitmapUp, upX, upY, bigCirclePaint);
        canvas.drawBitmap(bitmapDown, downX, downY, bigCirclePaint);
        canvas.drawBitmap(bitmapLeft, leftX, leftY, bigCirclePaint);
        canvas.drawBitmap(bitmapRight,rightX ,rightY , bigCirclePaint);

        canvas.drawCircle(x, y, middleR, middleCirclePaint); // 中间圈
        canvas.drawCircle(x, y, smallR, whiteCirclePaint); // 小白圈
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        float eventX = event.getX(), eventY = event.getY();

        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            Log.e(TAG, "down x:" + eventX + "; y:" + eventY  + ";position:" + judgePositin(eventX, eventY));
            String position = judgePositin(eventX, eventY);
            onPositionListener.goBackPosition(position);
            if (("left").equals(position)) {
                Log.e(TAG, "---"  + ("left").equals(position) + ";x:" + leftX + ";y:" + leftY);
//                onPositionListener.setOnPositionListener();
                canvas.drawBitmap(BitmapFactory.decodeResource(getResources(), R.mipmap.left_blue), leftX, leftY, bigCirclePaint);
//                canvas.drawBitmap(BitmapFactory.decodeResource(getResources(), R.mipmap.up_blue), upX, upY, bigCirclePaint);
            } else if (("up").equals(position)) {
                canvas.drawBitmap(BitmapFactory.decodeResource(getResources(), R.mipmap.up_blue), upX, upY, bigCirclePaint);
            }else if (("right").equals(position)) {
                canvas.drawBitmap(BitmapFactory.decodeResource(getResources(), R.mipmap.right_white),rightX ,rightY , bigCirclePaint);
            }else if (("down").equals(position)) {
                canvas.drawBitmap(BitmapFactory.decodeResource(getResources(), R.mipmap.down_white), downX, downY, bigCirclePaint);
            }
            invalidate();
//            return true; // 只有返回true这个控件的move和up才会响应
        } else if (event.getAction() == MotionEvent.ACTION_MOVE) {
            Log.e(TAG, "move x:" + eventX + "; y:" + eventY );

        }else if (event.getAction() == MotionEvent.ACTION_UP) {
            Log.e(TAG, "up x:" + eventX + "; y:" + eventY );

        }

        return super.onTouchEvent(event);
    }

    private String judgePositin(float eventX, float eventY) {
        if (eventX > (x - bigR) && eventX < (x - middleR) && eventY < (y + 2*iconHeight) && eventY > (y - 2* iconHeight)){
            return "left";
        } else if (eventX > (x - 2* iconWidth) && eventX < (x + 2*iconWidth) && eventY > (y - bigR) && eventY < (y - middleR)) {
            return "up";
        } else if (eventX < (x + bigR) && eventX > (x + middleR) && eventY < (y + 2*iconHeight) && eventY > (y - 2* iconHeight)) {
            return "right";
        } else if (eventX > (x - 2* iconWidth) && eventX < (x + 2*iconWidth) && eventY > (y + middleR) && eventY < (y + bigR)) {
            return "down";
        }
        return "";
    }

    private OnPositionListener onPositionListener;

    public void setOnPositionListener(OnPositionListener onPositionListener) {
        this.onPositionListener = onPositionListener;
    }

    public interface OnPositionListener {
        void goBackPosition(String position);
    }

}
