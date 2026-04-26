package mn.must.anar;

public class RecentMistakesFirstOrganizer implements CardOrganizer {
    @Override
    public void reorganizeCards(Deck deck) {
        java.util.List<Card> correctCards = new java.util.ArrayList<>();
        java.util.List<Card> incorrectCards = new java.util.ArrayList<>();

        for (Card card : deck.getCards()) {
            if (card.getWrongCount() == 0) {
                correctCards.add(card);
            } else {
                incorrectCards.add(card);
            }
        }
        
        incorrectCards.sort((card1, card2) -> Integer.compare(card1.getWrongCount(), card2.getWrongCount()));
        deck.getCards().clear();
        deck.getCards().addAll(incorrectCards);
        deck.getCards().addAll(correctCards);
    }
}
