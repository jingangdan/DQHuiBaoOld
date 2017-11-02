package com.dq.huibao.bean;

import java.util.List;

/**
 * Description：
 * Created by jingang on 2017/11/2.
 */
public class HomePageTest {
    private String result;
    private String msg;
    private List<Data> data;

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

    public List<Data> getData() {
        return data;
    }

    public void setData(List<Data> data) {
        this.data = data;
    }



    public class Data {
        private String id;
        private String temp;
        private Params params;
        private List<DataBean> dataBeans;
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

        public Params getParams() {
            return params;
        }

        public void setParams(Params params) {
            this.params = params;
        }

        public List<DataBean> getDataBeans() {
            return dataBeans;
        }

        public void setDataBeans(List<DataBean> dataBeans) {
            this.dataBeans = dataBeans;
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


        public class Params{
            private String placeholder;
            private String style;
            private String color;
            private String bgcolor;
            private String bordercolor;
            private String searchurl;
            private String uniacid;
            private String shape;
            private String align;
            private String scroll;
            private String num;
            private String notice;
            private String noticehref;
            private List<Layout> layout;
            private String showIndex;
            private String selection;
            private String currentPos;
            private CurrentLayout currentLayout;
            private String showtitle;
            private String titlecolor;
            private String showname;
            private String title;
            private String option;
            private String buysub;
            private String price;
            private String goodhref;
            private String type;
            private String credithref;

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

            public String getSearchurl() {
                return searchurl;
            }

            public void setSearchurl(String searchurl) {
                this.searchurl = searchurl;
            }

            public String getUniacid() {
                return uniacid;
            }

            public void setUniacid(String uniacid) {
                this.uniacid = uniacid;
            }

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

            public String getNum() {
                return num;
            }

            public void setNum(String num) {
                this.num = num;
            }

            public String getNotice() {
                return notice;
            }

            public void setNotice(String notice) {
                this.notice = notice;
            }

            public String getNoticehref() {
                return noticehref;
            }

            public void setNoticehref(String noticehref) {
                this.noticehref = noticehref;
            }

            public List<Layout> getLayout() {
                return layout;
            }

            public void setLayout(List<Layout> layout) {
                this.layout = layout;
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

            public CurrentLayout getCurrentLayout() {
                return currentLayout;
            }

            public void setCurrentLayout(CurrentLayout currentLayout) {
                this.currentLayout = currentLayout;
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

            public String getGoodhref() {
                return goodhref;
            }

            public void setGoodhref(String goodhref) {
                this.goodhref = goodhref;
            }

            public String getType() {
                return type;
            }

            public void setType(String type) {
                this.type = type;
            }

            public String getCredithref() {
                return credithref;
            }

            public void setCredithref(String credithref) {
                this.credithref = credithref;
            }

            @Override
            public String toString() {
                return "Params{" +
                        "placeholder='" + placeholder + '\'' +
                        ", style='" + style + '\'' +
                        ", color='" + color + '\'' +
                        ", bgcolor='" + bgcolor + '\'' +
                        ", bordercolor='" + bordercolor + '\'' +
                        ", searchurl='" + searchurl + '\'' +
                        ", uniacid='" + uniacid + '\'' +
                        ", shape='" + shape + '\'' +
                        ", align='" + align + '\'' +
                        ", scroll='" + scroll + '\'' +
                        ", num='" + num + '\'' +
                        ", notice='" + notice + '\'' +
                        ", noticehref='" + noticehref + '\'' +
                        ", layout=" + layout +
                        ", showIndex='" + showIndex + '\'' +
                        ", selection='" + selection + '\'' +
                        ", currentPos='" + currentPos + '\'' +
                        ", currentLayout=" + currentLayout +
                        ", showtitle='" + showtitle + '\'' +
                        ", titlecolor='" + titlecolor + '\'' +
                        ", showname='" + showname + '\'' +
                        ", title='" + title + '\'' +
                        ", option='" + option + '\'' +
                        ", buysub='" + buysub + '\'' +
                        ", price='" + price + '\'' +
                        ", goodhref='" + goodhref + '\'' +
                        ", type='" + type + '\'' +
                        ", credithref='" + credithref + '\'' +
                        '}';
            }

            public class Layout{
                private List<One> ones;
                private List<Two> twos;

                public List<One> getOnes() {
                    return ones;
                }

                public void setOnes(List<One> ones) {
                    this.ones = ones;
                }

                public List<Two> getTwos() {
                    return twos;
                }

                public void setTwos(List<Two> twos) {
                    this.twos = twos;
                }


                public class One{
                    private String cols;
                    private String rows;
                    private String isempty;
                    private String imgurl;
                    private String classname;
                    private String url;

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

                    public String getIsempty() {
                        return isempty;
                    }

                    public void setIsempty(String isempty) {
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

                    public String getUrl() {
                        return url;
                    }

                    public void setUrl(String url) {
                        this.url = url;
                    }

                    @Override
                    public String toString() {
                        return "One{" +
                                "cols='" + cols + '\'' +
                                ", rows='" + rows + '\'' +
                                ", isempty='" + isempty + '\'' +
                                ", imgurl='" + imgurl + '\'' +
                                ", classname='" + classname + '\'' +
                                ", url='" + url + '\'' +
                                '}';
                    }
                }

                public class Two{
                    private String cols;
                    private String rows;
                    private String isempty;
                    private String imgurl;
                    private String classname;
                    private String url;

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

                    public String getIsempty() {
                        return isempty;
                    }

                    public void setIsempty(String isempty) {
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

                    public String getUrl() {
                        return url;
                    }

                    public void setUrl(String url) {
                        this.url = url;
                    }

                    @Override
                    public String toString() {
                        return "Two{" +
                                "cols='" + cols + '\'' +
                                ", rows='" + rows + '\'' +
                                ", isempty='" + isempty + '\'' +
                                ", imgurl='" + imgurl + '\'' +
                                ", classname='" + classname + '\'' +
                                ", url='" + url + '\'' +
                                '}';
                    }
                }

                @Override
                public String toString() {
                    return "Layout{" +
                            "ones=" + ones +
                            ", twos=" + twos +
                            '}';
                }
            }

            public class CurrentLayout{
                private String cols;
                private String rows;
                private String isempty;
                private String imgurl;
                private String classname;
                private String url;

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

                public String getIsempty() {
                    return isempty;
                }

                public void setIsempty(String isempty) {
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

                public String getUrl() {
                    return url;
                }

                public void setUrl(String url) {
                    this.url = url;
                }

                @Override
                public String toString() {
                    return "CurrentLayout{" +
                            "cols='" + cols + '\'' +
                            ", rows='" + rows + '\'' +
                            ", isempty='" + isempty + '\'' +
                            ", imgurl='" + imgurl + '\'' +
                            ", classname='" + classname + '\'' +
                            ", url='" + url + '\'' +
                            '}';
                }
            }

        }

        public class DataBean{
            private String id;
            private String imgurl;
            private String hrefurl;
            private String sysurl;
            private String text;
            private String color;
            private String option;
            private String img;
            private String goodid;
            private String name;
            private String priceold;
            private String pricenow;
            private String sales;
            private String unit;

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

            public String getHrefurl() {
                return hrefurl;
            }

            public void setHrefurl(String hrefurl) {
                this.hrefurl = hrefurl;
            }

            public String getSysurl() {
                return sysurl;
            }

            public void setSysurl(String sysurl) {
                this.sysurl = sysurl;
            }

            public String getText() {
                return text;
            }

            public void setText(String text) {
                this.text = text;
            }

            public String getColor() {
                return color;
            }

            public void setColor(String color) {
                this.color = color;
            }

            public String getOption() {
                return option;
            }

            public void setOption(String option) {
                this.option = option;
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

            public String getSales() {
                return sales;
            }

            public void setSales(String sales) {
                this.sales = sales;
            }

            public String getUnit() {
                return unit;
            }

            public void setUnit(String unit) {
                this.unit = unit;
            }

            @Override
            public String toString() {
                return "DataBean{" +
                        "id='" + id + '\'' +
                        ", imgurl='" + imgurl + '\'' +
                        ", hrefurl='" + hrefurl + '\'' +
                        ", sysurl='" + sysurl + '\'' +
                        ", text='" + text + '\'' +
                        ", color='" + color + '\'' +
                        ", option='" + option + '\'' +
                        ", img='" + img + '\'' +
                        ", goodid='" + goodid + '\'' +
                        ", name='" + name + '\'' +
                        ", priceold='" + priceold + '\'' +
                        ", pricenow='" + pricenow + '\'' +
                        ", sales='" + sales + '\'' +
                        ", unit='" + unit + '\'' +
                        '}';
            }
        }

        @Override
        public String toString() {
            return "Data{" +
                    "id='" + id + '\'' +
                    ", temp='" + temp + '\'' +
                    ", params=" + params +
                    ", dataBeans=" + dataBeans +
                    ", other='" + other + '\'' +
                    ", content='" + content + '\'' +
                    ", isadmin='" + isadmin + '\'' +
                    '}';
        }
    }

    @Override
    public String toString() {
        return "HomePageTest{" +
                "result='" + result + '\'' +
                ", msg='" + msg + '\'' +
                ", data=" + data +
                '}';
    }
}
