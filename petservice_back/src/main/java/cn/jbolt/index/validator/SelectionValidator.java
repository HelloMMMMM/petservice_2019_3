package cn.jbolt.index.validator;

import com.jfinal.core.Controller;
import com.jfinal.kit.StrKit;
import com.jfinal.upload.UploadFile;
import com.jfinal.validate.Validator;

import cn.jbolt.common.model.Selection;
import cn.jbolt.index.IndexController;

public class SelectionValidator extends Validator {

	@Override
	protected void validate(Controller c) {
		// TODO Auto-generated method stub
		UploadFile uploadFile = c.getFile();
		Selection selection = c.getModel(Selection.class);
		boolean isAdd=c.getBoolean("isAdd");
		if (StrKit.isBlank(selection.getPetName())
				|| selection.getSelectionTime() == null) {
			if (uploadFile!=null) {
				uploadFile.getFile().delete();
			}
			addError("errorMsg", "请将信息填写完整!");
			return;
		}
		if (uploadFile != null) {
			String absolutePath=uploadFile.getFile().getAbsolutePath();
			selection.setPetImg(absolutePath.substring(absolutePath.indexOf("upload")).replace("\\", "/"));
			c.setAttr("selection", selection);
		}else {
			if (isAdd) {
				addError("errorMsg", "请将信息填写完整!");
			}else {
				selection.setPetImg(c.getPara("petImg"));
				c.setAttr("selection", selection);
			}
		}
	}

	@Override
	protected void handleError(Controller c) {
		// TODO Auto-generated method stub
		IndexController indexController = (IndexController) c;
		indexController.keepModel(Selection.class);
		indexController.keepPara();
		indexController.petForm();
	}

}
