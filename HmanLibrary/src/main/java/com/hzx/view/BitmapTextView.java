package com.hzx.view;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.util.Log;

/**
 * Created by Hman on 2017/11/7.
 * 在图片上方写字并画出矩形框
 */
public class BitmapTextView {

    private static final String TAG = "BitmapTextView";
    public Bitmap bitmap = null;

    public Bitmap drawBitMap(String str, Bitmap bitmapDescriptor) {

        int width = bitmapDescriptor.getWidth(), height = bitmapDescriptor.getHeight();//marker的获取宽高
        int color = bitmapDescriptor.getPixel(width/2, height/2);

        bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_4444); //建立一个空的Bitmap

        Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);//抗锯齿
        paint.setDither(true); // 获取跟清晰的图像采样
        paint.setFilterBitmap(true);// 过滤
        paint.setColor(Color.WHITE);
//        paint.setColor(Color.rgb(Color.red(color), Color.green(color), Color.blue(color)));
        paint.setTextSize(24);

        Rect bounds = new Rect();
        paint.getTextBounds(str, 0, str.length(), bounds);//获取文字的范围
        bitmap = scaleBitmap(bitmap, bounds);

        //文字在mMarker中展示的位置
        float paddingLeft = (bitmap.getWidth() - bounds.width())/2;//在中间
//        float paddingTop = (bitmap.getHeight() / scale);//在顶部
        Log.e(TAG, "paddingLeft:" + paddingLeft  + ";bounds:" + bounds.height() + ";" + bounds.width() + ";" + (bitmap.getWidth() )/2);
        Canvas canvas = new Canvas(bitmap);

        Paint paintRect = new Paint(Paint.ANTI_ALIAS_FLAG);
        paintRect.setStyle(Paint.Style.FILL_AND_STROKE);
        paintRect.setColor(Color.rgb(Color.red(color), Color.green(color), Color.blue(color)));
        float left = canvas.getHeight();
        Log.e(TAG, "left:" + left + ";" + canvas.getWidth());
        RectF r2=new RectF();                           //RectF对象
        r2.left=paddingLeft - 2;                                 //左边
        r2.top= 0;                                 //上边
        r2.right= paddingLeft + bounds.width() + 12;                                   //右边
        r2.bottom=bounds.height() + 10;                              //下边
        canvas.drawRoundRect(r2, 10, 10, paintRect);        //绘制圆角矩形

        canvas.drawText(str, paddingLeft, bounds.height(), paint);

        RectF r3 = new RectF();
        r3.left = 0;
        r3.top = 0;
        r3.right = bitmap.getWidth(); r3.bottom = bitmap.getHeight();
//        canvas.drawRoundRect(r3, 10, 10, paintRect);        //绘制圆角矩形
        //合并两个bitmap为一个
        canvas.drawBitmap(bitmapDescriptor,( bitmap.getWidth() - width) /2, bounds.height() + 15, null);//marker的位置
        return bitmap;
    }

    private Bitmap scaleBitmap(Bitmap bitmap, Rect bounds) {
        if (bounds == null || bitmap == null) {
            return bitmap;
        } else {
            // 记录src的宽高
            int width = bitmap.getWidth();
            int height = bitmap.getHeight();
//            Log.e(TAG, "bitmap:w=" + width + ";h=" + height);
            int bWidth = bounds.width();
            int bHeight = bounds.height();
//            Log.e(TAG, "bounds:w=" + bWidth + ";h=" + bHeight);
            // 计算缩放比例
            int scaleWidth = bWidth;
            int scaleHeight = bHeight + height;

            int scale = bWidth/width;
//            Log.e(TAG, "scale:" + scale);
            // 开始缩放
            return Bitmap.createScaledBitmap(bitmap, (scale + 2)*width, scaleHeight + 15, true);
        }
    }
}
