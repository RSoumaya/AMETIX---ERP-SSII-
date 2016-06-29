package com.resotrekk.controller.offres;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

import com.resotrekk.business.CandidatIbusiness;
import com.resotrekk.business.CandidaturesIbusiness;
import com.resotrekk.business.OffreIbusiness;
import com.resotrekk.model.Candidat;
import com.resotrekk.model.Candidatures;
import com.resotrekk.model.Offre;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;

public class OffreCtr implements Serializable{

	//********************************************************************************

	private static final long serialVersionUID = 1L;

	private static final Logger logger = Logger.getLogger(OffreCtr.class);
	
	Offre offre = new Offre();
	List<Offre> list = new ArrayList<Offre>();
	OffreIbusiness offreBuss;
	
	CandidaturesIbusiness candidaturesBuss;
	Candidatures candidatures = new Candidatures();
	
	CandidatIbusiness candidatBuss;
	
	//********************************************************************************
	
	
	public String postulerOffre(){
		
		
		SecurityContext securityContext = SecurityContextHolder.getContext();
    	String username = securityContext.getAuthentication().getName();
    	BasicConfigurator.configure();
    	
    	try{
    	
    		if(username.equals("anonymousUser")){
        		
        		afficherMessageError("Vous devez vous connecter à votre compte afin de postuler");
        		logger.warn("Vous devez vous connecter à votre compte afin de postuler");
        	
    		}else{
        		
        		Candidat candidat = candidatBuss.retrounerCandidatSelonEmail(username);
        		
        		int exist = candidaturesBuss.retournerCandidatureSelonOffreIDandCandidatId(offre.getIdoffre(), candidat.getIdCandidat());
        		
        		if(exist >= 1){
        			
        			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Vous avez déja postulé à cette offre!"));
        		}else{
        				if(candidat.getCvCandidat().equals("rien")){
        					afficherMessageError("Vous devez charger votre cv avant de postuler");
        				}else{
        					candidatures.setCandidat(candidat);
                    		candidatures.setOffre(offre);	
                    		candidaturesBuss.ajouterCandidatures(candidatures);
                    		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Votre demande a été envoyé à notre service de recrutement!"));

        				}
        			        		}
        	}
    		
    	}catch(Exception e){
    		
    		 logger.warn("Erreur dans la méthode postuler. " + e);
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
	//********************************************************************************
	
	public Offre getOffre() {
		return offre;
	}
	public void setOffre(Offre offre) {
		this.offre = offre;
	}
	public List<Offre> getList() {
		
		list = offreBuss.retournerToutesOffre();
		return list;
	}
	public void setList(List<Offre> list) {
		this.list = list;
	}
	public OffreIbusiness getOffreBuss() {
		return offreBuss;
	}
	public void setOffreBuss(OffreIbusiness offreBuss) {
		this.offreBuss = offreBuss;
	}
	public CandidaturesIbusiness getCandidaturesBuss() {
		return candidaturesBuss;
	}
	public void setCandidaturesBuss(CandidaturesIbusiness candidaturesBuss) {
		this.candidaturesBuss = candidaturesBuss;
	}
	public Candidatures getCandidatures() {
		return candidatures;
	}
	public void setCandidatures(Candidatures candidatures) {
		this.candidatures = candidatures;
	}
	public CandidatIbusiness getCandidatBuss() {
		return candidatBuss;
	}
	public void setCandidatBuss(CandidatIbusiness candidatBuss) {
		this.candidatBuss = candidatBuss;
	}
	public static Logger getLogger() {
		return logger;
	}

	//********************************************************************************
}
