import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.IOException;
import java.io.FileWriter;
public class Admin extends Person implements Review
{
    Scanner sc=new Scanner(System.in);
    ArrayList<Course> courses=new ArrayList<Course>();
    ArrayList<Student> students=new ArrayList<Student>();
    ArrayList<Instructor> instructors=new ArrayList<Instructor>();
    ArrayList<Admin> admins = new ArrayList<Admin>();
    File userFile;

//    protected static int cCount=0;
//    protected static int sCount=0;
//    protected static int iCount=0;


    public Admin(String name, int age, String mobilenumber, String emailAddress, String username, String pass) {
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
    public void setMobilenumber(String mobilenumber) {this.mobilenumber=mobilenumber;}

    @Override
    public String getEmailAddress() {
        return emailAddress;
    }

    @Override
    public void setEmailAddress(String emailAddress) {

        this.emailAddress=emailAddress;
    }


    public Student createStudent(String name, int age, String mobilenumber, String emailAddress, String username, String pass, Department d) throws IOException {
        Student s=new Student(name,age,mobilenumber,emailAddress,username,pass,d);
        File userFile=new File(username+".txt");
        students.add(s);
        PrintWriter userO = new PrintWriter(new FileWriter(userFile, true));// l feh el data kolaha bta3t el student da bs
        PrintWriter stuO = new PrintWriter(new FileWriter(stuF, true));// l feh el usersnames bta3t el students
        stuO.write(username+"\n");
        userO.write(username+" "+pass+"\n");
        String departmentSmall= String.valueOf(d);
        departmentSmall=departmentSmall.toLowerCase();
        userO.write(name+"\n"+emailAddress+"\n"+mobilenumber+"\n"+age+"\n"+d+"\n");
        userO.close();
        stuO.close();
        return s;
    }

    public Instructor createInstructor(String name, int age, String mobilenumber, String emailAddress,String username,String pass) throws IOException {
        Instructor I=new Instructor(name,age,mobilenumber,emailAddress,username,pass);//view students and add bonus to each student//w ymkn ashof reports
        File userFile=new File(username+".txt");
        instructors.add(I);
        PrintWriter userO = new PrintWriter(new FileWriter(userFile, true));
        PrintWriter insO = new PrintWriter(new FileWriter(insF, true));
        insO.write(username+"\n");
        userO.write(username+" "+pass+"\n");
        userO.write(name+"\n"+emailAddress+"\n"+mobilenumber+"\n"+age+"\n");
        userO.close();
        insO.close();
        return I;
    }
    public void createCourse(String name, int cnoOfQuestions,double creditHours) throws IOException {
        Course c=new Course(name,cnoOfQuestions,creditHours);
        File userFile=new File(name+".txt");
        courses.add(c);
        PrintWriter couO = new PrintWriter(new FileWriter(courseF, true));
        PrintWriter userO = new PrintWriter(new FileWriter(userFile, true));
        couO.write(name+"\n");
        userO.write(creditHours+" "+cnoOfQuestions+" "+name+"\n");
        userO.close();
        couO.close();
    }
    public void assignCourse(String courseName,Object user) throws IOException {
        int flag=0;
        Scanner Cfile = new Scanner(courseF);
        Scanner UfileScanner;
        if(user instanceof Student) {
            UfileScanner = new Scanner(new File(((Student) user).getUsername() + ".txt"));
        }
        else {
            UfileScanner = new Scanner(new File(((Instructor) user).getUsername() + ".txt"));
        }
        int i=0;
        while(i<5) {//b skip lines el feha data msh 3ayzha 3ashan ageb el courses el 3and el student
            UfileScanner.nextLine();
            i++;
        }
        if(user instanceof Student)
            UfileScanner.nextLine();
        ArrayList<String> coursesExisted=new ArrayList<String>();
        while(UfileScanner.hasNext()) {//ageb elcourses elly 3nd elstudent
            coursesExisted.add(UfileScanner.nextLine());
        }
        while (Cfile.hasNext()){
            String name1=Cfile.nextLine();//name1 da esm el course
            if(name1.equals(courseName)){
                if(coursesExisted.contains(courseName)){
                    System.out.println("Course Exist");
                    return;
                    }
                if(user instanceof Student) {// bwrite el courses elstudent byakhodha fe student file
                    PrintWriter stuWriter = new PrintWriter(new FileWriter(new File(((Student) user).getUsername() + ".txt"), true));
                    stuWriter.write(courseName+"\n");
                    stuWriter.close();
                }
                else {// bwrite el courses eldr bydeha fe dr file
                    PrintWriter insWriter = new PrintWriter(new FileWriter(new File(((Instructor) user).getUsername() + ".txt"), true));
                    insWriter.write(courseName+"\n");
                    insWriter.close();
                }
                flag=1;
            }
        }
        if(flag==0){
            System.out.println("Enter course name again");
            courseName=sc.nextLine();
            courseName=courseName.toLowerCase();
            assignCourse(courseName,user);
        }
        if(user instanceof Student) {
            PrintWriter couWriter = new PrintWriter(new FileWriter(new File(courseName+".txt"), true));
            couWriter.write((((Student) user).getUsername())+"\n");
            couWriter.close();
        }
        else {
            PrintWriter couWriter = new PrintWriter(new FileWriter(new File(courseName + ".txt"), true));
            couWriter.write((((Instructor) user).getUsername()) + "\n");
            couWriter.close();
        }
        System.out.println("Course added Successfully\n");
    }
    public void createAdmin(String name, int age, String mobilenumber, String emailAddress,String username,String pass) throws IOException {
        Admin A=new Admin(name,age,mobilenumber,emailAddress,username,pass);
        File userF=new File(username+".txt");
        admins.add(A);
        PrintWriter userO = new PrintWriter(new FileWriter(userF, true));
        PrintWriter adminO = new PrintWriter(new FileWriter(adminF, true));
        adminO.write(username+"\n");
        userO.write(username+" "+pass+"\n");
        userO.write(name+"\n"+emailAddress+"\n"+mobilenumber+"\n"+age+"\n");
        userO.close();
        adminO.close();
    }

    public void viewStudents() throws FileNotFoundException {
        Scanner stuScanner=new Scanner(stuF);
        while(stuScanner.hasNext()){
            String username=stuScanner.nextLine();
            File userFile=new File(username+".txt");
            Scanner userFileScanner=new Scanner(userFile);
            userFileScanner.nextLine();
            System.out.println(userFileScanner.nextLine());
        }
        System.out.println();
    }
    @Override
    public void viewCourse() throws FileNotFoundException {
        File coursesFile=new File("course.txt");
        Scanner coursesFileScanner=new Scanner(coursesFile);
        System.out.println();
        while(coursesFileScanner.hasNext()) {
            System.out.println(coursesFileScanner.nextLine());
        }
        System.out.println();
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