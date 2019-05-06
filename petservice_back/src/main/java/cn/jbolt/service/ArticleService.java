package cn.jbolt.service;

import com.jfinal.plugin.activerecord.Page;

import cn.jbolt.common.model.Article;

public class ArticleService {
	public void addArticle(Article article){
		article.save();
	}
	
	public void deleteArticle(int id){
		Article.dao.deleteById(id);
	}
	
	public void updateArticle(Article article){
		Article.dao.findById(article.getId()).set("articleTitle", article.getArticleTitle())
		.set("articleContent", article.getArticleContent()).update();
	}
	
	public Page<Article> getArticlePages(int page,String keyWord,int storeId){
		Page<Article> pages = null;
		if (keyWord != null) {
			pages = Article.dao.paginate(page, 10, "select *",
					"from article where storeId = ? and articleTitle like concat('%',?,'%')", storeId, keyWord);
		} else {
			pages = Article.dao.paginate(page, 10, "select *", "from article where storeId = ?", storeId);
		}
		return pages;
	}
}
