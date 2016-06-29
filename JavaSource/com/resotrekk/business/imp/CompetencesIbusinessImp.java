package com.resotrekk.business.imp;

import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.transaction.annotation.Transactional;

import com.resotrekk.business.CompetencesIbusiness;
import com.resotrekk.dao.CompetencesIdao;
import com.resotrekk.model.Competences;

public class CompetencesIbusinessImp implements CompetencesIbusiness{

	//***************************************************************************
	
	CompetencesIdao competenceDao;
	
	//***************************************************************************
	
	@Transactional(readOnly = true)
	@Override
	public void ajouterCompetence(Competences competences) {
		
		try{
			competenceDao.saveCompetence(competences);
		}catch(DataAccessException e){
			e.printStackTrace();
		}
		
	}
	
	@Transactional(readOnly = true)
	@Override
	public void supprimerCompetences(Competences competence) {
		
		try{
			competenceDao.deleteCompetence(competence);
		}catch(DataAccessException e){
			e.printStackTrace();
		}
		
	}
	
	@Transactional(readOnly = true)
	@Override
	public List<Competences> retrournerTousLesCompetences() {
		
		List<Competences> list = null;
		try{
			list = competenceDao.getAllCompetences();
		}catch(DataAccessException e){
			e.printStackTrace();
		}
		return list;
	}

	@Transactional(readOnly = true)
	@Override
	public Competences retournerCompetenceByLibelle(int idcomp) {
		
		Competences competences = null;
		try{
			competences = competenceDao.findCompetenceByLibelle(idcomp);
		}catch(DataAccessException e){
			e.printStackTrace();
		}
		return competences;
	}
	
	//***************************************************************************
	
	public CompetencesIdao getCompetenceDao() {
		return competenceDao;
	}

	public void setCompetenceDao(CompetencesIdao competenceDao) {
		this.competenceDao = competenceDao;
	}
	//***************************************************************************
}
