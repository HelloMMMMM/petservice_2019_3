package com.hellom.petserviceandroid.service;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.blankj.utilcode.util.SPUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.hellom.petserviceandroid.R;
import com.hellom.petserviceandroid.base.Constant;
import com.hellom.petserviceandroid.base.LoadingCallBack;
import com.hellom.petserviceandroid.base.ResponseBean;
import com.lzy.okgo.OkGo;
import com.qmuiteam.qmui.widget.QMUIEmptyView;

public class ServiceFragment extends Fragment {
    private int page = 1;
    private SwipeRefreshLayout refresh;
    private RecyclerView serviceList;
    private QMUIEmptyView emptyView;
    private ServiceListAdapter adapter;
    private int storeId;

    public static ServiceFragment newInstance(int storeId) {
        Bundle args = new Bundle();
        args.putInt("storeId", storeId);
        ServiceFragment fragment = new ServiceFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_goods, container, false);
        initView(view);
        initListener();
        initData();
        return view;
    }

    private void initView(View view) {
        Bundle bundle = getArguments();
        if (bundle != null) {
            storeId = bundle.getInt("storeId");
        }
        emptyView = view.findViewById(R.id.empty_view);
        refresh = view.findViewById(R.id.refresh);
        serviceList = view.findViewById(R.id.goods_list);
        serviceList.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
        adapter = new ServiceListAdapter(R.layout.layout_service_list_item);
        serviceList.setAdapter(adapter);
    }

    private void initListener() {
        refresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                emptyView.hide();
                page = 1;
                getServiceList();
            }
        });
        adapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                ServiceListBean.ServiceBean serviceBean = (ServiceListBean.ServiceBean) adapter.getData().get(position);
                switch (view.getId()) {
                    case R.id.buy_service:
                        int vipId = SPUtils.getInstance().getInt("vipId", 0);
                        if (vipId == 0) {
                            ToastUtils.showShort("只有vip用户才可申请服务");
                        } else {
                            buyService(serviceBean.getId());
                        }
                        break;
                    default:
                        break;
                }
            }
        });
        adapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
            @Override
            public void onLoadMoreRequested() {
                page++;
                getServiceList();
            }
        }, serviceList);
    }

    private void initData() {
        refresh.setRefreshing(true);
        getServiceList();
    }

    public void getServiceList() {
        OkGo.<ServiceListBean>post(Constant.ROOT_URL + Constant.GET_SERVICE_LIST)
                .params("page", page)
                .params("storeId", storeId)
                .execute(new LoadingCallBack<>(ServiceListBean.class, getActivity(), new LoadingCallBack.Callback<ServiceListBean>() {
                    @Override
                    public void onSuccess(ServiceListBean response) {
                        refresh.setRefreshing(false);
                        if (response.getServiceList().size() == 0) {
                            if (page == 1) {
                                adapter.setNewData(null);
                                emptyView.show("暂无内容", null);
                            }
                            adapter.loadMoreEnd();
                            return;
                        }
                        if (page == 1) {
                            adapter.setNewData(response.getServiceList());
                        } else if (page > 1) {
                            adapter.addData(response.getServiceList());
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

    private void buyService(int serviceId) {
        OkGo.<ResponseBean>post(Constant.ROOT_URL + Constant.CREATE_ORDER)
                .params("orderType", 2)
                .params("masterId", SPUtils.getInstance().getInt("id"))
                .params("storeId", storeId)
                .params("serviceId", serviceId)
                .execute(new LoadingCallBack<>(ResponseBean.class, getActivity(), new LoadingCallBack.Callback<ResponseBean>() {
                    @Override
                    public void onSuccess(ResponseBean response) {
                        ToastUtils.showShort("申请成功，等待审核");
                    }

                    @Override
                    public void onError() {

                    }
                }, true));
    }
}
