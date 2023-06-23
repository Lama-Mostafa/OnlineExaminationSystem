import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Instructor extends Person implements Review {
    ArrayList<Course> courses=new ArrayList<Course>();
    ArrayList<Student> students=new ArrayList<Student>();
    ArrayList<Instructor> instructors=new ArrayList<Instructor>();
    private File userFile;
    private int cCount=0;
    private int sCount=0;

    public Instructor(String name, int age, String mobilenumber, String emailAddress, String username, String pass) {
        super(name, age, mobilenumber, emailAddress, username, pass);
        userFile=new File(username+".txt");
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
    }

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

    public ArrayList<Course> getCourses() {
        return courses;
    }

    public ArrayList<Student> getStudents() {
        return students;
    }


    public int getcCount() {
        return cCount;
    }

    public void setcCount(int cCount) {
        this.cCount = cCount;
    }

    public void addCourse(Course course){
        courses.add(course);
        cCount++;
    }
    public void addStudent(Student student){
        students.add(student);
        sCount++;
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
    public void viewInstructorStudents() throws FileNotFoundException {
        Scanner userO=new Scanner(userFile);
        skipLines(userO);
        while(userO.hasNext()) {
            String course = userO.nextLine();
            System.out.println(course+" students :");
            File courseFile = new File(course + ".txt");
            Scanner courseScanner = new Scanner(courseFile);
            courseScanner.nextLine();
            while (courseScanner.hasNext()) {
                String studentUsername = courseScanner.nextLine();
                File studentFile = new File(studentUsername + ".txt");
                Scanner studentScanner = new Scanner(studentFile);
                studentScanner.nextLine();
                System.out.println(studentScanner.nextLine());
            }
            System.out.println();
        }
    }

    @Override
    public void skipLines(Scanner userO) {
        int i=0;
        while(i<5){
            userO.nextLine();
            i++;
        }
    }
}
