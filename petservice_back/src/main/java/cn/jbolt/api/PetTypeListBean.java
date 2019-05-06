package cn.jbolt.api;

import java.util.List;

import cn.jbolt.common.model.Pettype;

public class PetTypeListBean extends ResponseBean{
	private List<Pettype> petTypeList;

	public List<Pettype> getPetTypeList() {
		return petTypeList;
	}

	public void setPetTypeList(List<Pettype> petTypeList) {
		this.petTypeList = petTypeList;
	}
	
	
}
