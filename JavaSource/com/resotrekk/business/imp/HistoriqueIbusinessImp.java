package com.resotrekk.business.imp;

import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.transaction.annotation.Transactional;

import com.resotrekk.business.HistoriqueIbusiness;
import com.resotrekk.dao.HistoriqueIdao;
import com.resotrekk.model.Historique;

public class HistoriqueIbusinessImp implements HistoriqueIbusiness{

	
	//************************************************************************************************
	
	HistoriqueIdao historiqueDao;
	
	//************************************************************************************************
	
	@Transactional(readOnly = true)
	@Override
	public void ajouterHistorique(Historique historique) {
		

		try{
			historiqueDao.saveHistorique(historique);
		}catch(DataAccessException e){
			e.printStackTrace();
		}		
	}

	@Transactional(readOnly = true)
	@Override
	public List<Historique> retournerHistorique() {
		
		List<Historique> list = null;
		try{
			list = historiqueDao.getHistorique();
		}catch(DataAccessException e){
			e.printStackTrace();
		}	
		return list;
	}

	//************************************************************************************************
	
	public HistoriqueIdao getHistoriqueDao() {
		return historiqueDao;
	}
	public void setHistoriqueDao(HistoriqueIdao historiqueDao) {
		this.historiqueDao = historiqueDao;
	}
	
	//************************************************************************************************
}
