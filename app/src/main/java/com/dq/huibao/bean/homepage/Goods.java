package com.dq.huibao.bean.homepage;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Description：
 * Created by jingang on 2017/11/4.
 */

public class Goods {

    /**
     * result : 1
     * msg : 成功
     * data : {"params":{"style":"100%","showtitle":"1","titlecolor":"","bgcolor":"","showname":"1","title":"","option":"sale-rx","buysub":"buy-2","price":"1","type":"1","credithref":{"selfurl":true,"urlstr":"","i":"1604","m":"sz_yi","c":"entry","do":"plugin","p":"creditshop","method":"detail","query":""}},"data":[{"id":"G1509175533437","img":"http://www.dequanhuibao.com/attachment/images/1604/2017/10/i33mAggxZD9My9aYjk83GyyAj3O3gU.jpg","goodid":"98448","name":"韩国sexy secret加绒加厚7号打底裤瘦腿袜外穿瘦腿踩脚男士棉裤","priceold":"99.00","pricenow":"29.90","goodhref":{"selfurl":true,"urlstr":"","i":"1604","m":"sz_yi","c":"entry","do":"shop","p":"detail","method":"","query":"&id=98448"}},{"id":"G1509175544229","img":"http://www.dequanhuibao.com/attachment/images/1604/2017/10/F9UzRHxFM7PhFZuMFYzfCM7E99rc9X.jpg","goodid":"99120","name":"无印良品皮裤女香味加绒修身PU哑光muji高腰皮裤显瘦打底裤黑女裤","priceold":"200.00","pricenow":"35.90","goodhref":{"selfurl":true,"urlstr":"","i":"1604","m":"sz_yi","c":"entry","do":"shop","p":"detail","method":"","query":"&id=99120"}}]}
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
         * params : {"style":"100%","showtitle":"1","titlecolor":"","bgcolor":"","showname":"1","title":"","option":"sale-rx","buysub":"buy-2","price":"1","type":"1","credithref":{"selfurl":true,"urlstr":"","i":"1604","m":"sz_yi","c":"entry","do":"plugin","p":"creditshop","method":"detail","query":""}}
         * data : [{"id":"G1509175533437","img":"http://www.dequanhuibao.com/attachment/images/1604/2017/10/i33mAggxZD9My9aYjk83GyyAj3O3gU.jpg","goodid":"98448","name":"韩国sexy secret加绒加厚7号打底裤瘦腿袜外穿瘦腿踩脚男士棉裤","priceold":"99.00","pricenow":"29.90","goodhref":{"selfurl":true,"urlstr":"","i":"1604","m":"sz_yi","c":"entry","do":"shop","p":"detail","method":"","query":"&id=98448"}},{"id":"G1509175544229","img":"http://www.dequanhuibao.com/attachment/images/1604/2017/10/F9UzRHxFM7PhFZuMFYzfCM7E99rc9X.jpg","goodid":"99120","name":"无印良品皮裤女香味加绒修身PU哑光muji高腰皮裤显瘦打底裤黑女裤","priceold":"200.00","pricenow":"35.90","goodhref":{"selfurl":true,"urlstr":"","i":"1604","m":"sz_yi","c":"entry","do":"shop","p":"detail","method":"","query":"&id=99120"}}]
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
             * style : 100%
             * showtitle : 1
             * titlecolor :
             * bgcolor :
             * showname : 1
             * title :
             * option : sale-rx
             * buysub : buy-2
             * price : 1
             * type : 1
             * credithref : {"selfurl":true,"urlstr":"","i":"1604","m":"sz_yi","c":"entry","do":"plugin","p":"creditshop","method":"detail","query":""}
             */

            private String style;
            private String showtitle;
            private String titlecolor;
            private String bgcolor;
            private String showname;
            private String title;
            private String option;
            private String buysub;
            private String price;
            private String type;
            private CredithrefBean credithref;

            public String getStyle() {
                return style;
            }

            public void setStyle(String style) {
                this.style = style;
            }

            public String getShowtitle() {
                return showtitle;
            }

            public void setShowtitle(String showtitle) {
                this.showtitle = showtitle;
            }

            public String getTitlecolor() {
                return titlecolor;
            }

            public void setTitlecolor(String titlecolor) {
                this.titlecolor = titlecolor;
            }

            public String getBgcolor() {
                return bgcolor;
            }

            public void setBgcolor(String bgcolor) {
                this.bgcolor = bgcolor;
            }

            public String getShowname() {
                return showname;
            }

            public void setShowname(String showname) {
                this.showname = showname;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public String getOption() {
                return option;
            }

            public void setOption(String option) {
                this.option = option;
            }

            public String getBuysub() {
                return buysub;
            }

            public void setBuysub(String buysub) {
                this.buysub = buysub;
            }

            public String getPrice() {
                return price;
            }

            public void setPrice(String price) {
                this.price = price;
            }

            public String getType() {
                return type;
            }

            public void setType(String type) {
                this.type = type;
            }

            public CredithrefBean getCredithref() {
                return credithref;
            }

            public void setCredithref(CredithrefBean credithref) {
                this.credithref = credithref;
            }

            public static class CredithrefBean {
                /**
                 * selfurl : true
                 * urlstr :
                 * i : 1604
                 * m : sz_yi
                 * c : entry
                 * do : plugin
                 * p : creditshop
                 * method : detail
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
            }
        }

        public static class DataBean {
            /**
             * id : G1509175533437
             * img : http://www.dequanhuibao.com/attachment/images/1604/2017/10/i33mAggxZD9My9aYjk83GyyAj3O3gU.jpg
             * goodid : 98448
             * name : 韩国sexy secret加绒加厚7号打底裤瘦腿袜外穿瘦腿踩脚男士棉裤
             * priceold : 99.00
             * pricenow : 29.90
             * goodhref : {"selfurl":true,"urlstr":"","i":"1604","m":"sz_yi","c":"entry","do":"shop","p":"detail","method":"","query":"&id=98448"}
             */

            private String id;
            private String img;
            private String goodid;
            private String name;
            private String priceold;
            private String pricenow;
            private GoodhrefBean goodhref;

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getImg() {
                return img;
            }

            public void setImg(String img) {
                this.img = img;
            }

            public String getGoodid() {
                return goodid;
            }

            public void setGoodid(String goodid) {
                this.goodid = goodid;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getPriceold() {
                return priceold;
            }

            public void setPriceold(String priceold) {
                this.priceold = priceold;
            }

            public String getPricenow() {
                return pricenow;
            }

            public void setPricenow(String pricenow) {
                this.pricenow = pricenow;
            }

            public GoodhrefBean getGoodhref() {
                return goodhref;
            }

            public void setGoodhref(GoodhrefBean goodhref) {
                this.goodhref = goodhref;
            }

            public static class GoodhrefBean {
                /**
                 * selfurl : true
                 * urlstr :
                 * i : 1604
                 * m : sz_yi
                 * c : entry
                 * do : shop
                 * p : detail
                 * method :
                 * query : &id=98448
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
                    return "GoodhrefBean{" +
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
                        ", img='" + img + '\'' +
                        ", goodid='" + goodid + '\'' +
                        ", name='" + name + '\'' +
                        ", priceold='" + priceold + '\'' +
                        ", pricenow='" + pricenow + '\'' +
                        ", goodhref=" + goodhref +
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
        return "Goods{" +
                "result='" + result + '\'' +
                ", msg='" + msg + '\'' +
                ", data=" + data +
                '}';
    }
}
