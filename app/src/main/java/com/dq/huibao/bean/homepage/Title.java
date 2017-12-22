package com.dq.huibao.bean.homepage;

/**
 * Created by jingang on 2017/12/12.
 */

public class Title {

    /**
     * result : 1
     * msg : 成功
     * data : {"params":{"title1":"食 品","title2":"","showtitle2":"0","fontsize1":"16px","fontsize2":"14px","align":"center","color":"#000"},"data":""}
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
         * params : {"title1":"食 品","title2":"","showtitle2":"0","fontsize1":"16px","fontsize2":"14px","align":"center","color":"#000"}
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
             * title1 : 食 品
             * title2 :
             * showtitle2 : 0
             * fontsize1 : 16px
             * fontsize2 : 14px
             * align : center
             * color : #000
             */

            private String title1;
            private String title2;
            private String showtitle2;
            private String fontsize1;
            private String fontsize2;
            private String align;
            private String color;

            public String getTitle1() {
                return title1;
            }

            public void setTitle1(String title1) {
                this.title1 = title1;
            }

            public String getTitle2() {
                return title2;
            }

            public void setTitle2(String title2) {
                this.title2 = title2;
            }

            public String getShowtitle2() {
                return showtitle2;
            }

            public void setShowtitle2(String showtitle2) {
                this.showtitle2 = showtitle2;
            }

            public String getFontsize1() {
                return fontsize1;
            }

            public void setFontsize1(String fontsize1) {
                this.fontsize1 = fontsize1;
            }

            public String getFontsize2() {
                return fontsize2;
            }

            public void setFontsize2(String fontsize2) {
                this.fontsize2 = fontsize2;
            }

            public String getAlign() {
                return align;
            }

            public void setAlign(String align) {
                this.align = align;
            }

            public String getColor() {
                return color;
            }

            public void setColor(String color) {
                this.color = color;
            }

            @Override
            public String toString() {
                return "ParamsBean{" +
                        "title1='" + title1 + '\'' +
                        ", title2='" + title2 + '\'' +
                        ", showtitle2='" + showtitle2 + '\'' +
                        ", fontsize1='" + fontsize1 + '\'' +
                        ", fontsize2='" + fontsize2 + '\'' +
                        ", align='" + align + '\'' +
                        ", color='" + color + '\'' +
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
        return "Title{" +
                "result='" + result + '\'' +
                ", msg='" + msg + '\'' +
                ", data=" + data +
                '}';
    }
}
