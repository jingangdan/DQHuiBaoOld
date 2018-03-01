package com.dq.huibao.bean.pay;

import java.util.List;

/**
 * 选择支付方式
 * Created by jingang on 2018/1/25.
 */

public class PayType {


    /**
     * status : 1
     * data : {"order":{"id":"74","pay_money":"5.00","ordersn":"RN201802281650446425"},"paytype":[{"code":"alipay","img":"/Public/api/alipay.png","name":"支付宝支付"}]}
     */

    private int status;
    private DataBean data;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * order : {"id":"74","pay_money":"5.00","ordersn":"RN201802281650446425"}
         * paytype : [{"code":"alipay","img":"/Public/api/alipay.png","name":"支付宝支付"}]
         */

        private OrderBean order;
        private List<PaytypeBean> paytype;

        public OrderBean getOrder() {
            return order;
        }

        public void setOrder(OrderBean order) {
            this.order = order;
        }

        public List<PaytypeBean> getPaytype() {
            return paytype;
        }

        public void setPaytype(List<PaytypeBean> paytype) {
            this.paytype = paytype;
        }

        public static class OrderBean {
            /**
             * id : 74
             * pay_money : 5.00
             * ordersn : RN201802281650446425
             */

            private String id;
            private String pay_money;
            private String ordersn;

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getPay_money() {
                return pay_money;
            }

            public void setPay_money(String pay_money) {
                this.pay_money = pay_money;
            }

            public String getOrdersn() {
                return ordersn;
            }

            public void setOrdersn(String ordersn) {
                this.ordersn = ordersn;
            }
        }

        public static class PaytypeBean {
            /**
             * code : alipay
             * img : /Public/api/alipay.png
             * name : 支付宝支付
             */

            private String code;
            private String img;
            private String name;

            public String getCode() {
                return code;
            }

            public void setCode(String code) {
                this.code = code;
            }

            public String getImg() {
                return img;
            }

            public void setImg(String img) {
                this.img = img;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            @Override
            public String toString() {
                return "PaytypeBean{" +
                        "code='" + code + '\'' +
                        ", img='" + img + '\'' +
                        ", name='" + name + '\'' +
                        '}';
            }
        }

        @Override
        public String toString() {
            return "DataBean{" +
                    "order=" + order +
                    ", paytype=" + paytype +
                    '}';
        }
    }

    @Override
    public String toString() {
        return "PayType{" +
                "status=" + status +
                ", data=" + data +
                '}';
    }
}
