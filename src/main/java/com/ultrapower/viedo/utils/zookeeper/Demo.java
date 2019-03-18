/*
 * FileName: Demo.java
 *
 * Company: 北京神州泰岳软件股份有限公司
 * Copyright 2011-2012 (C) Ultrapower Software CO., LTD. All Rights Reserved.
 */
package com.ultrapower.viedo.utils.zookeeper;

import java.util.List;

import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.ZooDefs.Ids;
import org.apache.zookeeper.ZooKeeper;

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
 * 2018年5月2日 下午6:11:44          dell        4.1         To create
 * </p>
 *
 * @since
 * @see
 */
public class Demo {
    private final static int TIME_OUT = 3000;

    public static void main(String[] args) throws Exception {
        ZooKeeper zkp = new ZooKeeper("localhost:2181", TIME_OUT, null);
        zkp.create("/myConf", "data1".getBytes(), Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL);
        if (zkp.exists("/myConf", false) != null) {
            System.out.println("myConf exists now.");
        }
        try {
            // 当节点名已存在时再去创建它会抛出KeeperException(即使本次的ACL、CreateMode和上次的不一样)
            zkp.create("/node1", "data1".getBytes(), Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
        } catch (KeeperException e) {
            System.out.println("KeeperException caught:" + e.getMessage());
        }
        zkp.close();
        zkp = new ZooKeeper("localhost:2181", TIME_OUT, null);
        if (zkp.exists("/myConf", false) == null) {
            System.out.println("node1 dosn't exists now.");
        }

        zkp.create("/node-", "same data".getBytes(), Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT_SEQUENTIAL);
        zkp.create("/node-", "same data".getBytes(), Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT_SEQUENTIAL);
        zkp.create("/node-", "same data".getBytes(), Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT_SEQUENTIAL);
        List<String> children = zkp.getChildren("/", null);
        for (String child : children) {
            System.out.println(child);
        }

        zkp.close();
    }
}
