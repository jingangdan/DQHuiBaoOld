package com.dq.huibao.bean.account;

/**
 * Created by jingang on 2017/12/25.
 */

public class Account {

    /**
     * status : 1
     * data : 741084
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
        return "Account{" +
                "status=" + status +
                ", data='" + data + '\'' +
                '}';
    }
}
