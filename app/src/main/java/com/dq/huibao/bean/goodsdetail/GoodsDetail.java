package com.dq.huibao.bean.goodsdetail;

/**
 * Description：
 * Created by jingang on 2017/11/13.
 */

public class GoodsDetail {
    private String result;
    private String msg;
    private Data data;

    public void setResult(String result) {
        this.result = result;
    }

    public String getResult() {
        return result;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getMsg() {
        return msg;
    }

    public void setData(Data data) {
        this.data = data;
    }

    public Data getData() {
        return data;
    }

    @Override
    public String toString() {
        return "GoodsDetail{" +
                "result='" + result + '\'' +
                ", msg='" + msg + '\'' +
                ", data=" + data +
                '}';
    }
}
