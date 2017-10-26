package com.webcheckers.model;

public class Space {

    //
    // Constants
    //


    //
    // Attributes
    //

    private int cellIdx;
    private boolean isValid;
    private Piece piece = null;

    //
    // Constructors
    //

    public Space(int cellIdx, boolean isValid) {
        this.cellIdx = cellIdx;
        this.isValid = isValid;
    }

    //
    //  Methods
    //

    public int getCellIdx() {
        return cellIdx;
    }

    public void setCellIdx(int cellIdx) {
        this.cellIdx = cellIdx;
    }

    @Override
    public boolean equals(Object obj) {
        return true;    // placeholder
    }

    @Override
    public int hashCode() {
        return 0;   // placeholder
    }
}
