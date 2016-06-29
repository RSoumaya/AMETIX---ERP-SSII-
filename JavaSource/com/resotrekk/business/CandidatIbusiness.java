package com.resotrekk.business;

import java.util.List;

import com.resotrekk.model.Candidat;

public interface CandidatIbusiness {

	
	public void ajouterCandidat(Candidat candidat);
	public void modifierCandidat(Candidat candidat);
	public void supprimerCandidat(Candidat candidat);
	public Candidat retrounerCandidat(int idcandidat);
	public Candidat retrounerCandidatSelonEmail(String adressemail);
	public int retrounerCandidatSelonNomPren(String nom, String prenom);
	public List<Candidat> retournerTousCandidat();
	public List<Candidat> retournerTenCandidat();
	public Candidat retrounerCandidatSansTransaction(int idcandidat);
	public int authentificationnCandidat(String mail);
}
