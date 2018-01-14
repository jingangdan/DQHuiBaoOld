package com.dq.huibao.Interface;

/**
 * 复选框接口
 * Created by jingang on 2018/1/13.
 */

public interface CheckInterface {
    /**
     * 组选框状态改变触发的事件
     *
     * @param position  元素位置
     * @param isChecked 元素选中与否
     */
    void checkGroup(int position, boolean isChecked);
}
