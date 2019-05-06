package com.hellom.petserviceandroid.article;

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

import com.blankj.utilcode.util.SPUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.hellom.petserviceandroid.R;
import com.hellom.petserviceandroid.base.Constant;
import com.hellom.petserviceandroid.base.LoadingCallBack;
import com.hellom.petserviceandroid.base.ResponseBean;
import com.lzy.okgo.OkGo;
import com.qmuiteam.qmui.widget.QMUIEmptyView;

public class ArticleFragment extends Fragment {
    private int page = 1;
    private SwipeRefreshLayout refresh;
    private RecyclerView articleList;
    private QMUIEmptyView emptyView;
    private ArticleListAdapter adapter;
    private int storeId;

    public static ArticleFragment newInstance(int storeId) {
        Bundle args = new Bundle();
        args.putInt("storeId", storeId);
        ArticleFragment fragment = new ArticleFragment();
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
        articleList = view.findViewById(R.id.goods_list);
        articleList.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
        adapter = new ArticleListAdapter(R.layout.layout_article_list_item);
        articleList.setAdapter(adapter);
    }

    private void initListener() {
        refresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                emptyView.hide();
                page = 1;
                getArticleList();
            }
        });
        adapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
            @Override
            public void onLoadMoreRequested() {
                page++;
                getArticleList();
            }
        }, articleList);
        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                ArticleListBean.ArticleBean articleBean = (ArticleListBean.ArticleBean) adapter.getData().get(position);
                jumpToArticleDetail(articleBean.getArticleTitle(), articleBean.getArticleContent());
            }
        });
    }

    private void initData() {
        refresh.setRefreshing(true);
        getArticleList();
    }

    public void getArticleList() {
        OkGo.<ArticleListBean>post(Constant.ROOT_URL + Constant.GET_ARTICLE_LIST)
                .params("page", page)
                .params("storeId", storeId)
                .execute(new LoadingCallBack<>(ArticleListBean.class, getActivity(), new LoadingCallBack.Callback<ArticleListBean>() {
                    @Override
                    public void onSuccess(ArticleListBean response) {
                        refresh.setRefreshing(false);
                        if (response.getArticleList().size() == 0) {
                            if (page == 1) {
                                adapter.setNewData(null);
                                emptyView.show("暂无内容", null);
                            }
                            adapter.loadMoreEnd();
                            return;
                        }
                        if (page == 1) {
                            adapter.setNewData(response.getArticleList());
                        } else if (page > 1) {
                            adapter.addData(response.getArticleList());
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

    private void jumpToArticleDetail(String title, String content) {
        Intent intent = new Intent(getActivity(), ArticleDetailActivity.class);
        intent.putExtra("title", title);
        intent.putExtra("content", content);
        startActivity(intent);
    }
}
