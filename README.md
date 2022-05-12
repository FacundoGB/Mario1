----ESPAÑOL----
En este proyecto voy a llevar adelante la creacion de un 2D Game Engine en Java basado en Mario de 8bit.

Commit inicial: Creacion de ventana de juego con GLFW y OpenGL.

--------------------

----ENGLISH----
Int his project I'm going to learn how to deploy a 2D Game Engine in Java based on an 8 bit Mario.

Innitial commit: Cretion and deployment of the games window with GLFW and OpenGL.

---------------


Commit 2: Event Handlers and Event Listeners. Here I set the key and mouse listeners.
Notes: DX(horizontal) and DY(vertical) values, they represent the axis in which a pointer would move in a frame
They do not represent the absolute position. If a pointer moves from (0,0) to (2,1) DY would be 1 and DX would be 2.
They represent the DISTANCE ELAPSED between frames. In order to make sure they are always correct we must reset them at the end of every frame.

Commit 3: Scene Manager & Delta Time Variable. Delta Time is very important, we use this to let know the game know 
how much time has elapsed since the last frame, which we can use to time different animations or objects. The Scene Manager helps us separate our game logic from platform layer. The window is out interface between the game and our OS, and our scene is our game code specific stuff, nothing OS specific should live in the game code.

We use the function nanotime() given by java that gives us our time in nano seconds. if we flip the equation we gett how many seconds elapse in a nano second. When we think in time we think in the order of seconds (*eg fps).
We know that our Delta Time variable (DT) is the amount of time that happen in one frame (dt s/f) so to convertit to f/s we just flip it: 1/dt = f/s.