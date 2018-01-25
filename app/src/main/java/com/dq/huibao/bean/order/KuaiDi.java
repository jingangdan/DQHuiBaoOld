package com.dq.huibao.bean.order;

import java.util.List;

/**
 * 物流信息 实体类
 * Created by jingang on 2018/1/25.
 */

public class KuaiDi {

    /**
     * message : ok
     * nu : 811886971355
     * ischeck : 0
     * condition : H100
     * com : yuantong
     * status : 200
     * state : 5
     * data : [{"time":"2018-01-25 15:35:57","ftime":"2018-01-25 15:35:57","context":"福建省厦门市机场分部公司 派件中","location":""},{"time":"2018-01-24 21:31:10","ftime":"2018-01-24 21:31:10","context":"福建省厦门市公司 已发出","location":""},{"time":"2018-01-24 16:14:17","ftime":"2018-01-24 16:14:17","context":"泉州转运中心公司 已发出","location":""},{"time":"2018-01-24 16:10:54","ftime":"2018-01-24 16:10:54","context":"泉州转运中心公司 已收入","location":""},{"time":"2018-01-23 23:00:55","ftime":"2018-01-23 23:00:55","context":"广州转运中心公司 已发出","location":""},{"time":"2018-01-23 22:56:33","ftime":"2018-01-23 22:56:33","context":"广州转运中心公司 已收入","location":""},{"time":"2018-01-23 20:54:44","ftime":"2018-01-23 20:54:44","context":"广东省广州市新增城市公司 已发出","location":""},{"time":"2018-01-23 20:25:57","ftime":"2018-01-23 20:25:57","context":"广东省广州市新增城市公司 已打包","location":""},{"time":"2018-01-23 20:23:01","ftime":"2018-01-23 20:23:01","context":"广东省广州市新增城市公司 已收件","location":""},{"time":"2018-01-23 18:51:03","ftime":"2018-01-23 18:51:03","context":"广东省广州市新增城市公司 已收件","location":""}]
     */

    private String message;
    private String nu;
    private String ischeck;
    private String condition;
    private String com;
    private String status;
    private String state;
    private List<DataBean> data;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getNu() {
        return nu;
    }

    public void setNu(String nu) {
        this.nu = nu;
    }

    public String getIscheck() {
        return ischeck;
    }

    public void setIscheck(String ischeck) {
        this.ischeck = ischeck;
    }

    public String getCondition() {
        return condition;
    }

    public void setCondition(String condition) {
        this.condition = condition;
    }

    public String getCom() {
        return com;
    }

    public void setCom(String com) {
        this.com = com;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * time : 2018-01-25 15:35:57
         * ftime : 2018-01-25 15:35:57
         * context : 福建省厦门市机场分部公司 派件中
         * location :
         */

        private String time;
        private String ftime;
        private String context;
        private String location;

        public String getTime() {
            return time;
        }

        public void setTime(String time) {
            this.time = time;
        }

        public String getFtime() {
            return ftime;
        }

        public void setFtime(String ftime) {
            this.ftime = ftime;
        }

        public String getContext() {
            return context;
        }

        public void setContext(String context) {
            this.context = context;
        }

        public String getLocation() {
            return location;
        }

        public void setLocation(String location) {
            this.location = location;
        }

        @Override
        public String toString() {
            return "DataBean{" +
                    "time='" + time + '\'' +
                    ", ftime='" + ftime + '\'' +
                    ", context='" + context + '\'' +
                    ", location='" + location + '\'' +
                    '}';
        }
    }

    @Override
    public String toString() {
        return "KuaiDi{" +
                "message='" + message + '\'' +
                ", nu='" + nu + '\'' +
                ", ischeck='" + ischeck + '\'' +
                ", condition='" + condition + '\'' +
                ", com='" + com + '\'' +
                ", status='" + status + '\'' +
                ", state='" + state + '\'' +
                ", data=" + data +
                '}';
    }
}
