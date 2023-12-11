GRASP/SOLID PRINCIPLES USED:
1. Creator Pattern (GRASP): The Deck class primarily is responsible for creating the Card objects, because
    only that class is going to be using the cards in multiple functions.
2. Information Expert (GRASP): The classes in WarSystems primarily handle deciding the winner because they
    will all get the information from WarItems.
3. Open-Closed Principle (SOLID): The classes in WarItems do not need to be modified, only extended: Card.java,
    Deck.java, and Player.java are all three have all the functions they need.
4. Controller Pattern (GRASP): Only the main class, WarGame.java, controls the entire program. Without it, the
    game of War never happens.