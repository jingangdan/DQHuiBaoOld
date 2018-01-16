package com.dq.huibao.bean.addr;

/**
 * 添加收货地址返回
 * Created by jingang on 2018/1/16.
 */

public class AddrReturn {

    /**
     * status : 1
     * data : 添加成功
     */

    private int status;
    private String data;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "AddrReturn{" +
                "status=" + status +
                ", data='" + data + '\'' +
                '}';
    }
}
