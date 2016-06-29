package com.resotrekk.controller.plus;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import org.springframework.dao.DataAccessException;

import com.resotrekk.business.CompetencesIbusiness;
import com.resotrekk.business.HistoriqueIbusiness;
import com.resotrekk.model.Administrateur;
import com.resotrekk.model.Competences;
import com.resotrekk.model.Historique;

public class CompetenceCtr {

	
	//*******************************************************************
	
	List<Competences> listcompetences = new ArrayList<Competences>();
	Competences competences = new Competences();
	Competences competencesModif = new Competences();
	Competences competencesDelete = new Competences();
	CompetencesIbusiness competencesBuss;

	private boolean disabledannuler = true;
	
	HistoriqueIbusiness historiqueBuss;
	//*******************************************************************
	
	public String ajouterCompetences(){
		
		try{
			
			competencesBuss.ajouterCompetence(competences);
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("La nouvelle compétence a été ajouté avec succés!"));
			setDisabledannuler(true);
			
			Historique historique = new Historique();
			Date dNow = new Date( );
			SimpleDateFormat ft = new SimpleDateFormat ("E yyyy.MM.dd 'à' hh:mm:ss a zzz");
			historique.setDescription("Une compétence à été ajouté/traité le "+ft.format(dNow));
			historiqueBuss.ajouterHistorique(historique);
				
			historique = new Historique();
			
			clear();
		}catch(DataAccessException e){
			e.printStackTrace();
		}
		return "success";
	}
	
	public String clear(){
		
		competences = new Competences();
		setDisabledannuler(true);
		return "success";
	}
	
	public String competenceToModif(){
		
		this.competences = competencesModif;
		setDisabledannuler(false);
		return "success";
	}
	
	public String competenceToDelete(){
		
		try{
			
			competencesBuss.supprimerCompetences(competencesDelete);
			
			Historique historique = new Historique();
			Date dNow = new Date( );
			SimpleDateFormat ft = new SimpleDateFormat ("E yyyy.MM.dd 'à' hh:mm:ss a zzz");
			historique.setDescription("La compétence "+competencesDelete.getLibelleCompetence() +" a été supprimé le "+ft.format(dNow));
			historiqueBuss.ajouterHistorique(historique);
				
			historique = new Historique();
			
		}catch(DataAccessException e){
			e.printStackTrace();
		}
		return "success";
	}
	
	
	public void init() {
	    
		FacesContext facesContext = FacesContext.getCurrentInstance();
	    
	    if (!facesContext.isPostback() && !facesContext.isValidationFailed()) {
	    	
	    	setDisabledannuler(true);
	    	competences = new Competences();
	    }
	}
	//*******************************************************************
	
	public List<Competences> getListcompetences() {
		listcompetences = competencesBuss.retrournerTousLesCompetences();
		return listcompetences;
	}
	public void setListcompetences(List<Competences> listcompetences) {
		this.listcompetences = listcompetences;
	}
	public Competences getCompetences() {
		return competences;
	}
	public void setCompetences(Competences competences) {
		this.competences = competences;
	}
	public Competences getCompetencesModif() {
		return competencesModif;
	}
	public void setCompetencesModif(Competences competencesModif) {
		this.competencesModif = competencesModif;
	}
	public Competences getCompetencesDelete() {
		return competencesDelete;
	}
	public void setCompetencesDelete(Competences competencesDelete) {
		this.competencesDelete = competencesDelete;
	}
	public CompetencesIbusiness getCompetencesBuss() {
		return competencesBuss;
	}
	public void setCompetencesBuss(CompetencesIbusiness competencesBuss) {
		this.competencesBuss = competencesBuss;
	}
	public boolean isDisabledannuler() {
		return disabledannuler;
	}
	public void setDisabledannuler(boolean disabledannuler) {
		this.disabledannuler = disabledannuler;
	}
	public HistoriqueIbusiness getHistoriqueBuss() {
		return historiqueBuss;
	}
	public void setHistoriqueBuss(HistoriqueIbusiness historiqueBuss) {
		this.historiqueBuss = historiqueBuss;
	}
	//*******************************************************************
}
