package com.hellom.petserviceandroid.service;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.hellom.petserviceandroid.R;

/**
 * author:helloM
 * email:1694327880@qq.com
 */
public class ServiceListAdapter extends BaseQuickAdapter<ServiceListBean.ServiceBean, BaseViewHolder> {
    public ServiceListAdapter(int layoutResId) {
        super(layoutResId);
    }

    @Override
    protected void convert(BaseViewHolder helper, ServiceListBean.ServiceBean item) {
        helper.setText(R.id.info_1, "服务类型:" + item.getServiceType());
        helper.setText(R.id.info_2, "服务价格区间:" + item.getMinPrice() + "-" + item.getMaxPrice());
        helper.setText(R.id.info_3, "服务时间区间:" + item.getStartTime() + "-" + item.getEndTime());
        helper.addOnClickListener(R.id.buy_service);
    }
}
