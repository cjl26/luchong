package com.gzyct.m.api.busi.util.logutil;

import java.io.*;
import java.nio.channels.FileChannel;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Created by chenjiajian on 2018/2/7.
 */
public class MergeFiles {

    public static void main(String[] s ){
        String path = "C:\\Users\\chenjiajian\\Desktop\\wxapp\\logs";
        mergeFiles(path,path+"\\all.log");
    }

    /**
     * 读取某个文件夹下的所有文件
     */
    public static String[] readfile(String filepath)  {
        try {

            File file = new File(filepath);
            if (!file.isDirectory()) {
                System.out.println("文件");
                System.out.println("path=" + file.getPath());
                System.out.println("absolutepath=" + file.getAbsolutePath());
                System.out.println("name=" + file.getName());
                return null;

            } else {
                System.out.println("文件夹");
                String[] filelist = file.list();

                return filelist;
            }

        } catch (Exception e) {
            System.out.println("readfile()   Exception:" + e.getMessage());
            return null;
        }
    }

    public static boolean mergeFiles(String filesPath, String resultPath) {
        File file = new File(filesPath);
        File[] files = file.listFiles();

        List<File> fileList = new ArrayList<File>();
        for (File f : files) {
            fileList.add(f);
        }

        Collections.sort(fileList, new Comparator<File>() {
            @Override
            public int compare(File o1, File o2) {
                if (o1.isDirectory() && o2.isFile())
                    return -1;
                if (o1.isFile() && o2.isDirectory())
                    return 1;
                return o2.getName().compareTo(o1.getName());
            }
        });

        File resultFile = new File(resultPath);

        try {
            FileChannel resultFileChannel = new FileOutputStream(resultFile, true).getChannel();
            for (int i = 0; i < fileList.size(); i ++) {
                FileChannel blk = new FileInputStream(files[i]).getChannel();
                resultFileChannel.transferFrom(blk, resultFileChannel.size(), blk.size());
                blk.close();
            }
            resultFileChannel.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return false;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }

        return true;
    }
}
