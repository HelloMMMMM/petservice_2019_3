package com.hellom.petserviceandroid.orderlist;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.blankj.utilcode.util.SPUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.hellom.petserviceandroid.R;
import com.hellom.petserviceandroid.base.BaseActivity;
import com.hellom.petserviceandroid.base.Constant;
import com.hellom.petserviceandroid.base.LoadingCallBack;
import com.lzy.okgo.OkGo;
import com.qmuiteam.qmui.widget.QMUIEmptyView;

/**
 * author:helloM
 * email:1694327880@qq.com
 */
public class OrderListActivity extends BaseActivity {
    private int page = 1;
    private SwipeRefreshLayout refresh;
    private OrderListAdapter adapter;
    private RecyclerView orderList;
    private QMUIEmptyView emptyView;

    @Override
    public void initView() {
        TextView title = findViewById(R.id.title);
        title.setText("订单列表");

        refresh = findViewById(R.id.refresh);
        orderList = findViewById(R.id.order_list);
        orderList.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        adapter = new OrderListAdapter(R.layout.layout_order_list_item);
        orderList.setAdapter(adapter);
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
                getOrderList();
            }
        });
        adapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
            @Override
            public void onLoadMoreRequested() {
                page++;
                getOrderList();
            }
        }, orderList);
    }

    @Override
    public void initData() {
        refresh.setRefreshing(true);
        getOrderList();
    }

    @Override
    public int getContentViewId() {
        return R.layout.activity_order_list;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.back:
                finish();
                break;
            default:
                break;
        }
    }

    public void getOrderList() {
        OkGo.<OrderListBean>post(Constant.ROOT_URL + Constant.GET_ORDER_LIST)
                .params("id", SPUtils.getInstance().getInt("id"))
                .params("page", page)
                .execute(new LoadingCallBack<>(OrderListBean.class, this, new LoadingCallBack.Callback<OrderListBean>() {
                    @Override
                    public void onSuccess(OrderListBean response) {
                        refresh.setRefreshing(false);
                        if (response.getOrderBeans().size() == 0) {
                            if (page == 1) {
                                adapter.setNewData(null);
                                emptyView.show("暂无内容", null);
                            }
                            adapter.loadMoreEnd();
                            return;
                        }
                        if (page == 1) {
                            adapter.setNewData(response.getOrderBeans());
                        } else if (page > 1) {
                            adapter.addData(response.getOrderBeans());
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
