package com.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.model.Contact;
import com.service.PhoneBookService;

@Controller
public class PhoneBookController {

	@Autowired
	private PhoneBookService phoneBookService;

	@GetMapping("/home")
	public String viewHome() {
		return "home";
	}

	@GetMapping("/contacts/new")
	public String addNewContact(Model model) {
		Contact contact = new Contact();
		model.addAttribute("contact", contact);
		return "addcontact";
	}

	@GetMapping("/contacts")
	public String listContacts(Model model) {
		model.addAttribute("contacts", phoneBookService.getAllContacts());
		return "contactList";
	}

	@PostMapping("/contacts/add")
	public String saveContact(@ModelAttribute("contact") Contact contact) {
		phoneBookService.saveContact(contact);
		return "redirect:/contacts";
	}

	@GetMapping("/contacts/edit/{id}")
	public String editContact(@PathVariable Integer id, Model model) {
		model.addAttribute("contact", phoneBookService.getContactByID(id));
		return "editcontact";
	}

	@PostMapping("/contacts/update/{id}")
	public String updateContact(@PathVariable Integer id, @ModelAttribute("contact") Contact contact, Model model) {

		Contact existingContact = phoneBookService.getContactByID(id);
		existingContact.setContactId(id);
		existingContact.setContactName(contact.getContactName());
		existingContact.setContactEmail(contact.getContactEmail());
		existingContact.setContactNumber(contact.getContactNumber());

		phoneBookService.saveContact(existingContact);

		return "redirect:/contacts";

	}
	
	@GetMapping("/contacts/delete/{id}")
	public String deleteContact(@PathVariable Integer id) {
		phoneBookService.deleteContact(id);
		return "redirect:/contacts";
	}
}
