package com.Mohammadreza.UD20.Controllers;


import com.badlogic.gdx.Input;

public class KeyBinding {

    public static int MOVE_UP = Input.Keys.W;
    public static int MOVE_DOWN = Input.Keys.S;
    public static int MOVE_LEFT = Input.Keys.A;
    public static int MOVE_RIGHT = Input.Keys.D;
    public static int RELOAD = Input.Keys.R;


    public static char getStringKeyboardWithNumber(int number){
        return (char) number;
    }





}
