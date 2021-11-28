package com.dbms.mySchoolApp.dao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Date;
import com.dbms.mySchoolApp.dao.ClassStudentDao;
import java.text.SimpleDateFormat;
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
import com.dbms.mySchoolApp.models.Exam;
import com.dbms.mySchoolApp.models.Student;
import com.dbms.mySchoolApp.models.FeeDetails;
import com.dbms.mySchoolApp.models.Attendance;
import com.dbms.mySchoolApp.models.Subject;
import com.dbms.mySchoolApp.models.ClassStudent;
import com.dbms.mySchoolApp.utils.PreparedStatementUtil;
import com.dbms.mySchoolApp.dao.rowmappers.StudentRowMapper;
import com.dbms.mySchoolApp.dao.rowmappers.AttendanceRowMapper;
import com.dbms.mySchoolApp.dao.rowmappers.GradesRowMapper;
import com.dbms.mySchoolApp.dao.rowmappers.ClassStudentRowMapper;

@Transactional
@Repository
public class AttendanceDaoImpl implements AttendanceDao {
    @Autowired
    private JdbcTemplate template;

    @Autowired
    private PreparedStatementUtil preparedStatementUtil;
    @Autowired
    private ClassStudentDao classStudentDao;
    @Override
    public void save(Attendance attendance) {
    	Date date=attendance.getDate();
    	String pattern = "yyyy-MM-dd";
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
		String date1 = simpleDateFormat.format(date);
        String sql = 
        		"INSERT INTO attendance (registrationNo, date, classId, attendance) "
        		+ "VALUES (? ,?, ?, ? ) ON DUPLICATE KEY UPDATE "
        		+ "attendance=VALUES(attendance)";
        template.update(sql,attendance.getClassStudent().getStudent().getRegistrationNo(), 
        		date1,attendance.getClassDetails().getClassId(),
        		attendance.getAttendance());
    }

    @Override
    public List<Attendance> get(Attendance attendance) {
    	try {
    		String sql = 
    			"SELECT * FROM attendance JOIN student ON attendance.registrationNo = student.registrationNo WHERE classId = 3 && MONTH(date)=11 && YEAR(date)=2021";
	    	List<Attendance> attendanceList = template.query(sql, new AttendanceRowMapper());
	        return attendanceList;
	    } catch (EmptyResultDataAccessException e) {
	        return null;
	    }
    }
    
    public List<Attendance> getStudents(Attendance attendance) {
    	try {
    		int classId=attendance.getClassDetails().getClassId();
    		Date date = attendance.getDate();
    		Calendar calendar = Calendar.getInstance();
    		calendar.setTime(date);
    		String pattern = "yyyy-MM-dd";
    		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
    		String date1 = simpleDateFormat.format(date);
    		int year = calendar.get(Calendar.YEAR);
    		String sql = 
        			"SELECT * FROM attendance NATURAL JOIN student WHERE classId = ? && date = ? ";
    		  List<Attendance> att = template.query(sql, new AttendanceRowMapper(), new Object[] {classId , date1});
    		  HashMap<Integer, Integer>mp = new HashMap<Integer, Integer> ();
    		  for(int i=0;i<att.size();i++) {
    			  System.out.println(att.get(i).getDate());
    			  if(att.get(i).getAttendance()==1) {
    				  mp.put(att.get(i).getStudent().getRegistrationNo(),1);
    			  }
    		  }
    		List<ClassStudent> attendanceList =classStudentDao.getAllInClass(attendance.getClassDetails(), year);
		    List<Attendance> atlist = new ArrayList<Attendance>();
		    for (int i = 0; i < attendanceList.size(); i++) 
		    {
		    	Attendance ki = new Attendance();
		    	ki.setClassStudent(attendanceList.get(i));
		    	atlist.add(ki);
		    	ki.setDate(date);
		    	if(mp.get(ki.getClassStudent().getStudent().getRegistrationNo()) !=null) {
		    		ki.setAttendance(1);
		    	}
		    	else
		    	ki.setAttendance(0);
		    }
		     return atlist;
	    } catch (EmptyResultDataAccessException e) {
	        return null;
	    }
    }

    @Override
    public void delete(int subjectId) {
        String sql = "DELETE FROM subject WHERE subjectId = ?";
        template.update(sql,subjectId );
    }

}