package com.resotrekk.controller.admin;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import org.springframework.dao.DataAccessException;

import com.resotrekk.business.AdminIbusiness;
import com.resotrekk.business.HistoriqueIbusiness;
import com.resotrekk.model.Administrateur;
import com.resotrekk.model.Feedback;
import com.resotrekk.model.Historique;

public class AdminCtr {

	
	//**************************************************************************
	
	Administrateur admin = new Administrateur();
	AdminIbusiness adminBuss;
	private String password = "";
	private String confirmPassword = "";
	
	List<Administrateur> list = new ArrayList<Administrateur>();
	
	HistoriqueIbusiness historiqueBuss;
	//**************************************************************************
	
	public String ajouterAdmin() throws NoSuchAlgorithmException{
	
		try{
			
			if(password.equals(confirmPassword)){
				
				admin.setMotDePasse(getMD5Hash(confirmPassword));
				admin.setPhoto("resotrekkpphotoprofilpresotrekk.jpg");
				admin.setEnabled(true);
				adminBuss.ajouterAdmin(admin);
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Un nouveau administrateur créer!"));
				
				Historique historique = new Historique();
				Date dNow = new Date( );
				SimpleDateFormat ft = new SimpleDateFormat ("E yyyy.MM.dd 'à' hh:mm:ss a zzz");
				historique.setDescription("Un nouveau admin "+ admin.getNomAdmin() +" à été ajouté le "+ft.format(dNow));
				historiqueBuss.ajouterHistorique(historique);
					
				historique = new Historique();
				
				clear();
			}else{
				afficherMessageError("Les deux mots de passes ne sont pas identiques");
			}
		}catch(DataAccessException e){
			e.printStackTrace();
		}
		
		return  "success";
	}
	
	
	//Afficher des messages d'erreurs
	public void afficherMessageError(String message){
			
		FacesMessage facesMessage = new FacesMessage();
		facesMessage.setSeverity(FacesMessage.SEVERITY_ERROR);
		facesMessage.setSummary(message);
		FacesContext facesContext = FacesContext.getCurrentInstance();
		facesContext.addMessage(null,facesMessage);

		}
	
	public String clear(){
		
		admin = new Administrateur();
		password = "";
		confirmPassword = "";
		return "success";
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
	
	
	public void init() {
	    
		FacesContext facesContext = FacesContext.getCurrentInstance();
	    
	    if (!facesContext.isPostback() && !facesContext.isValidationFailed()) {
	    	
	    	admin = new Administrateur();
	    }
	}
	//**************************************************************************
	
	public Administrateur getAdmin() {
		return admin;
	}
	public void setAdmin(Administrateur admin) {
		this.admin = admin;
	}
	public AdminIbusiness getAdminBuss() {
		return adminBuss;
	}
	public void setAdminBuss(AdminIbusiness adminBuss) {
		this.adminBuss = adminBuss;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getConfirmPassword() {
		return confirmPassword;
	}
	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}
	public HistoriqueIbusiness getHistoriqueBuss() {
		return historiqueBuss;
	}
	public void setHistoriqueBuss(HistoriqueIbusiness historiqueBuss) {
		this.historiqueBuss = historiqueBuss;
	}
	public List<Administrateur> getList() {
		list = adminBuss.retournerAdministrateur();
		return list;
	}
	public void setList(List<Administrateur> list) {
		this.list = list;
	}
	//**************************************************************************
}
