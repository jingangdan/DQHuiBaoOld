package com.dq.huibao.bean.order;

import java.util.List;

/**
 * 订单详情 实体类
 * Created by jingang on 2018/1/24.
 */

public class OrderDetail {

    /**
     * status : 1
     * data : {"id":"33","ordersn":"SN201801241533413384","uid":"4","money":"296.00","status":"-1","shopid":"0","pay_money":"266.40","discount_money":"29.60","dipatch_money":"10.00","createtime":"1516779221","paytime":"0","sendtime":"0","finishtime":"0","get_score":"282","is_comment":"0","express":"","expressorder":"","expressname":"","refund":"0","refund_remark":"","address":{"id":"4","uid":"4","province":"山东省","city":"临沂市","district":"","regionid":"1454","addr":"哈哈哈","contact":"jingang","mobile":"17865069380","isdefault":"1"},"shopname":"惠宝商城","goodslist":[{"id":"36","goodsname":"冬季男士职业加绒加厚保暖衬衫商务纯色长袖衬衫免烫白色保暖衬衫","optionname":"","price":"70.20","count":"2","thumb":"/attachment/images/1604/2017/12/K8apuZbP80pN3AGGqJWO8sSoOQG8GH.jpg"},{"id":"37","goodsname":"爱生活竹纤维男士短袜船袜女士船袜","optionname":"","price":"63.00","count":"2","thumb":"/attachment/images/sz_yi/1604/2017/12/pzt9Cq281mNd8QzgdzyGdd5Z188yN8.jpg"}]}
     */

    private int status;
    private DataBean data;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * id : 33
         * ordersn : SN201801241533413384
         * uid : 4
         * money : 296.00
         * status : -1
         * shopid : 0
         * pay_money : 266.40
         * discount_money : 29.60
         * dipatch_money : 10.00
         * createtime : 1516779221
         * paytime : 0
         * sendtime : 0
         * finishtime : 0
         * get_score : 282
         * is_comment : 0
         * express :
         * expressorder :
         * expressname :
         * refund : 0
         * refund_remark :
         * address : {"id":"4","uid":"4","province":"山东省","city":"临沂市","district":"","regionid":"1454","addr":"哈哈哈","contact":"jingang","mobile":"17865069380","isdefault":"1"}
         * shopname : 惠宝商城
         * goodslist : [{"id":"36","goodsname":"冬季男士职业加绒加厚保暖衬衫商务纯色长袖衬衫免烫白色保暖衬衫","optionname":"","price":"70.20","count":"2","thumb":"/attachment/images/1604/2017/12/K8apuZbP80pN3AGGqJWO8sSoOQG8GH.jpg"},{"id":"37","goodsname":"爱生活竹纤维男士短袜船袜女士船袜","optionname":"","price":"63.00","count":"2","thumb":"/attachment/images/sz_yi/1604/2017/12/pzt9Cq281mNd8QzgdzyGdd5Z188yN8.jpg"}]
         */

        private String id;
        private String ordersn;
        private String uid;
        private String money;
        private String status;
        private String shopid;
        private String pay_money;
        private String discount_money;
        private String dipatch_money;
        private String createtime;
        private String paytime;
        private String sendtime;
        private String finishtime;
        private String get_score;
        private String is_comment;
        private String express;
        private String expressorder;
        private String expressname;
        private String refund;
        private String refund_remark;
        private AddressBean address;
        private String shopname;
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

        public String getUid() {
            return uid;
        }

        public void setUid(String uid) {
            this.uid = uid;
        }

        public String getMoney() {
            return money;
        }

        public void setMoney(String money) {
            this.money = money;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getShopid() {
            return shopid;
        }

        public void setShopid(String shopid) {
            this.shopid = shopid;
        }

        public String getPay_money() {
            return pay_money;
        }

        public void setPay_money(String pay_money) {
            this.pay_money = pay_money;
        }

        public String getDiscount_money() {
            return discount_money;
        }

        public void setDiscount_money(String discount_money) {
            this.discount_money = discount_money;
        }

        public String getDipatch_money() {
            return dipatch_money;
        }

        public void setDipatch_money(String dipatch_money) {
            this.dipatch_money = dipatch_money;
        }

        public String getCreatetime() {
            return createtime;
        }

        public void setCreatetime(String createtime) {
            this.createtime = createtime;
        }

        public String getPaytime() {
            return paytime;
        }

        public void setPaytime(String paytime) {
            this.paytime = paytime;
        }

        public String getSendtime() {
            return sendtime;
        }

        public void setSendtime(String sendtime) {
            this.sendtime = sendtime;
        }

        public String getFinishtime() {
            return finishtime;
        }

        public void setFinishtime(String finishtime) {
            this.finishtime = finishtime;
        }

        public String getGet_score() {
            return get_score;
        }

        public void setGet_score(String get_score) {
            this.get_score = get_score;
        }

        public String getIs_comment() {
            return is_comment;
        }

        public void setIs_comment(String is_comment) {
            this.is_comment = is_comment;
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

        public String getRefund() {
            return refund;
        }

        public void setRefund(String refund) {
            this.refund = refund;
        }

        public String getRefund_remark() {
            return refund_remark;
        }

        public void setRefund_remark(String refund_remark) {
            this.refund_remark = refund_remark;
        }

        public AddressBean getAddress() {
            return address;
        }

        public void setAddress(AddressBean address) {
            this.address = address;
        }

        public String getShopname() {
            return shopname;
        }

        public void setShopname(String shopname) {
            this.shopname = shopname;
        }

        public List<GoodslistBean> getGoodslist() {
            return goodslist;
        }

        public void setGoodslist(List<GoodslistBean> goodslist) {
            this.goodslist = goodslist;
        }

        public static class AddressBean {
            /**
             * id : 4
             * uid : 4
             * province : 山东省
             * city : 临沂市
             * district :
             * regionid : 1454
             * addr : 哈哈哈
             * contact : jingang
             * mobile : 17865069380
             * isdefault : 1
             */

            private String id;
            private String uid;
            private String province;
            private String city;
            private String district;
            private String regionid;
            private String addr;
            private String contact;
            private String mobile;
            private String isdefault;

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getUid() {
                return uid;
            }

            public void setUid(String uid) {
                this.uid = uid;
            }

            public String getProvince() {
                return province;
            }

            public void setProvince(String province) {
                this.province = province;
            }

            public String getCity() {
                return city;
            }

            public void setCity(String city) {
                this.city = city;
            }

            public String getDistrict() {
                return district;
            }

            public void setDistrict(String district) {
                this.district = district;
            }

            public String getRegionid() {
                return regionid;
            }

            public void setRegionid(String regionid) {
                this.regionid = regionid;
            }

            public String getAddr() {
                return addr;
            }

            public void setAddr(String addr) {
                this.addr = addr;
            }

            public String getContact() {
                return contact;
            }

            public void setContact(String contact) {
                this.contact = contact;
            }

            public String getMobile() {
                return mobile;
            }

            public void setMobile(String mobile) {
                this.mobile = mobile;
            }

            public String getIsdefault() {
                return isdefault;
            }

            public void setIsdefault(String isdefault) {
                this.isdefault = isdefault;
            }
        }

        public static class GoodslistBean {
            /**
             * id : 36
             * goodsname : 冬季男士职业加绒加厚保暖衬衫商务纯色长袖衬衫免烫白色保暖衬衫
             * optionname :
             * price : 70.20
             * count : 2
             * thumb : /attachment/images/1604/2017/12/K8apuZbP80pN3AGGqJWO8sSoOQG8GH.jpg
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
                    ", uid='" + uid + '\'' +
                    ", money='" + money + '\'' +
                    ", status='" + status + '\'' +
                    ", shopid='" + shopid + '\'' +
                    ", pay_money='" + pay_money + '\'' +
                    ", discount_money='" + discount_money + '\'' +
                    ", dipatch_money='" + dipatch_money + '\'' +
                    ", createtime='" + createtime + '\'' +
                    ", paytime='" + paytime + '\'' +
                    ", sendtime='" + sendtime + '\'' +
                    ", finishtime='" + finishtime + '\'' +
                    ", get_score='" + get_score + '\'' +
                    ", is_comment='" + is_comment + '\'' +
                    ", express='" + express + '\'' +
                    ", expressorder='" + expressorder + '\'' +
                    ", expressname='" + expressname + '\'' +
                    ", refund='" + refund + '\'' +
                    ", refund_remark='" + refund_remark + '\'' +
                    ", address=" + address +
                    ", shopname='" + shopname + '\'' +
                    ", goodslist=" + goodslist +
                    '}';
        }
    }

    @Override
    public String toString() {
        return "OrderDetail{" +
                "status=" + status +
                ", data=" + data +
                '}';
    }
}
