package com.company.quiz.entity;

import java.util.ArrayList;
import java.util.List;

public class Question {
    private final int id;
    private String name;
    private String intro;
    private List<Answer> answers = new ArrayList<>();
    private String explanation;
    private List<Theme> themes = new ArrayList<>(); // todo

    public Question(int id, String name, String intro, List<Answer> answers, String explanation, List<Theme> themes) {
        this.id = id;
        this.name = name;
        this.intro = intro;
        this.answers = answers;
        this.explanation = explanation;
        this.themes = themes;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIntro() {
        return intro;
    }

    public void setIntro(String intro) {
        this.intro = intro;
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
                ", intro='" + intro + '\'' +
                ", answers=" + answers +
                ", explanation='" + explanation + '\'' +
                ", themes=" + themes +
                '}';
    }
}
