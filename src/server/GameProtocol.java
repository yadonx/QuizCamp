package server;

import model.Category;
import model.QuestionDatabase;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.Properties;

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
    private int rounds;
    private int questions;

    List<Category> categoryList;

    GameProtocol() {
        Properties roundProperties = new Properties();
        Properties questionProperties = new Properties();
        try {
            roundProperties.load(new FileInputStream("src/server/properties/threeRound.properties"));
            questionProperties.load(new FileInputStream("src/server/properties/threeQuestion.properties"));
        }catch (IOException  e){
            e.printStackTrace();
        }
        rounds = Integer.parseInt(roundProperties.getProperty("round"));
        questions = Integer.parseInt(questionProperties.getProperty("questions"));

        System.out.println(rounds);

        questionDatabase = new QuestionDatabase();
        categoryList = questionDatabase.getShuffledCategories(rounds);//Todo fixa med Properties.
    }

    public Category getCategory(){
        if(categoryList.size() <= currentCategory){
            return null;
        }
        Category temp = categoryList.get(currentCategory);
        temp.getShuffledQuestions(questions);
        currentCategory++;
        return temp;
    }
}
