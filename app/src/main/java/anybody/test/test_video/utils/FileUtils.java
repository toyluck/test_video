package anybody.test.test_video.utils;

import android.content.Context;
import android.os.Environment;


import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import anybody.test.test_video.common.Contant;

public class FileUtils {

    /**
     * 保存路径的文件夹名称
     */
    public static String DIR_NAME = "qupai_video_test";

    /**
     * 给指定的文件名按照时间命名
     */
    private static SimpleDateFormat OUTGOING_DATE_FORMAT =
            new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss-SSS");

    /**
     * 得到指定的Video保存路径
     *
     * @return
     */
    public static File getDoneVideoPath() {
        File dir = new File(Environment.getExternalStorageDirectory()
                + File.separator + DIR_NAME);

        if (!dir.exists()) {
            dir.mkdirs();
        }

        return dir;
    }

    /**
     * 得到输出的Video保存路径
     *
     * @return
     */
    public static String newOutgoingFilePath() {
        String str = OUTGOING_DATE_FORMAT.format(new Date());
        String fileName = getDoneVideoPath() + File.separator
                + "video_" + str + ".mp4";
        return fileName;
    }

    public static void copyFiles(File file, Context context) {
        if (!file.exists()) {
            return;
        }
        if (file.isDirectory()) {
            File[] files = file.listFiles();
            for (File f : files) {
                copyFiles(f, context);
            }
        } else {
            File targetFile = new File(Contant.VIDEOPATH);
            System.out.println(targetFile.exists()+"   FileUtils.copyFiles - > " + targetFile);

            File parentFile = targetFile.getParentFile();
            if (!parentFile.exists()) {
                parentFile.mkdirs();
            }
            if (targetFile.exists()) {
                try {
                    targetFile.createNewFile();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            try {
                BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(targetFile));
                FileInputStream fis = new FileInputStream(file);
                byte[] buf = new byte[1024];
                int length = 0;
                while ((length = fis.read(buf)) > 0) {
                    bos.write(buf, 0, length);
                }
                fis.close();
                bos.close();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {


            } finally {

            }

        }
    }
}
