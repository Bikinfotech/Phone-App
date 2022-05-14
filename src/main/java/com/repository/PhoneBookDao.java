package com.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.model.Contact;

public interface PhoneBookDao extends JpaRepository<Contact, Integer>{

}
