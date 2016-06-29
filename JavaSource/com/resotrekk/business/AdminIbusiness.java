package com.resotrekk.business;

import java.util.List;

import com.resotrekk.model.Administrateur;

public interface AdminIbusiness {

	public void ajouterAdmin(Administrateur admin);
	public List<Integer> retournerNombres(); 
	public int authentificationn(String mail, String password);
	public int authentificationnAdmin(String mail);
	public Administrateur retournerAdminMail(String mail);
	public List<Administrateur> retournerAdministrateur();
}
