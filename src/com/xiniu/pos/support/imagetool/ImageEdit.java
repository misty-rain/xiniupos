package com.xiniu.pos.support.imagetool;

import android.content.Context;
import android.graphics.*;
import android.view.View;


import java.io.File;
import java.io.FileOutputStream;

import com.xiniu.pos.R;
import com.xiniu.pos.support.file.FileManager;
import com.xiniu.pos.support.utils.AppLogger;

/**
 * 
 * Copyright © 2014ENGC. All rights reserved.
 * @Title: ImageEdit.java
 * @Package: com.engc.jlcollege.support.imagetool
 * @Description: 图片编辑类
 * @author: wutao  
 * @date: 2014-7-7 下午5:36:10
 */
public class ImageEdit {
	/**
	 * 获得圆形图片
	 * @Title: getRoundedCornerBitmap
	 * @Description: TODO
	 * @return: Bitmap
	 */
    public static Bitmap getRoundedCornerBitmap(Bitmap bitmap) {
        Bitmap output = Bitmap.createBitmap(bitmap.getWidth(),
                bitmap.getHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(output);

        final int color = 0xff424242;
        final Paint paint = new Paint();
        final Rect rect = new Rect(0, 0, bitmap.getWidth(), bitmap.getHeight());
        final RectF rectF = new RectF(rect);
        final float roundPx = 3;

        paint.setAntiAlias(true);
        canvas.drawARGB(0, 0, 0, 0);
        paint.setColor(color);
        canvas.drawRoundRect(rectF, roundPx, roundPx, paint);

        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
        canvas.drawBitmap(bitmap, rect, rect, paint);
        bitmap.recycle();
        return output;
    }

    /**
     * 将String 转换为 bitmap
     * @Title: convertStringToBitmap
     * @Description: TODO
     * @return: String
     */
    public static String convertStringToBitmap(Context context, View et) {
        Bitmap bitmap = et.getDrawingCache();
        Bitmap output = Bitmap.createBitmap(bitmap.getWidth(),
                bitmap.getHeight(), Bitmap.Config.ARGB_8888);
        output.eraseColor(context.getResources().getColor(R.color.white));
        Canvas canvas = new Canvas(output);

        final Paint paint = new Paint();
        final Rect rect = new Rect(0, 0, bitmap.getWidth(), bitmap.getHeight());

        paint.setAntiAlias(true);
        canvas.drawBitmap(bitmap, rect, rect, paint);

        try {
            String path = FileManager.getTxt2picPath() + File.separator + "tmp.png";
            AppLogger.e(path);
            FileManager.createNewFileInSDCard(path);
            FileOutputStream out = new FileOutputStream(path);
            output.compress(Bitmap.CompressFormat.PNG, 90, out);
//            bitmap.recycle();
            if (new File(path).exists())
                return path;
        } catch (Exception e) {
            AppLogger.e(e.getMessage());
        }
        return "";
    }

}
