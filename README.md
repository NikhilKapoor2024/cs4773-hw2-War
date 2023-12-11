# cs4773-hw2-War

This repository was my second **Object Oriented Programming** assignment for my Fall 2023 semester. We were tasked with creating a program that will simulate a
game of War. Unlike assignment 1, we had to make the program from scratch through **Maven**, the automation tool for **Java**. The stipulation of this assignment was
to make three versions of the game:

**Version 1**: The deck is shuffled between 2 players. Every round, both players play a card from the top of their deck. If one player has a higher tier card than the other, then they
take both cards and add them to the bottom of their deck. If both players play cards of the same rank, then they enter a round of War, where both players add three additional cards to be in
play. The last additional card is used to decide the round winner. If both players tie, then another three cards are played with the last one being the deciding winner. If one player has a
higher rank than the other, then that player wins the round of War and adds the cards that were in play to the bottom of their deck. The game continues until one of the players has zero cards left,
in which that player loses the game while the other is declared the winner.

**Version 2**: The deck is shuffled between 2 players. Every round, both players play a card from the top of their deck. If one player has a higher tier card than the other, then they
take both cards and add them to a seperate point pile. Other than that, the game is played almost the same as Version 1. The game continues until one of the players has zero cards left,
in which the game ends, and the winner is determined by how many cards are in their respective point piles.

**Version 3**: The deck is shuffled between 3 players. Every round, all three players play a card from the top of their deck. If one player has a higher tier card than the others, then they
take all three cards and add them to their respective point pile. If two or three players tie in a normal round, then all three players enter War, which is played nearly the same except adapted
for three players. The game ends when all three players have zero cards in their deck and the winner is determined by the player with the most cards in their point pile.

For this assignment, we were to demonstrate knowledge of **SOLID** and **GRASP** principles.
