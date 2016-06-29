package com.resotrekk.business;

import java.util.List;

import com.resotrekk.model.Competences;

public interface CompetencesIbusiness {

	public void ajouterCompetence(Competences competences);
	public void supprimerCompetences(Competences competence);
	public List<Competences> retrournerTousLesCompetences();
	public Competences retournerCompetenceByLibelle(int idcomp);
}
