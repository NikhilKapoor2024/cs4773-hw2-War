package hw2.WarSystem;

import java.util.ArrayList;
import hw2.WarItems.*;

/**
 * File: VersionThree.java
 * Type: Class
 * Purpose: represents object for third variation of War (three players, point piles, first to zero loses)
 */
public class VersionThree implements WarSystemInterface {
    private boolean endOfGame = false;
    private ArrayList<Player> players;
    private Deck gameDeck;
    private int seed;

    public VersionThree(ArrayList<Player> players, int seed) {
        this.players = players;
        this.seed = seed;
        gameDeck = new Deck(seed, 52);
    }

    @Override
    public boolean playGame() {
        players = instantiatePlayers(players);
        Player playerOne = players.get(0);
        Player playerTwo = players.get(1);
        Player playerThree = players.get(2);
        Deck playerOnePoints = new Deck();
        Deck playerTwoPoints = new Deck();
        Deck playerThreePoints = new Deck();

        if (playerOne.getPlayerDeckCount() == 0 || playerTwo.getPlayerDeckCount() == 0 || playerThree.getPlayerDeckCount() == 0) {
            System.err.println("ERROR: One of the players has no cards in their deck.");
            return false;
        }

        while (endOfGame == false) {
            if (midGameWinnerCheck(playerOne, playerTwo, playerThree) == true) {
                break;
            }
            Deck cardsPlayed = new Deck();
            playersPlayCards(playerOne, playerTwo, playerThree, cardsPlayed);
            roundWinner(playerOne, playerTwo, playerThree, cardsPlayed, playerOnePoints, playerTwoPoints, playerThreePoints);

        }
        finalGameWinner(playerOnePoints, playerTwoPoints, playerThreePoints);

        return true;
    }

    private ArrayList<Player> instantiatePlayers(ArrayList<Player> players) {
        this.players.add(new Player(Deck.dealCardsToPlayers(3, 17, gameDeck)));
        this.players.add(new Player(Deck.dealCardsToPlayers(3, 17, gameDeck)));
        this.players.add(new Player(Deck.dealCardsToPlayers(3, 17, gameDeck)));
        
        return players;
    }

    private void playersPlayCards(Player playerOne, Player playerTwo, Player playerThree, Deck cardsPlayed) {
        playCard(playerOne, cardsPlayed);
        System.out.println("Player 1 plays " + cardsPlayed.getCards().getLast().getCardName());
        playCard(playerTwo, cardsPlayed);
        System.out.println("Player 2 plays " + cardsPlayed.getCards().getLast().getCardName());
        playCard(playerThree, cardsPlayed);
        System.out.println("Plaeyr 3 plays " + cardsPlayed.getCards().getLast().getCardName());
    }

    private void playCard(Player player, Deck deck) {
        Card card = player.playCard();
        deck.addCardToDeck(card);
    }

    private void roundWinner(Player playerOne, Player playerTwo, Player playerThree, Deck deck, Deck p1PD, Deck p2PD, Deck p3PD) {
        Card p1Card = deck.getCards().get(deck.getDeckSize() - 3);
        Card p2Card = deck.getCards().get(deck.getDeckSize() - 2);
        Card p3Card = deck.getCards().get(deck.getDeckSize() - 1);

        if (p1Card.getRank() > p2Card.getRank()) {
            if (p1Card.getRank() > p3Card.getRank()) {
                System.out.println("Player 1 wins the round!");
                p1PD.addCardsToDeck(deck);
            }
            else {
                System.out.println("Player 3 wins the round!");
                p3PD.addCardsToDeck(deck);
            }
        }
        else if (p1Card.getRank() < p2Card.getRank()) {
            if (p2Card.getRank() > p3Card.getRank()) {
                System.out.println("Player 2 wins the round!");
                p2PD.addCardsToDeck(deck);
            }
            else {
                System.out.println("Player 3 wins the round!");
                p3PD.addCardsToDeck(deck);
            }
        }
        else {
            System.out.println("*** TIME TO GO TO WAR ***");
            warRound(playerOne, playerTwo, playerThree, deck, p1PD, p2PD, p3PD);
        }

        System.out.println("Player 1 has a score of " + p1PD.getDeckSize());
        System.out.println("Player 2 has a score of " + p2PD.getDeckSize());
        System.out.println("Player 3 has a score of " + p3PD.getDeckSize());
    }

    private void warRound(Player playerOne, Player playerTwo, Player playerThree, Deck warRoundDeck, Deck p1PD, Deck p2PD, Deck p3PD) {
        // first two cards
        for (int i = 0; i < 2; i++) {
            playCard(playerOne, warRoundDeck);
            playCard(playerTwo, warRoundDeck);
            playCard(playerThree, warRoundDeck);
            if (midGameWinnerCheck(playerOne, playerTwo, playerThree) == true) { // one player runs out of cards during the first phase
                endOfGame = true;
                return;
            }
        }

        // last additonal card
        playCard(playerOne, warRoundDeck);
        System.out.println("Player 1 plays " + warRoundDeck.getCards().getLast().getCardName());
        playCard(playerTwo, warRoundDeck);
        System.out.println("Player 2 plays " + warRoundDeck.getCards().getLast().getCardName());
        playCard(playerThree, warRoundDeck);
        System.out.println("Player 3 plays " + warRoundDeck.getCards().getLast().getCardName());
        if (midGameWinnerCheck(playerOne, playerTwo, playerThree) == true) {
            endOfGame = true;
            return;
        }

        roundWinner(playerOne, playerTwo, playerThree, warRoundDeck, p1PD, p2PD, p3PD);
    }

    private boolean midGameWinnerCheck(Player playerOne, Player playerTwo, Player playerThree) {
        if (playerOne.getPlayerDeckCount() == 0 || playerTwo.getPlayerDeckCount() == 0 || playerThree.getPlayerDeckCount() == 0) {
            return true;
        }

        return false;
    }

    private void finalGameWinner(Deck p1PD, Deck p2PD, Deck p3PD) {
        System.out.println("All rounds have been finished. Time to find the winner...");
        if (p1PD.getDeckSize() > p2PD.getDeckSize()) {
            if (p3PD.getDeckSize() > p1PD.getDeckSize()) {
                System.out.println("PLAYER 3 WINS!!!!!!!!!");
            }
            else {
                System.out.println("PLAYER 1 WINS!!!!!!!!!");
            }
        }
        else if (p1PD.getDeckSize() < p2PD.getDeckSize()) {
            if (p3PD.getDeckSize() > p2PD.getDeckSize()) {
                System.out.println("PLAYER 3 WINS!!!!!!!!!");
            }
            else {
                System.out.println("PLAYER 2 WINS!!!!!!!!!");
            }
        }
        else {
            System.out.println("PLAYER 1 AND PLAYER 2 TIED!!!!!!!!!");
        }
    }
}
