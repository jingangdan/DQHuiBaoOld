package com.dq.huibao.bean.goodsdetail;

/**
 * Description：
 * Created by jingang on 2017/11/13.
 */

public class Items {

    private String id;
    private String uniacid;
    private String specid;
    private String title;
    private String thumb;
    private String show;
    private String displayorder;
    //@JsonProperty("valueId")
    private String valueid;
    private String virtual;
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

    public void setSpecid(String specid) {
        this.specid = specid;
    }
    public String getSpecid() {
        return specid;
    }

    public void setTitle(String title) {
        this.title = title;
    }
    public String getTitle() {
        return title;
    }

    public void setThumb(String thumb) {
        this.thumb = thumb;
    }
    public String getThumb() {
        return thumb;
    }

    public void setShow(String show) {
        this.show = show;
    }
    public String getShow() {
        return show;
    }

    public void setDisplayorder(String displayorder) {
        this.displayorder = displayorder;
    }
    public String getDisplayorder() {
        return displayorder;
    }

    public void setValueid(String valueid) {
        this.valueid = valueid;
    }
    public String getValueid() {
        return valueid;
    }

    public void setVirtual(String virtual) {
        this.virtual = virtual;
    }
    public String getVirtual() {
        return virtual;
    }

}
