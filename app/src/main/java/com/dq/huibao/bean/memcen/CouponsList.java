package com.dq.huibao.bean.memcen;

import java.util.List;

/**
 * Description：
 * Created by jingang on 2017/11/22.
 */

public class CouponsList {

    /**
     * result : 1
     * msg : 成功
     * data : [{"id":"103","timelimit":"0","timedays":"7","timestart":"-28800","timeend":"-28800","thumb":"images/1604/2017/08/AvrRH9Vf9zdHmbvN9RVNbNVhBB8Vp8.jpg","couponname":"【德泉惠宝购物商城单品】20元优惠券","enough":"50.00","backtype":"0","deduct":"20.00","discount":"0.00","backmoney":"","backcredit":"","backredpack":"","bgcolor":"","credit":"0","money":"0.00","getmax":"5"},{"id":"106","timelimit":"0","timedays":"7","timestart":"-28800","timeend":"-28800","thumb":"images/1604/2017/08/WhvHoKXHyHPc3Y2N3OKq0XQpK3iKBX.jpg","couponname":"【德泉惠宝购物商城单品】15元优惠券","enough":"40.00","backtype":"0","deduct":"15.00","discount":"0.00","backmoney":"","backcredit":"","backredpack":"","bgcolor":"","credit":"0","money":"0.00","getmax":"5"},{"id":"105","timelimit":"0","timedays":"7","timestart":"-28800","timeend":"-28800","thumb":"images/1604/2017/08/wIx2HW2v6x6ok3zHDJafhHV2iIYD3h.jpg","couponname":"【德泉惠宝购物商城单品】10元优惠券","enough":"30.00","backtype":"0","deduct":"10.00","discount":"0.00","backmoney":"","backcredit":"","backredpack":"","bgcolor":"","credit":"0","money":"0.00","getmax":"5"},{"id":"104","timelimit":"0","timedays":"7","timestart":"-28800","timeend":"-28800","thumb":"images/1604/2017/08/I2otRPP5QLl2m2oq205L02G1pG26GG.jpg","couponname":"【德泉惠宝购物商城单品】3元优惠券","enough":"10.00","backtype":"0","deduct":"3.00","discount":"0.00","backmoney":"","backcredit":"","backredpack":"","bgcolor":"","credit":"0","money":"0.00","getmax":"5"}]
     */

    private String result;
    private String msg;
    private List<DataBean> data;

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

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * id : 103
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
         * credit : 0
         * money : 0.00
         * getmax : 5
         */

        private String id;
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
        private String credit;
        private String money;
        private String getmax;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
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

        public String getCredit() {
            return credit;
        }

        public void setCredit(String credit) {
            this.credit = credit;
        }

        public String getMoney() {
            return money;
        }

        public void setMoney(String money) {
            this.money = money;
        }

        public String getGetmax() {
            return getmax;
        }

        public void setGetmax(String getmax) {
            this.getmax = getmax;
        }

        @Override
        public String toString() {
            return "DataBean{" +
                    "id='" + id + '\'' +
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
                    ", credit='" + credit + '\'' +
                    ", money='" + money + '\'' +
                    ", getmax='" + getmax + '\'' +
                    '}';
        }
    }

    @Override
    public String toString() {
        return "CouponsList{" +
                "result='" + result + '\'' +
                ", msg='" + msg + '\'' +
                ", data=" + data +
                '}';
    }
}
