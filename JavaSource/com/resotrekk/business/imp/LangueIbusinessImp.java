package com.resotrekk.business.imp;

import java.util.List;
import org.springframework.dao.DataAccessException;
import org.springframework.transaction.annotation.Transactional;
import com.resotrekk.business.LangueIbusiness;
import com.resotrekk.dao.LangueIdao;
import com.resotrekk.model.LangueCandidat;

public class LangueIbusinessImp implements LangueIbusiness {

	//*************************************************************
	
	LangueIdao langueDao;
	
	//*************************************************************
	
	@Transactional(readOnly = true)
	@Override
	public void ajouterLangueCandidat(LangueCandidat langueCandidat) {
		
		try{
			langueDao.saveLangueCandidat(langueCandidat);
		}catch(DataAccessException e){
			e.printStackTrace();
		}
		
	}

	@Transactional(readOnly = true)
	@Override
	public void modifierLangueCandidat(LangueCandidat langueCandidat) {
		
		try{
			langueDao.updateLangueCandidat(langueCandidat);
		}catch(DataAccessException e){
			e.printStackTrace();
		}
		
	}

	@Transactional(readOnly = true)
	@Override
	public void supprimerLangueCandidat(LangueCandidat langueCandidat) {
		
		try{
			langueDao.deleteLangueCandidat(langueCandidat);
		}catch(DataAccessException e){
			e.printStackTrace();
		}
		
	}

	@Transactional(readOnly = true)
	@Override
	public List<LangueCandidat> retournerTousLangueCandidat() {
		
		List<LangueCandidat> list = null;
		try{
			list = langueDao.getAllLangueCandidat();
		}catch(DataAccessException e){
			e.printStackTrace();
		}
		return list;
	}

	@Transactional(readOnly = true)
	@Override
	public List<LangueCandidat> retournerTousLanguesCandidatSelonId(
			int idcandidat) {
		
		List<LangueCandidat> list = null;
		try{
			list = langueDao.getAllLangueCandidatById(idcandidat);
		}catch(DataAccessException e){
			e.printStackTrace();
		}
		return list; 
	}

	//*************************************************************
	
	public LangueIdao getLangueDao() {
		return langueDao;
	}

	public void setLangueDao(LangueIdao langueDao) {
		this.langueDao = langueDao;
	}
		
	//*************************************************************
		
}
