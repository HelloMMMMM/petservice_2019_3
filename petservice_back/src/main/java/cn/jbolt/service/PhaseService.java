package cn.jbolt.service;

import java.util.List;

import com.jfinal.plugin.activerecord.Db;

import cn.jbolt.common.model.Phase;

public class PhaseService {
	// 1：正在进行，2：已结束

	public Phase getUnderwayPhase(int storeId) {
		List<Phase> phases = Phase.dao.find("select * from phase where isEnd = ? and storeId = ?", 1, storeId);
		return phases != null && phases.size() > 0 ? phases.get(0) : null;
	}

	public void createPhase(int storeId) {
		Phase phase = getUnderwayPhase(storeId);
		if (phase == null) {
			int currentPhaseNum = Db.queryInt("select count(*) from phase where storeId = ?", storeId);
			new Phase().setPhaseNum(currentPhaseNum + 1).setIsEnd(1).setStoreId(storeId).save();
		}
	}

	public List<Phase> getAllPhase(int storeId) {
		return Phase.dao.find("select * from phase where storeId = ? ", storeId);
	}

	public List<Phase> getAllEndPhase(int storeId) {
		return Phase.dao.find("select * from phase where storeId = ? and isEnd=2", storeId);
	}
	
	public void endPhase(int storeId) {
		Phase phase = getUnderwayPhase(storeId);
		if (phase != null) {
			phase.set("isEnd", 2).update();
		}
	}
}
