package com.resotrekk.controller.candidat;

import java.io.Serializable;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import org.apache.log4j.Logger;
import org.springframework.dao.DataAccessException;

import com.resotrekk.business.CandidatIbusiness;
import com.resotrekk.business.CompetencesCandidatIbusiness;
import com.resotrekk.business.ExperienceIbusiness;
import com.resotrekk.business.LangueIbusiness;
import com.resotrekk.controller.offres.OffreCtr;
import com.resotrekk.mail.Candidatpassword;
import com.resotrekk.model.Candidat;
import com.resotrekk.model.CompetencesCandidat;
import com.resotrekk.model.ExperienceCandidat;
import com.resotrekk.model.LangueCandidat;

public class CandidatCtr implements Serializable{

	
	//*****************************************************************************************
	
	private static final long serialVersionUID = 1L;

	private static final Logger logger = Logger.getLogger(CandidatCtr.class);
	
	List<Candidat> listcandidat = new ArrayList<Candidat>();
	Candidat candidat = new Candidat();
	CandidatIbusiness candidatBuss;
	
	CompetencesCandidat competencesCandidat = new CompetencesCandidat();
	List<CompetencesCandidat> listcompetencecandidat = new ArrayList<CompetencesCandidat>();
	CompetencesCandidatIbusiness competenceCandidatBuss;
	
	LangueCandidat langueCandidat = new LangueCandidat();
	List<LangueCandidat> listlangueCandidat = new ArrayList<LangueCandidat>();
	LangueIbusiness langueCandidatBuss;
	
	
	ExperienceCandidat experienceCandidat = new ExperienceCandidat();
	List<ExperienceCandidat> listexperienceCandidat = new ArrayList<ExperienceCandidat>();
	ExperienceIbusiness experienceCandidatBuss;
	
	//*****************************************************************************************
	
	public String motdepasseOublie() throws NoSuchAlgorithmException{
	
		int count = 0 ;
		try{
			count = candidatBuss.authentificationnCandidat(candidat.getEmailCandidat());
			if(count == 1){
				candidat = candidatBuss.retrounerCandidatSelonEmail(candidat.getEmailCandidat());
				//Générer un nouveau mot de passe
				String chars = "abcdefghijklmnopqrstuvwxyz"; 
			    String pass = "";
			    for(int x=0;x<chars.length() ;x++)
			    {
			       int i = (int)Math.floor(Math.random() * 26); 
			       pass += chars.charAt(i);
			    }
			    
			   Candidatpassword mot = new Candidatpassword();
			   mot.send(candidat, pass);
			   
			   candidat.setMotDePasseCandidat(getMD5Hash(pass));
			   candidatBuss.modifierCandidat(candidat);
			   candidat = new Candidat();
			   FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Un message contenant votre nouveau mot de passe a été envoyé sur votre boite mail!"));
			   return "succeess";
			}else{
				afficherMessageError("Adresse n'existe pas");
				return "fail";
			}
		}catch(DataAccessException e){
			e.printStackTrace();
			logger.warn(e);
			return "fail";
		}
	}
	
	
	//Cryptage du mot de passe en md5
	public static String getMD5Hash(String s) throws NoSuchAlgorithmException {

		String result = s;
			if (s != null) {
				MessageDigest md = MessageDigest.getInstance("MD5"); // or "SHA-1"
				md.update(s.getBytes());
				BigInteger hash = new BigInteger(1, md.digest());
				result = hash.toString(16);
				while (result.length() < 32) { // 40 for SHA-1
					result = "0" + result;
					   	}
				}
		return result; 
	}
		
	//Afficher des messages d'erreurs
	public void afficherMessageError(String message){
					
		FacesMessage facesMessage = new FacesMessage();
		facesMessage.setSeverity(FacesMessage.SEVERITY_ERROR);
		facesMessage.setSummary(message);
		FacesContext facesContext = FacesContext.getCurrentInstance();
		facesContext.addMessage(null,facesMessage);

	}
	
	//*****************************************************************************************
	public List<Candidat> getListcandidat() {
		listcandidat = candidatBuss.retournerTousCandidat();
		return listcandidat;
	}
	public void setListcandidat(List<Candidat> listcandidat) {
		this.listcandidat = listcandidat;
	}
	public Candidat getCandidat() {
		return candidat;
	}
	public void setCandidat(Candidat candidat) {
		this.candidat = candidat;
	}
	public CandidatIbusiness getCandidatBuss() {
		return candidatBuss;
	}
	public void setCandidatBuss(CandidatIbusiness candidatBuss) {
		this.candidatBuss = candidatBuss;
	}
	public CompetencesCandidat getCompetencesCandidat() {
		return competencesCandidat;
	}
	public void setCompetencesCandidat(CompetencesCandidat competencesCandidat) {
		this.competencesCandidat = competencesCandidat;
	}
	public List<CompetencesCandidat> getListcompetencecandidat() {
		listcompetencecandidat = competenceCandidatBuss.retournerCompetencesCandidatById(candidat.getIdCandidat());
		return listcompetencecandidat;
	}
	public void setListcompetencecandidat(
			List<CompetencesCandidat> listcompetencecandidat) {
		this.listcompetencecandidat = listcompetencecandidat;
	}
	public CompetencesCandidatIbusiness getCompetenceCandidatBuss() {
		return competenceCandidatBuss;
	}
	public void setCompetenceCandidatBuss(
			CompetencesCandidatIbusiness competenceCandidatBuss) {
		this.competenceCandidatBuss = competenceCandidatBuss;
	}
	public LangueCandidat getLangueCandidat() {
		return langueCandidat;
	}
	public void setLangueCandidat(LangueCandidat langueCandidat) {
		this.langueCandidat = langueCandidat;
	}
	public List<LangueCandidat> getListlangueCandidat() {
		listlangueCandidat = langueCandidatBuss.retournerTousLanguesCandidatSelonId(candidat.getIdCandidat());
		return listlangueCandidat;
	}
	public void setListlangueCandidat(List<LangueCandidat> listlangueCandidat) {
		this.listlangueCandidat = listlangueCandidat;
	}
	public LangueIbusiness getLangueCandidatBuss() {
		return langueCandidatBuss;
	}
	public void setLangueCandidatBuss(LangueIbusiness langueCandidatBuss) {
		this.langueCandidatBuss = langueCandidatBuss;
	}
	public ExperienceCandidat getExperienceCandidat() {
		return experienceCandidat;
	}
	public void setExperienceCandidat(ExperienceCandidat experienceCandidat) {
		this.experienceCandidat = experienceCandidat;
	}
	public List<ExperienceCandidat> getListexperienceCandidat() {
		listexperienceCandidat = experienceCandidatBuss.retournerTousExperienceCandidatSelonId(candidat.getIdCandidat());
		return listexperienceCandidat;
	}
	public void setListexperienceCandidat(
			List<ExperienceCandidat> listexperienceCandidat) {
		this.listexperienceCandidat = listexperienceCandidat;
	}
	public ExperienceIbusiness getExperienceCandidatBuss() {
		return experienceCandidatBuss;
	}
	public void setExperienceCandidatBuss(ExperienceIbusiness experienceCandidatBuss) {
		this.experienceCandidatBuss = experienceCandidatBuss;
	}
	public static Logger getLogger() {
		return logger;
	}
	//*****************************************************************************************
}
