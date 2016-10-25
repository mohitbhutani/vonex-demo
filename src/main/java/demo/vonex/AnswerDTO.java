package demo.vonex;

/**
 * Created by mohit bhutani on 25/10/16.
 */
public class AnswerDTO {

    String answer;
    String status;

    public String getAnswer() {
        return answer;
    }

    public AnswerDTO setAnswer(String answer) {
        this.answer = answer;
        return this;
    }

    public String getStatus() {
        return status;
    }

    public AnswerDTO setStatus(String status) {
        this.status = status;
        return this;
    }
}
