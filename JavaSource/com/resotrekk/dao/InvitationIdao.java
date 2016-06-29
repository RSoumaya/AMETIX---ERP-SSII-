package com.resotrekk.dao;

import java.util.Date;
import java.util.List;

import com.resotrekk.model.Invitation;

public interface InvitationIdao {

	public void saveInvitation(Invitation invitation);
	public List<Invitation> getAllInvitation();
	public Invitation getInvitation(String title);
}
