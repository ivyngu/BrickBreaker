/**
 * Name: Ivy Nguyen
 * Pennkey: ivyng
 * Execution: java GameBoard
 *
 * Description: creates the gameboard that sets up BrickBreaker on the screen,
 *              handles the interaction between platform, brick, and ball objects,
 *              and manages game state
**/

public class GameBoard {
    private Platform p;
    private Brick[][] bricks;
    private Ball b;
    private int lives;
    private int score;
    
    // constructor
    public GameBoard() {
        this.p = new Platform(0.5, 0.2);
        this.b = new Ball(0.5, 0.12, 0, 0, 0, 0);
        // create 2D array of bricks
        this.bricks = new Brick[6][7];
        // to keep track of px of brick
        double addRow = 0.15;
        for (int i = 0; i < bricks.length; i++) {
            // to keep track of py of brick
            double addCol = 0.3;
            for (int j = 0; j < bricks[i].length; j++) {
                bricks[i][j] = new Brick(addRow, addCol, generateStrength());
                addCol += 0.1;
            }
            addRow += 0.14;
        }
        this.lives = 6;
        this.score = 0;
    }
    
     /**
    * Input: none
    * Output: int strength of brick
    * Description: randomly assigns a brick its strength
    */
    private int generateStrength() {
        int strength;
        // for randomizing strength of brick
        double rand = Math.random();
        if (rand < 0.2) {
            strength = 3;
        }
        else if (rand < 0.6) {
            strength = 2; 
        }
        else if (rand < 1) {
            strength = 1;
        }
        else {
            // in the case that strength isn't initialized due to randomization
            // have to throw an exception
            throw new IllegalArgumentException("Strength wasn't initialized.");
        }
        return strength;
    }
    
    /**
    * Input: none
    * Output: none
    * Description: draws the board for the game
    */
    public void drawBoard() {
        PennDraw.clear(PennDraw.WHITE);
        // platform
        p.draw();
        // ball
        b.draw();
        // bricks
        for (int i = 0; i < bricks.length; i++) {
            for (int j = 0; j < bricks[i].length; j++) {
                bricks[i][j].draw();
            }
        }
        // wall
        PennDraw.setPenColor(PennDraw.GRAY);
        PennDraw.setPenRadius(0.1);
        PennDraw.line(0, 0, 0, 1);
        PennDraw.line(0, 1, 1, 1);
        PennDraw.line(1, 1, 1, 0);
        // lives + score
        PennDraw.setPenColor(0, 0, 0); 
        PennDraw.text(0.3, 0.97, "Lives:" + " " + this.lives);
        PennDraw.text(0.6, 0.97, "Score:" + " " + this.score);
    }
    
    /**
    * Input: none
    * Output: none
    * Description: updates the coordinates of the ball depending on what it hits
    */
    public void updateGame() {
        // if it hits the 2 sidewalls, update velocity
        if (b.impactSideWall()) {
            b.bounceHorizontal();
        }
        // if it hits the top wall, update velocity
        if (b.impactTopWall()) {
            b.bounceVertical();
        }
        // if it hits the platform, update velocity
        if (b.impact(p)) {
            b.bouncePlatform();
            // if user catches the ball, increase the score
            updateScore();
        }
        // if the ball hits the brick, update velocity
        for (int i = 0; i < bricks.length; i++) {
            for (int j = 0; j < bricks[i].length; j++) {
                if (b.impact(bricks[i][j]) && bricks[i][j].getStrength() > 0) {
                    b.bounceBrick();
                    // if user hits a brick, it loses strength & user get points
                    bricks[i][j].loseStrength();
                    updateScore();
                }
            }
        }
        // update the position of the ball
        b.move();
        return;
    }
    
    /**
    * Input: none
    * Output: none
    * Description: adds to one's score
    */
    public void updateScore() {
        score += 25;
    }
    
    /**
    * Input: none
    * Output: none
    * Description: updates lives of user
    */
    public void loseLife() {
        if (lives > 0) {
            lives--;
        }
    }
    
    /**
    * Input: char that user typed on keyboard
    * Output: none
    * Description: updates the platform's positions based on user input
    */
    public void updatePlatform(char keyTyped) {
        if (keyTyped == 'a') {
            p.moveLeft();
        }
        else if (keyTyped == 'd') {
            p.moveRight();
        }
    }
    
    /**
    * Input: none
    * Output: boolean that tells you whether the ball fell off the screen
    * Description: checks whether ball is still on the screen or not
    */
    public boolean checkOffScreen() {
        if (b.getPY() < 0) {
            return true;
        }
        return false;
    }
    
    /**
    * Input: none
    * Output: boolean whether user wins or not
    * Description: tells you whether user wins or not
    */
    public boolean checkWinner() {
        int sumStrength = 0;
        // if all the bricks have strength of 0, you have broken all of them
        for (int i = 0; i < bricks.length; i++) {
            for (int j = 0; j < bricks[i].length; j++) {
                sumStrength += bricks[i][j].getStrength();
            }
        }
        // if you've broken all the bricks w/ the lives you have, then you've won
        if (sumStrength <= 0 && this.lives > 0) {
            return true;
        }
        return false;
    }
    
    /**
    * Input: none
    * Output: boolean whether you lost or not
    * Description: tells you whether user lost yet
    */
    public boolean checkLoser() {
        // if the user has negative lives, they've lost
        if (lives <= 0) {
            return true;
        }
        return false;
    }
    
    /**
    * Input: none
    * Output: none
    * Description: changes the ball's velocity initially & gets it moving
    */
    public void releaseBall() {
        // releases ball on user input
        b.setInitialVelocity();
        b.move();
    }
   
    /**
    * Input: none
    * Output: none
    * Description: resets the ball & platform coordinates to their original state
    */
    public void reposition() {
        p.setPosition(0.5);
        b.setInitialPosition(0.5, 0.12); 
    }
    
     /**
    * Input: none
    * Output: int number of lives user has left
    * Description: returns the number of lives user has left in game
    */
    public int getLives() {
        return lives;
    }
}