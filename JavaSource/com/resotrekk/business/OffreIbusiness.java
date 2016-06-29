package com.resotrekk.business;

import java.util.List;
import com.resotrekk.model.Offre;

public interface OffreIbusiness {

	
	public void ajouterOffre(Offre offre);
	public void modifierOffre(Offre offre);
	public void supprimerOffre(Offre offre);
	public List<Offre> retournerToutesOffre();
}
