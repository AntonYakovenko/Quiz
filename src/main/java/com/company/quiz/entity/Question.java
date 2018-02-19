package com.company.quiz.entity;

import java.util.List;

public class Question {
    private int id;
    private String name;
    private String description;
    private List<Answer> answers;
    private String explanation;
    private List<Theme> themes; // todo

    public Question() {}

    public Question(int id, String name, String description, List<Answer> answers, String explanation, List<Theme> themes) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.answers = answers;
        this.explanation = explanation;
        this.themes = themes;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
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
                ", themes=" + themes +
                '}';
    }
}
