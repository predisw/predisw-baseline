package com.predisw.test.mockito.InjectMock;


public class Game {

    private Player player;

//    public Game(Player player) {
//        this.player = player;
//    }


    // through setter instance
    public void setPlayer(Player player){
        this.player = player;
    }

    public String attack() {
        return "Player attack with: " + player.getWeapon();
    }
}