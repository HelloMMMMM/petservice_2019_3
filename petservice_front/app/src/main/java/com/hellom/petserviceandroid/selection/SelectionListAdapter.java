package com.hellom.petserviceandroid.selection;

import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.hellom.petserviceandroid.R;
import com.hellom.petserviceandroid.util.GlideUtil;

/**
 * author:helloM
 * email:1694327880@qq.com
 */
public class SelectionListAdapter extends BaseQuickAdapter<SelectionListBean.SelectionBean, BaseViewHolder> {
    private boolean isPetStarList;

    public SelectionListAdapter(int layoutResId, boolean isPetStarList) {
        super(layoutResId);
        this.isPetStarList = isPetStarList;
    }

    @Override
    protected void convert(BaseViewHolder helper, SelectionListBean.SelectionBean item) {
        helper.setText(R.id.phase_num, "第" + item.getSelectionTime() + "期");
        ImageView petImg = helper.getView(R.id.pet_img);
        GlideUtil.load(mContext, item.getPetImg(), petImg);
        helper.setText(R.id.pet_name, item.getPetName());
        helper.setText(R.id.pet_star, item.getPetStar() + "票");
        if (!isPetStarList) {
            helper.addOnClickListener(R.id.add_selection_star);
        }
    }
}
