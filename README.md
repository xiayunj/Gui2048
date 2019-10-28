Program Description:
    This Program is a game whose name is 2048. After opening this program,
you can play this game by using the arrow keys on your keyboard. For
example, if you press your UP arrow key, you can move the tiles on the 
board, then the tiles with numbers will move up, and two same numbers will
add together to be a larger number. The rule of winning is to get a tile
with number 2048! However, if you can't move in any 4 directions, the game
is over. Also, you can save your game by pressing S, then the game will be
saved. Your score is on top of the game, the larger of numbers you get, the
higher score you will get.

Short Response:
    Linux Questions:
        1) command: mkdir -p fooDir/barDir
        2) For example, if you are in a directory with some java files and
some class files, but you only want to know all the java files. At this
time, you can use command ls *.java. This will show all the java files in
this directory instead of ls which will show all files in the directory.
Another example is to copy only all java files in this directory, you can
use command cp *.java. These two examples are about how to use a wildcard
character.
        3) command: gvim -p *.java

    Java Questions:
        4) The keyword static in regards to methods means that a member 
method can be accessed without requiring an instantiation of the class to
which it belongs. That is, you can call this method without creating the 
object which it belongs. For example, the static method in Math class to
get the absolute value of an integer is Math.abs(int x). You don't need
to create a Math object to use this method, but you can just call 
Math.abs(int x) to find the absolute value of integer x.
        5) I will suggest her to write all the shape classes with super/
sub relationships and the first super class is just shape. She can make 
this class to be an abstract class to have an abstract method draw(). Then
she can write the Circle, Square, etc. shapes as subclasses of Shape class.
Thus, she can write the draw() method in each shape class as override 
methods. This writing style is more convenient.
