package com.hellom.petserviceandroid.article;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.hellom.petserviceandroid.R;

/**
 * author:helloM
 * email:1694327880@qq.com
 */
public class ArticleListAdapter extends BaseQuickAdapter<ArticleListBean.ArticleBean, BaseViewHolder> {
    public ArticleListAdapter(int layoutResId) {
        super(layoutResId);
    }

    @Override
    protected void convert(BaseViewHolder helper, ArticleListBean.ArticleBean item) {
        helper.setText(R.id.info_1, item.getArticleTitle());
        helper.setText(R.id.info_2, item.getArticleContent());
    }
}
