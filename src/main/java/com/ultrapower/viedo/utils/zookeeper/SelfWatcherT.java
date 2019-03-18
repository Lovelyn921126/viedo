/*
 * FileName: SelfWatcherT.java
 *
 * Company: 北京神州泰岳软件股份有限公司
 * Copyright 2011-2012 (C) Ultrapower Software CO., LTD. All Rights Reserved.
 */
package com.ultrapower.viedo.utils.zookeeper;

import java.util.List;

import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooDefs.Ids;
import org.apache.zookeeper.ZooKeeper;
import org.apache.zookeeper.data.Stat;

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
 * 2018年5月3日 下午5:12:47          dell        4.1         To create
 * </p>
 *
 * @since
 * @see
 */
public class SelfWatcherT {

    public static Watcher getWatch(final String msg) {
        return new Watcher() {

            @Override
            public void process(WatchedEvent arg0) {
                System.out.println(msg + "\t" + arg0.toString());

            }
        };
    }

    public static ZooKeeper zooKeeper = null;

    /**
     *
     */
    public SelfWatcherT() {
        try {
            zooKeeper = new ZooKeeper("localhost:2181", 3000, getWatch("create"));
            // zooKeeper.delete("/root", -1);
            zooKeeper.create("/root", "root".getBytes(), Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
            // String path = "/" + "root" + "/" + "s";

        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public static void setWatch() {
        try {
            Stat stat = zooKeeper.exists("/root", getWatch("exists"));
            if (stat != null) {
                byte[] bs = zooKeeper.getData("/root", getWatch("getData"), stat);
                System.out.println(new String(bs));

                //zooKeeper.setData("/root", "data".getBytes(), stat.getVersion());
                String path = zooKeeper.create("/root/s", new byte[0], Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL);
                System.out.println(path);

            }
        } catch (KeeperException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public static void trigeWatcher() {
        try {
            Stat stat = zooKeeper.exists("/root", false);

            //zooKeeper.setData("/root", "a".getBytes(), stat.getVersion());
        } catch (KeeperException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public static void trigeWatcherC() {
        try {
            Stat stat = zooKeeper.exists("/root/s", false);
            // zooKeeper.getChildren("/root", getWatch("getChildren"));
            //zooKeeper.setData("/root/s", "a".getBytes(), stat.getVersion());
        } catch (KeeperException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    private static void disConnection() {
        if (zooKeeper != null) {
            try {
                List<String> list = zooKeeper.getChildren("/root", getWatch("getChildren"));
                for (String string : list) {
                    zooKeeper.delete("/root" + "/" + string, -1);
                }
                zooKeeper.delete("/root", -1);
                zooKeeper.close();
            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        SelfWatcherT selfWatcherT = new SelfWatcherT();
        System.out.println("========================setWatch");
        selfWatcherT.setWatch();
        System.out.println("========================trigeWatcher");
        selfWatcherT.trigeWatcher();
        System.out.println("========================trigeWatcherC");
        selfWatcherT.trigeWatcherC();
        System.out.println("========================disConnection");
        selfWatcherT.disConnection();
    }
}
