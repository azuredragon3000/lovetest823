package com.myapp.lovetest_azuredragon3000.quiz.object;

public class Category {

    private  int id;
    private String name;

    public Category(){
    }

    public Category(String name) {
        this.name = name;
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

    @Override
    public String toString() {
        return getName();
    }
}
