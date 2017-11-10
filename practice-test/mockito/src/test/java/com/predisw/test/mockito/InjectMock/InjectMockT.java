package com.predisw.test.mockito.InjectMock;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;


import static org.junit.Assert.assertEquals;


public class InjectMockT {

    @Mock
    private Player player;

    @InjectMocks
    private Game game; // if you doesnt instance then mockito will do that for you

    @InjectMocks
    private Game game1 = new Game();  // only inject but not instance

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }


    @Test
    public void test() {
        Mockito.when(player.getWeapon()).thenReturn("Sword");
        assertEquals("Player attack with: Sword", game.attack());

        Mockito.when(player.getWeapon()).thenReturn("Sword");
        assertEquals("Player attack with: Sword", game1.attack());
    }


}


