/*
 * FileName: SelfWatcher.java
 *
 * Company: 北京神州泰岳软件股份有限公司
 * Copyright 2011-2012 (C) Ultrapower Software CO., LTD. All Rights Reserved.
 */
package com.ultrapower.viedo.utils.zookeeper;

import java.io.IOException;

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
 * 2018年5月3日 下午1:46:44          dell        4.1         To create
 * </p>
 *
 * @since
 * @see
 */
public class SelfWatcher implements Watcher {
    ZooKeeper zk = null;

    /* (non-Javadoc)
     * @see org.apache.zookeeper.Watcher#process(org.apache.zookeeper.WatchedEvent)
     */
    @Override
    public void process(WatchedEvent event) {
        System.out.println(event.getPath());
        Stat stat;
        try {
            stat = zk.exists(event.getPath(), this);
            zk.getData(event.getPath(), this, stat);
        } catch (KeeperException | InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

    /**
     *
     */
    public SelfWatcher(String url) {
        // TODO Auto-generated constructor stub
        try {
            zk = new ZooKeeper(url, 3000, this);

            zk.create("/tNode", "a".getBytes(), Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL);

        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (KeeperException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    void setWatcher() {
        try {
            Stat stat = zk.exists("/tNode", true);
            if (stat != null) {
                zk.getData("/tNode", true, stat);
            }
        } catch (KeeperException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    void trigeWatcher() {
        try {
            Stat stat = zk.exists("/tNode", true);
            zk.setData("/tNode", "a".getBytes(), stat.getVersion());
        } catch (KeeperException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    void disConnect() {
        if (zk != null) {
            try {

                zk.close();
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        SelfWatcher watcher = new SelfWatcher("localhost:2181");
        watcher.setWatcher();
        Thread.sleep(1000);
        watcher.trigeWatcher();
        // watcher.disConnect();

    }

}
