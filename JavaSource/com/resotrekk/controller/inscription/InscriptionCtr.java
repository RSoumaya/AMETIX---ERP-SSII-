package com.resotrekk.controller.inscription;

import java.io.IOException;
import java.io.Serializable;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import org.apache.log4j.Logger;
import org.springframework.dao.DataAccessException;

import com.resotrekk.business.CandidatIbusiness;
import com.resotrekk.business.RoleCandidatIbusiness;
import com.resotrekk.controller.gerermesinfos.InformationsCtr;
import com.resotrekk.mail.ConfirmInscri;
import com.resotrekk.model.Candidat;
import com.resotrekk.model.RoleCandidat;

public class InscriptionCtr implements Serializable {

	//**********************************************************************************
	
	private static final long serialVersionUID = 1L;

	private static final Logger logger = Logger.getLogger(InscriptionCtr.class);
	
	Candidat candidat = new Candidat();
	RoleCandidat roleCandidat = new RoleCandidat();
	
	CandidatIbusiness candidatBuss;
	RoleCandidatIbusiness roleCandidatBuss;
	
	private String password = "";
	private String confirmpassword = "";
	
	//**********************************************************************************
	
	//Ajouter un candidat
	public void ajouterCandidat() throws NoSuchAlgorithmException, IOException{
		
		Candidat candidatVerif = new Candidat();
		int count = 0 ;
		try{
			
			candidatVerif = candidatBuss.retrounerCandidatSelonEmail(getCandidat().getEmailCandidat());
			count = candidatBuss.retrounerCandidatSelonNomPren(getCandidat().getNomCandidat(), getCandidat().getPrenomCandidat());
			
			if(candidatVerif != null | count == 1 ){
				
				System.out.println(count);
				afficherMessageError("Un compte existe déja avec cette adresse email");
				
			}else{
				System.out.println(count);
						if(password.equals(confirmpassword)){
							
							candidat.setPhotoprofil("resotrekkpphotoprofilpresotrekk.jpg");
							candidat.setCvCandidat("rien");
							candidat.setEnabled(true);
							candidat.setMotDePasseCandidat(getMD5Hash(getPassword()));
							candidatBuss.ajouterCandidat(candidat);
							
							roleCandidat.setAutorite("candidat");
							roleCandidat.setCandidat(candidat);
							roleCandidatBuss.ajouterRoleCandidat(roleCandidat);
							
							Candidat candidatmail = new Candidat();
							candidatmail = candidat;
							clear();
							FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Votre inscription a été confirmé.Vous pouvez vous connecter sur votre espace!")); 
							ConfirmInscri confirm = new ConfirmInscri();
							confirm.send(candidatmail);
							candidatmail = new Candidat();
							
						}else{
						
							afficherMessageError("Les deux mots de passes ne sont pas identiques");
					}
				
			}
		}catch(DataAccessException e){
			e.printStackTrace();
			logger.warn(e);
		}
	}
	
	//Vider l'objet 
	public void clear(){
		
		candidat = new Candidat();
		roleCandidat = new RoleCandidat();
		password = "";
		confirmpassword = "";
	}
	
	//Afficher des messages d'erreurs
	public void afficherMessageError(String message){
		
		FacesMessage facesMessage = new FacesMessage();
		facesMessage.setSeverity(FacesMessage.SEVERITY_ERROR);
		facesMessage.setSummary(message);
		FacesContext facesContext = FacesContext.getCurrentInstance();
		facesContext.addMessage(null,facesMessage);

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
	//**********************************************************************************
	
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
	
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getConfirmpassword() {
		return confirmpassword;
	}
	public void setConfirmpassword(String confirmpassword) {
		this.confirmpassword = confirmpassword;
	}
	public RoleCandidat getRoleCandidat() {
		return roleCandidat;
	}
	public void setRoleCandidat(RoleCandidat roleCandidat) {
		this.roleCandidat = roleCandidat;
	}
	public RoleCandidatIbusiness getRoleCandidatBuss() {
		return roleCandidatBuss;
	}
	public void setRoleCandidatBuss(RoleCandidatIbusiness roleCandidatBuss) {
		this.roleCandidatBuss = roleCandidatBuss;
	}
	public static Logger getLogger() {
		return logger;
	}
	//**********************************************************************************
}
