package com.dq.huibao.bean.order;

import java.util.List;

/**
 * 订单列表 实体类
 * Created by jingang on 2018/1/20.
 */

public class Order {


    /**
     * status : 1
     * data : [{"id":"18","ordersn":"SN201801201022383242","status":"-1","refund":"0","pay_money":"212.40","is_comment":"0","sendtime":"0","express":"","expressorder":"","expressname":"","allcount":"2","goodslist":[{"id":"12","goodsname":"新款欧美简约牛津电脑背包 学生科技书包轻便男包 休闲双肩包批发","optionname":"黑色","price":"106.20","count":"2","thumb":"/attachment/images/1604/2017/12/M8zB8Gk2yqNi3Dgqq5y222G838cBiy.jpg"}]}]
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
         * id : 18
         * ordersn : SN201801201022383242
         * status : -1
         * refund : 0
         * pay_money : 212.40
         * is_comment : 0
         * sendtime : 0
         * express :
         * expressorder :
         * expressname :
         * allcount : 2
         * goodslist : [{"id":"12","goodsname":"新款欧美简约牛津电脑背包 学生科技书包轻便男包 休闲双肩包批发","optionname":"黑色","price":"106.20","count":"2","thumb":"/attachment/images/1604/2017/12/M8zB8Gk2yqNi3Dgqq5y222G838cBiy.jpg"}]
         */

        private String id;
        private String ordersn;
        private String status;
        private String refund;
        private String pay_money;
        private String is_comment;
        private String sendtime;
        private String express;
        private String expressorder;
        private String expressname;
        private String allcount;
        private List<GoodslistBean> goodslist;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getOrdersn() {
            return ordersn;
        }

        public void setOrdersn(String ordersn) {
            this.ordersn = ordersn;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getRefund() {
            return refund;
        }

        public void setRefund(String refund) {
            this.refund = refund;
        }

        public String getPay_money() {
            return pay_money;
        }

        public void setPay_money(String pay_money) {
            this.pay_money = pay_money;
        }

        public String getIs_comment() {
            return is_comment;
        }

        public void setIs_comment(String is_comment) {
            this.is_comment = is_comment;
        }

        public String getSendtime() {
            return sendtime;
        }

        public void setSendtime(String sendtime) {
            this.sendtime = sendtime;
        }

        public String getExpress() {
            return express;
        }

        public void setExpress(String express) {
            this.express = express;
        }

        public String getExpressorder() {
            return expressorder;
        }

        public void setExpressorder(String expressorder) {
            this.expressorder = expressorder;
        }

        public String getExpressname() {
            return expressname;
        }

        public void setExpressname(String expressname) {
            this.expressname = expressname;
        }

        public String getAllcount() {
            return allcount;
        }

        public void setAllcount(String allcount) {
            this.allcount = allcount;
        }

        public List<GoodslistBean> getGoodslist() {
            return goodslist;
        }

        public void setGoodslist(List<GoodslistBean> goodslist) {
            this.goodslist = goodslist;
        }

        public static class GoodslistBean {
            /**
             * id : 12
             * goodsname : 新款欧美简约牛津电脑背包 学生科技书包轻便男包 休闲双肩包批发
             * optionname : 黑色
             * price : 106.20
             * count : 2
             * thumb : /attachment/images/1604/2017/12/M8zB8Gk2yqNi3Dgqq5y222G838cBiy.jpg
             */

            private String id;
            private String goodsname;
            private String optionname;
            private String price;
            private String count;
            private String thumb;

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

            public String getOptionname() {
                return optionname;
            }

            public void setOptionname(String optionname) {
                this.optionname = optionname;
            }

            public String getPrice() {
                return price;
            }

            public void setPrice(String price) {
                this.price = price;
            }

            public String getCount() {
                return count;
            }

            public void setCount(String count) {
                this.count = count;
            }

            public String getThumb() {
                return thumb;
            }

            public void setThumb(String thumb) {
                this.thumb = thumb;
            }

            @Override
            public String toString() {
                return "GoodslistBean{" +
                        "id='" + id + '\'' +
                        ", goodsname='" + goodsname + '\'' +
                        ", optionname='" + optionname + '\'' +
                        ", price='" + price + '\'' +
                        ", count='" + count + '\'' +
                        ", thumb='" + thumb + '\'' +
                        '}';
            }
        }

        @Override
        public String toString() {
            return "DataBean{" +
                    "id='" + id + '\'' +
                    ", ordersn='" + ordersn + '\'' +
                    ", status='" + status + '\'' +
                    ", refund='" + refund + '\'' +
                    ", pay_money='" + pay_money + '\'' +
                    ", is_comment='" + is_comment + '\'' +
                    ", sendtime='" + sendtime + '\'' +
                    ", express='" + express + '\'' +
                    ", expressorder='" + expressorder + '\'' +
                    ", expressname='" + expressname + '\'' +
                    ", allcount='" + allcount + '\'' +
                    ", goodslist=" + goodslist +
                    '}';
        }
    }

    @Override
    public String toString() {
        return "Order{" +
                "status=" + status +
                ", data=" + data +
                '}';
    }
}
