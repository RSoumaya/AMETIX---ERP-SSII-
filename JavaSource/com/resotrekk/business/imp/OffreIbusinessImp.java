package com.resotrekk.business.imp;

import java.util.List;
import org.springframework.dao.DataAccessException;
import org.springframework.transaction.annotation.Transactional;
import com.resotrekk.business.OffreIbusiness;
import com.resotrekk.dao.OffreIdao;
import com.resotrekk.model.Offre;

public class OffreIbusinessImp implements OffreIbusiness{

	//*************************************************************
	
		OffreIdao offreDao;
		
	//*************************************************************
		
	@Transactional(readOnly = true)
	@Override
	public void ajouterOffre(Offre offre) {
		
		try{
			offreDao.saveOffre(offre);
		}catch(DataAccessException e){
			e.printStackTrace();
		}
		
	}

	@Transactional(readOnly = true)
	@Override
	public void modifierOffre(Offre offre) {
		
		try{
			offreDao.updateOffre(offre);
		}catch(DataAccessException e){
			e.printStackTrace();
		}
		
	}

	@Transactional(readOnly = true)
	@Override
	public void supprimerOffre(Offre offre) {
	
		try{
			offreDao.deleteOffre(offre);
		}catch(DataAccessException e){
			e.printStackTrace();
		}
		
	}

	@Transactional(readOnly = true)
	@Override
	public List<Offre> retournerToutesOffre() {
		
		List<Offre> list = null;
		try{
			list = offreDao.getAllOffre();
		}catch(DataAccessException e){
			e.printStackTrace();
		}
		return list;
	}
	//*************************************************************

	public OffreIdao getOffreDao() {
		return offreDao;
	}

	public void setOffreDao(OffreIdao offreDao) {
		this.offreDao = offreDao;
	}
		
	//*************************************************************
}
