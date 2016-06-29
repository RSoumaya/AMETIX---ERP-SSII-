package com.resotrekk.controller.historique;

import java.util.ArrayList;
import java.util.List;

import com.resotrekk.business.HistoriqueIbusiness;
import com.resotrekk.model.Historique;

public class HistoriqueCtr {
	
	//******************************************************************
	
	HistoriqueIbusiness hitoriqueBuss;
	List<Historique> list = new ArrayList<Historique>();
	
	//******************************************************************
	
	public HistoriqueIbusiness getHitoriqueBuss() {
		return hitoriqueBuss;
	}
	public void setHitoriqueBuss(HistoriqueIbusiness hitoriqueBuss) {
		this.hitoriqueBuss = hitoriqueBuss;
	}
	public List<Historique> getList() {
		list = hitoriqueBuss.retournerHistorique();
		return list;
	}
	public void setList(List<Historique> list) {
		this.list = list;
	}
	//******************************************************************

}
