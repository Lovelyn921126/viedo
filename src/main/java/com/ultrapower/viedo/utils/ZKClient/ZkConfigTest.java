package com.ultrapower.viedo.utils.ZKClient;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class ZkConfigTest {

    public static void main(String[] args) {
        ZkConfigMag mag = new ZkConfigMag();
        Config config = mag.downLoadConfigFromDB();
        System.out.println("....加载数据库配置...." + config.toString());
        mag.syncConfigToZk();
        System.out.println("....同步配置文件到zookeeper....");

        //歇会，这样看比较清晰
        try {
            ScheduledExecutorService scheduledThreadPool = Executors.newScheduledThreadPool(5);
            scheduledThreadPool.schedule(new Runnable() {

                @Override
                public void run() {
                    ZkGetConfigClient client = new ZkGetConfigClient();
                    client.getConfig();
                }
            }, 3, TimeUnit.SECONDS);
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        mag.upLoadConfigToDB("cwhcc", "passwordcc");
        System.out.println("....修改配置文件...." + config.toString());
        mag.syncConfigToZk();
        System.out.println("....同步配置文件到zookeeper....");

    }

}
