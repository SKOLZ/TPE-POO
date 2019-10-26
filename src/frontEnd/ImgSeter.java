package frontEnd;

import java.awt.Image;
import java.util.HashMap;

import backEnd.BombBox;
import backEnd.Box;
import backEnd.Cell;
import backEnd.Destination;
import backEnd.Empty;
import backEnd.Hero;

/**
 * 
 * @author  Patricio Reller, German Romarion, Gabriel Zanzotti
 * 
 * Interface used to set the images according to its cell/piece
 * subclass.
 *
 */
public interface ImgSeter {
	
	/**
	 * 
	 * @param box
	 * 		element to be drawn
	 * @param map
	 * 		place to get the element image
	 * @param p
	 * 		panel where to append the images
	 * 
	 */
	void draw(Box box, HashMap<String, Image> map, BoardPanel p);
	
	/**
	 * 
	 * @param bb
	 * 		element to be drawn
	 * @param map
	 * 		place to get the element image
	 * @param p
	 * 		panel where to append the images
	 * 
	 */
	void draw(BombBox bb, HashMap<String, Image> map, BoardPanel p);
	
	/**
	 * 
	 * @param hero
	 * 		element to be drawn
	 * @param map
	 * 		place to get the element image
	 * @param p
	 * 		panel where to append the images
	 * 
	 */
	void draw(Hero hero, HashMap<String, Image> map, BoardPanel p);
	
	/**
	 * 
	 * @param empty
	 * 		element to be drawn
	 * @param map
	 * 		place to get the element image
	 * @param p
	 * 		panel where to append the images
	 * 
	 */
	void draw(Empty empty, HashMap<String, Image> map, BoardPanel p);
	
	/**
	 * 
	 * @param d
	 * 		element to be drawn
	 * @param map
	 * 		place to get the element image
	 * @param p
	 * 		panel where to append the images
	 * 
	 */
	void draw(Destination d, HashMap<String, Image> map, BoardPanel p);
	
	/**
	 * 
	 * @param c
	 * 		element to be drawn
	 * @param map
	 * 		place to get the element image
	 * @param p
	 * 		panel where to append the images
	 * 
	 */
	void draw(Cell c, HashMap<String, Image> map, BoardPanel p);
	
}
