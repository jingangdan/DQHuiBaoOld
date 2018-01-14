package com.dq.huibao.Interface;

import android.view.View;

/**
 * 改变数量接口
 * Created by jingang on 2018/1/13.
 */

public interface ModifyCountInterface {
    /**
     * 增加操作
     *
     * @param position      组元素位置
     * @param showCountView 用于展示变化后数量的View
     * @param isChecked     子元素选中与否
     */
    void doIncrease(int position, View showCountView, boolean isChecked, String id, String goodsid, int total);

    /**
     * 删减操作
     *
     * @param position      组元素位置
     * @param showCountView 用于展示变化后数量的View
     * @param isChecked     子元素选中与否
     */
    void doDecrease(int position, View showCountView, boolean isChecked, String id, String goodsid, int total);

    /**
     * 删除子item
     *
     * @param position
     */
    void childDelete(int position);
}
