package com.resotrekk.transmissionvariable;

import javax.faces.context.FacesContext;

import org.primefaces.context.RequestContext;
import org.springframework.dao.DataAccessException;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;


public class MultipleMenu {

	//*****************************************************************************
	
	private boolean carriere = true;
	public int count = 0 ;
	//*****************************************************************************
	
	public String currentPage(){
		
		try{
			
			if(count == 1){
				
				return "accueil.xhtml?faces-redirect=true";
			}else if(count == 2){
				
				return "backOffice/adminzone.xhtml?faces-redirect=true";
			}else{
				return "fail";
			}
		}catch(DataAccessException e){
			e.printStackTrace();
			return "fail";
		}
	}
	public void init() {
	    
		FacesContext facesContext = FacesContext.getCurrentInstance();
	    
	    if (!facesContext.isPostback() && !facesContext.isValidationFailed()) {
	    	
	    	SecurityContext securityContext = SecurityContextHolder.getContext();
	    	String username = securityContext.getAuthentication().getName();
	    	
	    	if(username.equals("anonymousUser")){
	    		
	    		setCarriere(true);
	    	}else{
	    		
	    		setCarriere(false);
	    	}
	    }
}
	
	public void client() {
	    
		FacesContext facesContext = FacesContext.getCurrentInstance();
	    
	    if (!facesContext.isPostback() && !facesContext.isValidationFailed()) {
	    	
	    	count = 1 ;
	    }
}
	
	
	public void admin() {
	    
		FacesContext facesContext = FacesContext.getCurrentInstance();
	    
	    if (!facesContext.isPostback() && !facesContext.isValidationFailed()) {
	    	
	    	count = 2 ;
	    }
}

	//*****************************************************************************
	
	public boolean isCarriere() {
		return carriere;
	}
	public void setCarriere(boolean carriere) {
		this.carriere = carriere;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	//*****************************************************************************
}
