package demo.vonex.domain;

/**
 * Created by mohit bhutani on 25/10/16.
 */
public class Token {
    String answer;
    String error;

    public String getAnswer() {
        return answer;
    }

    public Token setAnswer(String answer) {
        this.answer = answer;
        return this;
    }

    public String getError() {
        return error;
    }

    public Token setError(String error) {
        this.error = error;
        return this;
    }

    @Override
    public String toString() {
        return "Token{" +
                "answer='" + answer + '\'' +
                ", error='" + error + '\'' +
                '}';
    }
}
