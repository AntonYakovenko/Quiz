package com.company.quiz.entity;

public class Answer {
    private int id;
    private String answer;
    private boolean correct;
    private String explanation;

    public Answer() {}

    public Answer(int id, String answer, boolean correct, String explanation) {
        this.id = id;
        this.answer = answer;
        this.correct = correct;
        this.explanation = explanation;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public boolean isCorrect() {
        return correct;
    }

    public void setCorrect(boolean correct) {
        this.correct = correct;
    }

    public String getExplanation() {
        return explanation;
    }

    public void setExplanation(String explanation) {
        this.explanation = explanation;
    }

    @Override
    public String toString() {
        return "Answer{" +
                "id=" + id +
                ", answer='" + answer + '\'' +
                ", correct=" + correct +
                ", explanation='" + explanation + '\'' +
                '}';
    }
}
