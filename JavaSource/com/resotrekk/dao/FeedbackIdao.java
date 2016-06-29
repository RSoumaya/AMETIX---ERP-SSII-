package com.resotrekk.dao;

import java.util.List;

import com.resotrekk.model.Feedback;

public interface FeedbackIdao {

	public void saveFeedback(Feedback feedback);
	public List<Feedback> getAllFeedback();
}
