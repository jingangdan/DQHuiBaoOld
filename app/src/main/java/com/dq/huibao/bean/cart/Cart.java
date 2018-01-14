package com.dq.huibao.bean.cart;

import java.util.List;

/**
 * 添加购物车返回
 * Created by jingang on 2018/1/13.
 */

public class Cart {


    /**
     * status : 1
     * data : [{"shopid":"0","shopname":"惠宝商城","goodslist":[{"id":"6","uid":"4","goodsid":"2653","count":"1","createtime":"1515825895","shopid":"0","marketprice":"118.00","optionid":"4120","goods":{"canbuy":1,"id":2653,"goodsname":"新款欧美简约牛津电脑背包 学生科技书包轻便男包 休闲双肩包批发","status":1,"istime":"0","minbuy":0,"maxbuy":0,"option":{"id":"4120","goodsid":"2653","title":"黑色","thumb":"/attachment/images/1604/2017/12/M8zB8Gk2yqNi3Dgqq5y222G838cBiy.jpg","productprice":"168.00","marketprice":"118.00","costprice":"95.00","stock":"387","weight":"750","specs":"55691","gno":""},"thumb":"/attachment/images/1604/2017/12/M8zB8Gk2yqNi3Dgqq5y222G838cBiy.jpg","unit":"个","stock":987,"buycount":1,"weight":"750","shopid":0,"issendfree":1,"dispatch":0,"marketprice":"118.00","discount_money":0,"discountprice":"118.00","score":0}},{"id":"5","uid":"4","goodsid":"2590","count":"1","createtime":"1515810631","shopid":"0","marketprice":"70.00","optionid":"3923","goods":{"canbuy":0,"id":2590,"goodsname":"金丝虎新品男士保暖白色男式衬衫衣长袖加绒加厚衬衫商务长袖男装","status":0,"istime":"0","minbuy":0,"maxbuy":0,"option":{"id":"3923","goodsid":"2590","title":"T601-43+38","thumb":"/attachment/images/sz_yi/1604/2017/12/F99cH120x7u1XT7n07Xclu0Zxs0T1c.jpg","productprice":"159.00","marketprice":"70.00","costprice":"60.00","stock":"99","weight":"560","specs":"55532_55525","gno":""},"thumb":"/attachment/images/sz_yi/1604/2017/12/F99cH120x7u1XT7n07Xclu0Zxs0T1c.jpg","unit":"","stock":3465,"buycount":1,"weight":"560","shopid":0,"issendfree":0,"dispatch":0,"marketprice":"70.00","discount_money":0,"discountprice":"70.00","score":0}},{"id":"4","uid":"4","goodsid":"2560","count":"2","createtime":"1515809798","shopid":"0","marketprice":"36.00","optionid":"3508","goods":{"canbuy":1,"id":2560,"goodsname":"手表时尚韩版男士学生超薄礼品表便宜石英男表","status":1,"istime":"0","minbuy":0,"maxbuy":0,"option":{"id":"3508","goodsid":"2560","title":"黑面啡带","thumb":"/attachment/images/sz_yi/1604/2017/12/BQ5jXCmUM8XNjEONH8NnOWh8NJhxze.jpg","productprice":"56.00","marketprice":"36.00","costprice":"16.00","stock":"500","weight":"0","specs":"55451","gno":""},"thumb":"/attachment/images/sz_yi/1604/2017/12/BQ5jXCmUM8XNjEONH8NnOWh8NJhxze.jpg","unit":"个","stock":4500,"buycount":2,"weight":0,"shopid":0,"issendfree":0,"dispatch":0,"marketprice":"36.00","discount_money":0,"discountprice":"36.00","score":0}}]}]
     */

    private int status;
    private List<DataBean> data;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * shopid : 0
         * shopname : 惠宝商城
         * goodslist : [{"id":"6","uid":"4","goodsid":"2653","count":"1","createtime":"1515825895","shopid":"0","marketprice":"118.00","optionid":"4120","goods":{"canbuy":1,"id":2653,"goodsname":"新款欧美简约牛津电脑背包 学生科技书包轻便男包 休闲双肩包批发","status":1,"istime":"0","minbuy":0,"maxbuy":0,"option":{"id":"4120","goodsid":"2653","title":"黑色","thumb":"/attachment/images/1604/2017/12/M8zB8Gk2yqNi3Dgqq5y222G838cBiy.jpg","productprice":"168.00","marketprice":"118.00","costprice":"95.00","stock":"387","weight":"750","specs":"55691","gno":""},"thumb":"/attachment/images/1604/2017/12/M8zB8Gk2yqNi3Dgqq5y222G838cBiy.jpg","unit":"个","stock":987,"buycount":1,"weight":"750","shopid":0,"issendfree":1,"dispatch":0,"marketprice":"118.00","discount_money":0,"discountprice":"118.00","score":0}},{"id":"5","uid":"4","goodsid":"2590","count":"1","createtime":"1515810631","shopid":"0","marketprice":"70.00","optionid":"3923","goods":{"canbuy":0,"id":2590,"goodsname":"金丝虎新品男士保暖白色男式衬衫衣长袖加绒加厚衬衫商务长袖男装","status":0,"istime":"0","minbuy":0,"maxbuy":0,"option":{"id":"3923","goodsid":"2590","title":"T601-43+38","thumb":"/attachment/images/sz_yi/1604/2017/12/F99cH120x7u1XT7n07Xclu0Zxs0T1c.jpg","productprice":"159.00","marketprice":"70.00","costprice":"60.00","stock":"99","weight":"560","specs":"55532_55525","gno":""},"thumb":"/attachment/images/sz_yi/1604/2017/12/F99cH120x7u1XT7n07Xclu0Zxs0T1c.jpg","unit":"","stock":3465,"buycount":1,"weight":"560","shopid":0,"issendfree":0,"dispatch":0,"marketprice":"70.00","discount_money":0,"discountprice":"70.00","score":0}},{"id":"4","uid":"4","goodsid":"2560","count":"2","createtime":"1515809798","shopid":"0","marketprice":"36.00","optionid":"3508","goods":{"canbuy":1,"id":2560,"goodsname":"手表时尚韩版男士学生超薄礼品表便宜石英男表","status":1,"istime":"0","minbuy":0,"maxbuy":0,"option":{"id":"3508","goodsid":"2560","title":"黑面啡带","thumb":"/attachment/images/sz_yi/1604/2017/12/BQ5jXCmUM8XNjEONH8NnOWh8NJhxze.jpg","productprice":"56.00","marketprice":"36.00","costprice":"16.00","stock":"500","weight":"0","specs":"55451","gno":""},"thumb":"/attachment/images/sz_yi/1604/2017/12/BQ5jXCmUM8XNjEONH8NnOWh8NJhxze.jpg","unit":"个","stock":4500,"buycount":2,"weight":0,"shopid":0,"issendfree":0,"dispatch":0,"marketprice":"36.00","discount_money":0,"discountprice":"36.00","score":0}}]
         */

        private String shopid;
        private String shopname;
        private List<GoodslistBean> goodslist;
        public boolean isCheck = false;
        public boolean isChoosed;

        public boolean isCheck() {
            return isCheck;
        }

        public void setCheck(boolean check) {
            isCheck = check;
        }

        public boolean isChoosed() {
            return isChoosed;
        }

        public void setChoosed(boolean choosed) {
            isChoosed = choosed;
        }

        public String getShopid() {
            return shopid;
        }

        public void setShopid(String shopid) {
            this.shopid = shopid;
        }

        public String getShopname() {
            return shopname;
        }

        public void setShopname(String shopname) {
            this.shopname = shopname;
        }

        public List<GoodslistBean> getGoodslist() {
            return goodslist;
        }

        public void setGoodslist(List<GoodslistBean> goodslist) {
            this.goodslist = goodslist;
        }

        public static class GoodslistBean {
            /**
             * id : 6
             * uid : 4
             * goodsid : 2653
             * count : 1
             * createtime : 1515825895
             * shopid : 0
             * marketprice : 118.00
             * optionid : 4120
             * goods : {"canbuy":1,"id":2653,"goodsname":"新款欧美简约牛津电脑背包 学生科技书包轻便男包 休闲双肩包批发","status":1,"istime":"0","minbuy":0,"maxbuy":0,"option":{"id":"4120","goodsid":"2653","title":"黑色","thumb":"/attachment/images/1604/2017/12/M8zB8Gk2yqNi3Dgqq5y222G838cBiy.jpg","productprice":"168.00","marketprice":"118.00","costprice":"95.00","stock":"387","weight":"750","specs":"55691","gno":""},"thumb":"/attachment/images/1604/2017/12/M8zB8Gk2yqNi3Dgqq5y222G838cBiy.jpg","unit":"个","stock":987,"buycount":1,"weight":"750","shopid":0,"issendfree":1,"dispatch":0,"marketprice":"118.00","discount_money":0,"discountprice":"118.00","score":0}
             */

            private String id;
            private String uid;
            private String goodsid;
            private String count;
            private String createtime;
            private String shopid;
            private String marketprice;
            private String optionid;
            private GoodsBean goods;
            public boolean isCheck = false;
            public boolean isChoosed;

            public boolean isCheck() {
                return isCheck;
            }

            public void setCheck(boolean check) {
                isCheck = check;
            }

            public boolean isChoosed() {
                return isChoosed;
            }

            public void setChoosed(boolean choosed) {
                isChoosed = choosed;
            }

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getUid() {
                return uid;
            }

            public void setUid(String uid) {
                this.uid = uid;
            }

            public String getGoodsid() {
                return goodsid;
            }

            public void setGoodsid(String goodsid) {
                this.goodsid = goodsid;
            }

            public String getCount() {
                return count;
            }

            public void setCount(String count) {
                this.count = count;
            }

            public String getCreatetime() {
                return createtime;
            }

            public void setCreatetime(String createtime) {
                this.createtime = createtime;
            }

            public String getShopid() {
                return shopid;
            }

            public void setShopid(String shopid) {
                this.shopid = shopid;
            }

            public String getMarketprice() {
                return marketprice;
            }

            public void setMarketprice(String marketprice) {
                this.marketprice = marketprice;
            }

            public String getOptionid() {
                return optionid;
            }

            public void setOptionid(String optionid) {
                this.optionid = optionid;
            }

            public GoodsBean getGoods() {
                return goods;
            }

            public void setGoods(GoodsBean goods) {
                this.goods = goods;
            }

            public static class GoodsBean {
                /**
                 * canbuy : 1
                 * id : 2653
                 * goodsname : 新款欧美简约牛津电脑背包 学生科技书包轻便男包 休闲双肩包批发
                 * status : 1
                 * istime : 0
                 * minbuy : 0
                 * maxbuy : 0
                 * option : {"id":"4120","goodsid":"2653","title":"黑色","thumb":"/attachment/images/1604/2017/12/M8zB8Gk2yqNi3Dgqq5y222G838cBiy.jpg","productprice":"168.00","marketprice":"118.00","costprice":"95.00","stock":"387","weight":"750","specs":"55691","gno":""}
                 * thumb : /attachment/images/1604/2017/12/M8zB8Gk2yqNi3Dgqq5y222G838cBiy.jpg
                 * unit : 个
                 * stock : 987
                 * buycount : 1
                 * weight : 750
                 * shopid : 0
                 * issendfree : 1
                 * dispatch : 0
                 * marketprice : 118.00
                 * discount_money : 0
                 * discountprice : 118.00
                 * score : 0
                 */

                private int canbuy;
                private int id;
                private String goodsname;
                private int status;
                private String istime;
                private int minbuy;
                private int maxbuy;
                private OptionBean option;
                private String thumb;
                private String unit;
                private int stock;
                private int buycount;
                private String weight;
                private int shopid;
                private int issendfree;
                private int dispatch;
                private String marketprice;
                private int discount_money;
                private String discountprice;
                private int score;

                public int getCanbuy() {
                    return canbuy;
                }

                public void setCanbuy(int canbuy) {
                    this.canbuy = canbuy;
                }

                public int getId() {
                    return id;
                }

                public void setId(int id) {
                    this.id = id;
                }

                public String getGoodsname() {
                    return goodsname;
                }

                public void setGoodsname(String goodsname) {
                    this.goodsname = goodsname;
                }

                public int getStatus() {
                    return status;
                }

                public void setStatus(int status) {
                    this.status = status;
                }

                public String getIstime() {
                    return istime;
                }

                public void setIstime(String istime) {
                    this.istime = istime;
                }

                public int getMinbuy() {
                    return minbuy;
                }

                public void setMinbuy(int minbuy) {
                    this.minbuy = minbuy;
                }

                public int getMaxbuy() {
                    return maxbuy;
                }

                public void setMaxbuy(int maxbuy) {
                    this.maxbuy = maxbuy;
                }

                public OptionBean getOption() {
                    return option;
                }

                public void setOption(OptionBean option) {
                    this.option = option;
                }

                public String getThumb() {
                    return thumb;
                }

                public void setThumb(String thumb) {
                    this.thumb = thumb;
                }

                public String getUnit() {
                    return unit;
                }

                public void setUnit(String unit) {
                    this.unit = unit;
                }

                public int getStock() {
                    return stock;
                }

                public void setStock(int stock) {
                    this.stock = stock;
                }

                public int getBuycount() {
                    return buycount;
                }

                public void setBuycount(int buycount) {
                    this.buycount = buycount;
                }

                public String getWeight() {
                    return weight;
                }

                public void setWeight(String weight) {
                    this.weight = weight;
                }

                public int getShopid() {
                    return shopid;
                }

                public void setShopid(int shopid) {
                    this.shopid = shopid;
                }

                public int getIssendfree() {
                    return issendfree;
                }

                public void setIssendfree(int issendfree) {
                    this.issendfree = issendfree;
                }

                public int getDispatch() {
                    return dispatch;
                }

                public void setDispatch(int dispatch) {
                    this.dispatch = dispatch;
                }

                public String getMarketprice() {
                    return marketprice;
                }

                public void setMarketprice(String marketprice) {
                    this.marketprice = marketprice;
                }

                public int getDiscount_money() {
                    return discount_money;
                }

                public void setDiscount_money(int discount_money) {
                    this.discount_money = discount_money;
                }

                public String getDiscountprice() {
                    return discountprice;
                }

                public void setDiscountprice(String discountprice) {
                    this.discountprice = discountprice;
                }

                public int getScore() {
                    return score;
                }

                public void setScore(int score) {
                    this.score = score;
                }

                public static class OptionBean {
                    /**
                     * id : 4120
                     * goodsid : 2653
                     * title : 黑色
                     * thumb : /attachment/images/1604/2017/12/M8zB8Gk2yqNi3Dgqq5y222G838cBiy.jpg
                     * productprice : 168.00
                     * marketprice : 118.00
                     * costprice : 95.00
                     * stock : 387
                     * weight : 750
                     * specs : 55691
                     * gno :
                     */

                    private String id;
                    private String goodsid;
                    private String title;
                    private String thumb;
                    private String productprice;
                    private String marketprice;
                    private String costprice;
                    private String stock;
                    private String weight;
                    private String specs;
                    private String gno;

                    public String getId() {
                        return id;
                    }

                    public void setId(String id) {
                        this.id = id;
                    }

                    public String getGoodsid() {
                        return goodsid;
                    }

                    public void setGoodsid(String goodsid) {
                        this.goodsid = goodsid;
                    }

                    public String getTitle() {
                        return title;
                    }

                    public void setTitle(String title) {
                        this.title = title;
                    }

                    public String getThumb() {
                        return thumb;
                    }

                    public void setThumb(String thumb) {
                        this.thumb = thumb;
                    }

                    public String getProductprice() {
                        return productprice;
                    }

                    public void setProductprice(String productprice) {
                        this.productprice = productprice;
                    }

                    public String getMarketprice() {
                        return marketprice;
                    }

                    public void setMarketprice(String marketprice) {
                        this.marketprice = marketprice;
                    }

                    public String getCostprice() {
                        return costprice;
                    }

                    public void setCostprice(String costprice) {
                        this.costprice = costprice;
                    }

                    public String getStock() {
                        return stock;
                    }

                    public void setStock(String stock) {
                        this.stock = stock;
                    }

                    public String getWeight() {
                        return weight;
                    }

                    public void setWeight(String weight) {
                        this.weight = weight;
                    }

                    public String getSpecs() {
                        return specs;
                    }

                    public void setSpecs(String specs) {
                        this.specs = specs;
                    }

                    public String getGno() {
                        return gno;
                    }

                    public void setGno(String gno) {
                        this.gno = gno;
                    }

                    @Override
                    public String toString() {
                        return "OptionBean{" +
                                "id='" + id + '\'' +
                                ", goodsid='" + goodsid + '\'' +
                                ", title='" + title + '\'' +
                                ", thumb='" + thumb + '\'' +
                                ", productprice='" + productprice + '\'' +
                                ", marketprice='" + marketprice + '\'' +
                                ", costprice='" + costprice + '\'' +
                                ", stock='" + stock + '\'' +
                                ", weight='" + weight + '\'' +
                                ", specs='" + specs + '\'' +
                                ", gno='" + gno + '\'' +
                                '}';
                    }
                }

                @Override
                public String toString() {
                    return "GoodsBean{" +
                            "canbuy=" + canbuy +
                            ", id=" + id +
                            ", goodsname='" + goodsname + '\'' +
                            ", status=" + status +
                            ", istime='" + istime + '\'' +
                            ", minbuy=" + minbuy +
                            ", maxbuy=" + maxbuy +
                            ", option=" + option +
                            ", thumb='" + thumb + '\'' +
                            ", unit='" + unit + '\'' +
                            ", stock=" + stock +
                            ", buycount=" + buycount +
                            ", weight='" + weight + '\'' +
                            ", shopid=" + shopid +
                            ", issendfree=" + issendfree +
                            ", dispatch=" + dispatch +
                            ", marketprice='" + marketprice + '\'' +
                            ", discount_money=" + discount_money +
                            ", discountprice='" + discountprice + '\'' +
                            ", score=" + score +
                            '}';
                }
            }

            @Override
            public String toString() {
                return "GoodslistBean{" +
                        "id='" + id + '\'' +
                        ", uid='" + uid + '\'' +
                        ", goodsid='" + goodsid + '\'' +
                        ", count='" + count + '\'' +
                        ", createtime='" + createtime + '\'' +
                        ", shopid='" + shopid + '\'' +
                        ", marketprice='" + marketprice + '\'' +
                        ", optionid='" + optionid + '\'' +
                        ", goods=" + goods +
                        ", isCheck=" + isCheck +
                        ", isChoosed=" + isChoosed +
                        '}';
            }
        }

        @Override
        public String toString() {
            return "DataBean{" +
                    "shopid='" + shopid + '\'' +
                    ", shopname='" + shopname + '\'' +
                    ", goodslist=" + goodslist +
                    ", isCheck=" + isCheck +
                    ", isChoosed=" + isChoosed +
                    '}';
        }
    }

    @Override
    public String toString() {
        return "Cart{" +
                "status=" + status +
                ", data=" + data +
                '}';
    }
}
