package cn.jbolt.index.validator;

import java.util.Date;

import com.jfinal.core.Controller;
import com.jfinal.ext.kit.DateKit;
import com.jfinal.kit.StrKit;
import com.jfinal.validate.Validator;

import cn.jbolt.common.model.Service;
import cn.jbolt.index.IndexController;

public class ServiceValidator extends Validator {

	@Override
	protected void validate(Controller c) {
		// TODO Auto-generated method stub
		Service service = c.getModel(Service.class);
		String startTimeString = c.get("startTimeString");
		String endTimeString = c.get("endTimeString");
		String tempTimeString = c.get("tempTimeString");
		boolean isAdd = c.getBoolean("isAdd");
		//基本信息判空
		if (StrKit.isBlank(service.getServiceType()) || service.getMinPrice() == null
				|| service.getMaxPrice() == null) {
			addError("errorMsg", "请将信息填写完整!");
			return;
		}
		//添加模式时对时间判空
		if (isAdd && (startTimeString.equals(tempTimeString) || endTimeString.equals(tempTimeString))) {
			addError("errorMsg", "请将信息填写完整!");
			return;
		}
		//价格区间判断
		if (service.getMinPrice().intValue() > service.getMaxPrice().intValue()) {
			addError("errorMsg", "最低价格不能大于最高价格!");
			return;
		}
		DateKit.setDatePattern("HH:mm");
		//添加模式，直接判断并设置时间
		if (isAdd) {
			Date startDate = DateKit.toDate(startTimeString);
			Date endDate = DateKit.toDate(endTimeString);
			if (startDate.getTime() > endDate.getTime()) {
				addError("errorMsg", "可预约开始时间不能大于结束时间!");
				return;
			}
			service.setStartTime(startTimeString);
			service.setEndTime(endTimeString);
		}
		//修改模式
		else {
			boolean isModifyStartTime=!startTimeString.equals(tempTimeString);
			boolean isModifyEndTime=!endTimeString.equals(tempTimeString);
			String currentStartTime=c.get("startTime");
			String currentEndTime=c.get("endTime");
			Date currentStartDate=DateKit.toDate(currentStartTime);
			Date currentEndDate=DateKit.toDate(currentEndTime);
			service.setStartTime(currentStartTime);
			service.setEndTime(currentEndTime);
			//更改了开始时间
			if (isModifyStartTime&&!isModifyEndTime) {
				Date startDate = DateKit.toDate(startTimeString);
				if (startDate.getTime()>currentEndDate.getTime()) {
					addError("errorMsg", "可预约开始时间不能大于结束时间!");
					return;
				}
				service.setStartTime(startTimeString);
				service.setEndTime(currentEndTime);
			}
			//更改了结束时间
			else if (!isModifyStartTime&&isModifyEndTime) {
				Date endDate = DateKit.toDate(endTimeString);
				if (currentStartDate.getTime()>endDate.getTime()) {
					addError("errorMsg", "可预约开始时间不能大于结束时间!");
					return;
				}
				service.setStartTime(currentStartTime);
				service.setEndTime(endTimeString);
			}
			//两个时间都更改了
			else if (isModifyStartTime&&isModifyEndTime){
				Date startDate = DateKit.toDate(startTimeString);
				Date endDate = DateKit.toDate(endTimeString);
				if (startDate.getTime() > endDate.getTime()) {
					addError("errorMsg", "可预约开始时间不能大于结束时间!");
					return;
				}
				service.setStartTime(startTimeString);
				service.setEndTime(endTimeString);
			}
		}
		c.setAttr("service", service);
	}

	@Override
	protected void handleError(Controller c) {
		// TODO Auto-generated method stub
		IndexController indexController = (IndexController) c;
		indexController.keepModel(Service.class);
		indexController.keepPara();
		indexController.serviceForm();
	}

}
