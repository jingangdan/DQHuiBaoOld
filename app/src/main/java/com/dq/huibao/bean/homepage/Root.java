package com.dq.huibao.bean.homepage;

import java.util.List;

/**
 * Description：
 * Created by jingang on 2017/11/4.
 */

public class Root {

    /**
     * result : 1
     * msg : 成功
     * data : [{"id":"M1502787821049","temp":"search","other":"","content":"","isadmin":"1"},{"id":"M1502332482666","temp":"banner","other":"","content":"","isadmin":"1"},{"id":"M1502332896567","temp":"menu","other":"","content":"","isadmin":"1"},{"id":"M1502332899359","temp":"menu","other":"","content":"","isadmin":"1"},{"id":"M1502333388997","temp":"notice","other":"","content":"","isadmin":"1"},{"id":"M1504261114930","temp":"picture","other":"","content":"","isadmin":"1"},{"id":"M1502333573901","temp":"picture","other":"","content":"","isadmin":"1"},{"id":"M1502333500860","temp":"cube","other":"","content":"","isadmin":"1"},{"id":"M1502333839448","temp":"picture","other":"","content":"","isadmin":"1"},{"id":"M1502333901482","temp":"cube","other":"","content":"","isadmin":"1"},{"id":"M1502333841298","temp":"picture","other":"","content":"","isadmin":"1"},{"id":"M1502334183449","temp":"picture","other":"","content":"","isadmin":"1"},{"id":"M1506739248507","temp":"goods","other":"","content":"","isadmin":"1"},{"id":"M1506138571303","temp":"goods","other":"","content":"","isadmin":"1"},{"id":"M1505182626653","temp":"goods","other":"","content":"","isadmin":"1"},{"id":"M1502334244030","temp":"goods","other":"","content":"","isadmin":"1"},{"id":"M1503480002281","temp":"goods","other":"","content":"","isadmin":"1"},{"id":"M1502334290915","temp":"picture","other":"","content":"","isadmin":"1"},{"id":"M1502334416501","temp":"goods","other":"","content":"","isadmin":"1"},{"id":"M1502334294210","temp":"picture","other":"","content":"","isadmin":"1"},{"id":"M1502334354275","temp":"picture","other":"","content":"","isadmin":"1"},{"id":"M1502334589194","temp":"goods","other":"","content":"","isadmin":"1"}]
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
         * id : M1502787821049
         * temp : search
         * other :
         * content :
         * isadmin : 1
         */

        private String id;
        private String temp;
        private String other;
        private String content;
        private String isadmin;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getTemp() {
            return temp;
        }

        public void setTemp(String temp) {
            this.temp = temp;
        }

        public String getOther() {
            return other;
        }

        public void setOther(String other) {
            this.other = other;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public String getIsadmin() {
            return isadmin;
        }

        public void setIsadmin(String isadmin) {
            this.isadmin = isadmin;
        }

        @Override
        public String toString() {
            return "DataBean{" +
                    "id='" + id + '\'' +
                    ", temp='" + temp + '\'' +
                    ", other='" + other + '\'' +
                    ", content='" + content + '\'' +
                    ", isadmin='" + isadmin + '\'' +
                    '}';
        }
    }

    @Override
    public String toString() {
        return "Root{" +
                "result='" + result + '\'' +
                ", msg='" + msg + '\'' +
                ", data=" + data +
                '}';
    }
}
