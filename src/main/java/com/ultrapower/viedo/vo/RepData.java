package com.ultrapower.viedo.vo;

/**
 * 请求响应数据
 * @author zhangjian
 *	
 * @param <T> 返回数据类型
 */
public class RepData<T> {

    /**
     * 响应状态
     */
    private int code;
    /**
     * 消息描述
     * 返回状态异常时不能为空
     */
    private String msg;
    /**
     * 请求响应数据
     */
    private T data;

    /**
     * 数据总条数
     */
    private int count = 1;

    //页大小
    private int pagesize;
    //当前页
    private int pageNumber;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getPagesize() {
        return pagesize;
    }

    public void setPagesize(int pagesize) {
        this.pagesize = pagesize;
    }

    public int getPageNumber() {
        return pageNumber;
    }

    public void setPageNumber(int pageNumber) {
        this.pageNumber = pageNumber;
    }

    @Override
    public String toString() {
        return "code=" + this.code + "--" + "msg=" + this.msg + "--" + "--" + "data=" + data + "--count=" + count;
    }
}
