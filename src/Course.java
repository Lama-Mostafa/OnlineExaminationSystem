public class Course {
    private String ID;
    private Student[] students;
    private String name;
    private int CnoOfQuestions;//el(20)
    private static int noOfCourses=0;
    private double creditHours;

    public Course(String name, int cnoOfQuestions, double creditHours) {
        ID=String.valueOf(noOfCourses);
        noOfCourses++;
        this.name = name;
        this.CnoOfQuestions=cnoOfQuestions;
        this.creditHours=creditHours;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Student[] getStudents() {
        return students;
    }

    public void setStudents(Student[] students) {
        this.students = students;
    }

    public int getCnoOfQuestions() {
        return CnoOfQuestions;
    }

    public void setCnoOfQuestions(int cnoOfQuestions) {CnoOfQuestions = cnoOfQuestions;}

    public double getCreditHours() { return creditHours;}

}
