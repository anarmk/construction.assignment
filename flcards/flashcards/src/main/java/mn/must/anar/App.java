package mn.must.anar;

// import java.io.IOException;
// import java.nio.file.Files;
// import java.nio.file.Path;
// import java.util.List;
import java.util.Scanner;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int repetitions = 1;
        String orderChoice = "3";
        boolean invertChoice = false;

        System.out.println("Please enter the path to a card file:");
        String filePath = scanner.nextLine();
        CardMaker cardMaker = new CardMaker();
        Deck loadedDeck = cardMaker.loadCards(filePath);
        int count = loadedDeck.getCards().size();
        System.out.println(count + " cards loaded.");

        while(true) {
        System.out.println("Please choose an option:");
        System.out.println("1. Help!");
        System.out.println("2. Order");
        System.out.println("3. Repetitions");
        System.out.println("4. Invert");
        System.out.println("5. Study flashcards");

        String choice = scanner.nextLine();
            switch (choice) {
                case "1":
                    System.out.println("This is help information");
                    System.out.println("Order explanation: Cards will be presented in the order they were added.");
                    System.out.println("-> Random: Cards will be shuffled randomly each time you study.");
                    System.out.println("-> Worst Mistakes First: Cards you have struggled with will be shown more frequently.");
                    System.out.println("-> Recent Mistakes: Cards you got wrong in the last session will be prioritized.");
                    System.out.println("Repetitions explanation: You can choose how many times you have answer correctly to remove a card.");
                    System.out.println("-> Default is 1.");
                    System.out.println("Invert explanation: You can choose to invert the question and answer of the cards for a different perspective.");
                    System.out.println("Study flashcards explanation: This option will start the study session with the current deck configuration.");
                    break;
                case "2":
                    System.out.println("Please choose an order(Default is Random order):");
                    System.out.println("1. Worst Mistakes First");
                    System.out.println("2. Recent Mistakes");
                    orderChoice = scanner.nextLine();
                    break;
                case "3":
                    System.out.println("Please enter the number of repetitions:");
                    repetitions = Integer.parseInt(scanner.nextLine());
                    break;
                case "4":
                    System.out.println("Deck inverted.");
                    invertChoice = true;
                    break;
                case "5":
                    // Implement logic to study flashcards
                    studyFlashcards(loadedDeck, orderChoice, repetitions, invertChoice);
                    scanner.close();
                    return;
                default:
                    System.out.println("Invalid option, please try again.");
            }
        }
    }

    public static void studyFlashcards(Deck deck, String orderChoice, int repetitions, boolean invertChoice) {
        WorstMistakesFirstOrganizer worstMistakesFirstOrganizer = new WorstMistakesFirstOrganizer();
        RecentMistakesFirstOrganizer recentMistakesFirstOrganizer = new RecentMistakesFirstOrganizer();
        RandomOrganizer randomOrganizer = new RandomOrganizer();
        Scanner scanner = new Scanner(System.in);
        if (invertChoice) {
            deck.invertCards();
        }
        while (!deck.isComplete()) {
            for (Card card : deck.getCards()) {
                System.out.println("Question: " + card.getQuestion());
                String response = scanner.nextLine();
                if (card.check(response)) {
                    System.out.println("Correct!");
                } else {
                    System.out.println("Incorrect! The correct answer is: " + card.getAnswer());
                }
                if (card.congratsRepeat()) {
                    System.out.println("REPEAT award: You've attempted this card more than 5 times. Keep going!");
                }
                if (card.congratsConfident()) {
                    System.out.println("CONFIDENT award: You've answered this card correctly 3 times. Great job!");
                }
                if (deck.congratsCorrect()) {
                    System.out.println("CORRECT award: You've answered all cards correctly in this session. Well done!");
                }
            }
            switch (orderChoice) {
                case "1":
                    worstMistakesFirstOrganizer.reorganizeCards(deck);
                    break;
                case "2":
                    recentMistakesFirstOrganizer.reorganizeCards(deck);
                    break;
                default:
                    randomOrganizer.reorganizeCards(deck);
                    break;
            }
            deck.removeSuccessfulCards(repetitions);
        }
        scanner.close();
    }
}
