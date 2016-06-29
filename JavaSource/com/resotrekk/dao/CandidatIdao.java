package com.resotrekk.dao;

import java.util.List;

import com.resotrekk.model.Candidat;

public interface CandidatIdao {

	
	    //Ajouter un objet
		public void saveCandidat(Candidat candidat);
		
		//Modifier un objet
		public void updateCandidat(Candidat candidat);
		
		//Supprimer un objet
		public void deleteCandidat(Candidat candidat);
		
		//Retourner un objet selon son id
		public Candidat findCandidatById(int id);
		
		//Retourner tous les objet dans une liste
		public List<Candidat> getAllCandidat();
		
		//Retourner un resultat si existance
		public Candidat findCandidatByEmail(String email);
		
		//Retourner un resultat si existance
		public int findCandidatByNomAndPrenom(String nom, String prenom);
		
		//retourner les 10 derniers candidats
		public 	List<Candidat> getLastTenCandidat();
		
		public Candidat findCandidatByIdWithoutTransaction(int id);
		
		public int existCandidat(String mail);
			
}
