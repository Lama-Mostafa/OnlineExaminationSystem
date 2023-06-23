import java.util.ArrayList;

public class Question {
    private String q;
    private ArrayList<String> answers;
    private char correctAnswer;
    private Exam e;

    public Question(String q, char correctAnswer, Exam e, ArrayList<String>answers) {
        this.correctAnswer=correctAnswer;
        this.q = q;
        this.e=e;
        this.answers=answers;
    }

    public ArrayList<String> getAnswers() {return answers;}

    public void setAnswers(ArrayList<String> answers) {this.answers = answers;}

    public Exam getE() {return e;}

    public void setE(Exam e) {this.e = e;}

    public String getQ() { return q; }

    public void setQ(String q) {
        this.q = q;
    }

    public char getCorrectAnswer() {
        return correctAnswer;
    }

    public void setCorrectAnswer(char correctAnswer) {
        this.correctAnswer = correctAnswer;
    }
}
