package com.resotrekk.controller.contact;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import org.apache.log4j.Logger;
import org.primefaces.event.map.OverlaySelectEvent;
import org.primefaces.model.map.DefaultMapModel;
import org.primefaces.model.map.LatLng;
import org.primefaces.model.map.MapModel;
import org.primefaces.model.map.Marker;
import org.springframework.dao.DataAccessException;

import com.resotrekk.business.ContactIbusiness;
import com.resotrekk.controller.offres.OffreCtr;
import com.resotrekk.model.Contact;

public class ContactCtr implements Serializable{


	//****************************************************************************
	
	private static final long serialVersionUID = 1L;

	private static final transient Logger logger = Logger.getLogger(ContactCtr.class);
	
	ContactIbusiness contactBuss;
	Contact contact = new Contact();
	
	List<Contact> list = new ArrayList<Contact>();
	int countmessage = 0;
	
	private MapModel simpleModel;
	private Marker marker;
	//****************************************************************************
	
	@PostConstruct
    public void init() {
        simpleModel = new DefaultMapModel();
          
        //Shared coordinates
        LatLng coord1 = new LatLng(48.837957, 2.23028);
        LatLng coord2 = new LatLng(48.9467962, 2.0633252);
        LatLng coord3 = new LatLng(49.2697377, 4.0441473);
          
        //Basic marker
        simpleModel.addOverlay(new Marker(coord1, "RESOPRINT PARIS"));
        simpleModel.addOverlay(new Marker(coord2, "XEROBOUTIQUE 95"));
        simpleModel.addOverlay(new Marker(coord3, "TREKK"));
    }
  
	
	public void onMarkerSelect(OverlaySelectEvent event) {
	        marker = (Marker) event.getOverlay();
	    }
	 
	public String ajouterContact() {
		
		try{
			
			contactBuss.ajouterContact(contact);
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Votre message a été envoyé avec succés!"));
			contact = new Contact();
		}catch(DataAccessException e){
			e.printStackTrace();
			logger.warn(e);
		}
		return "success";
	}
	
	public String clear() {
		
		contact = new Contact();
		return "success";
	}
	//****************************************************************************
	
	public ContactIbusiness getContactBuss() {
		return contactBuss;
	}
	public Marker getMarker() {
		return marker;
	}
	public void setMarker(Marker marker) {
		this.marker = marker;
	}
	public void setContactBuss(ContactIbusiness contactBuss) {
		this.contactBuss = contactBuss;
	}
	public Contact getContact() {
		return contact;
	}
	public void setContact(Contact contact) {
		this.contact = contact;
	}
	public List<Contact> getList() {
		
		list = contactBuss.retournerTousContact();
		return list;
	}
	public void setList(List<Contact> list) {
		this.list = list;
	}
	public int getCountmessage() {
		return countmessage;
	}
	public void setCountmessage(int countmessage) {
		this.countmessage = countmessage;
	}
	public static Logger getLogger() {
		return logger;
	}
	public MapModel getSimpleModel() {
		return simpleModel;
	}
	public void setSimpleModel(MapModel simpleModel) {
		this.simpleModel = simpleModel;
	}
	//****************************************************************************
	
}
