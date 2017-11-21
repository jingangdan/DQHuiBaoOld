package com.dq.huibao.bean.memcen;

import java.util.List;

/**
 * Description :优惠券
 * Created by jingang on 2017/11/21.
 */

public class Coupons {

    /**
     * result : 1
     * msg : 成功
     * data : {"list":[{"id":"5443","couponid":"103","gettime":"1511232122","timelimit":"0","timedays":"7","timestart":"-28800","timeend":"-28800","thumb":"images/1604/2017/08/AvrRH9Vf9zdHmbvN9RVNbNVhBB8Vp8.jpg","couponname":"【德泉惠宝购物商城单品】20元优惠券","enough":"50.00","backtype":"0","deduct":"20.00","discount":"0.00","backmoney":"","backcredit":"","backredpack":"","bgcolor":""}],"pagesize":"10"}
     */

    private String result;
    private String msg;
    private DataBean data;

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * list : [{"id":"5443","couponid":"103","gettime":"1511232122","timelimit":"0","timedays":"7","timestart":"-28800","timeend":"-28800","thumb":"images/1604/2017/08/AvrRH9Vf9zdHmbvN9RVNbNVhBB8Vp8.jpg","couponname":"【德泉惠宝购物商城单品】20元优惠券","enough":"50.00","backtype":"0","deduct":"20.00","discount":"0.00","backmoney":"","backcredit":"","backredpack":"","bgcolor":""}]
         * pagesize : 10
         */

        private String pagesize;
        private List<ListBean> list;

        public String getPagesize() {
            return pagesize;
        }

        public void setPagesize(String pagesize) {
            this.pagesize = pagesize;
        }

        public List<ListBean> getList() {
            return list;
        }

        public void setList(List<ListBean> list) {
            this.list = list;
        }

        public static class ListBean {
            /**
             * id : 5443
             * couponid : 103
             * gettime : 1511232122
             * timelimit : 0
             * timedays : 7
             * timestart : -28800
             * timeend : -28800
             * thumb : images/1604/2017/08/AvrRH9Vf9zdHmbvN9RVNbNVhBB8Vp8.jpg
             * couponname : 【德泉惠宝购物商城单品】20元优惠券
             * enough : 50.00
             * backtype : 0
             * deduct : 20.00
             * discount : 0.00
             * backmoney :
             * backcredit :
             * backredpack :
             * bgcolor :
             */

            private String id;
            private String couponid;
            private String gettime;
            private String timelimit;
            private String timedays;
            private String timestart;
            private String timeend;
            private String thumb;
            private String couponname;
            private String enough;
            private String backtype;
            private String deduct;
            private String discount;
            private String backmoney;
            private String backcredit;
            private String backredpack;
            private String bgcolor;

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getCouponid() {
                return couponid;
            }

            public void setCouponid(String couponid) {
                this.couponid = couponid;
            }

            public String getGettime() {
                return gettime;
            }

            public void setGettime(String gettime) {
                this.gettime = gettime;
            }

            public String getTimelimit() {
                return timelimit;
            }

            public void setTimelimit(String timelimit) {
                this.timelimit = timelimit;
            }

            public String getTimedays() {
                return timedays;
            }

            public void setTimedays(String timedays) {
                this.timedays = timedays;
            }

            public String getTimestart() {
                return timestart;
            }

            public void setTimestart(String timestart) {
                this.timestart = timestart;
            }

            public String getTimeend() {
                return timeend;
            }

            public void setTimeend(String timeend) {
                this.timeend = timeend;
            }

            public String getThumb() {
                return thumb;
            }

            public void setThumb(String thumb) {
                this.thumb = thumb;
            }

            public String getCouponname() {
                return couponname;
            }

            public void setCouponname(String couponname) {
                this.couponname = couponname;
            }

            public String getEnough() {
                return enough;
            }

            public void setEnough(String enough) {
                this.enough = enough;
            }

            public String getBacktype() {
                return backtype;
            }

            public void setBacktype(String backtype) {
                this.backtype = backtype;
            }

            public String getDeduct() {
                return deduct;
            }

            public void setDeduct(String deduct) {
                this.deduct = deduct;
            }

            public String getDiscount() {
                return discount;
            }

            public void setDiscount(String discount) {
                this.discount = discount;
            }

            public String getBackmoney() {
                return backmoney;
            }

            public void setBackmoney(String backmoney) {
                this.backmoney = backmoney;
            }

            public String getBackcredit() {
                return backcredit;
            }

            public void setBackcredit(String backcredit) {
                this.backcredit = backcredit;
            }

            public String getBackredpack() {
                return backredpack;
            }

            public void setBackredpack(String backredpack) {
                this.backredpack = backredpack;
            }

            public String getBgcolor() {
                return bgcolor;
            }

            public void setBgcolor(String bgcolor) {
                this.bgcolor = bgcolor;
            }

            @Override
            public String toString() {
                return "ListBean{" +
                        "id='" + id + '\'' +
                        ", couponid='" + couponid + '\'' +
                        ", gettime='" + gettime + '\'' +
                        ", timelimit='" + timelimit + '\'' +
                        ", timedays='" + timedays + '\'' +
                        ", timestart='" + timestart + '\'' +
                        ", timeend='" + timeend + '\'' +
                        ", thumb='" + thumb + '\'' +
                        ", couponname='" + couponname + '\'' +
                        ", enough='" + enough + '\'' +
                        ", backtype='" + backtype + '\'' +
                        ", deduct='" + deduct + '\'' +
                        ", discount='" + discount + '\'' +
                        ", backmoney='" + backmoney + '\'' +
                        ", backcredit='" + backcredit + '\'' +
                        ", backredpack='" + backredpack + '\'' +
                        ", bgcolor='" + bgcolor + '\'' +
                        '}';
            }
        }

        @Override
        public String toString() {
            return "DataBean{" +
                    "pagesize='" + pagesize + '\'' +
                    ", list=" + list +
                    '}';
        }
    }

    @Override
    public String toString() {
        return "Coupons{" +
                "result='" + result + '\'' +
                ", msg='" + msg + '\'' +
                ", data=" + data +
                '}';
    }
}
