package model;


import java.util.List;

/**
 * Created by Emil Johansson
 * Date: 2020-11-20
 * Time: 15:25
 * Project: QuizCamp
 * Package: model
 */
public class CategoryProtocol {

    Category category;
    private List<Question> questionList;
    private int currentQuestion = 0;

    public CategoryProtocol(Category category){
        this.category = category;
        questionList = category.shuffledList;
    }

    public Question getQuestion(){
        if(questionList.size() <= currentQuestion){
            return null;
        }
        Question question = questionList.get(currentQuestion);
        currentQuestion++;
        return question;
    }

    public String getCategoryName(){
        return category.categoryName;
    }
}
