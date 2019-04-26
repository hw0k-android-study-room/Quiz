package kr.hs.dgsw.quiz.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Question {
    private int id;
    private String question;
    private int score;

    private int answer;
    private String[] choices;

    public Question(String question, int score, int answer, String[] choices) {
        this.question = question;
        this.score = score;
        this.answer = answer;
        this.choices = choices;
    }
}
