package hw2.WarItems;

import java.util.LinkedList;
import java.util.Random;

/**
 * File: Deck.java
 * Type: Class
 * Purpose: Holds constructor and functions for Deck objects
 */
public class Deck {
    private LinkedList<Card> cards = new LinkedList<Card>();
    private int NUM_SUITES, NUM_RANKS;
    private int seed = 0;
    private int numCards;

    public Deck(int seed, int numCards) {
        this.numCards = numCards;
        this.NUM_SUITES = 4;
        this.NUM_RANKS = 13;
        this.seed = seed;
        cards = createDeck();
        cards = shuffleDeck(cards);
    }

    public Deck() {
        cards = new LinkedList<Card>();
    }

    public LinkedList<Card> createDeck() {
        int loopCount = 0;

        for (int i = 0; i < NUM_SUITES; i++) {
            for (int j = 0; j < NUM_RANKS; j++) {
                if (loopCount >= numCards) {
                    break;
                }
                Card card = new Card(j, i);
                cards.add(card);
                loopCount++;
            }
        }

        return cards;
    }

    public LinkedList<Card> shuffleDeck(LinkedList<Card> origDeck) {
        Random rand = new Random(seed);
        
        LinkedList<Card> finalDeck = new LinkedList<Card>();
        try {
            while (!origDeck.isEmpty()) {
                int index = rand.nextInt(origDeck.size());
                Card temp = origDeck.remove(index);
                finalDeck.add(temp);
            }
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
        }

        return finalDeck;
    }

    public static Deck dealCardsToPlayers(int numPlayers, int numCards, Deck tempDeck) {
        Deck playerDeck = new Deck();

        try {
            if (numPlayers <= 1) {
                throw new Exception("You can't play a game of War with less than two players.");
            }

            for (int i = 0; i < numCards; i++) {
                Card card = tempDeck.getTopCard();
                playerDeck.addCardToDeck(card);
                tempDeck.removeCardFromDeck(card);
            }
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
        }

        return playerDeck;
    }

    public int getDeckSize() {
        return this.cards.size();
    }

    public void addCardsToDeck(LinkedList<Card> incomingCards) {
        this.cards.addAll(incomingCards);
    }

    // for version 2 & 3
    public void addCardsToDeck(Deck deck) {
        this.cards.addAll(deck.getCards());
    }

    public void addCardToDeck(Card card) {
        this.cards.addLast(card);
    }

    public Card getTopCard() {
        return this.cards.get(0);
    }

    public void removeCardFromDeck(Card card) {
        this.cards.remove(card);
    }

    public Card removeFirstCardFromDeck() {
        return this.cards.removeFirst();
    }

    public LinkedList<Card> getCards() {
        return this.cards;
    }

}
