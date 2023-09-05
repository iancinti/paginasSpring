package com.example.paginasSpring.model;

public class Category {

    private Integer category;
    private String name;

    public Integer getCategory() {
        return category;
    }

    public void setCategory(Integer category) {
        this.category = category;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Category{" +
                "category=" + category +
                ", name='" + name + '\'' +
                '}';
    }

}
