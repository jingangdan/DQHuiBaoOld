package com.dq.huibao.bean;

/**
 * Description：
 * Created by jingang on 2017/11/2.
 */

public class Params {
    private String placeholder;

    private String style;

    private String color;

    private String bgcolor;

    private String bordercolor;

    private String searchurl;

    private String uniacid;

    public void setPlaceholder(String placeholder){
        this.placeholder = placeholder;
    }
    public String getPlaceholder(){
        return this.placeholder;
    }
    public void setStyle(String style){
        this.style = style;
    }
    public String getStyle(){
        return this.style;
    }
    public void setColor(String color){
        this.color = color;
    }
    public String getColor(){
        return this.color;
    }
    public void setBgcolor(String bgcolor){
        this.bgcolor = bgcolor;
    }
    public String getBgcolor(){
        return this.bgcolor;
    }
    public void setBordercolor(String bordercolor){
        this.bordercolor = bordercolor;
    }
    public String getBordercolor(){
        return this.bordercolor;
    }
    public void setSearchurl(String searchurl){
        this.searchurl = searchurl;
    }
    public String getSearchurl(){
        return this.searchurl;
    }
    public void setUniacid(String uniacid){
        this.uniacid = uniacid;
    }
    public String getUniacid(){
        return this.uniacid;
    }
}
