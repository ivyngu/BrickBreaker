/**
 * Name: Ivy Nguyen
 * Pennkey: ivyng
 * Execution: java BrickBreaker
 *
 * Description: handles animation and user input for BrickBreaker game
**/

public class BrickBreaker {
    public static void main(String[] args) {
        while (true) {
            // these are all initial conditions for game:
            // 1. create a new gameboard & draw it
            GameBoard g = new GameBoard();
            g.drawBoard();
            // 2. create variable to wait on user input
            boolean bufferGame = true;
            // 3. create variable to see whether the game needs to restart
            boolean continueGame = true;
            // 4. instructions for user to start
            PennDraw.setPenColor(0, 0, 0);
            PennDraw.text(0.5, 0.5, "Press any key to start.");
            PennDraw.enableAnimation(100);
            
            // 5. wait for user input to start
            while (bufferGame) {
                // if they press a button, start the game
                if (PennDraw.hasNextKeyTyped()) {
                    g.releaseBall();
                    g.drawBoard();
                    bufferGame = false;
                }
            }
            
            // conditions during the game:
            while (continueGame) {
                // 1. check to see if the user lost or not yet
                if (g.checkLoser()) {
                    // if they are, wait for user input to restart
                    bufferGame = true;
                    while (bufferGame) {
                        PennDraw.setPenColor(0, 0, 0); 
                        PennDraw.text(0.5, 0.5, 
                                      "You lost! Press any key to restart.");
                        // once they press a button, restart the game
                        if (PennDraw.hasNextKeyTyped()) {
                            bufferGame = false;
                        }
                        PennDraw.advance();
                    }
                    continueGame = false;   
                }
                // 2. check to see if user has won or not yet
                if (g.checkWinner()) {
                    // if they have, wait for user input to restart the game
                    bufferGame = true;
                    g.drawBoard();
                    while (bufferGame) {
                        PennDraw.setPenColor(0, 0, 0); 
                        PennDraw.text(0.5, 0.5, 
                                      "You win! Press any key to restart.");
                        // once they press a button, restart the game
                        if (PennDraw.hasNextKeyTyped()) {
                            bufferGame = false;
                        }
                        PennDraw.advance();
                    }
                    continueGame = false;
                }
                // 3. check to see whether the ball has fallen off the screen
                if (g.checkOffScreen()) {
                    bufferGame = true;
                    // if they do, they lose a life
                    g.loseLife();                    
                    // if so, wait for user input to continue only if they haven't
                    // won or lost yet
                    while (bufferGame && !g.checkLoser() && !g.checkWinner()) {
                        g.reposition();
                        g.drawBoard();
                        PennDraw.setPenColor(0, 0, 0); 
                        PennDraw.text(0.5, 0.5, "Press any key to continue.");
                        if (PennDraw.hasNextKeyTyped()) {
                            g.releaseBall();
                            g.drawBoard();
                            bufferGame = false;    
                        }
                    PennDraw.advance(); 
                    }
                }
                // 4. update the game as it continues
                // if they press a key, move the platform & update coordinates
                // of ball too
                if (PennDraw.hasNextKeyTyped()) {
                    char keyTyped = PennDraw.nextKeyTyped();
                    g.updatePlatform(keyTyped);
                }
                g.drawBoard();
                g.updateGame();
                PennDraw.advance();
            }
            // reset to begin the game again
            continueGame = true;
            PennDraw.advance();
        }
    } 
}