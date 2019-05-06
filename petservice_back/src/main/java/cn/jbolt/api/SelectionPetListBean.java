package cn.jbolt.api;

import java.util.List;

import cn.jbolt.common.model.Selection;

public class SelectionPetListBean extends ResponseBean{
	private List<Selection> selectionList;

	public List<Selection> getSelectionList() {
		return selectionList;
	}

	public void setSelectionList(List<Selection> selectionList) {
		this.selectionList = selectionList;
	}
}
