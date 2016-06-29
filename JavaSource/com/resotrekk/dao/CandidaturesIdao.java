package com.resotrekk.dao;

import java.util.List;

import com.resotrekk.model.Candidatures;

public interface CandidaturesIdao {

	public void saveCandidatures(Candidatures candidatures);
	public int getCandidatureAsOffreIDandCandidatId(int offre, int candidat);
	public List<Candidatures> getCandidatures();
}
