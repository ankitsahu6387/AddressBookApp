package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.Entity.AddressBook;

import java.util.Optional;

public interface AddressBookRepository extends JpaRepository<AddressBook, Long> {

    Optional<AddressBook> findByName(String name);
}
