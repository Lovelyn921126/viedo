/*
 * FileName: FileOperationUtils.java
 *
 * Company: 北京神州泰岳软件股份有限公司
 * Copyright 2011-2012 (C) Ultrapower Software CO., LTD. All Rights Reserved.
 */
package com.ultrapower.viedo.utils;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.awt.image.ConvolveOp;
import java.awt.image.Kernel;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.security.MessageDigest;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

import org.apache.commons.lang.StringUtils;

import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGEncodeParam;
import com.sun.image.codec.jpeg.JPEGImageEncoder;
import com.ultrapower.viedo.constants.Constants;

import flex.messaging.util.URLDecoder;
import it.sauronsoftware.jave.Encoder;
import it.sauronsoftware.jave.MultimediaInfo;

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
 * 2017年11月29日 下午5:53:44          dell        4.1         To create
 * </p>
 *
 * @since 
 * @see     
 */
public class FileOperationUtils {

    private static URL url;
    private static HttpURLConnection con;
    private static int state = -1;
    private static String HTTP = "http://";
    private static String HTTPS = "https://";

    /**
    * 获取视频时长
    * @param file
    * @return
    */
    public static String getViedoLength(File file) {
        StringBuilder stringBuilder = new StringBuilder();
        Encoder encoder = new Encoder();
        // File[] files = file.listFiles();
        long sum = 0;

        try {
            MultimediaInfo info = encoder.getInfo(file);
            sum += info.getDuration();
        } catch (Exception e) {
            e.printStackTrace();
        }

        sum = sum / 1000;//获取时长的总共秒
        long seond = sum % 60;//获取时长的秒
        long minite = sum / 60 % 60; //获取时长的分钟数
        long hour = sum / 60 / 60;
        DecimalFormat df = new DecimalFormat("00");

        stringBuilder.append(df.format(hour));
        stringBuilder.append(":" + df.format(minite));
        stringBuilder.append(":" + df.format(seond));
        return stringBuilder.toString();
    }

    /**
     * 获取文件的存储路径 不带后缀的 请使用时自行添加
     * @param type 获取路径的类型 0 视频的路径 1 图片的路径2 临时路径
     * @return
     * @throws UnsupportedEncodingException 
     */
    public static String getPath(int type) throws UnsupportedEncodingException {

        SimpleDateFormat sFormat = new SimpleDateFormat("yyyyMMdd");
        String datePath = sFormat.format(new Date());
        boolean result = true;
        StringBuilder path = new StringBuilder();
        if (type == 0) {
            path.append(Constants.UPLOAD_VIEDO_PATH);
        } else if (type == 1) {
            path.append(Constants.UPLOAD_IMG_PATH);
        } else if (type == 2) {
            path.append(Constants.UPLOAD_TEMP_PATH);
        } else {
            return null;
        }

        path.append(File.separator);
        path.append(datePath);
        //0 为 视频 1 为图片

        path.append(File.separator);
        //path.append(filePath);

        File saveFile = new File(path.toString());
        if (!saveFile.exists()) {
            result = saveFile.mkdirs();
            if (!result) {
                return null;
            }
        }
        String fileName = UUID.randomUUID().toString();
        fileName.replaceAll(".*\\\\|.*/|\\s", "");
        path.append(fileName);
        String newPath = "";

        newPath = URLDecoder.decode(path.toString(), "utf-8");

        return newPath;
    }

    /**
     * 获取文件MD5 
     * @param file
     * @return
     */
    public static String getFileMd5(File file) {
        if (!file.exists() || !file.isFile()) {
            return null;
        }
        String value = null;
        FileInputStream in = null;
        try {
            in = new FileInputStream(file);
        } catch (FileNotFoundException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }
        try {

            MappedByteBuffer byteBuffer = in.getChannel().map(FileChannel.MapMode.READ_ONLY, 0, file.length());
            MessageDigest md5 = MessageDigest.getInstance("MD5");
            md5.update(byteBuffer);
            BigInteger bi = new BigInteger(1, md5.digest());
            value = bi.toString(16);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (null != in) {
                try {
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return value;

    }

    public static void tailorImgToStandard(File file, String outFileUrl) {
        if (file == null) {
            return;
        }
        BufferedInputStream in = null;
        // String[] ratio = Constants.UPLOAD_IMG_RATIO.split("*");
        /* int height = Integer.valueOf(Constants.UPLOAD_IMG_HEIGHT);
        int weight = Integer.valueOf(Constants.UPLOAD_IMG_WEIGH);*/
        int height = 200;
        int weight = 300;
        BufferedOutputStream outputStream = null;
        try {
            in = new BufferedInputStream(new FileInputStream(file));
            Image image = ImageIO.read(in);
            BufferedImage bufferedImage = new BufferedImage(height, weight, BufferedImage.TYPE_INT_RGB);
            bufferedImage.getGraphics().drawImage(image, 0, 0, height, weight, null);
            outputStream = new BufferedOutputStream(new FileOutputStream(outFileUrl));
            ImageIO.write(bufferedImage, "PNG", outputStream);

        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            try {
                outputStream.close();
                in.close();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

        }

    }

    /**
     * 
     * @param originalFile 原始文件
     * @param resizedFile 压缩后的文件
     * @param scale  1 200*150 (高级搜索) 2 240*180(首页) 3 120*90 (相似推荐)
     * @param quality
     * @throws IOException
     */
    public static void resize(File originalFile, File resizedFile, int scale) throws IOException {

        int height = 0;
        int width = 0;
        if (scale == 1) {
            height = Integer.valueOf(Constants.UPLOAD_IMG_HEIGHT);
            width = Integer.valueOf(Constants.UPLOAD_IMG_WEIGH);
        } else if (scale == 2) {
            height = Integer.valueOf(Constants.UPLOAD_MAX_IMG_HEIGHT);
            width = Integer.valueOf(Constants.UPLOAD_MAX_IMG_WEIGH);
        } else {
            height = Integer.valueOf(Constants.UPLOAD_SMALL_IMG_HEIGHT);
            width = Integer.valueOf(Constants.UPLOAD_SMALL_IMG_WEIGH);
        }

        // int height = 300;
        // int width = 200;

        ImageIcon icon = new ImageIcon(originalFile.getCanonicalPath());
        Image image = icon.getImage();
        // height = (int) ((image.getHeight(null)) * scale);
        // width = (int) ((image.getWidth(null)) * scale);
        Image resizedImage = image.getScaledInstance(width, height, Image.SCALE_SMOOTH);
        Image temp = new ImageIcon(resizedImage).getImage();
        BufferedImage bufferedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        Graphics graphics = bufferedImage.getGraphics();
        graphics.setColor(Color.white);
        graphics.fillRect(0, 0, temp.getWidth(null), temp.getHeight(null));
        graphics.drawImage(temp, 0, 0, null);
        graphics.dispose();
        float softenFactor = 0.05f;
        float[] softenArray = { 0, softenFactor, 0, softenFactor, 1 - (softenFactor * 4), softenFactor, 0, softenFactor, 0 };

        Kernel kernel = new Kernel(3, 3, softenArray);
        ConvolveOp cOp = new ConvolveOp(kernel, ConvolveOp.EDGE_NO_OP, null);
        bufferedImage = cOp.filter(bufferedImage, null);
        FileOutputStream out = new FileOutputStream(resizedFile);
        JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(out);
        JPEGEncodeParam param = encoder.getDefaultJPEGEncodeParam(bufferedImage);
        param.setQuality(1, true);
        encoder.setJPEGEncodeParam(param);
        encoder.encode(bufferedImage);
        bufferedImage.flush();
        out.close();

    }

    /**
     * 验证url 合法性
     * @param urlString url路径
     * @return
     */
    public static boolean URLAvailability(String urlString) {

        //int count = 0;
        String httpUrl = "";
        String httpsUrl = "";
        if (StringUtils.isBlank(urlString)) {
            return false;
        }
        if (urlString.startsWith("www.")) {
            httpUrl = HTTP + urlString;
            httpsUrl = HTTPS + urlString;
        }
        // while (count < 3) {
        try {
            state = vailUrl(httpUrl);

            System.out.println("urlString===" + httpUrl + "state==" + state);
            if (state == 200) {
                return true;
            } else {
                state = vailUrl(httpsUrl);
                System.out.println("urlString===" + httpsUrl + "state==" + state);
                if (state == 200) {
                    return true;
                }
            }

        } catch (Exception e) {
            return false;

        }
        //  }
        return false;

    }

    private static int vailUrl(String urlString) {
        URL url;
        HttpURLConnection con;
        int result = 0;
        try {
            url = new URL(urlString);
            con = (HttpURLConnection) url.openConnection();
            result = con.getResponseCode();
        } catch (MalformedURLException e) {

            e.printStackTrace();
        } catch (IOException e) {

            e.printStackTrace();
        }

        return result;
    }

    public static void main(String[] args) {

        System.out.println(URLAvailability("www.baiduss.com"));
        //System.out.println(getViedoLength(new File("F://test.mp4")));
        //System.out.println(getFileMd5(new File("E://test.mp4")));
        //save(new File("F://test.mp4"), UUID.randomUUID() + ".mp4");

        //tailorImgToStandard(new File("E://ttt.png"), "E:/t.png");

        // resize(new File("E://ttt.png"), new File("E:/txx.png"), 1, 1);
    }
}
