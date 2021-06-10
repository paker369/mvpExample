package com.pakersite.example.app.utils;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Toast;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * 作者：jogger
 * 时间：2018/11/21 14:49
 * 描述：
 */
@SuppressWarnings("all")
public class FileUtil {
    private static final String FILE_NAME = "91gj";
    private static final String IMG_CACHE_FILE_PATH = FILE_NAME + File.separator + "img";
    private static final String TAG = FileUtil.class.getSimpleName();
    private static FileUtil sUtils;

    private FileUtil() {
    }

    public static FileUtil getInstance() {
        if (sUtils == null) {
            synchronized (FileUtil.class) {
                if (sUtils == null) {
                    sUtils = new FileUtil();
                }
            }
        }
        return sUtils;
    }

    public static File getImgCacheFile() {
        String sdCardPath = getSDCardPath();
        if (TextUtils.isEmpty(sdCardPath)) {
            return null;
        }
        File fileDir = new File(sdCardPath + File.separator + IMG_CACHE_FILE_PATH);
        if (!fileDir.exists())
            fileDir.mkdirs();
        return fileDir;
    }

    /**
     * 获取sd卡路径
     */
    public static String getSDCardPath() {
        File sdcardDir = null;
        // 判断SDCard是否存在
        boolean sdcardExist = Environment.getExternalStorageState().equals(
                Environment.MEDIA_MOUNTED) || Environment.getExternalStorageState().equals
                (Environment.MEDIA_SHARED);
        if (sdcardExist) {
            sdcardDir = Environment.getExternalStorageDirectory();
        }
        if (sdcardDir != null) {
            return sdcardDir.toString() + File.separator;
        } else {
            return null;
        }
    }

    public static void saveBmp2Gallery(Context context, Bitmap bmp, String picName) {
        String fileName = null;
        //系统相册目录
        String galleryPath = Environment.getExternalStorageDirectory()
                + File.separator + Environment.DIRECTORY_DCIM
                + File.separator + "Camera" + File.separator;


        // 声明文件对象
        File file = null;
        // 声明输出流
        FileOutputStream outStream = null;

        try {
            // 如果有目标文件，直接获得文件对象，否则创建一个以filename为名称的文件
            file = new File(galleryPath, picName + ".jpg");

            // 获得文件相对路径
            fileName = file.toString();
            // 获得输出流，如果文件中有内容，追加内容
            outStream = new FileOutputStream(fileName);
            if (null != outStream) {
                bmp.compress(Bitmap.CompressFormat.JPEG, 90, outStream);
            }

        } catch (Exception e) {
            e.getStackTrace();
        } finally {
            try {
                if (outStream != null) {
                    outStream.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }//通知相册更新
        MediaStore.Images.Media.insertImage(context.getContentResolver(),
                bmp, fileName, null);
        Intent intent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
        Uri uri = Uri.fromFile(file);
        intent.setData(uri);
        context.sendBroadcast(intent);
        Toast.makeText(context, "图片保存成功", Toast.LENGTH_SHORT).show();
    }

    public static File compressImg(String filePath) {
        File file = new File(filePath);
        Log.e(TAG, "-------->>" + filePath + ":" + file.exists());
        if (!file.exists()) return file;
        Bitmap bitmap = BitmapFactory.decodeFile(filePath);
        if (bitmap == null)
            return null;
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        int options = 100;
        bitmap.compress(Bitmap.CompressFormat.JPEG, options, baos);
//        boolean limit = baos.toByteArray().length / 1024 > 1024;
//        if (!limit) {
//            return file;
//        }
        long date = System.currentTimeMillis();
        file = new File(FileUtil.getImgCacheFile(), String.valueOf(date) + ".jpg");
        while (baos.toByteArray().length / 1024 > 1024) {//大小控制在1m以内
            baos.reset();
            options -= 10;
            bitmap.compress(Bitmap.CompressFormat.JPEG, options, baos);
        }
        ByteArrayInputStream bais = new ByteArrayInputStream(baos.toByteArray());
        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(file);
            byte[] buf = new byte[1024];
            int ret;
            while ((ret = bais.read(buf, 0, buf.length)) != -1) {
                fos.write(buf, 0, ret);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            bais.close();
            baos.close();
            if (fos != null)
                fos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return file;
    }

    /**
     * 移动文件
     *
     * @param srcFileName 源文件完整路径
     * @param destDirName 目的目录完整路径
     * @return 文件移动成功返回true，否则返回false
     */
    public static boolean moveFile(String srcFileName, String destDirName) {
        boolean result = false;
        FileInputStream fis = null;
        FileOutputStream fos = null;
        try {
            File srcFile = new File(srcFileName);
            if (!srcFile.exists() || !srcFile.isFile()) {
                result = false;
            } else {
                File destFile = new File(destDirName);
//                if (!destDir.exists())
//                    destDir.mkdirs();

                if (!destFile.getParentFile().exists()) {
                    destFile.getParentFile().mkdirs();
                }
                destFile.createNewFile();
                fis = new FileInputStream(srcFile);
                fos = new FileOutputStream(destDirName);
                byte[] buf = new byte[1024];
                int ret;
                while ((ret = fis.read(buf, 0, buf.length)) != -1) {
                    fos.write(buf, 0, ret);
                }
                result = true;
                srcFile.delete();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (fis != null) {
                try {
                    fis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (fos != null) {
                try {
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return result;
    }

    /**
     * 移动目录
     *
     * @param srcDirName  源目录完整路径
     * @param destDirName 目的目录完整路径
     * @return 目录移动成功返回true，否则返回false
     */
    public static boolean moveDirectory(String srcDirName, String destDirName) {

        File srcDir = new File(srcDirName);
        if (!srcDir.exists() || !srcDir.isDirectory())
            return false;

        File destDir = new File(destDirName);
        if (!destDir.exists())
            destDir.mkdirs();

        /**
         * 如果是文件则移动，否则递归移动文件夹。删除最终的空源文件夹
         * 注意移动文件夹时保持文件夹的树状结构
         */
        File[] sourceFiles = srcDir.listFiles();
        for (File sourceFile : sourceFiles) {
            if (sourceFile.isFile())
                moveFile(sourceFile.getAbsolutePath(), destDir.getAbsolutePath());
            else if (sourceFile.isDirectory())
                moveDirectory(sourceFile.getAbsolutePath(),
                        destDir.getAbsolutePath() + File.separator + sourceFile.getName());
            else
                ;
        }
        return srcDir.delete();
    }

    /**
     * 复制文件
     *
     * @param srcFileName  源文件完整路径
     * @param destFileName 目的完整路径
     * @return 文件复制成功返回true，否则返回false
     */
    public boolean copyFile(String srcFileName, String destFileName) {
        boolean result = false;
        FileInputStream fis = null;
        FileOutputStream fos = null;
        try {
            File srcFile = new File(srcFileName);
            if (!srcFile.exists() || !srcFile.isFile()) {
                result = false;
            } else {
                File destFile = new File(destFileName);
                if (!destFile.exists())
                    destFile.createNewFile();
                fis = new FileInputStream(srcFile);
                fos = new FileOutputStream(destFile);
                byte[] buf = new byte[1024];
                int ret;
                while ((ret = fis.read(buf, 0, buf.length)) != -1) {
                    fos.write(buf, 0, ret);
                }
                result = true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (fis != null) {
                try {
                    fis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (fos != null) {
                try {
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return result;
    }

    /**
     * 读取文件。
     *
     * @return message
     */
    public static String readFile(String filePath) {
        String result = "";
        InputStream in = null;
        ByteArrayOutputStream baos = null;
        BufferedInputStream bis = null;
        BufferedOutputStream bos = null;
        try {
            in = new FileInputStream(filePath);
            baos = new ByteArrayOutputStream();
            bis = new BufferedInputStream(in);
            bos = new BufferedOutputStream(baos);
            int ret;
            byte[] buf = new byte[1024];
            while ((ret = bis.read(buf, 0, buf.length)) != -1) {
                bos.write(buf, 0, ret);
            }
            result = bos.toString();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (in != null)
                    in.close();
                if (bis != null)
                    bis.close();
                if (baos != null)
                    baos.close();
                if (bos != null)
                    bos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return result;
    }

    /**
     * 写入文件。
     *
     * @return message
     */
    public static boolean writeFile(File file, String json) {
        boolean result = false;
        OutputStream out = null;
        ByteArrayInputStream bais = null;
        BufferedOutputStream bos = null;
        try {
            out = new FileOutputStream(file);
            bos = new BufferedOutputStream(out);
            bais = new ByteArrayInputStream(json.getBytes());
            int ret;
            byte[] buf = new byte[1024];
            while ((ret = bais.read(buf, 0, buf.length)) != -1) {
                bos.write(buf, 0, ret);
            }
            result = true;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (out != null)
                    out.close();
                if (bais != null)
                    bais.close();
                if (bos != null)
                    bos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return result;
    }
}
