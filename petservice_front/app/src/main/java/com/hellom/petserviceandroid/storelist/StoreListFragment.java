package com.hellom.petserviceandroid.storelist;

import android.content.Intent;
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
import android.widget.EditText;

import com.blankj.utilcode.util.ToastUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.hellom.petserviceandroid.R;
import com.hellom.petserviceandroid.base.Constant;
import com.hellom.petserviceandroid.base.LoadingCallBack;
import com.hellom.petserviceandroid.base.ResponseBean;
import com.hellom.petserviceandroid.store.StoreActivity;
import com.hellom.petserviceandroid.util.QQUtil;
import com.lzy.okgo.OkGo;
import com.qmuiteam.qmui.widget.QMUIEmptyView;

import java.util.ArrayList;
import java.util.List;

public class StoreListFragment extends Fragment implements View.OnClickListener {
    private EditText searchKeyWord;
    private SwipeRefreshLayout refresh;
    private RecyclerView storeList;
    private StoreListAdapter adapter;
    private QMUIEmptyView emptyView;
    private int page = 1;

    public static StoreListFragment newInstance() {
        Bundle args = new Bundle();
        StoreListFragment fragment = new StoreListFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_store_list, container, false);
        initView(view);
        initListener();
        initData();
        return view;
    }

    public void initView(View view) {
        searchKeyWord = view.findViewById(R.id.search_keyWord);
        view.findViewById(R.id.btn_search).setOnClickListener(this);
        emptyView = view.findViewById(R.id.empty_view);
        refresh = view.findViewById(R.id.refresh);
        storeList = view.findViewById(R.id.storeList);
        storeList.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
        adapter = new StoreListAdapter(R.layout.layout_store_list_item);
        storeList.setAdapter(adapter);
    }

    public void initListener() {
        refresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                emptyView.hide();
                page = 1;
                getStoreList();
            }
        });
        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                StoreListBean.StoreBean storeBean = (StoreListBean.StoreBean) adapter.getData().get(position);
                jumpToStore(storeBean.getId(), storeBean.getStoreName());
            }
        });
        adapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                StoreListBean.StoreBean storeBean = (StoreListBean.StoreBean) adapter.getData().get(position);
                switch (view.getId()) {
                    case R.id.contact_store:
                        QQUtil.toChat(getActivity(), storeBean.getQq());
                        break;
                    case R.id.add_store_star:
                        starStore(storeBean.getId());
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
                getStoreList();
            }
        }, storeList);
    }

    public void initData() {
        refresh.setRefreshing(true);
        getStoreList();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_search:
                page = 1;
                emptyView.hide();
                getStoreList();
                break;
            default:
                break;
        }
    }

    private void jumpToStore(int storeId, String storeName) {
        Intent intent = new Intent(getActivity(), StoreActivity.class);
        intent.putExtra("storeId", storeId);
        intent.putExtra("storeName", storeName);
        startActivity(intent);
    }

    public void getStoreList() {
        OkGo.<StoreListBean>post(Constant.ROOT_URL + Constant.GET_STORE_LIST)
                .params("page", page)
                .params("keyWord", searchKeyWord.getText().toString())
                .execute(new LoadingCallBack<>(StoreListBean.class, getActivity(), new LoadingCallBack.Callback<StoreListBean>() {
                    @Override
                    public void onSuccess(StoreListBean response) {
                        refresh.setRefreshing(false);
                        if (response.getStoreList().size() == 0) {
                            if (page == 1) {
                                adapter.setNewData(null);
                                emptyView.show("暂无内容", null);
                            }
                            adapter.loadMoreEnd();
                            return;
                        }
                        if (page == 1) {
                            adapter.setNewData(response.getStoreList());
                        } else if (page > 1) {
                            adapter.addData(response.getStoreList());
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

    private void starStore(int id) {
        OkGo.<ResponseBean>post(Constant.ROOT_URL + Constant.STAR_STORE)
                .params("id", id)
                .execute(new LoadingCallBack<>(ResponseBean.class, getActivity(), new LoadingCallBack.Callback<ResponseBean>() {
                    @Override
                    public void onSuccess(ResponseBean response) {
                        ToastUtils.showShort("加星成功");
                        refresh.setRefreshing(true);
                        page = 1;
                        getStoreList();
                    }

                    @Override
                    public void onError() {
                    }
                }, true));
    }
}
