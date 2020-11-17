package model;

class Question {
    final String questionText;
    final String answer1;
    final String answer2;
    final String answer3;
    final String answer4;
    final String correctAnswer;

    Question(String questionText, String answer1, String answer2, String answer3, String answer4) {
        this.questionText = questionText;
        this.answer1 = answer1;
        this.answer2 = answer2;
        this.answer3 = answer3;
        this.answer4 = answer4;
        correctAnswer = answer1;
    }
}
