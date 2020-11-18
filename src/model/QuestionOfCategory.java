package model;

import java.io.Serializable;

public class QuestionOfCategory implements Serializable {
    final String category1;
    final String category2;
    final String category3;

    public QuestionOfCategory(String category1, String category2, String category3) {
        this.category1 = category1;
        this.category2 = category2;
        this.category3 = category3;
    }
}

