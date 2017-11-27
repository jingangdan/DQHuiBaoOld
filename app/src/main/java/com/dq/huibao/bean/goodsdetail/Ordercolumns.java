package com.dq.huibao.bean.goodsdetail;

/**
 * Description：
 * Created by jingang on 2017/11/26.
 */

public class Ordercolumns {

    private String field;
    private String title;
    private String subtitle;
    private String width;
    public void setField(String field) {
        this.field = field;
    }
    public String getField() {
        return field;
    }

    public void setTitle(String title) {
        this.title = title;
    }
    public String getTitle() {
        return title;
    }

    public void setSubtitle(String subtitle) {
        this.subtitle = subtitle;
    }
    public String getSubtitle() {
        return subtitle;
    }

    public void setWidth(String width) {
        this.width = width;
    }
    public String getWidth() {
        return width;
    }

    @Override
    public String toString() {
        return "Ordercolumns{" +
                "field='" + field + '\'' +
                ", title='" + title + '\'' +
                ", subtitle='" + subtitle + '\'' +
                ", width='" + width + '\'' +
                '}';
    }
}