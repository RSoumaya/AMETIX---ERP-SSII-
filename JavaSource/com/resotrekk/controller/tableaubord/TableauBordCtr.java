package com.resotrekk.controller.tableaubord;

import java.util.ArrayList;
import java.util.List;

import javax.faces.context.FacesContext;

import com.resotrekk.business.AdminIbusiness;
import com.resotrekk.business.CandidatIbusiness;
import com.resotrekk.business.CandidaturesIbusiness;
import com.resotrekk.model.Administrateur;
import com.resotrekk.model.Candidat;
import com.resotrekk.model.Candidatures;

public class TableauBordCtr {

	//**********************************************************************
	
	AdminIbusiness adminBuss;
	List<Integer> list = new ArrayList<Integer>();
	int countcandidat = 0;
	int countcv = 0;
	int countcandidatures = 0;
	int countoffres = 0;
	int countmessages = 0; 
	int countfeedback = 0;
	
	CandidatIbusiness candidatBuss;
	List<Candidat> listcandidat = new ArrayList<Candidat>();
	CandidaturesIbusiness candidaturesBuss;
	List<Candidatures> listcandidatures = new ArrayList<Candidatures>();
	
	//**********************************************************************
	
	public void init(){
		
		FacesContext facesContext = FacesContext.getCurrentInstance();
	    
	    if (!facesContext.isPostback() && !facesContext.isValidationFailed()) {
	    	
			list = adminBuss.retournerNombres();
	    }
	    
	}
	
	//**********************************************************************
	
	public AdminIbusiness getAdminBuss() {
		return adminBuss;
	}
	public void setAdminBuss(AdminIbusiness adminBuss) {
		this.adminBuss = adminBuss;
	}
	public List<Integer> getList() {
		return list;
	}
	public void setList(List<Integer> list) {
		this.list = list;
	}
	public int getCountcandidat() {
		countcandidat = list.get(0);
		return countcandidat;
	}
	public void setCountcandidat(int countcandidat) {
		this.countcandidat = countcandidat;
	}
	public int getCountcv() {
		countcv = list.get(1);
		return countcv;
	}
	public void setCountcv(int countcv) {
		this.countcv = countcv;
	}
	public int getCountcandidatures() {
		countcandidatures = list.get(2);
		return countcandidatures;
	}
	public void setCountcandidatures(int countcandidatures) {
		this.countcandidatures = countcandidatures;
	}
	public int getCountoffres() {
		countoffres = list.get(3);
		return countoffres;
	}
	public void setCountoffres(int countoffres) {
		this.countoffres = countoffres;
	}
	public int getCountmessages() {
		countmessages = list.get(4);
		return countmessages;
	}
	public void setCountmessages(int countmessages) {
		this.countmessages = countmessages;
	}
	public CandidatIbusiness getCandidatBuss() {
		return candidatBuss;
	}
	public void setCandidatBuss(CandidatIbusiness candidatBuss) {
		this.candidatBuss = candidatBuss;
	}
	public List<Candidat> getListcandidat() {
		listcandidat = candidatBuss.retournerTenCandidat();
		return listcandidat;
	}
	public void setListcandidat(List<Candidat> listcandidat) {
		this.listcandidat = listcandidat;
	}
	public int getCountfeedback() {
		countfeedback = list.get(5);
		return countfeedback;
	}
	public void setCountfeedback(int countfeedback) {
		this.countfeedback = countfeedback;
	}
	public List<Candidatures> getListcandidatures() {
		listcandidatures = candidaturesBuss.listcandidatures();
		return listcandidatures;
	}
	public void setListcandidatures(List<Candidatures> listcandidatures) {
		this.listcandidatures = listcandidatures;
	}
	public CandidaturesIbusiness getCandidaturesBuss() {
		return candidaturesBuss;
	}
	public void setCandidaturesBuss(CandidaturesIbusiness candidaturesBuss) {
		this.candidaturesBuss = candidaturesBuss;
	}
	//**********************************************************************
}
