package com.resotrekk.business;

import java.util.List;

import com.resotrekk.model.ExperienceCandidat;

public interface ExperienceIbusiness {

	
	public void ajouterExperienceCandidat(ExperienceCandidat experienceCandidat);
	public void modifierExperienceCandidat(ExperienceCandidat experienceCandidat);
	public void supprimerExperienceCandidat(ExperienceCandidat experienceCandidat);
	public List<ExperienceCandidat> retournerTousExperienceCandidat();
	public List<ExperienceCandidat> retournerTousExperienceCandidatSelonId(int idcandidat);
	
}
