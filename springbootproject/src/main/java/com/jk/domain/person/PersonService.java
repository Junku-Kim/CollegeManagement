package com.jk.domain.person;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service
public class PersonService {

	private final PersonRepository personRepository;
	
	public PersonService(PersonRepository personRepository) {
		this.personRepository = personRepository;
	}
	
	public Optional<Person> findByLoginId(String loginId) {
		return this.personRepository.findByLoginId(loginId);
	}
	
	public List<Person> findAllByName(String name) {
		return this.personRepository.findAllByName(name);
	}
	
	public List<Person> findAllByDtype(String dtype) {
		return this.personRepository.findAllByDtype(dtype);
	}
	
	public List<Person> findAll() {
		return this.personRepository.findAll();
	}
	
	public void deleteByLoginId(String loginId) {
		this.personRepository.deleteByLoginId(loginId);
	}
	
	public void deleteAllByDtype(String dtype) {
		this.personRepository.deleteAllByDtype(dtype);
	}
	
	public void deleteAll() {
		this.personRepository.deleteAll();
	}
}
