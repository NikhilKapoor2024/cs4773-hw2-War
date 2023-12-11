package hw2.WarSystem;

import java.util.ArrayList;

import hw2.WarItems.*;

/**
 * File: VersionTwo.java
 * Type: Class
 * Purpose: holds gameplay pattern for version two of War (2 players, a point pile, and go until both are empty);
 */
public class VersionTwo implements WarSystemInterface {
    private boolean endOfGame = false;
    private final int MAX_NUM_CARDS = 52;
    private ArrayList<Player> players;
    private Deck gameDeck;
    private int seed;

    public VersionTwo(ArrayList<Player> players, int seed) {
        this.players = players;
        this.seed = seed;
        gameDeck = new Deck(seed, 52);
    }

    @Override
    public boolean playGame() {
        players = instantiatePlayers(players);
        Player playerOne = players.get(0);
        Player playerTwo = players.get(1);
        Deck playerOnePoints = new Deck();
        Deck playerTwoPoints = new Deck();

        System.out.println(playerOne.getPlayerDeckCount() + " " + playerTwo.getPlayerDeckCount());

        if (playerOne.getPlayerDeckCount() == 0 || playerTwo.getPlayerDeckCount() == 0) {
            System.err.println("ERROR: One of the players has no cards in their deck.");
            return false;
        }

        while (endOfGame == false) {
            if (midGameWinnerCheck(playerOne, playerTwo) == true) {
                break;
            }
            Deck cardsPlayed = new Deck();

            playersPlayCards(playerOne, playerTwo, cardsPlayed);
            roundWinner(playerOne, playerTwo, cardsPlayed, playerOnePoints, playerTwoPoints);

        }
        finalGameWinner(playerOnePoints, playerTwoPoints);

        return true;
    }

    private ArrayList<Player> instantiatePlayers(ArrayList<Player> players) {
        this.players.add(new Player(Deck.dealCardsToPlayers(2, 26, gameDeck)));
        this.players.add(new Player(Deck.dealCardsToPlayers(2, 26, gameDeck)));
        
        return players;
    }

    private void playersPlayCards(Player playerOne, Player playerTwo, Deck cardsPlayed) {
        playCard(playerOne, cardsPlayed);
        System.out.println("Player 1 plays " + cardsPlayed.getCards().getLast().getCardName());
        playCard(playerTwo, cardsPlayed);
        System.out.println("Player 2 plays " + cardsPlayed.getCards().getLast().getCardName());
    }

    private void playCard(Player player, Deck deck) {
        Card card = player.playCard();
        deck.addCardToDeck(card);
    }

    private void roundWinner(Player playerOne, Player playerTwo, Deck deck, Deck p1PD, Deck p2PD) {
        // deck size - 2 = playerOne card, deck size - 1 = playerTwo card
        if (deck.getCards().get(deck.getDeckSize() - 2).getRank() > deck.getCards().get(deck.getDeckSize() - 1).getRank()) {
            System.out.println("Player 1 wins the round!");
            p1PD.addCardsToDeck(deck);
        }
        else if (deck.getCards().get(deck.getDeckSize() - 2).getRank() < deck.getCards().get(deck.getDeckSize() - 1).getRank()) {
            System.out.println("Player 2 wins the round!");
            p2PD.addCardsToDeck(deck);
        }
        else {
            warRound(playerOne, playerTwo, deck, p1PD, p2PD);
        }
        System.out.println("Player 1 has won " + p1PD.getDeckSize() + " cards.");
        System.out.println("Player 2 has won " + p2PD.getDeckSize() + " cards.");
    }

    private void warRound(Player playerOne, Player playerTwo, Deck warRoundDeck, Deck p1PD, Deck p2PD) {
        
        // first two cards
        for (int i = 0; i < 2; i++) {
            playCard(playerOne, warRoundDeck);
            playCard(playerTwo, warRoundDeck);
            if (midGameWinnerCheck(playerOne, playerTwo) == true) { // one player runs out of cards during the first phase
                endOfGame = true;
                return;
            }
        }

        // last additonal card
        playCard(playerOne, warRoundDeck);
        System.out.println("Player 1 plays " + warRoundDeck.getCards().getLast().getCardName());
        playCard(playerTwo, warRoundDeck);
        System.out.println("Player 2 plays " + warRoundDeck.getCards().getLast().getCardName());
        if (midGameWinnerCheck(playerOne, playerTwo) == true) {
            endOfGame = true;
            return;
        }

        roundWinner(playerOne, playerTwo, warRoundDeck, p1PD, p2PD);
    }

    private boolean midGameWinnerCheck(Player playerOne, Player playerTwo) {
        if (playerOne.getPlayerDeckCount() == 0 && playerTwo.getPlayerDeckCount() == 0) {
            System.out.println("Both players have no cards left.");
            return true;
        }
        else if (playerOne.getPlayerDeckCount() == 0) {
            System.out.println("Player 1 has no cards left. Player 2 wins!");
            return true;
        }
        else if (playerTwo.getPlayerDeckCount() == 0) {
            System.out.println("Player 2 has no cards left. Player 1 wins!");
            return true;
        }

        if (playerOne.getPlayerDeckCount() == MAX_NUM_CARDS) {
            System.out.println("Player 1 wins!");
            return true;
        }
        else if (playerTwo.getPlayerDeckCount() == MAX_NUM_CARDS) {
            System.out.println("Player 2 wins!");
            return true;
        }

        return false;
    }

    private void finalGameWinner(Deck p1PD, Deck p2PD) {
        System.out.println("All rounds have been finished. Time to find the winner...");
        if (p1PD.getDeckSize() > p2PD.getDeckSize()) {
            System.out.println("PLAYER 1 WINS!!!!!!!!!");
        }
        else if (p1PD.getDeckSize() < p2PD.getDeckSize()) {
            System.out.println("PLAYER 2 WINS!!!!!!!!!");
        }
        else {
            System.out.println("PLAYER 1 AND PLAYER 2 TIED!!!!!!!!!");
        }
    }
    
}
