import java.util.ArrayList;
import java.util.List;

public class Student {
    /** 
     * The student's name
     */
    private String name;

    /**
     * The student's grade
     */
    private int grade;

    /**
     * The student's current total points
     */
    private int points;
    /**
     * The student's email
     */
	private String email;

	/**
     * The student's password
     */
	
	private String password;

     /**
      * Creates a new student object
      */
    public Student(String name, String email, String password, int grade, int points){
        this.name = name;
        this.email = email;
        this.password = password;
        this.grade = grade;
        this.points = points;
    }

    public String getName(){
        return name;
    }

    public void setName(String name){
        this.name = name;
    }

    public int getGrade(){
        return grade;
    }

    public void setGrade(int grade){
        this.grade = grade;
    }

    public int getPoints(){
        return points;
    }

    public void setPoints(int points){
        this.points = points;
    }

    //creates a list of all the students
    private static List<Student> studentList = new ArrayList<Student>();

    //add a student to the students list
    public static void addStudent(Student student) {
        studentList.add(student);
    }

    /**
     * Unregisters an existing student account. Does nothing if the account does not exist.
     *
     * @param student The student account to remove
     */
    public static void removeStudent(Student student) {
        studentList.remove(student);
    }
    
    /**
     * Gets a list of all active student accounts.
     *
     * @return A list of students
     */
    public static List<Student> getStudents() {
        return studentList;
    }

    //creates a list of all grade 9 students
    private static List<Student> nineStudents = new ArrayList<Student>();

    //adds students who are in grade 9 to the list
    public static void addNineStudent(Student student) {
        if (student.getGrade() == 9){
            nineStudents.add(student);
        } else {
            System.out.println("The student is not in grade 9");
        }
    }

    //removes a grade 9 student from the grade 9 students list
    public static void removeNineStudent(Student student) {
        if (student.getGrade() == 9) {
            nineStudents.remove(student);
        } else {
            System.out.println("The student is not in grade 9");
        }
    }

    //gets a list of all grade 9 students
    public static List<Student> getNineStudents() {
        return nineStudents;
    }

    //gets a list of all the grade 9 students individual points
    public static List<Integer> getNinePoints() {
        List<Integer> ninePoints = new ArrayList<Integer>();
        for (Student student : Student.getNineStudents()) {
            ninePoints.add(student.getPoints());
        }

        return ninePoints;
    }

    //creates a list of all grade 10 students
    private static List<Student> tenStudents = new ArrayList<Student>();

    //adds students who are in grade 10 to the list
    public static void addTenStudent(Student student) {
        if (student.getGrade() == 10){
            tenStudents.add(student);
        } else {
            System.out.println("The student is not in grade 10");
        }
    }

    //removes a grade 10 student from the grade 10 students list
    public static void removeTenStudent(Student student) {
        if (student.getGrade() == 10) {
            tenStudents.remove(student);
        } else {
            System.out.println("The student is not in grade 10");
        }
    }

    //gets a list of all grade 10 students
    public static List<Student> getTenStudents() {
        return tenStudents;
    }

    //gets a list of all the grade 10 students individual points
    public static List<Integer> getTenPoints() {
        List<Integer> tenPoints = new ArrayList<Integer>();
        for (Student student : Student.getTenStudents()) {
            tenPoints.add(student.getPoints());
        }

        return tenPoints;
    }

    //creates a list of all grade 11 students
    private static List<Student> elevenStudents = new ArrayList<Student>();

    //adds students who are in grade 11 to the list
    public static void addElevenStudent(Student student) {
        if (student.getGrade() == 11){
            elevenStudents.add(student);
        } else {
            System.out.println("The student is not in grade 11");
        }
    }

    //removes a grade 11 student from the grade 11 students list
    public static void removeElevenStudent(Student student) {
        if (student.getGrade() == 11) {
            elevenStudents.remove(student);
        } else {
            System.out.println("The student is not in grade 11");
        }
    }

    //gets a list of all grade 11 students
    public static List<Student> getElevenStudents() {
        return elevenStudents;
    }

    //gets a list of all the grade 11 students individual points
    public static List<Integer> getElevenPoints() {
        List<Integer> elevenPoints = new ArrayList<Integer>();
        for (Student student : Student.getElevenStudents()) {
            elevenPoints.add(student.getPoints());
        }

        return elevenPoints;
    }

    //creates a list of all grade 12 students
    private static List<Student> twelveStudents = new ArrayList<Student>();

    //adds students who are in grade 12 to the list
    public static void addTwelveStudent(Student student) {
        if (student.getGrade() == 12){
            twelveStudents.add(student);
        } else {
            System.out.println("The student is not in grade 12");
        }
    }

    //removes a grade 12 student from the grade 12 students list
    public static void removeTwelveStudent(Student student) {
        if (student.getGrade() == 12) {
            twelveStudents.remove(student);
        } else {
            System.out.println("The student is not in grade 12");
        }
    }

    //gets a list of all grade 12 students
    public static List<Student> getTwelveStudents() {

        return twelveStudents;
    }

    //gets a list of all the grade 12 students individual points
    public static List<Integer> getTwelvePoints() {
        List<Integer> twelvePoints = new ArrayList<Integer>();
        for (Student student : Student.getTwelveStudents()) {
            twelvePoints.add(student.getPoints());
        }

        return twelvePoints;
    }
}
