package com.dq.huibao.Interface;

/**
 * 首页点击事件回调接口
 * Created by jingang on 2018/2/3.
 */

public interface HomePageInterface {
    /**
     * 搜索
     */
    void doSearch();

    /**
     * @param position 组元素位置
     * @param title    组元素标题
     * @param type     组元素类型
     * @param content  组元素标记
     */
    void doHomePage(int position, String title, String type, String content);

//    /**
//     * @param position
//     * @param gid
//     */
//    void doGoodsList(int position, String gid);

}
