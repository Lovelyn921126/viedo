/*
 * FileName: FileUtil.java
 *
 * Company: 北京神州泰岳软件股份有限公司
 * Copyright 2011-2012 (C) Ultrapower Software CO., LTD. All Rights Reserved.
 */
package com.ultrapower.viedo.utils.file;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;

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
 * 2018年4月18日 上午9:49:45          dell        4.1         To create
 * </p>
 *
 * @since
 * @see
 */
public class FileUtil {
    private static HttpClient client = null;
    private static HttpPost post = null;
    private static HttpResponse response = null;
    private static String URL = "";

    public static String upload(File file) {
        client = HttpClientBuilder.create().build();
        FileBody body = new FileBody(file);
        String R = null;
        HttpEntity entity = MultipartEntityBuilder.create().addBinaryBody("file", file).build();
        post = new HttpPost(URL);
        post.setEntity(entity);

        try {
            response = client.execute(post);

            HttpEntity result = response.getEntity();
            R = EntityUtils.toString(entity, Charset.forName("UTF-8"));

        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return R;

    }
}
