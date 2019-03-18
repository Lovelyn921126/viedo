/*
 * FileName: SftpUtils.java
 *
 * Company: 北京神州泰岳软件股份有限公司
 * Copyright 2011-2012 (C) Ultrapower Software CO., LTD. All Rights Reserved.
 */
package com.ultrapower.viedo.utils.ftp;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.util.Properties;
import java.util.Vector;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;
import com.jcraft.jsch.SftpException;
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
 * 2018年6月7日 上午10:31:59          dell        4.1         To create
 * </p>
 *
 * @since
 * @see
 */
public class SftpUtils {

    private static ChannelSftp sftp = null;

    private static Session session = null;

    private final static Log log = LogFactory.getLog(SftpUtils.class);

    /**
    * 实例化sfp 服务器
    * @return
    */
    public static boolean connect() {
        /**
         * sftp 的主机
         */
        String ftpHost = Constants.FtpIpAddr;
        //sftp 的端口
        String port = Constants.FtpPort;
        //sftp 的登录用户名
        String ftpUserName = Constants.FtpUserName;
        // sftp 的登录密码
        String ftpPassword = Constants.FtpPwd;

        int ftpPort = 0;
        if (port != null && !port.equals("")) {
            ftpPort = Integer.valueOf(port);
        }
        try {
            JSch jsch = new JSch(); // 创建JSch对象
            session = jsch.getSession(ftpUserName, ftpHost, ftpPort); // 根据用户名，主机ip，端口获取一个Session对象
            log.debug("Session created.");

            if (ftpPassword != null) {
                session.setPassword(ftpPassword); // 设置密码
            }
            Properties config = new Properties();
            //禁用SSH 远程主机的公钥检查
            config.put("StrictHostKeyChecking", "no");
            session.setConfig(config);
            //設置過期時間
            session.setTimeout(1000 * 60 * 30);
            //链接
            session.connect();
            log.debug("Session connected.");
            //打开通道
            sftp = (ChannelSftp) session.openChannel("sftp");
            log.debug("Opening Channel.");
            sftp.connect();
            log.debug("Connected successfully to ftpHost = " + ftpHost + ",as ftpUserName = " + ftpUserName + ", returning: " + sftp);
            return true;
        } catch (JSchException e) {
            e.printStackTrace();
            log.error("Connected fail to ftpHost = " + ftpHost + ",as ftpUserName = " + ftpUserName + ", returning: " + sftp);
            return false;
        }
    }

    /**
     * 创建自定义的sftp 服务
     * @param ftpBean
     * @return
     */
    public static boolean connect(FtpBean ftpBean) {

        String ftpHost = ftpBean.getIpAddr();
        Integer port = ftpBean.getPort();
        String ftpUserName = ftpBean.getUserName();
        String ftpPassword = ftpBean.getPwd();

        try {
            JSch jsch = new JSch(); // 创建JSch对象
            session = jsch.getSession(ftpUserName, ftpHost, port); // 根据用户名，主机ip，端口获取一个Session对象
            log.debug("Session created.");

            if (ftpPassword != null) {
                session.setPassword(ftpPassword); // 设置密码
            }
            Properties config = new Properties();
            //禁用SSH 远程主机的公钥检查
            config.put("StrictHostKeyChecking", "no");
            session.setConfig(config);
            session.setTimeout(1000 * 60 * 30);
            session.connect();
            log.debug("Session connected.");
            sftp = (ChannelSftp) session.openChannel("sftp");
            log.debug("Opening Channel.");
            sftp.connect();
            log.debug("Connected successfully to ftpHost = " + ftpHost + ",as ftpUserName = " + ftpUserName + ", returning: " + sftp);
            return true;
        } catch (JSchException e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 断开连接
     */
    public static void disConnct() {
        if (sftp != null) {
            if (sftp.isConnected()) {
                sftp.disconnect();
            } else if (sftp.isClosed()) {
                log.debug("sftp is closed already");
            }
        }
    }

    /**
     * 断开连接
     */
    public static void disConnct(ChannelSftp channelSftp) {
        if (channelSftp != null) {
            if (channelSftp.isConnected()) {
                channelSftp.disconnect();
            } else if (channelSftp.isClosed()) {
                log.debug("sftp is closed already");
            }
        }
    }

    /**
     * sftp 下载 文件
     * @param directory 要下载的文件 目录
     * @param downloadFile 要下载的文件名
     * @param saveFile 下载的文件全路径
     * @param sftp  stfp服務
     */
    public static boolean download(String directory, String downloadFile, String saveFile, ChannelSftp sChannelSftp) {
        File file = new File(saveFile);
        //下载
        return download(directory, downloadFile, file, sChannelSftp);
    }

    /**
     *  sftp 下载 文件
     * @param directory 要下载的文件 目录
     * @param downloadFile 要下载的文件名
     * @param saveFile 下载的文件全路径
     * @return
     */
    public static boolean download(String directory, String downloadFile, String saveFile) {
        File file = new File(saveFile);
        //下载
        return download(directory, downloadFile, file);

    }

    /**
     *
     *  sftp 下载 文件
     * @param directory 要下载的文件 目录
     * @param downloadFile 要下载的文件名
     * @param saveFile 下载的文件
     * @param sChannelSftp stfp服務
     * @return
     */
    public static boolean download(String directory, String downloadFile, File saveFile, ChannelSftp sChannelSftp) {
        boolean flag = false;

        FileOutputStream fileOutputStream;
        try {
            fileOutputStream = new FileOutputStream(saveFile);
            flag = download(directory, downloadFile, fileOutputStream, sChannelSftp);
        } catch (FileNotFoundException e) {
            log.error("file： [" + saveFile.getAbsolutePath() + "]  non-existent; ");
            e.printStackTrace();
        }
        return flag;
        //下载

    }

    /**
    *  sftp 下载 文件
     * @param directory 要下载的文件 目录
     * @param downloadFile 要下载的文件名
     * @param saveFile 下载的文件
    * @return
    */
    public static boolean download(String directory, String downloadFile, File saveFile) {
        boolean flag = false;
        FileOutputStream fileOutputStream;
        try {
            fileOutputStream = new FileOutputStream(saveFile);
            flag = download(directory, downloadFile, fileOutputStream, sftp);
        } catch (FileNotFoundException e) {
            log.error("file： [" + saveFile.getAbsolutePath() + "]  non-existent; ");
            e.printStackTrace();
        }
        return flag;
        //下载

    }

    /**
    *  sftp 下载 文件
     * @param directory 要下载的文件 目录
     * @param downloadFile 要下载的文件名
     * @param outputStream 下载的文件流
    * @param sChannelSftp sftp 服务
    * @return
    */
    public static boolean download(String directory, String downloadFile, OutputStream outputStream, ChannelSftp sChannelSftp) {
        boolean flag = false;
        //验证参数
        if (StringUtils.isBlank(directory) || StringUtils.isBlank(downloadFile) || outputStream == null) {
            return flag;
        }
        //校验 stfp 服务
        if (sChannelSftp == null && !sftp.isConnected()) {
            return flag;
        }
        try {
            //切换路径
            sChannelSftp.cd(directory);
            //下载
            sChannelSftp.get(downloadFile, outputStream);
            flag = true;
        } catch (Exception e) {
            e.printStackTrace();
            log.debug("sftp download [" + directory + "/" + downloadFile + "] fail");
            return flag;
        }
        return flag;
    }

    /**
    *  sftp 下载 文件
     * @param directory 要下载的文件 目录
     * @param downloadFile 要下载的文件名
     * @param outputStream 下载的文件流
    * @return
    */
    public static boolean download(String directory, String downloadFile, OutputStream outputStream) {
        boolean flag = false;
        if (StringUtils.isBlank(directory) || StringUtils.isBlank(downloadFile) || outputStream == null) {
            return flag;
        }
        try {
            //创建sftp 链接
            connect();
            //切换路径
            sftp.cd(directory);
            //下载
            sftp.get(downloadFile, outputStream);
            flag = true;
        } catch (Exception e) {
            e.printStackTrace();
            log.debug("sftp download [" + directory + "/" + downloadFile + "] fail");
            return flag;
        }
        return flag;
    }

    /**
     * sftp 上传  采用自定义的方式 （mode可选值为：ChannelSftp.OVERWRITE，ChannelSftp.RESUME，ChannelSftp.APPEND）
     * @param pathname 上传到ftp上 文件的目录
     * @param fileName 上传到ftp上的文件名
     * @param filePath 上传的文件全路径
     * @param  model 上传模式
     * @return
     */
    public static boolean upload(String pathname, String fileName, String filePath, int model) {

        File file = new File(filePath);
        return upload(pathname, fileName, file, model);

    }

    /**
     * sftp 上传 采用自定义的方式 （mode可选值为：ChannelSftp.OVERWRITE，ChannelSftp.RESUME，ChannelSftp.APPEND）
     * @param pathname 上传到ftp上 文件的目录
     * @param fileName 上传到ftp上的文件名
     * @param filePath 上传的文件
     * @param model 上传模式
     * @return
     */
    public static boolean upload(String pathname, String fileName, File filePath, int model) {
        boolean flag = false;

        if (!filePath.exists()) {
            return flag;
        }
        try {
            FileInputStream in = new FileInputStream(filePath);
            flag = upload(pathname, fileName, in, model);

        } catch (FileNotFoundException e) {
            log.error("文件:[" + filePath + "] 不存在");
            e.printStackTrace();
        }

        return flag;
    }

    /**
      * sftp 上传 采用自定义的方式 （mode可选值为：ChannelSftp.OVERWRITE，ChannelSftp.RESUME，ChannelSftp.APPEND）
     * @param pathname 上传到ftp上 文件的目录
     * @param fileName 上传到ftp上的文件名
     * @param in 上传的文件流
     * @param model 上传的模式
     * @return
     */
    public static boolean upload(String pathname, String fileName, FileInputStream in, int model) {
        boolean flag = false;
        if (StringUtils.isBlank(pathname) && StringUtils.isNotBlank(fileName) && in != null) {
            // sftp.put(in, fileName);
            String dst = null;
            if (fileName.startsWith("/")) {
                dst = pathname + fileName;
            } else {
                dst = pathname + "/" + fileName;
            }
            try {
                connect();
                //创建目录 如果目录存在 则不创建
                CreateDirecroty(dst);
                //切换目录
                changeWorkingDirectory(dst);
                OutputStream out = sftp.put(dst, model); // 使用OVERWRITE模式
                byte[] buff = new byte[1024 * 256]; // 设定每次传输的数据块大小为256KB
                int read;
                if (out != null) {
                    System.out.println("Start to read input stream");

                    do {
                        read = in.read(buff, 0, buff.length);
                        if (read > 0) {
                            out.write(buff, 0, read);
                        }
                        out.flush();
                    } while (read >= 0);
                    System.out.println("input stream read done.");
                }
                flag = true;
            } catch (Exception e) {
                log.error("上传文件 失败：[" + dst + "]");
                e.printStackTrace();
            }
        }
        return flag;
    }

    /**
    *  上傳文件 默认采用的方式（覆盖）
    * @param pathname
    * @param fileName
    * @param in
    * @return
    */
    public static boolean upload(String pathname, String fileName, FileInputStream in) {
        boolean flag = false;
        if (StringUtils.isBlank(pathname) && StringUtils.isNotBlank(fileName) && in != null) {
            // sftp.put(in, fileName);
            String dst = null;
            if (fileName.startsWith("/")) {
                dst = pathname + fileName;
            } else {
                dst = pathname + "/" + fileName;
            }
            try {
                connect();
                //创建目录 如果目录存在 则不创建
                CreateDirecroty(dst);
                //切换目录
                changeWorkingDirectory(dst);
                OutputStream out = sftp.put(dst, ChannelSftp.OVERWRITE); // 使用OVERWRITE模式
                byte[] buff = new byte[1024 * 256]; // 设定每次传输的数据块大小为256KB
                int read;
                if (out != null) {
                    System.out.println("Start to read input stream");

                    do {
                        read = in.read(buff, 0, buff.length);
                        if (read > 0) {
                            out.write(buff, 0, read);
                        }
                        out.flush();
                    } while (read >= 0);
                    System.out.println("input stream read done.");
                }
                flag = true;
            } catch (Exception e) {
                log.error("上传文件 失败：[" + dst + "]");
                e.printStackTrace();
            }
        }
        return flag;
    }

    /**
    * 删除 文件
    * @param path
    * @return
    */
    public static boolean remove(String path) {
        boolean flag = false;
        if (StringUtils.isNotBlank(path)) {
            return flag;
        }
        try {
            sftp.rmdir(path);
            flag = true;
        } catch (SftpException e) {
            log.error("delete ：[" + path + "] fail");
            e.printStackTrace();
            return flag;
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
            sftp.cd(directory);
        } catch (Exception e) {
            log.error("directory：[" + directory + "]不存在");
            e.printStackTrace();
            flag = false;
        }
        return flag;
    }

    /**
     * 判断ftp服务器文件是否存在
     * @param path
     * @return
     */
    public static boolean existsFile(String path) {
        boolean flag = false;
        try {
            Vector v = sftp.ls(path);
            if (v.size() > 0) {
                flag = true;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return flag;
    }

    /**
     *创建目录（只能向下创建一级路径）
     * @param dir
     * @return.
     */
    public static boolean makeDirectory(String dir) {
        boolean flag = false;
        try {
            //创建一级目录
            sftp.mkdir(dir);
            flag = true;
        } catch (Exception e) {
            log.error("mkdir ：[" + dir + "] fail");
            e.printStackTrace();
        }
        return flag;
    }

    /**
    *创建多层目录文件，如果有ftp服务器已存在该文件，则不创建，如果无，则创建
    * @param directory
    * @return
     * @throws UnsupportedEncodingException
     * @throws IOException
    */
    public static boolean CreateDirecroty(String remote) {
        boolean success = false;
        //验证目录是否存在 合法
        if (!remote.equalsIgnoreCase("/") && !changeWorkingDirectory(remote)) {
            //解决中文路径乱码
            String directory = "";
            try {
                directory = new String(remote.getBytes("GBK"), "iso-8859-1");
                //去除 路径的前后  /
                directory = directory.replace("/", " ").trim().replace(" ", "/");
                //遍历 路径 创建多级路径
                String[] diStrings = directory.split("/");
                StringBuilder stringBuilder = new StringBuilder();
                for (String path : diStrings) {
                    stringBuilder.append("/");
                    stringBuilder.append(path);
                    //解决中文路径乱码的问题
                    String dir = new String(stringBuilder.toString().getBytes("GBK"), "iso-8859-1");
                    if (!existsFile(stringBuilder.toString())) {
                        if (makeDirectory(dir)) {
                            log.info("创建目录[" + dir + "]成功");
                        } else {
                            log.info("创建目录[" + dir + "]失败");
                            return false;
                        }
                    }
                }
            } catch (UnsupportedEncodingException e) {
                log.error("path ：[" + directory + "] supportedEncoding fail");
                e.printStackTrace();
            }

        }
        return success;
    }

    public static void main(String[] args) throws Exception {
        FtpBean ftpBean = new FtpBean();
        ftpBean.setIpAddr("192.168.180.93");
        ftpBean.setPort(22);
        ftpBean.setUserName("root");
        ftpBean.setPwd("RedHatyhjc");
        ftpBean.setPath("/home/ithelp/");
        connect(ftpBean);
        File file = new File("E://ftp.xml");
        CreateDirecroty("/home/ithelp/HQ/");
        sftp.put("E://ftp.xml", "/home/ithelp/HQ/ftp.xml");
        /* sftp.cd("/home");
        Vector vector = sftp.ls("ithelp");
        System.out.println(StringUtils.join(vector, ","));*/
    }

}
