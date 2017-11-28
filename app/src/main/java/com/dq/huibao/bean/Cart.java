package com.dq.huibao.bean;

import java.util.List;

/**
 * Description：购物车
 * Created by jingang on 2017/11/27.
 */

public class Cart {

    /**
     * result : 1
     * msg : 成功
     * data : {"total":"11","list":[{"id":"1727","total":"1","isopenchannel":"0","goodsid":"100103","stock":"26","optionstock":"26","minbuy":"0","maxbuy":"0","title":"350ml、430ml匡迪/华象HX-712号高真空商务礼品盒广告保温杯304内胆","thumb":"images/1604/2017/11/fs4akTAISQCI1n4UGq8ka8KIK0os01.jpg","marketprice":"30.00","productprice":"59.00","optiontitle":"350ml","optionid":"182562","specs":"105726"},{"id":"1726","total":"1","isopenchannel":"0","goodsid":"100047","stock":"997","optionstock":"","minbuy":"0","maxbuy":"0","title":"甜夹脆枣夹核桃仁休闲零食特产红枣500g","thumb":"images/1604/2017/11/Gl6rIj4NsAvaN6aCkdSXDZDicxsDXW.jpg","marketprice":"25.00","productprice":"39.00","optiontitle":"","optionid":"0","specs":""},{"id":"1725","total":"1","isopenchannel":"0","goodsid":"100038","stock":"1000","optionstock":"1000","minbuy":"0","maxbuy":"0","title":"和田特级一级大枣 红枣新疆特产干果500g 休闲零食","thumb":"images/1604/2017/11/J4AgPtGf014lBlztTQbQeqF22t7ugE.jpg","marketprice":"10.00","productprice":"28.00","optiontitle":"和田特级大枣+250g","optionid":"182817","specs":"105425_105939"},{"id":"1724","total":"8","isopenchannel":"1","goodsid":"100055","stock":"565","optionstock":"565","minbuy":"0","maxbuy":"0","title":"韩版女装秋季冬显瘦单层拉毛打底裤内拉绒九分裤","thumb":"images/1604/2017/11/EMwsyDijY5Psl2i8Yy568DWM2lYU7m.jpg","marketprice":"10.80","productprice":"19.90","optiontitle":"九分款+藏青色","optionid":"181968","specs":"105469_105478"}],"totalprice":"151.40","difference":"","ischannelpay":"","verify_goods_ischannelpick":"","virtual_currency":"1","yunbi_title":""}
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
         * total : 11
         * list : [{"id":"1727","total":"1","isopenchannel":"0","goodsid":"100103","stock":"26","optionstock":"26","minbuy":"0","maxbuy":"0","title":"350ml、430ml匡迪/华象HX-712号高真空商务礼品盒广告保温杯304内胆","thumb":"images/1604/2017/11/fs4akTAISQCI1n4UGq8ka8KIK0os01.jpg","marketprice":"30.00","productprice":"59.00","optiontitle":"350ml","optionid":"182562","specs":"105726"},{"id":"1726","total":"1","isopenchannel":"0","goodsid":"100047","stock":"997","optionstock":"","minbuy":"0","maxbuy":"0","title":"甜夹脆枣夹核桃仁休闲零食特产红枣500g","thumb":"images/1604/2017/11/Gl6rIj4NsAvaN6aCkdSXDZDicxsDXW.jpg","marketprice":"25.00","productprice":"39.00","optiontitle":"","optionid":"0","specs":""},{"id":"1725","total":"1","isopenchannel":"0","goodsid":"100038","stock":"1000","optionstock":"1000","minbuy":"0","maxbuy":"0","title":"和田特级一级大枣 红枣新疆特产干果500g 休闲零食","thumb":"images/1604/2017/11/J4AgPtGf014lBlztTQbQeqF22t7ugE.jpg","marketprice":"10.00","productprice":"28.00","optiontitle":"和田特级大枣+250g","optionid":"182817","specs":"105425_105939"},{"id":"1724","total":"8","isopenchannel":"1","goodsid":"100055","stock":"565","optionstock":"565","minbuy":"0","maxbuy":"0","title":"韩版女装秋季冬显瘦单层拉毛打底裤内拉绒九分裤","thumb":"images/1604/2017/11/EMwsyDijY5Psl2i8Yy568DWM2lYU7m.jpg","marketprice":"10.80","productprice":"19.90","optiontitle":"九分款+藏青色","optionid":"181968","specs":"105469_105478"}]
         * totalprice : 151.40
         * difference :
         * ischannelpay :
         * verify_goods_ischannelpick :
         * virtual_currency : 1
         * yunbi_title :
         */

        private String total;
        private String totalprice;
        private String difference;
        private String ischannelpay;
        private String verify_goods_ischannelpick;
        private String virtual_currency;
        private String yunbi_title;
        private List<ListBean> list;

        public String getTotal() {
            return total;
        }

        public void setTotal(String total) {
            this.total = total;
        }

        public String getTotalprice() {
            return totalprice;
        }

        public void setTotalprice(String totalprice) {
            this.totalprice = totalprice;
        }

        public String getDifference() {
            return difference;
        }

        public void setDifference(String difference) {
            this.difference = difference;
        }

        public String getIschannelpay() {
            return ischannelpay;
        }

        public void setIschannelpay(String ischannelpay) {
            this.ischannelpay = ischannelpay;
        }

        public String getVerify_goods_ischannelpick() {
            return verify_goods_ischannelpick;
        }

        public void setVerify_goods_ischannelpick(String verify_goods_ischannelpick) {
            this.verify_goods_ischannelpick = verify_goods_ischannelpick;
        }

        public String getVirtual_currency() {
            return virtual_currency;
        }

        public void setVirtual_currency(String virtual_currency) {
            this.virtual_currency = virtual_currency;
        }

        public String getYunbi_title() {
            return yunbi_title;
        }

        public void setYunbi_title(String yunbi_title) {
            this.yunbi_title = yunbi_title;
        }

        public List<ListBean> getList() {
            return list;
        }

        public void setList(List<ListBean> list) {
            this.list = list;
        }

        public static class ListBean {
            /**
             * id : 1727
             * total : 1
             * isopenchannel : 0
             * goodsid : 100103
             * stock : 26
             * optionstock : 26
             * minbuy : 0
             * maxbuy : 0
             * title : 350ml、430ml匡迪/华象HX-712号高真空商务礼品盒广告保温杯304内胆
             * thumb : images/1604/2017/11/fs4akTAISQCI1n4UGq8ka8KIK0os01.jpg
             * marketprice : 30.00
             * productprice : 59.00
             * optiontitle : 350ml
             * optionid : 182562
             * specs : 105726
             */

            private String id;
            private String total;
            private String isopenchannel;
            private String goodsid;
            private String stock;
            private String optionstock;
            private String minbuy;
            private String maxbuy;
            private String title;
            private String thumb;
            private String marketprice;
            private String productprice;
            private String optiontitle;
            private String optionid;
            private String specs;
            public boolean isCheck = false;
            public boolean isChoosed;

//            public boolean isCheck() {
//                return isCheck;
//            }
//
//            public void setCheck(boolean check) {
//                isCheck = check;
//            }

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

            public String getTotal() {
                return total;
            }

            public void setTotal(String total) {
                this.total = total;
            }

            public String getIsopenchannel() {
                return isopenchannel;
            }

            public void setIsopenchannel(String isopenchannel) {
                this.isopenchannel = isopenchannel;
            }

            public String getGoodsid() {
                return goodsid;
            }

            public void setGoodsid(String goodsid) {
                this.goodsid = goodsid;
            }

            public String getStock() {
                return stock;
            }

            public void setStock(String stock) {
                this.stock = stock;
            }

            public String getOptionstock() {
                return optionstock;
            }

            public void setOptionstock(String optionstock) {
                this.optionstock = optionstock;
            }

            public String getMinbuy() {
                return minbuy;
            }

            public void setMinbuy(String minbuy) {
                this.minbuy = minbuy;
            }

            public String getMaxbuy() {
                return maxbuy;
            }

            public void setMaxbuy(String maxbuy) {
                this.maxbuy = maxbuy;
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

            public String getMarketprice() {
                return marketprice;
            }

            public void setMarketprice(String marketprice) {
                this.marketprice = marketprice;
            }

            public String getProductprice() {
                return productprice;
            }

            public void setProductprice(String productprice) {
                this.productprice = productprice;
            }

            public String getOptiontitle() {
                return optiontitle;
            }

            public void setOptiontitle(String optiontitle) {
                this.optiontitle = optiontitle;
            }

            public String getOptionid() {
                return optionid;
            }

            public void setOptionid(String optionid) {
                this.optionid = optionid;
            }

            public String getSpecs() {
                return specs;
            }

            public void setSpecs(String specs) {
                this.specs = specs;
            }

            @Override
            public String toString() {
                return "ListBean{" +
                        "id='" + id + '\'' +
                        ", total='" + total + '\'' +
                        ", isopenchannel='" + isopenchannel + '\'' +
                        ", goodsid='" + goodsid + '\'' +
                        ", stock='" + stock + '\'' +
                        ", optionstock='" + optionstock + '\'' +
                        ", minbuy='" + minbuy + '\'' +
                        ", maxbuy='" + maxbuy + '\'' +
                        ", title='" + title + '\'' +
                        ", thumb='" + thumb + '\'' +
                        ", marketprice='" + marketprice + '\'' +
                        ", productprice='" + productprice + '\'' +
                        ", optiontitle='" + optiontitle + '\'' +
                        ", optionid='" + optionid + '\'' +
                        ", specs='" + specs + '\'' +
                        ", isCheck=" + isCheck +
                        ", isChoosed=" + isChoosed +
                        '}';
            }
        }

        @Override
        public String toString() {
            return "DataBean{" +
                    "total='" + total + '\'' +
                    ", totalprice='" + totalprice + '\'' +
                    ", difference='" + difference + '\'' +
                    ", ischannelpay='" + ischannelpay + '\'' +
                    ", verify_goods_ischannelpick='" + verify_goods_ischannelpick + '\'' +
                    ", virtual_currency='" + virtual_currency + '\'' +
                    ", yunbi_title='" + yunbi_title + '\'' +
                    ", list=" + list +
                    '}';
        }
    }

    @Override
    public String toString() {
        return "Cart{" +
                "result='" + result + '\'' +
                ", msg='" + msg + '\'' +
                ", data=" + data +
                '}';
    }
}
