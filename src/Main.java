import java.io.*;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.*;


public class Main {
    public static void main(String[] a) throws IOException
    {
        System.out.println("   Faculty Examination System\n"+"================================\n\n");
        mainMenu();
    }
    public static void mainMenu() throws IOException {
        Scanner in = new Scanner(System.in);
        //Person p;
        System.out.println("Enter 1 to enter as an Admin\nEnter 2 to enter as an Instructor\nEnter 3 to enter as a Student\nEnter 0 to close program");
        int x = in.nextInt();
        switch (x) {
            case 1 -> admin();
            case 2 -> instructor();
            case 3 -> student();
            case 0 -> System.exit(0);
            default -> {
                System.out.println("INVALID INPUT");
                mainMenu();
            }
        }
    }
    public static void admin() throws IOException {
        Scanner in = new Scanner(System.in);
        File file = new File("admin.txt");
        Scanner infile;
        String username,pass,name, mobileNumber, emailAddress;
        PrintWriter couO;
        String name1, mobileNumber1, emailAddress1, name2,username1,pass1,department;
        int age,age1,qCount,flag=0,x,num,i,rightUsername=0;
        infile = new Scanner(file);
        System.out.println("Enter username and password :");
        username = in.nextLine();
        pass = in.nextLine();
        while (infile.hasNext()) {
            String u=infile.nextLine();
            if (u.equals(username)) {
                rightUsername=1;
                File userFile=new File(username+".txt");
                Scanner userFileScanner = new Scanner(userFile);
                userFileScanner.next();
                String p=userFileScanner.nextLine();
                p=p.replaceAll(" ","");
                if(p.equals(pass)){
                    rightUsername=2;
                    System.out.println("Welcome to Admin panel");
                    name=userFileScanner.nextLine();
                    emailAddress=userFileScanner.nextLine();
                    mobileNumber=userFileScanner.nextLine();
                    age=userFileScanner.nextInt();
                    Admin a1=new Admin(name,age,mobileNumber,emailAddress,username,pass);
                    while (true) {
                        System.out.println("Press 1 to add Student\nPress 2 to add Instructor\nPress 3 to add Course\nPress 4 to add admin\nPress 5 to assign Course to a Student\nPress 6 to assign Instructor to a Course\nEnter 7 to view all courses\nEnter 8 to view students");
                        System.out.println("To logout enter 0");
                        x = in.nextInt();
                        double credit;
                        in.nextLine();
                        switch (x) {
                            case 1 -> {
                                System.out.println("Enter Student name");
                                while(true) {// validation for the input
                                    name1 = in.nextLine();
                                    name1=name1.toLowerCase();
                                    int count=0;
                                    for (int j=0;j<name1.length();j++){
                                        char c=name1.charAt(j);
                                        if(c==' ')
                                            count++;
                                    }
                                    if(count>=2&&count<=5){
                                        break;
                                    }
                                    System.out.println("Enter the name from 3 names to 5");
                                }
                                System.out.println("Enter Student age");
                                while(true) {
                                    age1 = in.nextInt();
                                    if(age1<=30&&age1>=16)
                                        break;
                                    System.out.println("Enter the right age (16->30)");
                                }
                                in.nextLine();
                                System.out.println("Enter Student mobile number");
                                while(true) {
                                    mobileNumber1 = in.nextLine();
                                    int hasChar=0;
                                    if((mobileNumber1.charAt(0)=='0')&&(mobileNumber1.charAt(1)=='1')&&(mobileNumber1.length()==11)) {
                                        for (int j = 0; j < mobileNumber1.length(); j++) {
                                            if (!(Character.isDigit(mobileNumber1.charAt(j)))) {
                                                hasChar = 1;
                                                break;
                                            }
                                        }
                                    }
                                    else{
                                        System.out.println("Enter a correct number");
                                        continue;
                                    }
                                    if(hasChar==0)
                                        break;
                                    System.out.println("Enter a correct number");
                                }
                                System.out.println("Enter Student e-mail address");
                                while(true) {
                                    emailAddress1 = in.nextLine();
                                    if(emailAddress1.contains("@")&&emailAddress1.contains("."))
                                        break;
                                    System.out.println("Enter valid email");
                                }
                                System.out.println("Enter Student Department(IS,CS,SE,AI)");
                                while (true) {
                                    department = in.nextLine();
                                    department = department.toLowerCase();
                                    if((!department.equals("is"))&&(!department.equals("cs"))&&(!department.equals("ai"))&&(!department.equals("se"))){
                                        System.out.println("Enter valid department");
                                        continue;
                                    }
                                    break;
                                }
                                Department d;
                                if(department.equals("is"))
                                    d=Department.IS;
                                else if(department.equals("cs"))
                                    d=Department.CS;
                                else if (department.equals("ai"))
                                    d=Department.AI;
                                else
                                    d=Department.SE;
                                System.out.println("Enter Student username");
                                username1 = in.nextLine();
                                System.out.println("Enter Student password");
                                pass1 = in.nextLine();
                                Student s=a1.createStudent(name1, age1, mobileNumber1, emailAddress1, username1, pass1,d);
                                System.out.println("Enter number of courses the student takes");
                                num = in.nextInt();
                                Scanner sc = new Scanner(System.in);
                                i = 0;
                                while (i < num) {//write esm el course name fe file el student(el file e b esm elusername bta3 el student)
                                    System.out.println("Enter course number " + (i + 1) + " name:");
                                    name2 = sc.nextLine();
                                    name2=name2.toLowerCase();
                                    a1.assignCourse(name2, s);
                                    i++;
                                }
                            }
                            case 2 -> {
                                System.out.println("Enter Instructor name");
                                while(true) {
                                    name1 = in.nextLine();
                                    name1=name1.toLowerCase();
                                    int count=0;
                                    for (int j=0;j<name1.length();j++)
                                    {
                                        char c=name1.charAt(j);
                                        if(c==' ')
                                            count++;
                                    }
                                    if(count>=2 && count<=5)
                                        break;
                                    System.out.println("Enter the name from 3 names to 5");
                                }
                                System.out.println("Enter Instructor age");
                                while(true) {
                                    age1 = in.nextInt();
                                    if(age1<=60&&age1>=20)
                                        break;
                                    System.out.println("Enter the right age (20->60)");
                                }
                                in.nextLine();
                                System.out.println("Enter Instructor mobile number");
                                while(true) {
                                    mobileNumber1 = in.nextLine();
                                    int hasChar=0;
                                    if((mobileNumber1.charAt(0)=='0')&&(mobileNumber1.charAt(1)=='1')&&(mobileNumber1.length()==11)) {
                                        for (int j = 0; j < mobileNumber1.length(); j++) {
                                            if (!(Character.isDigit(mobileNumber1.charAt(j)))) {
                                                hasChar = 1;
                                                break;
                                            }
                                        }
                                    }
                                    else{
                                        System.out.println("Enter a correct number");
                                        continue;
                                    }
                                    if(hasChar==0)
                                        break;
                                    System.out.println("Enter a correct number");
                                }
                                System.out.println("Enter Instructor e-mail address");
                                while(true) {
                                    emailAddress1 = in.nextLine();
                                    if(emailAddress1.contains("@")&&emailAddress1.contains("."))
                                        break;
                                    System.out.println("Enter valid email");
                                }
                                System.out.println("Enter Instructor username");
                                username1 = in.nextLine();
                                System.out.println("Enter Instructor password");
                                pass1 = in.nextLine();
                                Instructor ins=a1.createInstructor(name1, age1, mobileNumber1, emailAddress1, username1, pass1);
                                System.out.println("Enter number of courses the Instructor teaches");
                                num = in.nextInt();
                                in.nextLine();
                                i = 0;
                                while (i < num)
                                {// write the course el dr bydeha fe el file bta3 el dr
                                    System.out.println("Enter course number " + (i + 1) + " name:");
                                    name2 = in.nextLine();
                                    name2 = name2.toLowerCase();
                                    a1.assignCourse(name2, ins);
                                    i++;
                                }
                            }
                            case 3 -> {
                                System.out.println("Enter Course name");
                                name1 = in.nextLine();
                                name1=name1.toLowerCase();
                                System.out.println("Enter Course number of final questions");
                                while(true) {
                                    qCount = in.nextInt();
                                    if(qCount>=10&&qCount<=30)
                                        break;
                                    System.out.println("Enter valid number of questions (10->30)");
                                }
                                System.out.println("Enter Course credit hours");
                                while (true) {
                                    credit = in.nextDouble();
                                    if(credit<5&&credit>=0)
                                        break;
                                    System.out.println("Enter valid credit hours (0->5)");
                                }
                                a1.createCourse(name1, qCount, credit);
                            }
                            case 4 ->
                            {
                                System.out.println("Enter the Admin's name");
                                while(true) {
                                    name1 = in.nextLine();
                                    name1=name1.toLowerCase();
                                    int count=0;
                                    for (int j=0;j<name1.length();j++){
                                        char c=name1.charAt(j);
                                        if(c==' ')
                                            count++;
                                    }
                                    if(count>=2&&count<=5){
                                        break;
                                    }
                                    System.out.println("Enter the name from 3 names to 5");
                                }
                                System.out.println("Enter Admin's age");
                                while(true) {
                                    age1 = in.nextInt();
                                    if(age1<=60&&age1>=25)
                                        break;
                                    System.out.println("Enter the right age (25->60)");
                                }
                                in.nextLine();
                                System.out.println("Enter Admin's mobile number");
                                while(true) {
                                    mobileNumber1 = in.nextLine();
                                    int hasChar=0;
                                    if((mobileNumber1.charAt(0)=='0')&&(mobileNumber1.charAt(1)=='1')&&(mobileNumber1.length()==11)) {
                                        for (int j = 0; j < mobileNumber1.length(); j++) {
                                            if (!(Character.isDigit(mobileNumber1.charAt(j)))) {
                                                hasChar = 1;
                                                break;
                                            }
                                        }
                                    }
                                    else{
                                        System.out.println("Enter a correct number");
                                        continue;
                                    }
                                    if(hasChar==0)
                                        break;
                                    System.out.println("Enter a correct number");
                                }
                                System.out.println("Enter Admin's e-mail address");
                                while(true) {
                                    emailAddress1 = in.nextLine();
                                    if(emailAddress1.contains("@")&&emailAddress1.contains("."))
                                        break;
                                    System.out.println("Enter valid email");
                                }
                                System.out.println("Enter Admin's username");
                                username1 = in.nextLine();
                                System.out.println("Enter Admin's password");
                                pass1 = in.nextLine();
                                a1.createAdmin(name1, age1, mobileNumber1, emailAddress1, username1, pass1);
                            }
                            case 5 -> {
                                System.out.println("Enter Course name");
                                name1 = in.nextLine();
                                name1 = name1.toLowerCase();
                                System.out.println("Enter Student username");
                                username1 = in.nextLine();
                                File stuUserFile=new File(username1+".txt");
                                Scanner stuUserFileScanner=new Scanner(stuUserFile);
                                stuUserFileScanner.next();
                                pass1=stuUserFileScanner.nextLine();
                                pass1=pass1.replaceAll(" ","");
                                name2=stuUserFileScanner.nextLine();
                                emailAddress1=stuUserFileScanner.nextLine();
                                mobileNumber1=stuUserFileScanner.nextLine();
                                age1=stuUserFileScanner.nextInt();
                                stuUserFileScanner.nextLine();
                                department=stuUserFileScanner.nextLine();
                                Department d;
                                if(department.equals("is"))
                                    d=Department.IS;
                                else if(department.equals("cs"))
                                    d=Department.CS;
                                else if (department.equals("ai"))
                                    d=Department.AI;
                                else
                                    d=Department.SE;
                                Person stu=new Student(name2,age1,mobileNumber1,emailAddress1,username1,pass1,d);
                                a1.assignCourse(name1, stu);
                            }
                            case 6 ->
                            {
                                System.out.println("Enter Course name");
                                name1 = in.nextLine();
                                name1=name1.toLowerCase();
                                System.out.println("Enter Instructor username");
                                username1 = in.nextLine();
                                File insUserFile=new File(username1+".txt");
                                Scanner insUserFileScanner=new Scanner(insUserFile);
                                insUserFileScanner.next();
                                pass1=insUserFileScanner.nextLine();
                                pass1=pass1.replaceAll(" ","");
                                name2=insUserFileScanner.nextLine();
                                emailAddress1=insUserFileScanner.nextLine();
                                mobileNumber1=insUserFileScanner.nextLine();
                                age1=insUserFileScanner.nextInt();
                                insUserFileScanner.nextLine();
                                Person Ins=new Instructor(name2,age1,mobileNumber1,emailAddress1,username1,pass1);
                                a1.assignCourse(name1, Ins);
                            }
                            case 7 -> a1.viewCourse();
                            case 8 -> a1.viewStudents();
                            case 0 -> {
                                flag = 1;
                                mainMenu();
                            }
                        }
                        if (flag==1) break;
                    }
                }
            }
        }
        if(rightUsername==1||rightUsername==0){
            System.out.println("Wrong username or password");
            admin();
        }
    }



    public static void instructor() throws IOException {
        File instructorFile=new File("instructor.txt");
        Scanner fileIn=new Scanner(instructorFile);

        Scanner in=new Scanner(System.in);

        String username,pass,usernameFile,passwordFile,name,emailAddress,mobileNumber;
        int age,flag=0,rightUsername=0;

        System.out.println("Enter username and password :");
        username = in.nextLine();
        pass = in.nextLine();

        while(fileIn.hasNext())
        {
            usernameFile=fileIn.nextLine();
            if(usernameFile.equals(username))
            {
                rightUsername=1;
                File userFile=new File(usernameFile+".txt");
                Scanner userFileScanner=new Scanner(userFile);
                userFileScanner.next();
                passwordFile=userFileScanner.nextLine();
                passwordFile=passwordFile.replaceAll(" ","");

                if(passwordFile.equals(pass))
                {
                    rightUsername=2;
                    System.out.println("Welcome to Instructor panel");

                    name=userFileScanner.nextLine();
                    emailAddress=userFileScanner.nextLine();
                    mobileNumber=userFileScanner.nextLine();
                    age=userFileScanner.nextInt();

                    Person i=new Instructor(name,age,mobileNumber,emailAddress,username,pass);

                    while (true)
                    {
                        System.out.println("Enter 1 to add Exam\nEnter 2 to view courses\nEnter 3 to view students");
                        System.out.println("Enter 0 to logout");
                        int x=in.nextInt();
                        in.nextLine();
                        switch (x) {
                            case 1 -> {
                                System.out.println("Enter course name:");
                                String courseName = in.nextLine();
                                courseName = courseName.toLowerCase();
                                userFileScanner.nextLine();
                                while (userFileScanner.hasNext()) {
                                    String courseName1 = userFileScanner.nextLine();
                                    courseName1 = courseName1.toLowerCase();
                                    if (courseName1.equals(courseName)) {
                                        int year,month,day;
                                        File courseFile = new File(courseName + ".txt");
                                        Scanner courseFileScanner = new Scanner(courseFile);
                                        int cNoOfQuestions = courseFileScanner.nextInt();
                                        System.out.println("Enter number of questions you're willing to enter");
                                        int instructorNoOfQuestions;
                                        while(true) {
                                            instructorNoOfQuestions = in.nextInt();
                                            if(instructorNoOfQuestions<cNoOfQuestions) {
                                                System.out.println("Please enter more or equal than "+cNoOfQuestions);
                                                continue;
                                            }
                                            break;
                                        }
                                        in.nextLine();
                                        double creditHours = courseFileScanner.nextDouble();
                                        Course examCourse = new Course(courseName, cNoOfQuestions, creditHours);
                                        courseFileScanner.close();
                                        System.out.println("Enter exam date");
                                        System.out.println("Enter year:");
                                        while(true) {
                                            year = in.nextInt();
                                            if(year==2022||year==2023)
                                                break;
                                            System.out.println("Enter right year");
                                        }

                                        System.out.println("Enter month:");
                                        while (true) {
                                            month = in.nextInt();
                                            if(month<=12&&month>=1)
                                                break;
                                            System.out.println("Enter a valid month");
                                        }

                                        System.out.println("Enter day:");
                                        while (true) {
                                            day = in.nextInt();
                                            if(day<=31&&day>=1)
                                                break;
                                            System.out.println("Enter a valid day");
                                        }

                                        System.out.println("Select session of the next:\n1-From 9 to 11\n2-From 11 to 13\n3-From 13 to 15\nEnter 4 to enter other time");
                                        int respond = in.nextInt();
                                        int hour = 0, min = 0;
                                        switch (respond) {
                                            case 1 -> hour = 9;
                                            case 2 -> hour = 11;
                                            case 3 -> hour = 1;
                                            case 4 -> {
                                                System.out.println("MAKE SURE TO ENTER IN 24 HOUR CLOCK");
                                                System.out.println("Enter hour:");
                                                while (true) {
                                                    hour = in.nextInt();
                                                    if(hour>=9&&hour<=16)
                                                        break;
                                                    System.out.println("Enter time between 9 and 16");
                                                }
                                                System.out.println("Enter minutes:");
                                                while(true) {
                                                    min = in.nextInt();
                                                    if(min>=0&&min<=59)
                                                        break;
                                                    System.out.println("Enter valid minutes");
                                                }
                                            }
                                        }
                                        int count = 0;
                                        Exam e = new Exam(examCourse, (Instructor) i, instructorNoOfQuestions, year, month, day, hour, min);
                                        ArrayList<Question> questions = new ArrayList<Question>();
                                        in.nextLine();
                                        while (count < instructorNoOfQuestions) {
                                            System.out.println("Enter question number " + (count + 1) + " :");
                                            String question = in.nextLine();
                                            question = question.toLowerCase();
                                            char questionLetter = 'a';
                                            int charCount = 0;
                                            ArrayList<String> answers = new ArrayList<String>();
                                            while (charCount < 4) {
                                                System.out.println("Enter choice " + (questionLetter++) + " :");
                                                answers.add(in.nextLine());
                                                charCount++;
                                            }
                                            System.out.println("Enter the correct answer character :");
                                            char correctAnswer = in.next().charAt(0);
                                            in.nextLine();
                                            Question q = new Question(question, correctAnswer, e, answers);
                                            questions.add(q);
                                            count++;
                                        }
                                        e.setQuestions(questions);
                                        e.writeData();
                                        break;
                                    }
                                }
                            }
                            case 2 -> i.viewCourse();
                            case 3 -> ((Instructor) i).viewInstructorStudents();
                            case 0 -> {
                                flag = 1;
                                mainMenu();
                            }
                        }
                        if(flag==1) break;
                    }
                }
            }
        }
        if(rightUsername==0||rightUsername==1) {
            System.out.println("Wrong username or password");
            instructor();
        }
    }


    public static void student() throws IOException {
        File studentFile=new File("student.txt");
        Scanner fileIn=new Scanner(studentFile);
        Scanner in=new Scanner(System.in);
        String username,pass,usernameFile,passwordFile,name,emailAddress,mobileNumber;
        int age,flag=0,rightUsername=0;
        System.out.println("Enter username and password :");
        username = in.nextLine();
        pass = in.nextLine();
        while(fileIn.hasNext()){
            usernameFile=fileIn.nextLine();
            if(usernameFile.equals(username))
            {
                rightUsername=1;
                File userFile=new File(usernameFile+".txt");
                Scanner userFileScanner=new Scanner(userFile);
                userFileScanner.next();
                passwordFile=userFileScanner.nextLine();
                passwordFile=passwordFile.replaceAll(" ","");
                if(passwordFile.equals(pass)){
                    rightUsername=2;
                    System.out.println("Welcome to Student panel");
                    name=userFileScanner.nextLine();
                    emailAddress=userFileScanner.nextLine();
                    mobileNumber=userFileScanner.nextLine();
                    age=userFileScanner.nextInt();
                    userFileScanner.nextLine();
                    String department=userFileScanner.nextLine();
                    Department dep;
                    if(department.equals("is"))
                        dep=Department.IS;
                    else if(department.equals("cs"))
                        dep=Department.CS;
                    else if (department.equals("ai"))
                        dep=Department.AI;
                    else
                        dep=Department.SE;
                    Person s=new Student(name,age,mobileNumber,emailAddress,username,pass,dep);
                    while (true){
                        System.out.println("Enter 1 to view exams\nEnter 2 to view courses\nEnter 3 to view grades");//view all grades
                        System.out.println("Enter 0 to logout");
                        int x=in.nextInt();
                        switch (x){
                            case 1:
                                File examsFile=new File("exams.txt");
                                Scanner examFileScanner=new Scanner(examsFile);
                                ArrayList<String>userCourses=new ArrayList<String>();
                                while(userFileScanner.hasNext()) {
                                    userCourses.add(userFileScanner.nextLine());
                                }
                                ArrayList<String>coursesExams=new ArrayList<String>();
                                while(examFileScanner.hasNext()){
                                    int f=0;
                                    String courseName=examFileScanner.nextLine();
                                    if(userCourses.contains(courseName)){
                                        File examCourseFile=new File(courseName+"_exam.txt");
                                        Scanner examCourseFileScanner=new Scanner(examCourseFile);
                                        System.out.println(courseName+"\n"+"Dr. "+examFileScanner.nextLine()+"\n");
                                        examCourseFileScanner.nextLine();
                                        examCourseFileScanner.nextLine();
                                        examCourseFileScanner.nextInt();
                                        System.out.println("Date : "+examCourseFileScanner.next()+"\n"+"Time: "+examCourseFileScanner.next()+"\n");
                                        examCourseFileScanner.nextLine();
                                        coursesExams.add(courseName);
                                        f=1;
                                    }
                                    if(f==0)
                                        examFileScanner.nextLine();
                                }
                                System.out.println("Enter course name that you want to enter it's exam :");
                                in.nextLine();
                                String courseChosen=in.nextLine();
                                courseChosen=courseChosen.toLowerCase();
                                if(coursesExams.contains(courseChosen)){
                                    File takingExamFile=new File(courseChosen+"_exam.txt");
                                    File courseFile=new File(courseChosen+".txt");
                                    Scanner courseFileScanner=new Scanner(courseFile);
                                    int courseNumOfQuestions=courseFileScanner.nextInt();
                                    double courseCreditHours=courseFileScanner.nextDouble();
                                    Course examCourse=new Course(courseChosen,courseNumOfQuestions,courseCreditHours);
                                    Scanner takingExamFileScanner=new Scanner(takingExamFile);
                                    SimpleDateFormat dateFormatter=new SimpleDateFormat("yyyy/M/d");
                                    SimpleDateFormat timeFormatter=new SimpleDateFormat("H:m");
                                    Date currentDate=new Date();
                                    String currentDateFormatted=dateFormatter.format(currentDate);
                                    String currentTimeFormatted=timeFormatter.format(currentDate);
                                    takingExamFileScanner.nextLine();
                                    String insUsername=takingExamFileScanner.nextLine();
                                    int instructorNoOfQuestions=takingExamFileScanner.nextInt();
                                    String examDate=takingExamFileScanner.next();
                                    String examDateInYearMonthDay[]=examDate.split("/");
                                    int year=Integer.parseInt(examDateInYearMonthDay[0]);
                                    int month=Integer.parseInt(examDateInYearMonthDay[1]);
                                    int day=Integer.parseInt(examDateInYearMonthDay[2]);
                                    String userDateInYearMonthDay[]=currentDateFormatted.split("/");
                                    int userYear=Integer.parseInt(userDateInYearMonthDay[0]);
                                    int userMonth=Integer.parseInt(userDateInYearMonthDay[1]);
                                    int userDay=Integer.parseInt(userDateInYearMonthDay[2]);
                                    String examTime=takingExamFileScanner.next();
                                    String examTimeInHourAndMinute[]=examTime.split(":");
                                    String examHour=examTimeInHourAndMinute[0];
                                    String examMinute=examTimeInHourAndMinute[1];
                                    int examHourInt=Integer.parseInt(examHour);
                                    int examMinuteInt=Integer.parseInt(examMinute);
                                    String userTimeInHourAndMinute[]=currentTimeFormatted.split(":");
                                    String userHour=userTimeInHourAndMinute[0];
                                    String userMinute=userTimeInHourAndMinute[1];
                                    int userHourInt=Integer.parseInt(userHour);
                                    int userMinuteInt=Integer.parseInt(userMinute);
                                    int courseNumCount=0;
                                    ArrayList<Integer> numbersChosen=new ArrayList<Integer>();
                                    takingExamFileScanner.nextLine();
                                    while(courseNumCount<courseNumOfQuestions){
                                        Integer num=(int)((Math.random()*(instructorNoOfQuestions)));
                                        if(numbersChosen.contains(num))
                                            continue;
                                        numbersChosen.add(num);
                                        courseNumCount++;
                                    }
                                    ArrayList<Question> questions=new ArrayList<Question>();
                                    File insFile=new File(insUsername+".txt");
                                    Scanner insFileScanner=new Scanner(insFile);
                                    insFileScanner.next();
                                    String insPass=insFileScanner.next();
                                    insFileScanner.nextLine();
                                    String insName=insFileScanner.nextLine();
                                    insName= insName.toLowerCase();
                                    String insEmail=insFileScanner.nextLine();
                                    String insNumber=insFileScanner.nextLine();
                                    int insAge=insFileScanner.nextInt();
                                    Instructor ins=new Instructor(insName,insAge,insNumber,insEmail,insUsername,insPass);
                                    Exam e=new Exam(examCourse,ins,instructorNoOfQuestions,year,month,day,examHourInt,examMinuteInt);
                                    while(takingExamFileScanner.hasNext()){
                                        String q=takingExamFileScanner.nextLine();
                                        String a=takingExamFileScanner.nextLine();
                                        String b=takingExamFileScanner.nextLine();
                                        String c=takingExamFileScanner.nextLine();
                                        String d=takingExamFileScanner.nextLine();
                                        ArrayList<String> ans=new ArrayList<>();
                                        ans.add(a);
                                        ans.add(b);
                                        ans.add(c);
                                        ans.add(d);
                                        char correctAns=takingExamFileScanner.next().charAt(0);
                                        takingExamFileScanner.nextLine();
                                        Question questionObject=new Question(q,correctAns,e,ans);
                                        questions.add(questionObject);
                                    }
                                    int enteredExam=0;
                                    int mark=0;
                                    int count=0;
                                    int timeIsUP=0;
                                    if((userYear==year)&&(userMonth==month)&&(userDay==day)) {
                                        while (true) {
                                            if ((userHourInt == examHourInt) || (userHourInt == (examHourInt + 1)) || (userHourInt == (examHourInt + 2))) {
                                                while (true) {
                                                    if ((userMinuteInt < examMinuteInt) && (userHourInt == examHourInt))
                                                        break;
                                                    if ((userHourInt == (examHourInt + 2)) && (userMinuteInt > examMinuteInt)) {
                                                        timeIsUP=1;
                                                        break;
                                                    }
                                                    enteredExam = 1;
                                                    Question question = questions.get(numbersChosen.get(count));
                                                    System.out.println(question.getQ());
                                                    int i = 0;
//                                                ArrayList<Integer> answersDisplayed=new ArrayList<Integer>();
                                                    char displayedChar = 'a';
                                                    while (i < 4) {
//                                                    Integer num=(int)(Math.random()*4);
//                                                    if(answersDisplayed.contains(num))
//                                                        continue;
//                                                    answersDisplayed.add(num);
                                                        System.out.println(displayedChar + " - " + question.getAnswers().get(i));
                                                        displayedChar++;
                                                        i++;
                                                    }
                                                    char ans;
                                                    while(true) {
                                                        ans = in.next().toLowerCase().charAt(0);
                                                        if((ans=='a')||(ans=='b')||(ans=='c')||(ans=='d'))
                                                            break;
                                                        System.out.println("Enter a correct letter (a-b-c-d)");
                                                    }
                                                    if (ans == question.getCorrectAnswer()) {
                                                        mark++;
                                                    }
                                                    if (count == (courseNumOfQuestions - 1))
                                                        break;
                                                    count++;
                                                    currentDate = new Date();
                                                    currentTimeFormatted=timeFormatter.format(currentDate);
                                                    String currentTimeInHourAndMinute[]=currentTimeFormatted.split(":");
                                                    userHour=currentTimeInHourAndMinute[0];
                                                    userMinute=currentTimeInHourAndMinute[1];
                                                    userHourInt=Integer.parseInt(userHour);
                                                    userMinuteInt=Integer.parseInt(userMinute);
                                                }
                                                in.nextLine();
                                            }
                                            else{
                                                System.out.println("Exam time exceeded or exam time hasn't come yet\n");
                                                break;
                                            }
                                            if (enteredExam == 1) {
                                                if(timeIsUP==1)
                                                    System.out.println("TIME IS UP");
                                                System.out.println("Marks = " + mark);
                                                double percentage=(mark/(double)courseNumCount)*100.0;
                                                DecimalFormat df = new DecimalFormat("0.00");
                                                percentage= Double.parseDouble(df.format(percentage));
                                                System.out.println("Percentage = "+percentage);
                                                File stuToCourse=new File(username+"_"+courseChosen+".txt");
                                                PrintWriter stuToCourseWriter = new PrintWriter(new FileWriter(stuToCourse, true));
                                                stuToCourseWriter.write(Double.toString(percentage));
                                                stuToCourseWriter.write("\n");
                                                stuToCourseWriter.close();
                                                break;
                                            }
                                        }
                                    }
                                }
                                else
                                    System.out.println("Wrong input or no exam assigned to the course you entered\n");
                                break;
                            case 2:
                                s.viewCourse();
                                break;
                            case 3:
                                ((Student) s).viewGrades();
                                break;
                            case 0:
                                flag=1;
                                mainMenu();
                                break;
                        }
                        if(flag==1) break;
                    }
                }
            }
        }
        if(rightUsername==0||rightUsername==1){
            System.out.println("Wrong username or password");
            student();
        }
    }
}