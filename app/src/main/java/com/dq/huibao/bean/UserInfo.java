package com.dq.huibao.bean;

import java.util.List;

/**
 * Description：
 * Created by jingang on 2017/11/20.
 */

public class UserInfo {

    /**
     * result : 1
     * msg : 成功
     * data : {"member":{"id":"7394938","uniacid":"1604","uid":"56757","groupid":"0","level":"0","agentid":"0","openid":"oV_rgwyxKELajwPnOkx57Xpl2cYw","realname":"","mobile":"","pwd":"","weixin":"","content":"","createtime":"1502000765","agenttime":"1502000765","status":"1","isagent":"1","clickcount":"0","agentlevel":"0","noticeset":"","nickname":"0A___1%代还信用卡","credit1":"0","credit2":"0","birthyear":"","birthmonth":"","birthday":"","gender":"1","avatar":"http://wx.qlogo.cn/mmopen/JiavaWZxX4YurSvbExyeibfxqLebvBFhEUng0162PDZG9iaO5yY2db9u8oTFNgmXLCzibibe3dExHhwWGV30OxVz6YNaPs7u7nSCwibJtqGtqDUdQ/0","province":"山东","city":"临沂","area":"","childtime":"0","inviter":"0","agentnotupgrade":"0","agentselectgoods":"0","agentblack":"0","fixagentid":"0","regtype":"1","isbindmobile":"0","isjumpbind":"0","diymemberid":"0","isblack":"0","diymemberdataid":"0","diycommissionid":"0","diycommissiondataid":"0","diymemberfields":"","diymemberdata":"","diycommissionfields":"","diycommissiondata":"","referralsn":"","bindapp":"0","credit20":"0","alipay":"","alipayname":"","check_imgs":"","bonuslevel":"0","bonus_area":"0","bonus_status":"0","bonus_province":"","bonus_city":"","bonus_district":"","bonus_area_commission":"0.00","street":"0","bonus_street":"","ischannel":"0","channel_level":"0","channeltime":"0","isagency":"0","bank":"","bank_num":"","virtual_currency":"0.00","virtual_temporary":"0.00","virtual_temporary_total":"0.00","last_money":"0.00","updatetime":"","membermobile":"","upgradeleveltime":"0"},"referrer":[],"order":{"status0":"0","status1":"0","status2":"0","status4":"0"},"orderhotel":{"status0":"0","status1":"0","status6":"0","status4":"0"},"memberhotel":true,"level":"","counts":{"cartcount":"0","favcount":"0","couponcount":"0"}}
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
         * member : {"id":"7394938","uniacid":"1604","uid":"56757","groupid":"0","level":"0","agentid":"0","openid":"oV_rgwyxKELajwPnOkx57Xpl2cYw","realname":"","mobile":"","pwd":"","weixin":"","content":"","createtime":"1502000765","agenttime":"1502000765","status":"1","isagent":"1","clickcount":"0","agentlevel":"0","noticeset":"","nickname":"0A___1%代还信用卡","credit1":"0","credit2":"0","birthyear":"","birthmonth":"","birthday":"","gender":"1","avatar":"http://wx.qlogo.cn/mmopen/JiavaWZxX4YurSvbExyeibfxqLebvBFhEUng0162PDZG9iaO5yY2db9u8oTFNgmXLCzibibe3dExHhwWGV30OxVz6YNaPs7u7nSCwibJtqGtqDUdQ/0","province":"山东","city":"临沂","area":"","childtime":"0","inviter":"0","agentnotupgrade":"0","agentselectgoods":"0","agentblack":"0","fixagentid":"0","regtype":"1","isbindmobile":"0","isjumpbind":"0","diymemberid":"0","isblack":"0","diymemberdataid":"0","diycommissionid":"0","diycommissiondataid":"0","diymemberfields":"","diymemberdata":"","diycommissionfields":"","diycommissiondata":"","referralsn":"","bindapp":"0","credit20":"0","alipay":"","alipayname":"","check_imgs":"","bonuslevel":"0","bonus_area":"0","bonus_status":"0","bonus_province":"","bonus_city":"","bonus_district":"","bonus_area_commission":"0.00","street":"0","bonus_street":"","ischannel":"0","channel_level":"0","channeltime":"0","isagency":"0","bank":"","bank_num":"","virtual_currency":"0.00","virtual_temporary":"0.00","virtual_temporary_total":"0.00","last_money":"0.00","updatetime":"","membermobile":"","upgradeleveltime":"0"}
         * referrer : []
         * order : {"status0":"0","status1":"0","status2":"0","status4":"0"}
         * orderhotel : {"status0":"0","status1":"0","status6":"0","status4":"0"}
         * memberhotel : true
         * level :
         * counts : {"cartcount":"0","favcount":"0","couponcount":"0"}
         */

        private MemberBean member;
        private OrderBean order;
        private OrderhotelBean orderhotel;
        private boolean memberhotel;
        private String level;
        private CountsBean counts;
        private List<?> referrer;

        public MemberBean getMember() {
            return member;
        }

        public void setMember(MemberBean member) {
            this.member = member;
        }

        public OrderBean getOrder() {
            return order;
        }

        public void setOrder(OrderBean order) {
            this.order = order;
        }

        public OrderhotelBean getOrderhotel() {
            return orderhotel;
        }

        public void setOrderhotel(OrderhotelBean orderhotel) {
            this.orderhotel = orderhotel;
        }

        public boolean isMemberhotel() {
            return memberhotel;
        }

        public void setMemberhotel(boolean memberhotel) {
            this.memberhotel = memberhotel;
        }

        public String getLevel() {
            return level;
        }

        public void setLevel(String level) {
            this.level = level;
        }

        public CountsBean getCounts() {
            return counts;
        }

        public void setCounts(CountsBean counts) {
            this.counts = counts;
        }

        public List<?> getReferrer() {
            return referrer;
        }

        public void setReferrer(List<?> referrer) {
            this.referrer = referrer;
        }

        public static class MemberBean {
            /**
             * id : 7394938
             * uniacid : 1604
             * uid : 56757
             * groupid : 0
             * level : 0
             * agentid : 0
             * openid : oV_rgwyxKELajwPnOkx57Xpl2cYw
             * realname :
             * mobile :
             * pwd :
             * weixin :
             * content :
             * createtime : 1502000765
             * agenttime : 1502000765
             * status : 1
             * isagent : 1
             * clickcount : 0
             * agentlevel : 0
             * noticeset :
             * nickname : 0A___1%代还信用卡
             * credit1 : 0
             * credit2 : 0
             * birthyear :
             * birthmonth :
             * birthday :
             * gender : 1
             * avatar : http://wx.qlogo.cn/mmopen/JiavaWZxX4YurSvbExyeibfxqLebvBFhEUng0162PDZG9iaO5yY2db9u8oTFNgmXLCzibibe3dExHhwWGV30OxVz6YNaPs7u7nSCwibJtqGtqDUdQ/0
             * province : 山东
             * city : 临沂
             * area :
             * childtime : 0
             * inviter : 0
             * agentnotupgrade : 0
             * agentselectgoods : 0
             * agentblack : 0
             * fixagentid : 0
             * regtype : 1
             * isbindmobile : 0
             * isjumpbind : 0
             * diymemberid : 0
             * isblack : 0
             * diymemberdataid : 0
             * diycommissionid : 0
             * diycommissiondataid : 0
             * diymemberfields :
             * diymemberdata :
             * diycommissionfields :
             * diycommissiondata :
             * referralsn :
             * bindapp : 0
             * credit20 : 0
             * alipay :
             * alipayname :
             * check_imgs :
             * bonuslevel : 0
             * bonus_area : 0
             * bonus_status : 0
             * bonus_province :
             * bonus_city :
             * bonus_district :
             * bonus_area_commission : 0.00
             * street : 0
             * bonus_street :
             * ischannel : 0
             * channel_level : 0
             * channeltime : 0
             * isagency : 0
             * bank :
             * bank_num :
             * virtual_currency : 0.00
             * virtual_temporary : 0.00
             * virtual_temporary_total : 0.00
             * last_money : 0.00
             * updatetime :
             * membermobile :
             * upgradeleveltime : 0
             */

            private String id;
            private String uniacid;
            private String uid;
            private String groupid;
            private String level;
            private String agentid;
            private String openid;
            private String realname;
            private String mobile;
            private String pwd;
            private String weixin;
            private String content;
            private String createtime;
            private String agenttime;
            private String status;
            private String isagent;
            private String clickcount;
            private String agentlevel;
            private String noticeset;
            private String nickname;
            private String credit1;
            private String credit2;
            private String birthyear;
            private String birthmonth;
            private String birthday;
            private String gender;
            private String avatar;
            private String province;
            private String city;
            private String area;
            private String childtime;
            private String inviter;
            private String agentnotupgrade;
            private String agentselectgoods;
            private String agentblack;
            private String fixagentid;
            private String regtype;
            private String isbindmobile;
            private String isjumpbind;
            private String diymemberid;
            private String isblack;
            private String diymemberdataid;
            private String diycommissionid;
            private String diycommissiondataid;
            private String diymemberfields;
            private String diymemberdata;
            private String diycommissionfields;
            private String diycommissiondata;
            private String referralsn;
            private String bindapp;
            private String credit20;
            private String alipay;
            private String alipayname;
            private String check_imgs;
            private String bonuslevel;
            private String bonus_area;
            private String bonus_status;
            private String bonus_province;
            private String bonus_city;
            private String bonus_district;
            private String bonus_area_commission;
            private String street;
            private String bonus_street;
            private String ischannel;
            private String channel_level;
            private String channeltime;
            private String isagency;
            private String bank;
            private String bank_num;
            private String virtual_currency;
            private String virtual_temporary;
            private String virtual_temporary_total;
            private String last_money;
            private String updatetime;
            private String membermobile;
            private String upgradeleveltime;

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getUniacid() {
                return uniacid;
            }

            public void setUniacid(String uniacid) {
                this.uniacid = uniacid;
            }

            public String getUid() {
                return uid;
            }

            public void setUid(String uid) {
                this.uid = uid;
            }

            public String getGroupid() {
                return groupid;
            }

            public void setGroupid(String groupid) {
                this.groupid = groupid;
            }

            public String getLevel() {
                return level;
            }

            public void setLevel(String level) {
                this.level = level;
            }

            public String getAgentid() {
                return agentid;
            }

            public void setAgentid(String agentid) {
                this.agentid = agentid;
            }

            public String getOpenid() {
                return openid;
            }

            public void setOpenid(String openid) {
                this.openid = openid;
            }

            public String getRealname() {
                return realname;
            }

            public void setRealname(String realname) {
                this.realname = realname;
            }

            public String getMobile() {
                return mobile;
            }

            public void setMobile(String mobile) {
                this.mobile = mobile;
            }

            public String getPwd() {
                return pwd;
            }

            public void setPwd(String pwd) {
                this.pwd = pwd;
            }

            public String getWeixin() {
                return weixin;
            }

            public void setWeixin(String weixin) {
                this.weixin = weixin;
            }

            public String getContent() {
                return content;
            }

            public void setContent(String content) {
                this.content = content;
            }

            public String getCreatetime() {
                return createtime;
            }

            public void setCreatetime(String createtime) {
                this.createtime = createtime;
            }

            public String getAgenttime() {
                return agenttime;
            }

            public void setAgenttime(String agenttime) {
                this.agenttime = agenttime;
            }

            public String getStatus() {
                return status;
            }

            public void setStatus(String status) {
                this.status = status;
            }

            public String getIsagent() {
                return isagent;
            }

            public void setIsagent(String isagent) {
                this.isagent = isagent;
            }

            public String getClickcount() {
                return clickcount;
            }

            public void setClickcount(String clickcount) {
                this.clickcount = clickcount;
            }

            public String getAgentlevel() {
                return agentlevel;
            }

            public void setAgentlevel(String agentlevel) {
                this.agentlevel = agentlevel;
            }

            public String getNoticeset() {
                return noticeset;
            }

            public void setNoticeset(String noticeset) {
                this.noticeset = noticeset;
            }

            public String getNickname() {
                return nickname;
            }

            public void setNickname(String nickname) {
                this.nickname = nickname;
            }

            public String getCredit1() {
                return credit1;
            }

            public void setCredit1(String credit1) {
                this.credit1 = credit1;
            }

            public String getCredit2() {
                return credit2;
            }

            public void setCredit2(String credit2) {
                this.credit2 = credit2;
            }

            public String getBirthyear() {
                return birthyear;
            }

            public void setBirthyear(String birthyear) {
                this.birthyear = birthyear;
            }

            public String getBirthmonth() {
                return birthmonth;
            }

            public void setBirthmonth(String birthmonth) {
                this.birthmonth = birthmonth;
            }

            public String getBirthday() {
                return birthday;
            }

            public void setBirthday(String birthday) {
                this.birthday = birthday;
            }

            public String getGender() {
                return gender;
            }

            public void setGender(String gender) {
                this.gender = gender;
            }

            public String getAvatar() {
                return avatar;
            }

            public void setAvatar(String avatar) {
                this.avatar = avatar;
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

            public String getArea() {
                return area;
            }

            public void setArea(String area) {
                this.area = area;
            }

            public String getChildtime() {
                return childtime;
            }

            public void setChildtime(String childtime) {
                this.childtime = childtime;
            }

            public String getInviter() {
                return inviter;
            }

            public void setInviter(String inviter) {
                this.inviter = inviter;
            }

            public String getAgentnotupgrade() {
                return agentnotupgrade;
            }

            public void setAgentnotupgrade(String agentnotupgrade) {
                this.agentnotupgrade = agentnotupgrade;
            }

            public String getAgentselectgoods() {
                return agentselectgoods;
            }

            public void setAgentselectgoods(String agentselectgoods) {
                this.agentselectgoods = agentselectgoods;
            }

            public String getAgentblack() {
                return agentblack;
            }

            public void setAgentblack(String agentblack) {
                this.agentblack = agentblack;
            }

            public String getFixagentid() {
                return fixagentid;
            }

            public void setFixagentid(String fixagentid) {
                this.fixagentid = fixagentid;
            }

            public String getRegtype() {
                return regtype;
            }

            public void setRegtype(String regtype) {
                this.regtype = regtype;
            }

            public String getIsbindmobile() {
                return isbindmobile;
            }

            public void setIsbindmobile(String isbindmobile) {
                this.isbindmobile = isbindmobile;
            }

            public String getIsjumpbind() {
                return isjumpbind;
            }

            public void setIsjumpbind(String isjumpbind) {
                this.isjumpbind = isjumpbind;
            }

            public String getDiymemberid() {
                return diymemberid;
            }

            public void setDiymemberid(String diymemberid) {
                this.diymemberid = diymemberid;
            }

            public String getIsblack() {
                return isblack;
            }

            public void setIsblack(String isblack) {
                this.isblack = isblack;
            }

            public String getDiymemberdataid() {
                return diymemberdataid;
            }

            public void setDiymemberdataid(String diymemberdataid) {
                this.diymemberdataid = diymemberdataid;
            }

            public String getDiycommissionid() {
                return diycommissionid;
            }

            public void setDiycommissionid(String diycommissionid) {
                this.diycommissionid = diycommissionid;
            }

            public String getDiycommissiondataid() {
                return diycommissiondataid;
            }

            public void setDiycommissiondataid(String diycommissiondataid) {
                this.diycommissiondataid = diycommissiondataid;
            }

            public String getDiymemberfields() {
                return diymemberfields;
            }

            public void setDiymemberfields(String diymemberfields) {
                this.diymemberfields = diymemberfields;
            }

            public String getDiymemberdata() {
                return diymemberdata;
            }

            public void setDiymemberdata(String diymemberdata) {
                this.diymemberdata = diymemberdata;
            }

            public String getDiycommissionfields() {
                return diycommissionfields;
            }

            public void setDiycommissionfields(String diycommissionfields) {
                this.diycommissionfields = diycommissionfields;
            }

            public String getDiycommissiondata() {
                return diycommissiondata;
            }

            public void setDiycommissiondata(String diycommissiondata) {
                this.diycommissiondata = diycommissiondata;
            }

            public String getReferralsn() {
                return referralsn;
            }

            public void setReferralsn(String referralsn) {
                this.referralsn = referralsn;
            }

            public String getBindapp() {
                return bindapp;
            }

            public void setBindapp(String bindapp) {
                this.bindapp = bindapp;
            }

            public String getCredit20() {
                return credit20;
            }

            public void setCredit20(String credit20) {
                this.credit20 = credit20;
            }

            public String getAlipay() {
                return alipay;
            }

            public void setAlipay(String alipay) {
                this.alipay = alipay;
            }

            public String getAlipayname() {
                return alipayname;
            }

            public void setAlipayname(String alipayname) {
                this.alipayname = alipayname;
            }

            public String getCheck_imgs() {
                return check_imgs;
            }

            public void setCheck_imgs(String check_imgs) {
                this.check_imgs = check_imgs;
            }

            public String getBonuslevel() {
                return bonuslevel;
            }

            public void setBonuslevel(String bonuslevel) {
                this.bonuslevel = bonuslevel;
            }

            public String getBonus_area() {
                return bonus_area;
            }

            public void setBonus_area(String bonus_area) {
                this.bonus_area = bonus_area;
            }

            public String getBonus_status() {
                return bonus_status;
            }

            public void setBonus_status(String bonus_status) {
                this.bonus_status = bonus_status;
            }

            public String getBonus_province() {
                return bonus_province;
            }

            public void setBonus_province(String bonus_province) {
                this.bonus_province = bonus_province;
            }

            public String getBonus_city() {
                return bonus_city;
            }

            public void setBonus_city(String bonus_city) {
                this.bonus_city = bonus_city;
            }

            public String getBonus_district() {
                return bonus_district;
            }

            public void setBonus_district(String bonus_district) {
                this.bonus_district = bonus_district;
            }

            public String getBonus_area_commission() {
                return bonus_area_commission;
            }

            public void setBonus_area_commission(String bonus_area_commission) {
                this.bonus_area_commission = bonus_area_commission;
            }

            public String getStreet() {
                return street;
            }

            public void setStreet(String street) {
                this.street = street;
            }

            public String getBonus_street() {
                return bonus_street;
            }

            public void setBonus_street(String bonus_street) {
                this.bonus_street = bonus_street;
            }

            public String getIschannel() {
                return ischannel;
            }

            public void setIschannel(String ischannel) {
                this.ischannel = ischannel;
            }

            public String getChannel_level() {
                return channel_level;
            }

            public void setChannel_level(String channel_level) {
                this.channel_level = channel_level;
            }

            public String getChanneltime() {
                return channeltime;
            }

            public void setChanneltime(String channeltime) {
                this.channeltime = channeltime;
            }

            public String getIsagency() {
                return isagency;
            }

            public void setIsagency(String isagency) {
                this.isagency = isagency;
            }

            public String getBank() {
                return bank;
            }

            public void setBank(String bank) {
                this.bank = bank;
            }

            public String getBank_num() {
                return bank_num;
            }

            public void setBank_num(String bank_num) {
                this.bank_num = bank_num;
            }

            public String getVirtual_currency() {
                return virtual_currency;
            }

            public void setVirtual_currency(String virtual_currency) {
                this.virtual_currency = virtual_currency;
            }

            public String getVirtual_temporary() {
                return virtual_temporary;
            }

            public void setVirtual_temporary(String virtual_temporary) {
                this.virtual_temporary = virtual_temporary;
            }

            public String getVirtual_temporary_total() {
                return virtual_temporary_total;
            }

            public void setVirtual_temporary_total(String virtual_temporary_total) {
                this.virtual_temporary_total = virtual_temporary_total;
            }

            public String getLast_money() {
                return last_money;
            }

            public void setLast_money(String last_money) {
                this.last_money = last_money;
            }

            public String getUpdatetime() {
                return updatetime;
            }

            public void setUpdatetime(String updatetime) {
                this.updatetime = updatetime;
            }

            public String getMembermobile() {
                return membermobile;
            }

            public void setMembermobile(String membermobile) {
                this.membermobile = membermobile;
            }

            public String getUpgradeleveltime() {
                return upgradeleveltime;
            }

            public void setUpgradeleveltime(String upgradeleveltime) {
                this.upgradeleveltime = upgradeleveltime;
            }
        }

        public static class OrderBean {
            /**
             * status0 : 0
             * status1 : 0
             * status2 : 0
             * status4 : 0
             */

            private String status0;
            private String status1;
            private String status2;
            private String status4;

            public String getStatus0() {
                return status0;
            }

            public void setStatus0(String status0) {
                this.status0 = status0;
            }

            public String getStatus1() {
                return status1;
            }

            public void setStatus1(String status1) {
                this.status1 = status1;
            }

            public String getStatus2() {
                return status2;
            }

            public void setStatus2(String status2) {
                this.status2 = status2;
            }

            public String getStatus4() {
                return status4;
            }

            public void setStatus4(String status4) {
                this.status4 = status4;
            }
        }

        public static class OrderhotelBean {
            /**
             * status0 : 0
             * status1 : 0
             * status6 : 0
             * status4 : 0
             */

            private String status0;
            private String status1;
            private String status6;
            private String status4;

            public String getStatus0() {
                return status0;
            }

            public void setStatus0(String status0) {
                this.status0 = status0;
            }

            public String getStatus1() {
                return status1;
            }

            public void setStatus1(String status1) {
                this.status1 = status1;
            }

            public String getStatus6() {
                return status6;
            }

            public void setStatus6(String status6) {
                this.status6 = status6;
            }

            public String getStatus4() {
                return status4;
            }

            public void setStatus4(String status4) {
                this.status4 = status4;
            }
        }

        public static class CountsBean {
            /**
             * cartcount : 0
             * favcount : 0
             * couponcount : 0
             */

            private String cartcount;
            private String favcount;
            private String couponcount;

            public String getCartcount() {
                return cartcount;
            }

            public void setCartcount(String cartcount) {
                this.cartcount = cartcount;
            }

            public String getFavcount() {
                return favcount;
            }

            public void setFavcount(String favcount) {
                this.favcount = favcount;
            }

            public String getCouponcount() {
                return couponcount;
            }

            public void setCouponcount(String couponcount) {
                this.couponcount = couponcount;
            }

            @Override
            public String toString() {
                return "CountsBean{" +
                        "cartcount='" + cartcount + '\'' +
                        ", favcount='" + favcount + '\'' +
                        ", couponcount='" + couponcount + '\'' +
                        '}';
            }
        }

        @Override
        public String toString() {
            return "DataBean{" +
                    "member=" + member +
                    ", order=" + order +
                    ", orderhotel=" + orderhotel +
                    ", memberhotel=" + memberhotel +
                    ", level='" + level + '\'' +
                    ", counts=" + counts +
                    ", referrer=" + referrer +
                    '}';
        }
    }

    @Override
    public String toString() {
        return "UserInfo{" +
                "result='" + result + '\'' +
                ", msg='" + msg + '\'' +
                ", data=" + data +
                '}';
    }
}
