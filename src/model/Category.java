package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Category implements Serializable {
    String categoryName;
    List<Question> questionList = new ArrayList<>();
    List<Question> shuffledList = new ArrayList<>();

    Category(String categoryName) {
        this.categoryName = categoryName;
    }

    public void addQuestion(Question question) {
        questionList.add(question);
    }

    public void getShuffledQuestions(int amount){
        List<Question> tempList = new ArrayList<>(questionList);
        Collections.shuffle(tempList);
        List<Question> outList = new ArrayList<>();
        for (int i = 0; i < amount; i++){
            outList.add(tempList.get(i));
        }
        shuffledList = outList;
    }

}