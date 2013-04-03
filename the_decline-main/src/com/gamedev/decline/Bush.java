package com.gamedev.decline;

import com.badlogic.gdx.graphics.Texture;


public class Bush extends Item
{
  GlobalSingleton gs = GlobalSingleton.getInstance();
  
  final static int BUSH_SIZE = 100;
  
  public Bush(Texture texture) {
	super(texture, 0, 0,BUSH_SIZE);
}
  
}
