package com.dq.huibao.bean;

/**
 * 版本更新
 * Created by jingang on 2018/2/22.
 */

public class CheckVersion {

    /**
     * status : 1
     * data : {"appname":"hb-1.0.2.apk","serverVersion":"1.0.2","lastForce":"1","upgradeinfo":"V1.0.2版本更新\r\n1.更新*******\r\n2.修改*******\r\n3.增加*******","updateurl":"http://new.dequanhuibao.com/Public/appversion/hb-1.0.2.apk"}
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
         * appname : hb-1.0.2.apk
         * serverVersion : 1.0.2
         * lastForce : 1
         * upgradeinfo : V1.0.2版本更新
         * 1.更新*******
         * 2.修改*******
         * 3.增加*******
         * updateurl : http://new.dequanhuibao.com/Public/appversion/hb-1.0.2.apk
         */

        private String appname;
        private String serverVersion;
        private String lastForce;
        private String upgradeinfo;
        private String updateurl;

        public String getAppname() {
            return appname;
        }

        public void setAppname(String appname) {
            this.appname = appname;
        }

        public String getServerVersion() {
            return serverVersion;
        }

        public void setServerVersion(String serverVersion) {
            this.serverVersion = serverVersion;
        }

        public String getLastForce() {
            return lastForce;
        }

        public void setLastForce(String lastForce) {
            this.lastForce = lastForce;
        }

        public String getUpgradeinfo() {
            return upgradeinfo;
        }

        public void setUpgradeinfo(String upgradeinfo) {
            this.upgradeinfo = upgradeinfo;
        }

        public String getUpdateurl() {
            return updateurl;
        }

        public void setUpdateurl(String updateurl) {
            this.updateurl = updateurl;
        }

        @Override
        public String toString() {
            return "DataBean{" +
                    "appname='" + appname + '\'' +
                    ", serverVersion='" + serverVersion + '\'' +
                    ", lastForce='" + lastForce + '\'' +
                    ", upgradeinfo='" + upgradeinfo + '\'' +
                    ", updateurl='" + updateurl + '\'' +
                    '}';
        }
    }

    @Override
    public String toString() {
        return "CheckVersion{" +
                "status=" + status +
                ", data=" + data +
                '}';
    }
}
