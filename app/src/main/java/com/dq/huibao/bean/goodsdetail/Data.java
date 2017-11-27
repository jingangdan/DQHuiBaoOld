package com.dq.huibao.bean.goodsdetail;

import java.util.List;

/**
 * Description：
 * Created by jingang on 2017/11/13.
 */

public class Data {

    private Goods goods;
    private Saleset saleset;
    private Shop shop;
    private List<String> pics;
    private List<Options> options;
    private List<Specs> specs;
    private List<Params> params;
    private String paramsNum;
    private boolean commission;
    private String commissionText;
    private Level level;
    private boolean isfavorite;
    private List<Comment> comment;
    private String commentcount;
    public void setGoods(Goods goods) {
        this.goods = goods;
    }
    public Goods getGoods() {
        return goods;
    }

    public void setSaleset(Saleset saleset) {
        this.saleset = saleset;
    }
    public Saleset getSaleset() {
        return saleset;
    }

    public void setShop(Shop shop) {
        this.shop = shop;
    }
    public Shop getShop() {
        return shop;
    }

    public void setPics(List<String> pics) {
        this.pics = pics;
    }
    public List<String> getPics() {
        return pics;
    }

    public void setOptions(List<Options> options) {
        this.options = options;
    }
    public List<Options> getOptions() {
        return options;
    }

    public void setSpecs(List<Specs> specs) {
        this.specs = specs;
    }
    public List<Specs> getSpecs() {
        return specs;
    }

    public void setParams(List<Params> params) {
        this.params = params;
    }
    public List<Params> getParams() {
        return params;
    }

    public void setParamsNum(String paramsNum) {
        this.paramsNum = paramsNum;
    }
    public String getParamsNum() {
        return paramsNum;
    }

    public void setCommission(boolean commission) {
        this.commission = commission;
    }
    public boolean getCommission() {
        return commission;
    }

    public void setCommissionText(String commissionText) {
        this.commissionText = commissionText;
    }
    public String getCommissionText() {
        return commissionText;
    }

    public void setLevel(Level level) {
        this.level = level;
    }
    public Level getLevel() {
        return level;
    }

    public void setIsfavorite(boolean isfavorite) {
        this.isfavorite = isfavorite;
    }
    public boolean getIsfavorite() {
        return isfavorite;
    }

    public void setComment(List<Comment> comment) {
        this.comment = comment;
    }
    public List<Comment> getComment() {
        return comment;
    }

    public void setCommentcount(String commentcount) {
        this.commentcount = commentcount;
    }
    public String getCommentcount() {
        return commentcount;
    }

    @Override
    public String toString() {
        return "Data{" +
                "goods=" + goods +
                ", saleset=" + saleset +
                ", shop=" + shop +
                ", pics=" + pics +
                ", options=" + options +
                ", specs=" + specs +
                ", params=" + params +
                ", paramsNum='" + paramsNum + '\'' +
                ", commission=" + commission +
                ", commissionText='" + commissionText + '\'' +
                ", level=" + level +
                ", isfavorite=" + isfavorite +
                ", comment=" + comment +
                ", commentcount='" + commentcount + '\'' +
                '}';
    }
}