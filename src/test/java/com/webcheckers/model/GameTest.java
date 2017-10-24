package com.webcheckers.model;

import org.junit.Test;

import static org.junit.Assert.assertSame;

//import static org.junit.jupiter.api.Assertions.*;


/**
 *  The unit test suite for the {@link Game} component.
 *
 *  @author <a href="mailto:mfabbu@rit.edu">Matt Arlauckas</a>
 */
class GameTest {

    private static final String PLAYER_RED_NAME = "Bob";
    private static final String PLAYER_WHITE_NAME = "Sally";
    private static final Player PLAYER_RED = new Player(PLAYER_RED_NAME);
    private static final Player PLAYER_WHITE = new Player(PLAYER_WHITE_NAME);


//    @Before
//    void setUp() {
//    }
//
//    @org.junit.jupiter.api.AfterEach
//    void tearDown() {
//    }

//    @Test


    @Test
    void getPlayerRedUsername() {
        final Game CuT = new Game(PLAYER_RED,PLAYER_WHITE);
        assertSame(PLAYER_RED_NAME, PLAYER_RED.getUsername());
    }

    @Test
    void getPlayerWhiteUsername() {
        final Game CuT = new Game(PLAYER_RED,PLAYER_WHITE);
        assertSame(PLAYER_WHITE_NAME, PLAYER_WHITE.getUsername());
    }
}