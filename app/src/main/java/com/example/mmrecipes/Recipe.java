package com.example.mmrecipes;

import java.util.List;

public class Recipe {

    int id;
    String title;
    String description;
    String category;
    byte[] ingredients;
    byte[] quantities;
    byte[] steps;
    boolean vegan;
    boolean vegetarian;
    boolean gluten;
    boolean diary;
    boolean soy;
    boolean alcohol;
    boolean sesame;
    boolean seafood;
    boolean wheat;
    boolean celery;
    boolean nuts;
    boolean peanuts;
    boolean eggs;
    boolean pork;
    int prepTime;
    int cookTime;
    int serving;
    String skillLevel;
    String image;

    public Recipe() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public byte[] getIngredients() {
        return ingredients;
    }

    public void setIngredients(byte[] ingredients) {
        this.ingredients = ingredients;
    }

    public byte[] getQuantities() {
        return quantities;
    }

    public void setQuantities(byte[] quantities) {
        this.quantities = quantities;
    }

    public byte[] getSteps() {
        return steps;
    }

    public void setSteps(byte[] steps) {
        this.steps = steps;
    }

    public boolean isVegan() {
        return vegan;
    }

    public void setVegan(boolean vegan) {
        this.vegan = vegan;
    }

    public boolean isVegetarian() {
        return vegetarian;
    }

    public void setVegetarian(boolean vegetarian) {
        this.vegetarian = vegetarian;
    }

    public boolean isGluten() {
        return gluten;
    }

    public void setGluten(boolean gluten) {
        this.gluten = gluten;
    }

    public boolean isDiary() {
        return diary;
    }

    public void setDiary(boolean diary) {
        this.diary = diary;
    }

    public boolean isSoy() {
        return soy;
    }

    public void setSoy(boolean soy) {
        this.soy = soy;
    }

    public boolean isAlcohol() {
        return alcohol;
    }

    public void setAlcohol(boolean alcohol) {
        this.alcohol = alcohol;
    }

    public boolean isSesame() {
        return sesame;
    }

    public void setSesame(boolean sesame) {
        this.sesame = sesame;
    }

    public boolean isSeafood() {
        return seafood;
    }

    public void setSeafood(boolean seafood) {
        this.seafood = seafood;
    }

    public boolean isWheat() {
        return wheat;
    }

    public void setWheat(boolean wheat) {
        this.wheat = wheat;
    }

    public boolean isCelery() {
        return celery;
    }

    public void setCelery(boolean celery) {
        this.celery = celery;
    }

    public boolean isNuts() {
        return nuts;
    }

    public void setNuts(boolean nuts) {
        this.nuts = nuts;
    }

    public boolean isPeanuts() {
        return peanuts;
    }

    public void setPeanuts(boolean peanuts) {
        this.peanuts = peanuts;
    }

    public boolean isEggs() {
        return eggs;
    }

    public void setEggs(boolean eggs) {
        this.eggs = eggs;
    }

    public boolean isPork() {
        return pork;
    }

    public void setPork(boolean pork) {
        this.pork = pork;
    }

    public int getPrepTime() {
        return prepTime;
    }

    public void setPrepTime(int prepTime) {
        this.prepTime = prepTime;
    }

    public int getCookTime() {
        return cookTime;
    }

    public void setCookTime(int cookTime) {
        this.cookTime = cookTime;
    }

    public int getServing() {
        return serving;
    }

    public void setServing(int serving) {
        this.serving = serving;
    }

    public String getSkillLevel() {
        return skillLevel;
    }

    public void setSkillLevel(String skillLevel) {
        this.skillLevel = skillLevel;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

}