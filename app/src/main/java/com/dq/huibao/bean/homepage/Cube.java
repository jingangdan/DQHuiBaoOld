package com.dq.huibao.bean.homepage;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Description：
 * Created by jingang on 2017/11/4.
 */

public class Cube {

    /**
     * result : 1
     * msg : 成功
     * data : {"params":{"bgcolor":"","layout":[{"cols":"2","rows":"1","isempty":false,"imgurl":"http://www.dequanhuibao.com/attachment/images/1604/2017/10/Nhvw74V4oJJwOi67oji6OJO6JI0vHH.jpg","classname":"index-0","url":{"selfurl":true,"urlstr":"","i":"1604","m":"sz_yi","c":"entry","do":"shop","p":"list","method":"","query":"&ccate=4735"}},{"cols":"2","rows":"1","isempty":false,"imgurl":"http://www.dequanhuibao.com/attachment/images/1604/2017/08/HVXv2xb2t24r9DdSBTN4I7xI9IBVk2.jpg","classname":"index-1","url":{"selfurl":true,"urlstr":"","i":"1604","m":"sz_yi","c":"entry","do":"shop","p":"list","method":"","query":"&pcate=4523"}},{"cols":"2","rows":"1","isempty":false,"imgurl":"http://www.dequanhuibao.com/attachment/images/1604/2017/08/Q8ijoC63ZjSsJiUR6Oj3PiI3pC3Pgs.jpg","classname":"index-2","url":{"selfurl":true,"urlstr":"","i":"1604","m":"sz_yi","c":"entry","do":"shop","p":"list","method":"","query":"&pcate=4533"}},{"cols":"2","rows":"1","isempty":false,"imgurl":"http://www.dequanhuibao.com/attachment/images/1604/2017/08/H40oEhPn6i3AE054A0v00o0656n3ji.jpg","classname":"index-3","url":{"selfurl":true,"urlstr":"","i":"1604","m":"sz_yi","c":"entry","do":"shop","p":"list","method":"","query":"&pcate=4454"}},{"cols":"2","rows":"1","isempty":false,"imgurl":"http://www.dequanhuibao.com/attachment/images/1604/2017/08/yzsYxmPEY27WWmx2A4YEHZhYmdRWdh.jpg","classname":"index-4","url":{"selfurl":true,"urlstr":"","i":"1604","m":"sz_yi","c":"entry","do":"shop","p":"list","method":"","query":"&pcate=4659"}},{"cols":"2","rows":"1","isempty":false,"imgurl":"http://www.dequanhuibao.com/attachment/images/1604/2017/08/ZuRl01zgYr7KHmkshQLgGu4zSRrLhR.jpg","classname":"index-5","url":{"selfurl":true,"urlstr":"","i":"1604","m":"sz_yi","c":"entry","do":"shop","p":"list","method":"","query":"&pcate=4514"}},{"cols":"1","rows":"1","isempty":false,"imgurl":"http://www.dequanhuibao.com/attachment/images/1604/2017/08/n6vnz35NPdeNn6N6jbi6q3En68Eno6.jpg","classname":"index-8","url":{"selfurl":false,"urlstr":"https://m.ly.com/guoneiyou/?refid=346535712","i":"","m":"","c":"","do":"","p":"","method":"","query":""}},{"cols":"1","rows":"1","isempty":false,"imgurl":"http://www.dequanhuibao.com/attachment/images/1604/2017/08/k5WZDhdo39mEd19BCx3nmec5FdmCz3.jpg","classname":"index-9","url":{"selfurl":false,"urlstr":"https://m.ly.com/dujia/?refid=346535712","i":"","m":"","c":"","do":"","p":"","method":"","query":""}},{"cols":"1","rows":"1","isempty":false,"imgurl":"http://www.dequanhuibao.com/attachment/images/1604/2017/08/J7qqqkK1I7bd6qJB1bkQ5J1QVvWB52.jpg","classname":"index-10","url":{"selfurl":false,"urlstr":"https://m.ly.com/bustour/?refid=346535712","i":"","m":"","c":"","do":"","p":"","method":"","query":""}},{"cols":"1","rows":"1","isempty":false,"imgurl":"http://www.dequanhuibao.com/attachment/images/1604/2017/08/bC6O5ozco904qLzj5jcc6jqc2L5J4F.jpg","classname":"index-11","url":{"selfurl":false,"urlstr":"https://m.ly.com/youlun/?refid=346535712","i":"","m":"","c":"","do":"","p":"","method":"","query":""}}],"showIndex":"12","selection":"","currentPos":"","currentLayout":{"cols":"2","rows":"1","isempty":true,"imgurl":"http://www.dequanhuibao.com/attachment/images/1604/2017/10/Nhvw74V4oJJwOi67oji6OJO6JI0vHH.jpg","classname":"index-0","url":{"selfurl":true,"urlstr":"","i":"1604","m":"sz_yi","c":"entry","do":"shop","p":"list","method":"","query":"&ccate=4735"}}},"data":[]}
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
         * params : {"bgcolor":"","layout":[{"cols":"2","rows":"1","isempty":false,"imgurl":"http://www.dequanhuibao.com/attachment/images/1604/2017/10/Nhvw74V4oJJwOi67oji6OJO6JI0vHH.jpg","classname":"index-0","url":{"selfurl":true,"urlstr":"","i":"1604","m":"sz_yi","c":"entry","do":"shop","p":"list","method":"","query":"&ccate=4735"}},{"cols":"2","rows":"1","isempty":false,"imgurl":"http://www.dequanhuibao.com/attachment/images/1604/2017/08/HVXv2xb2t24r9DdSBTN4I7xI9IBVk2.jpg","classname":"index-1","url":{"selfurl":true,"urlstr":"","i":"1604","m":"sz_yi","c":"entry","do":"shop","p":"list","method":"","query":"&pcate=4523"}},{"cols":"2","rows":"1","isempty":false,"imgurl":"http://www.dequanhuibao.com/attachment/images/1604/2017/08/Q8ijoC63ZjSsJiUR6Oj3PiI3pC3Pgs.jpg","classname":"index-2","url":{"selfurl":true,"urlstr":"","i":"1604","m":"sz_yi","c":"entry","do":"shop","p":"list","method":"","query":"&pcate=4533"}},{"cols":"2","rows":"1","isempty":false,"imgurl":"http://www.dequanhuibao.com/attachment/images/1604/2017/08/H40oEhPn6i3AE054A0v00o0656n3ji.jpg","classname":"index-3","url":{"selfurl":true,"urlstr":"","i":"1604","m":"sz_yi","c":"entry","do":"shop","p":"list","method":"","query":"&pcate=4454"}},{"cols":"2","rows":"1","isempty":false,"imgurl":"http://www.dequanhuibao.com/attachment/images/1604/2017/08/yzsYxmPEY27WWmx2A4YEHZhYmdRWdh.jpg","classname":"index-4","url":{"selfurl":true,"urlstr":"","i":"1604","m":"sz_yi","c":"entry","do":"shop","p":"list","method":"","query":"&pcate=4659"}},{"cols":"2","rows":"1","isempty":false,"imgurl":"http://www.dequanhuibao.com/attachment/images/1604/2017/08/ZuRl01zgYr7KHmkshQLgGu4zSRrLhR.jpg","classname":"index-5","url":{"selfurl":true,"urlstr":"","i":"1604","m":"sz_yi","c":"entry","do":"shop","p":"list","method":"","query":"&pcate=4514"}},{"cols":"1","rows":"1","isempty":false,"imgurl":"http://www.dequanhuibao.com/attachment/images/1604/2017/08/n6vnz35NPdeNn6N6jbi6q3En68Eno6.jpg","classname":"index-8","url":{"selfurl":false,"urlstr":"https://m.ly.com/guoneiyou/?refid=346535712","i":"","m":"","c":"","do":"","p":"","method":"","query":""}},{"cols":"1","rows":"1","isempty":false,"imgurl":"http://www.dequanhuibao.com/attachment/images/1604/2017/08/k5WZDhdo39mEd19BCx3nmec5FdmCz3.jpg","classname":"index-9","url":{"selfurl":false,"urlstr":"https://m.ly.com/dujia/?refid=346535712","i":"","m":"","c":"","do":"","p":"","method":"","query":""}},{"cols":"1","rows":"1","isempty":false,"imgurl":"http://www.dequanhuibao.com/attachment/images/1604/2017/08/J7qqqkK1I7bd6qJB1bkQ5J1QVvWB52.jpg","classname":"index-10","url":{"selfurl":false,"urlstr":"https://m.ly.com/bustour/?refid=346535712","i":"","m":"","c":"","do":"","p":"","method":"","query":""}},{"cols":"1","rows":"1","isempty":false,"imgurl":"http://www.dequanhuibao.com/attachment/images/1604/2017/08/bC6O5ozco904qLzj5jcc6jqc2L5J4F.jpg","classname":"index-11","url":{"selfurl":false,"urlstr":"https://m.ly.com/youlun/?refid=346535712","i":"","m":"","c":"","do":"","p":"","method":"","query":""}}],"showIndex":"12","selection":"","currentPos":"","currentLayout":{"cols":"2","rows":"1","isempty":true,"imgurl":"http://www.dequanhuibao.com/attachment/images/1604/2017/10/Nhvw74V4oJJwOi67oji6OJO6JI0vHH.jpg","classname":"index-0","url":{"selfurl":true,"urlstr":"","i":"1604","m":"sz_yi","c":"entry","do":"shop","p":"list","method":"","query":"&ccate=4735"}}}
         * data : []
         */

        private ParamsBean params;
        private List<?> data;

        public ParamsBean getParams() {
            return params;
        }

        public void setParams(ParamsBean params) {
            this.params = params;
        }

        public List<?> getData() {
            return data;
        }

        public void setData(List<?> data) {
            this.data = data;
        }

        public static class ParamsBean {
            /**
             * bgcolor :
             * layout : [{"cols":"2","rows":"1","isempty":false,"imgurl":"http://www.dequanhuibao.com/attachment/images/1604/2017/10/Nhvw74V4oJJwOi67oji6OJO6JI0vHH.jpg","classname":"index-0","url":{"selfurl":true,"urlstr":"","i":"1604","m":"sz_yi","c":"entry","do":"shop","p":"list","method":"","query":"&ccate=4735"}},{"cols":"2","rows":"1","isempty":false,"imgurl":"http://www.dequanhuibao.com/attachment/images/1604/2017/08/HVXv2xb2t24r9DdSBTN4I7xI9IBVk2.jpg","classname":"index-1","url":{"selfurl":true,"urlstr":"","i":"1604","m":"sz_yi","c":"entry","do":"shop","p":"list","method":"","query":"&pcate=4523"}},{"cols":"2","rows":"1","isempty":false,"imgurl":"http://www.dequanhuibao.com/attachment/images/1604/2017/08/Q8ijoC63ZjSsJiUR6Oj3PiI3pC3Pgs.jpg","classname":"index-2","url":{"selfurl":true,"urlstr":"","i":"1604","m":"sz_yi","c":"entry","do":"shop","p":"list","method":"","query":"&pcate=4533"}},{"cols":"2","rows":"1","isempty":false,"imgurl":"http://www.dequanhuibao.com/attachment/images/1604/2017/08/H40oEhPn6i3AE054A0v00o0656n3ji.jpg","classname":"index-3","url":{"selfurl":true,"urlstr":"","i":"1604","m":"sz_yi","c":"entry","do":"shop","p":"list","method":"","query":"&pcate=4454"}},{"cols":"2","rows":"1","isempty":false,"imgurl":"http://www.dequanhuibao.com/attachment/images/1604/2017/08/yzsYxmPEY27WWmx2A4YEHZhYmdRWdh.jpg","classname":"index-4","url":{"selfurl":true,"urlstr":"","i":"1604","m":"sz_yi","c":"entry","do":"shop","p":"list","method":"","query":"&pcate=4659"}},{"cols":"2","rows":"1","isempty":false,"imgurl":"http://www.dequanhuibao.com/attachment/images/1604/2017/08/ZuRl01zgYr7KHmkshQLgGu4zSRrLhR.jpg","classname":"index-5","url":{"selfurl":true,"urlstr":"","i":"1604","m":"sz_yi","c":"entry","do":"shop","p":"list","method":"","query":"&pcate=4514"}},{"cols":"1","rows":"1","isempty":false,"imgurl":"http://www.dequanhuibao.com/attachment/images/1604/2017/08/n6vnz35NPdeNn6N6jbi6q3En68Eno6.jpg","classname":"index-8","url":{"selfurl":false,"urlstr":"https://m.ly.com/guoneiyou/?refid=346535712","i":"","m":"","c":"","do":"","p":"","method":"","query":""}},{"cols":"1","rows":"1","isempty":false,"imgurl":"http://www.dequanhuibao.com/attachment/images/1604/2017/08/k5WZDhdo39mEd19BCx3nmec5FdmCz3.jpg","classname":"index-9","url":{"selfurl":false,"urlstr":"https://m.ly.com/dujia/?refid=346535712","i":"","m":"","c":"","do":"","p":"","method":"","query":""}},{"cols":"1","rows":"1","isempty":false,"imgurl":"http://www.dequanhuibao.com/attachment/images/1604/2017/08/J7qqqkK1I7bd6qJB1bkQ5J1QVvWB52.jpg","classname":"index-10","url":{"selfurl":false,"urlstr":"https://m.ly.com/bustour/?refid=346535712","i":"","m":"","c":"","do":"","p":"","method":"","query":""}},{"cols":"1","rows":"1","isempty":false,"imgurl":"http://www.dequanhuibao.com/attachment/images/1604/2017/08/bC6O5ozco904qLzj5jcc6jqc2L5J4F.jpg","classname":"index-11","url":{"selfurl":false,"urlstr":"https://m.ly.com/youlun/?refid=346535712","i":"","m":"","c":"","do":"","p":"","method":"","query":""}}]
             * showIndex : 12
             * selection :
             * currentPos :
             * currentLayout : {"cols":"2","rows":"1","isempty":true,"imgurl":"http://www.dequanhuibao.com/attachment/images/1604/2017/10/Nhvw74V4oJJwOi67oji6OJO6JI0vHH.jpg","classname":"index-0","url":{"selfurl":true,"urlstr":"","i":"1604","m":"sz_yi","c":"entry","do":"shop","p":"list","method":"","query":"&ccate=4735"}}
             */

            private String bgcolor;
            private String showIndex;
            private String selection;
            private String currentPos;
            private CurrentLayoutBean currentLayout;
            private List<LayoutBean> layout;

            public String getBgcolor() {
                return bgcolor;
            }

            public void setBgcolor(String bgcolor) {
                this.bgcolor = bgcolor;
            }

            public String getShowIndex() {
                return showIndex;
            }

            public void setShowIndex(String showIndex) {
                this.showIndex = showIndex;
            }

            public String getSelection() {
                return selection;
            }

            public void setSelection(String selection) {
                this.selection = selection;
            }

            public String getCurrentPos() {
                return currentPos;
            }

            public void setCurrentPos(String currentPos) {
                this.currentPos = currentPos;
            }

            public CurrentLayoutBean getCurrentLayout() {
                return currentLayout;
            }

            public void setCurrentLayout(CurrentLayoutBean currentLayout) {
                this.currentLayout = currentLayout;
            }

            public List<LayoutBean> getLayout() {
                return layout;
            }

            public void setLayout(List<LayoutBean> layout) {
                this.layout = layout;
            }

            public static class CurrentLayoutBean {
                /**
                 * cols : 2
                 * rows : 1
                 * isempty : true
                 * imgurl : http://www.dequanhuibao.com/attachment/images/1604/2017/10/Nhvw74V4oJJwOi67oji6OJO6JI0vHH.jpg
                 * classname : index-0
                 * url : {"selfurl":true,"urlstr":"","i":"1604","m":"sz_yi","c":"entry","do":"shop","p":"list","method":"","query":"&ccate=4735"}
                 */

                private String cols;
                private String rows;
                private boolean isempty;
                private String imgurl;
                private String classname;
                private UrlBean url;

                public String getCols() {
                    return cols;
                }

                public void setCols(String cols) {
                    this.cols = cols;
                }

                public String getRows() {
                    return rows;
                }

                public void setRows(String rows) {
                    this.rows = rows;
                }

                public boolean isIsempty() {
                    return isempty;
                }

                public void setIsempty(boolean isempty) {
                    this.isempty = isempty;
                }

                public String getImgurl() {
                    return imgurl;
                }

                public void setImgurl(String imgurl) {
                    this.imgurl = imgurl;
                }

                public String getClassname() {
                    return classname;
                }

                public void setClassname(String classname) {
                    this.classname = classname;
                }

                public UrlBean getUrl() {
                    return url;
                }

                public void setUrl(UrlBean url) {
                    this.url = url;
                }

                public static class UrlBean {
                    /**
                     * selfurl : true
                     * urlstr :
                     * i : 1604
                     * m : sz_yi
                     * c : entry
                     * do : shop
                     * p : list
                     * method :
                     * query : &ccate=4735
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

            public static class LayoutBean {
                /**
                 * cols : 2
                 * rows : 1
                 * isempty : false
                 * imgurl : http://www.dequanhuibao.com/attachment/images/1604/2017/10/Nhvw74V4oJJwOi67oji6OJO6JI0vHH.jpg
                 * classname : index-0
                 * url : {"selfurl":true,"urlstr":"","i":"1604","m":"sz_yi","c":"entry","do":"shop","p":"list","method":"","query":"&ccate=4735"}
                 */

                private String cols;
                private String rows;
                private boolean isempty;
                private String imgurl;
                private String classname;
                private UrlBeanX url;

                public String getCols() {
                    return cols;
                }

                public void setCols(String cols) {
                    this.cols = cols;
                }

                public String getRows() {
                    return rows;
                }

                public void setRows(String rows) {
                    this.rows = rows;
                }

                public boolean isIsempty() {
                    return isempty;
                }

                public void setIsempty(boolean isempty) {
                    this.isempty = isempty;
                }

                public String getImgurl() {
                    return imgurl;
                }

                public void setImgurl(String imgurl) {
                    this.imgurl = imgurl;
                }

                public String getClassname() {
                    return classname;
                }

                public void setClassname(String classname) {
                    this.classname = classname;
                }

                public UrlBeanX getUrl() {
                    return url;
                }

                public void setUrl(UrlBeanX url) {
                    this.url = url;
                }

                public static class UrlBeanX {
                    /**
                     * selfurl : true
                     * urlstr :
                     * i : 1604
                     * m : sz_yi
                     * c : entry
                     * do : shop
                     * p : list
                     * method :
                     * query : &ccate=4735
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
                        return "UrlBeanX{" +
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
                    return "LayoutBean{" +
                            "cols='" + cols + '\'' +
                            ", rows='" + rows + '\'' +
                            ", isempty=" + isempty +
                            ", imgurl='" + imgurl + '\'' +
                            ", classname='" + classname + '\'' +
                            ", url=" + url +
                            '}';
                }
            }

            @Override
            public String toString() {
                return "ParamsBean{" +
                        "bgcolor='" + bgcolor + '\'' +
                        ", showIndex='" + showIndex + '\'' +
                        ", selection='" + selection + '\'' +
                        ", currentPos='" + currentPos + '\'' +
                        ", currentLayout=" + currentLayout +
                        ", layout=" + layout +
                        '}';
            }
        }

        @Override
        public String toString() {
            return "DataBean{" +
                    "params=" + params +
                    ", data=" + data +
                    '}';
        }
    }

    @Override
    public String toString() {
        return "Cube{" +
                "result='" + result + '\'' +
                ", msg='" + msg + '\'' +
                ", data=" + data +
                '}';
    }
}
