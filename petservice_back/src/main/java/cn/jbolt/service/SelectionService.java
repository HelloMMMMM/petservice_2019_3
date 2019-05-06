package cn.jbolt.service;

import java.util.ArrayList;
import java.util.List;

import com.jfinal.plugin.activerecord.Page;

import cn.jbolt.common.model.Phase;
import cn.jbolt.common.model.Selection;

public class SelectionService {
	public void addSelectionPet(Selection selection) {
		selection.save();
	}

	public Page<Selection> getSelectionPage(int page, int phaseNum, int storeId) {
		Page<Selection> pages = null;
		pages = Selection.dao.paginate(page, 10, "select *",
				"from selection where storeId = ? and selectionTime = ? order by petStar desc", storeId, phaseNum);
		return pages;
	}

	public void deleteSelectionPet(int id) {
		Selection.dao.deleteById(id);
	}

	public void updateSelectionPet(Selection selection) {
		Selection.dao.findById(selection.getId()).set("petName", selection.getPetName())
				.set("petImg", selection.getPetImg()).update();
	}

	public void starSelectionPet(int selectionId) {
		Selection selection = Selection.dao.findById(selectionId);
		if (selection.getId() != null) {
			selection.set("petStar", selection.getPetStar() + 1).update();
		}
	}

	public Selection getSelectionStar(int storeId, int phaseNum) {
		List<Selection> selections = Selection.dao.find(
				"select * from selection where storeId =? and selectionTime=? order by petStar", storeId, phaseNum);
		return selections != null && selections.size() > 0 ? selections.get(0) : null;
	}

	public List<Selection> getAllSelectionStar(int storeId) {
		PhaseService phaseService = new PhaseService();
		List<Phase> phases = phaseService.getAllEndPhase(storeId);
		List<Selection> selections = new ArrayList<>();
		for (Phase phase : phases) {
			Selection selection = getSelectionStar(storeId, phase.getPhaseNum());
			selections.add(selection);
		}
		return selections;
	}
}
