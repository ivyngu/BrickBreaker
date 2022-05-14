/**
 * Name: Ivy Nguyen
 * Pennkey: ivyng
 * Execution: java Platform
 *
 * Description: creates a platform that moves horizontally & catches the ball
**/

public class Platform {
    private double px; // platform's center of mass
    private double length;
    
    // constructor
    public Platform(double px, double length) {
        if (px > 0 && px < 1 && length > 0 && length < 0.5) {
            this.px = px;
            this.length = length;
        }
    }
    
     /**
    * Input: none
    * Output: none
    * Description: draws the platform on the screen
    */
    public void draw() {
        PennDraw.setPenColor(70, 71, 78);
        PennDraw.setPenRadius(0.01);
        PennDraw.line(this.px - (this.length / 2), 0.1, this.px + (this.length / 2),
                      0.1);
    }
    
     /**
    * Input: none
    * Output: double px
    * Description: getter function for returning px of platform
    */
    public double getPX() {
        return px;
    }
    
     /**
    * Input: none
    * Output: double length
    * Description: getter function for returning length of platform
    */
    public double getLength() {
        return length;
    }
    
     /**
    * Input: none
    * Output: double representing where px would be if the platform moved left
    * Description: calculates projected px when platform moves left
    */
    private double projectMoveLeft() {
        double projectedPX = px;
        projectedPX -= 0.1;
        return projectedPX;
    }
    
    /**
    * Input: none
    * Output: double representing where px would be if the platform moved right
    * Description: calculates projected px when platform moves right
    */
    private double projectMoveRight() {
        double projectedPX = px;
        projectedPX += 0.1;
        return projectedPX;
    }
    
    /**
    * Input: double of projected position when platform moves left or right
    * Output: boolean whether platform position would be in bounds of screen with
    *         movement
    * Description: sees if platform movement would result in out of bounds placement
    *              on screen
    */
    private boolean projectedInBounds(double projectedPX) {
        // check if projected position is in range of screen
        if (projectedPX + (length / 2) < 0.9 && projectedPX - (length / 2) > 0.09) {
            return true;
        }
        return false;
    }

    /**
    * Input: none
    * Output: none
    * Description: actually moves platform right if projected position is valid
    */
    public void moveRight() {
        // if the projected position is in range of screen, the platform can move
        if (projectedInBounds(projectMoveRight())) {
            px += 0.1;
        }
    }
    
     /**
    * Input: none
    * Output: none
    * Description: actually moves platform left if projected position is valid
    */
    public void moveLeft() {
        // if the projected position is in range of screen, the platform can move
        if (projectedInBounds(projectMoveLeft())) {
            px -= 0.1;
        }
    }
   
     /**
    * Input: double new px, where the new px should be
    * Output: none
    * Description: setter function for a new px
    */
    public void setPosition(double newPX) {
        px = newPX;
    }
    
     /**
    * Input: none
    * Output: string containing px and length of platform
    * Description: prints out px and length
    */
    public String toString() {
        String output = "";
        output += this.px + "," + this.length; 
        return output;
    }
}