package com.isep.sixquiprendgame;

import javafx.scene.control.Label;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

@Getter @Setter
public class Board {

    private Stack<Card> stack1;
    private Stack<Card> stack2;
    private Stack<Card> stack3;
    private Stack<Card> stack4;
    private List<Stack> stacks;

    public Board() {
        this.stack1 = new Stack();
        this.stack2 = new Stack();
        this.stack3 = new Stack();
        this.stack4 = new Stack();
        this.stacks = new ArrayList<Stack>();
        stacks.add(stack1);
        stacks.add(stack2);
        stacks.add(stack3);
        stacks.add(stack4);
    }

}