package com.pakersite.example.app.utils;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Environment;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class SaveBitmapToPhoto {
    /**
     * 保存图片到指定路径
     *
     * @param context
     * @param bitmap   要保存的图片
     * @return true 成功 false失败
     */
    public static boolean saveImageToGallery(Context context, Bitmap bitmap) {
        // 保存图片至指定路径
        final String SAVE_PIC_PATH = Environment.getExternalStorageState().equalsIgnoreCase(Environment.MEDIA_MOUNTED)
                ? Environment.getExternalStorageDirectory().getAbsolutePath() : "/mnt/sdcard";//保存到SD卡
        final String SAVE_REAL_PATH = SAVE_PIC_PATH + "/49tuku/";//保存的确切位置


        File foder = new File(SAVE_REAL_PATH);
        if (!foder.exists()) {
            //创建文件夹
            foder.mkdirs();
        }
        File file = new File(SAVE_REAL_PATH, System.currentTimeMillis() + ".png");
        if (file.exists()) {
            file.delete();
        }
        try {
            file.createNewFile();
            BufferedOutputStream outputStream = new BufferedOutputStream(new FileOutputStream(file));
            boolean isSuccess = bitmap.compress(Bitmap.CompressFormat.JPEG, 80, outputStream);
            outputStream.flush();
            outputStream.close();
            //发广播告诉相册有图片需要更新，这样可以在图册下看到保存的图片了
            Uri uri = Uri.fromFile(file);
            context.sendBroadcast(new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE, uri));
            if (isSuccess) {
                return true;
            } else {
                return false;
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        return false;
    }


//        String storePath = Environment.getExternalStorageDirectory().getAbsolutePath() + File.separator + "49tuku";
//        File appDir = new File(storePath);
//        if (!appDir.exists()) {
//            appDir.mkdir();
//        }
//
//        File file = new File(appDir, fileName);
//
//        try {
//            file.createNewFile();
//            FileOutputStream fos = new FileOutputStream(file);
//            //通过io流的方式来压缩保存图片(80代表压缩20%)
//            boolean isSuccess = bitmap.compress(Bitmap.CompressFormat.JPEG, 80, fos);
//            fos.flush();
//            fos.close();
//
//            //发送广播通知系统图库刷新数据
//            Uri uri = Uri.fromFile(file);
//            context.sendBroadcast(new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE, uri));
//            if (isSuccess) {
//                ArmsUtils.makeText(context,"图片保存成功");
//                return true;
//            } else {
//                ArmsUtils.makeText(context,"图片保存失败");
//                return false;
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        return false;
//    }

}
