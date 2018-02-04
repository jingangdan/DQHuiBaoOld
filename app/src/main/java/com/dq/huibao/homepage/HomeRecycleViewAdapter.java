package com.dq.huibao.homepage;

import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.dq.huibao.Interface.HomePageInterface;
import com.dq.huibao.R;
import com.dq.huibao.adapter.index.AppimglistAdapter;
import com.dq.huibao.adapter.index.GListAdapter;
import com.dq.huibao.adapter.index.MenuAdapter;
import com.dq.huibao.bean.index.Index;
import com.dq.huibao.lunbotu.CycleViewPager;
import com.dq.huibao.rollpagerview.ImageLoopAdapter;
import com.dq.huibao.rollpagerview.OnItemClickListener;
import com.dq.huibao.rollpagerview.RollPagerView;
import com.dq.huibao.ui.KeywordsActivity;

import java.util.List;

/**
 * 首页
 * Created by jingang on 2018/02/01.
 */

public class HomeRecycleViewAdapter extends RecyclerView.Adapter {

    /**
     * 类型1：搜索
     */
    public static final int TYPE_SEARCG = 0;

    /**
     * 类型2：banner
     */
    public static final int TYPE_BANNER = 1;
    /**
     * 类型3：菜单
     */
    public static final int TYPE_MENU = 2;
    /**
     * 类型4：图片组
     */
    public static final int TYPE_IMGLIST = 3;
    /**
     * 类型5：商品列表
     */
    public static final int TYPE_GOODSLIST = 4;


    /**
     * 当前类型
     */
    public int currentType = TYPE_MENU;

    private final Context mContext;
    private List<Index.DataBean> dataList;
    /**
     * 以后用它来初始化布局
     */
    private final LayoutInflater mLayoutInflater;

    GridLayoutManager mManager;
    private Intent intent;
    private HomePageInterface hpInterface;

    public HomeRecycleViewAdapter(Context mContext, List<Index.DataBean> dataList) {
        this.mContext = mContext;
        this.dataList = dataList;

        //以后用它来初始化布局
        mLayoutInflater = LayoutInflater.from(mContext);

        mManager = null;
        intent = null;
    }

    public void setHpInterface(HomePageInterface hpInterface) {
        this.hpInterface = hpInterface;
    }

    /**
     * 相当于getView创建ViewHolder布局
     *
     * @param parent
     * @param viewType 当前的类型
     * @return
     */
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        if (viewType == TYPE_SEARCG) {
            return new SearchViewHolder(mContext, mLayoutInflater.inflate(R.layout.layout_search, parent, false));
        } else if (viewType == TYPE_BANNER) {
            return new BannerViewHolder(mContext, mLayoutInflater.inflate(R.layout.layout_banner, parent, false));
        } else if (viewType == TYPE_MENU) {
            return new MenuViewHolder(mContext, mLayoutInflater.inflate(R.layout.layout_menu, parent, false));
        } else if (viewType == TYPE_IMGLIST) {
            return new ImgListViewHolder(mContext, mLayoutInflater.inflate(R.layout.layout_imglist, parent, false));
        } else if (viewType == TYPE_GOODSLIST) {
            return new GoodsListViewHolder(mContext, mLayoutInflater.inflate(R.layout.layout_goodslist, parent, false));
        }
        return null;
    }

    /**
     * 相当于getView中的绑定数据模块
     *
     * @param holder
     * @param position
     */
    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (getItemViewType(position) == TYPE_SEARCG) {
            SearchViewHolder searchViewHolder = (SearchViewHolder) holder;
            searchViewHolder.setData();
        } else if (getItemViewType(position) == TYPE_BANNER) {
            BannerViewHolder bannerViewHolder = (BannerViewHolder) holder;
            bannerViewHolder.setData(dataList.get(0).getBanner());
        } else if (getItemViewType(position) == TYPE_MENU) {
            MenuViewHolder menuViewHolder = (MenuViewHolder) holder;
            menuViewHolder.setData(dataList.get(0).getMenu());
        } else if (getItemViewType(position) == TYPE_IMGLIST) {
            ImgListViewHolder imgListViewHolder = (ImgListViewHolder) holder;
            imgListViewHolder.setData(dataList.get(0).getAppimglist());
        } else if (getItemViewType(position) == TYPE_GOODSLIST) {
            GoodsListViewHolder goodsListViewHolder = (GoodsListViewHolder) holder;
            goodsListViewHolder.setData(dataList.get(0).getGlist());
        }
    }

    /**
     * 总共有多少个item
     *
     * @return
     */
    @Override
    public int getItemCount() {
        return 5;
    }

    /**
     * 得到类型
     */
    @Override
    public int getItemViewType(int position) {
        switch (position) {
            case TYPE_SEARCG:
                currentType = TYPE_SEARCG;
                break;
            case TYPE_BANNER:
                currentType = TYPE_BANNER;
                break;
            case TYPE_MENU:
                currentType = TYPE_MENU;
                break;
            case TYPE_IMGLIST:
                currentType = TYPE_IMGLIST;
                break;
            case TYPE_GOODSLIST:
                currentType = TYPE_GOODSLIST;

                break;
        }
        return currentType;
    }

    class SearchViewHolder extends RecyclerView.ViewHolder {
        private final Context mContext;
        private LinearLayout linearLayout;
        private ImageView iv_search;

        public SearchViewHolder(Context mContext, View itemView) {
            super(itemView);
            this.mContext = mContext;
            linearLayout = itemView.findViewById(R.id.lin_hp_search);
            iv_search = itemView.findViewById(R.id.iv_hp_sreach);
        }

        public void setData() {
            linearLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    //暴露search接口
                    hpInterface.doSearch();
                }
            });

        }
    }

    class BannerViewHolder extends RecyclerView.ViewHolder {
        private Context mContext;
        RollPagerView rollPagerView;

        public BannerViewHolder(Context mContext, View itemView) {
            super(itemView);
            this.mContext = mContext;
            rollPagerView = itemView.findViewById(R.id.rollPagerView);

            DisplayMetrics dm = mContext.getResources().getDisplayMetrics();
            int width = dm.widthPixels;
            LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) rollPagerView.getLayoutParams();
            params.height = width / 3;//宽高比 1:3
            rollPagerView.setLayoutParams(params);
        }

        public void setData(final List<Index.DataBean.BannerBean> bannerBeans) {
            rollPagerView.setAdapter(new ImageLoopAdapter(rollPagerView, mContext, bannerBeans));
            rollPagerView.setOnItemClickListener(new OnItemClickListener() {
                @Override
                public void onItemClick(int position) {
                    //暴露banner接口
                    hpInterface.doHomePage(
                            position,
                            bannerBeans.get(position).getTitle(),
                            bannerBeans.get(position).getType(),
                            bannerBeans.get(position).getContent()
                    );
                }
            });
        }

    }

    class MenuViewHolder extends RecyclerView.ViewHolder {

        private final Context mContext;
        private RecyclerView recyclerView;

        public MenuViewHolder(Context mContext, View itemView) {
            super(itemView);
            this.mContext = mContext;
            recyclerView = (RecyclerView) itemView.findViewById(R.id.rv_menu);

        }

        public void setData(final List<Index.DataBean.MenuBean> menuBeans) {
            mManager = new GridLayoutManager(mContext, 5, GridLayoutManager.VERTICAL, false);
            recyclerView.setLayoutManager(mManager);
            final MenuAdapter menuAdapter = new MenuAdapter(mContext, menuBeans);
            recyclerView.setAdapter(menuAdapter);

            menuAdapter.setOnItemClickListener(new com.dq.huibao.Interface.OnItemClickListener() {
                @Override
                public void onItemClick(View view, int position) {
                    hpInterface.doHomePage(
                            position,
                            menuBeans.get(position).getTitle(),
                            menuBeans.get(position).getType(),
                            menuBeans.get(position).getContent()
                    );
                }
            });

        }
    }

    class ImgListViewHolder extends RecyclerView.ViewHolder {
        private Context mContext;
        private RecyclerView recyclerView;

        public ImgListViewHolder(Context mContext, View itemView) {
            super(itemView);
            this.mContext = mContext;
            recyclerView = (RecyclerView) itemView.findViewById(R.id.rv_imglist);
        }

        public void setData(final List<Index.DataBean.AppimglistBean> appimglistBeans) {

            mManager = new GridLayoutManager(mContext, 4, GridLayoutManager.VERTICAL, false);
            mManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
                @Override
                public int getSpanSize(int position) {
                    String imgwidth = appimglistBeans.get(position).getWidth();
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

            recyclerView.setLayoutManager(mManager);
            final AppimglistAdapter appimglistAdapter = new AppimglistAdapter(mContext, appimglistBeans);
            recyclerView.setAdapter(appimglistAdapter);

            appimglistAdapter.setOnItemClickListener(new com.dq.huibao.Interface.OnItemClickListener() {
                @Override
                public void onItemClick(View view, int position) {
                    hpInterface.doHomePage(
                            position,
                            appimglistBeans.get(position).getTitle(),
                            appimglistBeans.get(position).getType(),
                            appimglistBeans.get(position).getContent()
                    );
                }
            });

        }
    }


    class GoodsListViewHolder extends RecyclerView.ViewHolder {

        private final Context mContext;
        private RecyclerView recyclerView;

        public GoodsListViewHolder(Context mContext, View itemView) {
            super(itemView);
            this.mContext = mContext;
            recyclerView = (RecyclerView) itemView.findViewById(R.id.rv_goodslist);
        }

        public void setData(List<Index.DataBean.GlistBean> glistBeans) {
            mManager = new GridLayoutManager(mContext, 1, GridLayoutManager.VERTICAL, false);
            recyclerView.setLayoutManager(mManager);
            GListAdapter gListAdapter = new GListAdapter(mContext, glistBeans);
            recyclerView.setAdapter(gListAdapter);
        }
    }

}


