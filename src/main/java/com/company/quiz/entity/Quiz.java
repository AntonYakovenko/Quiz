package com.company.quiz.entity;

import java.util.List;

public class Quiz {
    private int id;
    private String name;
    private String description;
    private List<Question> questions;
    private List<Theme> themes;

    public Quiz() {}

    public Quiz(int id, String name, String description, List<Question> questions, List<Theme> themes) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.questions = questions;
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

    public List<Question> getQuestions() {
        return questions;
    }

    public void setQuestions(List<Question> questions) {
        this.questions = questions;
    }

    public List<Theme> getThemes() {
        return themes;
    }

    public void setThemes(List<Theme> themes) {
        this.themes = themes;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;

        Quiz that = (Quiz) obj;

        if (id != that.id) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (description != null ? !description.equals(that.description) : that.description != null) return false;
        if (questions != null ? !questions.equals(that.questions) : that.questions != null) return false;
        return themes != null ? themes.equals(that.themes) : that.themes == null;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (questions != null ? questions.hashCode() : 0);
        result = 31 * result + (themes != null ? themes.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Quiz{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", questions=" + questions +
                ", themes=" + themes +
                '}';
    }
}
