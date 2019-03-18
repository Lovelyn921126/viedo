/*
 * FileName: R.java
 *
 * Company: 北京神州泰岳软件股份有限公司
 * Copyright 2011-2012 (C) Ultrapower Software CO., LTD. All Rights Reserved.
 */
package com.ultrapower.viedo.utils.web;

import java.util.HashMap;
import java.util.Map;

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
 * 2018年4月17日 上午10:08:30          dell        4.1         To create
 * </p>
 *
 * @since
 * @see
 */
public class R extends HashMap<String, Object> {

    /**
     *
     */
    private static final long serialVersionUID = -7830556953955389106L;

    public R() {
        put("code", 200);
    }

    public R error() {
        return error(500, "系统异常请联系系统管理员");
    }

    public R error(String msg) {
        return error(500, msg);
    }

    public R error(int code, String msg) {
        R r = new R();
        r.put("code", code);
        r.put("msg", msg);
        return r;
    }

    public R ok(String msg) {
        R r = new R();
        r.put("msg", msg);
        return r;
    }

    public R ok(Map<String, Object> map) {
        R r = new R();
        r.putAll(map);
        return r;
    }

}
