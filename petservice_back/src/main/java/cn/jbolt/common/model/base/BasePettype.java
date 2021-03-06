package cn.jbolt.common.model.base;

import com.jfinal.plugin.activerecord.Model;
import com.jfinal.plugin.activerecord.IBean;

/**
 * Generated by JFinal, do not modify this file.
 */
@SuppressWarnings({"serial", "unchecked"})
public abstract class BasePettype<M extends BasePettype<M>> extends Model<M> implements IBean {

	public M setId(java.lang.Integer id) {
		set("id", id);
		return (M)this;
	}
	
	public java.lang.Integer getId() {
		return getInt("id");
	}

	public M setPetTypeName(java.lang.String petTypeName) {
		set("petTypeName", petTypeName);
		return (M)this;
	}
	
	public java.lang.String getPetTypeName() {
		return getStr("petTypeName");
	}

}
