package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class QuestionOfCategory implements Serializable {
    private final List<String> categories = new ArrayList<>();

    public QuestionOfCategory(String category1, String category2, String category3, String category4) {//TODO: 3 catagories
        this.categories.add(category1);
        this.categories.add(category2);
        this.categories.add(category3);
        this.categories.add(category4);
    }

    public List<String> getCategories() {
        return categories;
    }
}

