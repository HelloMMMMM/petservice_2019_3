package cn.jbolt.api;

import java.util.List;

import cn.jbolt.common.model.Article;

public class ArticleListBean extends ResponseBean{
	private List<Article> articleList;

	public List<Article> getArticleList() {
		return articleList;
	}

	public void setArticleList(List<Article> articleList) {
		this.articleList = articleList;
	}

}
