package com.resotrekk.business.imp;

import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.transaction.annotation.Transactional;

import com.resotrekk.business.CandidatIbusiness;
import com.resotrekk.dao.CandidatIdao;
import com.resotrekk.model.Candidat;

public class CandidatIbusinessImp implements CandidatIbusiness{

	//****************************************************************
	
	CandidatIdao candidatDao;
	
	//****************************************************************
	
	@Transactional(readOnly = true)
	@Override
	public void ajouterCandidat(Candidat candidat) {
		
		try{
			candidatDao.saveCandidat(candidat);
		}catch(DataAccessException e){
			e.printStackTrace();
		}
		
	}

	@Transactional(readOnly = true)
	@Override
	public void modifierCandidat(Candidat candidat) {
		
		try{
			candidatDao.updateCandidat(candidat);
		}catch(DataAccessException e){
			e.printStackTrace();
		}
		
	}

	@Override
	public void supprimerCandidat(Candidat candidat) {
		
		try{
			candidatDao.deleteCandidat(candidat);
		}catch(DataAccessException e){
			e.printStackTrace();
		}
		
	}

	@Transactional(readOnly = true)
	@Override
	public Candidat retrounerCandidat(int idcandidat) {
		
		Candidat candidat = null;
		try{
			candidat  = candidatDao.findCandidatById(idcandidat);
		}catch(DataAccessException e){
			e.printStackTrace();
		}
		return candidat;
	}
	
	
	@Transactional(readOnly = true)
	@Override
	public Candidat retrounerCandidatSansTransaction(int idcandidat) {
		
		Candidat candidat = null;
		try{
			candidat  = candidatDao.findCandidatByIdWithoutTransaction(idcandidat);
		}catch(DataAccessException e){
			e.printStackTrace();
		}
		return candidat;
	}

	@Override
	public List<Candidat> retournerTousCandidat() {
		
		List<Candidat> list = null;
		try{
			list = candidatDao.getAllCandidat();
		}catch(DataAccessException e){
			e.printStackTrace();
		}
		return list;
	}

	@Transactional(readOnly = true)
	@Override
	public Candidat retrounerCandidatSelonEmail(String adressemail) {
		
		Candidat candidat = null;
		try{
			candidat  = candidatDao.findCandidatByEmail(adressemail);
		}catch(DataAccessException e){
			e.printStackTrace();
		}
		return candidat;
	}

	@Transactional(readOnly = true)
	@Override
	public List<Candidat> retournerTenCandidat() {
		
		List <Candidat> list = null;
		try{
			list = candidatDao.getLastTenCandidat();
		}catch(DataAccessException e){
			e.printStackTrace();
		}
		return list;
	}
	
	@Transactional(readOnly = true)
	@Override
	public int authentificationnCandidat(String mail) {
		
		int count = 0;
		try{
			
			count = candidatDao.existCandidat(mail);
		}catch(DataAccessException e){
			e.printStackTrace();
		}
		return count;
	}

	@Transactional(readOnly = true)
	@Override
	public int retrounerCandidatSelonNomPren(String nom, String prenom) {
		
		int count = 0;
		try{
			
			count = candidatDao.findCandidatByNomAndPrenom(nom, prenom);
		}catch(DataAccessException e){
			e.printStackTrace();
		}
		return count;
	}
	//****************************************************************
	
	public CandidatIdao getCandidatDao() {
		return candidatDao;
	}
	public void setCandidatDao(CandidatIdao candidatDao) {
		this.candidatDao = candidatDao;
	}
	
	//****************************************************************
}
