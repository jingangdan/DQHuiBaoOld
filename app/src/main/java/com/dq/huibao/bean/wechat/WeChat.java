package com.dq.huibao.bean.wechat;

/**
 * Description：
 * Created by jingang on 2017/11/17.
 */

public class WeChat {


    /**
     * icon : http://wx.qlogo.cn/mmopen/vi_32/wH6mut1DhL4mOxU8SZySDJgTy00LFiarDB61P1g3rHv3E309OGhgB0uyRohGqXkibSBOB4NDpI9rSUsoiaA5dbmEQ/0
     * token : 4_l1adAAUuNbmvB4dzFwZAr4ovyJX8JncsGqAsOzN3jntSlTxcJ06K7b1cV8pzoaFlH-NlFuASROpKDwgoIbCTK9Z0QPmQIRPhLnfIiG32Ya0
     * nickname : 0A___1%代还信用卡
     * expiresTime : 1511160408452
     * expiresIn : 7200
     * unionid : oQcuF1YMpiUEApK5EpHGt1Wj35zk
     * province : Shandong
     * gender : 0
     * openid : oDDb6w_ZJ96DSniFFlS0pcDt4xlM
     * refresh_token : 4_HjkaFB4PXjsfTkZWxxSlHlbBlbfwFaEy67kCMx3Wgu59lZITlGchNjHThDMe5w1M-xDNod6rhGI3Z_FDkFivBXPaGPC9BcDFrIEITkR_NSU
     * weibo : oDDb6w_ZJ96DSniFFlS0pcDt4xlM
     * city : Linyi
     * country : CN
     */

    private String icon;
    private String token;
    private String nickname;
    private long expiresTime;
    private int expiresIn;
    private String unionid;
    private String province;
    private String gender;
    private String openid;
    private String refresh_token;
    private String weibo;
    private String city;
    private String country;

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public long getExpiresTime() {
        return expiresTime;
    }

    public void setExpiresTime(long expiresTime) {
        this.expiresTime = expiresTime;
    }

    public int getExpiresIn() {
        return expiresIn;
    }

    public void setExpiresIn(int expiresIn) {
        this.expiresIn = expiresIn;
    }

    public String getUnionid() {
        return unionid;
    }

    public void setUnionid(String unionid) {
        this.unionid = unionid;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid;
    }

    public String getRefresh_token() {
        return refresh_token;
    }

    public void setRefresh_token(String refresh_token) {
        this.refresh_token = refresh_token;
    }

    public String getWeibo() {
        return weibo;
    }

    public void setWeibo(String weibo) {
        this.weibo = weibo;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    @Override
    public String toString() {
        return "WeChat{" +
                "icon='" + icon + '\'' +
                ", token='" + token + '\'' +
                ", nickname='" + nickname + '\'' +
                ", expiresTime=" + expiresTime +
                ", expiresIn=" + expiresIn +
                ", unionid='" + unionid + '\'' +
                ", province='" + province + '\'' +
                ", gender='" + gender + '\'' +
                ", openid='" + openid + '\'' +
                ", refresh_token='" + refresh_token + '\'' +
                ", weibo='" + weibo + '\'' +
                ", city='" + city + '\'' +
                ", country='" + country + '\'' +
                '}';
    }
}
