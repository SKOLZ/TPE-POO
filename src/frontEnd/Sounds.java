package frontEnd;

import java.io.*;

/**
 * @author Patricio Reller, German Romarion, Gabriel Zanzotti
 * 
 *         Enumerates all sounds to be used with their paths. Utilizes the
 *         implementation SoundManager to play sounds.
 */
public enum Sounds {
	MENUMUSIC("music" + File.separator + "menumusic.wav"), WIN("music"
			+ File.separator + "win.wav"), LOSE("music" + File.separator
			+ "lose.wav");

	private SoundManager sm;

	/**
	 * Initializes sounds creating a sound manager for each one.
	 */
	Sounds(String s) {
		sm = new SoundManager(s);
	}
	/**
	 * Calls the sound manager to play the sound.
	 */
	public void play() {
		sm.play();
	}
	/**
	 * Calls the sound manager to stop the sound.
	 */
	public void stop() {
		sm.stop();
	}
	
	public static void stopAll(){
		for (Sounds s : Sounds.values()){
			s.stop();
		}
	}
}