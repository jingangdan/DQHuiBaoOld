package com.dq.huibao.bean.homepage;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Description：
 * Created by jingang on 2017/11/4.
 */

public class Picture {

    /**
     * result : 1
     * msg : 成功
     * data : {"params":[],"data":[{"id":"P0000000000001","imgurl":"http://www.dequanhuibao.com/attachment/images/1604/2017/11/Ul688XRj6t8X8lL6RJDUhn5L86zj7X.jpg","hrefurl":{"selfurl":true,"urlstr":"","i":"1604","m":"sz_yi","c":"entry","do":"plugin","p":"designer","method":"","query":"&pageid=590"},"option":"0"}]}
     */

    private String result;
    private String msg;
    private DataBeanX data;

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

    public DataBeanX getData() {
        return data;
    }

    public void setData(DataBeanX data) {
        this.data = data;
    }

    public static class DataBeanX {
        private List<?> params;
        private List<DataBean> data;

        public List<?> getParams() {
            return params;
        }

        public void setParams(List<?> params) {
            this.params = params;
        }

        public List<DataBean> getData() {
            return data;
        }

        public void setData(List<DataBean> data) {
            this.data = data;
        }

        public static class DataBean {
            /**
             * id : P0000000000001
             * imgurl : http://www.dequanhuibao.com/attachment/images/1604/2017/11/Ul688XRj6t8X8lL6RJDUhn5L86zj7X.jpg
             * hrefurl : {"selfurl":true,"urlstr":"","i":"1604","m":"sz_yi","c":"entry","do":"plugin","p":"designer","method":"","query":"&pageid=590"}
             * option : 0
             */

            private String id;
            private String imgurl;
            private HrefurlBean hrefurl;
            private String option;

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getImgurl() {
                return imgurl;
            }

            public void setImgurl(String imgurl) {
                this.imgurl = imgurl;
            }

            public HrefurlBean getHrefurl() {
                return hrefurl;
            }

            public void setHrefurl(HrefurlBean hrefurl) {
                this.hrefurl = hrefurl;
            }

            public String getOption() {
                return option;
            }

            public void setOption(String option) {
                this.option = option;
            }

            public static class HrefurlBean {
                /**
                 * selfurl : true
                 * urlstr :
                 * i : 1604
                 * m : sz_yi
                 * c : entry
                 * do : plugin
                 * p : designer
                 * method :
                 * query : &pageid=590
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
                    return "HrefurlBean{" +
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
                return "DataBean{" +
                        "id='" + id + '\'' +
                        ", imgurl='" + imgurl + '\'' +
                        ", hrefurl=" + hrefurl +
                        ", option='" + option + '\'' +
                        '}';
            }
        }

        @Override
        public String toString() {
            return "DataBeanX{" +
                    "params=" + params +
                    ", data=" + data +
                    '}';
        }
    }

    @Override
    public String toString() {
        return "Picture{" +
                "result='" + result + '\'' +
                ", msg='" + msg + '\'' +
                ", data=" + data +
                '}';
    }
}
