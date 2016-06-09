
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Collection;
import java.util.Collections;
import java.util.LinkedList;

public class Deck {

    private static final Logger log = LoggerFactory.getLogger(Deck.class);

    private LinkedList<Card> cards = new LinkedList<Card>();

    public Deck(int numberOfDecks) {
        if (numberOfDecks > 0) {
            for (int j = 0; j < numberOfDecks; j++) {
                for (Suit s : Suit.values()) {
                    for (int i = 2; i < 15; i++) {
                        cards.add(new Card(i, s));
                    }
                }
            }
        }
        Collections.shuffle(cards);
    }

    public void add(Card c) {
        cards.add(c);
    }

    public void addAll(Collection<Card> c) {
        cards.addAll(c);
    }

    public Card getTopCard() {
        return cards.removeFirst();
    }

    public boolean isEmpty() {
        return cards.isEmpty();
    }

    public int size() {
        return cards.size();
    }

}
