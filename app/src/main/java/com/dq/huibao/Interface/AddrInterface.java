package com.dq.huibao.Interface;

/**
 * 收货地址操作回调接口
 * Created by jingang on 2018/1/16.
 */

public interface AddrInterface {
    /**
     * 修改收货地址
     *
     * @param position  组元素位置
     * @param id        收货地址id
     * @param regionid  选择区域id
     * @param region    选择区域名称
     * @param isdefault 是否为默认地址
     * @param addr      详细地址
     * @param contact   联系人
     * @param mobile    联系方式
     */
    void checkEdit(int position, String id, String regionid, String region, int isdefault, String addr, String contact, String mobile);

    /**
     * 删除收货地址
     *
     * @param position 组元素位置
     * @param id       收货地址id
     */
    void checkDel(int position, String id);

    /**
     * 设为默认地址
     *
     * @param id
     * @param position
     */
    void checkIsdefault(String id, int position);

}
