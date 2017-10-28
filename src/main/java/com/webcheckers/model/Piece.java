package com.webcheckers.model;

import java.util.Arrays;

public class Piece {

    //
    // Constants
    //


    //
    // Attributes
    //

    private Type type;
    private Color color;

    //
    // Constructors
    //

    public Piece(Type type, Color color) {
        this.type = type;
        this.color = color;

    }

    //
    //  Methods
    //

    public Type getType() {
        return type;
    }

    public Color getColor() {
        return color;
    }

    @Override
    public boolean equals(Object obj) {
        return true;
    }

    @Override
    public int hashCode() {
        return 1;
    }
}
