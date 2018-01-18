package com.dq.huibao.bean.cart;

import java.util.List;

/**
 * 确认订单 实体类
 * Created by jingang on 2018/1/18.
 */

public class CheckOrder {


    /**
     * status : 1
     * data : [{"shopid":"0","shopname":"惠宝商城","goodslist":[{"id":"4","uid":"4","goodsid":"2560","count":"2","createtime":"1515809798","shopid":"0","marketprice":"36.00","optionid":"3508","goods":{"canbuy":1,"id":2560,"goodsname":"手表时尚韩版男士学生超薄礼品表便宜石英男表","status":1,"istime":"0","minbuy":0,"maxbuy":0,"option":{"id":"3508","goodsid":"2560","title":"黑面啡带","thumb":"/attachment/images/sz_yi/1604/2017/12/BQ5jXCmUM8XNjEONH8NnOWh8NJhxze.jpg","productprice":"56.00","marketprice":"36.00","costprice":"16.00","stock":"500","weight":"0","specs":"55451","gno":""},"thumb":"/attachment/images/sz_yi/1604/2017/12/BQ5jXCmUM8XNjEONH8NnOWh8NJhxze.jpg","unit":"个","stock":4500,"buycount":2,"weight":0,"shopid":0,"issendfree":0,"marketprice":"36.00","discount_money":3.6,"discountprice":32.4,"score":32}}],"money_all":72,"discount_all":7.2,"score_all":64,"count_all":2,"dispatch_all":0}]
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
         * goodslist : [{"id":"4","uid":"4","goodsid":"2560","count":"2","createtime":"1515809798","shopid":"0","marketprice":"36.00","optionid":"3508","goods":{"canbuy":1,"id":2560,"goodsname":"手表时尚韩版男士学生超薄礼品表便宜石英男表","status":1,"istime":"0","minbuy":0,"maxbuy":0,"option":{"id":"3508","goodsid":"2560","title":"黑面啡带","thumb":"/attachment/images/sz_yi/1604/2017/12/BQ5jXCmUM8XNjEONH8NnOWh8NJhxze.jpg","productprice":"56.00","marketprice":"36.00","costprice":"16.00","stock":"500","weight":"0","specs":"55451","gno":""},"thumb":"/attachment/images/sz_yi/1604/2017/12/BQ5jXCmUM8XNjEONH8NnOWh8NJhxze.jpg","unit":"个","stock":4500,"buycount":2,"weight":0,"shopid":0,"issendfree":0,"marketprice":"36.00","discount_money":3.6,"discountprice":32.4,"score":32}}]
         * money_all : 72
         * discount_all : 7.2
         * score_all : 64
         * count_all : 2
         * dispatch_all : 0
         */

        private String shopid;
        private String shopname;
        private double money_all;
        private double discount_all;
        private double score_all;
        private int count_all;
        private double dispatch_all;
        private List<GoodslistBean> goodslist;

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

        public double getMoney_all() {
            return money_all;
        }

        public void setMoney_all(double money_all) {
            this.money_all = money_all;
        }

        public double getDiscount_all() {
            return discount_all;
        }

        public void setDiscount_all(double discount_all) {
            this.discount_all = discount_all;
        }

        public double getScore_all() {
            return score_all;
        }

        public void setScore_all(double score_all) {
            this.score_all = score_all;
        }

        public int getCount_all() {
            return count_all;
        }

        public void setCount_all(int count_all) {
            this.count_all = count_all;
        }

        public double getDispatch_all() {
            return dispatch_all;
        }

        public void setDispatch_all(double dispatch_all) {
            this.dispatch_all = dispatch_all;
        }

        public List<GoodslistBean> getGoodslist() {
            return goodslist;
        }

        public void setGoodslist(List<GoodslistBean> goodslist) {
            this.goodslist = goodslist;
        }

        public static class GoodslistBean {
            /**
             * id : 4
             * uid : 4
             * goodsid : 2560
             * count : 2
             * createtime : 1515809798
             * shopid : 0
             * marketprice : 36.00
             * optionid : 3508
             * goods : {"canbuy":1,"id":2560,"goodsname":"手表时尚韩版男士学生超薄礼品表便宜石英男表","status":1,"istime":"0","minbuy":0,"maxbuy":0,"option":{"id":"3508","goodsid":"2560","title":"黑面啡带","thumb":"/attachment/images/sz_yi/1604/2017/12/BQ5jXCmUM8XNjEONH8NnOWh8NJhxze.jpg","productprice":"56.00","marketprice":"36.00","costprice":"16.00","stock":"500","weight":"0","specs":"55451","gno":""},"thumb":"/attachment/images/sz_yi/1604/2017/12/BQ5jXCmUM8XNjEONH8NnOWh8NJhxze.jpg","unit":"个","stock":4500,"buycount":2,"weight":0,"shopid":0,"issendfree":0,"marketprice":"36.00","discount_money":3.6,"discountprice":32.4,"score":32}
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
                 * id : 2560
                 * goodsname : 手表时尚韩版男士学生超薄礼品表便宜石英男表
                 * status : 1
                 * istime : 0
                 * minbuy : 0
                 * maxbuy : 0
                 * option : {"id":"3508","goodsid":"2560","title":"黑面啡带","thumb":"/attachment/images/sz_yi/1604/2017/12/BQ5jXCmUM8XNjEONH8NnOWh8NJhxze.jpg","productprice":"56.00","marketprice":"36.00","costprice":"16.00","stock":"500","weight":"0","specs":"55451","gno":""}
                 * thumb : /attachment/images/sz_yi/1604/2017/12/BQ5jXCmUM8XNjEONH8NnOWh8NJhxze.jpg
                 * unit : 个
                 * stock : 4500
                 * buycount : 2
                 * weight : 0
                 * shopid : 0
                 * issendfree : 0
                 * marketprice : 36.00
                 * discount_money : 3.6
                 * discountprice : 32.4
                 * score : 32
                 */

                private int canbuy;
                private int id;
                private String goodsname;
                private int status;
                private String istime;
                private double minbuy;
                private double maxbuy;
                private OptionBean option;
                private String thumb;
                private String unit;
                private int stock;
                private int buycount;
                private int weight;
                private int shopid;
                private int issendfree;
                private String marketprice;
                private double discount_money;
                private double discountprice;
                private double score;

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

                public double getMinbuy() {
                    return minbuy;
                }

                public void setMinbuy(double minbuy) {
                    this.minbuy = minbuy;
                }

                public double getMaxbuy() {
                    return maxbuy;
                }

                public void setMaxbuy(double maxbuy) {
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

                public int getWeight() {
                    return weight;
                }

                public void setWeight(int weight) {
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

                public String getMarketprice() {
                    return marketprice;
                }

                public void setMarketprice(String marketprice) {
                    this.marketprice = marketprice;
                }

                public double getDiscount_money() {
                    return discount_money;
                }

                public void setDiscount_money(double discount_money) {
                    this.discount_money = discount_money;
                }

                public double getDiscountprice() {
                    return discountprice;
                }

                public void setDiscountprice(double discountprice) {
                    this.discountprice = discountprice;
                }

                public double getScore() {
                    return score;
                }

                public void setScore(double score) {
                    this.score = score;
                }

                public static class OptionBean {
                    /**
                     * id : 3508
                     * goodsid : 2560
                     * title : 黑面啡带
                     * thumb : /attachment/images/sz_yi/1604/2017/12/BQ5jXCmUM8XNjEONH8NnOWh8NJhxze.jpg
                     * productprice : 56.00
                     * marketprice : 36.00
                     * costprice : 16.00
                     * stock : 500
                     * weight : 0
                     * specs : 55451
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
                            ", weight=" + weight +
                            ", shopid=" + shopid +
                            ", issendfree=" + issendfree +
                            ", marketprice='" + marketprice + '\'' +
                            ", discount_money=" + discount_money +
                            ", discountprice=" + discountprice +
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
                        '}';
            }
        }

        @Override
        public String toString() {
            return "DataBean{" +
                    "shopid='" + shopid + '\'' +
                    ", shopname='" + shopname + '\'' +
                    ", money_all=" + money_all +
                    ", discount_all=" + discount_all +
                    ", score_all=" + score_all +
                    ", count_all=" + count_all +
                    ", dispatch_all=" + dispatch_all +
                    ", goodslist=" + goodslist +
                    '}';
        }
    }

    @Override
    public String toString() {
        return "CheckOrder{" +
                "status=" + status +
                ", data=" + data +
                '}';
    }
}
