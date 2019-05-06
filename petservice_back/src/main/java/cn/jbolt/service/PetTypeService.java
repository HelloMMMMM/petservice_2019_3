package cn.jbolt.service;

import java.util.List;

import cn.jbolt.common.model.Pettype;

public class PetTypeService {
	public List<Pettype> getAllPetType() {
		return Pettype.dao.findAll();
	}
}
