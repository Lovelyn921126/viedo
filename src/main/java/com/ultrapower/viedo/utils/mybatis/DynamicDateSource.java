/*
 * FileName: DynamicDateSource.java
 *
 * Company: 北京神州泰岳软件股份有限公司
 * Copyright 2011-2012 (C) Ultrapower Software CO., LTD. All Rights Reserved.
 */
package com.ultrapower.viedo.utils.mybatis;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import javax.sql.DataSource;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

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
 * 2018年4月4日 下午3:19:39          dell        4.1         To create
 * </p>
 *
 * @since
 * @see
 */
public class DynamicDateSource extends AbstractRoutingDataSource {

    /**
     * master库 dataSource
     */
    private DataSource master;

    /**
     * slaves
     */
    private List<DataSource> slaves;

    private AtomicInteger counter = new AtomicInteger();

    @Override
    protected Object determineCurrentLookupKey() {
        // do nothing
        return null;
    }

    @Override
    public void afterPropertiesSet() {
        // do nothing
    }

    /* (non-Javadoc)
     * @see org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource#determineTargetDataSource()
     */
    @Override
    protected DataSource determineTargetDataSource() {
        DataSource reutrunData = null;
        if (DataSourceHolder.isMaster()) {
            reutrunData = master;
        } else if (DataSourceHolder.isSlave()) {
            int count = counter.getAndIncrement();
            if (count > 100000) {
                counter.set(0);
            }
            int n = slaves.size();
            int index = count % n;
            reutrunData = slaves.get(index);
        } else {
            reutrunData = master;
        }
        return reutrunData;
    }

    /**
     * @return the master
     */
    public DataSource getMaster() {
        return master;
    }

    /**
     * @param master the master to set
     */
    public void setMaster(DataSource master) {
        this.master = master;
    }

    /**
     * @return the slaves
     */
    public List<DataSource> getSlaves() {
        return slaves;
    }

    /**
     * @param slaves the slaves to set
     */
    public void setSlaves(List<DataSource> slaves) {
        this.slaves = slaves;
    }

}
