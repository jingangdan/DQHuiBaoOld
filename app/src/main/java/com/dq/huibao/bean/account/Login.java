package com.dq.huibao.bean.account;

/**
 * 登录
 * Created by jingang on 2017/12/25.
 */

public class Login {


    /**
     * status : 1
     * data : {"uid":"4","phone":"17865069350","regtime":"1515809780","balance":"0.00","score":"0","user_status":"1","regtype":"app","token":"dcb252e7fc72e2ff493511c4bf5616d8","role_id":"2","nickname":"","sex":"1","city":"","province":"","country":"","headimgurl":"","realname":"","role_name":""}
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
         * uid : 4
         * phone : 17865069350
         * regtime : 1515809780
         * balance : 0.00
         * score : 0
         * user_status : 1
         * regtype : app
         * token : dcb252e7fc72e2ff493511c4bf5616d8
         * role_id : 2
         * nickname :
         * sex : 1
         * city :
         * province :
         * country :
         * headimgurl :
         * realname :
         * role_name :
         */

        private String uid;
        private String phone;
        private String regtime;
        private String balance;
        private String score;
        private String user_status;
        private String regtype;
        private String token;
        private String role_id;
        private String nickname;
        private String sex;
        private String city;
        private String province;
        private String country;
        private String headimgurl;
        private String realname;
        private String role_name;

        public String getUid() {
            return uid;
        }

        public void setUid(String uid) {
            this.uid = uid;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public String getRegtime() {
            return regtime;
        }

        public void setRegtime(String regtime) {
            this.regtime = regtime;
        }

        public String getBalance() {
            return balance;
        }

        public void setBalance(String balance) {
            this.balance = balance;
        }

        public String getScore() {
            return score;
        }

        public void setScore(String score) {
            this.score = score;
        }

        public String getUser_status() {
            return user_status;
        }

        public void setUser_status(String user_status) {
            this.user_status = user_status;
        }

        public String getRegtype() {
            return regtype;
        }

        public void setRegtype(String regtype) {
            this.regtype = regtype;
        }

        public String getToken() {
            return token;
        }

        public void setToken(String token) {
            this.token = token;
        }

        public String getRole_id() {
            return role_id;
        }

        public void setRole_id(String role_id) {
            this.role_id = role_id;
        }

        public String getNickname() {
            return nickname;
        }

        public void setNickname(String nickname) {
            this.nickname = nickname;
        }

        public String getSex() {
            return sex;
        }

        public void setSex(String sex) {
            this.sex = sex;
        }

        public String getCity() {
            return city;
        }

        public void setCity(String city) {
            this.city = city;
        }

        public String getProvince() {
            return province;
        }

        public void setProvince(String province) {
            this.province = province;
        }

        public String getCountry() {
            return country;
        }

        public void setCountry(String country) {
            this.country = country;
        }

        public String getHeadimgurl() {
            return headimgurl;
        }

        public void setHeadimgurl(String headimgurl) {
            this.headimgurl = headimgurl;
        }

        public String getRealname() {
            return realname;
        }

        public void setRealname(String realname) {
            this.realname = realname;
        }

        public String getRole_name() {
            return role_name;
        }

        public void setRole_name(String role_name) {
            this.role_name = role_name;
        }

        @Override
        public String toString() {
            return "DataBean{" +
                    "uid='" + uid + '\'' +
                    ", phone='" + phone + '\'' +
                    ", regtime='" + regtime + '\'' +
                    ", balance='" + balance + '\'' +
                    ", score='" + score + '\'' +
                    ", user_status='" + user_status + '\'' +
                    ", regtype='" + regtype + '\'' +
                    ", token='" + token + '\'' +
                    ", role_id='" + role_id + '\'' +
                    ", nickname='" + nickname + '\'' +
                    ", sex='" + sex + '\'' +
                    ", city='" + city + '\'' +
                    ", province='" + province + '\'' +
                    ", country='" + country + '\'' +
                    ", headimgurl='" + headimgurl + '\'' +
                    ", realname='" + realname + '\'' +
                    ", role_name='" + role_name + '\'' +
                    '}';
        }
    }

    @Override
    public String toString() {
        return "Login{" +
                "status=" + status +
                ", data=" + data +
                '}';
    }
}
