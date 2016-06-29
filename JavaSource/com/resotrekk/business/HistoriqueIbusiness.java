package com.resotrekk.business;

import java.util.List;
import com.resotrekk.model.Historique;

public interface HistoriqueIbusiness {

	public void ajouterHistorique(Historique historique);
	public List<Historique> retournerHistorique();
	
}
