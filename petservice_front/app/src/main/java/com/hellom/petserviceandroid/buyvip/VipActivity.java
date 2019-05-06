package com.hellom.petserviceandroid.buyvip;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.blankj.utilcode.util.SPUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.hellom.petserviceandroid.R;
import com.hellom.petserviceandroid.base.BaseActivity;
import com.hellom.petserviceandroid.base.Constant;
import com.hellom.petserviceandroid.base.LoadingCallBack;
import com.hellom.petserviceandroid.base.ResponseBean;
import com.lzy.okgo.OkGo;
import com.qmuiteam.qmui.widget.QMUIEmptyView;

/**
 * author:helloM
 * email:1694327880@qq.com
 */
public class VipActivity extends BaseActivity {
    private int page = 1;
    private SwipeRefreshLayout refresh;
    private VipListAdapter adapter;
    private RecyclerView vipList;
    private QMUIEmptyView emptyView;

    @Override
    public void initView() {
        TextView title = findViewById(R.id.title);
        title.setText("VIP列表");

        refresh = findViewById(R.id.refresh);
        vipList = findViewById(R.id.vip_list);
        vipList.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        adapter = new VipListAdapter(R.layout.layout_vip_list_item);
        vipList.setAdapter(adapter);
        emptyView = findViewById(R.id.empty_view);
    }

    @Override
    public void initListener() {
        findViewById(R.id.back).setOnClickListener(this);
        refresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                emptyView.hide();
                page = 1;
                getVipList();
            }
        });
        adapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
            @Override
            public void onLoadMoreRequested() {
                page++;
                getVipList();
            }
        }, vipList);
        adapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                switch (view.getId()) {
                    case R.id.buy_vip:
                        VipListBean.VipsBean vipsBean = (VipListBean.VipsBean) adapter.getData().get(position);
                        buyVip(vipsBean.getId());
                        break;
                    default:
                        break;
                }
            }
        });
    }

    @Override
    public void initData() {
        refresh.setRefreshing(true);
        getVipList();
    }

    @Override
    public int getContentViewId() {
        return R.layout.activity_vip;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.back:
                setResult(RESULT_CANCELED);
                finish();
                break;
            default:
                break;
        }
    }

    public void buyVip(int vipCardId) {
        int vipId = SPUtils.getInstance().getInt("vipId", 0);
        if (vipId > 0) {
            ToastUtils.showShort("您已经是vip了");
            return;
        }
        OkGo.<ResponseBean>post(Constant.ROOT_URL + Constant.BUY_VIP)
                .params("id", SPUtils.getInstance().getInt("id"))
                .params("vipId", vipCardId)
                .execute(new LoadingCallBack<>(ResponseBean.class, this, new LoadingCallBack.Callback<ResponseBean>() {
                    @Override
                    public void onSuccess(ResponseBean response) {
                        ToastUtils.showShort("购买成功");
                        setResult(RESULT_OK);
                        finish();
                    }

                    @Override
                    public void onError() {

                    }
                }, true));
    }

    public void getVipList() {
        OkGo.<VipListBean>post(Constant.ROOT_URL + Constant.GET_VIP_LIST)
                .params("page", page)
                .execute(new LoadingCallBack<>(VipListBean.class, this, new LoadingCallBack.Callback<VipListBean>() {
                    @Override
                    public void onSuccess(VipListBean response) {
                        refresh.setRefreshing(false);
                        if (response.getVips().size() == 0) {
                            if (page == 1) {
                                adapter.setNewData(null);
                                emptyView.show("暂无内容", null);
                            }
                            adapter.loadMoreEnd();
                            return;
                        }
                        if (page == 1) {
                            adapter.setNewData(response.getVips());
                        } else if (page > 1) {
                            adapter.addData(response.getVips());
                        }
                        adapter.loadMoreComplete();
                    }

                    @Override
                    public void onError() {
                        refresh.setRefreshing(false);
                        adapter.setNewData(null);
                        emptyView.show("出现错误，请刷新重试", null);
                    }
                }, false));
    }
}
