package com.dbms.mySchoolApp.validators;

import com.dbms.mySchoolApp.dao.StudentDao;
import com.dbms.mySchoolApp.dao.SubjectDao;
import com.dbms.mySchoolApp.dao.TeacherDao;
import com.dbms.mySchoolApp.dao.UserDao;
import com.dbms.mySchoolApp.dao.ClassDetailsDao;
import com.dbms.mySchoolApp.dao.ClassStudentDao;
import com.dbms.mySchoolApp.dao.TeacherClassSubjectDao;
import com.dbms.mySchoolApp.models.User;
import com.dbms.mySchoolApp.models.Student;
import com.dbms.mySchoolApp.models.Teacher;
import com.dbms.mySchoolApp.models.ClassDetails;
import com.dbms.mySchoolApp.models.ClassStudent;
import com.dbms.mySchoolApp.models.TeacherClassSubject;
import com.dbms.mySchoolApp.services.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class TeacherClassSubjectValidator implements Validator {
    @Autowired
    private UserService userService;
    @Autowired
    private StudentDao studentDao;
    @Autowired
    private SubjectDao subjectDao;
    @Autowired
    private TeacherDao teacherDao;
    @Autowired
    private UserDao userDao;
    @Autowired
    private ClassDetailsDao classDetailsDao;
    @Autowired
    private ClassStudentDao classStudentDao;
    @Autowired
    private TeacherClassSubjectDao teacherClassSubjectDao;

    @Override
    public boolean supports(Class<?> aClass) {
        return User.class.equals(aClass);
    }

    @Override
    public void validate(Object target, Errors errors) {
    	TeacherClassSubject teacherClassSubject = (TeacherClassSubject) target;
    	if(teacherClassSubject.getSubject().getSubjectId() == 0 || subjectDao.get(teacherClassSubject.getSubject().getSubjectId())==null) {
   		 {errors.rejectValue("subject.subjectId", "Invalid.subject");System.out.println("opopop");}
   	}else {
    	if(teacherDao.get(teacherClassSubject.getTeacher().getTeacherId())==null) {
      		 errors.rejectValue("teacher.teacherId", "Invalid.teacher");
      	}
    	else {
    	Teacher teacher = teacherDao.get(teacherClassSubject.getTeacher().getTeacherId());
    	int sub = teacherClassSubject.getSubject().getSubjectId();
    	if(!(sub==teacher.getSubject1()) && sub!=teacher.getSubject2() && sub!=teacher.getSubject3() ) {
    		 errors.rejectValue("subject.subjectId", "Invalid.teacherSubject");
    	}
    	}
    }
    }
   
}