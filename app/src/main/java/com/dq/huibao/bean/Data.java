package com.dq.huibao.bean;

/**
 * Description：
 * Created by jingang on 2017/11/2.
 */

public class Data {
    private String id;

    private String temp;

    private Params params;

    private String data;

    private String other;

    private String content;

    private int isadmin;

    public void setId(String id){
        this.id = id;
    }
    public String getId(){
        return this.id;
    }
    public void setTemp(String temp){
        this.temp = temp;
    }
    public String getTemp(){
        return this.temp;
    }
    public void setParams(Params params){
        this.params = params;
    }
    public Params getParams(){
        return this.params;
    }
    public void setData(String data){
        this.data = data;
    }
    public String getData(){
        return this.data;
    }
    public void setOther(String other){
        this.other = other;
    }
    public String getOther(){
        return this.other;
    }
    public void setContent(String content){
        this.content = content;
    }
    public String getContent(){
        return this.content;
    }
    public void setIsadmin(int isadmin){
        this.isadmin = isadmin;
    }
    public int getIsadmin(){
        return this.isadmin;
    }
}
