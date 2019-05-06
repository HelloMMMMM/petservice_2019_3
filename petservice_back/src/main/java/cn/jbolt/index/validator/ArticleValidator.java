package cn.jbolt.index.validator;

import com.jfinal.core.Controller;
import com.jfinal.kit.StrKit;
import com.jfinal.validate.Validator;

import cn.jbolt.common.model.Article;
import cn.jbolt.index.IndexController;

public class ArticleValidator extends Validator {

	@Override
	protected void validate(Controller c) {
		// TODO Auto-generated method stub
		Article article = c.getModel(Article.class);
		if (StrKit.isBlank(article.getArticleTitle())||StrKit.isBlank(article.getArticleContent())) {
			addError("errorMsg", "请将信息填写完整！");
			return;
		}
		c.setAttr("article", article);
	}

	@Override
	protected void handleError(Controller c) {
		// TODO Auto-generated method stub
		IndexController indexController = (IndexController) c;
		indexController.keepModel(Article.class);
		indexController.keepPara();
		indexController.articleForm();
	}

}
