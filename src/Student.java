import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Student extends Person implements Review
{
    private String ID;
    private Exam []exam;
    private static int stcount=0;
    private int examCount=0;
    private char []grades;
    private Course []courses;
    private int courseCount=0;
    private File userFile;
    private Department d;

    public Student(String name, int age, String mobilenumber, String emailAddress, String username, String pass,Department d) {
        super(name, age, mobilenumber, emailAddress, username,pass);
        ID=String.valueOf(stcount);
        stcount++;
        this.d=d;
        userFile=new File(username+".txt");
    }

    public String getD() {
        return String.valueOf(d);
    }

    public void setD(Department d) {
        this.d = d;
    }

    @Override
    public String getUsername() {
        return username;
    }


    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {

        this.name=name;
    }

    @Override
    public int getAge() {
        return age;
    } // leeh de be-return 0,msh mafrod age?!

    @Override
    public void setAge(int age) {

        this.age=age;
    }

    @Override
    public String getMobilenumber() {
        return mobilenumber;
    }

    @Override
    public void setMobilenumber(String mobilenumber) {

        this.mobilenumber=mobilenumber;
    }

    @Override
    public String getEmailAddress() {
        return emailAddress;
    }

    @Override
    public void setEmailAddress(String emailAddress) {

        this.emailAddress=emailAddress;
    }

    public Exam[] getExam() {
        return exam;
    }

    public void setExam(Exam[] exam) {
        this.exam = exam;
    }

    public int getExamCount() {
        return examCount;
    }

    public void setExamCount(int examCount) {
        this.examCount = examCount;
    }

    public char[] getGrades() {
        return grades;
    }

    public void setGrades(char[] grades) {
        this.grades = grades;
    }

    public Course[] getCourses() {
        return courses;
    }

    public void setCourses(Course[] courses) {
        this.courses = courses;
    }

    public int getCourseCount() {
        return courseCount;
    }

    public void setCourseCount(int courseCount) {
        this.courseCount = courseCount;
    }

    public void addExam(Exam exam){
        this.exam[examCount]=exam;
        examCount++;
    }
    public void addCourse(Course course){
        this.courses[courseCount]=course;
        courseCount++;

    }


    public void addGrade(char grade,int index){
        grades[index]=grade;
    }


    @Override
    public void viewCourse() throws FileNotFoundException {
        Scanner userO=new Scanner(userFile);
        skipLines(userO);
        while(userO.hasNext()) {
            System.out.println(userO.nextLine());
        }
        System.out.println();
    }


    public void viewGrades() throws FileNotFoundException {
        Scanner userO=new Scanner(userFile);
        skipLines(userO);
        while(userO.hasNext()){
            String course=userO.nextLine();
            File courseExamGradeFile=new File(username+"_"+course+".txt");
            if(courseExamGradeFile.exists()){
                Scanner courseExamGradeFileScanner=new Scanner(courseExamGradeFile);
                System.out.println(course+":");
                System.out.println(courseExamGradeFileScanner.nextLine());
                break;
            }
        }
        System.out.println();
    }
    @Override
    public void skipLines(Scanner userO){//make abstract
        int i=0;
        while(i<6){
            userO.nextLine();
            i++;
        }
    }
}
