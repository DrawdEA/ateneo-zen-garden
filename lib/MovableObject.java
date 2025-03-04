/**
 * The Moveable object lets the object be moved.
 * This may be through its x-axis, y-axis, or both at the same time.
 * 
 * @author Edward Joshua M. Diesta (241571), Charles Joshua T. Uy (244644)
 * @version March 3, 2025
 * 
 * We have not discussed the Java language code in our program 
 * with anyone other than our instructor or the teaching assistants 
 * assigned to this course.
 * 
 * We have not used Java language code obtained from another student, 
 * or any other unauthorized source, either modified or unmodified.
 * 
 * If any Java language code or documentation used in our program 
 * was obtained from another source, such as a textbook or website, 
 * that has been clearly noted with a proper citation in the comments 
 * of our program.
 */

 package lib;

 public interface MovableObject {
    /**
     * Moves the x and y values of the object.
     * 
     * @param x x-value of the location being set to
     * @param y y-value of the location being set to
     */
    public void setXY(int x, int y);

    /**
     * Moves the x value of the object.
     * 
     * @param x x-value of the location being set to
     */
    public void setX(int x);

    /**
     * Moves the x value of the object.
     * 
     * @param y y-value of the location being set to
     */
    public void setY(int y);
 }
 