package com.predisw.test.mockito.InjectMock;

public class Player {
    private String weapon;

    public Player(String weapon) {
        this.weapon = weapon;
    }

    String getWeapon() {
        return weapon;
    }

}

