=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=
CIS 120 Game Project README
PennKey: vdelopez
=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=

===================
=: Core Concepts :=
===================

- List the four core concepts, the features they implement, and why each feature
  is an appropriate use of the concept. Incorporate the feedback you got after
  submitting your proposal.

  1. 2D Arrays. It implements the core 4x4 board of 2048 by storing the current value
  of each tile. An empty tile has a value of 0, a tile with a 2 has a value of 2, etc.
  A 2D array is the simplest and best way to implement a 4x4 board, as it makes it easy
  to change values of a specific tile when needed. Also, the size of the board is
  always 4x4, so we can simply declare a 4x4 2D int array and we are done.

  2. Collections / Maps. It implements the sliding and merging of tiles around the board.
  I used a LinkedList to make use of the pop() functions. I would put the row
  into the LinkedList, and calling pop() would shift everything once in a direction. A
  second LinkedList is used to do the merging of tiles. LinkedLists are appropriate for this
  as while you could manually shift all the values over to the left just using the 2D
  array and conditional statements, LinkedLists make the process so much easier with
  its pop() function.

  3. File I/O. It implements the feature that allows the user to save their progress and
  load it later if they wish to pause and continue playing later. The load feature works
  regardless if the user presses load later on in the game, or if they close the game
  and return later. This feature is appropriate as I can simply write the values of the 2D
  array to the file to save the game state, and read in the values to load the game
  state again.

  4. JUnit Testable Component. It implements a way to test the model component of my game,
  without worrying about drawing things on the screen and the GUI, or having to play
  until I reach a certain game state (for example, the winning condition of getting a 2048
  tile). This feature is an appropriate use of the concept because it tests my core-game
  state.

=========================
=: Your Implementation :=
=========================

- Provide an overview of each of the classes in your code, and what their
  function is in the overall game.
The TwentyFourtyEight class is the model of my game, and implements the main logic of
the game. It contains the slideLeft(), slideRight(), slideUp(), and slideDown() functions
that allow the user to play the game. It also has functions to check if the user lost or won,
save and load progress, restart the current game, and randomly place a new tile on the board.
The GameBoard class is the controller and view of my game, and implements the main GUI
for the game. It uses ActionListeners to listen for arrow key presses, and calls methods
from the TwentyFourtyEight class to update the state of the board accordingly. It uses its
paintComponent method to set up the 4x4 grid layout of tiles, and repaints itself after
every arrow key press so the user can correctly see the state of the board.
The RunTwentyFourtyEight class acts mainly as the view of my game, but also implements
controller functionality through its buttons. It adds every component to the JFrame, and
displays the JFrame to the user. Its buttons wait for a mouse click on it, and then
perform a corresponding action depending on the button clicked.
The Tile class was created to create uniform tiles that could be placed in a 4x4 grid
layout on the board. I checked what the value of the tile was, and then assigned it
a corresponding color similar to the original game.

- Were there any significant stumbling blocks while you were implementing your
  game (related to your design, or otherwise)?
One significant stumbling block for me was figuring out how to implement the sliding
and merging of tiles. I had a vague idea that I wanted to utilize the pop() function
in LinkedLists to automatically and easily shift tiles, but implementing the merging
took a bit of thinking. One thing I constantly had to think about was maintaining the
Model-View-Controller design framework in my code as well, and making sure the model
is completely independent from the view and controller, and was able to be tested with
JUnit tests.

- Evaluate your design. Is there a good separation of functionality? How well is
  private state encapsulated? What would you refactor, if given the chance?
I believe that my design has a good separation of functionality. The model is completely
separate from the GUI, and I can play a game just on the TwentyFourtyEight file if I
wanted to. Private state is encapsulated well throughout my design as nothing in the state
can be directly modified from outside of the model. If given the chance, I may refactor
my slide methods as the methods are very similar, just with some switching around of
variables in the loop.


========================
=: External Resources :=
========================

- Cite any external resources (images, tutorials, etc.) that you may have used 
  while implementing your game.
Took a screenshot of the tiles at this link: https://blog.wolfram.com/2014/05/09/2048/
and used https://imagecolorpicker.com to get the hex values of the colors.