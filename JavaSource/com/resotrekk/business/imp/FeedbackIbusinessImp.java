package com.resotrekk.business.imp;

import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.transaction.annotation.Transactional;

import com.resotrekk.business.FeedbackIbusiness;
import com.resotrekk.dao.FeedbackIdao;
import com.resotrekk.model.Feedback;

public class FeedbackIbusinessImp implements FeedbackIbusiness{

	//****************************************************************
	
	FeedbackIdao feedbackDao;
	
	//****************************************************************
	
	@Transactional(readOnly = true)
	@Override
	public void ajouterFeedback(Feedback feedback) {
		
		try{
			feedbackDao.saveFeedback(feedback);
		}catch(DataAccessException e){
			e.printStackTrace();
		}
		
	}

	@Transactional(readOnly = true)
	@Override
	public List<Feedback> retournerTousFeedback() {
		
		List<Feedback> list = null;
		try{
			list = feedbackDao.getAllFeedback();
		}catch(DataAccessException e){
			e.printStackTrace();
		}
		return list;
	}
	//****************************************************************

	public FeedbackIdao getFeedbackDao() {
		return feedbackDao;
	}

	public void setFeedbackDao(FeedbackIdao feedbackDao) {
		this.feedbackDao = feedbackDao;
	}
	
	//****************************************************************
}
