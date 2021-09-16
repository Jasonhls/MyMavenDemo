package com.cn.file;

import java.io.*;
import java.nio.channels.Channels;
import java.nio.channels.FileChannel;
import java.nio.channels.WritableByteChannel;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 * 将图片压缩成zip格式文件的工具
 */
public class ZipFileUtils {
    private static String FILE_NAME = "demo";
    private static String JPG_FILE = "C:\\Users\\Jason\\Desktop\\测试hls\\demo.mp4";
    private static String ZIP_FILE = "C:\\Users\\Jason\\Desktop\\测试hls\\d.zip";
    private static Integer FILE_SIZE = 0;
    private static String SUFFIX_FILE = ".mp4";

    public static void main(String[] args) {
//        zipFileNoBuffer();//耗时113848
//        zipFileBuffer();//耗时3169
        zipFileChannel();//耗时35
    }

    public static void zipFileNoBuffer(){
        File zipFile = new File(ZIP_FILE);
        try {
            ZipOutputStream zipOut = new ZipOutputStream(new FileOutputStream(zipFile));
            //开始时间
            long beginTime = System.currentTimeMillis();

            for (int i = 0;i < 5;i++){
                InputStream input = new FileInputStream(JPG_FILE);
                zipOut.putNextEntry(new ZipEntry(FILE_NAME + i));
                int temp = 0;
                while((temp = input.read()) != -1){
                    zipOut.write(temp);
                }
            }
            printInfo(beginTime);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public static void zipFileBuffer(){
        File zipFile = new File(ZIP_FILE);
        try {
            ZipOutputStream zipOut = new ZipOutputStream(new FileOutputStream(zipFile));
            BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(zipOut);
            //开始时间
            long beginTime = System.currentTimeMillis();

            for (int i = 0;i < 5;i++){
                BufferedInputStream bufferedInputStream = new BufferedInputStream(new FileInputStream(JPG_FILE));
                zipOut.putNextEntry(new ZipEntry(FILE_NAME + i));
                int temp = 0;
                while((temp = bufferedInputStream.read()) != -1){
                    bufferedOutputStream.write(temp);
                }
            }
            printInfo(beginTime);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public static void zipFileChannel(){
        //开始时间
        long beginTime = System.currentTimeMillis();
        File zipFile = new File(ZIP_FILE);
        try {
            ZipOutputStream zipOut = new ZipOutputStream(new FileOutputStream(zipFile));
            WritableByteChannel writableByteChannel = Channels.newChannel(zipOut);

            for (int i = 0;i < 5;i++){
                FileChannel fileChannel = new FileInputStream(JPG_FILE).getChannel();
                zipOut.putNextEntry(new ZipEntry(i + SUFFIX_FILE));
                fileChannel.transferTo(0,1024,writableByteChannel);
            }
            printInfo(beginTime);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public static void printInfo(long beginTime){
        long endTime = System.currentTimeMillis();
        System.out.println("consum time:"+ (endTime - beginTime));
    }
}
