package com.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.model.Contact;
import com.repository.PhoneBookDao;
import com.service.PhoneBookService;

@Service
public class PhoneBookService {

	@Autowired
	private PhoneBookDao phoneBookDao;

	public List<Contact> getAllContacts() {
		return phoneBookDao.findAll();
	}
	
	public Contact saveContact(Contact contact) {
		return phoneBookDao.save(contact);
	}
	
	public Contact getContactByID(Integer id) {
		return phoneBookDao.findById(id).get();	
	}
	
	public void deleteContact(Integer id) {
		phoneBookDao.deleteById(id);		
	}
}
