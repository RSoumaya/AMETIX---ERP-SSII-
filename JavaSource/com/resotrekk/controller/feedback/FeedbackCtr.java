package com.resotrekk.controller.feedback;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.ComponentSystemEvent;

import org.springframework.dao.DataAccessException;

import com.resotrekk.business.CandidatIbusiness;
import com.resotrekk.business.FeedbackIbusiness;
import com.resotrekk.business.HistoriqueIbusiness;
import com.resotrekk.model.Candidat;
import com.resotrekk.model.Feedback;
import com.resotrekk.model.Historique;
import com.resotrekk.model.Invitation;

public class FeedbackCtr {

	
	//***************************************************************
	
	Feedback feedback = new Feedback();
	FeedbackIbusiness feedbackBuss;
	List<Feedback> listfeedback = new ArrayList<Feedback>();
	Feedback feebackModif = new Feedback();
	
	List<Candidat> listcandidat = new ArrayList<Candidat>();
	CandidatIbusiness candidatBuss;
	
	private String valselectedCandidat = "";
	private LinkedHashMap<String, String> itemsCandidat = new LinkedHashMap<String, String>();//SelectOneMenu
	
	private boolean disabledannuler = true;
	
	HistoriqueIbusiness historiqueBuss;
	
	//***************************************************************
	
	public String ajouterfeedback(){
		
		try{
			
			
			int valselectedcandidat = Integer.valueOf(getValselectedCandidat());
		    Candidat candidat =  candidatBuss.retrounerCandidat(valselectedcandidat);
		    feedback.setCandidat(candidat);
		    
			feedbackBuss.ajouterFeedback(feedback);
			
			Historique historique = new Historique();
			Date dNow = new Date( );
			SimpleDateFormat ft = new SimpleDateFormat ("E yyyy.MM.dd 'à' hh:mm:ss a zzz");
			historique.setDescription("Un feedback à été réalisé le "+ft.format(dNow));
			historiqueBuss.ajouterHistorique(historique);
				
			historique = new Historique();
			
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Votre feedback a été envoyé avec succés!"));
	
			clear();
			setDisabledannuler(true);
			valselectedCandidat = "";
		}catch(DataAccessException e){
			e.printStackTrace();
		}
		return "success";
	}
	
	public String clear(){
	
		feedback = new Feedback();
		setDisabledannuler(true);
		valselectedCandidat = "";
		return "success";
	}
	
	public String feedbackToModif(){
		
		this.feedback = feebackModif;
		setDisabledannuler(false);
		valselectedCandidat = Integer.toString(feedback.getCandidat().getIdCandidat());
		return "success";
	}
	
	
	//Rechargement de la liste des expériences selon la langue
	public void init(ComponentSystemEvent event) {
		
		if (!FacesContext.getCurrentInstance().isPostback()) {
			
			setDisabledannuler(true);
			listcandidat = candidatBuss.retournerTousCandidat();
			Iterator itercandidat = listcandidat.iterator();
				if (!itercandidat.hasNext()) {
					System.out.println("La liste est vide");
					return;
				}
				while (itercandidat.hasNext()) {
					Candidat candidat = (Candidat) itercandidat.next();
					String valeurcandidat = candidat.getNomCandidat()+" "+candidat.getPrenomCandidat() + " ---- " + candidat.getEmailCandidat();
					String clecandidat = String.valueOf(candidat.getIdCandidat());
					itemsCandidat.put(valeurcandidat, clecandidat);	    		
				}	
			}
		}
	
	
	public void initt() {
	    
		FacesContext facesContext = FacesContext.getCurrentInstance();
	    
	    if (!facesContext.isPostback() && !facesContext.isValidationFailed()) {
	    	
	    	feedback = new Feedback();
	    }
	}
	//***************************************************************
	
	public List<Candidat> getListcandidat() {
		return listcandidat;
	}
	public void setListcandidat(List<Candidat> listcandidat) {
		this.listcandidat = listcandidat;
	}
	public CandidatIbusiness getCandidatBuss() {
		return candidatBuss;
	}
	public void setCandidatBuss(CandidatIbusiness candidatBuss) {
		this.candidatBuss = candidatBuss;
	}
	public Feedback getFeedback() {
		return feedback;
	}
	public void setFeedback(Feedback feedback) {
		this.feedback = feedback;
	}
	public FeedbackIbusiness getFeedbackBuss() {
		return feedbackBuss;
	}
	public void setFeedbackBuss(FeedbackIbusiness feedbackBuss) {
		this.feedbackBuss = feedbackBuss;
	}
	public List<Feedback> getListfeedback() {
		listfeedback = feedbackBuss.retournerTousFeedback();
		return listfeedback;
	}
	public void setListfeedback(List<Feedback> listfeedback) {
		this.listfeedback = listfeedback;
	}
	public Feedback getFeebackModif() {
		return feebackModif;
	}
	public void setFeebackModif(Feedback feebackModif) {
		this.feebackModif = feebackModif;
	}
	public String getValselectedCandidat() {
		return valselectedCandidat;
	}
	public void setValselectedCandidat(String valselectedCandidat) {
		this.valselectedCandidat = valselectedCandidat;
	}
	public LinkedHashMap<String, String> getItemsCandidat() {
		return itemsCandidat;
	}
	public void setItemsCandidat(LinkedHashMap<String, String> itemsCandidat) {
		this.itemsCandidat = itemsCandidat;
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
	//***************************************************************
}
