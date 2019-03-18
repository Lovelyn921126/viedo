/*
 * FileName: HttpUploadUtil.java
 *
 * Company: 北京神州泰岳软件股份有限公司
 * Copyright 2011-2012 (C) Ultrapower Software CO., LTD. All Rights Reserved.
 */
package com.ultrapower.viedo.utils;

import java.io.File;
import java.net.URI;
import java.net.URL;
import java.nio.charset.Charset;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

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
 * 2017年12月5日 下午2:22:30          dell        4.1         To create
 * </p>
 *
 * @since 
 * @see     
 */
public class HttpUploadUtil {

    private static final String VK_UPLOADFILE_URL = Constants.VK_UOLOADFILE_URL;

    private static final String IMGFILE = "imgFile";
    private static final String MAXIMGFILE = "maxImgFile";
    private static final String SMALLIMGFILE = "smallImgFile";
    private static final String VIEDOFILE = "viedoFile";
    private static final String CODEFILE = "codeFile";

    /**
    * http 文件上传
    * @param filePath 文件路径   
    * @param type           类型 1 图片  2 大图 3 小图  4 二维码  5 视频 
    * @throws Exception
    */
    public static String fileUpload(String filePath, int type) throws Exception {
        File uploadFile = new File(filePath);
        if (!uploadFile.exists()) {
            return null;
        }
        return fileUpload(uploadFile, type);

    }

    /**
    * http 文件上传
    * @param filePath 文件路径   
    * @param type           类型 1 图片  2 大图 3 小图  4 二维码  5 视频 
    * @throws Exception
    */
    public static String fileUpload(File uploadFile, int type) throws Exception {

        String fileName = null;
        System.out.println("start................");
        CloseableHttpClient httpClient = null;
        CloseableHttpResponse httpResponse = null;
        httpClient = HttpClients.createDefault();
        // 把一个普通参数和文件上传给下面这个地址 
        // String url = URLEncoder.encode(VK_UPLOADFILE_URL, "UTF-8");
        URL url = new URL(VK_UPLOADFILE_URL);
        System.out.println("url============" + url);
        URI uri = new URI(url.getProtocol(), url.getHost(), url.getPath(), url.getQuery(), null);
        System.out.println("uri============" + uri);
        HttpPost post = new HttpPost(VK_UPLOADFILE_URL);
        //将文件转换成流对象  FileBody
        FileBody body = new FileBody(uploadFile);
        if (type == 1) {
            fileName = IMGFILE;
        } else if (type == 2) {
            fileName = MAXIMGFILE;
        } else if (type == 3) {
            fileName = SMALLIMGFILE;
        } else if (type == 4) {
            fileName = CODEFILE;
        } else {
            fileName = VIEDOFILE;
        }

        HttpEntity reqEntity = MultipartEntityBuilder.create().addPart(fileName, body).build();

        post.setEntity(reqEntity);
        System.out.println("VK_UPLOADFILE_URL======" + VK_UPLOADFILE_URL);
        httpResponse = httpClient.execute(post);
        HttpEntity resEntity = httpResponse.getEntity();
        String result = null;
        if (resEntity != null) {
            result = EntityUtils.toString(resEntity, Charset.forName("UTF-8"));
            System.out.println(result);
        }
        // 销毁
        EntityUtils.consume(resEntity);
        httpResponse.close();
        httpClient.close();
        return result;

    }
    /* public static String upload(String localFile, int type) {
        File file = new File(localFile);
        if (!file.exists()) {
            return null;
        }
        PostMethod filePost = new PostMethod(VK_UPLOADFILE_URL);
        HttpClient client = new HttpClient();
        String fileName = null;
        try {
            if (type == 1) {
                fileName = IMGFILE;
            } else {
                fileName = VIEDOFILE;
            }
            Part[] parts = { new FilePart(fileName, file) };
            filePost.setRequestEntity(new MultipartRequestEntity(parts, filePost.getParams()));
    
            client.getHttpConnectionManager().getParams().setConnectionTimeout(5000);
    
            int status = client.executeMethod(filePost);
            if (status == HttpStatus.SC_OK) {
                return "上传成功";
            } else {
                System.out.println("上传失败");
                return "上传失败";
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            filePost.releaseConnection();
        }
        return null;
    }
    */
    /*    public static String inputStreamUpload(String filePath, int type) {
        //创建HttpClient对象
        CloseableHttpClient client = HttpClients.createDefault();
        //构建POST请求   请求地址请更换为自己的。
        //1)
        HttpPost post = new HttpPost(VK_UPLOADFILE_URL);
        InputStream inputStream = null;
        try {
            //文件路径请换成自己的
            //2)
            File file = new File(filePath);
            if (!file.exists()) {
                return null;
            }
            inputStream = new FileInputStream(file);
            MultipartEntityBuilder builder = MultipartEntityBuilder.create();
            builder.setMode(HttpMultipartMode.BROWSER_COMPATIBLE);
            //第一个参数为 相当于 Form表单提交的file框的name值 第二个参数就是我们要发送的InputStream对象了
            //第三个参数是文件名
            //3)
            String fileName = null;
    
            if (type == 1) {
                fileName = IMGFILE;
            } else {
                fileName = VIEDOFILE;
            }
            builder.addBinaryBody(fileName, inputStream, ContentType.create("multipart/form-data"), "text01.txt");
            //4)构建请求参数 普通表单项
            StringBody stringBody = new StringBody("12", ContentType.MULTIPART_FORM_DATA);
            builder.addPart(fileName, stringBody);
            HttpEntity entity = builder.build();
            post.setEntity(entity);
            //发送请求
            HttpResponse response = client.execute(post);
            entity = response.getEntity();
            if (entity != null) {
                inputStream = entity.getContent();
                //转换为字节输入流
                BufferedReader br = new BufferedReader(new InputStreamReader(inputStream, Consts.UTF_8));
                String body = null;
                while ((body = br.readLine()) != null) {
                    System.out.println(body);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    
    }*/
}
