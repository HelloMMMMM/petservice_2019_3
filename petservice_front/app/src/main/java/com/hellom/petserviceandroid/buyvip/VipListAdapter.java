package com.hellom.petserviceandroid.buyvip;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.hellom.petserviceandroid.R;

/**
 * author:helloM
 * email:1694327880@qq.com
 */
public class VipListAdapter extends BaseQuickAdapter<VipListBean.VipsBean, BaseViewHolder> {
    public VipListAdapter(int layoutResId) {
        super(layoutResId);
    }

    @Override
    protected void convert(BaseViewHolder helper, VipListBean.VipsBean item) {
        helper.setText(R.id.info_1, "VIP卡名称:" + item.getName());
        helper.setText(R.id.info_2, "VIP卡价格:" + item.getPrice());
        helper.setText(R.id.info_3, "VIP卡优惠详情:" + item.getDesc());
        helper.addOnClickListener(R.id.buy_vip);
    }
}
