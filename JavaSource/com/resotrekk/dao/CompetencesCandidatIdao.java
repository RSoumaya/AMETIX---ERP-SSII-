package com.resotrekk.dao;

import java.util.List;
import com.resotrekk.model.CompetencesCandidat;

public interface CompetencesCandidatIdao {

	
	public void saveCompetencesCandidat(CompetencesCandidat competencesCandidat);
	
	public void deleteCompetencesCandidat(CompetencesCandidat competencesCandidat);
	
	//Retourner tous les objet dans une liste selon id du candidat
	public List<CompetencesCandidat> getAllCompetencesCandidatById(int idcandidat);
}
