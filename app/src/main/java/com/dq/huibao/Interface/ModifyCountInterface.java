package com.dq.huibao.Interface;

import android.view.View;

/**
 * 改变数量接口
 * Created by jingang on 2018/1/13.
 */

/**
 * 改变数量的接口
 */
public interface ModifyCountInterface {
    /**
     * 增加操作
     *
     * @param groupPosition 组元素位置
     * @param childPosition 子元素位置
     * @param showCountView 用于展示变化后数量的View
     * @param isChecked     子元素选中与否
     * @param gid 商品id
     * @param optionid 规格id
     * @param count 数量
     */
    void doIncrease(int groupPosition, int childPosition, View showCountView, boolean isChecked, String gid, String optionid, int count);

    /**
     * 减少操作
     *
     * @param groupPosition 组元素位置
     * @param childPosition 子元素位置
     * @param showCountView 用于展示变化后数量的View
     * @param isChecked     子元素选中与否
     * @param gid
     * @param optionid
     * @param count
     */
    void doDecrease(int groupPosition, int childPosition, View showCountView, boolean isChecked, String gid, String optionid, int count);

    /**
     * 删除子item
     *
     * @param groupPosition
     * @param childPosition
     */
    void childDelete(int groupPosition, int childPosition);
}