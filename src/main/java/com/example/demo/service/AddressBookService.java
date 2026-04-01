package com.example.demo.service;

import org.springframework.stereotype.*;

import com.example.demo.Entity.AddressBook;
import com.example.demo.Entity.Contact;
import com.example.demo.dto.ContactDTO;
import com.example.demo.repository.AddressBookRepository;
import com.example.demo.repository.ContactRepository;

@Service
public class AddressBookService implements IAddressBookService {

    private final AddressBookRepository addressRepo;
    private final ContactRepository contactRepo;

    public AddressBookService(AddressBookRepository addressRepo, ContactRepository contactRepo) {
        this.addressRepo = addressRepo;
        this.contactRepo = contactRepo;
    }

    @Override
    public void createAddressBook(String name) {
        AddressBook book = new AddressBook();
        book.setName(name);
        addressRepo.save(book);
    }

    @Override
    public void addContact(String bookName, ContactDTO dto) {

        AddressBook book = addressRepo.findByName(bookName)
                .orElseThrow(() -> new RuntimeException("AddressBook not found"));

        //  No duplicate
        contactRepo
                .findByFirstNameIgnoreCaseAndLastNameIgnoreCaseAndAddressBook_Name(
                        dto.getFirstName(), dto.getLastName(), bookName)
                .ifPresent(c -> { throw new RuntimeException("Duplicate contact"); });

        Contact contact = new Contact();
        contact.setFirstName(dto.getFirstName());
        contact.setLastName(dto.getLastName());
        contact.setAddress(dto.getAddress());
        contact.setCity(dto.getCity());
        contact.setState(dto.getState());
        contact.setZip(dto.getZip());
        contact.setPhone(dto.getPhone());
        contact.setEmail(dto.getEmail());
        contact.setAddressBook(book);

        contactRepo.save(contact);
    }

    @Override
    public void editContact(String bookName, String firstName, String lastName, ContactDTO dto) {

        Contact contact = contactRepo
                .findByFirstNameIgnoreCaseAndLastNameIgnoreCaseAndAddressBook_Name(
                        firstName, lastName, bookName)
                .orElseThrow(() -> new RuntimeException("Contact not found"));

        contact.setAddress(dto.getAddress());
        contact.setCity(dto.getCity());
        contact.setState(dto.getState());
        contact.setZip(dto.getZip());
        contact.setPhone(dto.getPhone());
        contact.setEmail(dto.getEmail());

        contactRepo.save(contact);
    }

    @Override
    public void deleteContact(String bookName, String firstName, String lastName) {

        Contact contact = contactRepo
                .findByFirstNameIgnoreCaseAndLastNameIgnoreCaseAndAddressBook_Name(
                        firstName, lastName, bookName)
                .orElseThrow(() -> new RuntimeException("Contact not found"));

        contactRepo.delete(contact);
    }
}