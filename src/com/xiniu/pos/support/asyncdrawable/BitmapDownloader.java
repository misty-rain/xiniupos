package com.xiniu.pos.support.asyncdrawable;

import android.annotation.SuppressLint;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.widget.ImageView;


import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import com.xiniu.pos.support.file.FileLocationMethod;
import com.xiniu.pos.support.lib.MyAsyncTask;
import com.xiniu.pos.support.settinghelper.SettingUtility;
import com.xiniu.pos.support.utils.GlobalContext;



/**
 * User: qii
 * Date: 12-12-12
 */
public class BitmapDownloader {

    private Drawable transPic = new ColorDrawable(Color.TRANSPARENT);

    private Map<String, PictureBitmapWorkerTask> picTasks = new ConcurrentHashMap<String, PictureBitmapWorkerTask>();

    @SuppressLint("NewApi")
    /**
     * 通过key 从缓存获取图片
     * @Title: getBitmapFromMemCache
     * @Description: TODO
     * @return: Bitmap
     */
	protected Bitmap getBitmapFromMemCache(String key) {
        if (TextUtils.isEmpty(key))
            return null;
        else
            return GlobalContext.getInstance().getAvatarCache().get(key);
    }


    /**
     * 加载图片
     * @Title: downloadPic
     * @Description: TODO
     * @return: void
     */
    public void downloadPic(ImageView view, String  url) {
        downloadAvatar(view, url, false);
    }


    public void downloadAvatar(ImageView view, String url ,boolean isEnableShow) {
        //boolean isFling = fragment.isListViewFling();
        downloadAvatar(view, url, isEnableShow);
    }

    public void downloadAvatar(ImageView view, String url) {

        if (url == null) {
            view.setImageDrawable(transPic);
            return;
        }

       //String url;
        FileLocationMethod method;
        if (SettingUtility.getEnableBigAvatar()) {
            //url = user.getAvatar_large();
            method = FileLocationMethod.avatar_large;
        } else {
            //url = user.getProfile_image_url();
            method = FileLocationMethod.avatar_small;
        }
        display(view, url, method, false);
    }

  /*  public void downContentPic(ImageView view, MessageBean msg, AbstractTimeLineFragment fragment) {
        String picUrl;

       // boolean isFling = ((AbstractTimeLineFragment) fragment).isListViewFling();

        if (SettingUtility.getEnableBigPic()) {
            picUrl = msg.getBmiddle_pic();
            display(view, picUrl, FileLocationMethod.picture_bmiddle, isFling);

        } else {
            picUrl = msg.getThumbnail_pic();
            display(view, picUrl, FileLocationMethod.picture_thumbnail, isFling);

        }
    }*/

    private void display(ImageView view, String urlKey, FileLocationMethod method, boolean isFling) {
        view.clearAnimation();
        final Bitmap bitmap = getBitmapFromMemCache(urlKey);
        if (bitmap != null) {
            view.setImageBitmap(bitmap);
            cancelPotentialDownload(urlKey, view);
            picTasks.remove(urlKey);
        } else {
            if (isFling) {
                view.setImageDrawable(transPic);
                return;
            }

            PictureBitmapWorkerTask task = picTasks.get(urlKey);

            if (task != null) {
                task.addView(view);
                view.setImageDrawable(new PictureBitmapDrawable(task));
                return;
            }


            if (cancelPotentialDownload(urlKey, view)) {
                task = new PictureBitmapWorkerTask(picTasks, view, urlKey, method);
                PictureBitmapDrawable downloadedDrawable = new PictureBitmapDrawable(task);
                view.setImageDrawable(downloadedDrawable);
                task.executeOnExecutor(MyAsyncTask.THREAD_POOL_EXECUTOR);
                picTasks.put(urlKey, task);
                return;
            }

        }

    }


    public void totalStopLoadPicture() {
        if (picTasks != null) {
            for (String task : picTasks.keySet()) {
                picTasks.get(task).cancel(true);
            }
        }
    }


    private static boolean cancelPotentialDownload(String url, ImageView imageView) {
        PictureBitmapWorkerTask bitmapDownloaderTask = getBitmapDownloaderTask(imageView);

        if (bitmapDownloaderTask != null) {
            String bitmapUrl = bitmapDownloaderTask.getUrl();
            if ((bitmapUrl == null) || (!bitmapUrl.equals(url))) {
                bitmapDownloaderTask.cancel(true);
            } else {
                return false;
            }
        }
        return true;
    }


    private static PictureBitmapWorkerTask getBitmapDownloaderTask(ImageView imageView) {
        if (imageView != null) {
            Drawable drawable = imageView.getDrawable();
            if (drawable instanceof PictureBitmapDrawable) {
                PictureBitmapDrawable downloadedDrawable = (PictureBitmapDrawable) drawable;
                return downloadedDrawable.getBitmapDownloaderTask();
            }
        }
        return null;
    }


}