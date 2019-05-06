package com.hellom.petserviceandroid.article;

import android.view.View;
import android.widget.TextView;

import com.hellom.petserviceandroid.R;
import com.hellom.petserviceandroid.base.BaseActivity;

/**
 * author:helloM
 * email:1694327880@qq.com
 */
public class ArticleDetailActivity extends BaseActivity {
    @Override
    public void initView() {
        TextView title = findViewById(R.id.article_title);
        TextView content = findViewById(R.id.article_content);
        title.setText(getIntent().getStringExtra("title"));
        content.setText(getIntent().getStringExtra("content"));
    }

    @Override
    public void initListener() {
        findViewById(R.id.back).setOnClickListener(this);
    }

    @Override
    public void initData() {

    }

    @Override
    public int getContentViewId() {
        return R.layout.activity_article_detail;
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
}
