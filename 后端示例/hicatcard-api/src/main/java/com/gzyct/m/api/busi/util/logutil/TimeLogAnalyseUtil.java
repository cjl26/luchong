package com.gzyct.m.api.busi.util.logutil;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Created by chenjiajian on 2017/6/21.
 */
public class TimeLogAnalyseUtil {

    public static void main(String[] args){
        readFileByChars("C:\\Users\\chenjiajian\\Desktop\\wxapp\\logs\\all.log","handleTime:","ms");
    }

    public static void  readFileByChars(String fileName, String prefix, String  suffix) {

        List<Float> floats = new ArrayList<>();
        Float total = (float)0;
        String str = "";
        FileInputStream fis = null;
        InputStreamReader isr = null;
        BufferedReader br = null; //用于包装InputStreamReader,提高处理性能。因为BufferedReader有缓冲的，而InputStreamReader没有。

        FileWriter fw = null;
        BufferedWriter bw = null;
        try {

            fis = new FileInputStream(fileName);

            // 从文件系统中的某个文件中获取字节
            isr = new InputStreamReader(fis);// InputStreamReader 是字节流通向字符流的桥梁,
            br = new BufferedReader(isr);// 从字符输入流中读取文件中的内容,封装了一个new InputStreamReader的对象
            while ((str = br.readLine()) != null) {
                int usedTimeIdxStart = str.lastIndexOf(prefix);
                if(usedTimeIdxStart>=0){
//                    System.out.println("usedTimeIdxStart:"+usedTimeIdxStart);
//                    System.out.println("用时:"+str.substring(usedTimeIdxStart+"alltime-".length(),str.length()));
                    int endIdx  = str.length();
                    if(suffix != null && str.indexOf(suffix) > 0){
                            endIdx = str.indexOf(suffix);
                    }

                    Float usedTime = Float.parseFloat(str.substring(usedTimeIdxStart+prefix.length(),endIdx));
                    floats.add(usedTime);
                    total += usedTime;
                    if(floats.size()%1000 == 0){
                        System.out.println("第"+floats.size()+"行分析完成");
                    }
                }

            }
            Collections.sort(floats, new Comparator<Float>() {
                @Override
                public int compare(Float f1, Float f2) {
                    return f2.compareTo(f1);
                }
            });
            System.out.println("file "+fileName);
            fw = new FileWriter(fileName+".analyse.log");
            bw = new BufferedWriter(fw);
            bw.write(fileName);
            bw.newLine();
            String analyseStr = "关键字:"+prefix+"当天请求总数:"+floats.size()+"|平均用时:"+total/floats.size()+"ms|最长用时:"+floats.get(0)+"ms"+"|最短用时:"+floats.get(floats.size()-1)+"ms";
            System.out.println(analyseStr);
            bw.write(analyseStr);
            bw.newLine();
            System.out.println("最长用时前10:");
            bw.write("最长用时前10:");
            bw.newLine();
            for(int i = 0;i< 10;i++){
                System.out.println(floats.get(i));
                bw.write(""+floats.get(i));
                bw.newLine();
            }
            bw.flush();
            // 当读取的一行不为空时,把读到的str的值赋给str1
//            System.out.println(str1);// 打印出str1
        } catch (FileNotFoundException e) {
            System.out.println("找不到指定文件");
        } catch (IOException e) {
            System.out.println("读取文件失败");
        }catch (Exception e){
            e.printStackTrace();
            System.out.println("读取文件失败");
        }
        finally {

            try {
                br.close();
                isr.close();
                fis.close();
                bw.close();
                fw.close();
                // 关闭的时候最好按照先后顺序关闭最后开的先关闭所以先关s,再关n,最后关m
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
