package frontEnd;

import java.awt.Color;
import java.awt.Image;
import java.util.HashMap;

import backEnd.BombBox;
import backEnd.Box;
import backEnd.Cell;
import backEnd.Destination;
import backEnd.Empty;
import backEnd.Hero;
import backEnd.Position;

/**
 * 
 * @author  Patricio Reller, German Romarion, Gabriel Zanzotti
 *
 *Implements the image setter, each element calls the
 *method draw with an instance of this class as a 
 *parameter, their methods change the parameter with the
 *receptor to know the real type of the element and load
 *the images depending on which element image is being loaded.
 *
 */
public class DrawerSeter implements ImgSeter {
	Image colorableBoxCenter;
	
	/**
	 * loads the Box image according to the game status
	 */
	@Override
	public void draw(Box box, HashMap<String, Image> map, BoardPanel p) {
		Image pieceImg = boxCommonSettings(box,map);

		Position pos = box.getCell().getPosition();
		p.appendImage(pos.getRow(), pos.getCol(), colorableBoxCenter);
		p.appendImage(pos.getRow(), pos.getCol(), pieceImg);
		
	}

	/**
	 * loads the BombBox image according to the game status
	 */
	@Override
	public void draw(BombBox bb, HashMap<String, Image> map, BoardPanel p) {
		Image pieceImg = boxCommonSettings(bb,map);
		Integer i = bb.getLife();
		
		colorableBoxCenter = ImageUtils.drawString(colorableBoxCenter, i.toString(), Color.BLACK);
		Position pos = bb.getCell().getPosition();
		p.appendImage(pos.getRow(), pos.getCol(), colorableBoxCenter);
		p.appendImage(pos.getRow(), pos.getCol(), pieceImg);
	}

	/**
	 * loads the Hero image
	 */
	@Override
	public void draw(Hero hero, HashMap<String, Image> map, BoardPanel p) {
		Image pieceImg = map.get(hero.getClass().getName());
		Position pos = hero.getCell().getPosition();
		p.appendImage(pos.getRow(), pos.getCol(), pieceImg);
		
	}

	/**
	 * Empty should do nothing because when a cell has 
	 * Empty as a piece you have to see only the cell.
	 */
	@Override
	public void draw(Empty empty, HashMap<String, Image> map, BoardPanel p) {
		// do nothing as emptiness has no image
	}

	/**
	 * loads the Destination image
	 *
	 */
	@Override
	public void draw(Destination d, HashMap<String, Image> map, BoardPanel p) {
		Image cellImg = map.get(d.getClass().getName());
		Image colorableButtonCenter = map.get("buttonCenter");
		Position pos = d.getPosition();
		colorableButtonCenter = ImageUtils.colorize(colorableButtonCenter,  d.getColor());
		p.appendImage(pos.getRow(), pos.getCol(), cellImg);
		p.appendImage(pos.getRow(), pos.getCol(), colorableButtonCenter);

	}

	/**
	 * loads the Cell image according to the game status
	 */
	@Override
	public void draw(Cell c, HashMap<String, Image> map, BoardPanel p) {
		Image cellImg = map.get(c.getClass().getName());
		Position pos = c.getPosition();
		p.appendImage(pos.getRow(), pos.getCol(), cellImg);
	}
	
	/**
	 * 
	 * @param box
	 * 		Box or BombBox type to set the common image 
	 * 		characteristics.
	 * @param map
	 * 		map where to get the images
	 * @return
	 * 		returns the piece image because the colorable
	 * 		center is an instance variable
	 * 
	 */
	private Image boxCommonSettings(Box box, HashMap<String, Image> map){
		Image pieceImg = map.get(box.getClass().getName());
		colorableBoxCenter = map.get("boxCenter");
		colorableBoxCenter = ImageUtils.colorize(colorableBoxCenter,  box.getColor());
		if(box.arrived()){
			colorableBoxCenter = ImageUtils.increaseBrightness(colorableBoxCenter);
		}
		return pieceImg;
	}
}
