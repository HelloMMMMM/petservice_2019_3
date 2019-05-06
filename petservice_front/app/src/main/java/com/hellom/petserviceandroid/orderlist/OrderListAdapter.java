package com.hellom.petserviceandroid.orderlist;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.hellom.petserviceandroid.R;

/**
 * author:helloM
 * email:1694327880@qq.com
 */
public class OrderListAdapter extends BaseQuickAdapter<OrderListBean.OrderBeansBean, BaseViewHolder> {
    public OrderListAdapter(int layoutResId) {
        super(layoutResId);
    }

    @Override
    protected void convert(BaseViewHolder helper, OrderListBean.OrderBeansBean item) {
        OrderListBean.OrderBeansBean.StoreBean storeBean = item.getStore();
        helper.setText(R.id.info_0, "店铺:" + storeBean.getStoreName());
        if (item.getOrderType() == 1) {
            //商品订单
            helper.setText(R.id.info_1, "商品订单");
            OrderListBean.OrderBeansBean.GoodsBean goodsBean = item.getGoods();
            helper.setText(R.id.info_2, "商品名称:" + goodsBean.getGoodsName());
            helper.setText(R.id.info_3, "商品价格:" + goodsBean.getGoodsPrice());
            helper.setText(R.id.info_4, "商品类型:" + goodsBean.getGoodsType());
        } else if (item.getOrderType() == 2) {
            //服务订单
            String accept = null;
            if (item.getIsAccept() == 1) {
                accept = "-未审核";
            } else if (item.getIsAccept() == 2) {
                accept = "-已审核";
            }
            helper.setText(R.id.info_1, "服务订单" + accept);
            OrderListBean.OrderBeansBean.ServiceBean serviceBean = item.getService();
            helper.setText(R.id.info_2, "服务类型:" + serviceBean.getServiceType());
            helper.setText(R.id.info_3, "服务价格区间:" + serviceBean.getMinPrice() + " - " + serviceBean.getMaxPrice());
            helper.setText(R.id.info_4, "服务时间区间:" + serviceBean.getStartTime() + " - " + serviceBean.getEndTime());
        }
    }
}
