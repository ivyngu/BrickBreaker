/**
 * Name: Ivy Nguyen
 * Pennkey: ivyng
 * Execution: java Ball
 *
 * Description: creates a ball that bounces & interacts with bricks, walls, and the
 *              platform
**/

public class Ball {
    // some fields for ball
    private double px;
    private double py;
    private double vx;
    private double vy;
    private double ax;
    private double ay;
    
    // constructor
    public Ball(double px, double py, double vx, double vy, double ax, double ay) {
        this.px = px;
        this.py = py;
        this.vx = vx;
        this.vy = vy;
        this.ax = ax;
        this.ay = ay;
    }
    
     /**
    * Input: none
    * Output: none
    * Description: draws ball on screen
    */
    public void draw() {
        PennDraw.setPenColor(145, 160, 239);
        PennDraw.filledCircle(this.px, this.py, 0.02);
    }

     /**
    * Input: none
    * Output: double py of ball
    * Description: getter function for py of ball
    */
    public double getPY() {
        return py;
    }
    
     /**
    * Input: none
    * Output: none
    * Description: set initial position of ball when the game resets
    */
    public void setInitialPosition(double newPX, double newPY) {
        px = newPX;
        py = newPY;
    }
    
    /**
    * Input: none
    * Output: none
    * Description: sets the initial velocity of the ball when it starts moving
    */
    public void setInitialVelocity() {
        vx = 0.005;
        vy = 0.005;
    }
    
     /**
    * Input: none
    * Output: boolean whether ball hits the top wall or not
    * Description: tells if ball hit top wall or not
    */
    public boolean impactTopWall() {
        // if the ball's y position is here, it's hitting the wall
        if (py >= 0.9) {
            return true;
        }
        return false;
    }
    
    /**
    * Input: none
    * Output: boolean whether ball hits the side wall or not
    * Description: tells if ball hit side wall or not
    */
    public boolean impactSideWall() {
        // if the ball's x position is between this range, it's hitting the side wall
        if (px <= 0.1 || px >= 0.9) {
            return true;
        }
        return false;
    }
    
     /**
    * Input: Brick object
    * Output: boolean whether ball hits the brick object or not
    * Description: tells if ball hit brick object or not
    */
    public boolean impact(Brick b) {
        // if ball's px and py positions are in range of brick's positions, 
        // it's hitting the brick
        if (px >= (b.getPX() - b.getHalfWidth()) && 
            px <= (b.getPX() + b.getHalfWidth()) && 
            py >= (b.getPY() - b.getHalfLength()) && 
            py <= (b.getPY()) + b.getHalfLength()) {
            return true;
        }
        return false;
    }
    
    /**
    * Input: platform object
    * Output: boolean whether ball hits the platform object or not
    * Description: tells if ball hit platform object or not
    */
    public boolean impact(Platform p) {
        // if ball's px and py positions are in range of platform's positions,
        // it is hitting the platform
        if (px >= (p.getPX() - (p.getLength() / 2)) && 
            px <= (p.getPX() + (p.getLength() / 2)) && 
            py >= 0.1 && py <= 0.12) {
            return true;
        }
        return false;
    }
    
    /**
    * Input: none
    * Output: none
    * Description: changes only the velocity in x direction of ball & lets it bounce
    */
    public void bounceHorizontal() {
        // change sign to bounce
        vx = -vx;
        // offset position so it doesn't get stuck when bouncing
        if (px < 0.5) {
            px += 0.0001;
        }
        else {
            px -= 0.0001;
        }
        py += 0.00001;
    }
    
   /**
    * Input: none
    * Output: none
    * Description: changes only the velocity in y direction of ball & lets it bounce
    */
    public void bounceVertical() {
        // change sign to bounce
        vy = -vy;
        // offset position so it doesn't get stuck when bouncing
        py -= 0.0001;
        px += 0.0001;
    }

    /**
    * Input: none
    * Output: none
    * Description: changes acceleration of ball when it hits a brick & updates its
    *              velocity
    */
    public void bounceBrick() {
        // set acceleration when it hits brick
        ax = 0.002 * px;
        ay = 0.002 * py;
        // update velocity's magnitude
        vx += ax;
        vy += ay;
        // change signs of velocity so it bounces
        bounceHorizontal();
        bounceVertical();
    }
    
      /**
    * Input: none
    * Output: none
    * Description: changes acceleration of ball when it hits the platform & updates
    *              its velocity
    */
    public void bouncePlatform() {
        // set acceleration when it hits platform
        ax = 0.001;
        ay = 0.001;
        // update velocity
        vx += ax;
        vy += ay;
        // offset position so it doesn't get stuck on platform
        px += 0.01;
        py += 0.01;
        // change signs so the ball bounces
        bounceHorizontal();
        bounceVertical();
    }
       
    /**
    * Input: none
    * Output: none
    * Description: updates position of ball as its velocity changes
    */
    public void move() {
        px += vx;
        py += vy; 
    }

     /**
    * Input: none
    * Output: String output containing fields of ball
    * Description: prints out ball's fields
    */
    public String toString() {
        String output = "";
        output += this.px + "," + this.py + "," + this.vx + "," + this.vy + "," + 
            this.ax + "," + this.ay; 
        return output;
    }
}