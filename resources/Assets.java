/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.angrygoose.aqg3.resources;

import com.angrygoose.aqg3.utilities.Utility;
import java.awt.image.BufferedImage;
import java.io.File;

/**
 *
 * @author angrygoose
 */
public class Assets {
    //backgrounds
    public static BufferedImage storyBgr, highlandsBgr, hillsBgr;

    public static void init(){

        //storyInterface
        storyBgr = Utility.loadImage(new File("res/img/storyBgr.png"));

        //backgrounds
        highlandsBgr = Utility.loadImage(new File("res/img/highlandsBgr.png"));
        hillsBgr = Utility.loadImage(new File("res/img/hillsBgr.png"));
    }
}
