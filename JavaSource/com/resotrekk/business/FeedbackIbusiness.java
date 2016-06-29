package com.resotrekk.business;

import java.util.List;

import com.resotrekk.model.Feedback;

public interface FeedbackIbusiness {

	public void ajouterFeedback(Feedback feedback);
	public List<Feedback> retournerTousFeedback();
}
