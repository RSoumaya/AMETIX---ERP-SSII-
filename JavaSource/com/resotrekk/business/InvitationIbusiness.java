package com.resotrekk.business;

import java.util.Date;
import java.util.List;

import com.resotrekk.model.Invitation;

public interface InvitationIbusiness {

	public void ajouterInvitation(Invitation invitation);
	public List<Invitation> consulterInvitation();
	public Invitation consulterInvitation(String title);
	
}
