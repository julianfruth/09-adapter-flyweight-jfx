package de.thro.inf.prg3.a09.model;

import de.thro.inf.prg3.a09.resource.ResourceLoader;
import javafx.scene.image.Image;

public class FxImageLoaderAdapter {
	private ResourceLoader<Image> resourceLoader;

	public FxImageLoaderAdapter() {
		this.resourceLoader = new ResourceLoader<>(Image::new);
	}

	public Image loadImage(String path){
		return resourceLoader.loadResource(getClass().getClassLoader(), path);
	}

	public Image loadImage(String path, ClassLoader clazzLoader){
	return resourceLoader.loadResource(clazzLoader, path);
	}
}
