package com.resotrekk.controller.gerermesinfos;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import org.apache.log4j.Logger;
import org.primefaces.model.UploadedFile;
import org.springframework.dao.DataAccessException;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

import com.resotrekk.business.CandidatIbusiness;
import com.resotrekk.business.CompetencesCandidatIbusiness;
import com.resotrekk.business.CompetencesIbusiness;
import com.resotrekk.business.ExperienceIbusiness;
import com.resotrekk.business.LangueIbusiness;
import com.resotrekk.controller.offres.OffreCtr;
import com.resotrekk.model.Candidat;
import com.resotrekk.model.Competences;
import com.resotrekk.model.CompetencesCandidat;
import com.resotrekk.model.ExperienceCandidat;
import com.resotrekk.model.LangueCandidat;
import com.resotrekk.transmissionvariable.MultipleMenu;

public class InformationsCtr implements Serializable{

	//********************************************************************************
	
	private static final long serialVersionUID = 1L;

	private static final Logger logger = Logger.getLogger(InformationsCtr.class);
	
	CandidatIbusiness candidatBuss;
	Candidat candidat = new Candidat();
	
	LangueCandidat langueCandidat = new LangueCandidat();
	LangueCandidat langueCandidatModif = new LangueCandidat();
	LangueCandidat langueCandidatDelete = new LangueCandidat();
	
	LangueIbusiness langueBuss;
	
	List<LangueCandidat> list = new ArrayList<LangueCandidat>();
	
	
	ExperienceCandidat experienceCandidat = new ExperienceCandidat();
	ExperienceCandidat experienceCandidatModif = new ExperienceCandidat();
	ExperienceCandidat experienceCandidatDelete = new ExperienceCandidat();
	
	ExperienceIbusiness experienceBuss;
	
	List<ExperienceCandidat> listexperience = new ArrayList<ExperienceCandidat>();
	
	
	Candidat candidatUpdated = new Candidat();
	private String oldPassword = "";
	private String newPassword = "";
	private String confirmnewPassword = "";
	
	
	private UploadedFile file;
	private String destinationDocument = "C:\\Program Files\\Apache Software Foundation\\Tomcat 8.0\\webapps\\resotrekk\\frontOffice\\CVcandidat\\" ;
	private UploadedFile filephotoprofil;
	private String destinationphotoprofil = "C:\\Program Files\\Apache Software Foundation\\Tomcat 8.0\\webapps\\resotrekk\\frontOffice\\photoprofil\\" ;

	 
	CompetencesIbusiness competencesBuss;
	List<Competences> listcomp = new ArrayList<Competences>();
	private LinkedHashMap<String, String> itemscompetences = new LinkedHashMap<String, String>();
	private String valselectetcompetences;
	 
	 
	CompetencesCandidat competenceCandidat = new CompetencesCandidat();
	CompetencesCandidatIbusiness competenceCandidatBuss;
	List<CompetencesCandidat> listcompcandidat = new ArrayList<CompetencesCandidat>();
	CompetencesCandidat competenceCandidatForModif = new CompetencesCandidat();
	CompetencesCandidat competenceCandidatForDelete = new CompetencesCandidat();
	
	//********************************************************************************
	
	public String modifierInformations(){
		
		try{
			
			init();
			candidatBuss.modifierCandidat(candidat);
			
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Vos informations ont été modifié avec succés!"));		
		}catch(DataAccessException e){			
			logger.warn(e);
		}
		return "success";
		
	}
	
	//Importer les infos des le depart
	public void init() {
	    
		FacesContext facesContext = FacesContext.getCurrentInstance();
	    
	    if (!facesContext.isPostback() && !facesContext.isValidationFailed()) {
	    		    	
	    	SecurityContext securityContext = SecurityContextHolder.getContext();
	    	String username = securityContext.getAuthentication().getName();
	    	candidat = candidatBuss.retrounerCandidatSelonEmail(username);
	    	
	    
			listcomp =  competencesBuss.retrournerTousLesCompetences();
			Iterator itercompetences = listcomp.iterator();
			
			if (! itercompetences.hasNext()) {
				System.out.println("La liste est vide");
				return;
			}
			while ( itercompetences.hasNext()) {
				Competences competence = (Competences) itercompetences.next();
				String valeurcompetence = competence.getLibelleCompetence();
				String cleecompetence = String.valueOf(competence.getIdcompetences());
				itemscompetences.put(valeurcompetence,cleecompetence);
				
			}
	    	
	    }
	}
	
	//*****************************************Langue*******************************************************************
	public String ajouterLangue(){
    	
		try{
			
			SecurityContext securityContext = SecurityContextHolder.getContext();
	    	String username = securityContext.getAuthentication().getName();
	    	candidat = candidatBuss.retrounerCandidatSelonEmail(username);
	    	
	    	
			langueCandidat.setCandidat(candidat);
			langueBuss.ajouterLangueCandidat(langueCandidat);
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Vos informations ont été modifié avec succés!"));
			clear();
			
		}catch(DataAccessException e){
			e.printStackTrace();
			logger.warn(e);
		}
		return "success";
	}
	
	public String langueModif(){
	
		this.langueCandidat = langueCandidatModif;
		return "success";
	}
	
	public String langueDelete(){
		
		try{
			
			langueBuss.supprimerLangueCandidat(langueCandidatDelete);
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Vos informations ont été modifié avec succés!"));
		}catch(DataAccessException e){
			
			logger.warn(e);
		}
		
		return "success";
	}
	
	//*****************************************Experience*******************************************************************

	public String ajouterExperience(){
    	
		
		try{
			
			SecurityContext securityContext = SecurityContextHolder.getContext();
	    	String username = securityContext.getAuthentication().getName();
	    	candidat = candidatBuss.retrounerCandidatSelonEmail(username);
	    	
	    	
			experienceCandidat.setCandidat(candidat);
			experienceBuss.ajouterExperienceCandidat(experienceCandidat);
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Vos informations ont été modifié avec succés!"));
			clear();
			
		}catch(DataAccessException e){
			e.printStackTrace();
			logger.warn(e);
		}
		return "success";
	}
	
	public String ExperienceModif(){
	
		this.experienceCandidat = experienceCandidatModif;
		return "success";
	}
	
	public String ExperienceDelete(){
		
		try{
			
		experienceBuss.supprimerExperienceCandidat(experienceCandidatDelete);
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Vos informations ont été modifié avec succés!"));
			
		}catch(DataAccessException e){
			
		logger.warn(e);
		}
		
	return "success";
	}
	
	//***********************************Modifier mon compte*********************************************
	
	public String modifierMonCompte() throws NoSuchAlgorithmException{
		
		
		if(!candidat.getMotDePasseCandidat().equals(getMD5Hash(getOldPassword()))){
			
			afficherMessageError("Vous devez vérifier votre mot de passe");
		}else{
			
			if(getNewPassword().equals(getConfirmnewPassword())){
				
				candidat.setMotDePasseCandidat(getMD5Hash(getNewPassword()));
				candidatBuss.modifierCandidat(candidat);
				clear();
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Vos informations ont été modifié avec succés!"));
			}else{
				afficherMessageError("Les deux mots de passes ne sont pas identiques");
			}
		}
		
		return "success";
	}
	
		
	//********************************************charger CV et photoprofil*****************************************************************
	
	public void uploadphotoprofil() throws IOException {
	    
		
		String photodeprofilextension = "";
		
		long taille = filephotoprofil.getSize();
		double x = (double) taille / 1024;
		
		String expression = "(.*?)\\.(gif|jpg|jpeg|gif|png|PNG|GIF|JPG|JPEG|bmp)$";
		
		 if((!filephotoprofil.getFileName().matches(expression)) ) {
				
			 FacesContext.getCurrentInstance().
		        addMessage(null,
		            new FacesMessage(FacesMessage.SEVERITY_ERROR,
		                                       "Vous devez entrer un format de photo!"+ filephotoprofil.getFileName(), null));
			 
				 }else{
					 
				 
		if(filephotoprofil != null && x <=3000) {

			String extension = filephotoprofil.getFileName().substring(filephotoprofil.getFileName().lastIndexOf("."));
			photodeprofilextension = candidat.getIdCandidat()+extension;			 
	        candidat.setPhotoprofil(photodeprofilextension);
	        candidatBuss.modifierCandidat(candidat);
	        FacesMessage message = new FacesMessage("Succesful", filephotoprofil.getFileName() + " is uploaded.");
	            try {
					copyFile(photodeprofilextension, filephotoprofil.getInputstream(),destinationphotoprofil);
			} catch (Exception e) {
					e.printStackTrace();
		}
	}else{
		FacesContext.getCurrentInstance().
        addMessage(null,
            new FacesMessage(FacesMessage.SEVERITY_ERROR,
                                       "Photo invalid ! "+ filephotoprofil.getFileName(), null));
	}
				 }
}
		
		
	public void upload() throws IOException {
		
		
		String expression = "([^\\s]+(\\.(?i)(pdf|PDF))$)";
		
		 if((!file.getFileName().matches(expression)) ) {
				
			 FacesContext.getCurrentInstance().
		        addMessage(null,
		            new FacesMessage(FacesMessage.SEVERITY_ERROR,
		                                       "Vous devez entrer un format PDF!"+ file.getFileName(), null));
			 
				 }else{
		 
	        if(file != null) {
	        	
	        	System.out.println(file.getFileName().toString());
	        	
	        	long taille = file.getSize();
				double x = (double) taille / 1024;
				System.out.println(x);
				
	            try {
	            	if(x <= 500){
						candidat.setCvCandidat(candidat.getIdCandidat()+".pdf");
			        	candidatBuss.modifierCandidat(candidat);
			            FacesMessage message = new FacesMessage("Succesful", file.getFileName() + " is uploaded.");
			            copyFile(String.valueOf(candidat.getIdCandidat())+".pdf", file.getInputstream(),destinationDocument);
					}else{
						 FacesContext.getCurrentInstance().
					        addMessage(null,
					            new FacesMessage(FacesMessage.SEVERITY_ERROR,
					                                       "La taille dépasse 500 Ko ! "+ file.getFileName(), null));
					}
	            	
				} catch (Exception e) {
					e.printStackTrace();
				}
	        }
	}
	    }
	
	//Méthode générique de déplacement des fichiers CV et Lettre de motivation
	public void copyFile(String fileName, InputStream in,String fileDestination) throws FileNotFoundException {
		        try {
		            // write the inputStream to a FileOutputStream
		            OutputStream out = new FileOutputStream(new File(fileDestination + fileName));
		            int read = 0;
		            byte[] bytes = new byte[1024];
		            while ((read = in.read(bytes)) != -1) {
		                out.write(bytes, 0, read);
		            }
		            in.close();
		            out.flush();
		            out.close();
		            System.out.println("New file created!");
		        }catch (IOException e) {
		            System.out.println(e.getMessage());
		        }
		    }
	
	//**********************************************Mes competences****************************************************
	
		public String ajouterCompetences(){
    	
		
		try{
		
			SecurityContext securityContext = SecurityContextHolder.getContext();
	    	String username = securityContext.getAuthentication().getName();
	    	candidat = candidatBuss.retrounerCandidatSelonEmail(username);
	    	
	    	Competences comp = new Competences();
	    	comp = competencesBuss.retournerCompetenceByLibelle(Integer.valueOf(getValselectetcompetences()));
	    	
	    	competenceCandidat.setCompetences(comp);
	    	competenceCandidat.setCandidat(candidat);
			
			competenceCandidatBuss.ajouterCompetenceCandidat(competenceCandidat);
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Vos informations ont été modifié avec succés!"));
			clear();
			
		}catch(DataAccessException e){
			e.printStackTrace();
		}
		return "success";
	}
	
		
	public String competencesModif(){
			
			this.competenceCandidat = competenceCandidatForModif;
			valselectetcompetences = Integer.toString(competenceCandidat.getCompetences().getIdcompetences());
			return "success";
	}
		
	public String competencesDelete(){
			
			competenceCandidatBuss.supprimerCompetenceCandidat(competenceCandidatForDelete);
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Vos informations ont été modifié avec succés!"));
			return "success";
	}
	
	//*****************************************************************************************************************
	
	public void clear(){
		
		langueCandidat = new LangueCandidat();
		experienceCandidat = new ExperienceCandidat();
		oldPassword = "";
		confirmnewPassword = "";
		newPassword = "";
		competenceCandidat = new CompetencesCandidat();
		valselectetcompetences = "";
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
	
	//********************************************************************************
	
	public CandidatIbusiness getCandidatBuss() {
		return candidatBuss;
	}
	public void setCandidatBuss(CandidatIbusiness candidatBuss) {
		this.candidatBuss = candidatBuss;
	}
	public Candidat getCandidat() {
		return candidat;
	}
	public void setCandidat(Candidat candidat) {
		this.candidat = candidat;
	}
	public LangueCandidat getLangueCandidat() {
		return langueCandidat;
	}
	public void setLangueCandidat(LangueCandidat langueCandidat) {
		this.langueCandidat = langueCandidat;
	}
	public LangueCandidat getLangueCandidatModif() {
		return langueCandidatModif;
	}
	public void setLangueCandidatModif(LangueCandidat langueCandidatModif) {
		this.langueCandidatModif = langueCandidatModif;
	}
	public LangueCandidat getLangueCandidatDelete() {
		return langueCandidatDelete;
	}
	public void setLangueCandidatDelete(LangueCandidat langueCandidatDelete) {
		this.langueCandidatDelete = langueCandidatDelete;
	}
	public LangueIbusiness getLangueBuss() {
		return langueBuss;
	}
	public void setLangueBuss(LangueIbusiness langueBuss) {
		this.langueBuss = langueBuss;
	}
	public List<LangueCandidat> getList() {
		
		SecurityContext securityContext = SecurityContextHolder.getContext();
    	String username = securityContext.getAuthentication().getName();
    	candidat = candidatBuss.retrounerCandidatSelonEmail(username);
		list = langueBuss.retournerTousLanguesCandidatSelonId(candidat.getIdCandidat());
		return list;
	}
	public void setList(List<LangueCandidat> list) {
		this.list = list;
	}
	public ExperienceCandidat getExperienceCandidat() {
		return experienceCandidat;
	}
	public void setExperienceCandidat(ExperienceCandidat experienceCandidat) {
		this.experienceCandidat = experienceCandidat;
	}
	public ExperienceCandidat getExperienceCandidatModif() {
		return experienceCandidatModif;
	}
	public void setExperienceCandidatModif(
			ExperienceCandidat experienceCandidatModif) {
		this.experienceCandidatModif = experienceCandidatModif;
	}
	public ExperienceCandidat getExperienceCandidatDelete() {
		return experienceCandidatDelete;
	}
	public void setExperienceCandidatDelete(
			ExperienceCandidat experienceCandidatDelete) {
		this.experienceCandidatDelete = experienceCandidatDelete;
	}
	public ExperienceIbusiness getExperienceBuss() {
		return experienceBuss;
	}
	public void setExperienceBuss(ExperienceIbusiness experienceBuss) {
		this.experienceBuss = experienceBuss;
	}
	public List<ExperienceCandidat> getListexperience() {
		
		SecurityContext securityContext = SecurityContextHolder.getContext();
    	String username = securityContext.getAuthentication().getName();
    	candidat = candidatBuss.retrounerCandidatSelonEmail(username);
		listexperience = experienceBuss.retournerTousExperienceCandidatSelonId(candidat.getIdCandidat());
		return listexperience;
	}
	public void setListexperience(List<ExperienceCandidat> listexperience) {
		this.listexperience = listexperience;
	}
	public Candidat getCandidatUpdated() {
		return candidatUpdated;
	}
	public void setCandidatUpdated(Candidat candidatUpdated) {
		this.candidatUpdated = candidatUpdated;
	}
	public String getOldPassword() {
		return oldPassword;
	}
	public void setOldPassword(String oldPassword) {
		this.oldPassword = oldPassword;
	}
	public String getNewPassword() {
		return newPassword;
	}
	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}
	public String getConfirmnewPassword() {
		return confirmnewPassword;
	}
	public void setConfirmnewPassword(String confirmnewPassword) {
		this.confirmnewPassword = confirmnewPassword;
	}
	public UploadedFile getFile() {
		return file;
	}
	public void setFile(UploadedFile file) {
		this.file = file;
	}
	public String getDestinationDocument() {
		return destinationDocument;
	}
	public void setDestinationDocument(String destinationDocument) {
		this.destinationDocument = destinationDocument;
	}
	public UploadedFile getFilephotoprofil() {
		return filephotoprofil;
	}
	public void setFilephotoprofil(UploadedFile filephotoprofil) {
		this.filephotoprofil = filephotoprofil;
	}
	public String getDestinationphotoprofil() {
		return destinationphotoprofil;
	}
	public void setDestinationphotoprofil(String destinationphotoprofil) {
		this.destinationphotoprofil = destinationphotoprofil;
	}
	public CompetencesIbusiness getCompetencesBuss() {
		return competencesBuss;
	}
	public void setCompetencesBuss(CompetencesIbusiness competencesBuss) {
		this.competencesBuss = competencesBuss;
	}
	public List<Competences> getListcomp() {
		return listcomp;
	}
	public void setListcomp(List<Competences> listcomp) {
		this.listcomp = listcomp;
	}
	public LinkedHashMap<String, String> getItemscompetences() {
		return itemscompetences;
	}
	public void setItemscompetences(LinkedHashMap<String, String> itemscompetences) {
		this.itemscompetences = itemscompetences;
	}
	public String getValselectetcompetences() {
		return valselectetcompetences;
	}
	public void setValselectetcompetences(String valselectetcompetences) {
		this.valselectetcompetences = valselectetcompetences;
	}
	public CompetencesCandidat getCompetenceCandidat() {
		return competenceCandidat;
	}
	public void setCompetenceCandidat(CompetencesCandidat competenceCandidat) {
		this.competenceCandidat = competenceCandidat;
	}
	public CompetencesCandidatIbusiness getCompetenceCandidatBuss() {
		return competenceCandidatBuss;
	}
	public void setCompetenceCandidatBuss(
			CompetencesCandidatIbusiness competenceCandidatBuss) {
		this.competenceCandidatBuss = competenceCandidatBuss;
	}
	public List<CompetencesCandidat> getListcompcandidat() {

		SecurityContext securityContext = SecurityContextHolder.getContext();
    	String username = securityContext.getAuthentication().getName();
    	candidat = candidatBuss.retrounerCandidatSelonEmail(username);
    	listcompcandidat = competenceCandidatBuss.retournerCompetencesCandidatById(candidat.getIdCandidat());
		return listcompcandidat;
	}
	public void setListcompcandidat(List<CompetencesCandidat> listcompcandidat) {
		this.listcompcandidat = listcompcandidat;
	}

	public CompetencesCandidat getCompetenceCandidatForModif() {
		return competenceCandidatForModif;
	}
	public void setCompetenceCandidatForModif(
			CompetencesCandidat competenceCandidatForModif) {
		this.competenceCandidatForModif = competenceCandidatForModif;
	}
	public CompetencesCandidat getCompetenceCandidatForDelete() {
		return competenceCandidatForDelete;
	}
	public void setCompetenceCandidatForDelete(
			CompetencesCandidat competenceCandidatForDelete) {
		this.competenceCandidatForDelete = competenceCandidatForDelete;
	}
	public static Logger getLogger() {
		return logger;
	}
	//********************************************************************************
}
