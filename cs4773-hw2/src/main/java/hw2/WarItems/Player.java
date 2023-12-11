package hw2.WarItems;

/**
 * File: Player.java
 * Type: Class
 * Purpose: holds Player object constructor and related functions
 */
public class Player {
    private Deck playerDeck;
    private Card playerCard;

    public Player(Deck deck) {
        this.playerDeck = deck;
    }

    public Deck getDeck() {
        return playerDeck;
    }

    public Card getCard() {
        return playerCard;
    }

    public int getPlayerDeckCount() {
        return playerDeck.getDeckSize();
    }

    public Card playCard() {
        return playerDeck.getCards().removeFirst();
    }

    public void addCardToPlayerDeck(Card cardToAdd) {
        playerDeck.addCardToDeck(cardToAdd);
    }

    public void addCardsToPlayerDeck(Deck deck) {
        playerDeck.addCardsToDeck(deck.getCards());
    }

    public void removeCardFromPlayerDeck(Card cardToRemove) {
        playerDeck.removeCardFromDeck(cardToRemove);
    }
}
