package com.dq.huibao.ui.memcen;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bigkoo.pickerview.OptionsPickerView;
import com.dq.huibao.R;
import com.dq.huibao.base.BaseActivity;
import com.dq.huibao.bean.account.Login;
import com.dq.huibao.bean.addr.AddrReturn;
import com.dq.huibao.bean.common.Region;
import com.dq.huibao.utils.CodeUtils;
import com.dq.huibao.utils.GsonUtil;
import com.dq.huibao.utils.HttpUtils;
import com.dq.huibao.utils.MD5Util;
import com.dq.huibao.utils.SPUserInfo;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Description：添加收货地址
 * Created by jingang on 2017/10/31.
 */

public class AddAddressActivity extends BaseActivity {

    /*选择地区*/
    @Bind(R.id.rel_address_area)
    RelativeLayout relAddressArea;

    @Bind(R.id.tv_address_area)
    TextView tvAddressArea;
    @Bind(R.id.et_addr_contact)
    EditText etAddrContact;
    @Bind(R.id.et_addr_mobile)
    EditText etAddrMobile;
    @Bind(R.id.et_addr)
    EditText etAddr;
    @Bind(R.id.but_delivery)
    Button butDelivery;

    @Bind(R.id.cb_addr_isdefault)
    CheckBox cbAddrIsdefault;

    /*UI获取参数*/


    /*解析数据*/
    private List<Region.DataBean> regionList = new ArrayList<>();

    private ArrayList<String> options1Items = new ArrayList<>();
    private ArrayList<ArrayList<String>> options2Items = new ArrayList<>();
    private ArrayList<ArrayList<ArrayList<String>>> options3Items = new ArrayList<>();

    private int tag1 = 0, tag2 = 0;

    /*接口地址*/
    private String PATH = "";
    private String MD5_PATH = "";
    private RequestParams params = null;

    /*本地轻量型缓存*/
    private SPUserInfo spUserInfo;
    private String phone = "", token = "";

    private Intent intent;
    private String addr = "", contact = "", mobile = "", regionid = "", region = "", isdefault = "", addrid = "", tag = "";
    private String UTF_addr = "", UTF_contact = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addaddress);
        ButterKnife.bind(this);

        intent = getIntent();
        addrid = intent.getStringExtra("addrid");
        addr = intent.getStringExtra("addr");
        contact = intent.getStringExtra("contact");
        mobile = intent.getStringExtra("mobile");
        regionid = intent.getStringExtra("regionid");
        region = intent.getStringExtra("region");
        isdefault = intent.getStringExtra("isdefault");

        etAddrContact.setText(contact);
        etAddrMobile.setText(mobile);
        etAddr.setText(addr);
        tvAddressArea.setText(region);

        if (isdefault != null) {
            if (isdefault.equals("1")) {
                cbAddrIsdefault.setChecked(true);
            } else if (isdefault.equals("0")) {
                cbAddrIsdefault.setChecked(false);
            }
        } else {
            isdefault = "0";
        }


        tag = intent.getStringExtra("tag");


        getRegion();
        isLogin();

    }

    @Override
    protected void initWidght() {
        super.initWidght();
        setTitleName("添加新地址");
    }

    /*
        * 判断登录状态
        *  */
    @SuppressLint("WrongConstant")
    public void isLogin() {
        spUserInfo = new SPUserInfo(getApplication());

        if (spUserInfo.getLogin().equals("1")) {

            if (!(spUserInfo.getLoginReturn().equals(""))) {
                Login login = GsonUtil.gsonIntance().gsonToBean(spUserInfo.getLoginReturn(), Login.class);
                phone = login.getData().getPhone();
                token = login.getData().getToken();

            }

        } else {
        }
    }

    @OnClick({R.id.rel_address_area, R.id.but_delivery, R.id.cb_addr_isdefault})
    public void onClick(View view) {
        addr = etAddr.getText().toString();
        contact = etAddrContact.getText().toString();
        mobile = etAddrMobile.getText().toString();
        try {
            UTF_contact = URLEncoder.encode(contact, "UTF-8");
            UTF_addr = URLEncoder.encode(addr, "UTF-8");

        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        switch (view.getId()) {
            case R.id.rel_address_area:
                //选择所在区域
                if (regionList.size() > 0) {
                    showPickerView();

                }

                break;

            case R.id.but_delivery:
                System.out.println("111 = " + regionid);
                //添加
                if (!UTF_contact.equals("")) {
                    if (!mobile.equals("")) {
                        if (!UTF_addr.equals("")) {
                            if (!regionid.equals("")) {
                                if (tag.equals("1")) {
                                    //修改
                                    editAddr(addrid, regionid, isdefault, addr, UTF_addr, contact, UTF_contact, mobile, phone, token);

                                } else if (tag.equals("0")) {
                                    //添加
                                    addAddr(regionid, isdefault, addr, UTF_addr, contact, UTF_contact, mobile, phone, token);
                                }


                            } else {
                                toast("请选择所在区域");
                            }

                        } else {
                            toast("详细地址不可为空");
                        }
                    } else {
                        toast("联系电话不可为空");
                    }
                } else {
                    toast("收货人不可为空");
                }

                break;

            case R.id.cb_addr_isdefault:
                //是否默认
                if (cbAddrIsdefault.isChecked()) {
                    isdefault = "1";
                } else {
                    isdefault = "0";
                }
                break;

            default:
                break;
        }
    }

    /**
     * 获取省市列表
     */
    public void getRegion() {
        PATH = HttpUtils.PATHS + HttpUtils.COMMON_REGION;
        params = new RequestParams(PATH);
        System.out.println("获取省市列表 = " + PATH);
        x.http().get(params,
                new Callback.CommonCallback<String>() {
                    @Override
                    public void onSuccess(String result) {
                        System.out.println("获取省市列表 = " + result);
                        Region region = GsonUtil.gsonIntance().gsonToBean(result, Region.class);

                        /**
                         * 添加省份数据
                         *
                         * 注意：如果是添加的JavaBean实体，则实体类需要实现 IPickerViewData 接口，
                         * PickerView会通过getPickerViewText方法获取字符串显示出来。
                         */
                        regionList = region.getData();

                        for (int i = 0; i < regionList.size(); i++) {
                            //遍历省份
                            ArrayList<String> CityList = new ArrayList<>();//该省的城市列表（第二级）
                            ArrayList<ArrayList<String>> Province_AreaList = new ArrayList<>();//该省的所有地区列表（第三极）

                            options1Items.add(region.getData().get(i).getRegion_name());

                            for (int c = 0; c < regionList.get(i).getCity().size(); c++) {//遍历该省份的所有城市
                                String CityName = regionList.get(i).getCity().get(c).getRegion_name();
                                CityList.add(CityName);//添加城市
                                ArrayList<String> City_AreaList = new ArrayList<>();//该城市的所有地区列表

                                //如果无地区数据，建议添加空字符串，防止数据为null 导致三个选项长度不匹配造成崩溃
                                if (regionList.get(i).getCity().get(c).getRegion_name() == null
                                        || regionList.get(i).getCity().get(c).getDistrict().size() == 0) {
                                    City_AreaList.add("");
                                } else {

                                    for (int d = 0; d < regionList.get(i).getCity().get(c).getDistrict().size(); d++) {//该城市对应地区所有数据
                                        String AreaName = regionList.get(i).getCity().get(c).getDistrict().get(d).getRegion_name();

                                        City_AreaList.add(AreaName);//添加该城市所有地区数据
                                    }
                                }
                                Province_AreaList.add(City_AreaList);//添加该省所有地区数据
                            }

                            /**
                             * 添加城市数据
                             */
                            options2Items.add(CityList);

                            /**
                             * 添加地区数据
                             */
                            options3Items.add(Province_AreaList);
                        }

                    }

                    @Override
                    public void onError(Throwable ex, boolean isOnCallback) {

                    }

                    @Override
                    public void onCancelled(CancelledException cex) {

                    }

                    @Override
                    public void onFinished() {

                    }
                });

    }

    /**
     * 添加收货地址
     *
     * @param regionid  选择区域id
     * @param isdefault 是否设为默认  0 否 1 是
     * @param addr      详细地址
     * @param contact   联系人
     * @param mobile    联系电话
     * @param phone
     * @param token
     */
    public void addAddr(final String regionid, String isdefault, String addr, String UTF_addr, String contact, String UTF_contact, String mobile, String phone, String token) {
        MD5_PATH = "addr=" + UTF_addr + "&contact=" + UTF_contact + "&isdefault=" + isdefault + "&mobile=" + mobile +
                "&phone=" + phone + "&regionid=" + regionid + "&timestamp=" + (System.currentTimeMillis() / 1000) + "&token=" + token;

        PATH = HttpUtils.PATHS + HttpUtils.MEMBER_ADDADDR + MD5_PATH + "&sign=" +
                MD5Util.getMD5String("addr=" + addr + "&contact=" + contact + "&isdefault=" + isdefault + "&mobile=" + mobile +
                        "&phone=" + phone + "&regionid=" + regionid + "&timestamp=" + (System.currentTimeMillis() / 1000) + "&token=" + token + HttpUtils.KEY);

        params = new RequestParams(PATH);
        System.out.println("加密 = " + MD5_PATH);

        System.out.println("添加收货地址 = " + PATH);
        x.http().post(params,
                new Callback.CommonCallback<String>() {
                    @Override
                    public void onSuccess(String result) {
                        System.out.println("添加收货地址 = " + result);
                        AddrReturn addrReturn = GsonUtil.gsonIntance().gsonToBean(result, AddrReturn.class);
                        if (addrReturn.getStatus() == 1) {
                            toast("" + addrReturn.getData());
                            intent = new Intent();
                            setResult(CodeUtils.ADDR_ADD, intent);
                            AddAddressActivity.this.finish();
                        }


                    }

                    @Override
                    public void onError(Throwable ex, boolean isOnCallback) {

                    }

                    @Override
                    public void onCancelled(CancelledException cex) {

                    }

                    @Override
                    public void onFinished() {

                    }
                });
    }

    /**
     * 修改收货地址
     *
     * @param id
     * @param regionid
     * @param isdefault
     * @param addr
     * @param UTF_addr
     * @param contact
     * @param UTF_contact
     * @param mobile
     * @param phone
     * @param token
     */
    public void editAddr(String id, final String regionid, String isdefault, String addr, String UTF_addr, String contact, String UTF_contact, String mobile, String phone, String token) {
        MD5_PATH = "addr=" + UTF_addr + "&contact=" + UTF_contact + "&id=" + id + "&isdefault=" + isdefault + "&mobile=" + mobile +
                "&phone=" + phone + "&regionid=" + regionid + "&timestamp=" + (System.currentTimeMillis() / 1000) + "&token=" + token;

        PATH = HttpUtils.PATHS + HttpUtils.MEMBER_EDITADDR + MD5_PATH + "&sign=" +
                MD5Util.getMD5String("addr=" + addr + "&contact=" + contact + "&id=" + id + "&isdefault=" + isdefault + "&mobile=" + mobile +
                        "&phone=" + phone + "&regionid=" + regionid + "&timestamp=" + (System.currentTimeMillis() / 1000) + "&token=" + token + HttpUtils.KEY);

        System.out.println("addr=" + addr + "&contact=" + contact + "&id=" + id + "&isdefault=" + isdefault + "&mobile=" + mobile +
                "&phone=" + phone + "&regionid=" + regionid + "&timestamp=" + (System.currentTimeMillis() / 1000) + "&token=" + token + HttpUtils.KEY);

        System.out.println("加密 = " + MD5_PATH);

        params = new RequestParams(PATH);

        System.out.println("修改收货地址 = " + PATH);
        x.http().get(params,
                new Callback.CommonCallback<String>() {
                    @Override
                    public void onSuccess(String result) {
                        System.out.println("修改收货地址 = " + result);
                        AddrReturn addrReturn = GsonUtil.gsonIntance().gsonToBean(result, AddrReturn.class);
                        if (addrReturn.getStatus() == 1) {
                            toast("" + addrReturn.getData());
                            intent = new Intent();
                            setResult(CodeUtils.ADDR_ADD, intent);
                            AddAddressActivity.this.finish();
                        }


                    }

                    @Override
                    public void onError(Throwable ex, boolean isOnCallback) {

                    }

                    @Override
                    public void onCancelled(CancelledException cex) {

                    }

                    @Override
                    public void onFinished() {

                    }
                });
    }


    private void showPickerView() {
        OptionsPickerView pvOptions = new OptionsPickerView.Builder(this, new OptionsPickerView.OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int options1, int options2, int options3, View v) {
                //返回的分别是三个级别的选中位置
                String text = options1Items.get(options1) +
                        options2Items.get(options1).get(options2) +
                        options3Items.get(options1).get(options2).get(options3);

                getRegionid(options1, options2);

                tvAddressArea.setText(text);
            }
        }).setTitleText("")
                .setDividerColor(Color.GRAY)
                .setTextColorCenter(Color.GRAY)
                .setContentTextSize(14)
                .setOutSideCancelable(false)
                .build();

          /*pvOptions.setPicker(options1Items);//一级选择器
        pvOptions.setPicker(options1Items, options2Items);//二级选择器*/
        pvOptions.setPicker(options1Items, options2Items, options3Items);//三级选择器
        pvOptions.show();
    }

    /**
     * @param position1
     * @param position2
     */
    public void getRegionid(int position1, int position2) {
        for (int i = 0; i < regionList.size(); i++) {
            for (int j = 0; j < regionList.get(i).getCity().size(); j++) {
                regionid = regionList.get(position1).getCity().get(position2).getId();
            }
        }
    }

}
