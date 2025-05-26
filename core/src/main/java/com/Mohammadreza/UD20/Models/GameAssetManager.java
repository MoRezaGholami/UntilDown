package com.Mohammadreza.UD20.Models;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;

import java.util.ArrayList;

public class GameAssetManager {
    private static GameAssetManager gameAssetManager;
    public static Skin skin = new Skin(Gdx.files.internal("skin/freezing-ui.json"));
    public static Music music = Gdx.audio.newMusic(Gdx.files.internal("musics/Bullet.mp3"));
    public static Music goodThingsGoMusic = Gdx.audio.newMusic(Gdx.files.internal("musics/GoodThingsGo.mp3"));
    public static Music KnowMusic = Gdx.audio.newMusic(Gdx.files.internal("musics/Know.mp3"));
    public static String cursor = "Cursor.png";
    public static Texture cursorTexture = new Texture(cursor);

    public static String character1_idle0 = "1/Idle_0.png";
    public static String character1_idle1 = "1/Idle_1.png";
    public static String character1_idle2 = "1/Idle_2.png";
    public static String character1_idle3 = "1/Idle_3.png";
    public static String character1_idle4 = "1/Idle_4.png";
    public static String character1_idle5 = "1/Idle_5.png";
    public static Texture character1_idle0_tex = new Texture(character1_idle0);
    public static Texture character1_idle1_tex = new Texture(character1_idle1);
    public static Texture character1_idle2_tex = new Texture(character1_idle2);
    public static Texture character1_idle3_tex = new Texture(character1_idle3);
    public static Texture character1_idle4_tex = new Texture(character1_idle4);
    public static Texture character1_idle5_tex = new Texture(character1_idle5);
    public static ArrayList<Texture> characterTextureList = new ArrayList<>();
    public static Animation<Texture> character1_idle_frames = new Animation<>(0.1f, character1_idle0_tex, character1_idle1_tex, character1_idle2_tex, character1_idle3_tex, character1_idle4_tex, character1_idle5_tex);
    //character 2 :

    public static String character2_idle0 = "2/1.png";
    public static String character2_idle1 = "2/2.png";
    public static String character2_idle2 = "2/3.png";
    public static String character2_idle3 = "2/4.png";
    public static String character2_idle4 = "2/5.png";
    public static String character2_idle5 = "2/6.png";
    public static Texture character2_idle0_tex = new Texture(character2_idle0);
    public static Texture character2_idle1_tex = new Texture(character2_idle1);
    public static Texture character2_idle2_tex = new Texture(character2_idle2);
    public static Texture character2_idle3_tex = new Texture(character2_idle3);
    public static Texture character2_idle4_tex = new Texture(character2_idle4);
    public static Texture character2_idle5_tex = new Texture(character2_idle5);
    public static Animation<Texture> character2_idle_frames = new Animation(0.1f, character2_idle0_tex , character2_idle1_tex , character2_idle2_tex , character2_idle3_tex , character2_idle4_tex , character2_idle5_tex);

    //character 3 :

    public static String character3_idle0 = "3/1.png";
    public static String character3_idle1 = "3/2.png";
    public static String character3_idle2 = "3/3.png";
    public static String character3_idle3 = "3/4.png";
    public static String character3_idle4 = "3/5.png";
    public static Texture character3_idle0_tex = new Texture(character3_idle0);
    public static Texture character3_idle1_tex = new Texture(character3_idle1);
    public static Texture character3_idle2_tex = new Texture(character3_idle2);
    public static Texture character3_idle3_tex = new Texture(character3_idle3);
    public static Texture character3_idle4_tex = new Texture(character3_idle4);
    public static Animation<Texture> character3_idle_frames = new Animation(0.1f , character3_idle0_tex , character3_idle1_tex , character3_idle2_tex , character3_idle3_tex , character3_idle4_tex);

    //character  4 :

    public static String character4_idle0 = "4/1.png";
    public static String character4_idle1 = "4/2.png";
    public static String character4_idle2 = "4/3.png";
    public static String character4_idle3 = "4/4.png";
    public static String character4_idle4 = "4/5.png";
    public static String character4_idle5 = "4/6.png";
    public static Texture character4_idle0_tex = new Texture(character4_idle0);
    public static Texture character4_idle1_tex = new Texture(character4_idle1);
    public static Texture character4_idle2_tex = new Texture(character4_idle2);
    public static Texture character4_idle3_tex = new Texture(character4_idle3);
    public static Texture character4_idle4_tex = new Texture(character4_idle4);
    public static Texture character4_idle5_tex = new Texture(character4_idle5);
    public static Animation<Texture> getCharacter4_idle_frames = new Animation(0.1f , character4_idle0_tex , character4_idle1_tex ,  character4_idle2_tex , character4_idle3_tex , character4_idle4_tex , character4_idle5_tex);

    public static String character5_idle0 = "Dasher/0.png";
    public static String character5_idle1 = "Dasher/1.png";
    public static String character5_idle2 = "Dasher/2.png";
    public static String character5_idle3 = "Dasher/3.png";
    public static String character5_idle4 = "Dasher/4.png";
    public static String character5_idle5 = "Dasher/5.png";
    public static Texture character5_idle0_tex = new Texture(character5_idle0);
    public static Texture character5_idle1_tex = new Texture(character5_idle1);
    public static Texture character5_idle2_tex = new Texture(character5_idle2);
    public static Texture character5_idle3_tex = new Texture(character5_idle3);
    public static Texture character5_idle4_tex = new Texture(character5_idle4);
    public static Texture character5_idle5_tex = new Texture(character5_idle5);
    public static Animation<Texture> dasherAnimation = new Animation(0.1f , character5_idle0_tex , character5_idle1_tex ,character5_idle2_tex ,character5_idle3_tex , character5_idle4_tex , character5_idle5_tex  );

    //Revolver Skin and Reload

    public static String revolver = "smg/Revolver/RevolverStill.png";
    public static String revolverReload0 = "smg/Revolver/0.png";
    public static String revolverReload1 = "smg/Revolver/1.png";
    public static String revolverReload2 = "smg/Revolver/2.png";
    public static String revolverReload3 = "smg/Revolver/3.png";
    public static Texture revolverReload1_tex = new Texture(revolverReload1);
    public static Texture revolverReload2_tex = new Texture(revolverReload2);
    public static Texture revolverReload3_tex = new Texture(revolverReload3);
    public static Texture revolverReload0_tex = new Texture(revolverReload0);
    public static Texture revolverTexture = new Texture(revolver);

    public static Animation<Texture> revolverReloadAnimation = new Animation<>(0.2f ,revolverReload0_tex , revolverReload1_tex , revolverReload2_tex , revolverReload3_tex );

    //Smg Skin and Reload

    public static String smg = "smg/SMGStill.png";
    public static String smgReload0 = "smg/0.png";
    public static String smgReload1 = "smg/1.png";
    public static String smgReload2 = "smg/2.png";
    public static String smgReload3 = "smg/3.png";
    public static Texture smgReload1_tex = new Texture(smgReload1);
    public static Texture smgReload2_tex = new Texture(smgReload2);
    public static Texture smgReload3_tex = new Texture(smgReload3);
    public static Texture smgReload0_tex = new Texture(smgReload0);
    public static Texture smgTexture = new Texture(smg);
    public static Animation<Texture> smgReloadAnimation = new Animation(0.2f , smgReload0_tex , smgReload1_tex, smgReload2_tex , smgReload3_tex );


    //smgDual Skin and Reload :
    public static String smgDouble = "smg/T_DualSMGs_Icon.png";
    public static Texture smgDoubleTex = new Texture(smgDouble);
    //smgDual reload is the same with smg.


    public static String brainMonster = "Brain Monster/BrainMonster_0.png";
    public static Texture brainMonsterTex = new Texture(brainMonster);
    public static String brainMonster1 = "Brain Monster/BrainMonster_1.png";
    public static String brainMonster2 = "Brain Monster/BrainMonster_2.png";
    public static String brainMonster3 = "Brain Monster/BrainMonster_3.png";
    public static Texture brainMonsterTex1 = new Texture(brainMonster1);
    public static Texture brainMonsterTex2 = new Texture(brainMonster2);
    public static Texture brainMonsterTex3 = new Texture(brainMonster3);
    public static Animation<Texture> brainMonsterAnimation= new Animation(0.3f , brainMonsterTex , brainMonsterTex1 , brainMonsterTex2 , brainMonsterTex3 );


    public static String bullet = "bullet.png";

    public static String deathFx0 = "DeathFx/DeathFx_0.png";
    public static String deathFx1 = "DeathFx/DeathFx_1.png";
    public static String deathFx2 = "DeathFx/DeathFx_2.png";
    public static String deathFx3 = "DeathFx/DeathFx_3.png";
    public static Texture deathFxTex = new Texture(deathFx0);
    public static Texture deathFxTex1 = new Texture(deathFx1);
    public static Texture deathFxTex2 = new Texture(deathFx2);
    public static Texture deathFxTex3 = new Texture(deathFx3);
    public static Animation<Texture> deathAnimation = new Animation(0.2f , deathFxTex , deathFxTex1 , deathFxTex2 , deathFxTex3 );


    public static String WingedMonster0 = "WingedMonster/WingedMonster_0.png";
    public static String WingedMonster1 = "WingedMonster/WingedMonster_1.png";
    public static String WingedMonster2 = "WingedMonster/WingedMonster_2.png";
    public static String WingedMonster3 = "WingedMonster/WingedMonster_3.png";
    public static String WingedMonster4 = "WingedMonster/WingedMonster_4.png";
    public static Texture wingedMonsterTex0 = new Texture(WingedMonster0);
    public static Texture wingedMonsterTex1 = new Texture(WingedMonster1);
    public static Texture wingedMonsterTex2 = new Texture(WingedMonster2);
    public static Texture wingedMonsterTex3 = new Texture(WingedMonster3);
    public static Texture wingedMonsterTex4 = new Texture(WingedMonster4);
    public static Animation<Texture> wingedMonsterAnimation = new Animation(0.3f , wingedMonsterTex0 , wingedMonsterTex1 , wingedMonsterTex2 , wingedMonsterTex3 , wingedMonsterTex4 );
    public static String WingedBullet0 = "WingedMonster_Em.png";
    public static Texture wingedBulletTex = new Texture(WingedBullet0);


    public static String treeMonster0 = "TreeMonster/T_TreeMonster_0.png";
    public static String treeMonster1 = "TreeMonster/T_TreeMonster_1.png";
    public static String treeMonster2 = "TreeMonster/T_TreeMonster_2.png";
    public static String treeMonster3 = "TreeMonster/T_TreeMonsterWalking.png";
    public static Texture treeMonsterTex = new Texture(treeMonster0);
    public static Texture treeMonsterTex1 = new Texture(treeMonster1);
    public static Texture treeMonsterTex2 = new Texture(treeMonster2);
    public static Texture treeMonsterTex3 = new Texture(treeMonster3);
    public static Animation<Texture> treeAnimation = new Animation(0.2f , treeMonsterTex , treeMonsterTex1 , treeMonsterTex2 , treeMonsterTex3 );

    public static String monsterSeed = "MonsterSeed.png";
    public static Texture monsterSeedTex = new Texture(monsterSeed);

    public static String heart = "Heart.png";
    public static Texture heartTex = new Texture(heart);


    public static String shanaPortrait = "avatars/Shana.png";
    public static String dasherPortrait = "avatars/Dasher.png";
    public static String diamondPortrait = "avatars/Diamond.png";
    public static String lilithPortrait = "avatars/Lilith.png";
    public static String scarletPortrait = "avatars/Scarlet.png";
    public static Texture scarletPortraitTex = new Texture(scarletPortrait);
    public static Texture lilithPortraitTex = new Texture(lilithPortrait);
    public static Texture dasherPortraitTex = new Texture(dasherPortrait);
    public static Texture diamondPortraitTex = new Texture(diamondPortrait);
    public static Texture shanaPortraitTex = new Texture(shanaPortrait);



    public static String Explosion1 = "PlayerDamage/T_FireExplosionSmall_0.png";
    public static String Explosion2 = "PlayerDamage/T_FireExplosionSmall_1.png";
    public static String Explosion3 = "PlayerDamage/T_FireExplosionSmall_2.png";
    public static String Explosion4 = "PlayerDamage/T_FireExplosionSmall_3.png";
    public static String Explosion5 = "PlayerDamage/T_FireExplosionSmall_4.png";
    public static String Explosion6 = "PlayerDamage/T_FireExplosionSmall_5.png";
    public static Texture ExpTexture0 = new Texture(Explosion1);
    public static Texture ExpTexture1 = new Texture(Explosion2);
    public static Texture ExpTexture2 = new Texture(Explosion3);
    public static Texture ExpTexture3 = new Texture(Explosion4);
    public static Texture ExpTexture4 = new Texture(Explosion5);
    public static Texture ExpTexture5 = new Texture(Explosion6);
    public static Animation<Texture> explosionAnimation = new Animation(0.2f , ExpTexture0 , ExpTexture1 , ExpTexture2 , ExpTexture3 , ExpTexture4  , ExpTexture5 );


    public static String DragonIdle0 = "DragonIdle/DragonIdle_0.png";
    public static String DragonIdle1 = "DragonIdle/DragonIdle_1.png";
    public static String DragonIdle2 = "DragonIdle/DragonIdle_2.png";
    public static String DragonIdle3 = "DragonIdle/DragonIdle_3.png";
    public static String DragonIdle4 = "DragonIdle/DragonIdle_4.png";
    public static String DragonIdle5 = "DragonIdle/DragonIdle_5.png";
    public static Texture DragonTex1 = new Texture(DragonIdle0);
    public static Texture DragonTex2 = new Texture(DragonIdle1);
    public static Texture DragonTex3 = new Texture(DragonIdle2);
    public static Texture DragonTex4 = new Texture(DragonIdle3);
    public static Texture DragonTex5 = new Texture(DragonIdle4);
    public static Texture DragonTex6 = new Texture(DragonIdle5);
    public static Animation<Texture> dragonIdleAnimation = new Animation(0.1f , DragonTex1 , DragonTex2  , DragonTex3 , DragonTex4 , DragonTex5 , DragonTex6 );


    public static String DragonAttack0 = "DragonAttack/DragonAttack_0.png";
    public static String DragonAttack1 = "DragonAttack/DragonAttack_1.png";
    public static String DragonAttack2 = "DragonAttack/DragonAttack_2.png";
    public static String DragonAttack3 = "DragonAttack/DragonAttack_3.png";
    public static Texture DragonAttackTex0 = new Texture(DragonAttack0);
    public static Texture DragonAttackTex1 = new Texture(DragonAttack1);
    public static Texture DragonAttackTex2 = new Texture(DragonAttack2);
    public static Texture DragonAttackTex3 = new Texture(DragonAttack3);

    public static Animation<Texture> dragonAttackAnimation = new Animation(0.2f , DragonAttackTex0 , DragonAttackTex1 , DragonAttackTex2 , DragonAttackTex3);





    public static Sound shootSFX = Gdx.audio.newSound(Gdx.files.internal("sfx/single_shot.wav"));
    public static Sound reloadSFX = Gdx.audio.newSound(Gdx.files.internal("sfx/Reload.wav"));
    public static Sound monsterDeath = Gdx.audio.newSound(Gdx.files.internal("sfx/Death.wav"));
    public static Sound wining = Gdx.audio.newSound(Gdx.files.internal("sfx/Wining.wav"));
    public static Sound losing = Gdx.audio.newSound(Gdx.files.internal("sfx/Losing.wav"));

    public static boolean sfxEnabled = true;
    public static boolean blackAndWhiteEnabled = false;


    public static Skin getSkin() {
        return skin;
    }

    public static Animation<Texture> getCharacter1_idle_animation() {
        return character1_idle_frames;
    }

    public static String getCharacter1_idle0(){
        return character1_idle0;
    }

    public static Texture getSmgTexture(){
        return smgTexture;
    }

    public static String getSmg(){
        return smg;
    }

    public static String getBullet(){
        return bullet;
    }

    public static ArrayList<Texture> getCharacterTextureList() {
        return characterTextureList;
    }

    public static Music getMusic() {
        return music;
    }

    public static Music getMusicByName(String name){
        switch (name) {
            case "Good things go-Linkin Park":
                return goodThingsGoMusic;
            case "Know-Nf":
                return KnowMusic;
            default :
                return music;
        }
    }
}
