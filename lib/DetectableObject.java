/**
 * The DetectableObject interface is responsible for making an object detectable.
 * This means that it can check whether or not a certain point is inside the shape.
 * It requires a class to create an isWithin method. 
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

public interface DetectableObject {
    boolean isWithin(int x, int y);
}
