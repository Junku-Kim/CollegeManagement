package com.jk.domain.person;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonRepository extends JpaRepository<Person, Long> {

	Optional<Person> findByLoginId(String loginId);

	List<Person> findAllByName(String name);
	
	List<Person> findAllByDtype(String dtype); 
	
	void deleteByLoginId(String loginId);
	
	void deleteAllByDtype(String dtype);
}
