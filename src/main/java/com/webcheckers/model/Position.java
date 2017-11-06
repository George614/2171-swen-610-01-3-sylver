package com.webcheckers.model;

/**
 *  Class to represent piece Position
 *  Author: <a href="mailto:mfabbu@rit.edu">Matt Arlauckas</a>
 *  Date: 2017-10-25
 */
public class Position {

    //
    //  Constants
    //


    //
    //  Attributes
    //
    private final int row;
    private final int cell;

    //
    //  Constructors
    //

    public Position(int row, int cell) {
        this.row = row;
        this.cell = cell;
    }

    //
    //  Methods
    //

    public int getRow() { return row; }
    public int getCell() { return cell; }

}
