package com.dq.huibao.homepage;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Display;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.dq.huibao.Interface.OnItemClickListener;
import com.dq.huibao.R;
import com.dq.huibao.adapter.index.AppimglistAdapter;
import com.dq.huibao.adapter.index.GoodsListAdapter;
import com.dq.huibao.adapter.index.MenuAdapter;
import com.dq.huibao.base.BaseFragment;
import com.dq.huibao.bean.account.Login;
import com.dq.huibao.bean.index.Index;
import com.dq.huibao.bean.memcen.Sign;
import com.dq.huibao.bean.memcen.SignIndex;
import com.dq.huibao.lunbotu.ADInfo;
import com.dq.huibao.lunbotu.CycleViewPager;
import com.dq.huibao.lunbotu.ViewFactory;
import com.dq.huibao.refresh.PullToRefreshView;
import com.dq.huibao.ui.GoodsDetailsActivity;
import com.dq.huibao.ui.GoodsListActivity;
import com.dq.huibao.ui.KeywordsActivity;
import com.dq.huibao.ui.LoginActivity;
import com.dq.huibao.ui.homepage.WebActivity;
import com.dq.huibao.ui.memcen.SignRuleActivity;
import com.dq.huibao.utils.BaseRecyclerViewHolder;
import com.dq.huibao.utils.CodeUtils;
import com.dq.huibao.utils.GsonUtil;
import com.dq.huibao.utils.HttpPath;
import com.dq.huibao.utils.ImageUtils;
import com.dq.huibao.utils.MD5Util;
import com.dq.huibao.utils.NetworkUtils;
import com.dq.huibao.utils.SPUserInfo;
import com.nostra13.universalimageloader.cache.disc.naming.Md5FileNameGenerator;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 首页
 * Created by jingang on 2018/1/10.
 */

public class FMHomePageOlds extends BaseFragment implements
        PullToRefreshView.OnHeaderRefreshListener,
        PullToRefreshView.OnFooterRefreshListener {
    @Bind(R.id.rv_hp_menu)
    RecyclerView rvHpMenu;
    @Bind(R.id.rv_hp_appimglist)
    RecyclerView rvHpAppimglist;
    @Bind(R.id.rv_hp_glist)
    RecyclerView rvHpGlist;
    @Bind(R.id.lin_hp_search)
    LinearLayout linHpSearch;
    @Bind(R.id.ptrv_hp)
    PullToRefreshView pullToRefreshView;

    private View view;
    /*接口地址*/
    private String PATH = "", MD5_PATH = "";
    private RequestParams params = null;

    /*解析数据结构
    * 搜索 banner menu 图片广告 热门商品列表*/
    private Index index;
    private List<Index.DataBean.BannerBean> bannerList = new ArrayList<>();
    private List<Index.DataBean.MenuBean> menuList = new ArrayList<>();
    private List<Index.DataBean.AppimglistBean> appimgList = new ArrayList<>();
    private List<Index.DataBean.GlistBean> gList = new ArrayList<>();

    private MenuAdapter menuAdapter;
    private AppimglistAdapter appimglistAdapter;
    GridLayoutManager mManager;
    private GAdapter gAdapter;

    /*轮播图*/
    private CycleViewPager cycleViewPager;
    private List<ImageView> views = new ArrayList<ImageView>();
    private List<ADInfo> infos;
    private ADInfo info;

    private Intent intent;

    GridLayoutManager gmMenu = null, gmImg = null, gmGlist = null;

    private SPUserInfo spUserInfo;
    private String phone = "", token = "";

    /*dialog*/
    private TextView tv_sign, tv_sign_rule, tv_sign_days;

    private Boolean cansign = false;
    private String cur_count = "", cur_money = "";

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_homepage, null);
        ButterKnife.bind(this, view);

        gmMenu = new GridLayoutManager(getActivity(), 5) {
            @Override
            public boolean canScrollVertically() {
                return false;
            }
        };

        rvHpMenu.setLayoutManager(new GridLayoutManager(getActivity(), 5, GridLayoutManager.VERTICAL, false));
        //rvHpMenu.setLayoutManager(gmMenu);
        menuAdapter = new MenuAdapter(getActivity(), menuList);
        rvHpMenu.setAdapter(menuAdapter);
        menuAdapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                setIntent(menuList.get(position).getType(),
                        menuList.get(position).getTitle(),
                        menuList.get(position).getContent());
            }
        });


        gmImg = new GridLayoutManager(getActivity(), 4) {
            @Override
            public boolean canScrollVertically() {
                return false;
            }
        };

        appimglistAdapter = new AppimglistAdapter(getActivity(), appimgList);
        mManager = new GridLayoutManager(getActivity(), 4, GridLayoutManager.VERTICAL, false);
        //rvHpAppimglist.setLayoutManager(gmImg);
        mManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                String imgwidth = appimgList.get(position).getWidth();
                if (imgwidth.equals("50")) {
                    return 2;
                } else if (imgwidth.equals("100")) {
                    return 4;
                } else if (imgwidth.equals("25")) {
                    return 1;
                }
                return 1;
            }
        });
        rvHpAppimglist.setLayoutManager(mManager);
        rvHpAppimglist.setAdapter(appimglistAdapter);

        appimglistAdapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                setIntent(appimgList.get(position).getType(),
                        appimgList.get(position).getTitle(),
                        appimgList.get(position).getContent());
            }
        });


        gmGlist = new GridLayoutManager(getActivity(), 1) {
            @Override
            public boolean canScrollVertically() {
                return false;
            }
        };

        //rvHpGlist.setLayoutManager(gmGlist);
        rvHpGlist.setLayoutManager(new GridLayoutManager(getActivity(), 1, GridLayoutManager.VERTICAL, false));
        gAdapter = new GAdapter(getActivity(), gList);
        rvHpGlist.setAdapter(gAdapter);

//        rvHpMenu.setNestedScrollingEnabled(false);
//        rvHpAppimglist.setNestedScrollingEnabled(false);
//        rvHpAppimglist.setNestedScrollingEnabled(false);

        if (isNetworkUtils()) {
            getIndex();
        } else {
            toast("无网络连接");
        }

        pullToRefreshView.setOnHeaderRefreshListener(this);
        pullToRefreshView.setOnFooterRefreshListener(this);
        pullToRefreshView.setLastUpdated(new Date().toLocaleString());

        return view;
    }

    @Override
    protected void lazyLoad() {

    }

    /**
     * 获取首页信息
     * <p>
     * -url链接
     * article文章
     * cate分类
     * goods商品
     * custom自定义分类
     * articlecate文章分类
     * search  搜索
     * url # 不做操作
     */
    public void getIndex() {
        PATH = HttpPath.PATHS + HttpPath.INDEXT_INDEX;
        params = new RequestParams(PATH);
        System.out.println("首页 = " + PATH);
        x.http().get(params,
                new Callback.CommonCallback<String>() {
                    @Override
                    public void onSuccess(String result) {
                        System.out.println("首页 = " + result);
                        index = GsonUtil.gsonIntance().gsonToBean(result, Index.class);

                        bannerList.clear();
                        menuList.clear();
                        appimgList.clear();
                        gList.clear();

                        if (!index.getData().getBanner().toString().equals("[]")) {
                            bannerList = index.getData().getBanner();
                            //轮播图 banner
                            configImageLoader();

                            setLunbotu();

                        }
                        if (!index.getData().getMenu().toString().equals("[]")) {
                            //按钮 menu
                            menuList.addAll(index.getData().getMenu());
                            menuAdapter.notifyDataSetChanged();
                        }
                        if (!index.getData().getAppimglist().toString().equals("[]")) {
                            //图片组
                            appimgList.addAll(index.getData().getAppimglist());
                            appimglistAdapter.notifyDataSetChanged();
                        }
                        if (!index.getData().getGlist().toString().equals("[]")) {
                            gList.addAll(index.getData().getGlist());
                            gAdapter.notifyDataSetChanged();
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
     * 设置轮播图
     */
    public void setLunbotu() {
        cycleViewPager = (CycleViewPager) getActivity().getFragmentManager().findFragmentById(R.id.fragment_cycle_viewpager_content);

        infos = new ArrayList<>();
        for (int i = 0; i < bannerList.size(); i++) {
            info = new ADInfo();
            info.setUrl(HttpPath.NEW_HEADER + bannerList.get(i).getThumb().toString());
            info.setContent("");
            info.setImg("");
            infos.add(info);
        }

        // 将最后一个ImageView添加进来
        views.add(ViewFactory.getImageView(getActivity(), infos.get(infos.size() - 1).getUrl()));
        for (int i = 0; i < infos.size(); i++) {
            views.add(ViewFactory.getImageView(getActivity(), infos.get(i).getUrl()));
        }
        // 将第一个ImageView添加进来
        views.add(ViewFactory.getImageView(getActivity(), infos.get(0).getUrl()));

        // 设置循环，在调用setData方法前调用
        cycleViewPager.setCycle(true);

        // 在加载数据前设置是否循环
        cycleViewPager.setData(views, infos, mAdCycleViewListener);
        //设置轮播
        cycleViewPager.setWheel(true);

        // 设置轮播时间，默认5000ms
        cycleViewPager.setTime(3000);
        //设置圆点指示图标组居中显示，默认靠右
        cycleViewPager.setIndicatorCenter();
    }

    /*轮播图点击事件*/
    private CycleViewPager.ImageCycleViewListener mAdCycleViewListener =
            new CycleViewPager.ImageCycleViewListener() {

                @SuppressLint("WrongConstant")
                @Override
                public void onImageClick(ADInfo info, int position, View imageView) {
                    if (cycleViewPager.isCycle()) {
                        setIntent(bannerList.get(position - 1).getType(),
                                bannerList.get(position - 1).getTitle(),
                                bannerList.get(position - 1).getContent());

                    }

                }

            };

    /**
     * 根据不同情况 跳转不同界面
     *
     * @param type
     * @param content
     */
    public void setIntent(String type, String title, String content) {
        switch (type) {
            case "url":
                //链接 web
                intent = new Intent(getActivity(), WebActivity.class);
                intent.putExtra("url", content);
                startActivity(intent);
                break;
            case "article":
                //文章
                break;
            case "cate":
                //分类（商品列表）
                intent = new Intent(getActivity(), GoodsListActivity.class);
                intent.putExtra("content", "cate=" + content);
                intent.putExtra("catename", title);
                intent.putExtra("keywords", "");
                startActivity(intent);
                break;
            case "goods":
                //商品详情
                intent = new Intent(getActivity(), GoodsDetailsActivity.class);
                intent.putExtra("gid", content);
                startActivity(intent);
                break;
            case "custom":
                //自定义分类
                intent = new Intent(getActivity(), GoodsListActivity.class);
                intent.putExtra("content", "cate=" + content);
                intent.putExtra("catename", title);
                intent.putExtra("keywords", "");
                startActivity(intent);
                break;
            case "articlecate":
                //文章分类
                break;
            case "search":
                //搜索
                intent = new Intent(getActivity(), GoodsListActivity.class);
                intent.putExtra("content", content);
                intent.putExtra("catename", title);
                intent.putExtra("keywords", "");
                startActivity(intent);
                break;
            case "#":
                //不做操作
                break;

            case "action":
                if (content.equals("sign")) {
                    if (isLogin()) {

                        getSignIndex(phone, token);

                        final AlertDialog dlg = new AlertDialog.Builder(getActivity()).create();
                        dlg.show();
                        Window window = dlg.getWindow();
                        window.setGravity(Gravity.CENTER);//设置弹框在屏幕的下方
                        window.setContentView(R.layout.dialog_sign);
                        tv_sign = window.findViewById(R.id.tv_sign);
                        tv_sign_rule = window.findViewById(R.id.tv_sign_rule);
                        tv_sign_days = window.findViewById(R.id.tv_sign_days);

//                        if (cansign) {
//                            tv_sign.setText("签到");
//                        } else {
//                            tv_sign.setText("已签到");
//                        }
//                        tv_sign_days.setText("" + cur_count + "天");

                        //设置弹框的高为屏幕的一半宽是屏幕的宽
                        WindowManager windowManager = getActivity().getWindowManager();
                        Display display = windowManager.getDefaultDisplay();
                        WindowManager.LayoutParams lp = dlg.getWindow().getAttributes();
                        lp.width = (int) (display.getWidth() * 0.7); //设置宽度
                        lp.height = (int) (display.getHeight() * 0.5); //设置宽度
                        dlg.getWindow().setAttributes(lp);

                        tv_sign.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                //toast("签到");
                                if (cansign) {
                                    setSign(phone, token);
                                } else {
                                    toast("已签到");
                                }

                            }
                        });

                        tv_sign_rule.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                intent = new Intent(getActivity(), SignRuleActivity.class);
                                intent.putExtra("phone", phone);
                                intent.putExtra("token", token);
                                startActivity(intent);

                                dlg.dismiss();
                            }
                        });
                    } else {
                        /*弹出框*/
                        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                        builder.setMessage("确定登录？");
                        builder.setTitle("提示：未登录");
                        builder.setPositiveButton("确认", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                                intent = new Intent(getActivity(), LoginActivity.class);
                                startActivityForResult(intent, CodeUtils.HOMEPAGE);


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

                break;

            default:
                break;
        }
    }

    /**
     * 配置ImageLoder
     */
    private void configImageLoader() {
        // 初始化ImageLoader
        @SuppressWarnings("deprecation")
        DisplayImageOptions options = new DisplayImageOptions.Builder().showStubImage(R.mipmap.icon_stub) // 设置图片下载期间显示的图片
                .showImageForEmptyUri(R.mipmap.icon_empty) // 设置图片Uri为空或是错误的时候显示的图片
                .showImageOnFail(R.mipmap.icon_error) // 设置图片加载或解码过程中发生错误显示的图片
                .cacheInMemory(true) // 设置下载的图片是否缓存在内存中
                .cacheOnDisc(true) // 设置下载的图片是否缓存在SD卡中
                // .displayer(new RoundedBitmapDisplayer(20)) // 设置成圆角图片
                .build(); // 创建配置过得DisplayImageOption对象

        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(getActivity().getApplicationContext()).defaultDisplayImageOptions(options)
                .threadPriority(Thread.NORM_PRIORITY - 2).denyCacheImageMultipleSizesInMemory()
                .discCacheFileNameGenerator(new Md5FileNameGenerator()).tasksProcessingOrder(QueueProcessingType.LIFO).build();
        ImageLoader.getInstance().init(config);
    }


    /**
     * 判断联网状态
     */
    public boolean isNetworkUtils() {
        if (NetworkUtils.isNotWorkAvilable(getActivity())) {
            if (NetworkUtils.getCurrentNetType(getActivity()) != null) {
                System.out.println("联网类型 = " + NetworkUtils.getCurrentNetType(getActivity()));
                return true;
            }
        } else {
            return false;
        }
        return false;
    }

    /*判断登录状态*/
    public boolean isLogin() {
        spUserInfo = new SPUserInfo(getActivity().getApplication());
        if (spUserInfo.getLogin().equals("1")) {
            if (spUserInfo.getLogin().equals("1")) {
                if (!(spUserInfo.getLoginReturn().equals(""))) {
                    Login login = GsonUtil.gsonIntance().gsonToBean(spUserInfo.getLoginReturn(), Login.class);
                    phone = login.getData().getPhone();
                    token = login.getData().getToken();
                }

            } else {
            }
            return true;
        } else if (spUserInfo.getLogin().equals("")) {
            return false;
        }
        return false;
    }


    /**
     * 获取签到信息
     *
     * @param phone
     * @param token
     */
    public void getSignIndex(String phone, String token) {
        MD5_PATH = "phone=" + phone + "&timestamp=" + (System.currentTimeMillis() / 1000) + "&token=" + token;
        PATH = HttpPath.PATHS + HttpPath.ACTIVITYSIGN_INDEX + MD5_PATH + "&sign=" +
                MD5Util.getMD5String(MD5_PATH + HttpPath.KEY);
        params = new RequestParams(PATH);
        System.out.println("签到信息 = " + PATH);
        x.http().get(params,
                new Callback.CommonCallback<String>() {
                    @Override
                    public void onSuccess(String result) {
                        System.out.println("签到信息 = " + result);
                        SignIndex signIndex = GsonUtil.gsonIntance().gsonToBean(result, SignIndex.class);
                        if (signIndex.getStatus() == 1) {
                            cansign = signIndex.getData().isCansign();
                            cur_count = signIndex.getData().getCur_count();
                            cur_money = signIndex.getData().getCur_money();

                            if (cansign) {
                                tv_sign.setText("签到");
                            } else {
                                tv_sign.setText("已签到");
                            }
                            tv_sign_days.setText("" + cur_count + "天");

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
     * 签到
     *
     * @param phone
     * @param token
     */
    public void setSign(String phone, String token) {
        MD5_PATH = "phone=" + phone + "&timestamp=" + (System.currentTimeMillis() / 1000) + "&token=" + token;
        PATH = HttpPath.PATHS + HttpPath.ACTIVITY_SIGN + MD5_PATH + "&sign=" +
                MD5Util.getMD5String(MD5_PATH + HttpPath.KEY);

        params = new RequestParams(PATH);
        System.out.println("签到 = " + PATH);
        x.http().post(params,
                new Callback.CommonCallback<String>() {
                    @Override
                    public void onSuccess(String result) {
                        System.out.println("签到 = " + result);
                        Sign sign = GsonUtil.gsonIntance().gsonToBean(result, Sign.class);
                        if (sign.getStatus() == 1) {
                            toast("" + sign.getData().getMsg());
                            tv_sign.setText("已签到");
                        } else if (sign.getStatus() == 0) {
                            toast("" + sign.getData().getMsg());
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

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    @OnClick({R.id.lin_hp_search})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.lin_hp_search:
                intent = new Intent(getActivity(), KeywordsActivity.class);
                startActivity(intent);
                break;

//            case R.id.edit_sreach:
//                intent = new Intent(getActivity(), KeywordsActivity.class);
//                startActivity(intent);
//                break;

            default:
                break;
        }
    }

    @Override
    public void onHeaderRefresh(PullToRefreshView view) {
        pullToRefreshView.postDelayed(new Runnable() {
            @Override
            public void run() {
                //刷新数据
                pullToRefreshView.onHeaderRefreshComplete("更新于:"
                        + Calendar.getInstance().getTime().toLocaleString());
                pullToRefreshView.onHeaderRefreshComplete();

                if (isNetworkUtils()) {
                    //getIndex();
                } else {
                    toast("无网络连接");
                }

            }

        }, 1000);
    }

    @Override
    public void onFooterRefresh(PullToRefreshView view) {
        pullToRefreshView.postDelayed(new Runnable() {

            @Override
            public void run() {
                //加载更多数据
                pullToRefreshView.onFooterRefreshComplete();

            }

        }, 1000);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == CodeUtils.HOMEPAGE) {
            if (resultCode == CodeUtils.LOGIN) {
                isLogin();
            }
        }
    }

    /**
     *
     */
    public class GAdapter extends RecyclerView.Adapter<GAdapter.MyViewHolder> {
        private Context mContext;
        private List<Index.DataBean.GlistBean> glistBeanList;
        private OnItemClickListener onItemClickListener;

        public GAdapter(Context mContext, List<Index.DataBean.GlistBean> glistBeanList) {
            this.mContext = mContext;
            this.glistBeanList = glistBeanList;
        }

        public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
            this.onItemClickListener = onItemClickListener;
        }

        @Override
        public MyViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
            MyViewHolder vh = new MyViewHolder(
                    LayoutInflater.from(mContext).inflate(R.layout.item_glist, viewGroup, false)
            );
            return vh;
        }

        @Override
        public void onBindViewHolder(final MyViewHolder holder, final int i) {
            if (onItemClickListener != null) {
                holder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        int position = holder.getLayoutPosition(); // 1
                        onItemClickListener.onItemClick(holder.itemView, position); // 2
                    }
                });

            }
            ImageUtils.loadIntoUseFitWidth(mContext,
                    HttpPath.NEW_HEADER + glistBeanList.get(i).getThumb(),
                    R.mipmap.icon_empty001,
                    R.mipmap.icon_error001,
                    holder.img);


            holder.recyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 2, GridLayoutManager.VERTICAL, false));
            final GoodsListAdapter goodsListAdapter = new GoodsListAdapter(mContext, glistBeanList.get(i).getGoodslist());
            holder.recyclerView.setAdapter(goodsListAdapter);

            goodsListAdapter.setOnItemClickListener(new OnItemClickListener() {
                @Override
                public void onItemClick(View view, int position) {
                    intent = new Intent(mContext, GoodsDetailsActivity.class);
                    intent.putExtra("gid", glistBeanList.get(i).getGoodslist().get(position).getId());
                    startActivity(intent);
                }
            });


        }

        @Override
        public int getItemCount() {
            return glistBeanList.size();
        }

        public class MyViewHolder extends BaseRecyclerViewHolder {
            private ImageView img;
            private RecyclerView recyclerView;

            public MyViewHolder(View view) {
                super(view);
                img = (ImageView) view.findViewById(R.id.iv_hp_glist);
                recyclerView = (RecyclerView) view.findViewById(R.id.rv_hp_glist);
            }
        }
    }
}
