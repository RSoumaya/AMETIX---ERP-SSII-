package com.resotrekk.business.imp;

import java.util.List;
import org.springframework.dao.DataAccessException;
import org.springframework.transaction.annotation.Transactional;
import com.resotrekk.business.RoleCandidatIbusiness;
import com.resotrekk.dao.RoleCandidatIdao;
import com.resotrekk.model.RoleCandidat;

public class RoleCandidatIbusinessImp implements RoleCandidatIbusiness {


	//****************************************************************
	
	RoleCandidatIdao rolecandidatDao;
		
	//****************************************************************
		
	@Transactional(readOnly = true)	
	@Override
	public void ajouterRoleCandidat(RoleCandidat roleCandidat) {
		
		try{
			rolecandidatDao.saveRoleCandidat(roleCandidat);
		}catch(DataAccessException e){
			e.printStackTrace();
		}
	}

	@Transactional(readOnly = true)
	@Override
	public void modifierRoleCandidat(RoleCandidat roleCandidat) {
		
		try{
			rolecandidatDao.updateRoleCandidat(roleCandidat);
		}catch(DataAccessException e){
			e.printStackTrace();
		}
	}

	@Transactional(readOnly = true)
	@Override
	public void supprimerRoleCandidat(RoleCandidat roleCandidat) {
		
		try{
			rolecandidatDao.deleteRoleCandidat(roleCandidat);
		}catch(DataAccessException e){
			e.printStackTrace();
		}
	}

	@Transactional(readOnly = true)
	@Override
	public RoleCandidat retrounerRoleCandidat(int idRoleCandidat) {
		
		RoleCandidat rolecandidat = null;
		try{
			rolecandidat  = rolecandidatDao.findRoleCandidatById(rolecandidat);
		}catch(DataAccessException e){
			e.printStackTrace();
		}
		return rolecandidat;
	}

	@Transactional(readOnly = true)
	@Override
	public List<RoleCandidat> retournerTousRoleCandidat() {
		
		List<RoleCandidat> list = null;
		try{
			list = rolecandidatDao.getAllRoleCandidat();
		}catch(DataAccessException e){
			e.printStackTrace();
		}
		return list;
	}

	//****************************************************************
	
	public RoleCandidatIdao getRolecandidatDao() {
		return rolecandidatDao;
	}

	public void setRolecandidatDao(RoleCandidatIdao rolecandidatDao) {
		this.rolecandidatDao = rolecandidatDao;
	}
			
	//****************************************************************
}
