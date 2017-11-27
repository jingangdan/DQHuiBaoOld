package com.dq.huibao.bean.goodsdetail;

import java.util.List;

/**
 * Description：
 * Created by jingang on 2017/11/13.
 */

public class Saleset {

    private List<String> enoughs2;
    private List<String> enoughs3;

    public void setEnoughs2(List<String> enoughs2) {
        this.enoughs2 = enoughs2;
    }

    public List<String> getEnoughs2() {
        return enoughs2;
    }

    public void setEnoughs3(List<String> enoughs3) {
        this.enoughs3 = enoughs3;
    }

    public List<String> getEnoughs3() {
        return enoughs3;
    }

    @Override
    public String toString() {
        return "Saleset{" +
                "enoughs2=" + enoughs2 +
                ", enoughs3=" + enoughs3 +
                '}';
    }
}
