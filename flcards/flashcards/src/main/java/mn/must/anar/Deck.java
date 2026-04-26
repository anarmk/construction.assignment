package mn.must.anar;
import java.util.ArrayList;
import java.util.List;

public class Deck {
    private String name;
    private List<Card> cards;

    public Deck() {
        this.cards = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public List<Card> getCards() {
        return cards;
    }

    public void addCard(Card card) {
        cards.add(card);
    }

    public void removeCard(Card card) {
        cards.remove(card);
    }

    public void invertCards() {
        for (Card card : cards) {
            String temp = card.getQuestion();
            card.setQuestion(card.getAnswer());
            card.setAnswer(temp);
        }
    }

    public void removeSuccessfulCards(int repetitions) {
        cards.removeIf(card -> card.getCorrectCount() >= repetitions);
    }

    public boolean isComplete(){
        return cards.isEmpty();
    }

    public boolean congratsCorrect(){
        for (Card card : cards) {
            if (card.isLastAttemptWrong()) {
                return false;
            }
        }
        return true;
    }
}
