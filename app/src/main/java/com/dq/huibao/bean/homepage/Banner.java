package com.dq.huibao.bean.homepage;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Description：
 * Created by jingang on 2017/11/4.
 */

public class Banner {


    /**
     * result : 1
     * msg : 成功
     * data : {"params":{"shape":"shape3","align":"center","scroll":"2","bgcolor":""},"data":[{"id":"B0000000000002","imgurl":"http://www.dequanhuibao.com/attachment/images/1604/2017/10/Z5qSsusTTeXQSSt4EE5X558PElS57q.jpg","hrefurl":{"selfurl":false,"urlstr":"http://mp.weixin.qq.com/s/JDvkR3RHLjmbOAuwUWbnJw","i":"","m":"","c":"","do":"","p":"","method":"","query":""},"sysurl":"url"},{"id":"B0000000000003","imgurl":"http://www.dequanhuibao.com/attachment/images/1604/2017/10/ew8hAWmwt2nyH5H852NNkz8W8cm9Tt.jpg","hrefurl":{"selfurl":true,"urlstr":"","i":"1604","m":"sz_yi","c":"entry","do":"shop","p":"list","method":"","query":"&ccate=4735"},"sysurl":"url"},{"id":"B1503980629975","imgurl":"http://www.dequanhuibao.com/attachment/images/1604/2017/10/aXkzkxCRsRFcB5FBRr2RRKXRcbxD1R.jpg","hrefurl":{"selfurl":true,"urlstr":"","i":"1604","m":"sz_yi","c":"entry","do":"shop","p":"detail","method":"","query":"&id=91703"},"sysurl":"url"},{"id":"B1506160261503","imgurl":"http://www.dequanhuibao.com/attachment/images/1604/2017/11/RHBrAcNNfyZNnSnH0fHalo0NLAs6Ce.jpg","hrefurl":{"selfurl":true,"urlstr":"","i":"1604","m":"sz_yi","c":"entry","do":"shop","p":"detail","method":"","query":"&id=95385"},"sysurl":"url"},{"id":"B1506331202928","imgurl":"http://www.dequanhuibao.com/attachment/images/1604/2017/09/sB0USHrEZmsIeb0p0PeMIShB3YbheH.jpg","hrefurl":{"selfurl":true,"urlstr":"","i":"1604","m":"sz_yi","c":"entry","do":"shop","p":"detail","method":"","query":"&id=94595"},"sysurl":"url"},{"id":"B1506413902867","imgurl":"http://www.dequanhuibao.com/attachment/images/1604/2017/10/H83azgc3VdkV48614eWQ6deVn4eW6C.jpg","hrefurl":{"selfurl":true,"urlstr":"","i":"1604","m":"sz_yi","c":"entry","do":"plugin","p":"coupon","method":"","query":""},"sysurl":"url"},{"id":"B1508750942469","imgurl":"http://www.dequanhuibao.com/attachment/images/1604/2017/10/px9ZVb0j5lZwKdc1yd99kB1XzbzWKB.jpg","hrefurl":{"selfurl":true,"urlstr":"","i":"1604","m":"sz_yi","c":"entry","do":"shop","p":"detail","method":"","query":"&id=91703"},"sysurl":"url"},{"id":"B1509779526140","imgurl":"http://www.dequanhuibao.com/attachment/images/1604/2017/11/CfWI0FtFfFKeFn5Ii5SteFfgVZ05vM.png","hrefurl":{"selfurl":true,"urlstr":"","i":"1604","m":"sz_yi","c":"entry","do":"shop","p":"list","method":"","query":"&pcate=4503&ccate=4751"},"sysurl":"url"}]}
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
         * params : {"shape":"shape3","align":"center","scroll":"2","bgcolor":""}
         * data : [{"id":"B0000000000002","imgurl":"http://www.dequanhuibao.com/attachment/images/1604/2017/10/Z5qSsusTTeXQSSt4EE5X558PElS57q.jpg","hrefurl":{"selfurl":false,"urlstr":"http://mp.weixin.qq.com/s/JDvkR3RHLjmbOAuwUWbnJw","i":"","m":"","c":"","do":"","p":"","method":"","query":""},"sysurl":"url"},{"id":"B0000000000003","imgurl":"http://www.dequanhuibao.com/attachment/images/1604/2017/10/ew8hAWmwt2nyH5H852NNkz8W8cm9Tt.jpg","hrefurl":{"selfurl":true,"urlstr":"","i":"1604","m":"sz_yi","c":"entry","do":"shop","p":"list","method":"","query":"&ccate=4735"},"sysurl":"url"},{"id":"B1503980629975","imgurl":"http://www.dequanhuibao.com/attachment/images/1604/2017/10/aXkzkxCRsRFcB5FBRr2RRKXRcbxD1R.jpg","hrefurl":{"selfurl":true,"urlstr":"","i":"1604","m":"sz_yi","c":"entry","do":"shop","p":"detail","method":"","query":"&id=91703"},"sysurl":"url"},{"id":"B1506160261503","imgurl":"http://www.dequanhuibao.com/attachment/images/1604/2017/11/RHBrAcNNfyZNnSnH0fHalo0NLAs6Ce.jpg","hrefurl":{"selfurl":true,"urlstr":"","i":"1604","m":"sz_yi","c":"entry","do":"shop","p":"detail","method":"","query":"&id=95385"},"sysurl":"url"},{"id":"B1506331202928","imgurl":"http://www.dequanhuibao.com/attachment/images/1604/2017/09/sB0USHrEZmsIeb0p0PeMIShB3YbheH.jpg","hrefurl":{"selfurl":true,"urlstr":"","i":"1604","m":"sz_yi","c":"entry","do":"shop","p":"detail","method":"","query":"&id=94595"},"sysurl":"url"},{"id":"B1506413902867","imgurl":"http://www.dequanhuibao.com/attachment/images/1604/2017/10/H83azgc3VdkV48614eWQ6deVn4eW6C.jpg","hrefurl":{"selfurl":true,"urlstr":"","i":"1604","m":"sz_yi","c":"entry","do":"plugin","p":"coupon","method":"","query":""},"sysurl":"url"},{"id":"B1508750942469","imgurl":"http://www.dequanhuibao.com/attachment/images/1604/2017/10/px9ZVb0j5lZwKdc1yd99kB1XzbzWKB.jpg","hrefurl":{"selfurl":true,"urlstr":"","i":"1604","m":"sz_yi","c":"entry","do":"shop","p":"detail","method":"","query":"&id=91703"},"sysurl":"url"},{"id":"B1509779526140","imgurl":"http://www.dequanhuibao.com/attachment/images/1604/2017/11/CfWI0FtFfFKeFn5Ii5SteFfgVZ05vM.png","hrefurl":{"selfurl":true,"urlstr":"","i":"1604","m":"sz_yi","c":"entry","do":"shop","p":"list","method":"","query":"&pcate=4503&ccate=4751"},"sysurl":"url"}]
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
             * shape : shape3
             * align : center
             * scroll : 2
             * bgcolor :
             */

            private String shape;
            private String align;
            private String scroll;
            private String bgcolor;

            public String getShape() {
                return shape;
            }

            public void setShape(String shape) {
                this.shape = shape;
            }

            public String getAlign() {
                return align;
            }

            public void setAlign(String align) {
                this.align = align;
            }

            public String getScroll() {
                return scroll;
            }

            public void setScroll(String scroll) {
                this.scroll = scroll;
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
             * id : B0000000000002
             * imgurl : http://www.dequanhuibao.com/attachment/images/1604/2017/10/Z5qSsusTTeXQSSt4EE5X558PElS57q.jpg
             * hrefurl : {"selfurl":false,"urlstr":"http://mp.weixin.qq.com/s/JDvkR3RHLjmbOAuwUWbnJw","i":"","m":"","c":"","do":"","p":"","method":"","query":""}
             * sysurl : url
             */

            private String id;
            private String imgurl;
            private HrefurlBean hrefurl;
            private String sysurl;

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

            public String getSysurl() {
                return sysurl;
            }

            public void setSysurl(String sysurl) {
                this.sysurl = sysurl;
            }

            public static class HrefurlBean {
                /**
                 * selfurl : false
                 * urlstr : http://mp.weixin.qq.com/s/JDvkR3RHLjmbOAuwUWbnJw
                 * i :
                 * m :
                 * c :
                 * do :
                 * p :
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
                        ", sysurl='" + sysurl + '\'' +
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
        return "Banner{" +
                "result='" + result + '\'' +
                ", msg='" + msg + '\'' +
                ", data=" + data +
                '}';
    }
}
