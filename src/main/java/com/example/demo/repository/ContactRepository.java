package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.Entity.Contact;

import java.util.Optional;

public interface ContactRepository extends JpaRepository<Contact, Long> {

    Optional<Contact> findByFirstNameIgnoreCaseAndLastNameIgnoreCaseAndAddressBook_Name(
            String firstName, String lastName, String bookName);
}
