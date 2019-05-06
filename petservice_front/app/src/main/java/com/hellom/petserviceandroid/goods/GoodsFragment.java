package com.hellom.petserviceandroid.goods;

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

public class GoodsFragment extends Fragment {
    private int page = 1;
    private SwipeRefreshLayout refresh;
    private RecyclerView goodsList;
    private QMUIEmptyView emptyView;
    private GoodsListAdapter adapter;
    private int storeId;

    public static GoodsFragment newInstance(int storeId) {
        Bundle args = new Bundle();
        args.putInt("storeId", storeId);
        GoodsFragment fragment = new GoodsFragment();
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
        goodsList = view.findViewById(R.id.goods_list);
        goodsList.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
        adapter = new GoodsListAdapter(R.layout.layout_goods_list_item);
        goodsList.setAdapter(adapter);
    }

    private void initListener() {
        refresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                emptyView.hide();
                page = 1;
                getGoodsList();
            }
        });
        adapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                GoodsListBean.GoodsBean goodsBean = (GoodsListBean.GoodsBean) adapter.getData().get(position);
                switch (view.getId()) {
                    case R.id.buy_goods:
                        buyGoods(goodsBean.getId());
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
                getGoodsList();
            }
        }, goodsList);
    }

    private void initData() {
        refresh.setRefreshing(true);
        getGoodsList();
    }

    public void getGoodsList() {
        OkGo.<GoodsListBean>post(Constant.ROOT_URL + Constant.GET_GOODS_LIST)
                .params("page", page)
                .params("storeId", storeId)
                .execute(new LoadingCallBack<>(GoodsListBean.class, getActivity(), new LoadingCallBack.Callback<GoodsListBean>() {
                    @Override
                    public void onSuccess(GoodsListBean response) {
                        refresh.setRefreshing(false);
                        if (response.getGoodsList().size() == 0) {
                            if (page == 1) {
                                adapter.setNewData(null);
                                emptyView.show("暂无内容", null);
                            }
                            adapter.loadMoreEnd();
                            return;
                        }
                        if (page == 1) {
                            adapter.setNewData(response.getGoodsList());
                        } else if (page > 1) {
                            adapter.addData(response.getGoodsList());
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

    private void buyGoods(int goodsId) {
        OkGo.<ResponseBean>post(Constant.ROOT_URL + Constant.CREATE_ORDER)
                .params("orderType", 1)
                .params("masterId", SPUtils.getInstance().getInt("id"))
                .params("storeId", storeId)
                .params("goodsId", goodsId)
                .execute(new LoadingCallBack<>(ResponseBean.class, getActivity(), new LoadingCallBack.Callback<ResponseBean>() {
                    @Override
                    public void onSuccess(ResponseBean response) {
                        ToastUtils.showShort("购买成功");
                    }

                    @Override
                    public void onError() {

                    }
                }, true));
    }
}
