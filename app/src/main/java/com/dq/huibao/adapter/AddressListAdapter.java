package com.dq.huibao.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.dq.huibao.Interface.AddrInterface;
import com.dq.huibao.Interface.OnItemClickListener;
import com.dq.huibao.Interface.OnItemLongClickListener;
import com.dq.huibao.R;
import com.dq.huibao.bean.addr.Addr;
import com.dq.huibao.utils.BaseRecyclerViewHolder;

import java.util.List;

/**
 * Description：
 * Created by jingang on 2017/10/31.
 */

public class AddressListAdapter extends RecyclerView.Adapter<AddressListAdapter.MyViewHolder> {
    private OnItemClickListener onItemClickListener;
    private OnItemLongClickListener onItemLongClickListener;

    private Context mContext;
    private List<Addr.DataBean> addrList;

    private AddrInterface addrInterface;

    public AddressListAdapter(Context mContext, List<Addr.DataBean> addrList) {
        this.mContext = mContext;
        this.addrList = addrList;
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public void setOnItemLongClickListener(OnItemLongClickListener onItemLongClickListener) {
        this.onItemLongClickListener = onItemLongClickListener;
    }

    public void setAddrInterface(AddrInterface addrInterface) {
        this.addrInterface = addrInterface;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        MyViewHolder vh = new MyViewHolder(LayoutInflater.from(mContext).inflate(R.layout.item_addresslist, parent, false));
        return vh;
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {
        if (onItemClickListener != null) {
            //
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int position = holder.getLayoutPosition(); // 1
                    onItemClickListener.onItemClick(holder.itemView, position); // 2
                }
            });

        }
        if (onItemLongClickListener != null) {
            holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    int position = holder.getLayoutPosition();
                    onItemLongClickListener.onItemLongClick(holder.itemView, position);
                    //返回true 表示消耗了事件 事件不会继续传递
                    return true;
                }
            });
        }

        holder.contact.setText("" + addrList.get(position).getContact());
        holder.mobile.setText("" + addrList.get(position).getMobile());
        holder.addr.setText("" + addrList.get(position).getProvince() + addrList.get(position).getCity() + addrList.get(position).getDistrict() + addrList.get(position).getAddr());
        if (addrList.get(position).getIsdefault().equals("1")) {
            holder.checkBox.setChecked(true);
        } else if (addrList.get(position).getIsdefault().equals("0")) {
            holder.checkBox.setChecked(false);
        }
        holder.edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //暴露修改接口
                addrInterface.checkEdit(position,
                        addrList.get(position).getId(),
                        addrList.get(position).getRegionid(),
                        addrList.get(position).getProvince() + addrList.get(position).getCity() + addrList.get(position).getDistrict(),
                        Integer.parseInt(addrList.get(position).getIsdefault()),
                        addrList.get(position).getAddr(),
                        addrList.get(position).getContact(),
                        addrList.get(position).getMobile());
            }
        });
        holder.del.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //暴露删除接口
                addrInterface.checkDel(position, addrList.get(position).getId());
            }
        });

        holder.linIsdefault.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addrInterface.checkIsdefault(addrList.get(position).getId(), position);
            }
        });

    }

    @Override
    public int getItemCount() {
        return addrList.size();
    }

    public class MyViewHolder extends BaseRecyclerViewHolder {
        private TextView contact, mobile, addr;
        private CheckBox checkBox;
        private Button edit, del;
        private LinearLayout linIsdefault;

        public MyViewHolder(View view) {
            super(view);
            contact = view.findViewById(R.id.tv_item_addr_contact);
            mobile = view.findViewById(R.id.tv_item_addr_mobile);
            addr = view.findViewById(R.id.tv_item_addr);

            checkBox = view.findViewById(R.id.cb_item_address);

            edit = view.findViewById(R.id.but_item_address_update);
            del = view.findViewById(R.id.but_item_address_delete);
            linIsdefault = view.findViewById(R.id.lin_addr_isdefault);

        }
    }
}
