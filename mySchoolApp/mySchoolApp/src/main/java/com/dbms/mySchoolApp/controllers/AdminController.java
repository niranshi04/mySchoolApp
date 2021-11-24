package com.dbms.mySchoolApp.controllers;
import com.dbms.mySchoolApp.services.SecurityService;
import java.util.HashMap;
import com.dbms.mySchoolApp.validators.UserValidator;
import com.dbms.mySchoolApp.services.UserService;
import com.dbms.mySchoolApp.validators.StudentValidator;
import com.dbms.mySchoolApp.validators.NumberValidator;
import com.dbms.mySchoolApp.validators.ClassValidator;
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
import com.dbms.mySchoolApp.models.TeacherClassSubject;
import com.dbms.mySchoolApp.models.FeeDetails;
import com.dbms.mySchoolApp.models.Grades;
import com.dbms.mySchoolApp.models.Attendance;
import com.dbms.mySchoolApp.models.ClassDetails;
import com.dbms.mySchoolApp.models.ClassStudent;
import com.dbms.mySchoolApp.models.Grades;

@Transactional
@Controller
public class AdminController {
	
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
    
    @GetMapping("/admin")
    public String admin(Model model) {
        model.addAttribute("title", "Admin page");
        model.addAttribute("message", "Welcome to our website!");
        return "home/admin";
    }
    
    @GetMapping("/admin/students")
    public String students(Model model) {
        model.addAttribute("title", "Students Portal");
        model.addAttribute("message", "Welcome to the Students Portal!");
        return "admin/student";
    }
    
    @GetMapping("/admin/teacher")
    public String teacher(Model model) {
        model.addAttribute("title", "Teachers Portal");
        model.addAttribute("message", "Welcome to the Teachers Portal!");
        return "admin/teacher";
    }
    
    @GetMapping("/admin/studentRegister")
    public String addStudent(Model model) {
        String role = securityService.findLoggedInUserRole();
        model.addAttribute("title", "Student Portal");
        model.addAttribute("message", "Create Student's profile");
        model.addAttribute("submessage1", "Add Student Details");
        model.addAttribute("buttonmessage", "Proceed");
        model.addAttribute("submiturl", "/admin/studentRegister");
        Student student = new Student();
        model.addAttribute("student", student);
        model.addAttribute("edit", false);
        return "admin/addEditStudent";
    }

    @PostMapping("/admin/studentRegister")
    public String addStudent(@Valid @ModelAttribute("student") Student student, BindingResult bindingResult, Model model) {
    	studentValidator.validate(student, bindingResult);
    	studentValidator.validateExistingRole(student, bindingResult);
    	numberValidator.validateOp(student.getPhoneNumber(),bindingResult, "phoneNumber");
        if (bindingResult.hasErrors()) {
        	String role = securityService.findLoggedInUserRole();
            model.addAttribute("title", "Student Portal");
            model.addAttribute("message", "Create Student's profile");
            model.addAttribute("submessage1", "Add Student Details");
            model.addAttribute("buttonmessage", "Proceed");
            model.addAttribute("submiturl", "/admin/studentRegister");
            model.addAttribute("student", student);
            model.addAttribute("edit", false);
            return "admin/addEditStudent";
        }
        User user = student.getUser();
        user.setRole("ROLE_STUDENT");
        User exist = userDao.findByEmailAddress(user.getEmailAddress());
        if(exist == null) {
        	String password = UUID.randomUUID().toString();
            user.setPassword(password);
            userService.save(user);
            userService.sendVerificationEmail(user,password);
        	student.setUser(user);
            student = studentDao.save(student);
            return "redirect:/admin/parent/"+student.getRegistrationNo();
        }
        student.setUser(exist);
        student = studentDao.save(student);
        model.addAttribute("submiturl", "/admin/studentRegister");
        return "redirect:/admin/parent/"+student.getRegistrationNo();    
    }
    
    @GetMapping("/admin/studentDetail")
    public String studentDetail(Model model) {
    	model.addAttribute("title", "Find a student");
        model.addAttribute("student", new Student());
        return "admin/findStudent";
    }
    
    @PostMapping("/admin/findStudent")
    	public String findStudent(@Valid @ModelAttribute("student") Student student, BindingResult bindingResult, Model model) {
    	studentValidator.validateExisting(student, bindingResult);
        if (bindingResult.hasErrors()) {
        	model.addAttribute("title", "Find a student");
            model.addAttribute("student", student);
            return "admin/findStudent";
        }
    	model.addAttribute("title", "Student Detail");
    	Student st1 = studentDao.getByEmailAddress(student.getUser().getEmailAddress());
        model.addAttribute("student", st1);
        return "admin/studentDetail";
    }
    
    @GetMapping("/admin/editStudent/{registrationNo}")
    public String editStudent(@PathVariable("registrationNo") int registrationNo, Model model) {
    	if(studentDao.get(registrationNo) != null) {
    		Student student = studentDao.get(registrationNo);
	    	String role = securityService.findLoggedInUserRole();
	        model.addAttribute("title", "Student Portal");
	        model.addAttribute("message", "Create Student's profile");
	        model.addAttribute("submessage1", "Add Student Details");
	        model.addAttribute("buttonmessage", "Proceed");
	        model.addAttribute("submiturl", "/admin/editStudent/"+ registrationNo);
	        model.addAttribute("student", student);
	        model.addAttribute("edit", true);
	        return "admin/addEditStudent";
    	}
    	return "/";
    }
    
    @PostMapping("/admin/editStudent/{registrationNo}")
    public String editStudent(@PathVariable("registrationNo") int registrationNo, @Valid @ModelAttribute("student") Student student, BindingResult bindingResult, Model model) {
    	if(studentDao.get(registrationNo) != null) {
    		numberValidator.validateOp(student.getPhoneNumber(), bindingResult, "phoneNumber");
    		Student stud1 = studentDao.get(registrationNo);
    		if(!(stud1.getUser().getEmailAddress()).equals(student.getUser().getEmailAddress()) ){
    			studentValidator.validate(student, bindingResult);
            	studentValidator.validateExistingRole(student, bindingResult);
            	if (bindingResult.hasErrors()) {
                	String role = securityService.findLoggedInUserRole();
                    model.addAttribute("title", "Student Portal");
                    model.addAttribute("message", "Create Student's profile");
                    model.addAttribute("submessage1", "Add Student Details");
                    model.addAttribute("buttonmessage", "Proceed");
                    model.addAttribute("submiturl", "/admin/editStudent/"+ registrationNo);
        	        model.addAttribute("student", student);
        	        model.addAttribute("edit", true);
                    return "admin/addEditStudent";  
                }
            	User usr = stud1.getUser();
            	String oldEmail = stud1.getUser().getEmailAddress();
                if(userDao.get(student.getUser().getEmailAddress())==null) {
                	String password = UUID.randomUUID().toString();
                    usr.setPassword(password);
                    
                    usr.setEmailAddress(student.getUser().getEmailAddress());
                    userService.save(usr);
                    userService.sendVerificationEmail(usr,password);
                }
                else {
                usr = userDao.get(student.getUser().getEmailAddress());
                }
                
            	student.setUser(usr);
            	studentDao.update(student);
            	userDao.delete(oldEmail);
            	return "redirect:/admin/editParent/{registrationNo}";
    		}
    		if (bindingResult.hasErrors()) {
            	String role = securityService.findLoggedInUserRole();
                model.addAttribute("title", "Student Portal");
                model.addAttribute("message", "Create Student's profile");
                model.addAttribute("submessage1", "Add Student Details");
                model.addAttribute("buttonmessage", "Proceed");
                model.addAttribute("submiturl", "/admin/editStudent/"+ registrationNo);
    	        model.addAttribute("student", student);
    	        model.addAttribute("edit", true);
                return "admin/addEditStudent";  
            }
    		User user = stud1.getUser();
    		student.setUser(user);
    		studentDao.update(student);
    		return "redirect:/admin/editParent/{registrationNo}";
    	}
    	return "admin/student";
    }
    
    @GetMapping("/admin/deleteStudent/{registrationNo}")
    public String deleteStudent(@PathVariable("registrationNo") int registrationNo, Model model) {
    	if(studentDao.get(registrationNo) != null) {
    		String emailAddress= studentDao.get(registrationNo).getUser().getEmailAddress();
    		userDao.delete(emailAddress);
    		studentDao.delete(registrationNo);
    		
	    	model.addAttribute("title", "Find a student");
	        model.addAttribute("student", new Student());
	        return "admin/findStudent";
    	}
    	return "/";
    }
    @GetMapping("/admin/viewAllStudents")
    public String viewAllStudents(Model model) {
        model.addAttribute("title", "Students List");
        model.addAttribute("message", "View all the Students");
        List<Student> students = studentDao.getAll();
        model.addAttribute("students", students);
        return "admin/viewAllStudents";
    }
    @GetMapping("/admin/parent/{registrationNo}")
    public String getParents(@PathVariable("registrationNo") int registrationNo, Model model) {
    	if(studentDao.get(registrationNo) != null) {
    		Parents parents = new Parents();
    		parents.setRegistrationNo(registrationNo);
    		System.out.println(parents.getPhoneNumber1());
	    	String role = securityService.findLoggedInUserRole();
	        model.addAttribute("title", "Student Portal");
	        model.addAttribute("message", "Create Student's profile");
	        model.addAttribute("submessage1", "Add Parents Details");
	        model.addAttribute("buttonmessage", "Proceed");
	        model.addAttribute("submiturl", "/admin/parent/"+ registrationNo);
	        model.addAttribute("parents", parents);
	        return "admin/parent";
    	}
    	return "admin/student";
    }
    @PostMapping("/admin/parent/{registrationNo}")
	public String setParents(@Valid @ModelAttribute("parents") Parents parents, BindingResult bindingResult, Model model) {
	if(studentDao.get(parents.getRegistrationNo()) == null)return "admin/student";
	if(parentsDao.get(parents.getRegistrationNo())!= null)return "admin/student";
	System.out.println(parents.getPhoneNumber1());
	if(parents.getPhoneNumber1()!=null && !parents.getPhoneNumber1().equals(""))
	numberValidator.validateOp(parents.getPhoneNumber1(),bindingResult , "phoneNumber1");
	if(parents.getPhoneNumber2()!=null && !parents.getPhoneNumber2().equals(""))
	numberValidator.validateOp(parents.getPhoneNumber2(),bindingResult , "phoneNumber2");
    if (bindingResult.hasErrors()) {
    	model.addAttribute("title", "Student Portal");
        model.addAttribute("message", "Create Student's profile");
        model.addAttribute("submessage1", "Add Parents Details");
        model.addAttribute("buttonmessage", "Proceed");
        model.addAttribute("submiturl", "/admin/parent/"+ parents.getRegistrationNo());
        model.addAttribute("parents", parents);
        model.addAttribute("edit", true);
        return "admin/parent";
    }
    parentsDao.save(parents);
    return "redirect:/admin/studentFees/{registrationNo}";
}
    @GetMapping("/admin/editParent/{registrationNo}")
    public String getEditParents(@PathVariable("registrationNo") int registrationNo, Model model) {
    	if(studentDao.get(registrationNo) != null) {
    		Parents parents = parentsDao.get(registrationNo);
    		if(parents == null) {
    			parents = new Parents();
    		}
    		System.out.println(parents.getPhoneNumber1());
	    	String role = securityService.findLoggedInUserRole();
	        model.addAttribute("title", "Student Portal");
	        model.addAttribute("message", "Create Student's profile");
	        model.addAttribute("submessage1", "Add Parents Details");
	        model.addAttribute("buttonmessage", "Proceed");
	        model.addAttribute("submiturl", "/admin/editParent/"+ registrationNo);
	        model.addAttribute("parents", parents);
	        return "admin/parent";
    	}
    	return "/admin/student";
    }
    @PostMapping("/admin/editParent/{registrationNo}")
	public String editParents(@Valid @ModelAttribute("parents") Parents parents, BindingResult bindingResult, Model model) {
	if(studentDao.get(parents.getRegistrationNo()) == null)return "/admin/student";
	System.out.println(parents.getPhoneNumber1());
	if(parents.getPhoneNumber1()!=null && !parents.getPhoneNumber1().equals(""))
	numberValidator.validateOp(parents.getPhoneNumber1(),bindingResult , "phoneNumber1");
	if(parents.getPhoneNumber2()!=null && !parents.getPhoneNumber2().equals(""))
	numberValidator.validateOp(parents.getPhoneNumber2(),bindingResult , "phoneNumber2");
    if (bindingResult.hasErrors()) {
    	System.out.println("j");
    	model.addAttribute("title", "Student Portal");
        model.addAttribute("message", "Create Student's profile");
        model.addAttribute("submessage1", "Add Parents Details");
        model.addAttribute("buttonmessage", "Proceed");
        model.addAttribute("submiturl", "/admin/editParent/"+ parents.getRegistrationNo());
        model.addAttribute("parents", parents);
        model.addAttribute("edit", true);
        return "admin/parent";
    }
    if(parentsDao.get(parents.getRegistrationNo())==null)
    	parentsDao.save(parents);
    else 
    {
    	parentsDao.update(parents);
    }
    return "redirect:/admin/editFees/{registrationNo}";
}
    
    @GetMapping("/admin/studentFees/{registrationNo}")
    public String addStudentFees(@PathVariable("registrationNo") int registrationNo, Model model) {
    	if(studentDao.get(registrationNo) != null) {
    		StudentFees studentFees = new StudentFees();
    		studentFees.setRegistrationNo(registrationNo);
	    	String role = securityService.findLoggedInUserRole();
	        model.addAttribute("title", "Student Portal");
	        model.addAttribute("message", "Create Student's profile");
	        model.addAttribute("submessage1", "Add Student Fees Details");
	        model.addAttribute("buttonmessage", "Submit");
	        model.addAttribute("submiturl", "/admin/studentFees/"+ registrationNo);
	        model.addAttribute("studentFees", studentFees);
	        return "admin/studentFees";
    	}
    	return "admin/student";
    }
    @PostMapping("/admin/studentFees/{registrationNo}")
	public String addFees(@Valid @ModelAttribute("studentFees") StudentFees studentFees, BindingResult bindingResult, Model model) {
	if(studentDao.get(studentFees.getRegistrationNo()) == null)return "/admin/student";
	if(studentFeesDao.get(studentFees.getRegistrationNo())!= null)return "admin/student";
    if (bindingResult.hasErrors()) {
    	model.addAttribute("title", "Student Portal");
        model.addAttribute("message", "Create Student's profile");
        model.addAttribute("submessage1", "Add Student Fees Details");
        model.addAttribute("buttonmessage", "Submit");
        model.addAttribute("submiturl", "/admin/studentFees/"+ studentFees.getRegistrationNo());
        model.addAttribute("studentFees", studentFees);
        return "admin/studentFees";
    }
    studentFeesDao.save(studentFees);
    return "admin/student";
}
    @GetMapping("/admin/editFees/{registrationNo}")
    public String getEditFees(@PathVariable("registrationNo") int registrationNo, Model model) {
    	if(studentDao.get(registrationNo) != null) {
    		StudentFees studentFees = studentFeesDao.get(registrationNo);
    		if(studentFees == null) {
    			studentFees = new StudentFees();
    		}
	        model.addAttribute("title", "Student Portal");
	        model.addAttribute("message", "Create Student's profile");
	        model.addAttribute("submessage1", "Edit Students Fees Details");
	        model.addAttribute("buttonmessage", "Submit");
	        model.addAttribute("submiturl", "/admin/editFees/"+ registrationNo);
	        model.addAttribute("studentFees", studentFees);
	        return "admin/studentFees";
    	}
    	return "/admin/student";
    }
    @PostMapping("/admin/editFees/{registrationNo}")
	public String editFees(@Valid @ModelAttribute("studentFees") StudentFees studentFees, BindingResult bindingResult, Model model) {
	if(studentDao.get(studentFees.getRegistrationNo()) == null)return "/admin/student";
    if (bindingResult.hasErrors()) {
    	model.addAttribute("title", "Student Portal");
        model.addAttribute("message", "Create Student's profile");
        model.addAttribute("submessage1", "Edit Students Fees Details");
        model.addAttribute("buttonmessage", "Submit");
        model.addAttribute("submiturl", "/admin/editFees/"+ studentFees.getRegistrationNo());
        model.addAttribute("studentFees", studentFees);
        model.addAttribute("edit", true);
        return "admin/studentFees";
    }
    if(studentFeesDao.get(studentFees.getRegistrationNo())==null)
    	studentFeesDao.save(studentFees);
    else {
    	studentFeesDao.update(studentFees);
    }
    return "/admin/student";
}
    
    @GetMapping("/admin/viewFeesDetails")
    public String viewFeesDetails(Model model) {
        model.addAttribute("title", "Fee Details");
        model.addAttribute("message", "View Fees Details");
        List<FeeDetails> feeDetails = feeDetailsDao.getAll();
        List<HashMap<Integer, Integer>> list = new ArrayList<HashMap<Integer, Integer>>();
        for(int i=0;i<feeDetails.size();i++) {
        	int j=i;
        	HashMap<Integer, Integer> mp = new HashMap<Integer,Integer>();
        	while(j<feeDetails.size() && feeDetails.get(j).getClassNo()==i+1) {
	        	mp.put(feeDetails.get(j).getMonth(), feeDetails.get(j).getFees());
	        	j+=1;
        	}
        	i=j;
        	list.add(mp);
        }
        model.addAttribute("feeDetails", list);
        return "admin/viewFeeDetails";
    }
    
    @GetMapping("/admin/classes")
    public String getClasses(Model model) {
        String role = securityService.findLoggedInUserRole();
        model.addAttribute("title", "Class Portal");
        model.addAttribute("message", "Edit and View Classes");
        model.addAttribute("submessage1", "Add Class");
        model.addAttribute("submessage2", "View Class");
        model.addAttribute("buttonmessage1", "Add Class");
        model.addAttribute("buttonmessage2", "Delete Class");
        model.addAttribute("buttonmessage3", "View Class");
        model.addAttribute("submiturl1", "/admin/addDeleteClass");
        model.addAttribute("submiturl2", "/admin/viewClass");
        ClassDetails classDetails = new ClassDetails();
        model.addAttribute("classDetails", classDetails);
        ClassStudent classStudent = new ClassStudent();
        model.addAttribute("classStudent", classStudent);
        return "admin/getClasses";
    }
    
 
    @PostMapping("/admin/addDeleteClass")
    public String addClass( @Valid @ModelAttribute("classDetails") ClassDetails classDetails, BindingResult bindingResult, Model model, HttpServletRequest request) {
    	if(request.getParameter("submit").equals("1"))
    	{classValidator.validate(classDetails, bindingResult);
    	if (!(bindingResult.hasErrors())) {
    		classDetailsDao.save(classDetails);
    		 classDetails = new ClassDetails();}
    	}
    	else {
    		classValidator.validateClass(classDetails, bindingResult);
    		if (!(bindingResult.hasErrors())) {
    			classDetailsDao.delete(classDetailsDao.get(classDetails.getClassNo(),classDetails.getSection()).getClassId());
        		classDetails = new ClassDetails();}
    	}
    		String role = securityService.findLoggedInUserRole();
            model.addAttribute("title", "Class Portal");
            model.addAttribute("message", "Edit and View Classes");
            model.addAttribute("submessage1", "Add Class");
            model.addAttribute("submessage2", "View Class");
            model.addAttribute("buttonmessage1", "Add Class");
            model.addAttribute("buttonmessage2", "Delete Class");
            model.addAttribute("buttonmessage3", "View Class");
            model.addAttribute("submiturl1", "/admin/addDeleteClass");
            model.addAttribute("submiturl2", "/admin/viewClass");
            model.addAttribute("classDetails", classDetails);
            ClassStudent classStudent = new ClassStudent();
            model.addAttribute("classStudent", classStudent);
            return "admin/getClasses";
    }
    
    @PostMapping("/admin/viewClass")
    public String viewClass( @Valid @ModelAttribute("classStudent") ClassStudent classStudent, BindingResult bindingResult, Model model) {
    	classStudentValidator.validateClass(classStudent, bindingResult);
    	if ((bindingResult.hasErrors())) {
    		String role = securityService.findLoggedInUserRole();
            model.addAttribute("title", "Class Portal");
            model.addAttribute("message", "Edit and View Classes");
            model.addAttribute("submessage1", "Add Class");
            model.addAttribute("submessage2", "View Class");
            model.addAttribute("buttonmessage1", "Add Class");
            model.addAttribute("buttonmessage2", "Delete Class");
            model.addAttribute("buttonmessage3", "View Class");
            model.addAttribute("submiturl1", "/admin/addDeleteClass");
            model.addAttribute("submiturl2", "/admin/viewClass");
            model.addAttribute("classDetails", new ClassDetails());
            model.addAttribute("classStudent", classStudent);
            return "admin/getClasses";
    	}
    	
    	int classId = classDetailsDao.get(classStudent.getClassDetails().getClassNo(),classStudent.getClassDetails().getSection()).getClassId();
    	classStudent.getClassDetails().setClassId(classId);
    	List<ClassStudent> classStudents = classStudentDao.getAllInClass(classStudent.getClassDetails(),classStudent.getStartYear());
    	List<TeacherClassSubject>  teacherClassSubjects = teacherClassSubjectDao.getAllInClass(classStudent.getClassDetails(),classStudent.getStartYear());
    	TeacherClassSubject teacherClassSubject = new TeacherClassSubject();
    	teacherClassSubject.setClassDetails(classStudent.getClassDetails());
    	teacherClassSubject.setYear(classStudent.getStartYear());
    	model.addAttribute("classStudents", classStudents);
    	model.addAttribute("classStudent", classStudent);
    	model.addAttribute("teacherClassSubject", teacherClassSubject);
        model.addAttribute("teacherClassSubjects", teacherClassSubjects);
    	return "admin/classStudents";
    }
    
    @PostMapping("/admin/deleteClassStudent")
    public String deleteClassStudent( @Valid @ModelAttribute("classStudent") ClassStudent classStudent, BindingResult bindingResult, Model model) {
    	int classId = classStudent.getClassDetails().getClassId();
    	classStudentDao.delete(classStudent);
    	classStudent.getStudent().setRegistrationNo(0);
    	List<ClassStudent> classStudents = classStudentDao.getAllInClass(classStudent.getClassDetails(),classStudent.getStartYear());
    	List<TeacherClassSubject>  teacherClassSubjects = teacherClassSubjectDao.getAllInClass(classStudent.getClassDetails(),classStudent.getStartYear());
    	TeacherClassSubject teacherClassSubject = new TeacherClassSubject();
    	teacherClassSubject.setClassDetails(classStudent.getClassDetails());
    	teacherClassSubject.setYear(classStudent.getStartYear());
    	model.addAttribute("classStudents", classStudents);
    	model.addAttribute("classStudent", classStudent);
    	model.addAttribute("teacherClassSubject", teacherClassSubject);
        model.addAttribute("teacherClassSubjects", teacherClassSubjects);
    	return "admin/classStudents";
    }
    
}
