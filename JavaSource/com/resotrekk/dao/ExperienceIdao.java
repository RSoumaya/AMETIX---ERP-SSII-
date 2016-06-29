package com.resotrekk.dao;

import java.util.List;
import com.resotrekk.model.ExperienceCandidat;

public interface ExperienceIdao {


	 //Ajouter un objet
	public void saveExperienceCandidat(ExperienceCandidat experienceCandidat);
	
	//Modifier un objet
	public void updateExperienceCandidat(ExperienceCandidat experienceCandidat);
	
	//Supprimer un objet
	public void deleteExperienceCandidat(ExperienceCandidat experienceCandidat);
	
	//Retourner tous les objet dans une liste
	public List<ExperienceCandidat> getAllExperienceCandidat();
	
	//Retourner tous les objet dans une liste selon id du candidat
	public List<ExperienceCandidat> getAllExperienceCandidatById(int idcandidat);
	
}
