package com.dq.huibao.bean.pay;

import java.util.List;

/**
 * 选择支付方式
 * Created by jingang on 2018/1/25.
 */

public class PayType {

    /**
     * status : 1
     * data : {"order":{"id":"31","pay_money":"140.40","ordersn":"SN201801240848234371"},"paytype":["balance","wxpay"]}
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
         * order : {"id":"31","pay_money":"140.40","ordersn":"SN201801240848234371"}
         * paytype : ["balance","wxpay"]
         */

        private OrderBean order;
        private List<String> paytype;

        public OrderBean getOrder() {
            return order;
        }

        public void setOrder(OrderBean order) {
            this.order = order;
        }

        public List<String> getPaytype() {
            return paytype;
        }

        public void setPaytype(List<String> paytype) {
            this.paytype = paytype;
        }

        public static class OrderBean {
            /**
             * id : 31
             * pay_money : 140.40
             * ordersn : SN201801240848234371
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

            @Override
            public String toString() {
                return "OrderBean{" +
                        "id='" + id + '\'' +
                        ", pay_money='" + pay_money + '\'' +
                        ", ordersn='" + ordersn + '\'' +
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
