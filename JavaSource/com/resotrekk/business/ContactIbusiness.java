package com.resotrekk.business;

import java.util.List;

import com.resotrekk.model.Contact;

public interface ContactIbusiness {

	public void ajouterContact(Contact contact);
	public List<Contact> retournerTousContact();
}
