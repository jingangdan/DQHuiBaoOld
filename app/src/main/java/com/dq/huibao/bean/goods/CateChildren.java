package com.dq.huibao.bean.goods;

import java.util.List;

/**
 * 子分类（二、三级）
 * Created by jingang on 2018/1/6.
 */

public class CateChildren {

    /**
     * status : 1
     * data : [{"id":"111","catename":"男装","displayorder":"5","thumb":"","description":null,"advimg":"images/1604/2017/05/Fsk7OOSE5ek5EONspEKNOkeDtkF7dE.png","advimg_pc":"images/1604/2017/10/Eykytece77fKcO8t7ctEEt1ENECUEE.jpg","ishome":"0","enabled":"0","parent_id":"107","cate_lv":"2","oldcate":"4467","children":[{"id":"112","catename":"男袜","displayorder":"3","thumb":"images/1604/2017/10/MhNV1lg4zd4hljn4MlMfTnFOjoljf4.png","description":null,"advimg":"","advimg_pc":"","ishome":"0","enabled":"0","parent_id":"111","cate_lv":"3","oldcate":"4473"},{"id":"140","catename":"睡衣","displayorder":"2","thumb":"images/1604/2017/09/z0gLROmrSxzg9yODMYAMXloWGMYAVF.png","description":null,"advimg":"","advimg_pc":"","ishome":"0","enabled":"0","parent_id":"111","cate_lv":"3","oldcate":"4593"},{"id":"157","catename":"衬衫","displayorder":"1","thumb":"images/1604/2017/11/jZo11YYfj0uX511Do9g9FxmRw9WGJy.png","description":null,"advimg":"","advimg_pc":"","ishome":"0","enabled":"0","parent_id":"111","cate_lv":"3","oldcate":"4749"}]},{"id":"117","catename":"女装","displayorder":"4","thumb":"images/1604/2017/05/L1K621CFg06oniB2b01of0e1c77BBi.png","description":null,"advimg":"images/1604/2017/05/Y1fD6N2e6eKR649yeKmR90tE4tRq69.jpg","advimg_pc":"images/1604/2017/10/s4msi5GGGssIK2IS1hz81ZM0m00K0p.jpg","ishome":"0","enabled":"0","parent_id":"107","cate_lv":"2","oldcate":"4454","children":[{"id":"118","catename":"女袜","displayorder":"2","thumb":"images/1604/2017/10/xcZ1v7pP5CMOZq3nsVp8z7nq5lZF01.png","description":null,"advimg":"","advimg_pc":"","ishome":"0","enabled":"0","parent_id":"117","cate_lv":"3","oldcate":"4483"},{"id":"139","catename":"睡衣","displayorder":"1","thumb":"images/1604/2017/09/PiH59492H6X8wQ9vyx525YZQVqvwQq.png","description":null,"advimg":"","advimg_pc":"","ishome":"0","enabled":"0","parent_id":"117","cate_lv":"3","oldcate":"4592"}]},{"id":"125","catename":"女鞋","displayorder":"3","thumb":"","description":null,"advimg":"","advimg_pc":"","ishome":"0","enabled":"0","parent_id":"107","cate_lv":"2","oldcate":"4553","children":[{"id":"126","catename":"拖鞋/人字拖","displayorder":"2","thumb":"images/1604/2017/09/C6Ie6T56R66T50iMTt5E5EVMvyUrJe.png","description":null,"advimg":"","advimg_pc":"","ishome":"0","enabled":"0","parent_id":"125","cate_lv":"3","oldcate":"4558"},{"id":"127","catename":"帆布鞋","displayorder":"1","thumb":"images/1604/2017/09/p8w034i8h7e9340U8u84ie4ui9EUZ8.png","description":null,"advimg":"","advimg_pc":"","ishome":"0","enabled":"0","parent_id":"125","cate_lv":"3","oldcate":"4559"}]},{"id":"135","catename":"女包","displayorder":"2","thumb":"","description":null,"advimg":"","advimg_pc":"","ishome":"0","enabled":"0","parent_id":"107","cate_lv":"2","oldcate":"4570","children":[{"id":"136","catename":"双肩包","displayorder":"1","thumb":"images/1604/2017/09/aZvCcO2EmzaSnnAMA3is882MZn5EVA.png","description":null,"advimg":"","advimg_pc":"","ishome":"0","enabled":"0","parent_id":"135","cate_lv":"3","oldcate":"4571"}]},{"id":"147","catename":"男包","displayorder":"1","thumb":"","description":null,"advimg":"","advimg_pc":"","ishome":"0","enabled":"0","parent_id":"107","cate_lv":"2","oldcate":"4625","children":[{"id":"148","catename":"钱包","displayorder":"2","thumb":"images/1604/2017/09/AZJKp6k37ykJCR7LK7ty8B4QyHB483.png","description":null,"advimg":"","advimg_pc":"","ishome":"0","enabled":"0","parent_id":"147","cate_lv":"3","oldcate":"4626"},{"id":"149","catename":"双肩包","displayorder":"1","thumb":"images/1604/2017/09/CUkZ73JuaEJA3OpzAgs7PRNL7G85Ks.png","description":null,"advimg":"","advimg_pc":"","ishome":"0","enabled":"0","parent_id":"147","cate_lv":"3","oldcate":"4627"}]}]
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
         * id : 111
         * catename : 男装
         * displayorder : 5
         * thumb :
         * description : null
         * advimg : images/1604/2017/05/Fsk7OOSE5ek5EONspEKNOkeDtkF7dE.png
         * advimg_pc : images/1604/2017/10/Eykytece77fKcO8t7ctEEt1ENECUEE.jpg
         * ishome : 0
         * enabled : 0
         * parent_id : 107
         * cate_lv : 2
         * oldcate : 4467
         * children : [{"id":"112","catename":"男袜","displayorder":"3","thumb":"images/1604/2017/10/MhNV1lg4zd4hljn4MlMfTnFOjoljf4.png","description":null,"advimg":"","advimg_pc":"","ishome":"0","enabled":"0","parent_id":"111","cate_lv":"3","oldcate":"4473"},{"id":"140","catename":"睡衣","displayorder":"2","thumb":"images/1604/2017/09/z0gLROmrSxzg9yODMYAMXloWGMYAVF.png","description":null,"advimg":"","advimg_pc":"","ishome":"0","enabled":"0","parent_id":"111","cate_lv":"3","oldcate":"4593"},{"id":"157","catename":"衬衫","displayorder":"1","thumb":"images/1604/2017/11/jZo11YYfj0uX511Do9g9FxmRw9WGJy.png","description":null,"advimg":"","advimg_pc":"","ishome":"0","enabled":"0","parent_id":"111","cate_lv":"3","oldcate":"4749"}]
         */

        private String id;
        private String catename;
        private String displayorder;
        private String thumb;
        private Object description;
        private String advimg;
        private String advimg_pc;
        private String ishome;
        private String enabled;
        private String parent_id;
        private String cate_lv;
        private String oldcate;
        private List<ChildrenBean> children;

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

        public Object getDescription() {
            return description;
        }

        public void setDescription(Object description) {
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

        public String getOldcate() {
            return oldcate;
        }

        public void setOldcate(String oldcate) {
            this.oldcate = oldcate;
        }

        public List<ChildrenBean> getChildren() {
            return children;
        }

        public void setChildren(List<ChildrenBean> children) {
            this.children = children;
        }

        public static class ChildrenBean {
            /**
             * id : 112
             * catename : 男袜
             * displayorder : 3
             * thumb : images/1604/2017/10/MhNV1lg4zd4hljn4MlMfTnFOjoljf4.png
             * description : null
             * advimg :
             * advimg_pc :
             * ishome : 0
             * enabled : 0
             * parent_id : 111
             * cate_lv : 3
             * oldcate : 4473
             */

            private String id;
            private String catename;
            private String displayorder;
            private String thumb;
            private Object description;
            private String advimg;
            private String advimg_pc;
            private String ishome;
            private String enabled;
            private String parent_id;
            private String cate_lv;
            private String oldcate;

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

            public Object getDescription() {
                return description;
            }

            public void setDescription(Object description) {
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

            public String getOldcate() {
                return oldcate;
            }

            public void setOldcate(String oldcate) {
                this.oldcate = oldcate;
            }

            @Override
            public String toString() {
                return "ChildrenBean{" +
                        "id='" + id + '\'' +
                        ", catename='" + catename + '\'' +
                        ", displayorder='" + displayorder + '\'' +
                        ", thumb='" + thumb + '\'' +
                        ", description=" + description +
                        ", advimg='" + advimg + '\'' +
                        ", advimg_pc='" + advimg_pc + '\'' +
                        ", ishome='" + ishome + '\'' +
                        ", enabled='" + enabled + '\'' +
                        ", parent_id='" + parent_id + '\'' +
                        ", cate_lv='" + cate_lv + '\'' +
                        ", oldcate='" + oldcate + '\'' +
                        '}';
            }
        }

        @Override
        public String toString() {
            return "DataBean{" +
                    "id='" + id + '\'' +
                    ", catename='" + catename + '\'' +
                    ", displayorder='" + displayorder + '\'' +
                    ", thumb='" + thumb + '\'' +
                    ", description=" + description +
                    ", advimg='" + advimg + '\'' +
                    ", advimg_pc='" + advimg_pc + '\'' +
                    ", ishome='" + ishome + '\'' +
                    ", enabled='" + enabled + '\'' +
                    ", parent_id='" + parent_id + '\'' +
                    ", cate_lv='" + cate_lv + '\'' +
                    ", oldcate='" + oldcate + '\'' +
                    ", children=" + children +
                    '}';
        }
    }

    @Override
    public String toString() {
        return "CateChildren{" +
                "status=" + status +
                ", data=" + data +
                '}';
    }
}
