package hw2;

import java.util.ArrayList;

import hw2.WarSystem.*;
import hw2.WarItems.*;

/**
 * File: WarGame.java
 * Type: Class
 * Function: Main method for running a game of War
 */
public class WarGame {

    public static void main( String[] args ) {
        ArrayList<Player> players;
        int numberOfRounds;
        int seed;

        if (args.length < 2) {
            System.err.println("ERROR: too few arguments");
            System.exit(-1);
        }

        switch (Integer.parseInt(args[0])) {
            case 1:
                players = new ArrayList<Player>();
                numberOfRounds = Integer.parseInt(args[1]);
                seed = Integer.parseInt(args[2]);

                VersionOne v1 = new VersionOne(numberOfRounds, players, seed);
                v1.playGame();
                break;
            case 2:
                players = new ArrayList<Player>();
                seed = Integer.parseInt(args[1]);

                VersionTwo v2 = new VersionTwo(players, seed);
                v2.playGame();
                break;
            case 3:
                players = new ArrayList<Player>();
                seed = Integer.parseInt(args[1]);
                VersionThree v3 = new VersionThree(players, seed);
                v3.playGame();
                break;
            default:
                System.err.println("ERROR: invalid version of War");
                System.exit(1);
        }
    }
    
}
