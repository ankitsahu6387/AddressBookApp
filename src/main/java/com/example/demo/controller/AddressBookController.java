package com.example.demo.controller;

import org.springframework.web.bind.annotation.*;

import com.example.demo.dto.ContactDTO;
import com.example.demo.service.IAddressBookService;

@RestController
@RequestMapping("/addressbook")
public class AddressBookController {

    private final IAddressBookService service;

    public AddressBookController(IAddressBookService service) {
        this.service = service;
    }

    @PostMapping("/create/{name}")
    public void createBook(@PathVariable String name) {
        service.createAddressBook(name);
    }

    @PostMapping("/{bookName}/contact")
    public void addContact(@PathVariable String bookName, @RequestBody ContactDTO dto) {
        service.addContact(bookName, dto);
    }

    @PutMapping("/{bookName}/contact/{firstName}/{lastName}")
    public void editContact(@PathVariable String bookName,@PathVariable String firstName,@PathVariable String lastName,@RequestBody ContactDTO dto) {

        service.editContact(bookName, firstName, lastName, dto);
    }

    @DeleteMapping("/{bookName}/contact/{firstName}/{lastName}")
    public void deleteContact(@PathVariable String bookName,@PathVariable String firstName,@PathVariable String lastName) {

        service.deleteContact(bookName, firstName, lastName);
    }
}