package com.resotrekk.controller.admin;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.math.BigInteger;
import java.net.MalformedURLException;
import java.net.URL;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import org.primefaces.model.UploadedFile;
import org.springframework.dao.DataAccessException;

import com.resotrekk.business.AdminIbusiness;
import com.resotrekk.mail.Adminpassword;
import com.resotrekk.model.Administrateur;

public class AuthentificationAdminCtr {

	
	//***********************************************************************
	
	AdminIbusiness adminBuss; 
	public Administrateur administrateur = new Administrateur();
	
	private String login = "";
	private String password = "";
	
	private String oldpassword = "";
	private String newpassword = "";
	private String confirmpassword = "";
	
	private UploadedFile file;
	private String destinationphotoprofil = "C:\\Program Files\\Apache Software Foundation\\Tomcat 8.0\\webapps\\resotrekk\\backOffice\\photodeprofil\\";
	//***********************************************************************
	
	public String seConnecter() throws NoSuchAlgorithmException{
		
		administrateur.setEmailAdmin(login);
		administrateur.setMotDePasse(password);
		int count = 0;
		
		try{
			 count = adminBuss.authentificationn(administrateur.getEmailAdmin(), getMD5Hash(administrateur.getMotDePasse()));
			 System.out.println(count);
			if(count == 1){
				administrateur = adminBuss.retournerAdminMail(administrateur.getEmailAdmin());
				count = 0;
				return "tableaudebord.xhtml?faces-redirect=true";
			}else{
				afficherMessageError("Vérifier vos coordonnées");
				login = "";
				password = "";
				return "fail";
			}
			
		}catch(DataAccessException e){
			e.printStackTrace();
			return "fail";
		}
		
	}
	
	//Mot de passe oublié 
	public String motdepasseOublie() throws NoSuchAlgorithmException{
		
		administrateur.setEmailAdmin(login);
		int count = 0;
		
		try{
			 count = adminBuss.authentificationnAdmin(administrateur.getEmailAdmin());
			 
			if(count == 1){
				administrateur = adminBuss.retournerAdminMail(administrateur.getEmailAdmin());
				
				//Générer un nouveau mot de passe
				String chars = "abcdefghijklmnopqrstuvwxyz"; 
			    String pass = "";
			    for(int x=0;x<chars.length() ;x++)
			    {
			       int i = (int)Math.floor(Math.random() * 26); 
			       pass += chars.charAt(i);
			    }
			    
			    //envoyer un mail
			    Adminpassword mot = new Adminpassword();
			    mot.send(administrateur, pass);
			    
			    administrateur.setMotDePasse(getMD5Hash(pass));
			    adminBuss.ajouterAdmin(administrateur);
				
				count = 0;
				pass = "";
				login = "";
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Un message contenant votre nouveau mot de passe a été envoyé sur votre boite mail!"));
				return "success";
			}else{
				afficherMessageError("Adresse n'existe pas");
				login = "";
				password = "";
				return "fail";
			}
			
		}catch(DataAccessException e){
			e.printStackTrace();
			return "fail";
		}
		
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
	
	public String deconnexion(){
		
		administrateur = new Administrateur();
		login = "";
		password = "";
		return "adminzone.xhtml?faces-redirect=true";
	}
	

	public void init(){
		
		FacesContext facesContext = FacesContext.getCurrentInstance();
	    
	    if (!facesContext.isPostback() && !facesContext.isValidationFailed()) {
	    	
	    	System.out.println("entre");
	    	login = "";
	    	password = "";
			administrateur = new Administrateur();
	    }
	    
	}
	

	public void redirect() throws IOException, NoSuchAlgorithmException{
		
		FacesContext facesContext = FacesContext.getCurrentInstance();
	    
	    if (!facesContext.isPostback() && !facesContext.isValidationFailed()) {
	    	int count = 0;
	    	administrateur.setEmailAdmin(login);
			administrateur.setMotDePasse(password);
			count = adminBuss.authentificationn(administrateur.getEmailAdmin(), getMD5Hash(administrateur.getMotDePasse()));
			
	    	if(count == 0){
	    		
	    		facesContext.getExternalContext().redirect("adminzone.xhtml");
	    		administrateur = new Administrateur();
	    	}
	    }
	}
	//**************************** Compte Administrateur*******************************************
	
	public String modifierMonCompte() throws NoSuchAlgorithmException{
		
		
		System.out.println(administrateur.getMotDePasse());
		if(!getMD5Hash(administrateur.getMotDePasse()).equals(getMD5Hash(getOldpassword()))){
			
			afficherMessageError("Vous devez vérifier votre mot de passe");
		}else{
			
			if(getNewpassword().equals(getConfirmpassword())){
				
				administrateur.setMotDePasse(getMD5Hash(getNewpassword()));
				adminBuss.ajouterAdmin(administrateur);
				oldpassword = "";
				newpassword = "";
				confirmpassword = "";
				
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Vos informations ont été modifié avec succés!"));
			}else{
				afficherMessageError("Les deux mots de passes ne sont pas identiques");
			}
		}
		
		return "success";
	}
	
	
	public String clear(){
		
		oldpassword = "";
		newpassword = "";
		confirmpassword = "";
		return "success";
	}
	
	//**************************** Charger nouvelle photo*******************************************
	
	public void uploadphotoprofil() throws NoSuchAlgorithmException, IOException {
	    		
		
		String photodeprofilextension = "";
		
		long taille = file.getSize();
		double x = (double) taille / 1024;
		
		String expression = "(.*?)\\.(gif|jpg|jpeg|gif|png|PNG|GIF|JPG|JPEG|bmp)$";
		
		 if((!file.getFileName().matches(expression)) ) {
				
			 FacesContext.getCurrentInstance().
		        addMessage(null,
		            new FacesMessage(FacesMessage.SEVERITY_ERROR,
		                                       "Vous devez entrer un format de photo !"+ file.getFileName(), null));
			 
				 }else{
					 
				 
		if(file != null && x <=3000) {
			
			String extension = file.getFileName().substring(file.getFileName().lastIndexOf("."));
			photodeprofilextension = administrateur.getIdAdmin()+extension;
			
			administrateur.setPhoto(photodeprofilextension);
	        administrateur.setMotDePasse(getMD5Hash(password));
	        adminBuss.ajouterAdmin(administrateur);
	           FacesMessage message = new FacesMessage("Succesful", file.getFileName() + " is uploaded.");
	         try {
				copyFile(photodeprofilextension, file.getInputstream(),destinationphotoprofil);
			} catch (Exception e) {
					e.printStackTrace();
			}
		 }
				 }
	}
	
	//Méthode générique de dépalement des fichiers CV et Lettre de motivation
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
	
	//**********************************************************************************************
	
	public AdminIbusiness getAdminBuss() {
		return adminBuss;
	}
	public void setAdminBuss(AdminIbusiness adminBuss) {
		this.adminBuss = adminBuss;
	}
	public Administrateur getAdministrateur() {
		return administrateur;
	}
	public void setAdministrateur(Administrateur administrateur) {
		this.administrateur = administrateur;
	}
	public String getOldpassword() {
		return oldpassword;
	}
	public void setOldpassword(String oldpassword) {
		this.oldpassword = oldpassword;
	}
	public String getNewpassword() {
		return newpassword;
	}
	public void setNewpassword(String newpassword) {
		this.newpassword = newpassword;
	}
	public String getConfirmpassword() {
		return confirmpassword;
	}
	public void setConfirmpassword(String confirmpassword) {
		this.confirmpassword = confirmpassword;
	}
	public UploadedFile getFile() {
		return file;
	}
	public void setFile(UploadedFile file) {
		this.file = file;
	}
	public String getDestinationphotoprofil() {
		return destinationphotoprofil;
	}
	public void setDestinationphotoprofil(String destinationphotoprofil) {
		this.destinationphotoprofil = destinationphotoprofil;
	}
	public String getLogin() {
		return login;
	}
	public void setLogin(String login) {
		this.login = login;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	//***********************************************************************
}
