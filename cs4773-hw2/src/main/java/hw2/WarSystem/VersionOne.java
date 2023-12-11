package hw2.WarSystem;

import java.util.ArrayList;

import hw2.WarItems.*;

/**
 * File: VersionOne.java
 * Type: Class
 * Purpose: Holds behavior patterns for version 1 of War (two players, last man standing)
 */
public class VersionOne implements WarSystemInterface {
    private boolean endOfGame = false;
    private final int MAX_NUM_CARDS = 52;
    private ArrayList<Player> players;
    private int numRounds;
    private Deck gameDeck;

    public VersionOne(int inputNumRounds, ArrayList<Player> players, int seed) {
        numRounds = inputNumRounds;
        this.players = players;
        gameDeck = new Deck(seed, 52);
    }

    @Override
    public boolean playGame() {
        players = instantiatePlayers(players);
        Player playerOne = players.get(0);
        Player playerTwo = players.get(1);
        System.out.println(playerOne.getPlayerDeckCount() + " " + playerTwo.getPlayerDeckCount());
        int roundNum = 1;
        
        if (playerOne.getPlayerDeckCount() == 0 || playerTwo.getPlayerDeckCount() == 0) {
            System.err.println("ERROR: One of the players has no cards in their deck.");
            return false;
        }

        while (endOfGame == false && numRounds > 0) {
            if (midGameWinnerCheck(playerOne, playerTwo) == true) {
                break;
            }

            Deck cardsPlayed = new Deck();

            System.out.println("ROUND " + roundNum);
            playersPlayCards(playerOne, playerTwo, cardsPlayed);
            roundWinner(playerOne, playerTwo, cardsPlayed);

            numRounds--;
            roundNum++;
        }

        finalGameWinner(playerOne, playerTwo);

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

    private void roundWinner(Player playerOne, Player playerTwo, Deck deck) {
        // deck size - 2 = playerOne card, deck size - 1 = playerTwo card
        if (deck.getCards().get(deck.getDeckSize() - 2).getRank() > deck.getCards().get(deck.getDeckSize() - 1).getRank()) {
            System.out.println("Player 1 wins the round!");
            playerOne.addCardsToPlayerDeck(deck);
        }
        else if (deck.getCards().get(deck.getDeckSize() - 2).getRank() < deck.getCards().get(deck.getDeckSize() - 1).getRank()) {
            System.out.println("Player 2 wins the round!");
            playerTwo.addCardsToPlayerDeck(deck);
        }
        else {
            warRound(playerOne, playerTwo, deck);
        }
        System.out.println("Player 1 has " + playerOne.getPlayerDeckCount() + " cards left.");
        System.out.println("Player 2 has " + playerTwo.getPlayerDeckCount() + " cards left.");
    }

    private void warRound(Player playerOne, Player playerTwo, Deck warRoundDeck) {
        System.out.println("*** TIME TO GO TO WAR ***");
        
        for (int i = 0; i < 2; i++) {
            playCard(playerOne, warRoundDeck);
            playCard(playerTwo, warRoundDeck);
            if (midGameWinnerCheck(playerOne, playerTwo) == true) { // one player runs out of cards during the first phase
                endOfGame = true;
                return;
            }
        }

        playCard(playerOne, warRoundDeck);
        System.out.println("Player 1 plays " + warRoundDeck.getCards().getLast().getCardName());
        playCard(playerTwo, warRoundDeck);
        System.out.println("Player 2 plays " + warRoundDeck.getCards().getLast().getCardName());
        if (midGameWinnerCheck(playerOne, playerTwo) == true) {
            endOfGame = true;
            return;
        }
        
        roundWinner(playerOne, playerTwo, warRoundDeck);
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

    private void finalGameWinner(Player playerOne, Player playerTwo) {
        System.out.println("All rounds have been finished. Time to find the winner...");
        if (playerOne.getPlayerDeckCount() > playerTwo.getPlayerDeckCount()) {
            System.out.println("PLAYER 1 WINS!!!!!!!!!");
        }
        else if (playerOne.getPlayerDeckCount() < playerTwo.getPlayerDeckCount()) {
            System.out.println("PLAYER 2 WINS!!!!!!!!!");
        }
        else {
            System.out.println("PLAYER 1 AND PLAYER 2 TIED!!!!!!!!!");
        }
    }
}