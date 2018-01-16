package com.dq.huibao.bean.addr;

import java.util.List;

/**
 * 收货地址 实体类
 * Created by jingang on 2018/1/16.
 */

public class Addr {

    /**
     * status : 1
     * data : [{"id":"3","uid":"4","province":"河北省","city":"石家庄市","district":null,"regionid":"39","addr":"哇哇","contact":"jingang","mobile":"17868069350","isdefault":"1","region":{"id":"39","region_name":"石家庄市","level":"2","parent_id":"38","province_id":"38","province_name":"河北省","city_id":"39","city_name":"石家庄市","district_id":null,"district_name":null}}]
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
         * id : 3
         * uid : 4
         * province : 河北省
         * city : 石家庄市
         * district : null
         * regionid : 39
         * addr : 哇哇
         * contact : jingang
         * mobile : 17868069350
         * isdefault : 1
         * region : {"id":"39","region_name":"石家庄市","level":"2","parent_id":"38","province_id":"38","province_name":"河北省","city_id":"39","city_name":"石家庄市","district_id":null,"district_name":null}
         */

        private String id;
        private String uid;
        private String province;
        private String city;
        private Object district;
        private String regionid;
        private String addr;
        private String contact;
        private String mobile;
        private String isdefault;
        private RegionBean region;

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

        public String getProvince() {
            return province;
        }

        public void setProvince(String province) {
            this.province = province;
        }

        public String getCity() {
            return city;
        }

        public void setCity(String city) {
            this.city = city;
        }

        public Object getDistrict() {
            return district;
        }

        public void setDistrict(Object district) {
            this.district = district;
        }

        public String getRegionid() {
            return regionid;
        }

        public void setRegionid(String regionid) {
            this.regionid = regionid;
        }

        public String getAddr() {
            return addr;
        }

        public void setAddr(String addr) {
            this.addr = addr;
        }

        public String getContact() {
            return contact;
        }

        public void setContact(String contact) {
            this.contact = contact;
        }

        public String getMobile() {
            return mobile;
        }

        public void setMobile(String mobile) {
            this.mobile = mobile;
        }

        public String getIsdefault() {
            return isdefault;
        }

        public void setIsdefault(String isdefault) {
            this.isdefault = isdefault;
        }

        public RegionBean getRegion() {
            return region;
        }

        public void setRegion(RegionBean region) {
            this.region = region;
        }

        public static class RegionBean {
            /**
             * id : 39
             * region_name : 石家庄市
             * level : 2
             * parent_id : 38
             * province_id : 38
             * province_name : 河北省
             * city_id : 39
             * city_name : 石家庄市
             * district_id : null
             * district_name : null
             */

            private String id;
            private String region_name;
            private String level;
            private String parent_id;
            private String province_id;
            private String province_name;
            private String city_id;
            private String city_name;
            private Object district_id;
            private Object district_name;

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getRegion_name() {
                return region_name;
            }

            public void setRegion_name(String region_name) {
                this.region_name = region_name;
            }

            public String getLevel() {
                return level;
            }

            public void setLevel(String level) {
                this.level = level;
            }

            public String getParent_id() {
                return parent_id;
            }

            public void setParent_id(String parent_id) {
                this.parent_id = parent_id;
            }

            public String getProvince_id() {
                return province_id;
            }

            public void setProvince_id(String province_id) {
                this.province_id = province_id;
            }

            public String getProvince_name() {
                return province_name;
            }

            public void setProvince_name(String province_name) {
                this.province_name = province_name;
            }

            public String getCity_id() {
                return city_id;
            }

            public void setCity_id(String city_id) {
                this.city_id = city_id;
            }

            public String getCity_name() {
                return city_name;
            }

            public void setCity_name(String city_name) {
                this.city_name = city_name;
            }

            public Object getDistrict_id() {
                return district_id;
            }

            public void setDistrict_id(Object district_id) {
                this.district_id = district_id;
            }

            public Object getDistrict_name() {
                return district_name;
            }

            public void setDistrict_name(Object district_name) {
                this.district_name = district_name;
            }

            @Override
            public String toString() {
                return "RegionBean{" +
                        "id='" + id + '\'' +
                        ", region_name='" + region_name + '\'' +
                        ", level='" + level + '\'' +
                        ", parent_id='" + parent_id + '\'' +
                        ", province_id='" + province_id + '\'' +
                        ", province_name='" + province_name + '\'' +
                        ", city_id='" + city_id + '\'' +
                        ", city_name='" + city_name + '\'' +
                        ", district_id=" + district_id +
                        ", district_name=" + district_name +
                        '}';
            }
        }

        @Override
        public String toString() {
            return "DataBean{" +
                    "id='" + id + '\'' +
                    ", uid='" + uid + '\'' +
                    ", province='" + province + '\'' +
                    ", city='" + city + '\'' +
                    ", district=" + district +
                    ", regionid='" + regionid + '\'' +
                    ", addr='" + addr + '\'' +
                    ", contact='" + contact + '\'' +
                    ", mobile='" + mobile + '\'' +
                    ", isdefault='" + isdefault + '\'' +
                    ", region=" + region +
                    '}';
        }
    }

    @Override
    public String toString() {
        return "Addr{" +
                "status=" + status +
                ", data=" + data +
                '}';
    }
}

