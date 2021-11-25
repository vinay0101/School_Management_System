import java.util.*;
import java.io.*;


class User{
	private String id = "Admin";
	private String name = "Admin";
	private String pass = "123";

	public User() {};

	public User(String name, String id, String pass ) {
		this.id = id;
		this.name = name;
		this.pass = pass;
	}

	public void set_id(String id) {
		this.id = id;
	}
	public void set_name(String name) {
		this.name = name;
	}
	public void set_pass(String pass) {
		this.pass = pass;
	} 
	public String get_id() {
		return id;
	}
	public String get_Name() {
		return name;
	}
	public String get_Pass(){
		return pass;
	}

}
class Student extends User{

	private int curr_class;
	private int attendance;
	private ArrayList<Course> courses;

	public Student(String name, String id, String password, int curr_class){
		super(name, id, password);
		this.curr_class = curr_class;
		courses = new ArrayList<Course>();
	}

	public ArrayList<Course> getCourses() {
		return courses;
	}

	public void setCourseObjects(ArrayList<String> crs) {
		for(String st: crs) {
			courses.add(new Course(st));
		}
	}

	public int searchCourseObject(String courseName){
		for(int i=0; i<courses.size(); i++){
			Course c = courses.get(i);
			//System.out.println(c.getCourseName());
			if(c.getCourseName().equals(courseName)){
				return i;
			}
		}
		return -1;
	}

	public void printCourseDetails() {
		for(Course c: courses) {
			System.out.println(c.getCourseName()+"    "+c.getMarks()+ "     "+c.getGrades());
		}
	}

	public void modifyStudentDetail(String name, String id, String password, int curr_class) {
		set_id(id);
		set_name(name);
		set_pass(password);
		this.curr_class=curr_class;
	}

	public void set_Password(String pswd){
		set_pass(pswd);
	}

	public int getAttendance() {
		return attendance;
	}

	public void setAttendance(int attendance) {
		this.attendance = attendance;
	}

	public void viewDetails(){
		System.out.println("Name: " + get_Name() + ", Class: " + curr_class + ", ID: "+ get_id());
		System.out.println("Attendance : "+getAttendance());
		System.out.println("Courses   Marks      Grades");
		printCourseDetails();
	}

	public void viewDetailsOnly(){
		System.out.println("Name: " + get_Name() + ", Class: " + curr_class + ", ID: "+ get_id());
	}

}

class Teacher extends User{
	private ArrayList <String> courses; 

	public Teacher(String name, String id, String password, ArrayList<String> courses){
		super(name, id, password);
		this.courses =courses;
	}

	public void modifyTeacherDetail(String name, String id, String password) {
		set_id(id);
		set_name(name);
		set_pass(password);
	}

	public void set_Password(String pswd){
		set_pass(pswd);
	}

	public void viewCourses() {
		for(String s: courses) {
			System.out.println(s);
		}
	}

	public void viewDetails(){
		System.out.println("Name: " + get_Name() +", ID: "+ get_id());
		System.out.println("Courses: ");
		for(String course:courses) {
			System.out.print(course+" ");
		}

	}
	
	public void viewDetailsOnly(){
		System.out.println("Name: " + get_Name() +", ID: "+ get_id());

	}

}


class Admin extends User{


	ArrayList <Student> stud_data;
	ArrayList <Teacher> teacher_data;


	private ArrayList<Classroom> clrmList;
	private ArrayList<ArrayList<String>> courses = new ArrayList<>();



	public Admin (ArrayList<Student> s_data, ArrayList<Teacher> t_data) {
		this.stud_data = s_data;
		this.teacher_data = t_data;
	}

	public ArrayList<Student> add_student(String name, int curr_class, String id, String password, ArrayList<Classroom> clrmList) {
		Student s = new Student(name, id, password, curr_class);
		this.clrmList=clrmList;
		stud_data.add(s);
		Classroom c = clrmList.get(curr_class-1);
		c.setClassroom_students(s);
		return stud_data;
	}

	public ArrayList<Teacher> add_teacher(String name, String id, String password, ArrayList<String> courses) {
		Teacher t = new Teacher(name, id, password, courses);
		teacher_data.add(t);
		return teacher_data;
	}

	public int search_student_byName(String name){
		for(int i=0; i<stud_data.size(); i++){
			Student s = stud_data.get(i);
			if(s.get_Name().equals(name)){
				return i;
			}
		}
		return -1;
	}

	public int search_student_byID(String id){
		for(int i=0; i<stud_data.size(); i++){
			Student s = stud_data.get(i);
			if(s.get_id().equals(id)){
				return i;
			}
		}
		return -1;
	}

	public int search_teacher_byName(String name){
		for(int i=0; i<teacher_data.size(); i++){
			Teacher t = teacher_data.get(i);
			if(t.get_Name().equals(name)){
				return i;
			}
		}
		return -1;
	}

	public int search_teacher_byID(String id){
		for(int i=0; i<teacher_data.size(); i++){
			Teacher t = teacher_data.get(i);
			if(t.get_id().equals(id)){
				return i;
			}
		}
		return -1;
	}

	public ArrayList<Student> remove_student(int n, String str){

		if(n==1){
			int idx_student = search_student_byName(str);
			stud_data.remove(idx_student);
		}

		else{
			int idx_student = search_student_byID(str);
			stud_data.remove(idx_student);
		}   

		return stud_data;
	}

	public ArrayList<Student> modify_student(int n, String str, String name, String id, String password, int curr_class){

		if(n==1){
			int idx_student = search_student_byName(str);
			stud_data.get(idx_student).modifyStudentDetail(name, id, password, curr_class);

		}

		else{
			int idx_student = search_student_byID(str);
			stud_data.get(idx_student).modifyStudentDetail(name, id, password, curr_class);
		}   

		return stud_data;
	}

	public ArrayList<Teacher> modify_teacher(int n, String str, String name, String id, String password){

		if(n==1){
			int idx_teacher = search_teacher_byName(str);
			teacher_data.get(idx_teacher).modifyTeacherDetail(name, id, password);

		}

		else{
			int idx_teacher = search_student_byID(str);
			teacher_data.get(idx_teacher).modifyTeacherDetail(name, id, password);
		}   

		return teacher_data;
	}



	public ArrayList<Teacher> remove_teacher(int n, String str){

		if(n==1){
			int idx_teacher = search_teacher_byName(str);
			teacher_data.remove(idx_teacher);
		}

		else{
			int idx_teacher = search_teacher_byID(str);
			teacher_data.remove(idx_teacher);
		}   

		return teacher_data;
	}

	public void view_Details_Students(){
		System.out.println("Student details are :-");
		for(Student s: stud_data){
			s.viewDetailsOnly();
		}
	}

	public ArrayList<Classroom> add_courses(int cl, ArrayList<String> courses) {
		clrmList.get(cl-1).setCourses(courses);
		clrmList.get(cl-1).viewCourses();
		return clrmList;
	}

	public void view_Details_Teachers(){
		for(Teacher t: teacher_data){
			t.viewDetailsOnly();
		}
	}
}

class Course{
	private String courseName;
	private int marks;
	private String grades;

	public Course(String courseName){
		this.courseName=courseName;
	}
	public String getCourseName() {
		return courseName;
	}
	public int getMarks() {
		return marks;
	}
	public String getGrades() {
		return grades;
	}
	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}
	public void setMarks(int marks) {
		this.marks = marks;
	}
	public void setGrades(String grades) {
		this.grades = grades;
	}


}

class Classroom {
	private int cl;
	private ArrayList<Student> classroom_students;
	private ArrayList<String> courses;

	public Classroom(int cl, ArrayList<String> courses){
		this.cl=cl;
		//System.out.println("Welcome to class "+cl);
		classroom_students = new ArrayList<Student>();
		this.courses=courses;
	}

	public void viewCourses() {
		for(String str: courses)
			System.out.println(str);
	}

	public void viewStudents() {
		for(Student st: classroom_students)
			System.out.println(st.get_Name());
	}

	public ArrayList<String> getCourses() {
		return courses;
	}

	public void setCourses(ArrayList<String> courses) {
		this.courses = courses;
	}

	public ArrayList<Student> getClassroom_students() {
		return classroom_students;
	}

	public void setClassroom_students(Student s) {
		classroom_students.add(s);
		s.setCourseObjects(courses);
	}

}



public class main{
	public static ArrayList <Student> s_data = new ArrayList<Student>();
	public static ArrayList <Teacher> t_data = new ArrayList<Teacher>();

	static int search_student_byName(String name){
		for(int i=0; i<s_data.size(); i++){
			Student s = s_data.get(i);
			if(s.get_Name().equals(name)){
				return i;
			}
		}
		return -1;
	}

	static int search_student_byID(String id){
		for(int i=0; i<s_data.size(); i++){
			Student s = s_data.get(i);
			if(s.get_id().equals(id)){
				return i;
			}
		}
		return -1;
	}

	static int search_teacher_byName(String name){
		for(int i=0; i<t_data.size(); i++){
			Teacher t = t_data.get(i);
			if(t.get_Name().equals(name)){
				return i;
			}
		}
		return -1;
	}

	static int search_teacher_byID(String id){
		for(int i=0; i<t_data.size(); i++){
			Teacher t = t_data.get(i);
			if(t.get_id().equals(id)){
				return i;
			}
		}
		return -1;
	}



	public static void main(String[] args) throws NumberFormatException, IOException {

		System.out.println("----------WELCOME TO THE STUDENT MANAGEMENT SYSTEM-------");
		System.out.println();

		ArrayList<Classroom> clrmList = new ArrayList<Classroom>(13);

		ArrayList<ArrayList<String>> coursesArrayList = new ArrayList<>(12);

		int j=1;

		for(int i=0;i<12;i++) {
			ArrayList<String> arr = new ArrayList<String>();
			arr.add("HINDI"+j);
			arr.add("ENGLISH"+j);
			arr.add("MATH"+j);
			arr.add("SCIENCE"+j);
			arr.add("SOCIAL"+j);
			j++;
			coursesArrayList.add(arr);
		}

		for(int i=0; i<12; i++) {
			clrmList.add(new Classroom((i+1),coursesArrayList.get(i)));
		}



		//Starting Point

		while(true){

			System.out.println("Login to the System:-");
			System.out.println("Enter: 1 - Admin");
			System.out.println("       2 - Teacher");
			System.out.println("       3 - Student");
			System.out.println("       4 - Exit");


			BufferedReader br = new BufferedReader (new InputStreamReader(System.in));
			int num_login = Integer.parseInt(br.readLine());
			System.out.println();

			Admin admin = new Admin(s_data, t_data);


			ArrayList<String> arr1 = new ArrayList<String>();
			arr1.add("HINDI12");
			arr1.add("ENGLISH12");
			arr1.add("MATH10");


			ArrayList<String> arr2 = new ArrayList<String>();
			arr2.add("SCIENCE10");
			arr2.add("SOCIAL12");

			admin.add_teacher("Megha Somani", "2019T001","2019T001",arr1);
			admin.add_teacher("Mamta Menariya", "2019T002","2019T002",arr2);

			if(num_login==1){

				System.out.println("Enter ID");
				String login_id_admin = br.readLine();

				System.out.println("Enter Password");
				String login_pswd_admin = br.readLine();
				System.out.println();

				if(admin.get_Name().equals(login_id_admin)&&admin.get_Pass().equals(login_pswd_admin)) {
					System.out.println("-----Hello! Admin----");
				}
				else {
					System.out.println("Invalid details !!");
					System.out.println();
					continue;
				}


				while(true) {

					System.out.println("Enter: 1 - Add Student");
					System.out.println("       2 - Remove Student");
					System.out.println("       3 - Modify Student details");
					System.out.println("       4 - Add Teacher");
					System.out.println("       5 - Remove Teacher");
					System.out.println("       6 - Modify Teacher details");
					System.out.println("       7 - View Student Details");
					System.out.println("       8 - View Teacher Details");
					System.out.println("       9 - Exit");

					int num_admin = Integer.parseInt(br.readLine());
					System.out.println();

					if(num_admin == 1){

						System.out.println("Enter the name, class, id of the student to be added.");
						String[] str = new String[3];

						for(int i=0; i<3; i++) {
							str[i] = br.readLine();
						}

						String passwordString =str[2];

						s_data = admin.add_student(str[0], Integer.parseInt(str[1]), str[2], passwordString, clrmList);
						System.out.println("Student successfully added.");
						System.out.println();
						continue;	 
					}

					if(num_admin == 2){
						System.out.println("Enter: 1 - Remove by Name");
						System.out.println("       2 - Remove by ID");

						int num_remove = Integer.parseInt(br.readLine());

						System.out.println("Type the name/ID");
						String nameOrID = br.readLine();
						s_data = admin.remove_student(num_remove, nameOrID);
						System.out.println("Student successfully removed.");
						System.out.println();
						continue;
					}

					if(num_admin==3) {
						System.out.println("Enter: 1 - Modify by Name");
						System.out.println("       2 - Modify by ID");

						int num_modify = Integer.parseInt(br.readLine());

						System.out.println("Type the name/ID");
						String nameOrID = br.readLine();

						System.out.println("Enter new name, id, class.");
						String[] str = new String[3];

						for(int i=0; i<3; i++) {
							str[i] = br.readLine();
						}

						String passwordString =str[1];

						s_data = admin.modify_student(num_modify, nameOrID, str[0], str[1], passwordString, Integer.parseInt(str[2]));
						System.out.println("Student details successfully modified.");
						System.out.println();
						//admin.view_Details_Students();
						continue;

					}

					if(num_admin == 4){

						System.out.println("Enter the name, id of the teacher to be added. Also add the no. of courses he/she will teach and name them.");
						String[] str = new String[2];

						for(int i=0; i<2; i++) {
							str[i] = br.readLine();
						}

						int b = Integer.parseInt(br.readLine());

						ArrayList<String> courseTeach = new ArrayList<>();
						for(int i=0; i<b; i++) {
							courseTeach.add(br.readLine());
						}

						String passwordString =str[1];
						t_data = admin.add_teacher(str[0], str[1], passwordString, courseTeach);
						System.out.println("Teacher successfully added.");
						System.out.println();
						//admin.view_Details_Teachers(); 
						continue;	 

					}

					if(num_admin == 5){
						System.out.println("Enter: 1 - Remove by Name");
						System.out.println("       2 - Remove by ID");

						int num_remove = Integer.parseInt(br.readLine());

						System.out.println("Type the name/ID");
						String nameOrID = br.readLine();
						t_data = admin.remove_teacher(num_remove, nameOrID);
						System.out.println("Teacher successfully removed.");
						System.out.println();
						continue;
					}

					if(num_admin==6) {
						System.out.println("Enter: 1 - Modify by Name");
						System.out.println("       2 - Modify by ID");

						int num_modify = Integer.parseInt(br.readLine());

						System.out.println("Type the name/ID");
						String nameOrID = br.readLine();

						System.out.println("Enter new name, id.");
						String[] str = new String[3];

						for(int i=0; i<2; i++) {
							str[i] = br.readLine();
						}

						String passwordString =str[1];
						t_data = admin.modify_teacher(num_modify, nameOrID, str[0], str[1], passwordString);

						System.out.println("Teacher details successfully modified.");
						System.out.println();
						continue;

					}

					

					if(num_admin==7){
						admin.view_Details_Students();
						System.out.println();
						continue;
					}

					if(num_admin==8){
						admin.view_Details_Teachers();
						System.out.println();
						continue;
					}

					if(num_admin==9){
						System.out.println("-----Thank You admin----");
						System.out.println();
						break;
					}

				}
			}

			else if(num_login==2){

				System.out.println("Enter ID");
				String login_id = br.readLine();

				System.out.println("Enter Password");
				String login_pswd = br.readLine();

				int idx =search_teacher_byID(login_id);
				if(idx>=0) {
					if(t_data.get(idx).get_Pass().equals(login_pswd)) {
						System.out.println("-----Hello! Teacher----");
						System.out.println();

					}
					else {
						System.out.println("Wrong Password");
						System.out.println();
						continue;
					}
				}

				else {
					System.out.print("Wrong id.");
					System.out.println();
					continue;
				}

				while(true) {

					System.out.println("Enter: 1 - To Enter Attendance of the students");
					System.out.println("       2 - To enter marks and grades of a whole class in a particular subject");
					System.out.println("       3 - Set Password");
					System.out.println("       4 - View Details");
					System.out.println("	   5 - Exit");

					int num_teach = Integer.parseInt(br.readLine());
					System.out.println();

					if(num_teach==1) {
						System.out.println("Enter the class");
						int classNo = Integer.parseInt(br.readLine());

						System.out.println("(Add attendance student wise)");
						ArrayList<Student> students = clrmList.get(classNo-1).getClassroom_students();
						for(Student st: students) {
							System.out.print(st.get_Name()+" :");
							int att = Integer.parseInt(br.readLine());
							st.setAttendance(att);
						}
						System.out.println("Attendance successsfully added");
						System.out.println();

					}

					if(num_teach==2) {
						System.out.println("Type the class and course name among your courses shown below: ");
						System.out.println();
						Teacher teacher = t_data.get(idx);
						teacher.viewCourses();
						int cl = Integer.parseInt(br.readLine());
						String string = br.readLine();
						//System.out.println(string);
						System.out.println();
						Classroom classroom = clrmList.get(cl-1);
						ArrayList<Student> students = classroom.getClassroom_students();

						System.out.println("Enter the marks and grades studentwise.");
						for(Student st: students) {
							System.out.print(st.get_Name()+" : ");
							int indx = st.searchCourseObject(string);
							//System.out.println(indx);
							if(idx<0) {
								System.out.print("Course doesnot exist");
								System.out.println();
								continue;
							}
							st.getCourses().get(indx).setMarks(Integer.parseInt(br.readLine()));
							st.getCourses().get(indx).setGrades(br.readLine());
						}

						System.out.println("Successsfully added");
						System.out.println();

					}

					if(num_teach==3) {
						Teacher teacher = t_data.get(idx);
						System.out.print("Enter new Password : ");
						teacher.set_pass(br.readLine());
						System.out.println("Password reset successfully.");
						System.out.println();
					}

					if(num_teach==4) {
						Teacher teacher = t_data.get(idx);
						teacher.viewDetails();
						System.out.println();
					}
					
					if(num_teach==5) {
						System.out.println("-----Thank You Teacher----");
						System.out.println();
						break;
					}
				}
			}

			else if(num_login==3){
				System.out.println("Enter ID");
				String login_id = br.readLine();

				System.out.println("Enter Password");
				String login_pswd = br.readLine();

				int idx =search_student_byID(login_id);
				if(idx>=0) {
					if(s_data.get(idx).get_Pass().equals(login_pswd)) {
						System.out.println("-----Hello! Student----");
						System.out.println();
					}
					else {
						System.out.println("Wrong Password");
						System.out.println();
						continue;
					}
				}

				else {
					System.out.print("Wrong id.");
					System.out.println();
					continue;
				}
				
				while(true) {

					System.out.println("Enter: 1 - Set Password");
					System.out.println("       2 - View Details");
					System.out.println("	   3 - Exit");

					int num_teach = Integer.parseInt(br.readLine());
					System.out.println();

					if(num_teach==1) {
						Student student = s_data.get(idx);
						System.out.print("Enter new Password : ");
						student.set_pass(br.readLine());
						System.out.println("Password reset successfully.");
						System.out.println();
					}

					if(num_teach==2) {
						Student student = s_data.get(idx);
						student.viewDetails();
						System.out.println();
					}
					
					if(num_teach==3) {
						System.out.println("-----Thank You Student----");
						System.out.println();
						break;
					}
				}

			}

			else if(num_login==4){
				System.out.println("-----Thank You----");
				break;
			}

			else{
				System.out.println("-----Invalid number----");
				System.out.println();
				
				continue;
			}
		}
	}
}
