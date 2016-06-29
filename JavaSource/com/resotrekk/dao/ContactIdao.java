package com.resotrekk.dao;

import java.util.List;

import com.resotrekk.model.Contact;

public interface ContactIdao {

	public void saveContact(Contact contact);
	public List<Contact> getAllContact();
}
