package com.dq.huibao.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by asus on 2018/2/6.
 */

public class Find implements Serializable {
    private static final long serialVersionUID = 2189052605715370758L;

    public List<String> urlList = new ArrayList<>();

    public boolean isShowAll = false;
}

