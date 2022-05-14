/**
 * Name: Ivy Nguyen
 * Pennkey: ivyng
 * Execution: java Brick
 *
 * Description: creates bricks that the ball has to eliminate
 **/

public class Brick {
    private double px; // x-center
    private double py; // y-center
    private int strength; // how strong brick is
    private double halfWidth;
    private double halfLength;
    
    // constructor
    public Brick(double px, double py, int strength) {
        this.px = px;
        this.py = py;
        this.strength = strength;
        this.halfWidth = 0.06;
        this.halfLength = 0.03;
    }
    
     /**
    * Input: none
    * Output: none
    * Description: draws bricks according to their strengths
    */
    public void draw() {
        if (this.strength == 1) {
            PennDraw.setPenColor(145, 239, 145);    
        }
        else if (this.strength == 2) {
            PennDraw.setPenColor(252, 255, 131);
        }
        else if (this.strength == 3) {
            PennDraw.setPenColor(250, 115, 124);
        }
        else if (this.strength == 0) {
            return;
        }
        PennDraw.filledRectangle(this.px, this.py, this.halfWidth, this.halfLength);
    }
    
     /**
    * Input: none
    * Output: double px of brick
    * Description: getter function for px of brick
    */
    public double getPX() {
        return this.px;
    }
    
     /**
    * Input: none
    * Output: double py of brick
    * Description: getter function for py of brick
    */
    public double getPY() {
        return this.py;
    }
    
     /**
    * Input: none
    * Output: double halfWidth of brick
    * Description: getter function for halfWidth of brick
    */
    public double getHalfWidth() {
        return this.halfWidth;
    }
    
     /**
    * Input: none
    * Output: double halfLength of brick
    * Description: getter function for halfLength of brick
    */
    public double getHalfLength() {
        return this.halfLength;
    }
    
     /**
    * Input: none
    * Output: double strength of brick (how many times it takes to break)
    * Description: getter function for strength of brick
    */
    public double getStrength() {
        return this.strength;
    }
    
     /**
    * Input: none
    * Output: none
    * Description: decreases the strength of a brick if it gets hit by ball
    */
    public void loseStrength() {
        if (this.strength > 0) {
            this.strength--;
        }
        return;
    }
}