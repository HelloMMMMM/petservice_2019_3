package cn.jbolt.common.model.base;

import com.jfinal.plugin.activerecord.Model;
import com.jfinal.plugin.activerecord.IBean;

/**
 * Generated by JFinal, do not modify this file.
 */
@SuppressWarnings({"serial", "unchecked"})
public abstract class BaseService<M extends BaseService<M>> extends Model<M> implements IBean {

	public M setId(java.lang.Integer id) {
		set("id", id);
		return (M)this;
	}
	
	public java.lang.Integer getId() {
		return getInt("id");
	}

	public M setServiceType(java.lang.String serviceType) {
		set("serviceType", serviceType);
		return (M)this;
	}
	
	public java.lang.String getServiceType() {
		return getStr("serviceType");
	}

	public M setMinPrice(java.math.BigDecimal minPrice) {
		set("minPrice", minPrice);
		return (M)this;
	}
	
	public java.math.BigDecimal getMinPrice() {
		return get("minPrice");
	}

	public M setMaxPrice(java.math.BigDecimal maxPrice) {
		set("maxPrice", maxPrice);
		return (M)this;
	}
	
	public java.math.BigDecimal getMaxPrice() {
		return get("maxPrice");
	}

	public M setStartTime(java.lang.String startTime) {
		set("startTime", startTime);
		return (M)this;
	}
	
	public java.lang.String getStartTime() {
		return getStr("startTime");
	}

	public M setEndTime(java.lang.String endTime) {
		set("endTime", endTime);
		return (M)this;
	}
	
	public java.lang.String getEndTime() {
		return getStr("endTime");
	}

	public M setStoreId(java.lang.Integer storeId) {
		set("storeId", storeId);
		return (M)this;
	}
	
	public java.lang.Integer getStoreId() {
		return getInt("storeId");
	}

}
