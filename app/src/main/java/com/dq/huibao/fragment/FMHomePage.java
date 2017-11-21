package com.dq.huibao.fragment;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;

import com.dq.huibao.Interface.OnItemClickListener;
import com.dq.huibao.R;
import com.dq.huibao.adapter.HpCubeAdapter;
import com.dq.huibao.adapter.HpGoodsAdapter;
import com.dq.huibao.adapter.HpMenuAdapter;
import com.dq.huibao.base.BaseFragment;
import com.dq.huibao.bean.homepage.Banner;
import com.dq.huibao.bean.homepage.Cube;
import com.dq.huibao.bean.homepage.Goods;
import com.dq.huibao.bean.homepage.Menu;
import com.dq.huibao.bean.homepage.Notice;
import com.dq.huibao.bean.homepage.Picture;
import com.dq.huibao.bean.homepage.Root;
import com.dq.huibao.bean.homepage.Search;
import com.dq.huibao.lunbotu.ADInfo;
import com.dq.huibao.lunbotu.CycleViewPager;
import com.dq.huibao.lunbotu.ViewFactory;
import com.dq.huibao.ui.GoodsDetailsActivity;
import com.dq.huibao.ui.GoodsListActivity;
import com.dq.huibao.ui.homepage.WebActivity;
import com.dq.huibao.utils.GsonUtil;
import com.dq.huibao.utils.HttpUtils;
import com.dq.huibao.utils.ImageUtils;
import com.dq.huibao.view.MarqueTextView;
import com.dq.huibao.view.PullToRefreshLayout;
import com.nostra13.universalimageloader.cache.disc.naming.Md5FileNameGenerator;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Description：首页
 * Created by jingang on 2017/10/16.
 */

public class FMHomePage extends BaseFragment{
    @Bind(R.id.lin_homepage)
    LinearLayout linHomepage;
    //    @Bind(R.id.sv_homepage)
//    ScrollView svHomepage;
    private View view;

    private List<ImageView> views = new ArrayList<ImageView>();
    private List<ADInfo> infos;
    private ADInfo info;

    private String urlstr = "";//跳转web地址

    private List<Banner.DataBeanX.DataBean> bannerList = new ArrayList<>();

    /*接收页面传值*/
    private Intent intent;

    /*接口地址*/
    private String PATH = "";

    RequestParams params = null;

    /*根*/
    private Root root;

    private LinearLayout lin_search = null, lin_banner = null, lin_menu = null,
            lin_notice = null;

    /*动态添加控件下标*/
    private int index_menu = 0, index_picture = 0, index_cube = 0,
            index_goods = 0;

    private CycleViewPager cycleViewPager;
    private RecyclerView recyclerView;
    private MarqueTextView tv_notice;

    /*搜索*/
    private EditText et_search;
    private ImageView iv_search;
    private String UTF_keywords = "";

//    @Bind(R.id.pullToRefreshLayout)
//    public PullToRefreshLayout pullToRefreshLayout;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fm_homepage_test, null);

        ButterKnife.bind(this, view);
        //init();
        getHomePage("1604");


        return view;
    }

//    private void init() {
//        pullToRefreshLayout.setOnFooterRefreshListener(this);
//        pullToRefreshLayout.setOnHeaderRefreshListener(this);
//        pullToRefreshLayout.setLastUpdated(new Date().toLocaleString());
//
//    }

    /**
     * 获取首页数据
     *
     * @param i 店铺id
     */
    public void getHomePage(String i) {
        PATH = HttpUtils.PATH + HttpUtils.HP_ROOT + "i=" + i;

        System.out.println("首页数据" + PATH);

        params = new RequestParams(PATH);
        x.http().get(params,
                new Callback.CommonCallback<String>() {
                    @Override
                    public void onSuccess(String result) {
                        System.out.println("首页数据 = " + result);
                        root = GsonUtil.gsonIntance().gsonToBean(result, Root.class);

                        setFor(root);

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
     * 根据temp动态生成UI
     *
     * @param root
     */
    public void setFor(Root root) {
        for (int i = 0; i < root.getData().size(); i++) {
            setTemp(root.getData().get(i).getId(), root.getData().get(i).getTemp());
//            try {
//                Thread.sleep(2 * 100);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
        }
    }

    /**
     * 根据temp获取各类别的数据
     *
     * @param temp
     */
    public void setTemp(String id, final String temp) {
        switch (temp) {
            case "search":
                //System.out.println("添加search");
                lin_search = (LinearLayout) getActivity().getLayoutInflater().inflate(R.layout.include_hp_search, null);
                linHomepage.addView(lin_search);

                et_search = (EditText) lin_search.findViewById(R.id.et_hp_sreach);
                iv_search = (ImageView) lin_search.findViewById(R.id.iv_hp_sreach);

                params = new RequestParams(HttpUtils.PATH + HttpUtils.HP_ROOT + "i=1604" + "&id=" + id);
                x.http().get(params,
                        new Callback.CommonCallback<String>() {
                            @Override
                            public void onSuccess(String result) {
                                // System.out.println(temp + " = " + result);

                                final Search search = GsonUtil.gsonIntance().gsonToBean(result, Search.class);

                                iv_search.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View view) {
//                                        setIntent(search.getData().getParams().getSearchurl().isSelfurl(),
//                                                search.getData().getParams().getSearchurl().getDoX(),
//                                                search.getData().getParams().getSearchurl().getP(),
//                                                search.getData().getParams().getSearchurl().getUrlstr(),
//                                                search.getData().getParams().getSearchurl().getQuery());

                                        intent = new Intent(getActivity(), GoodsListActivity.class);
                                        intent.putExtra("pcate", "");
                                        intent.putExtra("ccate", "");
                                        intent.putExtra("name", "全部商品");
                                        intent.putExtra("keywords", et_search.getText().toString());

                                        et_search.setText("");

                                        startActivity(intent);
                                    }
                                });


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

                break;

            case "banner":
                //System.out.println("添加banner");

                lin_banner = (LinearLayout) getActivity().getLayoutInflater().inflate(R.layout.include_hp_lunbotu, null);
                linHomepage.addView(lin_banner);

                cycleViewPager = (CycleViewPager) getActivity().getFragmentManager().findFragmentById(R.id.fragment_cycle_viewpager_content);

                params = new RequestParams(HttpUtils.PATH + HttpUtils.HP_ROOT + "i=1604" + "&id=" + id);
                x.http().get(params,
                        new Callback.CommonCallback<String>() {
                            @Override
                            public void onSuccess(String result) {
                                //System.out.println(temp + " = " + result);

                                configImageLoader();
                                Banner banner = GsonUtil.gsonIntance().gsonToBean(result, Banner.class);

                                bannerList = banner.getData().getData();

                                infos = new ArrayList<>();
                                for (int i = 0; i < banner.getData().getData().size(); i++) {
                                    info = new ADInfo();
                                    info.setUrl(banner.getData().getData().get(i).getImgurl());
                                    info.setContent(banner.getData().getData().get(i).getSysurl());
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


                break;

            case "menu":
                //System.out.println("添加menu");
                index_menu++;

                final RecyclerView rv = new RecyclerView(getActivity());
                rv.setId(index_menu);

                linHomepage.addView(rv);


                lin_menu = (LinearLayout) getActivity().getLayoutInflater().inflate(R.layout.include_hp_menu, null);
                linHomepage.addView(lin_menu);

                recyclerView = (RecyclerView) lin_menu.findViewById(R.id.rv_hp_menu);

                params = new RequestParams(HttpUtils.PATH + HttpUtils.HP_ROOT + "i=1604" + "&id=" + id);
                x.http().get(params,
                        new Callback.CommonCallback<String>() {
                            @Override
                            public void onSuccess(String result) {
                                //System.out.println(temp + " = " + result);

                                final Menu menu = GsonUtil.gsonIntance().gsonToBean(result, Menu.class);

                                HpMenuAdapter hpMenuAdapter = new HpMenuAdapter(getActivity(), menu.getData().getData());

                                rv.setLayoutManager(new LinearLayoutManager(getActivity()));
                                rv.setLayoutManager(new GridLayoutManager(getActivity(), 5, GridLayoutManager.VERTICAL, false));

                                rv.setAdapter(hpMenuAdapter);

                                hpMenuAdapter.setOnItemClickListener(new OnItemClickListener() {
                                    @Override
                                    public void onItemClick(View view, int position) {
                                        //toast("" + menu.getData().getData().get(position).getText());
                                        setIntent(menu.getData().getData().get(position).getHrefurl().isSelfurl(),
                                                menu.getData().getData().get(position).getHrefurl().getDoX(),
                                                menu.getData().getData().get(position).getHrefurl().getP(),
                                                menu.getData().getData().get(position).getHrefurl().getUrlstr(),
                                                menu.getData().getData().get(position).getHrefurl().getQuery());

                                    }
                                });

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

                break;

            case "notice":
                //System.out.println("添加notice");
                lin_notice = (LinearLayout) getActivity().getLayoutInflater().inflate(R.layout.include_hp_notice, null);
                linHomepage.addView(lin_notice);

                tv_notice = (MarqueTextView) lin_notice.findViewById(R.id.tv_hp_notice);

                params = new RequestParams(HttpUtils.PATH + HttpUtils.HP_ROOT + "i=1604" + "&id=" + id);
                x.http().get(params,
                        new Callback.CommonCallback<String>() {
                            @Override
                            public void onSuccess(String result) {
                                //System.out.println(temp + " = " + result);

                                final Notice notice = GsonUtil.gsonIntance().gsonToBean(result, Notice.class);

                                tv_notice.setText("" + notice.getData().getParams().getNotice() + "           " + notice.getData().getParams().getNotice());

                                tv_notice.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View view) {
                                        setIntent(notice.getData().getParams().getNoticehref().isSelfurl(),
                                                notice.getData().getParams().getNoticehref().getDoX(),
                                                notice.getData().getParams().getNoticehref().getP(),
                                                notice.getData().getParams().getNoticehref().getUrlstr(),
                                                notice.getData().getParams().getNoticehref().getQuery());
                                    }
                                });
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
                break;


            case "picture":
                //System.out.println("添加picture");
                index_picture++;

                final ImageView iv = new ImageView(getActivity());
                iv.setId(index_picture);

                linHomepage.addView(iv);

                params = new RequestParams(HttpUtils.PATH + HttpUtils.HP_ROOT + "i=1604" + "&id=" + id);
                x.http().get(params,
                        new Callback.CommonCallback<String>() {
                            @Override
                            public void onSuccess(String result) {
                                //System.out.println(temp + " = " + result);

                                final Picture picture = GsonUtil.gsonIntance().gsonToBean(result, Picture.class);

                                ImageUtils.loadIntoUseFitWidth(getActivity(),
                                        picture.getData().getData().get(0).getImgurl(),
                                        R.mipmap.icon_empty,
                                        R.mipmap.icon_error,
                                        iv);

                                iv.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View view) {
                                        setIntent(picture.getData().getData().get(0).getHrefurl().isSelfurl(),
                                                picture.getData().getData().get(0).getHrefurl().getDoX(),
                                                picture.getData().getData().get(0).getHrefurl().getP(),
                                                picture.getData().getData().get(0).getHrefurl().getUrlstr(),
                                                picture.getData().getData().get(0).getHrefurl().getQuery());
                                    }
                                });

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
                break;

            case "cube":
                //System.out.println("添加cube");

                final RecyclerView rv_cube = new RecyclerView(getActivity());
                rv_cube.setId(index_cube);
                linHomepage.addView(rv_cube);

                //getData("1604", id, "cube");
                params = new RequestParams(HttpUtils.PATH + HttpUtils.HP_ROOT + "i=1604" + "&id=" + id);
                x.http().get(params,
                        new Callback.CommonCallback<String>() {
                            @Override
                            public void onSuccess(String result) {
                                //System.out.println(temp + " = " + result);
                                final Cube cube = GsonUtil.gsonIntance().gsonToBean(result, Cube.class);

                                HpCubeAdapter hpCubeAdapter = new HpCubeAdapter(getActivity(), cube.getData().getParams().getLayout());

                                GridLayoutManager mManager;
                                mManager = new GridLayoutManager(getActivity(), 4, GridLayoutManager.VERTICAL, false);
                                mManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
                                    @Override
                                    public int getSpanSize(int position) {
                                        String imgwidth = cube.getData().getParams().getLayout().get(position).getCols();
                                        if (imgwidth.equals("1")) {
                                            return 1;
                                        } else if (imgwidth.equals("2")) {
                                            return 2;
                                        }
                                        return 1;
                                    }
                                });

                                rv_cube.setLayoutManager(mManager);

                                rv_cube.setAdapter(hpCubeAdapter);

                                hpCubeAdapter.setOnItemClickListener(new OnItemClickListener() {
                                    @Override
                                    public void onItemClick(View view, int position) {
                                        setIntent(cube.getData().getParams().getLayout().get(position).getUrl().isSelfurl(),
                                                cube.getData().getParams().getLayout().get(position).getUrl().getDoX(),
                                                cube.getData().getParams().getLayout().get(position).getUrl().getP(),
                                                cube.getData().getParams().getLayout().get(position).getUrl().getUrlstr(),
                                                cube.getData().getParams().getLayout().get(position).getUrl().getQuery());
                                    }
                                });

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
                break;

            case "goods":
                //System.out.println("添加goods");
                index_goods++;

                final RecyclerView rv_goods = new RecyclerView(getActivity());
                rv_goods.setId(index_goods);
                linHomepage.addView(rv_goods);

                params = new RequestParams(HttpUtils.PATH + HttpUtils.HP_ROOT + "i=1604" + "&id=" + id);
                x.http().get(params,
                        new Callback.CommonCallback<String>() {
                            @Override
                            public void onSuccess(String result) {
                                //System.out.println(temp + "  = " + result);

                                final Goods goods = GsonUtil.gsonIntance().gsonToBean(result, Goods.class);

                                HpGoodsAdapter hpGoodsAdapter = new HpGoodsAdapter(getActivity(), goods.getData().getData());

                                GridLayoutManager mManager;

                                mManager = new GridLayoutManager(getActivity(), 2, GridLayoutManager.VERTICAL, false);
                                mManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
                                    @Override
                                    public int getSpanSize(int position) {
                                        String imgwidth = goods.getData().getParams().getStyle();
                                        if (imgwidth.equals("49.5%")) {
                                            return 1;
                                        } else if (imgwidth.equals("100%")) {
                                            return 2;
                                        }
                                        return 1;
                                    }
                                });

                                rv_goods.setLayoutManager(mManager);
                                rv_goods.setAdapter(hpGoodsAdapter);

                                hpGoodsAdapter.setOnItemClickListener(new OnItemClickListener() {
                                    @Override
                                    public void onItemClick(View view, int position) {

                                        setIntent(goods.getData().getData().get(position).getGoodhref().isSelfurl(),
                                                goods.getData().getData().get(position).getGoodhref().getDoX(),
                                                goods.getData().getData().get(position).getGoodhref().getP(),
                                                goods.getData().getData().get(position).getGoodhref().getUrlstr(),
                                                goods.getData().getData().get(position).getGoodhref().getQuery());

                                    }
                                });


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
                break;

            default:
                break;

        }
    }

    /**
     * 根据不同条件 跳转不同界面
     *
     * @param isSelfurl
     * @param doX
     * @param pX
     * @param urlstr
     * @param query
     */
    public void setIntent(boolean isSelfurl, String doX, String pX, String urlstr, String query) {
        /*根据selfurl 判断跳转情况 false：网页 ture：activity*/
        if (isSelfurl == false) {
            //跳转网页
            intent = new Intent(getActivity(), WebActivity.class);
            intent.putExtra("url", urlstr);
            startActivity(intent);
        } else {
            //跳转页面
            // toast("根据判断跳转页面");

            /*根据do 判断跳转页面类型 shop  plugin*/
            if (doX.equals("shop")) {

                /*根据 p 判断 list detail*/
                if (pX.equals("list")) {
                    //商品列表
                    intent = new Intent(getActivity(), GoodsListActivity.class);
                    intent.putExtra("pcate", query);
                    intent.putExtra("ccate", "");
                    intent.putExtra("name", "");
                    intent.putExtra("keywords", "");
                    startActivity(intent);

                } else if (pX.equals("detail")) {
                    //商品详情
                    intent = new Intent(getActivity(), GoodsDetailsActivity.class);
                    intent.putExtra("gid", query);
                    startActivity(intent);

                } else {
                    toast("未知 pX = " + pX);
                }

            } else if (doX.equals("plugin")) {
                toast("不知道");
            } else {
                toast("未知 doX = " + doX);
            }

        }
    }

    /*轮播图点击事件*/
    private CycleViewPager.ImageCycleViewListener mAdCycleViewListener =
            new CycleViewPager.ImageCycleViewListener() {

                @SuppressLint("WrongConstant")
                @Override
                public void onImageClick(ADInfo info, int position, View imageView) {
                    int index = position - 1;
                    if (cycleViewPager.isCycle()) {

                        // System.out.println("lalala = " + bannerList.get(index).getHrefurl().isSelfurl());

                        /*根据selfurl 判断跳转情况 false：网页 ture：activity*/
                        if (bannerList.get(index).getHrefurl().isSelfurl() == false) {
                            //跳转网页
                            intent = new Intent(getActivity(), WebActivity.class);
                            intent.putExtra("url", bannerList.get(index).getHrefurl().getUrlstr());
                            startActivity(intent);
                        } else {
                            //跳转页面
                            //toast("根据判断跳转页面");
                            setIntent(bannerList.get(index).getHrefurl().isSelfurl(),
                                    bannerList.get(index).getHrefurl().getDoX(),
                                    bannerList.get(index).getHrefurl().getP(),
                                    bannerList.get(index).getHrefurl().getUrlstr(),
                                    bannerList.get(index).getHrefurl().getQuery());

                        }


                    }

                }

            };

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

    @Override
    protected void lazyLoad() {

    }

//    @Override
//    public void onHeaderRefresh(PullToRefreshLayout view) {
//
//        pullToRefreshLayout.postDelayed(new Runnable() {
//            @Override
//            public void run() {
//                //刷新数据
//                pullToRefreshLayout.onHeaderRefreshComplete("更新于:"
//                        + Calendar.getInstance().getTime().toLocaleString());
//                pullToRefreshLayout.onHeaderRefreshComplete();
//
//                //getHomePage("1604");
//
//            }
//
//        }, 1000);
//
//
//    }
//
//    @Override
//    public void onFooterRefresh(PullToRefreshLayout view) {
//        pullToRefreshLayout.postDelayed(new Runnable() {
//
//            @Override
//            public void run() {
//                //加载更多数据
//                pullToRefreshLayout.onFooterRefreshComplete();
//                //gridViewData.add(R.mipmap.ic_adai);
//                //gridViewAdapter.setData(gridViewData);
//                //getShopGoodss(str_latitude, str_longtitude, page);
//                //getLoadingShopGood(latitude, longtitude, page);
//
//            }
//
//        }, 1000);
//    }
}
