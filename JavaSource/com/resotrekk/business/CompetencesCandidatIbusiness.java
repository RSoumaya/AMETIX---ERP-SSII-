package com.resotrekk.business;

import java.util.List;

import com.resotrekk.model.CompetencesCandidat;

public interface CompetencesCandidatIbusiness {

	public void ajouterCompetenceCandidat(CompetencesCandidat competenceCandidat);
	
	public void supprimerCompetenceCandidat(CompetencesCandidat competenceCandidat);
	
	public List<CompetencesCandidat> retournerCompetencesCandidatById(
			int idcandidat);
}
