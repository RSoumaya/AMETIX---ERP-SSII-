package com.resotrekk.dao;

import java.util.List;
import com.resotrekk.model.Offre;

public interface OffreIdao {

	
		//Ajouter un objet
		public void saveOffre(Offre offre);
		
		//Modifier un objet
		public void updateOffre(Offre offre);
		
		//Supprimer un objet
		public void deleteOffre(Offre offre);
		
		//Retourner tous les objet dans une liste
		public List<Offre> getAllOffre();
		
}
