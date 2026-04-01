package com.example.demo.service;

import com.example.demo.dto.ContactDTO;

public interface IAddressBookService {

    void createAddressBook(String name);

    void addContact(String bookName, ContactDTO dto);

    void editContact(String bookName, String firstName, String lastName, ContactDTO dto);

    void deleteContact(String bookName, String firstName, String lastName);
}