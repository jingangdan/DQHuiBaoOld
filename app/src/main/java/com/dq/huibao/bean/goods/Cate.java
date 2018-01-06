package com.dq.huibao.bean.goods;

import java.util.List;

/**
 * Created by jingang on 2018/1/6.
 */

public class Cate {

    /**
     * status : 1
     * data : [{"id":"107","catename":"服装","displayorder":"4","thumb":"/attachment/admin//20171221/1513842451_1233355148.jpg","description":"","advimg":"/attachment/admin//20171221/1513842434_1047598200.jpg","advimg_pc":"","ishome":"0","enabled":"0","parent_id":"0","cate_lv":"1","oldcate":null},{"id":"161","catename":"生活必备","displayorder":"2","thumb":"/attachment/admin//20171221/1513842451_1233355148.jpg","description":"","advimg":"/attachment/admin//20171221/1513842297_877013288.jpg","advimg_pc":"","ishome":"0","enabled":"0","parent_id":"0","cate_lv":"1","oldcate":null},{"id":"162","catename":"数码产品","displayorder":"3","thumb":"/attachment/admin//20171221/1513842451_1233355148.jpg","description":"","advimg":"/attachment/admin//20171221/1513842307_1876416762.jpg","advimg_pc":"","ishome":"0","enabled":"0","parent_id":"0","cate_lv":"1","oldcate":null},{"id":"160","catename":"美食","displayorder":"1","thumb":"/attachment/admin//20171221/1513842307_1876416762.jpg","description":"","advimg":"/attachment/admin//20171221/1513842268_934854534.jpg","advimg_pc":"","ishome":"0","enabled":"0","parent_id":"0","cate_lv":"1","oldcate":null}]
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
         * id : 107
         * catename : 服装
         * displayorder : 4
         * thumb : /attachment/admin//20171221/1513842451_1233355148.jpg
         * description :
         * advimg : /attachment/admin//20171221/1513842434_1047598200.jpg
         * advimg_pc :
         * ishome : 0
         * enabled : 0
         * parent_id : 0
         * cate_lv : 1
         * oldcate : null
         */

        private String id;
        private String catename;
        private String displayorder;
        private String thumb;
        private String description;
        private String advimg;
        private String advimg_pc;
        private String ishome;
        private String enabled;
        private String parent_id;
        private String cate_lv;
        private Object oldcate;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getCatename() {
            return catename;
        }

        public void setCatename(String catename) {
            this.catename = catename;
        }

        public String getDisplayorder() {
            return displayorder;
        }

        public void setDisplayorder(String displayorder) {
            this.displayorder = displayorder;
        }

        public String getThumb() {
            return thumb;
        }

        public void setThumb(String thumb) {
            this.thumb = thumb;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public String getAdvimg() {
            return advimg;
        }

        public void setAdvimg(String advimg) {
            this.advimg = advimg;
        }

        public String getAdvimg_pc() {
            return advimg_pc;
        }

        public void setAdvimg_pc(String advimg_pc) {
            this.advimg_pc = advimg_pc;
        }

        public String getIshome() {
            return ishome;
        }

        public void setIshome(String ishome) {
            this.ishome = ishome;
        }

        public String getEnabled() {
            return enabled;
        }

        public void setEnabled(String enabled) {
            this.enabled = enabled;
        }

        public String getParent_id() {
            return parent_id;
        }

        public void setParent_id(String parent_id) {
            this.parent_id = parent_id;
        }

        public String getCate_lv() {
            return cate_lv;
        }

        public void setCate_lv(String cate_lv) {
            this.cate_lv = cate_lv;
        }

        public Object getOldcate() {
            return oldcate;
        }

        public void setOldcate(Object oldcate) {
            this.oldcate = oldcate;
        }

        @Override
        public String toString() {
            return "DataBean{" +
                    "id='" + id + '\'' +
                    ", catename='" + catename + '\'' +
                    ", displayorder='" + displayorder + '\'' +
                    ", thumb='" + thumb + '\'' +
                    ", description='" + description + '\'' +
                    ", advimg='" + advimg + '\'' +
                    ", advimg_pc='" + advimg_pc + '\'' +
                    ", ishome='" + ishome + '\'' +
                    ", enabled='" + enabled + '\'' +
                    ", parent_id='" + parent_id + '\'' +
                    ", cate_lv='" + cate_lv + '\'' +
                    ", oldcate=" + oldcate +
                    '}';
        }
    }

    @Override
    public String toString() {
        return "Cate{" +
                "status=" + status +
                ", data=" + data +
                '}';
    }
}
