package com.resotrekk.business;

import java.util.List;
import com.resotrekk.model.LangueCandidat;

public interface LangueIbusiness {

	public void ajouterLangueCandidat(LangueCandidat langueCandidat);
	public void modifierLangueCandidat(LangueCandidat langueCandidat);
	public void supprimerLangueCandidat(LangueCandidat langueCandidat);
	public List<LangueCandidat> retournerTousLangueCandidat();
	public List<LangueCandidat> retournerTousLanguesCandidatSelonId(int idcandidat);
}
