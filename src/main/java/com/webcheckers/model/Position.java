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
    private int row;
    private int cell;

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

    public void setRow(int row){this.row=row;}
    public void setCell(int c){this.cell=c;}

}
