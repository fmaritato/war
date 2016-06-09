
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.LinkedList;
import java.util.List;

public class War {

    private static final Logger log = LoggerFactory.getLogger(War.class);

    public static void main(String[] args) {
        // Player hands implemented as a deck
        Deck hand1 = new Deck(0);
        Deck hand2 = new Deck(0);

        // Creates a deck and shuffles it.
        Deck deck = new Deck(1);

        // Deal the cards to the two players.
        while (!deck.isEmpty()) {
            hand1.add(deck.getTopCard());
            hand2.add(deck.getTopCard());
        }

        log.debug("hand1 {} hand2 {}", hand1.size(), hand2.size());
        LinkedList<Card> trick = new LinkedList<Card>();

        while (true) {
            // grab the two cards we want to compare
            if (hand1.isEmpty() || hand2.isEmpty()) {
                break;
            }
            Card a = hand1.getTopCard();
            Card b = hand2.getTopCard();
            trick.add(a);
            trick.add(b);
            // Tie
            if (a.compareTo(b) == 0) {
                // For a tie, each play puts face down three cards
                // In the event one player does not have enough cards, this causes the player to lose.
                // The rules of the game are unclear here but this is one possibility.
                log.info("Tie. {},{}", a, b);
                if (!war(hand1, hand2, trick)) {
                    break;
                }
                log.info("trick size {}", trick.size());
            }
            // Hand 2 wins
            else if (a.compareTo(b) < 0) {
                log.info("Hand 2 wins. {},{}", a, b);
                hand2.addAll(trick);
                trick.clear();
            }
            // Hand 1 wins
            else {
                log.info("Hand 1 wins. {},{}", a, b);
                hand1.addAll(trick);
                trick.clear();
            }

            log.debug("hand1 {} hand2 {} {}", hand1.size(), hand2.size(), (hand1.size() + hand2.size()));
        }

        if (hand1.size() == 0) {
            log.info("hand 2 wins!");
        }
        else {
            log.info("hand1 wins!");
        }
    }

    private static boolean war(Deck hand1, Deck hand2, LinkedList<Card> trick) {
        for (int i = 0; i < 3; i++) {
            if (hand1.isEmpty() || hand2.isEmpty()) {
                log.info("Not enough cards. {}, {}", hand1.size(), hand2.size());
                return false;
            }
            trick.add(hand1.getTopCard());
            trick.add(hand2.getTopCard());
        }
        return true;
    }
}
