package com.resotrekk.dao;

import java.util.List;

import com.resotrekk.model.Administrateur;

public interface AdminIdao {

	public List<Integer> getNumberDashboard(); 
	public void saveAdministrateur(Administrateur admin);
	public int authentification(String mail, String password);
	public int existAdmin(String mail);
	public Administrateur findAdminByMail(String mail);
	public List<Administrateur> getAllAdmin();
}
