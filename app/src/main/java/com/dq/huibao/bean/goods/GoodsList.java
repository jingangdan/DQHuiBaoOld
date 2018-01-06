package com.dq.huibao.bean.goods;

import java.util.List;

/**
 * 商品列表
 * Created by jingang on 2018/1/6.
 */

public class GoodsList {

    /**
     * status : 1
     * data : [{"id":"2419","goodsname":"绿叶爱生活竹纤维男式船袜","thumb":"images/sz_yi/1604/2017/12/rhhyggQffu6Hq6WXwGY9QF6QGgf6HX.jpg","marketprice":"63.00","unit":"双","productprice":"63.00","ishot":"0","isrecommand":"0","isnew":"0","isdiscount":"0","issendfree":"0","istime":"0"},{"id":"2420","goodsname":"爱生活竹纤维男士短袜船袜女士船袜","thumb":"images/sz_yi/1604/2017/12/pzt9Cq281mNd8QzgdzyGdd5Z188yN8.jpg","marketprice":"70.00","unit":"双","productprice":"70.00","ishot":"0","isrecommand":"0","isnew":"0","isdiscount":"0","issendfree":"0","istime":"0"}]
     */

    private int status;
    private List<DataBean> data;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * id : 2419
         * goodsname : 绿叶爱生活竹纤维男式船袜
         * thumb : images/sz_yi/1604/2017/12/rhhyggQffu6Hq6WXwGY9QF6QGgf6HX.jpg
         * marketprice : 63.00
         * unit : 双
         * productprice : 63.00
         * ishot : 0
         * isrecommand : 0
         * isnew : 0
         * isdiscount : 0
         * issendfree : 0
         * istime : 0
         */

        private String id;
        private String goodsname;
        private String thumb;
        private String marketprice;
        private String unit;
        private String productprice;
        private String ishot;
        private String isrecommand;
        private String isnew;
        private String isdiscount;
        private String issendfree;
        private String istime;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getGoodsname() {
            return goodsname;
        }

        public void setGoodsname(String goodsname) {
            this.goodsname = goodsname;
        }

        public String getThumb() {
            return thumb;
        }

        public void setThumb(String thumb) {
            this.thumb = thumb;
        }

        public String getMarketprice() {
            return marketprice;
        }

        public void setMarketprice(String marketprice) {
            this.marketprice = marketprice;
        }

        public String getUnit() {
            return unit;
        }

        public void setUnit(String unit) {
            this.unit = unit;
        }

        public String getProductprice() {
            return productprice;
        }

        public void setProductprice(String productprice) {
            this.productprice = productprice;
        }

        public String getIshot() {
            return ishot;
        }

        public void setIshot(String ishot) {
            this.ishot = ishot;
        }

        public String getIsrecommand() {
            return isrecommand;
        }

        public void setIsrecommand(String isrecommand) {
            this.isrecommand = isrecommand;
        }

        public String getIsnew() {
            return isnew;
        }

        public void setIsnew(String isnew) {
            this.isnew = isnew;
        }

        public String getIsdiscount() {
            return isdiscount;
        }

        public void setIsdiscount(String isdiscount) {
            this.isdiscount = isdiscount;
        }

        public String getIssendfree() {
            return issendfree;
        }

        public void setIssendfree(String issendfree) {
            this.issendfree = issendfree;
        }

        public String getIstime() {
            return istime;
        }

        public void setIstime(String istime) {
            this.istime = istime;
        }

        @Override
        public String toString() {
            return "DataBean{" +
                    "id='" + id + '\'' +
                    ", goodsname='" + goodsname + '\'' +
                    ", thumb='" + thumb + '\'' +
                    ", marketprice='" + marketprice + '\'' +
                    ", unit='" + unit + '\'' +
                    ", productprice='" + productprice + '\'' +
                    ", ishot='" + ishot + '\'' +
                    ", isrecommand='" + isrecommand + '\'' +
                    ", isnew='" + isnew + '\'' +
                    ", isdiscount='" + isdiscount + '\'' +
                    ", issendfree='" + issendfree + '\'' +
                    ", istime='" + istime + '\'' +
                    '}';
        }
    }

    @Override
    public String toString() {
        return "GoodsList{" +
                "status=" + status +
                ", data=" + data +
                '}';
    }
}
