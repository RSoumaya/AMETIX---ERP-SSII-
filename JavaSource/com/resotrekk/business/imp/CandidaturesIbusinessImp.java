package com.resotrekk.business.imp;


import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.transaction.annotation.Transactional;

import com.resotrekk.business.CandidaturesIbusiness;
import com.resotrekk.dao.CandidaturesIdao;
import com.resotrekk.model.Candidatures;

public class CandidaturesIbusinessImp implements CandidaturesIbusiness {

	//****************************************************************
	
	CandidaturesIdao candidaturesDao;

	//****************************************************************

	@Transactional(readOnly = true)
	@Override
	public void ajouterCandidatures(Candidatures candidatures) {
		
		try{
			candidaturesDao.saveCandidatures(candidatures);
		}catch(DataAccessException e){
			e.printStackTrace();
		}
	}

	@Transactional(readOnly = true)
	@Override
	public int retournerCandidatureSelonOffreIDandCandidatId(int offre,
			int candidat) {
		
		int exist = 0;
		try{
			exist = candidaturesDao.getCandidatureAsOffreIDandCandidatId(offre, candidat);
		}catch(DataAccessException e){
			e.printStackTrace();
		}
		return exist;
	}
	
	@Transactional(readOnly = true)
	@Override
	public List<Candidatures> listcandidatures() {
		
		List<Candidatures> list = null;
		try{
			list = candidaturesDao.getCandidatures();
		}catch(DataAccessException e){
			e.printStackTrace();
		}
		return list;
	}

	//****************************************************************
	
	public CandidaturesIdao getCandidaturesDao() {
		return candidaturesDao;
	}
	public void setCandidaturesDao(CandidaturesIdao candidaturesDao) {
		this.candidaturesDao = candidaturesDao;
	}
	//****************************************************************
}
