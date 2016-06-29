package com.resotrekk.controller.invitation;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.ComponentSystemEvent;

import java.util.Date;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;

import org.primefaces.event.ScheduleEntryMoveEvent;
import org.primefaces.model.DefaultScheduleEvent;
import org.primefaces.model.DefaultScheduleModel;
import org.primefaces.model.ScheduleEvent;
import org.primefaces.model.ScheduleModel;
import org.springframework.dao.DataAccessException;

import com.resotrekk.business.CandidatIbusiness;
import com.resotrekk.business.HistoriqueIbusiness;
import com.resotrekk.business.InvitationIbusiness;
import com.resotrekk.mail.InvitationMail;
import com.resotrekk.model.Candidat;
import com.resotrekk.model.Historique;
import com.resotrekk.model.Invitation;

public class InvitationCtr {

	//*****************************************************************************
	
	private ScheduleModel eventModel;
	private ScheduleEvent event = new DefaultScheduleEvent();
	
	InvitationIbusiness invitationBuss;
	Invitation invitation = new Invitation();
	List<Invitation> listinvitation = new ArrayList<Invitation>();
	
	List<Candidat> listcandidat = new ArrayList<Candidat>();
	CandidatIbusiness candidatBuss;
	Candidat candidat = new Candidat();
	
	private String valselectedCandidat = "";
	private LinkedHashMap<String, String> itemsCandidat = new LinkedHashMap<String, String>();//SelectOneMenu
	
	HistoriqueIbusiness historiqueBuss;
	//******************************************************************************
	
	public String ajouterInvitation() throws IOException{
		
		try{
			if (invitation.getStartdate().after(invitation.getEndDate())){
			
				afficherMessageError("La date de début doit etre inférieur à la date de fin");
			}else{
				
			
			int valselectedcandidat = Integer.valueOf(getValselectedCandidat());	
		    candidat =  candidatBuss.retrounerCandidatSansTransaction(valselectedcandidat);
		    invitation.setCandidat(candidat);
		    invitation.setTitle(candidat.getEmailCandidat());
		    invitationBuss.ajouterInvitation(invitation);
		    
		    Historique historique = new Historique();
			Date dNow = new Date( );
		    SimpleDateFormat ft = new SimpleDateFormat ("E yyyy.MM.dd 'à' hh:mm:ss a zzz");
			historique.setDescription("Une invitation a été envoyé le "+ft.format(dNow)+" à "+candidat.getNomCandidat()+" "+candidat.getPrenomCandidat());
			historiqueBuss.ajouterHistorique(historique);
			
			historique = new Historique();
		    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Votre invitation a été envoyé avec succés!"));
		    
		    InvitationMail invitmail = new InvitationMail();
		    invitmail.send(candidat, invitation);
		    
		    clear();
			}
		}catch(DataAccessException e){
			e.printStackTrace();
		}
		return "success";
	}
	
	
	//Afficher des messages d'erreurs
	public void afficherMessageError(String message){
			
			FacesMessage facesMessage = new FacesMessage();
			facesMessage.setSeverity(FacesMessage.SEVERITY_ERROR);
			facesMessage.setSummary(message);
			FacesContext facesContext = FacesContext.getCurrentInstance();
			facesContext.addMessage(null,facesMessage);

	}
		
	public void onEventMove(ScheduleEntryMoveEvent selectEvent) {
		
		try{
		event = selectEvent.getScheduleEvent();
		String title = event.getTitle();
				 
		Invitation invitation = invitationBuss.consulterInvitation(title);
			 
		invitation.setStartdate(event.getStartDate());
		invitation.setEndDate(event.getEndDate());
		invitation.setTitle(title);
		invitationBuss.ajouterInvitation(invitation);
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Votre invitation a été modifié avec succés!"));
		
		}catch(DataAccessException e){
			e.printStackTrace();
		}
	}
	
	public void clear(){
		
		valselectedCandidat = "";
		invitation = new Invitation();
	}
	
	//Rechargement de la liste des expériences selon la langue
	public void init(ComponentSystemEvent event) {
			
		if (!FacesContext.getCurrentInstance().isPostback()) {
					
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
	    	
	    	invitation = new Invitation();
	    }
	}
	//******************************************************************************
	
	public ScheduleModel getEventModel() {
		//charger toutes les invitations dans le calendrier
				eventModel = new DefaultScheduleModel();

				//get the list of all interventions
				listinvitation = invitationBuss.consulterInvitation();
					for(Invitation i : listinvitation){
						   eventModel.addEvent(new DefaultScheduleEvent(i.getTitle(), i.getStartdate(), i.getEndDate()));
					}
					
		return eventModel;
	}
	public void setEventModel(ScheduleModel eventModel) {
		this.eventModel = eventModel;
	}
	public ScheduleEvent getEvent() {
		return event;
	}
	public void setEvent(ScheduleEvent event) {
		this.event = event;
	}
	public InvitationIbusiness getInvitationBuss() {
		return invitationBuss;
	}
	public void setInvitationBuss(InvitationIbusiness invitationBuss) {
		this.invitationBuss = invitationBuss;
	}
	public Invitation getInvitation() {
		return invitation;
	}
	public void setInvitation(Invitation invitation) {
		this.invitation = invitation;
	}
	public List<Candidat> getListcandidat() {
		listcandidat = candidatBuss.retournerTousCandidat();
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
	public Candidat getCandidat() {
		return candidat;
	}
	public void setCandidat(Candidat candidat) {
		this.candidat = candidat;
	}
	public List<Invitation> getListinvitation() {
		return listinvitation;
	}
	public void setListinvitation(List<Invitation> listinvitation) {
		this.listinvitation = listinvitation;
	}
	public HistoriqueIbusiness getHistoriqueBuss() {
		return historiqueBuss;
	}
	public void setHistoriqueBuss(HistoriqueIbusiness historiqueBuss) {
		this.historiqueBuss = historiqueBuss;
	}
	//******************************************************************************
}
