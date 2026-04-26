package mn.must.anar;

import java.util.ArrayList;
import java.util.Collections;

public class RandomOrganizer implements CardOrganizer{
    @Override
    public void reorganizeCards(Deck deck) {
        // Implement random shuffling logic here
        final ArrayList<Card> cardsCopy = new ArrayList<>(deck.getCards());
        Collections.shuffle(cardsCopy);
        deck.getCards().clear();
        deck.getCards().addAll(cardsCopy);
    }
}