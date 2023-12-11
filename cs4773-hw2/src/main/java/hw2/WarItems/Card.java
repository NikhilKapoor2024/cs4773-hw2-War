package hw2.WarItems;

/*
 * File: Card.java
 * Type: Class
 * Purpose: Creates the card object through a constructor and holds functions related to a card object
 */
public class Card {
    private String rank;
    private String suite;
    private String [] ranks = {"ACE", "KING", "QUEEN", "JACK", "10", "9", "8", "7", "6", "5", "4", "3", "2"};
    private String [] suites = {"HEARTS", "SPADES", "CLUBS", "DIAMONDS"};

    public Card(int r, int s) {
        rank = ranks[r];
        suite = suites[s];
    }

    public int getRank() {

        if (rank.equals("ACE")) {
            return 14;
        }
        else if (rank.equals("KING")) {
            return 13;
        }
        else if (rank.equals("QUEEN")) {
            return 12;
        }
        else if (rank.equals("JACK")) {
            return 11;
        }
        else {
            return Integer.parseInt(rank);
        }
        
    }

    public String getCardName() {
        return rank + " of " + suite;
    }

}
