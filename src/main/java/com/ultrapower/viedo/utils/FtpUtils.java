/*
 * FileName: FtpUtils.java
 *
 * Company: 北京神州泰岳软件股份有限公司
 * Copyright 2011-2012 (C) Ultrapower Software CO., LTD. All Rights Reserved.
 */
package com.ultrapower.viedo.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;
import org.apache.commons.net.ftp.FTPReply;

import com.ultrapower.viedo.constants.Constants;

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
 * 2018年5月30日 下午8:02:55          dell        4.1         To create
 * </p>
 *
 * @since
 * @see
 */
public class FtpUtils {

    // private final static Log log = LogFactory.getLog(FtpUtils.class);

    private static FTPClient ftpClient = null;

    /**
    * 实例化 ftp 服务
    * @return
    * @throws Exception
    */
    public static boolean initFtpClient() throws Exception {
        ftpClient = new FTPClient();
        boolean flag = false;
        int reply;
        ftpClient.connect(Constants.FtpIpAddr, Integer.valueOf(Constants.FtpPort));
        ftpClient.login(Constants.FtpUserName, Constants.FtpPwd);
        //ftp 被动模式 --即连接是 服务端开发端口给 客户端  避免主动模式下 客户端开放端口给服务端是 开放的端口 在服务端的防火墙内
        ftpClient.enterLocalPassiveMode();
        ftpClient.setFileType(FTPClient.BINARY_FILE_TYPE);
        ftpClient.setDataTimeout(1000 * 60 * 30); //设置传输超时（30分钟）
        reply = ftpClient.getReplyCode();
        if (!FTPReply.isPositiveCompletion(reply)) {
            // //log.error("ftp:" + Constants.FtpIpAddr + " 登录失败");
            ftpClient.disconnect();
            return flag;
        }
        ftpClient.changeWorkingDirectory(Constants.FtpPath);
        flag = true;
        return flag;
    }

    /**
    * 实例化 ftp 服务
    * @return
    * @throws Exception
    */
    public static boolean initFtpClient(FtpBean ftpBean) throws Exception {
        ftpClient = new FTPClient();
        boolean flag = false;
        int reply;
        ftpClient.connect(ftpBean.getIpAddr(), ftpBean.getPort());
        ftpClient.login(ftpBean.getUserName(), ftpBean.getPwd());
        //ftp 被动模式 --即连接是 服务端开发端口给 客户端  避免主动模式下 客户端开放端口给服务端是 开放的端口 在服务端的防火墙内
        //sftpClient.enterLocalPassiveMode();
        ftpClient.setFileType(FTPClient.BINARY_FILE_TYPE);
        ftpClient.setDataTimeout(1000 * 60 * 30); //设置传输超时（30分钟）
        reply = ftpClient.getReplyCode();
        if (!FTPReply.isPositiveCompletion(reply)) {
            // //log.error("ftp:" + ftpBean.getIpAddr() + " 登录失败");
            System.out.println("ftp:" + ftpBean.getIpAddr() + " 登录失败");
            ftpClient.disconnect();
            return flag;
        }
        ftpClient.changeWorkingDirectory(ftpBean.getPath());
        flag = true;
        return flag;
    }

    /**
    * 关闭ftp
    */
    public static void closeFtp() {
        if (ftpClient != null && ftpClient.isConnected()) {

            try {
                //ftp 登出
                ftpClient.logout();
                //ftp 断开连接
                ftpClient.disconnect();
            } catch (Exception e) {
                ////log.error("ftp:" + Constants.FtpIpAddr + "关闭失败");
                e.printStackTrace();
            }
        }
    }

    /**
     * ftp 上传文件
     * @param pathname ftp服务保存地址
     * @param fileName 上传到ftp的文件名
     * @param filePath 文件的本地地址
     * @return
     */
    public static boolean uploadFtpFile(String pathname, String fileName, String filePath) {

        return uploadFtpFile(pathname, fileName, filePath, FTP.BINARY_FILE_TYPE);

    }

    /**
     * ftp 上传文件
     * @param pathname ftp服务保存地址
     * @param fileName 上传到ftp的文件名
     * @param filePath 文件的本地地址
     * @param fileType 文件類型
     * @return
     */
    public static boolean uploadFtpFile(String pathname, String fileName, String filePath, int fileType) {
        File file = new File(filePath);
        if (file.exists()) {
            return uploadFtpFile(pathname, fileName, file, fileType);
        }
        return false;
    }

    /**
     * ftp 上传文件
     * @param pathname ftp服务保存地址
     * @param fileName 上传到ftp的文件名
     * @param File 输入文件
     * @return
     */
    public static boolean uploadFtpFile(String pathname, String fileName, File file) {

        return uploadFtpFile(pathname, fileName, file, FTP.BINARY_FILE_TYPE);

    }

    /**
     * ftp 上传文件
     * @param pathname ftp服务保存地址
     * @param fileName 上传到ftp的文件名
     * @param File 输入文件
     * @param fileType 文件類型
     * @return
     */
    public static boolean uploadFtpFile(String pathname, String fileName, File file, int fileType) {
        FileInputStream fileInputStream = null;
        try {
            if (file.exists()) {
                fileInputStream = new FileInputStream(file);
                return uploadFtpFile(pathname, fileName, fileInputStream, fileType);
            } else {
                return false;
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } finally {
            if (fileInputStream != null) {
                try {
                    fileInputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                    return false;
                }
            }
        }
        return false;

    }

    /**
     * ftp 上传文件
     * @param pathname ftp服务保存地址
     * @param fileName 上传到ftp的文件名
     * @param inputStream 输入文件流
     * @return
     */
    public static boolean uploadFtpFile(String pathname, String fileName, FileInputStream inputStream) {
        return uploadFtpFile(pathname, fileName, inputStream, FTP.BINARY_FILE_TYPE);
    }

    /**
     * 上传文件
     * @param pathname ftp服务保存地址
     * @param fileName 上传到ftp的文件名
     * @param inputStream 输入文件流
     * @param fileType 文件類型
     * @return
     */
    public static boolean uploadFtpFile(String pathname, String fileName, FileInputStream inputStream, int fileType) {
        boolean flag = false;
        try {
            //log.info("开始上传文件");
            if (ftpClient != null) {
                initFtpClient();
            }

            ftpClient.setFileType(fileType);
            CreateDirecroty(pathname);
            ftpClient.changeWorkingDirectory(pathname);
            ftpClient.storeFile(fileName, inputStream);
            inputStream.close();
            ftpClient.logout();
            flag = true;
            //log.info("上传上传文件成功");
        } catch (Exception e) {
            //log.info("上传上传文件失敗");
            e.printStackTrace();
            return false;
        } finally {
            if (ftpClient.isConnected()) {
                try {
                    ftpClient.disconnect();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (null != inputStream) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        return true;

    }

    /** * 下载文件 *
     * @param pathname FTP服务器文件目录 *
     * @param filename 文件名称 *
     * @param localpath 下载后的文件路径 *
     * @return */
    public static boolean downloadFtpFile(String pathname, String filename, String localpath) {
        boolean flag = false;
        OutputStream outputStream = null;
        //log.info("开始下载文件");
        try {
            //实例化 ftp 服务
            if (ftpClient != null) {
                initFtpClient();
            }
            changeWorkingDirectory(pathname);
            FTPFile[] files = ftpClient.listFiles();
            for (FTPFile ftpFile : files) {
                if (ftpFile.getName().equalsIgnoreCase(filename)) {
                    File localFile = new File(localpath + "/" + ftpFile.getName());
                    outputStream = new FileOutputStream(localFile);
                    ftpClient.retrieveFile(ftpFile.getName(), outputStream);
                    outputStream.close();
                    flag = true;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            flag = false;
        } finally {
            if (ftpClient.isConnected()) {
                try {
                    ftpClient.disconnect();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (outputStream != null) {
                try {
                    outputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return flag;

    }

    /**
     * 改变目录路径
     * @param directory
     * @return
     */
    public static boolean changeWorkingDirectory(String directory) {
        boolean flag = true;
        try {
            flag = ftpClient.changeWorkingDirectory(directory);
            if (flag) {
                //log.info("进入文件夹" + directory + " 成功！");
            } else {
                //log.info("进入文件夹" + directory + " 失败！开始创建文件夹");
                // ftpClient.makeDirectory(directory);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return flag;
    }

    /**
    *创建多层目录文件，如果有ftp服务器已存在该文件，则不创建，如果无，则创建
    * @param directory
    * @return
     * @throws IOException
    */
    public static boolean CreateDirecroty(String remote) throws IOException {
        boolean success = false;

        if (!remote.equalsIgnoreCase("/") && !changeWorkingDirectory(remote)) {
            //解决中文路径乱码
            String directory = new String(remote.getBytes("GBK"), "iso-8859-1");
            //去除 路径的前后  /
            directory = directory.replace("/", " ").trim().replace(" ", "/");
            //遍历 路径 创建多级路径
            String[] diStrings = directory.split("/");
            for (String path : diStrings) {
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append("/");
                stringBuilder.append(path);
                //解决中文路径乱码的问题
                String dir = new String(stringBuilder.toString().getBytes("GBK"), "iso-8859-1");
                if (!existsFile(path)) {
                    if (makeDirectory(dir)) {
                        changeWorkingDirectory(dir);
                        //log.info("创建目录[" + dir + "]成功");
                    } else {
                        //log.info("创建目录[" + dir + "]失败");
                        return false;
                    }
                } else {
                    changeWorkingDirectory(stringBuilder.toString());
                }
            }
        }
        return success;
    }

    /**
    *创建目录（只能向下创建一级路径）
    * @param dir
    * @return.
    */
    public static boolean makeDirectory(String dir) {
        boolean success = false;
        try {
            //c
            success = ftpClient.makeDirectory(dir);
            if (success) {
                //log.info("创建文件夹" + dir + " 成功！");
            } else {
                //log.info("创建文件夹" + dir + " 失败！");
            }
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return success;
    }

    /**
     * 判断ftp服务器文件是否存在
     * @param path
     * @return
     */
    public static boolean existsFile(String path) {
        boolean flag = false;
        try {
            FTPFile[] files = ftpClient.listFiles();
            if (files.length > 0) {
                return true;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return flag;
    }

    /**
    *删除ftp上的文件
    * @param fileName
    * @return
    */
    public boolean removeFile(String fileName) {
        boolean flag = false;
        //log.debug(MessageFormat.format("待删除文件：{0}", fileName));
        try {
            fileName = new String(fileName.getBytes("GBK"), "iso-8859-1");
            flag = ftpClient.deleteFile(fileName);
            //log.debug(MessageFormat.format("删除文件：[{0}]", flag ? "成功" : "失败", fileName));
            return flag;
        } catch (IOException e) {

            e.printStackTrace();
            return false;
        }
    }

    /**
     * 删除空目录
     * @param dir
     * @return
     */
    public boolean removeDir(String dir) {
        if (!dir.startsWith("/")) {
            dir = "/" + dir;
        }
        String d;
        try {
            d = new String(dir.toString().getBytes("GBK"), "iso-8859-1");
            return ftpClient.removeDirectory(d);
        } catch (IOException e) {

            e.printStackTrace();
            return false;
        }

    }

    public static void main(String[] args) throws Exception {
        FtpBean ftpBean = new FtpBean();
        ftpBean.setIpAddr("192.168.180.93");
        ftpBean.setPort(22);
        ftpBean.setUserName("root");
        ftpBean.setPwd("RedHatyhjc");
        ftpBean.setPath("/home/ithelp/");
        initFtpClient(ftpBean);
        File file = new File("E://ftp.xml");
        uploadFtpFile("/home/ithelp/HQ", "20180404_process.xml", file);
    }
}
