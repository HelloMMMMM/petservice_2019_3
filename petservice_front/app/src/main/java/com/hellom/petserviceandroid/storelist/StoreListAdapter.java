package com.hellom.petserviceandroid.storelist;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.hellom.petserviceandroid.R;
import com.hellom.petserviceandroid.base.ResponseBean;

public class StoreListAdapter extends BaseQuickAdapter<StoreListBean.StoreBean, BaseViewHolder> {
    public StoreListAdapter(int layoutResId) {
        super(layoutResId);
    }

    @Override
    protected void convert(BaseViewHolder helper, StoreListBean.StoreBean item) {
        helper.setText(R.id.store_name, item.getStoreName());
        helper.setText(R.id.store_address, item.getStoreAddress());
        helper.setText(R.id.store_phone, item.getPhone());
        helper.setText(R.id.store_qq, item.getQq());
        helper.setText(R.id.store_star, String.valueOf(item.getStoreStar()));
        helper.addOnClickListener(R.id.contact_store);
        helper.addOnClickListener(R.id.add_store_star);
    }
}
