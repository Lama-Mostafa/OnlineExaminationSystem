import java.io.File;
import java.util.Scanner;

public abstract class Person implements Review{

    protected String name;
    protected int age;
    protected String mobilenumber;
    protected String emailAddress;
    protected String username;
    protected String pass;

    File stuF=new File("student.txt");
    File insF=new File("instructor.txt");
    File courseF=new File("course.txt");
    File adminF=new File("admin.txt");

    public Person(String name, int age, String mobilenumber, String emailAddress, String username, String pass) {
        this.name = name;
        this.age = age;
        this.mobilenumber = mobilenumber;
        this.emailAddress = emailAddress;
        this.username = username;
        this.pass = pass;
    }

    public abstract String getUsername();

    public abstract String getName() ;

    public abstract void setName(String name);

    public abstract int getAge();

    public abstract void setAge(int age) ;

    public abstract String getMobilenumber() ;

    public abstract void setMobilenumber(String mobilenumber);

    public abstract String getEmailAddress();

    public abstract void setEmailAddress(String emailAddress);
    public abstract void skipLines(Scanner userO);

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPass() {
        return pass;
    }
}
