/*
 * FileName: DateTimeHandler.java
 *
 * Company: 北京神州泰岳软件股份有限公司
 * Copyright 2011-2012 (C) Ultrapower Software CO., LTD. All Rights Reserved.
 */
package com.ultrapower.viedo.utils.mybatis;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedJdbcTypes;
import org.apache.ibatis.type.MappedTypes;

import com.ultrapower.viedo.utils.date.DateTime;

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
 * 2018年4月26日 下午1:43:00          dell        4.1         To create
 * </p>
 *
 * @since
 * @see
 */
@MappedTypes({ com.ultrapower.viedo.utils.date.DateTime.class })
@MappedJdbcTypes({ JdbcType.TIMESTAMP })
public class DateTimeHandler extends BaseTypeHandler<DateTime> {

    /* (non-Javadoc)
     * @see org.apache.ibatis.type.BaseTypeHandler#getNullableResult(java.sql.ResultSet, java.lang.String)
     */
    @Override
    public DateTime getNullableResult(ResultSet arg0, String arg1) throws SQLException {
        Timestamp timestamp = arg0.getTimestamp(arg1);
        if (timestamp != null) {
            return new DateTime(timestamp.getTime());
        }
        return null;
    }

    /* (non-Javadoc)
     * @see org.apache.ibatis.type.BaseTypeHandler#getNullableResult(java.sql.ResultSet, int)
     */
    @Override
    public DateTime getNullableResult(ResultSet arg0, int arg1) throws SQLException {
        Timestamp timestamp = arg0.getTimestamp(arg1);
        if (timestamp != null) {
            return new DateTime(timestamp.getTime());
        }
        return null;
    }

    /* (non-Javadoc)
     * @see org.apache.ibatis.type.BaseTypeHandler#getNullableResult(java.sql.CallableStatement, int)
     */
    @Override
    public DateTime getNullableResult(CallableStatement callableStatement, int arg1) throws SQLException {
        // TODO Auto-generated method stub
        Timestamp timestamp = callableStatement.getTimestamp(arg1);
        if (timestamp != null) {
            return new DateTime(timestamp.getTime());
        }
        return null;

    }

    /* (non-Javadoc)
     * @see org.apache.ibatis.type.BaseTypeHandler#setNonNullParameter(java.sql.PreparedStatement, int, java.lang.Object, org.apache.ibatis.type.JdbcType)
     */
    @Override
    public void setNonNullParameter(PreparedStatement p, int arg1, DateTime arg2, JdbcType arg3) throws SQLException {
        p.setTimestamp(arg1, new Timestamp(arg2.MILLISECOND()));
        // p.setObject(arg1, arg2.MILLISECOND(), arg3.TIMESTAMP);

    }

}
