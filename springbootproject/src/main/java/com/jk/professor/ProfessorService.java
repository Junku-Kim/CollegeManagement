package com.jk.professor;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jk.person.PersonRepository;
import com.jk.person.PersonService;


@Transactional
@Service
public class ProfessorService extends PersonService{

    private final ProfessorRepository professorRepository;

    public ProfessorService(PersonRepository personRepository, ProfessorRepository professorRepository) {
    	super(personRepository);
        this.professorRepository = professorRepository;
    }

    public String registerProfessor(Professor professor) {
        validateProfessorForCreation(professor);
        this.professorRepository.save(professor);

        return professor.getLoginId();
    }

    private void validateProfessorForCreation(Professor professor) {
        this.professorRepository.findByLoginId(professor.getLoginId())
            .ifPresent(p -> {
                throw new IllegalStateException("이미 존재하는 교수입니다.");
            });
    }

    public List<Professor> findAllByRankProfessor(Rank rank) {
    	return this.professorRepository.findAllByRank(rank);
    }
    
    public Long deleteProfessor(Professor professor) {
		this.professorRepository.delete(professor);
		
		return professor.getId();
	}
}
