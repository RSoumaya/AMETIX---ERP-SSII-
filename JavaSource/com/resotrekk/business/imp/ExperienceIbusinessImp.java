package com.resotrekk.business.imp;

import java.util.List;
import org.springframework.dao.DataAccessException;
import org.springframework.transaction.annotation.Transactional;
import com.resotrekk.business.ExperienceIbusiness;
import com.resotrekk.dao.ExperienceIdao;
import com.resotrekk.model.ExperienceCandidat;

public class ExperienceIbusinessImp  implements ExperienceIbusiness{

	
	//****************************************************************
	
		ExperienceIdao expDao;
		
	//****************************************************************
		
	@Transactional(readOnly = true)
	@Override
	public void ajouterExperienceCandidat(ExperienceCandidat experienceCandidat) {
		
		try{
			expDao.saveExperienceCandidat(experienceCandidat);
		}catch(DataAccessException e){
			e.printStackTrace();
		}
		
	}

	@Transactional(readOnly = true)
	@Override
	public void modifierExperienceCandidat(ExperienceCandidat experienceCandidat) {
		
		try{
			expDao.updateExperienceCandidat(experienceCandidat);
		}catch(DataAccessException e){
			e.printStackTrace();
		}
		
	}

	@Transactional(readOnly = true)
	@Override
	public void supprimerExperienceCandidat(
			ExperienceCandidat experienceCandidat) {
		
		try{
			expDao.deleteExperienceCandidat(experienceCandidat);
		}catch(DataAccessException e){
			e.printStackTrace();
		}
		
	}

	@Transactional(readOnly = true)
	@Override
	public List<ExperienceCandidat> retournerTousExperienceCandidat() {
		
		List<ExperienceCandidat> list = null;
		try{
			list = expDao.getAllExperienceCandidat();
		}catch(DataAccessException e){
			e.printStackTrace();
		}
		return list;
	}

	@Transactional(readOnly = true)
	@Override
	public List<ExperienceCandidat> retournerTousExperienceCandidatSelonId(
			int idcandidat) {
		
		List<ExperienceCandidat> list = null;
		try{
			list = expDao.getAllExperienceCandidatById(idcandidat);
		}catch(DataAccessException e){
			e.printStackTrace();
		}
		return list;
	}
	//****************************************************************
	
	public ExperienceIdao getExpDao() {
		return expDao;
	}
	public void setExpDao(ExperienceIdao expDao) {
		this.expDao = expDao;
	}
		
	//****************************************************************
			
}
