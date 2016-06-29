package com.resotrekk.dao;

import java.io.Serializable;
import java.util.List;

import com.resotrekk.model.RoleCandidat;

public interface RoleCandidatIdao {
	
	    //Ajouter un objet
		public void saveRoleCandidat(RoleCandidat roleCandidat);
		
		//Modifier un objet
		public void updateRoleCandidat(RoleCandidat roleCandidat);
		
		//Supprimer un objet
		public void deleteRoleCandidat(RoleCandidat roleCandidat);
		
		//Retourner un objet selon son id
		public RoleCandidat findRoleCandidatById(Serializable id);
		
		//Retourner tous les objet dans une liste
		public List<RoleCandidat> getAllRoleCandidat();

}
