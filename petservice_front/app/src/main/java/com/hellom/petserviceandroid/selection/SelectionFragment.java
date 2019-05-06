package com.hellom.petserviceandroid.selection;

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

import com.blankj.utilcode.util.ToastUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.hellom.petserviceandroid.R;
import com.hellom.petserviceandroid.base.Constant;
import com.hellom.petserviceandroid.base.LoadingCallBack;
import com.hellom.petserviceandroid.base.ResponseBean;
import com.lzy.okgo.OkGo;
import com.qmuiteam.qmui.widget.QMUIEmptyView;

public class SelectionFragment extends Fragment {
    private int page = 1;
    private SwipeRefreshLayout refresh;
    private RecyclerView selectionList;
    private RecyclerView preSelectionStarList;
    private QMUIEmptyView emptyView;
    private SelectionListAdapter preAdapter;
    private SelectionListAdapter adapter;
    private int storeId;

    public static SelectionFragment newInstance(int storeId) {
        Bundle args = new Bundle();
        args.putInt("storeId", storeId);
        SelectionFragment fragment = new SelectionFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_selection, container, false);
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
        preSelectionStarList = view.findViewById(R.id.pre_selection_star);
        preSelectionStarList.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false));
        preAdapter = new SelectionListAdapter(R.layout.layout_pre_selection_list_item, true);
        preSelectionStarList.setAdapter(preAdapter);
        selectionList = view.findViewById(R.id.selection_list);
        selectionList.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
        adapter = new SelectionListAdapter(R.layout.layout_selection_list_item, false);
        selectionList.setAdapter(adapter);
    }

    private void initListener() {
        refresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                emptyView.hide();
                page = 1;
                getPetStarList();
            }
        });
        adapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
            @Override
            public void onLoadMoreRequested() {
                page++;
                getSelectionList();
            }
        }, selectionList);
        adapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                SelectionListBean.SelectionBean selectionBean = (SelectionListBean.SelectionBean) adapter.getData().get(position);
                switch (view.getId()) {
                    case R.id.add_selection_star:
                        starSelectionPet(selectionBean.getId());
                        break;
                    default:
                        break;
                }
            }
        });
    }

    private void initData() {
        refresh.setRefreshing(true);
        getPetStarList();
    }

    public void getPetStarList() {
        OkGo.<SelectionListBean>post(Constant.ROOT_URL + Constant.GET_PRE_SELECTION_STAR_LIST)
                .params("storeId", storeId)
                .execute(new LoadingCallBack<>(SelectionListBean.class, getActivity(), new LoadingCallBack.Callback<SelectionListBean>() {
                    @Override
                    public void onSuccess(SelectionListBean response) {
                        if (response.getSelectionList().size() > 0) {
                            preSelectionStarList.setVisibility(View.VISIBLE);
                            preAdapter.setNewData(response.getSelectionList());
                        } else {
                            preSelectionStarList.setVisibility(View.GONE);
                        }
                        getSelectionList();
                    }

                    @Override
                    public void onError() {
                        preSelectionStarList.setVisibility(View.GONE);
                    }
                }, false));
    }

    public void getSelectionList() {
        int currentPhaseNum = preAdapter.getData().size() + 1;
        OkGo.<SelectionListBean>post(Constant.ROOT_URL + Constant.GET_SELECTION_LIST)
                .params("page", page)
                .params("storeId", storeId)
                .params("phaseNum", currentPhaseNum)
                .execute(new LoadingCallBack<>(SelectionListBean.class, getActivity(), new LoadingCallBack.Callback<SelectionListBean>() {
                    @Override
                    public void onSuccess(SelectionListBean response) {
                        refresh.setRefreshing(false);
                        if (response.getSelectionList().size() == 0) {
                            if (page == 1) {
                                adapter.setNewData(null);
                                emptyView.show("暂无评选活动或暂无参选宠物", null);
                            }
                            adapter.loadMoreEnd();
                            return;
                        }
                        if (page == 1) {
                            adapter.setNewData(response.getSelectionList());
                        } else if (page > 1) {
                            adapter.addData(response.getSelectionList());
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

    private void starSelectionPet(int selectionId) {
        OkGo.<ResponseBean>post(Constant.ROOT_URL + Constant.STAR_SELECTION_PET)
                .params("selectionId", selectionId)
                .execute(new LoadingCallBack<>(ResponseBean.class, getActivity(), new LoadingCallBack.Callback<ResponseBean>() {
                    @Override
                    public void onSuccess(ResponseBean response) {
                        ToastUtils.showShort("投票成功");

                        emptyView.hide();
                        page = 1;
                        getPetStarList();
                    }

                    @Override
                    public void onError() {
                    }
                }, true));
    }
}
