import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class Exam {
    private Course examCourse;
    private Instructor ins;
    private ArrayList<Question> questions;
    private int numberOfQuestions;
    private int startTimeHour;
    private int startTimeMinute;
    private int year;
    private int month;
    private int day;
    ArrayList<Course> courses = new ArrayList<Course>();
    ArrayList<Student> students = new ArrayList<Student>();
    ArrayList<Instructor> instructors = new ArrayList<Instructor>();

    public Exam(Course examCourse, Instructor ins, int numOfQuestions, int year, int month, int day, int startTimeHour, int startTimeMinute) {
        this.examCourse = examCourse;
        this.ins = ins;
        this.numberOfQuestions=numOfQuestions;
        this.startTimeHour=startTimeHour;
        this.startTimeMinute=startTimeMinute;
        this.year=year;
        this.month=month;
        this.day=day;
    }

    public void writeData() throws IOException {
        File examsF=new File("exams.txt");
        PrintWriter examsO = new PrintWriter(new FileWriter(examsF, true));
        examsO.write(examCourse.getName()+"\n"+ins.getName()+"\n");
        examsO.close();
        File currentexamF=new File(examCourse.getName()+"_exam.txt");
        PrintWriter currentexamO=new PrintWriter(new FileWriter(currentexamF,true));
        currentexamO.write(examCourse.getName()+"\n"+ins.getUsername()+"\n"+numberOfQuestions+" "+year+"/"+month+"/"+day+" "+startTimeHour+":"+startTimeMinute+"\n");
        int count=0;
        while(count<questions.size()){
            currentexamO.write(questions.get(count).getQ()+"\n");
            int count2=0;
            while(count2<questions.get(count).getAnswers().size()) {
                currentexamO.write(questions.get(count).getAnswers().get(count2) + "\n");
                count2++;
            }
            currentexamO.write(questions.get(count).getCorrectAnswer()+"\n");
            count++;
        }
        currentexamO.close();
    }

    public Course getExamCourse() {
        return examCourse;
    }

    public void setExamCourse(Course examCourse) {
        this.examCourse = examCourse;
    }


    public Instructor getIns() {
        return ins;
    }

    public void setIns(Instructor ins) {
        this.ins = ins;
    }

    public ArrayList<Question> getQuestions() {return questions;}

    public void setQuestions(ArrayList<Question> questions) {
        this.questions = questions;
    }

    public int getNumberOfQuestions() {
        return numberOfQuestions;
    }

    public void setNumberOfQuestions(int numberOfQuestions) {
        this.numberOfQuestions = numberOfQuestions;
    }

}