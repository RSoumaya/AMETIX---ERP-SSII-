package com.resotrekk.controller.offres;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import org.springframework.dao.DataAccessException;

import com.resotrekk.business.HistoriqueIbusiness;
import com.resotrekk.business.OffreIbusiness;
import com.resotrekk.model.Historique;
import com.resotrekk.model.Offre;

public class GererOffreCtr {

	//********************************************************************************
	
	Offre offre = new Offre(); 
	OffreIbusiness offreBuss;
	List<Offre> list = new ArrayList<Offre>();
	Offre offreForModif = new Offre();
	Offre offreForDelete = new Offre();
	private boolean disabledannuler = true;
	HistoriqueIbusiness hitoriqueBuss;
	//********************************************************************************
	
	public String ajouterOffre(){
		
		try{
			
			Date mDate = new Date(System.currentTimeMillis());
			int mois = mDate.getMonth() + 1;			
			offre.setDateOffre(mDate.getDate()+"/"+ mois);
			offreBuss.ajouterOffre(offre);
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Votre offre a été ajouté avec succés!"));
			
			Historique historique = new Historique();
			Date dNow = new Date( );
		    SimpleDateFormat ft = new SimpleDateFormat ("E yyyy.MM.dd 'à' hh:mm:ss a zzz");
			historique.setDescription("Une offre a été traité le "+ft.format(dNow));
			hitoriqueBuss.ajouterHistorique(historique);
			
			historique = new Historique();
			offre = new Offre();
			setDisabledannuler(true);
		}catch(DataAccessException e){
			e.printStackTrace();
		}
		return "success";
	}
	
	
	public String clear(){
		
		offre = new Offre();
		setDisabledannuler(true);
		return "success";
	}
	
	public String offreModif(){
		
		setDisabledannuler(false);
		this.offre = offreForModif;
		return "success";
	}
	
	public String offreDelete(){
		
		try{
			offreBuss.supprimerOffre(offreForDelete);
			
			Historique historique = new Historique();
			Date dNow = new Date( );
		    SimpleDateFormat ft = new SimpleDateFormat ("E yyyy.MM.dd 'à' hh:mm:ss a zzz");
			historique.setDescription("Une offre a été supprimé le "+ft.format(dNow));
			hitoriqueBuss.ajouterHistorique(historique);
			
			historique = new Historique();
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Votre offre a été supprimé avec succés!"));
		}catch(DataAccessException e){
			e.printStackTrace();
		}
		return "success";
	}
	
		public void init() {
	    
		FacesContext facesContext = FacesContext.getCurrentInstance();
	    
	    if (!facesContext.isPostback() && !facesContext.isValidationFailed()) {
	    	
	    	setDisabledannuler(true);
	    	offre = new Offre();
	    }
	}
	//********************************************************************************
	
	
	public Offre getOffre() {
		return offre;
	}
	public void setOffre(Offre offre) {
		this.offre = offre;
	}
	public OffreIbusiness getOffreBuss() {
		return offreBuss;
	}
	public void setOffreBuss(OffreIbusiness offreBuss) {
		this.offreBuss = offreBuss;
	}
	public List<Offre> getList() {
		list = offreBuss.retournerToutesOffre();
		return list;
	}
	public void setList(List<Offre> list) {
		this.list = list;
	}
	public Offre getOffreForModif() {
		return offreForModif;
	}
	public void setOffreForModif(Offre offreForModif) {
		this.offreForModif = offreForModif;
	}
	public Offre getOffreForDelete() {
		return offreForDelete;
	}
	public void setOffreForDelete(Offre offreForDelete) {
		this.offreForDelete = offreForDelete;
	}
	public boolean isDisabledannuler() {
		return disabledannuler;
	}
	public void setDisabledannuler(boolean disabledannuler) {
		this.disabledannuler = disabledannuler;
	}
	public HistoriqueIbusiness getHitoriqueBuss() {
		return hitoriqueBuss;
	}
	public void setHitoriqueBuss(HistoriqueIbusiness hitoriqueBuss) {
		this.hitoriqueBuss = hitoriqueBuss;
	}
	//********************************************************************************
}
