package com.isep.sixquiprendgame;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;

@Getter @Setter
public abstract class Player {
    protected ArrayList<Card> hand;
    protected String name;
    protected int totalOxHead;

    protected int ranking;


}
