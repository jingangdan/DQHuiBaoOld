package com.dq.huibao.ui;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.Display;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.allenliu.versionchecklib.core.AllenChecker;
import com.allenliu.versionchecklib.core.VersionDialogActivity;
import com.allenliu.versionchecklib.core.VersionParams;
import com.dq.huibao.R;
import com.dq.huibao.bean.CheckVersion;
import com.dq.huibao.bean.addr.AddrReturn;
import com.dq.huibao.fragment.FMClassify;
import com.dq.huibao.fragment.FMDistribution;
import com.dq.huibao.fragment.FMFind;
import com.dq.huibao.fragment.FMHomePage;
import com.dq.huibao.fragment.FMMemCen;
import com.dq.huibao.fragment.FMShopcar;
import com.dq.huibao.fragment.FMStore;
import com.dq.huibao.update.CustomVersionDialogActivity;
import com.dq.huibao.update.UVService;
import com.dq.huibao.utils.GsonUtil;
import com.dq.huibao.utils.HttpPath;
import com.dq.huibao.utils.HttpxUtils;
import com.dq.huibao.utils.VersionCodeUtils;

import org.xutils.BuildConfig;
import org.xutils.common.Callback;
import org.xutils.x;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 惠宝商城主界面
 */
@SuppressLint("Registered")
public class MainActivity extends FragmentActivity {

    @Bind(R.id.content)
    FrameLayout content;
    @Bind(R.id.iv_home_page)
    ImageView ivHomePage;
    @Bind(R.id.tv_home_page)
    TextView tvHomePage;
    @Bind(R.id.lin_home_page)
    LinearLayout linHomePage;
    @Bind(R.id.lin_footer)
    LinearLayout linFooter;

    //使用Fragment组件  简单开启事务
    FragmentManager fm;
    FragmentTransaction ft;

    @Bind(R.id.iv_classify)
    ImageView ivClassify;
    @Bind(R.id.tv_classify)
    TextView tvClassify;
    @Bind(R.id.lin_classify)
    LinearLayout linClassify;
    @Bind(R.id.iv_shopcar)
    ImageView ivShopcar;
    @Bind(R.id.tv_shopcar)
    TextView tvShopcar;
    @Bind(R.id.lin_shopcar)
    LinearLayout linShopcar;
    @Bind(R.id.iv_members_center)
    ImageView ivMembersCenter;
    @Bind(R.id.tv_members_center)
    TextView tvMembersCenter;
    @Bind(R.id.lin_members_center)
    LinearLayout linMembersCenter;
    @Bind(R.id.iv_hp_store)
    ImageView ivHpStore;
    @Bind(R.id.tv_hp_store)
    TextView tvHpStore;
    @Bind(R.id.lin_hp_store)
    LinearLayout linHpStore;
    @Bind(R.id.iv_store)
    ImageView ivStore;
    @Bind(R.id.tv_store)
    TextView tvStore;
    @Bind(R.id.lin_store)
    LinearLayout linStore;
    @Bind(R.id.iv_distribution)
    ImageView ivDistribution;
    @Bind(R.id.tv_distribution)
    TextView tvDistribution;
    @Bind(R.id.lin_distribution)
    LinearLayout linDistribution;

    /*Fragment*/
    private FMHomePage fmHomePage;
    //private FMHomePageOlds fmHomePage;
    private FMClassify fmClassify;
    private FMShopcar fmShopcar;
    private FMMemCen fmMemCen;

    private FMFind fmStore;

    //private FMStore fmStore;
    private FMDistribution fmDistribution;

    public static MainActivity mainActivity;

    /*检查版本更新*/
    private String PATH = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        mainActivity = this;

//        System.out.println("5555 = " + VersionCodeUtils.getVerCode(this));
//        System.out.println("55555 = " + VersionCodeUtils.getVerName(this));

        getVersion(VersionCodeUtils.getVerName(this));

        x.Ext.init(this.getApplication());
        x.Ext.setDebug(BuildConfig.DEBUG);

        fm = getSupportFragmentManager();
        setTabSelection(0);

    }

    @SuppressLint("WrongConstant")
    @OnClick({R.id.lin_home_page, R.id.lin_classify, R.id.lin_shopcar, R.id.lin_members_center,
            R.id.lin_hp_store, R.id.lin_store, R.id.lin_distribution})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.lin_home_page:
                setTabSelection(0);
                break;
            case R.id.lin_classify:
                setTabSelection(1);
                linHomePage.setVisibility(View.VISIBLE);
                linHpStore.setVisibility(View.GONE);
                linDistribution.setVisibility(View.GONE);
                linStore.setVisibility(View.VISIBLE);
                break;
            case R.id.lin_shopcar:
                setTabSelection(2);
                linHomePage.setVisibility(View.VISIBLE);
                linHpStore.setVisibility(View.GONE);
                linDistribution.setVisibility(View.GONE);
                linStore.setVisibility(View.VISIBLE);
                break;
            case R.id.lin_members_center:
                setTabSelection(3);
                linHomePage.setVisibility(View.VISIBLE);
                linHpStore.setVisibility(View.GONE);
                linDistribution.setVisibility(View.GONE);
                linStore.setVisibility(View.VISIBLE);
                break;
            case R.id.lin_hp_store:
                setTabSelection(6);
                break;

            case R.id.lin_store:
                setTabSelection(4);
                linHomePage.setVisibility(View.GONE);
                linHpStore.setVisibility(View.VISIBLE);
                linDistribution.setVisibility(View.VISIBLE);
                linStore.setVisibility(View.GONE);
                break;

            case R.id.lin_distribution:
                setTabSelection(5);
                break;
        }
    }

    /**
     * 根据传入的index参数来设置选中的tab页。
     *
     * @param index
     */
    private void setTabSelection(int index) {
        int mycolor = getResources().getColor(R.color.mycolor);
        // 每次选中之前先清楚掉上次的选中状态
        clearSelection();
        // 开启一个Fragment事务
        FragmentTransaction ft = fm.beginTransaction();
        // 先隐藏掉所有的Fragment，以防止有多个Fragment显示在界面上的情况
        hideFragments(ft);

        switch (index) {
            case 0:
                ivHomePage.setImageResource(R.mipmap.icon_homepage002);
                tvHomePage.setTextColor(mycolor);

                if (fmHomePage == null) {
                    // 如果HomePageFragment为空，则创建一个并添加到界面上
                    fmHomePage = new FMHomePage();
                    //fmHomePage.setArguments(bundle);
                    ft.add(R.id.content, fmHomePage);
                } else {
                    // 如果HomePageFragment不为空，则直接将它显示出来
                    ft.show(fmHomePage);
                }

                break;
            case 1:

                ivClassify.setImageResource(R.mipmap.icon_classify002);
                tvClassify.setTextColor(mycolor);

                if (fmClassify == null) {
                    fmClassify = new FMClassify();
                    //fmOrderTest.setArguments(bundle);
                    ft.add(R.id.content, fmClassify);
                } else {
                    ft.show(fmClassify);
                }
                break;
            case 2:

                ivShopcar.setImageResource(R.mipmap.icon_shopcar002);
                tvShopcar.setTextColor(mycolor);

                if (fmShopcar == null) {
                    fmShopcar = new FMShopcar();
                    //fmShopping.setArguments(bundle);
                    ft.add(R.id.content, fmShopcar);
                } else {
                    ft.show(fmShopcar);
                }
                break;
            case 3:

                ivMembersCenter.setImageResource(R.mipmap.icon_memcen002);
                tvMembersCenter.setTextColor(mycolor);

                if (fmMemCen == null) {
                    fmMemCen = new FMMemCen();
                    //fmPerCen.setArguments(bundle);
                    ft.add(R.id.content, fmMemCen);
                } else {
                    ft.show(fmMemCen);
                }
                break;
            case 4:

                ivHpStore.setImageResource(R.mipmap.icon_store002);
                tvHpStore.setTextColor(mycolor);
                if (fmStore == null) {
                    fmStore = new FMFind();
                    ft.add(R.id.content, fmStore);
                } else {
                    ft.show(fmStore);
                }
                break;

            case 6:

                ivHpStore.setImageResource(R.mipmap.icon_store002);
                tvHpStore.setTextColor(mycolor);

                if (fmStore == null) {
                    fmStore = new FMFind();
                    ft.add(R.id.content, fmStore);
                } else {
                    ft.show(fmStore);
                }
                break;

            case 5:
                ivDistribution.setImageResource(R.mipmap.icon_distribution002);
                tvDistribution.setTextColor(mycolor);

                if (fmDistribution == null) {
                    fmDistribution = new FMDistribution();
                    ft.add(R.id.content, fmDistribution);
                } else {
                    ft.show(fmDistribution);
                }
                break;

            default:
                break;
        }
        ft.commit();
    }

    /**
     * 清除掉所有的选中状态。
     */
    private void clearSelection() {
        //还原初始状态

        ivHomePage.setImageResource(R.mipmap.icon_homepage001);
        ivClassify.setImageResource(R.mipmap.icon_classify001);
        ivShopcar.setImageResource(R.mipmap.icon_shopcar001);
        ivMembersCenter.setImageResource(R.mipmap.icon_memcen001);
        ivStore.setImageResource(R.mipmap.icon_store001);
        ivDistribution.setImageResource(R.mipmap.icon_distribution001);
        ivHpStore.setImageResource(R.mipmap.icon_store001);

        tvHomePage.setTextColor(Color.GRAY);
        tvClassify.setTextColor(Color.GRAY);
        tvShopcar.setTextColor(Color.GRAY);
        tvMembersCenter.setTextColor(Color.GRAY);
        tvStore.setTextColor(Color.GRAY);
        tvDistribution.setTextColor(Color.GRAY);
        tvHpStore.setTextColor(Color.GRAY);

    }

    /**
     * 将所有的Fragment都置为隐藏状态。
     *
     * @param transaction 用于对Fragment执行操作的事务
     */
    public void hideFragments(FragmentTransaction transaction) {
        if (fmHomePage != null) {
            transaction.hide(fmHomePage);
        }

        if (fmClassify != null) {
            transaction.hide(fmClassify);
        }
        if (fmShopcar != null) {
            transaction.hide(fmShopcar);
        }
        if (fmMemCen != null) {
            transaction.hide(fmMemCen);
        }
        if (fmStore != null) {
            transaction.hide(fmStore);
        }
        if (fmDistribution != null) {
            transaction.hide(fmDistribution);
        }

    }


    /**
     * 检查版本更新
     *
     * @param version
     */
    private String version_result = "";
    private TextView tv_now, tv_new, tv_msg;
    private Button but_cancle, but_shichang, but_update;

    public void getVersion(final String version) {
        PATH = HttpPath.PATHS + HttpPath.CHECK_VERSION + "version=" + version;
        System.out.println("版本更新 = " + PATH);
        HttpxUtils.Get(PATH,
                null,
                new Callback.CommonCallback<String>() {
                    @SuppressLint("WrongConstant")
                    @Override
                    public void onSuccess(String result) {
                        version_result = result;
                        System.out.println("版本更新 = " + result);
                        final CheckVersion checkVersion = GsonUtil.gsonIntance().gsonToBean(result, CheckVersion.class);
                        final String address = checkVersion.getData().getUpdateurl();

                        if (checkVersion.getStatus() == 1) {

                            final AlertDialog dlg = new AlertDialog.Builder(mainActivity).create();
                            dlg.show();
                            Window window = dlg.getWindow();
                            window.setGravity(Gravity.CENTER);//设置弹框在屏幕的下方
                            window.setContentView(R.layout.custom_dialog_two_layout);

                            //设置弹框的高为屏幕的一半宽是屏幕的宽
                            WindowManager windowManager = mainActivity.getWindowManager();
                            Display display = windowManager.getDefaultDisplay();
                            WindowManager.LayoutParams lp = dlg.getWindow().getAttributes();
                            lp.width = (int) (display.getWidth() * 0.7); //设置宽度
                            lp.height = (int) (display.getHeight() * 0.5); //设置宽度
                            dlg.getWindow().setAttributes(lp);

                            tv_now = dlg.findViewById(R.id.tv_cv_now);
                            tv_new = dlg.findViewById(R.id.tv_cv_new);
                            tv_msg = dlg.findViewById(R.id.tv_msg);
                            but_cancle = dlg.findViewById(R.id.but_cv_cancle);
                            but_shichang = dlg.findViewById(R.id.but_cv_shichang);
                            but_update = dlg.findViewById(R.id.btn_update);

                            tv_now.setText("当前版本：" + version);
                            tv_new.setText("最新版本：" + checkVersion.getData().getServerVersion());
                            tv_msg.setText("" + checkVersion.getData().getUpgradeinfo());

                            but_cancle.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    dlg.dismiss();
                                }
                            });

                            but_shichang.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                     /*市场更新*/
                                     dlg.dismiss();
                                    gotoMarket(mainActivity, mainActivity.getPackageName());
                                }
                            });

                            but_update.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    /*立即更新*/
                                    dlg.dismiss();
                                    VersionParams.Builder builder = new VersionParams.Builder()
                                            .setRequestUrl("http://www.baidu.com")
                                            .setService(UVService.class);
                                    stopService(new Intent(mainActivity, UVService.class));
                                    AllenChecker.startVersionCheck(mainActivity, builder.build());

//                                    VersionParams.Builder builder = new VersionParams.Builder()
//                                            .setRequestUrl(checkVersion.getData().getUpdateurl())
//                                            .setDownloadAPKPath(getApplicationContext().getFilesDir() + "/")
//                                            .setService(UVService.class);
//
//                                    stopService(new Intent(mainActivity, UVService.class));
//
//                                    if (!address.isEmpty())
//                                        builder.setDownloadAPKPath(address);
//                                    //更新界面选择
//                                    CustomVersionDialogActivity.customVersionDialogIndex = 3;
//                                    builder.setCustomDownloadActivityClass(VersionDialogActivity.class);
//                                    //下载界面选择
//                                    CustomVersionDialogActivity.isCustomDownloading = false;
//                                    builder.setCustomDownloadActivityClass(VersionDialogActivity.class);
//                                    //强制更新
//                                    CustomVersionDialogActivity.isForceUpdate = false;
//                                    builder.setCustomDownloadActivityClass(CustomVersionDialogActivity.class);
//
//                                    //强制重新下载
//                                    builder.setForceRedownload(true);
//                                    //是否显示通知栏
//                                    builder.setShowNotification(true);
//                                    builder.setShowDownloadingDialog(true);
//
//                                    AllenChecker.startVersionCheck(mainActivity, builder.build());
                                }
                            });

                        }

                    }

                    @SuppressLint("WrongConstant")
                    @Override
                    public void onError(Throwable ex, boolean isOnCallback) {
                        System.out.println("版本更新1 = " + version_result);
                        AddrReturn addrReturn = GsonUtil.gsonIntance().gsonToBean(version_result, AddrReturn.class);
                        if (addrReturn.getStatus() == 0) {
                            System.out.println("" + addrReturn.getData());
                            //Toast.makeText(mainActivity, "" + addrReturn.getData(), Toast.LENGTH_SHORT).show();
                        }

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
     * 打开市场
     */
    @SuppressLint("WrongConstant")
    public static void gotoMarket(Context context, String pck) {
        if (!isHaveMarket(context)) {
            Toast.makeText(context, "您手机中没有安装应用市场！", Toast.LENGTH_SHORT).show();
            return;
        }
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_VIEW);
        intent.setData(Uri.parse("market://details?id=" + pck));
        if (intent.resolveActivity(context.getPackageManager()) != null) {
            context.startActivity(intent);
        }
    }

    /**
     * 是否安装市场
     */
    private static boolean isHaveMarket(Context context) {
        Intent intent = new Intent();
        intent.setAction("android.intent.action.MAIN");
        intent.addCategory("android.intent.category.APP_MARKET");
        PackageManager pm = context.getPackageManager();
        List<ResolveInfo> infos = pm.queryIntentActivities(intent, 0);
        return infos.size() > 0;
    }

    /*版本更新 依赖update_version*/
//    public void getVersion(View view) {
//        //you can add your request params and request method
//        //eg.
//        //只有requsetUrl service 是必须值 其他参数都有默认值，可选
//
////        com.allenliu.versionchecklib.core.http.HttpHeaders headers=new com.allenliu.versionchecklib.core.http.HttpHeaders();
////        headers.put("a","b");
//        VersionParams.Builder builder = new VersionParams.Builder()
////                .setHttpHeaders(headers)
////                .setRequestMethod(requestMethod)
////                .setRequestParams(httpParams)
//                .setRequestUrl("http://www.baidu.com")
////                .setDownloadAPKPath(getApplicationContext().getFilesDir()+"/")
//                .setService(UVService.class);
//
//        stopService(new Intent(this, UVService.class));
//        switch (view.getId()) {
//            case R.id.sendbtn:
//                String pauseTime = etPauseTime.getText().toString();
//                String address = etAddress.getText().toString();
//                try {
//                    if (!pauseTime.isEmpty() && Long.valueOf(pauseTime) > 0) {
//                        builder.setPauseRequestTime(Long.valueOf(pauseTime));
//                    }
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
//                if (!address.isEmpty())
//                    builder.setDownloadAPKPath(address);
//                //更新界面选择
//                switch (radioGroup.getCheckedRadioButtonId()) {
//                    case R.id.btn1:
//                        CustomVersionDialogActivity.customVersionDialogIndex = 3;
//                        builder.setCustomDownloadActivityClass(VersionDialogActivity.class);
//                        break;
//                    case R.id.btn2:
//                        CustomVersionDialogActivity.customVersionDialogIndex = 1;
//                        builder.setCustomDownloadActivityClass(CustomVersionDialogActivity.class);
//                        break;
//                    case R.id.btn3:
//                        CustomVersionDialogActivity.customVersionDialogIndex = 2;
//                        builder.setCustomDownloadActivityClass(CustomVersionDialogActivity.class);
//                        break;
//                }
//                //下载进度界面选择
//                switch (radioGroup2.getCheckedRadioButtonId()) {
//                    case R.id.btn21:
//                        //同理
//                        CustomVersionDialogActivity.isCustomDownloading = false;
//                        builder.setCustomDownloadActivityClass(VersionDialogActivity.class);
//                        break;
//                    case R.id.btn22:
//                        //可以看到 更改更新界面或者是更改下载界面都是重写VersionDialogActivity
//                        CustomVersionDialogActivity.isCustomDownloading = true;
//                        builder.setCustomDownloadActivityClass(CustomVersionDialogActivity.class);
//                        break;
//                }
//                //强制更新
//                if (forceUpdateCheckBox.isChecked()) {
//                    CustomVersionDialogActivity.isForceUpdate = true;
//                    builder.setCustomDownloadActivityClass(CustomVersionDialogActivity.class);
//                } else {
//                    //同理
//                    CustomVersionDialogActivity.isForceUpdate = false;
//                    builder.setCustomDownloadActivityClass(CustomVersionDialogActivity.class);
//                }
//                //静默下载
//                if (silentDownloadCheckBox.isChecked()) {
//                    builder.setSilentDownload(true);
//                } else {
//                    builder.setSilentDownload(false);
//                }
//                //强制重新下载
//                if (forceDownloadCheckBox.isChecked()) {
//                    builder.setForceRedownload(true);
//                } else {
//                    builder.setForceRedownload(false);
//                }
//                //是否仅使用下载功能
//                if (onlyDownloadCheckBox.isChecked()) {
//                    //如果仅使用下载功能，downloadUrl是必须的
//                    builder.setOnlyDownload(true)
//                            .setDownloadUrl("http://test-1251233192.coscd.myqcloud.com/1_1.apk")
//                            .setTitle("检测到新版本")
//                            .setUpdateMsg(getString(R.string.updatecontent));
//                } else
//                    builder.setOnlyDownload(false);
//                //是否显示通知栏
//                if (showNotificationCheckBox.isChecked()) {
//                    builder.setShowNotification(true);
//                } else
//                    builder.setShowNotification(false);
//                if (showDownloadingCheckBox.isChecked()) {
//                    builder.setShowDownloadingDialog(true);
//                } else
//                    builder.setShowDownloadingDialog(false);
//
//                AllenChecker.startVersionCheck(this, builder.build());
//                break;
//
//            case R.id.cancelBtn:
//                AllenChecker.cancelMission();
//                VersionParams.Builder builder2 = new VersionParams.Builder();
//                builder2.setOnlyDownload(true)
//                        .setDownloadUrl("http://test-1251233192.coscd.myqcloud.com/1_1.apk")
//                        .setTitle("检测到新版本")
//                        .setForceRedownload(true)
//                        .setUpdateMsg(getString(R.string.updatecontent));
//                AllenChecker.startVersionCheck(this, builder2.build());
//                break;
//        }
//    }


    //双击的时间间隔
    private long millis = 0;

    /**
     * 监听返回键 双击退出程序
     *
     * @param keyCode
     * @param event
     * @return
     */
    @SuppressLint("WrongConstant")
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            //双击退出
            if (System.currentTimeMillis() - millis < 1000) {
                return super.onKeyDown(keyCode, event);
            } else {
                Toast.makeText(this, "再次点击退出程序", Toast.LENGTH_SHORT).show();
                millis = System.currentTimeMillis();
                return false;
            }

        }
        return false;
    }


}