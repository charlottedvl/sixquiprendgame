package com.isep.sixquiprendgame;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter @Setter
public abstract class Player {
    protected List<Card> hand;
    protected int totalOxHead;


}
