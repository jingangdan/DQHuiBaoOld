package com.dq.huibao.bean.homepage;

import com.google.gson.annotations.SerializedName;

/**
 * Description：
 * Created by jingang on 2017/11/4.
 */

public class Notice {


    /**
     * result : 1
     * msg : 成功
     * data : {"params":{"color":"#a3a3a3","bgcolor":"#eeeeee","notice":"秋冬季新装先到先得，万款产品等你嗨！购！","noticehref":{"selfurl":true,"urlstr":"","i":"1604","m":"sz_yi","c":"entry","do":"shop","p":"list","method":"","query":"&ccate=4726"},"scroll":"1"},"data":""}
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
         * params : {"color":"#a3a3a3","bgcolor":"#eeeeee","notice":"秋冬季新装先到先得，万款产品等你嗨！购！","noticehref":{"selfurl":true,"urlstr":"","i":"1604","m":"sz_yi","c":"entry","do":"shop","p":"list","method":"","query":"&ccate=4726"},"scroll":"1"}
         * data :
         */

        private ParamsBean params;
        private String data;

        public ParamsBean getParams() {
            return params;
        }

        public void setParams(ParamsBean params) {
            this.params = params;
        }

        public String getData() {
            return data;
        }

        public void setData(String data) {
            this.data = data;
        }

        public static class ParamsBean {
            /**
             * color : #a3a3a3
             * bgcolor : #eeeeee
             * notice : 秋冬季新装先到先得，万款产品等你嗨！购！
             * noticehref : {"selfurl":true,"urlstr":"","i":"1604","m":"sz_yi","c":"entry","do":"shop","p":"list","method":"","query":"&ccate=4726"}
             * scroll : 1
             */

            private String color;
            private String bgcolor;
            private String notice;
            private NoticehrefBean noticehref;
            private String scroll;

            public String getColor() {
                return color;
            }

            public void setColor(String color) {
                this.color = color;
            }

            public String getBgcolor() {
                return bgcolor;
            }

            public void setBgcolor(String bgcolor) {
                this.bgcolor = bgcolor;
            }

            public String getNotice() {
                return notice;
            }

            public void setNotice(String notice) {
                this.notice = notice;
            }

            public NoticehrefBean getNoticehref() {
                return noticehref;
            }

            public void setNoticehref(NoticehrefBean noticehref) {
                this.noticehref = noticehref;
            }

            public String getScroll() {
                return scroll;
            }

            public void setScroll(String scroll) {
                this.scroll = scroll;
            }

            public static class NoticehrefBean {
                /**
                 * selfurl : true
                 * urlstr :
                 * i : 1604
                 * m : sz_yi
                 * c : entry
                 * do : shop
                 * p : list
                 * method :
                 * query : &ccate=4726
                 */

                private boolean selfurl;
                private String urlstr;
                private String i;
                private String m;
                private String c;
                @SerializedName("do")
                private String doX;
                private String p;
                private String method;
                private String query;

                public boolean isSelfurl() {
                    return selfurl;
                }

                public void setSelfurl(boolean selfurl) {
                    this.selfurl = selfurl;
                }

                public String getUrlstr() {
                    return urlstr;
                }

                public void setUrlstr(String urlstr) {
                    this.urlstr = urlstr;
                }

                public String getI() {
                    return i;
                }

                public void setI(String i) {
                    this.i = i;
                }

                public String getM() {
                    return m;
                }

                public void setM(String m) {
                    this.m = m;
                }

                public String getC() {
                    return c;
                }

                public void setC(String c) {
                    this.c = c;
                }

                public String getDoX() {
                    return doX;
                }

                public void setDoX(String doX) {
                    this.doX = doX;
                }

                public String getP() {
                    return p;
                }

                public void setP(String p) {
                    this.p = p;
                }

                public String getMethod() {
                    return method;
                }

                public void setMethod(String method) {
                    this.method = method;
                }

                public String getQuery() {
                    return query;
                }

                public void setQuery(String query) {
                    this.query = query;
                }

                @Override
                public String toString() {
                    return "NoticehrefBean{" +
                            "selfurl=" + selfurl +
                            ", urlstr='" + urlstr + '\'' +
                            ", i='" + i + '\'' +
                            ", m='" + m + '\'' +
                            ", c='" + c + '\'' +
                            ", doX='" + doX + '\'' +
                            ", p='" + p + '\'' +
                            ", method='" + method + '\'' +
                            ", query='" + query + '\'' +
                            '}';
                }
            }

            @Override
            public String toString() {
                return "ParamsBean{" +
                        "color='" + color + '\'' +
                        ", bgcolor='" + bgcolor + '\'' +
                        ", notice='" + notice + '\'' +
                        ", noticehref=" + noticehref +
                        ", scroll='" + scroll + '\'' +
                        '}';
            }
        }

        @Override
        public String toString() {
            return "DataBean{" +
                    "params=" + params +
                    ", data='" + data + '\'' +
                    '}';
        }
    }

    @Override
    public String toString() {
        return "Notice{" +
                "result='" + result + '\'' +
                ", msg='" + msg + '\'' +
                ", data=" + data +
                '}';
    }
}
