package com.resotrekk.dao;

import java.util.List;
import com.resotrekk.model.Competences;

public interface CompetencesIdao {

	public void saveCompetence(Competences competences);
	public void deleteCompetence(Competences competences);
	public List<Competences> getAllCompetences();
	public Competences findCompetenceByLibelle(int idcomp);
	
}
