package com.resotrekk.business;

import java.util.List;

import com.resotrekk.model.Candidatures;

public interface CandidaturesIbusiness {

	public void ajouterCandidatures(Candidatures candidatures);
	public int retournerCandidatureSelonOffreIDandCandidatId(int offre, int candidat);
	public List<Candidatures> listcandidatures();
}
