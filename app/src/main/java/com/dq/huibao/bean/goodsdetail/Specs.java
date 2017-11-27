package com.dq.huibao.bean.goodsdetail;

import java.util.List;

/**
 * Description：
 * Created by jingang on 2017/11/13.
 */
public class Specs {

    private String id;
    private String uniacid;
    private String goodsid;
    private String title;
    private String description;
    private String displaytype;
    private String content;
    private String displayorder;
    private String propid;
    private List<Items> items;
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

    public void setDescription(String description) {
        this.description = description;
    }
    public String getDescription() {
        return description;
    }

    public void setDisplaytype(String displaytype) {
        this.displaytype = displaytype;
    }
    public String getDisplaytype() {
        return displaytype;
    }

    public void setContent(String content) {
        this.content = content;
    }
    public String getContent() {
        return content;
    }

    public void setDisplayorder(String displayorder) {
        this.displayorder = displayorder;
    }
    public String getDisplayorder() {
        return displayorder;
    }

    public void setPropid(String propid) {
        this.propid = propid;
    }
    public String getPropid() {
        return propid;
    }

    public void setItems(List<Items> items) {
        this.items = items;
    }
    public List<Items> getItems() {
        return items;
    }

    @Override
    public String toString() {
        return "Specs{" +
                "id='" + id + '\'' +
                ", uniacid='" + uniacid + '\'' +
                ", goodsid='" + goodsid + '\'' +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", displaytype='" + displaytype + '\'' +
                ", content='" + content + '\'' +
                ", displayorder='" + displayorder + '\'' +
                ", propid='" + propid + '\'' +
                ", items=" + items +
                '}';
    }
}