package com.dq.huibao.bean.homepage;

import com.google.gson.annotations.SerializedName;

/**
 * Description：
 * Created by jingang on 2017/11/4.
 */

public class Search {


    /**
     * result : 1
     * msg : 成功
     * data : {"params":{"placeholder":"","style":"style2","color":"","bgcolor":"#f20000","bordercolor":"","searchurl":{"selfurl":true,"urlstr":"","i":"1604","m":"sz_yi","c":"entry","do":"shop","p":"list","method":"","query":""},"uniacid":"1604"},"data":""}
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
         * params : {"placeholder":"","style":"style2","color":"","bgcolor":"#f20000","bordercolor":"","searchurl":{"selfurl":true,"urlstr":"","i":"1604","m":"sz_yi","c":"entry","do":"shop","p":"list","method":"","query":""},"uniacid":"1604"}
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
             * placeholder :
             * style : style2
             * color :
             * bgcolor : #f20000
             * bordercolor :
             * searchurl : {"selfurl":true,"urlstr":"","i":"1604","m":"sz_yi","c":"entry","do":"shop","p":"list","method":"","query":""}
             * uniacid : 1604
             */

            private String placeholder;
            private String style;
            private String color;
            private String bgcolor;
            private String bordercolor;
            private SearchurlBean searchurl;
            private String uniacid;

            public String getPlaceholder() {
                return placeholder;
            }

            public void setPlaceholder(String placeholder) {
                this.placeholder = placeholder;
            }

            public String getStyle() {
                return style;
            }

            public void setStyle(String style) {
                this.style = style;
            }

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

            public String getBordercolor() {
                return bordercolor;
            }

            public void setBordercolor(String bordercolor) {
                this.bordercolor = bordercolor;
            }

            public SearchurlBean getSearchurl() {
                return searchurl;
            }

            public void setSearchurl(SearchurlBean searchurl) {
                this.searchurl = searchurl;
            }

            public String getUniacid() {
                return uniacid;
            }

            public void setUniacid(String uniacid) {
                this.uniacid = uniacid;
            }

            public static class SearchurlBean {
                /**
                 * selfurl : true
                 * urlstr :
                 * i : 1604
                 * m : sz_yi
                 * c : entry
                 * do : shop
                 * p : list
                 * method :
                 * query :
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
                    return "SearchurlBean{" +
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
                        "placeholder='" + placeholder + '\'' +
                        ", style='" + style + '\'' +
                        ", color='" + color + '\'' +
                        ", bgcolor='" + bgcolor + '\'' +
                        ", bordercolor='" + bordercolor + '\'' +
                        ", searchurl=" + searchurl +
                        ", uniacid='" + uniacid + '\'' +
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
        return "Search{" +
                "result='" + result + '\'' +
                ", msg='" + msg + '\'' +
                ", data=" + data +
                '}';
    }
}
