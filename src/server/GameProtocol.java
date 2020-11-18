package server;

import model.Category;
import model.QuestionDatabase;

import java.util.List;

/**
 * Created by Filip Jakobsson
 * Date: 2020-11-17
 * Time: 16:18
 * Project: QuizCamp
 * Copyright: MIT
 */
public class GameProtocol {

    QuestionDatabase questionDatabase;
    private int currentCategory = 0;

    List<Category> categoryList;

    GameProtocol(){
        questionDatabase = new QuestionDatabase();
        categoryList = questionDatabase.getShuffledCategories(3);//Todo fixa med Properties.
    }

    public Category getCategory(){
        Category temp = categoryList.get(currentCategory);
        currentCategory++;
        return temp;
    }
}
