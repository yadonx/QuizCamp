package model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Category {
    private final String categoryName;
    private final List<Question> questionList = new ArrayList<>();

    Category(String categoryName) {
        this.categoryName = categoryName;
    }

    public void addQuestion(Question question) {
        questionList.add(question);
    }

    public List<Question> getShuffledQuestions(int amount) {
        List<Question> tempList = new ArrayList<>(questionList);
        tempList.remove(0);
        Collections.shuffle(tempList);
        List<Question> outList = new ArrayList<>();
        for (int i = 0; i < amount; i++) {
            outList.add(tempList.get(i));
        }
        return outList;
    }

    public String getCategoryName() {
        return categoryName;
    }
}