package cn.jbolt.service;

import java.util.List;

import com.jfinal.kit.StrKit;

import cn.jbolt.common.model.Master;

public class UserService {

	public Master findMasterById(int id) {
		return Master.dao.findById(id);
	}

	public Master findMasterByName(String name) {
		List<Master> masters = null;
		if (!StrKit.isBlank(name)) {
			masters = Master.dao.find("select * from master where masterName like concat ('%',?,'%')", name);
		}
		return masters != null && masters.size() > 0 ? masters.get(0) : null;
	}

	public Master masterLogin(String userName, String password) {
		List<Master> masters = Master.dao.find("select * from master where userName=? and password=?", userName,
				password);
		return masters != null && masters.size() > 0 ? masters.get(0) : null;
	}

	public void updatePetImg(int id, String imgUrl) {
		Master.dao.findById(id).set("petImg", imgUrl).update();
	}

	public void registerMaster(String userName, String password, String masterName, String masterAddress,
			String masterPhone, String petName, String petKind, String petType, String petImg, int petAge) {
		new Master().setUserName(userName).setPassword(password).setMasterName(masterName)
				.setMasterAddress(masterAddress).setMasterPhone(masterPhone).setPetName(petName).setPetKind(petKind)
				.setPetType(petType).setPetAge(petAge).setPetImg(petImg).save();
	}

	public void updateMasterInfo(int id, String masterName, String masterAddress, String masterPhone, String petName,
			String petKind, String petType, int petAge,String password) {
		Master.dao.findById(id).setMasterName(masterName).setMasterAddress(masterAddress).setMasterPhone(masterPhone)
				.setPetName(petName).setPetKind(petKind).setPetType(petType).setPetAge(petAge).setPassword(password).update();
	}
	
	public void buyVip(int id,int vipId){
		Master.dao.findById(id).setVipId(vipId).update();
	}
}
