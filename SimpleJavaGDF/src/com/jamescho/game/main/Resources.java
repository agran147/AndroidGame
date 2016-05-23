package com.jamescho.game.main;

import java.applet.Applet;
import java.applet.AudioClip;
import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;

import com.jamescho.framework.animation.Animation;
import com.jamescho.framework.animation.Frame;

public class Resources {
    
    public static BufferedImage welcome, iconimage,stop,
    run0,run1,run2,run3,run4,run5,selector,background1;
    public static BufferedImage mrun1,mrun2,mrun3,mrun4,mrun5;
    public static Color skyBlue;
    public static Color white;
    public static BufferedImage title1, title2,startBtn,exitBtn,intro,introback,introplayicon,waterIcon,waterHole1;
    public static Animation runAnim,runAnim2;
    
    public static void load() {
    	startBtn = loadImage("startBtn.jpg");
    	exitBtn = loadImage("exitBtn.jpg");
    	title1 = loadImage("title1.png");
    	title2 = loadImage("title2.png");
    	introplayicon = loadImage("introplayicon.png");
        intro = loadImage("intro.png");
        introback = loadImage("introback.png");
        background1 = loadImage("background1.png");
        stop = loadImage("stop.png");
        welcome = loadImage("welcome.png");
        iconimage = loadImage("iconimage.png");
        run0 = loadImage("run_anim0.png");
        run1 = loadImage("run_anim1.png");
        run2 = loadImage("run_anim2.png");
        run3 = loadImage("run_anim3.png");
        run4 = loadImage("run_anim4.png");
        run5 = loadImage("run_anim5.png");
        mrun1 = loadImage("runmonster_anim1.png");
        mrun2 = loadImage("runmonster_anim2.png");
        mrun3 = loadImage("runmonster_anim3.png");
        mrun4 = loadImage("runmonster_anim4.png");
        mrun5 = loadImage("runmonster_anim5.png");
        waterIcon = loadImage("ui_Energy-120x120.png");
        selector = loadImage("selector.png");
        skyBlue = new Color(208,244,247);
        waterHole1 = loadImage("WaterHole.png");
        Frame f0 = new Frame(run0,.1f);
        Frame f1 = new Frame(run1,.1f);
        Frame f2 = new Frame(run2,.1f);
        Frame f3 = new Frame(run3,.1f);
        Frame f4 = new Frame(run4,.1f);
        Frame f5 = new Frame(run5,.1f);
        Frame m1 = new Frame(mrun1,.1f);
        Frame m2 = new Frame(mrun2,.1f);
        Frame m3 = new Frame(mrun3,.1f);
        Frame m4 = new Frame(mrun4,.1f);
        Frame m5 = new Frame(mrun5,.1f);
        runAnim = new Animation(f0,f1,f2,f3,f4,f5);
        runAnim2 = new Animation(m1,m2,m3,m4,m5,m3,m2);
    }
    
    private static AudioClip loadSound(String filename) {
        URL fileURL = Resources.class.getResource("/resources/" + filename);
        return Applet.newAudioClip(fileURL);
    }
    
    private static BufferedImage loadImage(String filename) {
        BufferedImage img = null;
        try {
            img = ImageIO.read(Resources.class.getResourceAsStream("/resources/" + filename));
        } catch (IOException e) {
            System.out.println("Error while reading: " + filename);
            e.printStackTrace();
        }
        return img;
    }
}