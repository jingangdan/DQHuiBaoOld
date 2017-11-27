package com.dq.huibao.ui.memcen;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.bigkoo.pickerview.OptionsPickerView;
import com.dq.huibao.R;
import com.dq.huibao.base.BaseActivity;
import com.dq.huibao.bean.LoginBean;
import com.dq.huibao.bean.memcen.Member;
import com.dq.huibao.utils.GsonUtil;
import com.dq.huibao.utils.HttpUtils;
import com.dq.huibao.utils.MD5Util;
import com.dq.huibao.utils.SPUserInfo;
import com.dq.huibao.view.JsonBean;
import com.dq.huibao.view.JsonFileReader;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.util.ArrayList;
import java.util.Calendar;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Description：个人资料
 * Created by jingang on 2017/11/24.
 */

public class MemcenActivity extends BaseActivity {
    @Bind(R.id.rb_man)
    RadioButton rbMan;
    @Bind(R.id.rb_wuman)
    RadioButton rbWuman;
    @Bind(R.id.rg_sex)
    RadioGroup rgSex;
    /**/
    @Bind(R.id.et_member_realname)
    EditText etMemberRealname;
    @Bind(R.id.tv_member_mobile)
    TextView tvMemberMobile;
    @Bind(R.id.et_member_weixin)
    EditText etMemberWeixin;
    @Bind(R.id.et_member_mobile)
    EditText etMemberMobile;
    @Bind(R.id.et_member_alipay)
    EditText etMemberAlipay;
    @Bind(R.id.et_member_alipayname)
    EditText etMemberAlipayname;
    @Bind(R.id.tv_member_province)
    TextView tvMemberProvince;
    @Bind(R.id.tv_member_birth)
    TextView tvMemberBirth;
    @Bind(R.id.but_member_ok)
    Button butMemberOk;

    /*所在城市*/
    private ArrayList<JsonBean> options1Items = new ArrayList<>();
    private ArrayList<ArrayList<String>> options2Items = new ArrayList<>();

    /*生日*/
    int mYear, mMonth, mDay;

    /*接口地址*/
    private String PATH = "";
    private RequestParams params = null;

    /*接收页面传值*/
    private Intent intent;

    /*本地轻量型缓存*/
    private SPUserInfo spUserInfo;
    private String unionid = "";

    /*UI显示*/
    private String realname = "", weixin = "", membermobile = "", alipay = "",
            alipayname = "", gender = "", province = "", city = "",
            birthyear = "", birthmonth = "", birthday = "";

    @SuppressLint("WrongConstant")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_memcen);
        ButterKnife.bind(this);

        rgSex.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == rbMan.getId()) {
                    toast("男");
                } else if (checkedId == rbWuman.getId()) {
                    toast("女");
                }
            }
        });

        initJsonData();

        final Calendar ca = Calendar.getInstance();
        mYear = ca.get(Calendar.YEAR);
        mMonth = ca.get(Calendar.MONTH);
        mDay = ca.get(Calendar.DAY_OF_MONTH);

        initDate();
    }

    @Override
    protected void initWidght() {
        super.initWidght();
        setTitleName("个人资料");
    }

    @OnClick({R.id.tv_member_mobile, R.id.tv_member_province, R.id.tv_member_birth, R.id.but_member_ok})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_member_mobile:
                intent = new Intent(MemcenActivity.this, MobileActivity.class);
                startActivity(intent);

                break;
            case R.id.tv_member_province:
                //选择所在城市
                showPickerView();
                break;
            case R.id.tv_member_birth:
                //选择日期
                Calendar calendar = Calendar.getInstance();
                @SuppressLint("WrongConstant") final DatePickerDialog datePicker = new DatePickerDialog(this, null,
                        calendar.get(Calendar.YEAR),
                        calendar.get(Calendar.MONTH),
                        calendar.get(Calendar.DAY_OF_MONTH));
                datePicker.setCancelable(true);
                datePicker.setCanceledOnTouchOutside(true);
                datePicker.setButton(DialogInterface.BUTTON_POSITIVE, "确认",
                        new DialogInterface.OnClickListener() {
                            @RequiresApi(api = Build.VERSION_CODES.HONEYCOMB)
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                //确定的逻辑代码在监听中实现
                                DatePicker picker = datePicker.getDatePicker();
                                int year = picker.getYear();
                                int monthOfYear = picker.getMonth();
                                int dayOfMonth = picker.getDayOfMonth();

                                birthyear = String.valueOf(year);
                                birthmonth = String.valueOf(monthOfYear);
                                birthday = String.valueOf(dayOfMonth);
                                tvMemberBirth.setText(year + "-" + (monthOfYear + 1) + "-" + dayOfMonth);

                            }
                        });
                datePicker.setButton(DialogInterface.BUTTON_NEGATIVE, "取消",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                //取消什么也不用做
                                datePicker.cancel();
                            }
                        });
                datePicker.show();

                break;
            case R.id.but_member_ok:
                dialog();
                break;
        }
    }


    /**/
    public void initDate() {
        spUserInfo = new SPUserInfo(getApplication());

        if (!(spUserInfo.getLoginReturn().equals(""))) {
            LoginBean loginBean = GsonUtil.gsonIntance().gsonToBean(spUserInfo.getLoginReturn(), LoginBean.class);
            unionid = loginBean.getData().getUnionid();

            getMember(unionid);

        } else {
            toast("登录状态出错，请重新登录");
        }

    }

    /**
     * 获取个人资料
     *
     * @param unionid
     */
    public void getMember(String unionid) {

        PATH = HttpUtils.PATH + HttpUtils.SHOP_MEMBER_GETMEMBER +
                "unionid=" + unionid + "&stamp=" + (System.currentTimeMillis() / 1000) + "&doc=" +
                MD5Util.getMD5String(HttpUtils.SHOP_MEMBER_GETMEMBER + "unionid=" + unionid +
                        "&stamp=" + (System.currentTimeMillis() / 1000) + "&dequanhuibaocom");

        params = new RequestParams(PATH);
        System.out.println("获取个人资料 = " + PATH);

        x.http().get(params,
                new Callback.CommonCallback<String>() {
                    @Override
                    public void onSuccess(String result) {
                        System.out.println("获取个人资料 = " + result);
                        Member member = GsonUtil.gsonIntance().gsonToBean(result, Member.class);

                        realname = member.getData().getRealname();
                        weixin = member.getData().getWeixin();
                        membermobile = member.getData().getMembermobile();
                        alipay = member.getData().getAlipay();
                        alipayname = member.getData().getAlipayname();
                        gender = member.getData().getGender();
                        province = member.getData().getProvince();
                        city = member.getData().getCity();
                        birthyear = member.getData().getBirthyear();
                        birthmonth = member.getData().getBirthmonth();
                        birthday = member.getData().getBirthday();

                        setDate();


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
     * 修改个人资料
     *
     * @param unionid
     * @param realname     真实姓名
     * @param weixin       微信号
     * @param membermobile 手机号
     * @param alipay       支付宝账号
     * @param alipayname   支付宝姓名
     * @param gender       性别
     * @param province     省
     * @param city         市
     * @param birthyear    生日
     * @param birthmonth
     * @param birthday
     */
    public void setMember(String unionid, String realname, String weixin,
                          String membermobile, String alipay, String alipayname,
                          String gender, String province, String city,
                          String birthyear, String birthmonth, String birthday) {

        PATH = HttpUtils.PATH + HttpUtils.SHOP_MEMBER_GETMEMBER +
                "unionid=" + unionid + "&stamp=" + (System.currentTimeMillis() / 1000) + "&doc=" +
                MD5Util.getMD5String(HttpUtils.SHOP_MEMBER_GETMEMBER + "unionid=" + unionid +
                        "&stamp=" + (System.currentTimeMillis() / 1000) + "&dequanhuibaocom") +
                "&realname=" + realname + "&weixin=" + weixin +
                "&membermobile=" + membermobile + "&alipay=" + alipay + "&alipayname=" + alipayname +
                "&gender=" + gender + "&province=" + province + "&city=" + city +
                "&birthyear=" + birthyear + "&birthmonth=" + birthmonth + "&birthday=" + birthday;

        params = new RequestParams(PATH);
        System.out.println("修改个人资料 = " + PATH);

        x.http().get(params,
                new Callback.CommonCallback<String>() {
                    @Override
                    public void onSuccess(String result) {
                        System.out.println("修改个人资料 = " + result);
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

    /*组件赋值*/
    public void setDate() {
        etMemberRealname.setText("" + realname);
        etMemberMobile.setText("" + membermobile);
        etMemberWeixin.setText("" + weixin);
        etMemberAlipay.setText("" + alipay);
        etMemberAlipayname.setText("" + alipayname);

        tvMemberProvince.setText("" + province + "  " + city);
        if (!birthyear.equals("")) {
            tvMemberBirth.setText(birthyear + "-" + birthmonth + "-" + birthday);
        }

    }

    /*省市二级联动*/
    private void showPickerView() {
        OptionsPickerView pvOptions = new OptionsPickerView.Builder(this, new OptionsPickerView.OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int options1, int options2, int options3, View v) {
                //返回的分别是三个级别的选中位置
                String text = options1Items.get(options1).getPickerViewText() +
                        options2Items.get(options1).get(options2);
//                        options3Items.get(options1).get(options2).get(options3);

                province = options1Items.get(options1).getPickerViewText();
                city = options2Items.get(options1).get(options2);

                tvMemberProvince.setText(text);
            }
        }).setTitleText("")
                .setDividerColor(Color.GRAY)
                .setTextColorCenter(Color.GRAY)
                .setContentTextSize(13)
                .setOutSideCancelable(false)
                .build();
//        pvOptions.setPicker(options1Items);//一级选择器
        pvOptions.setPicker(options1Items, options2Items);//二级选择器
//        pvOptions.setPicker(options1Items, options2Items, options3Items);//三级选择器
        pvOptions.show();
    }

    /*解析数据*/
    private void initJsonData() {   //解析数据

        /**
         * 注意：assets 目录下的Json文件仅供参考，实际使用可自行替换文件
         * 关键逻辑在于循环体
         *
         * */
        //  获取json数据
        String JsonData = JsonFileReader.getJson(this, "province_data.json");
        ArrayList<JsonBean> jsonBean = parseData(JsonData);//用Gson 转成实体

        /**
         * 添加省份数据
         *
         * 注意：如果是添加的JavaBean实体，则实体类需要实现 IPickerViewData 接口，
         * PickerView会通过getPickerViewText方法获取字符串显示出来。
         */
        options1Items = jsonBean;

        for (int i = 0; i < jsonBean.size(); i++) {//遍历省份
            ArrayList<String> CityList = new ArrayList<>();//该省的城市列表（第二级）
            ArrayList<ArrayList<String>> Province_AreaList = new ArrayList<>();//该省的所有地区列表（第三极）

            for (int c = 0; c < jsonBean.get(i).getCityList().size(); c++) {//遍历该省份的所有城市
                String CityName = jsonBean.get(i).getCityList().get(c).getName();
                CityList.add(CityName);//添加城市

                ArrayList<String> City_AreaList = new ArrayList<>();//该城市的所有地区列表

                //如果无地区数据，建议添加空字符串，防止数据为null 导致三个选项长度不匹配造成崩溃
                if (jsonBean.get(i).getCityList().get(c).getArea() == null
                        || jsonBean.get(i).getCityList().get(c).getArea().size() == 0) {
                    City_AreaList.add("");
                } else {

                    for (int d = 0; d < jsonBean.get(i).getCityList().get(c).getArea().size(); d++) {//该城市对应地区所有数据
                        String AreaName = jsonBean.get(i).getCityList().get(c).getArea().get(d);

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
            //options3Items.add(Province_AreaList);
        }
    }

    public ArrayList<JsonBean> parseData(String result) {
        //Gson 解析
        ArrayList<JsonBean> detail = new ArrayList<>();
        try {
            JSONArray data = new JSONArray(result);
            Gson gson = new Gson();
            for (int i = 0; i < data.length(); i++) {
                JsonBean entity = gson.fromJson(data.optJSONObject(i).toString(), JsonBean.class);
                detail.add(entity);
            }
        } catch (Exception e) {
            e.printStackTrace();
            // mHandler.sendEmptyMessage(MSG_LOAD_FAILED);
        }
        return detail;
    }

    /*弹出框*/
    protected void dialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("确认修改吗？");
        builder.setTitle("提示");
        builder.setPositiveButton("确认", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
                setMember(unionid, etMemberRealname.getText().toString().toString(), etMemberWeixin.getText().toString(),
                        etMemberMobile.getText().toString(), etMemberAlipay.getText().toString(), etMemberAlipayname.getText().toString(),
                        "", province, city,
                        birthyear, birthmonth, birthday);

            }
        });
        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();

            }
        });
        builder.create().show();
    }

}
