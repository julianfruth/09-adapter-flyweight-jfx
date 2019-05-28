package de.thro.inf.prg3.a09.model;

import com.sun.javafx.fxml.builder.JavaFXImageBuilder;
import de.thro.inf.prg3.a09.model.empire.TieBomber;
import de.thro.inf.prg3.a09.model.empire.TieFighter;
import de.thro.inf.prg3.a09.model.empire.TieInterceptor;
import de.thro.inf.prg3.a09.model.rebellion.AWing;
import de.thro.inf.prg3.a09.model.rebellion.XWing;
import de.thro.inf.prg3.a09.model.rebellion.YWing;
import de.thro.inf.prg3.a09.resource.ResourceLoader;
import de.thro.inf.prg3.a09.util.NameGenerator;
import javafx.scene.image.Image;

import java.util.HashMap;
import java.util.Random;

/**
 * Factory to create new fighters
 * Creates random fighters
 *
 * @author Peter Kurfer
 */
public final class FighterFactory {

	private static final int NumberOfKnownFighterTypes = 6;
	private final Random random;
	private final NameGenerator nameGenerator;
	private final FxImageLoaderAdapter imageResourceLoader;
	private final HashMap<String, Image> flyweightCache;

	public FighterFactory() {
		nameGenerator = new NameGenerator();
		random = new Random();
		imageResourceLoader = new FxImageLoaderAdapter();
		flyweightCache = new HashMap<>();
	}

	private Image loadIfNotInCache(String path){
		if(!flyweightCache.containsKey(path)){
			var img = imageResourceLoader.loadImage(path);
			flyweightCache.put(path, img);
		}
		return flyweightCache.get(path);
	}

	/**
	 * Create a random Fighter
	 *
	 * @implNote the method has an implementation flaw because it always loads the fighters image
	 * @return a new Fighter instance
	 */
	public Fighter createFighter() {
		switch (random.nextInt(NumberOfKnownFighterTypes)) {
			case 0:
				return new AWing(nameGenerator.generateName(), loadIfNotInCache("fighter/awing.jpg"));
			case 1:
				return new XWing(nameGenerator.generateName(), loadIfNotInCache("fighter/xwing.jpg"));
			case 2:
				return new YWing(nameGenerator.generateName(), loadIfNotInCache("fighter/ywing.jpg"));
			case 3:
				return new TieBomber(nameGenerator.generateName(), loadIfNotInCache( "fighter/tiebomber.jpg"));
			case 4:
				return new TieFighter(nameGenerator.generateName(), loadIfNotInCache("fighter/tiefighter.jpg"));
			default:
				return new TieInterceptor(nameGenerator.generateName(), loadIfNotInCache("fighter/tieinterceptor.jpg"));
		}
	}
}
