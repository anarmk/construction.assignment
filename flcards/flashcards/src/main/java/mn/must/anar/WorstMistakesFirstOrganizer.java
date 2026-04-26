package mn.must.anar;

public class WorstMistakesFirstOrganizer implements CardOrganizer {
    @Override
    public void reorganizeCards(Deck deck) {
        java.util.List<Card> cards = new java.util.ArrayList<>(deck.getCards());
        deck.getCards().clear();

        cards.sort((card1, card2) -> Integer.compare(card2.getWrongCount(), card1.getWrongCount()));
        deck.getCards().addAll(cards);
    }
}