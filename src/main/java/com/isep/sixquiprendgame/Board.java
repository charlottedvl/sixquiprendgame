package com.isep.sixquiprendgame;

import javafx.scene.control.Label;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

@Getter @Setter
public class Board {

    private ArrayList<Card> stack1;
    private ArrayList<Card> stack2;
    private ArrayList<Card> stack3;
    private ArrayList<Card> stack4;
    private List<ArrayList> stacks;

    public Board() {
        this.stack1 = new ArrayList<Card>();
        this.stack2 = new ArrayList<Card>();
        this.stack3 = new ArrayList<Card>();
        this.stack4 = new ArrayList<Card>();
        this.stacks = new ArrayList<ArrayList>();
        stacks.add(stack1);
        stacks.add(stack2);
        stacks.add(stack3);
        stacks.add(stack4);
    }

}