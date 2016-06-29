package com.resotrekk.business.imp;

import java.util.List;
import org.springframework.dao.DataAccessException;
import org.springframework.transaction.annotation.Transactional;
import com.resotrekk.business.CompetencesCandidatIbusiness;
import com.resotrekk.dao.CompetencesCandidatIdao;
import com.resotrekk.model.CompetencesCandidat;

public class CompetenceCandidatIbusinessImp implements CompetencesCandidatIbusiness{

	
	//***********************************************************
	
	CompetencesCandidatIdao competenceCandidatDao ; 
	
	//***********************************************************
	
	@Transactional(readOnly = true)
	@Override
	public void ajouterCompetenceCandidat(CompetencesCandidat competenceCandidat) {
		
		try{
			competenceCandidatDao.saveCompetencesCandidat(competenceCandidat);
		}catch(DataAccessException e){
			e.printStackTrace();
		}
		
	}

	@Transactional(readOnly = true)
	@Override
	public void supprimerCompetenceCandidat(
			CompetencesCandidat competenceCandidat) {
		
		try{
			competenceCandidatDao.deleteCompetencesCandidat(competenceCandidat);
		}catch(DataAccessException e){
			e.printStackTrace();
		}
		
	}
	
	@Transactional(readOnly = true)
	@Override
	public List<CompetencesCandidat> retournerCompetencesCandidatById(
			int idcandidat) {
		
		List<CompetencesCandidat> list = null;
		try{
			list = competenceCandidatDao.getAllCompetencesCandidatById(idcandidat);
		}catch(DataAccessException e){
			e.printStackTrace();
		}
		return list;
	}
	//***********************************************************
	
	public CompetencesCandidatIdao getCompetenceCandidatDao() {
		return competenceCandidatDao;
	}

	public void setCompetenceCandidatDao(
			CompetencesCandidatIdao competenceCandidatDao) {
		this.competenceCandidatDao = competenceCandidatDao;
	}

	
	//***********************************************************

}
