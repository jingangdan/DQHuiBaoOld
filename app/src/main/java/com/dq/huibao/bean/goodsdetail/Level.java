package com.dq.huibao.bean.goodsdetail;

/**
 * Description：
 * Created by jingang on 2017/11/13.
 */

public class Level {

    private String discounttxt;
    private String discount;
    private String levelname;
    private String discountway;
    public void setDiscounttxt(String discounttxt) {
        this.discounttxt = discounttxt;
    }
    public String getDiscounttxt() {
        return discounttxt;
    }

    public void setDiscount(String discount) {
        this.discount = discount;
    }
    public String getDiscount() {
        return discount;
    }

    public void setLevelname(String levelname) {
        this.levelname = levelname;
    }
    public String getLevelname() {
        return levelname;
    }

    public void setDiscountway(String discountway) {
        this.discountway = discountway;
    }
    public String getDiscountway() {
        return discountway;
    }

    @Override
    public String toString() {
        return "Level{" +
                "discounttxt='" + discounttxt + '\'' +
                ", discount='" + discount + '\'' +
                ", levelname='" + levelname + '\'' +
                ", discountway='" + discountway + '\'' +
                '}';
    }
}
