package kr.hs.dgsw.quiz.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Question {
    public static final int TYPE_TEXT = 1;
    public static final int TYPE_IMAGE = 2;

    private int id;
    private String question;
    private int score;

    private int answer;
    private String[] choices;

    private int type;

    public Question(String question, int score, int answer, String[] choices, int type) {
        this.question = question;
        this.score = score;
        this.answer = answer;
        this.choices = choices;
        this.type = type;
    }
}
