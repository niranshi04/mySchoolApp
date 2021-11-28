package com.dbms.mySchoolApp.controllers;
import com.dbms.mySchoolApp.services.SecurityService;
import java.util.HashMap;
import com.dbms.mySchoolApp.validators.UserValidator;
import com.dbms.mySchoolApp.services.UserService;
import com.dbms.mySchoolApp.validators.StudentValidator;
import com.dbms.mySchoolApp.validators.NumberValidator;
import com.dbms.mySchoolApp.validators.ClassValidator;
import com.dbms.mySchoolApp.validators.TeacherValidator;
import com.dbms.mySchoolApp.validators.AttendanceValidator;
import com.dbms.mySchoolApp.validators.GradesValidator;
import com.dbms.mySchoolApp.validators.ClassStudentValidator;
import com.dbms.mySchoolApp.validators.TeacherClassSubjectValidator;
import javax.servlet.ServletException;
import java.text.SimpleDateFormat;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.Date;
import java.util.Calendar;
import javax.validation.Valid;
import com.dbms.mySchoolApp.dao.UserDao;
import com.dbms.mySchoolApp.dao.GradesDao;
import com.dbms.mySchoolApp.dao.StudentDao;
import com.dbms.mySchoolApp.dao.ParentsDao;
import com.dbms.mySchoolApp.dao.StudentFeesDao;
import com.dbms.mySchoolApp.dao.FeeDetailsDao;
import com.dbms.mySchoolApp.dao.ClassDetailsDao;
import com.dbms.mySchoolApp.dao.ClassStudentDao;
import com.dbms.mySchoolApp.dao.AttendanceDao;
import com.dbms.mySchoolApp.dao.TeacherDao;
import com.dbms.mySchoolApp.dao.TeacherSalaryDao;
import com.dbms.mySchoolApp.dao.TeacherClassSubjectDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PathVariable;
import com.dbms.mySchoolApp.models.User;
import com.dbms.mySchoolApp.models.Attendance;
import com.dbms.mySchoolApp.models.AttendanceList;
import com.dbms.mySchoolApp.models.Parents;
import com.dbms.mySchoolApp.models.Student;
import com.dbms.mySchoolApp.models.StudentFees;
import com.dbms.mySchoolApp.models.Teacher;
import com.dbms.mySchoolApp.models.TeacherSalary;
import com.dbms.mySchoolApp.models.TeacherClassSubject;
import com.dbms.mySchoolApp.models.FeeDetails;
import com.dbms.mySchoolApp.models.Grades;
import com.dbms.mySchoolApp.models.Attendance;
import com.dbms.mySchoolApp.models.ClassDetails;
import com.dbms.mySchoolApp.models.ClassStudent;
import com.dbms.mySchoolApp.models.Grades;


@Transactional
@Controller
public class TeacherController {

	@Autowired
    private UserDao userDao;
	
	@Autowired
    private StudentDao studentDao;
	
	@Autowired
    private ParentsDao parentsDao;
	
	@Autowired
    private GradesDao gradesDao;
	@Autowired
    private AttendanceDao attendanceDao;
	
	@Autowired
    private FeeDetailsDao feeDetailsDao;
	@Autowired
    private TeacherSalaryDao teacherSalaryDao;
	@Autowired
    private TeacherDao teacherDao;
	@Autowired
    private TeacherValidator teacherValidator;
	
	@Autowired
    private StudentFeesDao studentFeesDao;
	@Autowired
    private ClassDetailsDao classDetailsDao;
	@Autowired
    private ClassStudentDao classStudentDao;
	@Autowired
    private TeacherClassSubjectDao teacherClassSubjectDao;
    
    @Autowired
    private UserValidator userValidator;
    @Autowired
    private AttendanceValidator attendanceValidator;

    @Autowired
    private StudentValidator studentValidator;
    @Autowired
    private GradesValidator gradesValidator;
    @Autowired
    private ClassStudentValidator classStudentValidator;
    @Autowired
    private NumberValidator numberValidator;
    @Autowired
    private ClassValidator classValidator;
    @Autowired
    private TeacherClassSubjectValidator teacherClassSubjectValidator;
    
    @Autowired
    private SecurityService securityService;
    
    @Autowired
    private UserService userService;

@GetMapping("/admin/teacherRegister")
    public String addTeacher(Model model) {
        model.addAttribute("title", "Teacher Portal");
        model.addAttribute("message", "Create Teacher's profile");
        model.addAttribute("submessage1", "Add Teacher Details");
        model.addAttribute("buttonmessage", "Submit");
        model.addAttribute("submiturl", "/admin/teacherRegister");
        Teacher teacher = new Teacher();
        model.addAttribute("teacher", teacher);
        model.addAttribute("edit", false);
        return "admin/addEditTeacher";
    }

    @PostMapping("/admin/teacherRegister")
    public String addStudent(@Valid @ModelAttribute("teacher") Teacher teacher, BindingResult bindingResult, Model model) {
    	teacherValidator.validate(teacher, bindingResult);
    	teacherValidator.validateExistingRole(teacher, bindingResult);
    	numberValidator.validateOp(teacher.getPhoneNumber(),bindingResult, "phoneNumber");
        if (bindingResult.hasErrors()) {
        	model.addAttribute("title", "Teacher Portal");
            model.addAttribute("message", "Create Teacher's profile");
            model.addAttribute("submessage1", "Add Teacher Details");
            model.addAttribute("buttonmessage", "Proceed");
            model.addAttribute("submiturl", "/admin/teacherRegister");
            model.addAttribute("teacher", teacher);
            model.addAttribute("edit", false);
            return "admin/addEditTeacher";
        }
        User user = teacher.getUser();
        user.setRole("ROLE_TEACHER");
        User exist = userDao.findByEmailAddress(user.getEmailAddress());
        if(exist == null) {
        	String password = UUID.randomUUID().toString();
            user.setPassword(password);
            userService.save(user);
            userService.sendVerificationEmail(user,password);
            teacher.setUser(user);  
            teacher = teacherDao.save(teacher);
            return "redirect:/admin/teacherSalary/"+teacher.getTeacherId();
        }
        teacher.setUser(exist);
        teacher = teacherDao.save(teacher);
        model.addAttribute("submiturl", "/admin/teacherRegister");
        return "redirect:/admin/teacherSalary/"+teacher.getTeacherId();
        //return "redirect:/" + role + "/students/ST" + student.getRegistrationNo() + "/add-student-phone";
    }
    
    @GetMapping("/admin/teacherSalary/{teacherId}")
    public String addTeacherSalary(@PathVariable("teacherId") int teacherId, Model model) {
    	if(teacherDao.get(teacherId) != null) {
    		TeacherSalary teacherSalary = new TeacherSalary();
    		teacherSalary.setTeacherId(teacherId);
	    	String role = securityService.findLoggedInUserRole();
	        model.addAttribute("title", "Teacher Portal");
	        model.addAttribute("message", "Create Teachers profile");
	        model.addAttribute("submessage1", "Add Teacher Salary Details");
	        model.addAttribute("buttonmessage", "Submit");
	        model.addAttribute("submiturl", "/admin/teacherSalary/"+ teacherId);
	        model.addAttribute("teacherSalary", teacherSalary);
	        return "admin/teacherSalary";
    	}
    	return "admin/teacher";
    }
    @PostMapping("/admin/teacherSalary/{teacherId}")
	public String addTeacherSalary(@Valid @ModelAttribute("teacherSalary") TeacherSalary teacherSalary, BindingResult bindingResult, Model model) {
		if(teacherDao.get(teacherSalary.getTeacherId()) == null)return "/admin/teacher";
		if(teacherSalaryDao.get(teacherSalary.getTeacherId())!= null)return "admin/teacher";
	    if (bindingResult.hasErrors()) {
	    	model.addAttribute("title", "Teacher Portal");
	        model.addAttribute("message", "Create Teacher's profile");
	        model.addAttribute("submessage1", "Add Teacher Salary Details");
	        model.addAttribute("buttonmessage", "Submit");
	        model.addAttribute("submiturl", "/admin/teacherSalary/"+ teacherSalary.getTeacherId());
	        model.addAttribute("teacherSalary", teacherSalary);
	        return "admin/teacherSalary";
	    }
	    teacherSalaryDao.save(teacherSalary);
	    return "admin/teacher";
	}
    
    @GetMapping("/admin/editTeacher/{teacherId}")
    public String editTeacher(@PathVariable("teacherId") int teacherId, Model model) {
    	if(teacherDao.get(teacherId) != null) {
    		Teacher teacher = teacherDao.get(teacherId);
	        model.addAttribute("title", "Teacher Portal");
	        model.addAttribute("message", "Create Teacher's profile");
	        model.addAttribute("submessage1", "Add Teacher Details");
	        model.addAttribute("buttonmessage", "Proceed");
	        model.addAttribute("submiturl", "/admin/editTeacher/"+ teacherId);
	        model.addAttribute("teacher", teacher);
	        model.addAttribute("edit", true);
	        return "admin/addEditTeacher";
    	}
    	return "/";
    }
    
    @PostMapping("/admin/editTeacher/{teacherId}")
    public String editStudent(@PathVariable("teacherId") int teacherId, @Valid @ModelAttribute("teacher") Teacher teacher, BindingResult bindingResult, Model model) {
    	if(teacherDao.get(teacherId) != null) {
    		numberValidator.validateOp(teacher.getPhoneNumber(), bindingResult, "phoneNumber");
    		Teacher teac1 = teacherDao.get(teacherId);
    		if(!(teac1.getUser().getEmailAddress()).equals(teacher.getUser().getEmailAddress()) ){
    			teacherValidator.validate(teacher, bindingResult);
    			teacherValidator.validateExistingRole(teacher, bindingResult);
            	if (bindingResult.hasErrors()) {
                	String role = securityService.findLoggedInUserRole();
                    model.addAttribute("title", "Teacher Portal");
                    model.addAttribute("message", "Create Teacher's profile");
                    model.addAttribute("submessage1", "Add Teacher Details");
                    model.addAttribute("buttonmessage", "Proceed");
                    model.addAttribute("submiturl", "/admin/editTeacher/"+ teacherId);
        	        model.addAttribute("teacher", teacher);
        	        model.addAttribute("edit", true);
                    return "admin/addEditTeacher";  
                }
            	User usr = teac1.getUser();
            	String oldEmail = teac1.getUser().getEmailAddress();
                if(userDao.get(teacher.getUser().getEmailAddress())==null) {
                	System.out.println(usr.getRole());
                	String password = UUID.randomUUID().toString();
                    usr.setPassword(password);
                    
                    usr.setEmailAddress(teacher.getUser().getEmailAddress());
                    userService.save(usr);
                    userService.sendVerificationEmail(usr,password);
                }
                else {
                usr = userDao.get(teacher.getUser().getEmailAddress());
                }
                
                teacher.setUser(usr);
                teacherDao.update(teacher);
            	userDao.delete(oldEmail);
            	return "redirect:/admin/editSalary/{teacherId}";
    		}
    		if (bindingResult.hasErrors()) {
            	String role = securityService.findLoggedInUserRole();
                model.addAttribute("title", "Teacher Portal");
                model.addAttribute("message", "Create Teacher profile");
                model.addAttribute("submessage1", "Add Teacher Details");
                model.addAttribute("buttonmessage", "Proceed");
                model.addAttribute("submiturl", "/admin/editTeacher/"+ teacherId);
    	        model.addAttribute("teacher", teacher);
    	        model.addAttribute("edit", true);
                return "admin/addEditTeacher";  
            }
    		User user = teac1.getUser();
    		teacher.setUser(user);
    		teacherDao.update(teacher);
    		return "redirect:/admin/editSalary/{teacherId}";
    	}
    	return "admin/teacher";
    }
    @GetMapping("/admin/teacherDetail")
    public String teacherDetail(Model model) {
    	model.addAttribute("title", "Find a teacher");
        model.addAttribute("teacher", new Teacher());
        return "admin/findTeacher";
    }
    
    @PostMapping("/admin/findTeacher")
    	public String findTeacher(@Valid @ModelAttribute("teacher") Teacher teacher, BindingResult bindingResult, Model model) {
	    	teacherValidator.validateExisting(teacher, bindingResult);
	        if (bindingResult.hasErrors()) {
	        	System.out.println("j");
	        	model.addAttribute("title", "Find a teacher");
	            model.addAttribute("teacher", teacher);
	            return "admin/findTeacher";
	        }
	    	model.addAttribute("title", "Teacher Detail");
	    	Teacher st1 = teacherDao.getByEmailAddress(teacher.getUser().getEmailAddress());
	        model.addAttribute("teacher", st1);
	        return "admin/teacherDetail";
	    }
    
    @GetMapping("/admin/deleteTeacher/{teacherId}")
    public String deleteTeacher(@PathVariable("teacherId") int teacherId, Model model) {
    	if(teacherDao.get(teacherId) != null) {
    		String emailAddress= teacherDao.get(teacherId).getUser().getEmailAddress();
    		userDao.delete(emailAddress);
    		teacherDao.delete(teacherId);
	    	model.addAttribute("title", "Find a teacher");
	        model.addAttribute("teacher", new Teacher());
	        return "admin/findTeacher";
    	}
    	return "/";
    }
    
    @GetMapping("/admin/editSalary/{teacherId}")
    public String getEditSalary(@PathVariable("teacherId") int teacherId, Model model) {
    	if(teacherDao.get(teacherId) != null) {
    		 TeacherSalary teacherSalary = teacherSalaryDao.get(teacherId);
    		if(teacherSalary == null) {
    			teacherSalary = new TeacherSalary();
    		}
	        model.addAttribute("title", "Teacher  Portal");
	        model.addAttribute("message", "Create Teachers profile");
	        model.addAttribute("submessage1", "Edit teacher salary Details");
	        model.addAttribute("buttonmessage", "Submit");
	        model.addAttribute("submiturl", "/admin/editSalary/"+ teacherId);
	        model.addAttribute("teacherSalary", teacherSalary);
	        return "admin/teacherSalary";
    	}
    	return "/admin/teacher";
    }
    
    @PostMapping("/admin/editSalary/{teacherId}")
	public String editSalary(@Valid @ModelAttribute("teacherSalary") TeacherSalary teacherSalary, BindingResult bindingResult, Model model) {
		if(teacherDao.get(teacherSalary.getTeacherId()) == null)return "/admin/teacher";
	    if (bindingResult.hasErrors()) {
	    	model.addAttribute("title", "teacher Portal");
	        model.addAttribute("message", "Create teacher profile");
	        model.addAttribute("submessage1", "Edit teacher Salary Details");
	        model.addAttribute("buttonmessage", "Submit");
	        model.addAttribute("submiturl", "/admin/editSalary/"+ teacherSalary.getTeacherId());
	        model.addAttribute("teacherSalary", teacherSalary);
	        model.addAttribute("edit", true);
	        return "admin/teacherSalary";
	    }
	    if(teacherSalaryDao.get(teacherSalary.getTeacherId())==null)
	    	teacherSalaryDao.save(teacherSalary);
	    else {
	    	teacherSalaryDao.update(teacherSalary);
	    }
	    return "/admin/teacher";
	}
    
    @PostMapping("/admin/addClassStudent")
    public String addClassStudent( @Valid @ModelAttribute("classStudent") ClassStudent classStudent, BindingResult bindingResult, Model model) {
    	classStudentValidator.validateStudent(classStudent, bindingResult);
    	if ((bindingResult.hasErrors())) {
    		int classId = classStudent.getClassDetails().getClassId();
        	List<ClassStudent> classStudents = classStudentDao.getAllInClass(classStudent.getClassDetails(),classStudent.getStartYear());
        	List<TeacherClassSubject>  teacherClassSubjects = teacherClassSubjectDao.getAllInClass(classStudent.getClassDetails(),classStudent.getStartYear());
        	TeacherClassSubject teacherClassSubject = new TeacherClassSubject();
        	teacherClassSubject.setClassDetails(classStudent.getClassDetails());
        	teacherClassSubject.setYear(classStudent.getStartYear());
        	model.addAttribute("classStudents", classStudents);
        	model.addAttribute("classStudent", classStudent);
        	model.addAttribute("teacherClassSubject", teacherClassSubject);
            model.addAttribute("teacherClassSubjects", teacherClassSubjects);
    	}
    	int classId = classStudent.getClassDetails().getClassId();
    	ClassDetails classDetails = classDetailsDao.getClass(classId);
    	classStudent.setClassDetails(classDetails);
    	classStudentDao.save(classStudent);
    	List<ClassStudent> classStudents = classStudentDao.getAllInClass(classStudent.getClassDetails(),classStudent.getStartYear());
    	List<TeacherClassSubject>  teacherClassSubjects = teacherClassSubjectDao.getAllInClass(classStudent.getClassDetails(),classStudent.getStartYear());
    	TeacherClassSubject teacherClassSubject = new TeacherClassSubject();
    	teacherClassSubject.setClassDetails(classStudent.getClassDetails());
    	teacherClassSubject.setYear(classStudent.getStartYear());
    	model.addAttribute("classStudents", classStudents);
    	classStudent.getStudent().setRegistrationNo(0);
    	model.addAttribute("classStudent", classStudent);
    	model.addAttribute("teacherClassSubject", teacherClassSubject);
        model.addAttribute("teacherClassSubjects", teacherClassSubjects);
    	return "admin/classStudents";
    }
    
    
    
    @PostMapping("/admin/addEditTeacherClassSubject")
    public String addEditTeacherClassSubject( @Valid @ModelAttribute("teacherClassSubject") TeacherClassSubject teacherClassSubject, BindingResult bindingResult, Model model) {
    	teacherClassSubjectValidator.validate(teacherClassSubject, bindingResult);
    	if ((bindingResult.hasErrors())) {
    		int classId = teacherClassSubject.getClassDetails().getClassId();
    		ClassStudent classStudent = new ClassStudent();
    		classStudent.setClassDetails(teacherClassSubject.getClassDetails());
    		classStudent.setStartYear(teacherClassSubject.getYear());
    		List<ClassStudent> classStudents =  classStudentDao.getAllInClass(classStudent.getClassDetails(),classStudent.getStartYear());
        	List<TeacherClassSubject> teacherClassSubjects = teacherClassSubjectDao.getAllInClass(teacherClassSubject.getClassDetails(),teacherClassSubject.getYear());
        	model.addAttribute("classStudents", classStudents);
        	model.addAttribute("classStudent", classStudent);
        	model.addAttribute("teacherClassSubject", teacherClassSubject);
            model.addAttribute("teacherClassSubjects", teacherClassSubjects);
            return "admin/classStudents";
    	}
    	int classId = teacherClassSubject.getClassDetails().getClassId();
    	ClassDetails classDetails = classDetailsDao.getClass(classId);
    	teacherClassSubject.setClassDetails(classDetails);
    	teacherClassSubjectDao.save(teacherClassSubject);
    	ClassStudent classStudent = new ClassStudent();
		classStudent.setClassDetails(teacherClassSubject.getClassDetails());
		classStudent.setStartYear(teacherClassSubject.getYear());
		List<ClassStudent> classStudents =  classStudentDao.getAllInClass(classStudent.getClassDetails(),classStudent.getStartYear());
    	List<TeacherClassSubject>  teacherClassSubjects = teacherClassSubjectDao.getAllInClass(teacherClassSubject.getClassDetails(),teacherClassSubject.getYear());
    	model.addAttribute("classStudents", classStudents);
    	teacherClassSubject.getSubject().setSubjectId(0);
    	teacherClassSubject.getTeacher().setTeacherId(0);
    	model.addAttribute("classStudent", classStudent);
    	model.addAttribute("teacherClassSubject", teacherClassSubject);
        model.addAttribute("teacherClassSubjects", teacherClassSubjects);
    	return "admin/classStudents";
    }
    
    @PostMapping("/admin/deleteTeacherClassSubject")
    public String deleteTeacherClassSubject( @Valid @ModelAttribute("teacherClassSubject") TeacherClassSubject teacherClassSubject, BindingResult bindingResult, Model model) {
    	int classId = teacherClassSubject.getClassDetails().getClassId();
    	teacherClassSubjectDao.delete(teacherClassSubject);
    	teacherClassSubject.getSubject().setSubjectId(0);
    	List<TeacherClassSubject>  teacherClassSubjects = teacherClassSubjectDao.getAllInClass(teacherClassSubject.getClassDetails(),teacherClassSubject.getYear());
    	ClassStudent classStudent = new ClassStudent();
		classStudent.setClassDetails(teacherClassSubject.getClassDetails());
		classStudent.setStartYear(teacherClassSubject.getYear());
		List<ClassStudent> classStudents = classStudentDao.getAllInClass(classStudent.getClassDetails(),classStudent.getStartYear());
    	model.addAttribute("classStudents", classStudents);
    	model.addAttribute("classStudent", classStudent);
    	model.addAttribute("teacherClassSubject", teacherClassSubject);
        model.addAttribute("teacherClassSubjects", teacherClassSubjects);
    	return "admin/classStudents";
    }
    
    @GetMapping("/admin/grades")
    public String grades(Model model) {
        model.addAttribute("title", "Grades Portal");
        model.addAttribute("message", "Welcome to the Grades Portal!");
        return "admin/grades";
    }
    
    @GetMapping("/admin/studentGrades")
    public String studentGrades(Model model) {
        model.addAttribute("title", "Find Student Grades");
        model.addAttribute("grades", new Grades());
        return "admin/findStudentGrades";
    }
    
    @PostMapping("/admin/findStudentGrades")
	public String findStudentGrades(@Valid @ModelAttribute("grades") Grades grades, BindingResult bindingResult, Model model) {
		gradesValidator.validateClass(grades, bindingResult);
		gradesValidator.validateStudent(grades, bindingResult);
		gradesValidator.validate(grades, bindingResult);
	    if (bindingResult.hasErrors()) {
	    	model.addAttribute("title", "Find a student");
	        model.addAttribute("grades", grades);
	        return "admin/findStudentGrades";
	    }
		model.addAttribute("title", "Student Detail");
		int classId = classDetailsDao.get(grades.getClassStudent().getClassDetails().getClassNo(),
	    		grades.getClassStudent().getClassDetails().getSection()).getClassId();
	    grades.getClassStudent().getClassDetails().setClassId(classId);
	    model.addAttribute("grades", grades);
		List<Grades> gradeList = gradesDao.get(grades.getClassStudent(),grades.getSubject().getSubjectId());
	    model.addAttribute("gradeList", gradeList);
	    return "admin/studentGradesList";
	}

    @PostMapping("/admin/addEditStudentGrades")
	public String addEditStudentGrades(@Valid @ModelAttribute("grades") Grades grades, BindingResult bindingResult, Model model) {
		gradesValidator.validateExtras(grades, bindingResult);
	    if (bindingResult.hasErrors()) {
	    	model.addAttribute("title", "Find a student");
	    	List<Grades> gradeList = gradesDao.get(grades.getClassStudent(),grades.getSubject().getSubjectId());
	        model.addAttribute("gradeList", gradeList);
	        model.addAttribute("grades", grades);
	        return "admin/studentGradesList";
	    }
		model.addAttribute("title", "Student Detail");
		gradesDao.save(grades);
		List<Grades> gradeList = gradesDao.get(grades.getClassStudent(),grades.getSubject().getSubjectId());
	    model.addAttribute("gradeList", gradeList);
	    grades.setGrades(0);
	    grades.getTeacher().setTeacherId(0);
	    grades.getExam().setExamId(0);
	    model.addAttribute("grades", grades);
	    return "admin/studentGradesList";
	}

    
    @GetMapping("/admin/attendance")
    public String classAttendance(Model model) {
        model.addAttribute("title", "Find Class Attendance");
        model.addAttribute("attendance", new Attendance());
        return "admin/findClassAttendance";
    }
    
    @PostMapping("/admin/findClassAttendance")
	public String findClassAttendance(@Valid @ModelAttribute("attendance") Attendance attendance, BindingResult bindingResult, Model model) {
	attendanceValidator.validate(attendance, bindingResult);
	    if (bindingResult.hasErrors()) {
	    	model.addAttribute("title", "Find Class Attendance");
	        model.addAttribute("attendance", attendance);
	        return "admin/findClassAttendance";
	    }
	    int classId = classDetailsDao.get(attendance.getClassDetails().getClassNo(),
	    		attendance.getClassDetails().getSection()).getClassId();
	    attendance.getClassDetails().setClassId(classId);
	    Date date = new Date();
	    attendance.setDate(date);
	    List<Attendance> attendanceList = attendanceDao.get(attendance);
		model.addAttribute("title", "Student Detail");
	    model.addAttribute("attendance", attendance);
	    model.addAttribute("attendance2", attendance);
	    model.addAttribute("attendanceList", attendanceList);
	    return "admin/attendanceList";
	}
    
    @PostMapping("/admin/takeAttendance")
	public String takeAttendance(@Valid @ModelAttribute("attendance") Attendance attendance, BindingResult bindingResult, Model model) {
	  model.addAttribute("title", "Student Detail");
	  model.addAttribute("attendance", attendance);
	  List<Attendance>attlist = attendanceDao.getStudents(attendance);
	  AttendanceList listed = new AttendanceList();
	  listed.setStudent(attlist);
	  System.out.println(attlist);
	  model.addAttribute("atlist",listed);
	  return "admin/takeAttendance";
    }
    
    @PostMapping("/admin/postAttendance")
	public String postAttendance(@Valid @ModelAttribute("atlist") AttendanceList atlist, Model model) {
    	for(int i=0;i<atlist.getStudent().size();i++) {
    		attendanceDao.save(atlist.getStudent().get(i));
    	}
	    model.addAttribute("title", "Find Class Attendance");
	    model.addAttribute("attendance", new Attendance());
	    return "admin/findClassAttendance";
    }
}