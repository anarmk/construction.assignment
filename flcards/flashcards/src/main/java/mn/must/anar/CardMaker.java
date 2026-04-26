package mn.must.anar;

import java.io.File;
// import java.util.List;
import java.util.Scanner;

public class CardMaker {

    Deck deck = new Deck();

    public Deck loadCards(String filePath) {
        File file = new File(filePath);

        try (Scanner scanner = new Scanner(file)) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] parts = line.split("-");
                if (parts.length >= 2) {
                    String answer = parts[0].trim();
                    String question = parts[1].trim();
                    Card card = new Card(question, answer);
                    deck.addCard(card);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return deck;
    }
}