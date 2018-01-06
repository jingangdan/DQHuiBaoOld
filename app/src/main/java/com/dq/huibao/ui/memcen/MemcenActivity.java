package com.dq.huibao.ui.memcen;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.RequiresApi;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bigkoo.pickerview.OptionsPickerView;
import com.dq.huibao.R;
import com.dq.huibao.base.BaseActivity;
import com.dq.huibao.bean.LoginBean;
import com.dq.huibao.bean.account.Login;
import com.dq.huibao.bean.common.Region;
import com.dq.huibao.bean.memcen.Member;
import com.dq.huibao.utils.FileUtil;
import com.dq.huibao.utils.GsonUtil;
import com.dq.huibao.utils.HttpUtils;
import com.dq.huibao.utils.MD5Util;
import com.dq.huibao.utils.SPUserInfo;
import com.dq.huibao.view.JsonBean;
import com.dq.huibao.view.JsonFileReader;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.xutils.common.Callback;
import org.xutils.common.util.MD5;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.io.File;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

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

    @Bind(R.id.iv_percen_header)
    ImageView ivPercenHeader;
    @Bind(R.id.rel_percen_header)
    RelativeLayout relPercenHeader;

    /*所在城市*/
    private List<String> options1Items = new ArrayList<>();
    private ArrayList<ArrayList<String>> options2Items = new ArrayList<>();

    /*生日*/
    int mYear, mMonth, mDay;

    /*接口地址*/
    private String PATH = "";
    private String MD5_PATH = "";
    private RequestParams params = null;

    /*接收页面传值*/
    private Intent intent;

    /*本地轻量型缓存*/
    private SPUserInfo spUserInfo;
    //private String unionid = "";
    private String phone = "", token = "";

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

        relPercenHeader.setOnClickListener(new OnClickListener());

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

        //initJsonData();
        getRegion();

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

    @OnClick({R.id.rel_percen_header,
            R.id.tv_member_mobile, R.id.tv_member_province, R.id.tv_member_birth, R.id.but_member_ok})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.rel_percen_header:
                //选择头像
                break;
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
            //LoginBean loginBean = GsonUtil.gsonIntance().gsonToBean(spUserInfo.getLoginReturn(), LoginBean.class);
            //unionid = loginBean.getData().getUnionid();
            Login login = GsonUtil.gsonIntance().gsonToBean(spUserInfo.getLoginReturn(), Login.class);
            phone = login.getData().getPhone();
            token = login.getData().getToken();

            //getMember(unionid);
            getMember(phone, token);

        } else {
            toast("登录状态出错，请重新登录");
        }

    }

    /**
     * 获取个人信息
     *
     * @param phone
     * @param token
     */
    public void getMember(String phone, String token) {
        MD5_PATH = "phone=" + phone + "&timestamp=" + (System.currentTimeMillis() / 1000) + "&token=" + token;

        PATH = HttpUtils.PATHS + HttpUtils.MEM_MEMBER + MD5_PATH + "&sign=" +
                MD5Util.getMD5String(MD5_PATH + "&key=ivKDDIZHF2b0Gjgvv2QpdzfCmhOpya5k");

        params = new RequestParams(PATH);
        System.out.println("个人信息 = " + PATH);
        x.http().get(params,
                new Callback.CommonCallback<String>() {
                    @Override
                    public void onSuccess(String result) {
                        System.out.println("个人信息 = " + result);
                        Login login = GsonUtil.gsonIntance().gsonToBean(result, Login.class);

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


    private AlertDialog alertDialog;
    private Button btn_take_photo, btn_pick_photo, btn_cancel;
    //private SelectPicPopupWindow menuWindow;//自定义的头像编辑弹出框
    private String imgUrl = "";
    private static final String IMAGE_FILE_NAME = "avatarImage.jpg";// 头像文件名称
    private String urlpath;            // 图片本地路径

    private static final int REQUESTCODE_PICK = 0;        // 相册选图标记
    private static final int REQUESTCODE_TAKE = 1;        // 相机拍照标记
    private static final int REQUESTCODE_CUTTING = 2;    // 图片裁切标记

    private String resultStr = "";    // 服务端返回结果集
    private static ProgressDialog pd;// 等待进度圈

    /**
     *
     */
    public class OnClickListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            /*menuWindow = new SelectPicPopupWindow(mContext, itemsOnClick);01
            menuWindow.showAtLocation(v.findViewById(R.id.mainLayout),
                    Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL, 0, 0);*/

            //有待更新 可以使用popupwindow来实现 注意点击出现时候的背景变化
            alertDialog = new AlertDialog.Builder(MemcenActivity.this).create();
            View localView = getLayoutInflater()
                    .inflate(R.layout.personal_header_choice, null);
            localView.setAnimation(AnimationUtils.loadAnimation(
                    MemcenActivity.this, R.anim.slide_bottom_to_top));
            Window localWindow = alertDialog.getWindow();
            localWindow.getAttributes();
            alertDialog.show();
            localWindow.setContentView(localView);
            localWindow.setGravity(Gravity.BOTTOM);
            localWindow.setLayout(-1, 380);

            btn_take_photo = (Button) localView.findViewById(R.id.btn_take_photo);
            btn_take_photo.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //拍照

                    alertDialog.dismiss();//关闭AlertDialog

                    Intent takeIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                    //下面这句指定调用相机拍照后的照片存储的路径
                    takeIntent.putExtra(MediaStore.EXTRA_OUTPUT,
                            Uri.fromFile(new File(Environment.getExternalStorageDirectory(), IMAGE_FILE_NAME)));
                    startActivityForResult(takeIntent, REQUESTCODE_TAKE);

                }
            });

            btn_pick_photo = (Button) localView.findViewById(R.id.btn_pick_photo);
            btn_pick_photo.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //相册

                    alertDialog.dismiss();

                    Intent pickIntent = new Intent(Intent.ACTION_PICK, null);
                    // 如果朋友们要限制上传到服务器的图片类型时可以直接写如："image/jpeg 、 image/png等的类型"
                    pickIntent.setDataAndType(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, "image/*");
                    startActivityForResult(pickIntent, REQUESTCODE_PICK);

                }
            });

            btn_cancel = (Button) localView.findViewById(R.id.btn_cancel);
            //取消
            btn_cancel.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //取消
                    alertDialog.dismiss();
                }
            });

        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {

        switch (requestCode) {
            case REQUESTCODE_PICK:// 直接从相册获取
                try {
                    startPhotoZoom(data.getData());
                } catch (NullPointerException e) {
                    e.printStackTrace();// 用户点击取消操作
                }
                break;
            case REQUESTCODE_TAKE:// 调用相机拍照
                File temp = new File(Environment.getExternalStorageDirectory() + "/" + IMAGE_FILE_NAME);
                startPhotoZoom(Uri.fromFile(temp));
                break;
            case REQUESTCODE_CUTTING:// 取得裁剪后的图片
                if (data != null) {
                    setPicToView(data);
                }
                break;
        }
        super.onActivityResult(requestCode, resultCode, data);

    }

    /**
     * 裁剪图片方法实现
     *
     * @param uri
     */
    public void startPhotoZoom(Uri uri) {
        Intent intent = new Intent("com.android.camera.action.CROP");
        intent.setDataAndType(uri, "image/*");
        // crop=true是设置在开启的Intent中设置显示的VIEW可裁剪
        intent.putExtra("crop", "true");
        // aspectX aspectY 是宽高的比例
        intent.putExtra("aspectX", 1);
        intent.putExtra("aspectY", 1);
        // outputX outputY 是裁剪图片宽高
        intent.putExtra("outputX", 300);
        intent.putExtra("outputY", 300);
        intent.putExtra("return-data", true);
        startActivityForResult(intent, REQUESTCODE_CUTTING);
    }

    /**
     * 保存裁剪之后的图片数据
     *
     * @param picdata
     */
    private void setPicToView(Intent picdata) {
        Bundle extras = picdata.getExtras();
        if (extras != null) {
            // 取得SDCard图片路径做显示
            Bitmap photo = extras.getParcelable("data");
            Drawable drawable = new BitmapDrawable(null, photo);
            urlpath = FileUtil.saveFile(MemcenActivity.this, "temphead.jpg", photo);
            ivPercenHeader.setImageDrawable(drawable);
            //roundImageView.setImageDrawable(drawable);

            // 新线程后台上传服务端
//			pd = ProgressDialog.show(mContext, null, "正在上传图片，请稍候...");
//			new Thread(uploadImageRunnable).start();
        }
    }


    /**
     * 获取省市列表
     */
    public void getRegion() {
        PATH = HttpUtils.PATHS + HttpUtils.COMMON_REGION;

        params = new RequestParams(PATH);
        System.out.println("省市列表 = " + PATH);
        x.http().get(params,
                new Callback.CommonCallback<String>() {
                    @Override
                    public void onSuccess(String result) {
                        System.out.println("省市列表 = " + result);

                        Region region = GsonUtil.gsonIntance().gsonToBean(result, Region.class);
                        //options1Items = region.getData();

                        for (int i = 0; i < region.getData().size(); i++) {//遍历省份
                            ArrayList<String> CityList = new ArrayList<>();//该省的城市列表（第二级）
                            ArrayList<ArrayList<String>> Province_AreaList = new ArrayList<>();//该省的所有地区列表（第三极）

                            options1Items.add(region.getData().get(i).getRegion_name());

                            for (int c = 0; c < region.getData().get(i).getCity().size(); c++) {
                                //遍历该省份的所有城市
                                String CityName = region.getData().get(i).getCity().get(c).getRegion_name();
                                CityList.add(CityName);//添加城市

//                                ArrayList<String> City_AreaList = new ArrayList<>();//该城市的所有地区列表
//
//                                //如果无地区数据，建议添加空字符串，防止数据为null 导致三个选项长度不匹配造成崩溃
//                                if (jsonBean.get(i).getCityList().get(c).getArea() == null
//                                        || jsonBean.get(i).getCityList().get(c).getArea().size() == 0) {
//                                    City_AreaList.add("");
//                                } else {
//                                    for (int d = 0; d < jsonBean.get(i).getCityList().get(c).getArea().size(); d++) {//该城市对应地区所有数据
//                                        String AreaName = jsonBean.get(i).getCityList().get(c).getArea().get(d);
//
//                                        City_AreaList.add(AreaName);//添加该城市所有地区数据
//                                    }
//                                }
//                                Province_AreaList.add(City_AreaList);//添加该省所有地区数据
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

    /*省市二级联动*/

    private void showPickerView() {
        OptionsPickerView pvOptions = new OptionsPickerView.Builder(this, new OptionsPickerView.OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int options1, int options2, int options3, View v) {
                //返回的分别是三个级别的选中位置
//                String text = options1Items.get(options1).getPickerViewText() +
//                        options2Items.get(options1).get(options2);
//                        options3Items.get(options1).get(options2).get(options3);

                String text = options1Items.get(options1) +
                        options2Items.get(options1).get(options2);
//                        options3Items.get(options1).get(options2).get(options3);

                province = options1Items.get(options1);
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

    /*弹出框*/
    protected void dialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("确认修改吗？");
        builder.setTitle("提示");
        builder.setPositiveButton("确认", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
//                setMember(unionid, etMemberRealname.getText().toString().toString(), etMemberWeixin.getText().toString(),
//                        etMemberMobile.getText().toString(), etMemberAlipay.getText().toString(), etMemberAlipayname.getText().toString(),
//                        "", province, city,
//                        birthyear, birthmonth, birthday);

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
