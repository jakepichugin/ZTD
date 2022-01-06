package com.main;

import com.badlogic.gdx.Audio;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;

public class Resources {
    // TODO: UI Elements
    static Texture bg = new Texture(Gdx.files.internal("bg_lab.png"));
    static Texture button_cannon = new Texture(Gdx.files.internal("CannonIcon.png"));
    static Texture button_cannon_fire = new Texture(Gdx.files.internal("FireCannonIcon.png"));
    static Texture button_cannon_super = new Texture(Gdx.files.internal("SuperCannonIcon.png"));
    static Texture button_cannon_double = new Texture(Gdx.files.internal("doubleCannonIcon.png"));
    static Texture button_cannon_laser = new Texture(Gdx.files.internal("laserCannonIcon.png"));
    static Texture button_cannon_mounted = new Texture(Gdx.files.internal("mountedCannonIcon.png"));
    static Texture button_wall = new Texture(Gdx.files.internal("WallIcon.png"));
    static Texture bucket = new Texture(Gdx.files.internal("bucketbutton.png"));
    static Texture button_play = new Texture(Gdx.files.internal("play.png"));
    static Texture button_pause = new Texture(Gdx.files.internal("pause.png"));
    static Texture button_start = new Texture(Gdx.files.internal("startButton.png"));
    static Texture button_close = new Texture(Gdx.files.internal("x.png"));
    static Texture button_exit = new Texture(Gdx.files.internal("exitButton.png"));
    static Texture locked = new Texture(Gdx.files.internal("locked.png"));
    static Texture selected = new Texture(Gdx.files.internal("border.png"));
    static Texture tooltip_bg = new Texture(Gdx.files.internal("ttbg.png"));
    static Texture red_bar = new Texture(Gdx.files.internal("red_bar.png"));
    static Texture green_bar = new Texture(Gdx.files.internal("green_bar.png"));
    static Texture wall = new Texture(Gdx.files.internal("Wall.png"));

    // TODO: Cannons
    static Texture cannon = new Texture(Gdx.files.internal("Cannon.png"));
    static Texture cannon_fire = new Texture(Gdx.files.internal("Firecannon.png"));
    static Texture cannon_super = new Texture(Gdx.files.internal("SuperCannon.png"));
    static Texture cannon_double = new Texture(Gdx.files.internal("doubleCannon.png"));
    static Texture cannon_laser = new Texture(Gdx.files.internal("laserCannon.png"));
    static Texture cannon_mounted = new Texture(Gdx.files.internal("mountedCannon.png"));

    static Texture water_bucket = new Texture(Gdx.files.internal("water-deffence.png"));

    // TODO: Zombies
    static Texture zombie = new Texture(Gdx.files.internal("Zombies.png"));
    static Texture zombie_fast = new Texture(Gdx.files.internal("Fastzombies.png"));
    static Texture zombie_dif = new Texture(Gdx.files.internal("DifZombies.png"));
    static Texture zombie_speedy = new Texture(Gdx.files.internal("speedy_zombie.png"));
    static Texture zombie_riot = new Texture(Gdx.files.internal("riotzombieBIG.png"));
    static Texture zombie_water = new Texture(Gdx.files.internal("WaterBlob.png"));



    // TODO: Bullets
    static Texture Bullet = new Texture(Gdx.files.internal("Bullet.png"));
    static Texture bullet_missile = new Texture(Gdx.files.internal("missile.png"));
    static Texture firebullet = new Texture(Gdx.files.internal("firebullet.png"));
    static Texture superbullet = new Texture(Gdx.files.internal("superbullet.png"));
    static Texture laserbullet = new Texture(Gdx.files.internal("laserbullet.png"));
    static Texture waterbullet = new Texture(Gdx.files.internal("Bullet.png"));


    // TODO: Sounds
    static Sound sfx_bullet = Gdx.audio.newSound(Gdx.files.internal("Bullet.mp3"));

    // TODO: Effets
    static Texture click_effect = new Texture(Gdx.files.internal("click_effect.png"));
    static Texture tap_effect = new Texture(Gdx.files.internal("tap_effect_2.png"));
    static Texture zombie_death = new Texture(Gdx.files.internal("zombie_death-2.png"));



}
