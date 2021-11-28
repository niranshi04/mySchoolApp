package com.dbms.mySchoolApp.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Date;
import java.time.YearMonth;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.dbms.mySchoolApp.models.Parents;
import com.dbms.mySchoolApp.models.Student;
import com.dbms.mySchoolApp.models.TeacherClassSubject;
import com.dbms.mySchoolApp.models.ClassDetails;
import com.dbms.mySchoolApp.models.ClassStudent;
import com.dbms.mySchoolApp.models.FeeDetails;
import com.dbms.mySchoolApp.utils.PreparedStatementUtil;
import com.dbms.mySchoolApp.dao.rowmappers.StudentRowMapper;
import com.dbms.mySchoolApp.dao.rowmappers.TeacherClassSubjectRowMapper;
import com.dbms.mySchoolApp.dao.rowmappers.ClassStudentRowMapper;

@Transactional
@Repository
public class TeacherClassSubjectDaoImpl implements TeacherClassSubjectDao {
    @Autowired
    private JdbcTemplate template;

    @Autowired
    private PreparedStatementUtil preparedStatementUtil;

    @Override
    public void save(TeacherClassSubject teacherClassSubject) {
        String sql = "INSERT INTO teacherclasssubject (teacherId, classId, year, subjectId) "
                + "VALUES (?, ?, ?, ?) ON DUPLICATE KEY UPDATE teacherId=VALUES(teacherId) ";
        template.update(sql, teacherClassSubject.getTeacher().getTeacherId(), teacherClassSubject.getClassDetails().getClassId(),
        		teacherClassSubject.getYear(),teacherClassSubject.getSubject().getSubjectId());
    }

    
    @Override
    public List<ClassStudent> getAllPresentInClass(ClassDetails classDetails){
    	try {
    		int year = YearMonth.now().getYear();
        	String sql = "SELECT * FROM classstudent WHERE classId = ? && (startYear = ?) ||"
        			+ "(startYear = ?) ";
        	List<ClassStudent> classStudents = (List<ClassStudent>)template.query(sql, new ClassStudentRowMapper(),
        			new Object[] { classDetails.getClassId(),year, year-1});
            return classStudents;
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }
    @Override
    public List<TeacherClassSubject> getAllInClass(ClassDetails classDetails, int year ){
    	try {
        	String sql = "SELECT * FROM teacherclasssubject NATURAL JOIN teacher NATURAL JOIN subject WHERE classId = ? && (year = ? )||"
        			+ "(year = ?) ";
        	List<TeacherClassSubject> teacherClassSubjects = template.query(sql, new TeacherClassSubjectRowMapper(),
        			new Object[] { classDetails.getClassId(),year, year-1 });
            return teacherClassSubjects;
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }
    
    
    public List<ClassStudent> getAll(int year ){
    	try {
        	String sql = "SELECT * FROM classstudent WHERE startYear = ? ORDERBY classId ";
        	List<ClassStudent> classStudents = (List<ClassStudent>)template.query(sql, new ClassStudentRowMapper(),
        			new Object[] {year});
            return classStudents;
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }
    
    public ClassStudent getPresentClassOfStudent(int registrationNo) {
    	try {
    		int year = YearMonth.now().getYear();
        	String sql = "SELECT  classdetails.classNo, classdetails.section FROM classstudent NATURAL JOIN classdetails "
        			+ "WHERE registrationNo = ? && (startYear = ?) ||"
        			+ "(startYear = ? )";
        	ClassStudent classStudent= (ClassStudent)template.query(sql, new ClassStudentRowMapper(),
        			new Object[] {registrationNo, year, year-1});
            return classStudent;
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }
    
    public ClassStudent getClassOfStudent(int registrationNo, int year) {
    	try {
        	String sql = "SELECT  classdetails.classNo, classdetails.section FROM classstudent NATURAL JOIN classdetails "
        			+ "WHERE registrationNo = ? && startYear=?";
        	ClassStudent classStudent= (ClassStudent)template.queryForObject(sql, new ClassStudentRowMapper(),
        			new Object[] {registrationNo, year});
            return classStudent;
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    } 
    public void deletePresent(int classId, int registrationNo) {
    	int year = YearMonth.now().getYear();
    	 String sql = "DELETE FROM classstudent WHERE classId = ? && registrationNo = ? &&"
    	 		+ "(startYear = ? )|| "
    	 		+ "(startYear = ? )";
         template.update(sql,classId, registrationNo, year, year-1 );
    }
    
    public void updatePresent(ClassStudent classStudent, int registrationNo) {
    	int year = YearMonth.now().getYear();
   	 String sql ="UPDATE classstudent set classId = ? startYear= ?  WHERE registrationNo = ? &&"
   	 		+ "(startYear = ? )|| "
   	 		+ "(startYear = ?)";
        template.update(sql,classStudent.getClassDetails().getClassId(),classStudent.getStartYear(), 
        		registrationNo, year, year-1);
    }
    
    public void delete(TeacherClassSubject teacherClassSubject) {
    	String sql = "DELETE FROM teacherclasssubject WHERE classId = ? && subjectId = ?  && year = ?";
    	template.update(sql,teacherClassSubject.getClassDetails().getClassId(),
    			teacherClassSubject.getSubject().getSubjectId()
    			,teacherClassSubject.getYear() );
    }
    
    
   
}