package com.isep.sixquiprendgame;

public class BoardController {

    private HumanPlayer player;
    private AiPlayer ai;

    public void showInformation(HumanPlayer player, AiPlayer ai){
        this.player = player;
        this.ai = ai;
    }
}
