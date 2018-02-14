package com.company.quiz.entity;

import java.util.List;

public class Theme {
    private final int id;
    private String name;
    private Theme parent;
    private List<Theme> children;

    public Theme(int id, String name, Theme parent, List<Theme> children) {
        this.id = id;
        this.name = name;
        this.parent = parent;
        this.children = children;
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

    public Theme getParent() {
        return parent;
    }

    public void setParent(Theme parent) {
        this.parent = parent;
    }

    public List<Theme> getChildren() {
        return children;
    }

    public void setChildren(List<Theme> children) {
        this.children = children;
    }

    @Override
    public String toString() {
        return "Theme{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", parent=" + parent +
                ", children=" + children +
                '}';
    }
}
