package com.hellom.petserviceandroid.goods;

import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.hellom.petserviceandroid.R;
import com.hellom.petserviceandroid.util.GlideUtil;

/**
 * author:helloM
 * email:1694327880@qq.com
 */
public class GoodsListAdapter extends BaseQuickAdapter<GoodsListBean.GoodsBean, BaseViewHolder> {
    public GoodsListAdapter(int layoutResId) {
        super(layoutResId);
    }

    @Override
    protected void convert(BaseViewHolder helper, GoodsListBean.GoodsBean item) {
        ImageView goodsImg = helper.getView(R.id.goods_img);
        GlideUtil.load(mContext, item.getGoodsImg().replace("\\", "/"), goodsImg);
        helper.setText(R.id.info_1, "商品名称:" + item.getGoodsName());
        helper.setText(R.id.info_2, "商品价格:" + item.getGoodsPrice());
        helper.setText(R.id.info_3, "商品类型:" + item.getGoodsType());
        helper.addOnClickListener(R.id.buy_goods);
    }
}
