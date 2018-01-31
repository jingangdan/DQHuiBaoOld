package com.dq.huibao.bean.memcen;

/**
 * Created by asus on 2018/1/31.
 */

public class Sign {

    /**
     * status : 0
     * data : {"status":false,"msg":"不在签到时间范围内","signcount":152301,"nosign":256890}
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
         * status : false
         * msg : 不在签到时间范围内
         * signcount : 152301
         * nosign : 256890
         */

        private boolean status;
        private String msg;
        private int signcount;
        private int nosign;

        public boolean isStatus() {
            return status;
        }

        public void setStatus(boolean status) {
            this.status = status;
        }

        public String getMsg() {
            return msg;
        }

        public void setMsg(String msg) {
            this.msg = msg;
        }

        public int getSigncount() {
            return signcount;
        }

        public void setSigncount(int signcount) {
            this.signcount = signcount;
        }

        public int getNosign() {
            return nosign;
        }

        public void setNosign(int nosign) {
            this.nosign = nosign;
        }

        @Override
        public String toString() {
            return "DataBean{" +
                    "status=" + status +
                    ", msg='" + msg + '\'' +
                    ", signcount=" + signcount +
                    ", nosign=" + nosign +
                    '}';
        }
    }

    @Override
    public String toString() {
        return "Sign{" +
                "status=" + status +
                ", data=" + data +
                '}';
    }
}
