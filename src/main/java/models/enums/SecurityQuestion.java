package models.enums;

public enum SecurityQuestion {
    QUESTION1("1.What is your dream job?"),
    QUESTION2("2.What is your favorite color?"),
    QUESTION3("3.What is your favorite team?");


    private final String Question;
    SecurityQuestion(String question) {
        Question = question;
    }

    public String getQuestion() {
        return Question;
    }

}
