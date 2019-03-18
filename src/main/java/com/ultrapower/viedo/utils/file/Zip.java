package com.ultrapower.viedo.utils.file;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/*
 * FileName: Zip.java
 *
 * Company: 北京神州泰岳软件股份有限公司
 * Copyright 2011-2012 (C) Ultrapower Software CO., LTD. All Rights Reserved.
 */

/**
 * <p>
 * Description:
 * </p>
 *
 * @author dell
 * @version 4.1

 * <p>
 * History:
 *
 * Date                     Author         Version     Description
 * ---------------------------------------------------------------------------------
 * 2018年4月17日 上午10:58:02          dell        4.1         To create
 * </p>
 *
 * @since
 * @see
 */
public class Zip {
    public static void main(String[] args) {
        /*     FileInputStream fileInputStream = null;
        ZipOutputStream outputStream = null;
        try {
            File file = new File("E://t.png");
            fileInputStream = new FileInputStream(file);
            outputStream = new ZipOutputStream(new FileOutputStream("E://t.zip"));
        
            outputStream.putNextEntry(new ZipEntry(file.getName()));
            outputStream.setComment("测试");
            int count = 0;
            int max = 1024;
            byte[] buffer = new byte[max];
            while ((count = fileInputStream.read(buffer, 0, max)) != -1) {
                outputStream.write(buffer, 0, count);
            }
            outputStream.flush();
            outputStream.closeEntry();
        
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            try {
                fileInputStream.close();
                outputStream.close();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        
        }*/
        try {
            System.out.println(zip("C://Users//dell//Desktop//txt", "F://test.zip", ""));
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

    public static ZipOutputStream createOutputSteatm(String path) {
        ZipOutputStream outputStream = null;
        try {
            File file = new File(path);
            if (file.exists()) {
                file.delete();
            }
            file.createNewFile();
            outputStream = new ZipOutputStream(new FileOutputStream(file));
            return outputStream;
        } catch (Exception e) {
            e.printStackTrace();
            outputStream = null;
        }
        return outputStream;
    }

    /**
     *zip
     * @param filePath 要压缩文件的路径
     * @param zipPath 压缩文件的路径
     * @return
     * @throws IOException
     */
    public static boolean zip(String filePath, String zipPath, String path) throws IOException {
        boolean result = false;
        File file = new File(filePath);

        if (!file.exists()) {
            return result;
        }
        ZipOutputStream outputStream = createOutputSteatm(zipPath);
        outputStream.setLevel(6);
        if (file.isDirectory()) {
            result = zipDirectory(outputStream, file, path);
        } else {
            result = zipFile(outputStream, file, path);
        }
        outputStream.close();
        return result;
    }

    public static boolean zipFile(ZipOutputStream zos, File filePath, String path) throws IOException {

        FileInputStream stream = null;
        ZipEntry zipEntry = new ZipEntry(path + filePath.getName());
        zos.putNextEntry(zipEntry);
        try {
            stream = new FileInputStream(filePath);
            int max = 1024;
            byte[] buffer = new byte[max];
            int count = 0;

            while ((count = stream.read(buffer, 0, max)) != -1) {
                zos.write(buffer, 0, count);
            }
            zos.flush();
            zos.closeEntry();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            if (stream != null) {
                stream.close();
            }
        }
        return true;
    }

    public static void createZipNode(ZipOutputStream zos, String filePath) {
        try {
            ZipEntry zipEntry = new ZipEntry(filePath);
            zos.putNextEntry(zipEntry);
            zos.closeEntry();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static boolean zipDirectory(ZipOutputStream zos, File filePath, String path) throws IOException {
        boolean result = false;
        String p = filePath.getName() + File.separator;
        createZipNode(zos, p);
        File[] files = filePath.listFiles();
        for (File file : files) {
            if (file.isDirectory()) {
                result = zipDirectory(zos, file, p);
            } else {
                result = zipFile(zos, file, p);
            }
            if (!result) {
                return false;
            }
        }

        return true;
    }
}
