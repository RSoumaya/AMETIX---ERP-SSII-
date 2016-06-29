package com.resotrekk.dao;

import java.util.List;
import com.resotrekk.model.LangueCandidat;

public interface LangueIdao {

	
	 //Ajouter un objet
	public void saveLangueCandidat(LangueCandidat langueCandidat);
	
	//Modifier un objet
	public void updateLangueCandidat(LangueCandidat langueCandidat);
	
	//Supprimer un objet
	public void deleteLangueCandidat(LangueCandidat langueCandidat);
	
	//Retourner tous les objet dans une liste
	public List<LangueCandidat> getAllLangueCandidat();
	
	//Retourner tous les objet dans une liste selon id du candidat
	public List<LangueCandidat> getAllLangueCandidatById(int idcandidat);
}
