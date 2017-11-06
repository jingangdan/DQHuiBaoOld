package com.dq.huibao.bean.homepage;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Description：
 * Created by jingang on 2017/11/4.
 */

public class Menu {


    /**
     * result : 1
     * msg : 成功
     * data : {"params":{"num":"20%","style":"100%","bgcolor":"#fff"},"data":[{"id":"F0000000000001","imgurl":"http://www.dequanhuibao.com/attachment/images/1604/2017/09/l7tC3iN73R33Cjt3jcrF8i8MTff8gM.jpg","text":"优惠券区","hrefurl":{"selfurl":true,"urlstr":"","i":"1604","m":"sz_yi","c":"entry","do":"plugin","p":"designer","method":"","query":"&pageid=590"},"color":""},{"id":"F0000000000002","imgurl":"http://www.dequanhuibao.com/attachment/images/1604/2017/09/Tc8U0lLqQwL5walyla9ZuU8oaZUPw9.gif","text":"限时购","hrefurl":{"selfurl":true,"urlstr":"","i":"1604","m":"sz_yi","c":"entry","do":"shop","p":"list","method":"","query":"&pcate=4503&ccate=4521"},"color":""},{"id":"F0000000000003","imgurl":"http://www.dequanhuibao.com/attachment/images/1604/2017/09/XaguUYynU92n68L9UXxV6n22n25n6u.jpg","text":"名品汇","hrefurl":{"selfurl":true,"urlstr":"","i":"1604","m":"sz_yi","c":"entry","do":"plugin","p":"designer","method":"","query":"&pageid=591"},"color":""},{"id":"F0000000000004","imgurl":"http://www.dequanhuibao.com/attachment/images/1604/2017/09/S9eK50kpmP0DG1K19dnKUeD0QNKSn3.jpg","text":"旅游休闲","hrefurl":{"selfurl":true,"urlstr":"","i":"1604","m":"sz_yi","c":"entry","do":"plugin","p":"designer","method":"","query":"&pageid=593"},"color":""},{"id":"F0000000000005","imgurl":"http://www.dequanhuibao.com/attachment/images/1604/2017/10/TlId11yoMhyiPRoNGPKnPLW2RIo1ov.jpg","text":"德泉自营","hrefurl":{"selfurl":true,"urlstr":"","i":"1604","m":"sz_yi","c":"entry","do":"shop","p":"list","method":"","query":"&ccate=4520"},"color":""}]}
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
        /**
         * params : {"num":"20%","style":"100%","bgcolor":"#fff"}
         * data : [{"id":"F0000000000001","imgurl":"http://www.dequanhuibao.com/attachment/images/1604/2017/09/l7tC3iN73R33Cjt3jcrF8i8MTff8gM.jpg","text":"优惠券区","hrefurl":{"selfurl":true,"urlstr":"","i":"1604","m":"sz_yi","c":"entry","do":"plugin","p":"designer","method":"","query":"&pageid=590"},"color":""},{"id":"F0000000000002","imgurl":"http://www.dequanhuibao.com/attachment/images/1604/2017/09/Tc8U0lLqQwL5walyla9ZuU8oaZUPw9.gif","text":"限时购","hrefurl":{"selfurl":true,"urlstr":"","i":"1604","m":"sz_yi","c":"entry","do":"shop","p":"list","method":"","query":"&pcate=4503&ccate=4521"},"color":""},{"id":"F0000000000003","imgurl":"http://www.dequanhuibao.com/attachment/images/1604/2017/09/XaguUYynU92n68L9UXxV6n22n25n6u.jpg","text":"名品汇","hrefurl":{"selfurl":true,"urlstr":"","i":"1604","m":"sz_yi","c":"entry","do":"plugin","p":"designer","method":"","query":"&pageid=591"},"color":""},{"id":"F0000000000004","imgurl":"http://www.dequanhuibao.com/attachment/images/1604/2017/09/S9eK50kpmP0DG1K19dnKUeD0QNKSn3.jpg","text":"旅游休闲","hrefurl":{"selfurl":true,"urlstr":"","i":"1604","m":"sz_yi","c":"entry","do":"plugin","p":"designer","method":"","query":"&pageid=593"},"color":""},{"id":"F0000000000005","imgurl":"http://www.dequanhuibao.com/attachment/images/1604/2017/10/TlId11yoMhyiPRoNGPKnPLW2RIo1ov.jpg","text":"德泉自营","hrefurl":{"selfurl":true,"urlstr":"","i":"1604","m":"sz_yi","c":"entry","do":"shop","p":"list","method":"","query":"&ccate=4520"},"color":""}]
         */

        private ParamsBean params;
        private List<DataBean> data;

        public ParamsBean getParams() {
            return params;
        }

        public void setParams(ParamsBean params) {
            this.params = params;
        }

        public List<DataBean> getData() {
            return data;
        }

        public void setData(List<DataBean> data) {
            this.data = data;
        }

        public static class ParamsBean {
            /**
             * num : 20%
             * style : 100%
             * bgcolor : #fff
             */

            private String num;
            private String style;
            private String bgcolor;

            public String getNum() {
                return num;
            }

            public void setNum(String num) {
                this.num = num;
            }

            public String getStyle() {
                return style;
            }

            public void setStyle(String style) {
                this.style = style;
            }

            public String getBgcolor() {
                return bgcolor;
            }

            public void setBgcolor(String bgcolor) {
                this.bgcolor = bgcolor;
            }
        }

        public static class DataBean {
            /**
             * id : F0000000000001
             * imgurl : http://www.dequanhuibao.com/attachment/images/1604/2017/09/l7tC3iN73R33Cjt3jcrF8i8MTff8gM.jpg
             * text : 优惠券区
             * hrefurl : {"selfurl":true,"urlstr":"","i":"1604","m":"sz_yi","c":"entry","do":"plugin","p":"designer","method":"","query":"&pageid=590"}
             * color :
             */

            private String id;
            private String imgurl;
            private String text;
            private HrefurlBean hrefurl;
            private String color;

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

            public String getText() {
                return text;
            }

            public void setText(String text) {
                this.text = text;
            }

            public HrefurlBean getHrefurl() {
                return hrefurl;
            }

            public void setHrefurl(HrefurlBean hrefurl) {
                this.hrefurl = hrefurl;
            }

            public String getColor() {
                return color;
            }

            public void setColor(String color) {
                this.color = color;
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
                        ", text='" + text + '\'' +
                        ", hrefurl=" + hrefurl +
                        ", color='" + color + '\'' +
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
        return "Menu{" +
                "result='" + result + '\'' +
                ", msg='" + msg + '\'' +
                ", data=" + data +
                '}';
    }
}
