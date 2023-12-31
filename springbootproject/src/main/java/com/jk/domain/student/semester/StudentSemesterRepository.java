package com.jk.domain.student.semester;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jk.domain.semester.Semester;
import com.jk.domain.student.Student;

public interface StudentSemesterRepository extends JpaRepository<StudentSemester, Long>{

	Optional<StudentSemester> findByStudentAndSemester(Student student, Semester semester);
	
	List<StudentSemester> findAllByStudent(Student student);
	
	List<StudentSemester> findAllBySemester(Semester semester);
	
	List<StudentSemester> findAllByClassYear(int classYear);
    
    void deleteByStudentAndSemester(Student student, Semester semester);
    
    void deleteAllByStudent(Student student);
    
    void deleteAllBySemester(Semester semester);
}
