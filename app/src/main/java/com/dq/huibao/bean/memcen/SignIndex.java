package com.dq.huibao.bean.memcen;

/**
 * Created by asus on 2018/1/31.
 */

public class SignIndex {

    /**
     * status : 1
     * data : {"id":"1","uid":"4","sign_time":"0","cur_money":"1.00000","cur_count":"0","add_time":"1517377089","status":"0","cansign":true}
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
         * id : 1
         * uid : 4
         * sign_time : 0
         * cur_money : 1.00000
         * cur_count : 0
         * add_time : 1517377089
         * status : 0
         * cansign : true
         */

        private String id;
        private String uid;
        private String sign_time;
        private String cur_money;
        private String cur_count;
        private String add_time;
        private String status;
        private boolean cansign;

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

        public String getSign_time() {
            return sign_time;
        }

        public void setSign_time(String sign_time) {
            this.sign_time = sign_time;
        }

        public String getCur_money() {
            return cur_money;
        }

        public void setCur_money(String cur_money) {
            this.cur_money = cur_money;
        }

        public String getCur_count() {
            return cur_count;
        }

        public void setCur_count(String cur_count) {
            this.cur_count = cur_count;
        }

        public String getAdd_time() {
            return add_time;
        }

        public void setAdd_time(String add_time) {
            this.add_time = add_time;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public boolean isCansign() {
            return cansign;
        }

        public void setCansign(boolean cansign) {
            this.cansign = cansign;
        }

        @Override
        public String toString() {
            return "DataBean{" +
                    "id='" + id + '\'' +
                    ", uid='" + uid + '\'' +
                    ", sign_time='" + sign_time + '\'' +
                    ", cur_money='" + cur_money + '\'' +
                    ", cur_count='" + cur_count + '\'' +
                    ", add_time='" + add_time + '\'' +
                    ", status='" + status + '\'' +
                    ", cansign=" + cansign +
                    '}';
        }
    }

    @Override
    public String toString() {
        return "SignIndex{" +
                "status=" + status +
                ", data=" + data +
                '}';
    }
}
