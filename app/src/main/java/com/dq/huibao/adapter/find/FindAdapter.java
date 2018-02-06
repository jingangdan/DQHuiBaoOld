package com.dq.huibao.adapter.find;

import android.content.Context;

import com.dq.huibao.R;
import com.dq.huibao.bean.Find;
import com.dq.huibao.view.lrecyclerview.ListBaseAdapter;
import com.dq.huibao.view.lrecyclerview.SuperViewHolder;

import java.util.List;

/**
 * Created by jingang on 2018/2/6.
 */

public class FindAdapter extends ListBaseAdapter<Find> {

    private List<Find> mList;

    public FindAdapter(Context context, List<Find> mList) {
        super(context);
        this.mList = mList;

    }

    @Override
    public int getLayoutId() {
        return R.layout.sample_item_text;
    }

    @Override
    public void onBindItemHolder(SuperViewHolder holder, int position) {
        Find item = mDataList.get(position);
//        TextView titleText = holder.getView(R.id.info_text);
//        titleText.setText(item.title);
    }
}

