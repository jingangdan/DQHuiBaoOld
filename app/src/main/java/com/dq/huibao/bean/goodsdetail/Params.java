package com.dq.huibao.bean.goodsdetail;

/**
 * Description：
 * Created by jingang on 2017/11/13.
 */

public class Params {

    private String id;
    private String uniacid;
    private String goodsid;
    private String title;
    private String value;
    private String displayorder;
    public void setId(String id) {
        this.id = id;
    }
    public String getId() {
        return id;
    }

    public void setUniacid(String uniacid) {
        this.uniacid = uniacid;
    }
    public String getUniacid() {
        return uniacid;
    }

    public void setGoodsid(String goodsid) {
        this.goodsid = goodsid;
    }
    public String getGoodsid() {
        return goodsid;
    }

    public void setTitle(String title) {
        this.title = title;
    }
    public String getTitle() {
        return title;
    }

    public void setValue(String value) {
        this.value = value;
    }
    public String getValue() {
        return value;
    }

    public void setDisplayorder(String displayorder) {
        this.displayorder = displayorder;
    }
    public String getDisplayorder() {
        return displayorder;
    }

}
