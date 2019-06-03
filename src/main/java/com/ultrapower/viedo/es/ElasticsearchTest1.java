package com.ultrapower.viedo.es;

import java.net.InetAddress;
import java.net.UnknownHostException;

import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.elasticsearch.transport.client.PreBuiltTransportClient;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ElasticsearchTest1 {
public final static String HOST = "192.168.1.140";
    
    public final static int PORT = 9300; //http请求的端口是9200，客户端是9300
    
    private TransportClient client = null;
    /**
     * 获取客户端连接信息
     * @Title: getConnect 
     * @author sunt  
     * @date 2017年11月23日
     * @return void
     * @throws UnknownHostException 
     */
    @SuppressWarnings({ "resource", "unchecked" })
    @Before
    public void getConnect() throws UnknownHostException {
        client = new PreBuiltTransportClient(Settings.EMPTY).addTransportAddresses(
                 new InetSocketTransportAddress(InetAddress.getByName(HOST),PORT));
        log.info("连接信息:" + client.toString());
    }
    
    /**
     * 关闭连接
     * @Title: closeConnect 
     * @author sunt  
     * @date 2017年11月23日
     * @return void
     */
    @After
    public void closeConnect() {
        if(null != client) {
        	log.info("执行关闭连接操作...");
            client.close();
        }
    }
}
