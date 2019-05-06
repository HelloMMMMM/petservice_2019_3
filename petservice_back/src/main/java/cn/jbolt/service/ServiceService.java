package cn.jbolt.service;

import com.jfinal.plugin.activerecord.Page;
import cn.jbolt.common.model.Service;

public class ServiceService {
	public void addService(Service service){
		service.save();
	}
	
	public Page<Service> getServicePage(int page, String keyWord, int storeId) {
		Page<Service> pages = null;
		if (keyWord != null) {
			pages = Service.dao.paginate(page, 10, "select *",
					"from service where storeId = ? and serviceType like concat('%',?,'%')", storeId, keyWord);
		} else {
			pages = Service.dao.paginate(page, 10, "select *", "from service where storeId = ?", storeId);
		}
		return pages;
	}
	
	public void deleteService(int id){
		Service.dao.deleteById(id);
	}
	
	public void updateService(Service service){
		Service.dao.findById(service.getId()).set("serviceType", service.getServiceType())
		.set("minPrice", service.getMinPrice()).set("maxPrice", service.getMaxPrice())
		.set("startTime", service.getStartTime()).set("endTime", service.getEndTime()).update();
	}
	
	public Service getService(int serviceId){
		return Service.dao.findById(serviceId);
	}
}
