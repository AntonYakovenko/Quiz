package com.company.quiz.entity;

import java.util.List;

public class Question {
    private int id;
    private int name;
    private String description;
    private List<Answer> answers;
    private String explanation;
    private int quizId;

    private List<Theme> themes; // todo

    public Question() {}

    public Question(int id, int quizId) {
        this.id = id;
    }

    public Question(int id, int name, String description, List<Answer> answers, String explanation, List<Theme> themes, int quizId) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.answers = answers;
        this.explanation = explanation;
        this.themes = themes;
        this.quizId = quizId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getName() {
        return name;
    }

    public void setName(int name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Answer> getAnswers() {
        return answers;
    }

    public void setAnswers(List<Answer> answers) {
        this.answers = answers;
    }

    public String getExplanation() {
        return explanation;
    }

    public void setExplanation(String explanation) {
        this.explanation = explanation;
    }

    public int getQuizId() {
        return quizId;
    }

    public void setQuizId(int quizId) {
        this.quizId = quizId;
    }

    public List<Theme> getThemes() {
        return themes;
    }

    public void setThemes(List<Theme> themes) {
        this.themes = themes;
    }

    @Override
    public String toString() {
        return "Question{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", answers=" + answers +
                ", explanation='" + explanation + '\'' +
                ", quizId=" + quizId +
                ", themes=" + themes +
                '}';
    }
}
