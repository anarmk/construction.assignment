package mn.must.anar;

public class Card {
    private String question;
    private String answer;
    private int correctCount;
    private int wrongCount;
    private int attemptCount;
    private boolean lastAttemptWrong;

    public Card(String question, String answer) {
        this.question = question;
        this.answer = answer;
    }

    public String getQuestion() {
        return question;
    }

    public String getAnswer() {
        return answer;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public boolean check(String response){
        attemptCount++;
        if (response.equals(answer) || response.equals(question)) {
            correctCount++;
            lastAttemptWrong = false;
            return true; // Correct
        } else {
            wrongCount++;
            lastAttemptWrong = true;
            return false; // Incorrect
        }
    }

    public int getCorrectCount() {
        return correctCount;
    }

    public int getAttemptCount() {
        return attemptCount;
    }

    public int getWrongCount() {
        return wrongCount;
    }

    public boolean isLastAttemptWrong() {
        return lastAttemptWrong;
    }

    public boolean congratsRepeat() {
        if (attemptCount > 5) {
            return true;
        }
        return false;
    }

    public boolean congratsConfident(){
        if (correctCount >=3) {
            return true;
        }
        return false;
    }
}
