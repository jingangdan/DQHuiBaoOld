package com.dq.huibao.bean;

import java.util.List;

/**
 * Description：
 * Created by jingang on 2017/11/2.
 */

public class Root {
    private String result;

    private String msg;

    private List<Data> data;

    public void setResult(String result) {
        this.result = result;
    }

    public String getResult() {
        return this.result;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getMsg() {
        return this.msg;
    }

    public void setData(List<Data> data) {
        this.data = data;
    }

    public List<Data> getData() {
        return this.data;
    }

    @Override
    public String toString() {
        return "Root{" +
                "result='" + result + '\'' +
                ", msg='" + msg + '\'' +
                ", data=" + data +
                '}';
    }
}
