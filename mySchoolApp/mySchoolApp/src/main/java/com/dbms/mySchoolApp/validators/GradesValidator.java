package com.dbms.mySchoolApp.validators;

import com.dbms.mySchoolApp.dao.StudentDao;
import com.dbms.mySchoolApp.dao.UserDao;
import com.dbms.mySchoolApp.dao.ClassDetailsDao;
import com.dbms.mySchoolApp.dao.ClassStudentDao;
import com.dbms.mySchoolApp.dao.TeacherDao;
import com.dbms.mySchoolApp.dao.ExamDao;
import com.dbms.mySchoolApp.dao.SubjectDao;
import com.dbms.mySchoolApp.models.User;
import com.dbms.mySchoolApp.models.Student;
import com.dbms.mySchoolApp.models.Teacher;
import com.dbms.mySchoolApp.models.TeacherClassSubject;
import com.dbms.mySchoolApp.models.ClassDetails;
import com.dbms.mySchoolApp.models.ClassStudent;
import com.dbms.mySchoolApp.models.Grades;
import com.dbms.mySchoolApp.services.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class GradesValidator implements Validator {
    @Autowired
    private UserService userService;
    @Autowired
    private StudentDao studentDao;
    @Autowired
    private SubjectDao subjectDao;
    @Autowired
    private UserDao userDao;
    @Autowired
    private ClassDetailsDao classDetailsDao;
    @Autowired
    private ClassStudentDao classStudentDao;
    @Autowired
    private TeacherDao teacherDao;
    @Autowired
    private ExamDao examDao;
 

    @Override
    public boolean supports(Class<?> aClass) {
        return User.class.equals(aClass);
    }

    @Override
    public void validate(Object target, Errors errors) {
    	Grades grades = (Grades) target;
    	if(grades.getSubject().getSubjectId() == 0 || subjectDao.get(grades.getSubject().getSubjectId())==null) {
      		 errors.rejectValue("subject.subjectId", "Invalid.subject");System.out.println("opopop");
    }
    }   
 
    public void validateExtras(Object target, Errors errors) {
    	Grades grades = (Grades) target;
    	if(examDao.get(grades.getExam().getExamId())==null) {
      		 errors.rejectValue("exam.examId", "Invalid.exam");
    }
    	if(teacherDao.get(grades.getTeacher().getTeacherId())==null) {
     		 errors.rejectValue("teacher.teacherId", "Invalid.teacher");
     	}
   	else {
   	Teacher teacher = teacherDao.get(grades.getTeacher().getTeacherId());
   	int sub = grades.getSubject().getSubjectId();
   	if(!(sub==teacher.getSubject1()) && sub!=teacher.getSubject2() && sub!=teacher.getSubject3() ) {
   		 errors.rejectValue("teacher.teacherId", "Invalid.teacherSubject");
   	}
   	}
    }   
    public void validateClass(Object target, Errors errors) {
    	Grades grades = (Grades) target;
    	if(grades.getClassStudent().getClassDetails().getSection().length()<0 || grades.getClassStudent().getClassDetails().getSection().length()>=3) {
    		 errors.rejectValue("classStudent.classDetails.section", "Invalid.section");
    	}
    	else {
    		if(grades.getClassStudent().getStartYear()<1995 || grades.getClassStudent().getStartYear()>2999) {
    			errors.rejectValue("classStudent.startYear", "Invalid.year");
    		}
    		else
    		if(classDetailsDao.get(grades.getClassStudent().getClassDetails().getClassNo(),grades.getClassStudent().getClassDetails().getSection())==null) {
    			errors.rejectValue("classStudent.classDetails.classNo", "Invalid.class");
    		}
    	}
    }
   
    public void validateStudent(Object target, Errors errors) {
    	Grades grades = (Grades) target;
    	if(studentDao.get(grades.getClassStudent().getStudent().getRegistrationNo())==null) {
    		errors.rejectValue("classStudent.student.registrationNo", "Invalid.registrationNo");
    	}
    	else {
    		int classId = classDetailsDao.get(grades.getClassStudent().getClassDetails().getClassNo(),
    	    		grades.getClassStudent().getClassDetails().getSection()).getClassId();
    	    grades.getClassStudent().getClassDetails().setClassId(classId);
    		ClassStudent classSt= (classStudentDao.getClassOfStudent(grades.getClassStudent().getStudent().getRegistrationNo(),grades.getClassStudent().getStartYear()));
    		if(classSt == null || classSt.getClassDetails().getClassId()!=classId){
    			errors.rejectValue("classStudent.student.registrationNo", "Invalid.nonExistingStudent");
    		}
    	}
    }
   
}
