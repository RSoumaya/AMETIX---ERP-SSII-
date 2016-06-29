package com.resotrekk.dao;

import java.util.List;

import com.resotrekk.model.Historique;

public interface HistoriqueIdao {

	public void saveHistorique(Historique historique);
	public List<Historique> getHistorique();
	
}
