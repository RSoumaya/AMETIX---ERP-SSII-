package com.resotrekk.business;

import java.util.List;

import com.resotrekk.model.RoleCandidat;

public interface RoleCandidatIbusiness {

	
	public void ajouterRoleCandidat(RoleCandidat roleCandidat);
	public void modifierRoleCandidat(RoleCandidat roleCandidat);
	public void supprimerRoleCandidat(RoleCandidat roleCandidat);
	public RoleCandidat retrounerRoleCandidat(int idRoleCandidat);
	public List<RoleCandidat> retournerTousRoleCandidat();
	
}
