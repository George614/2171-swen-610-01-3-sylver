package com.webcheckers.model;

import java.util.Objects;

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
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Space space = (Space) obj;
        return cellIdx == space.cellIdx &&
                isValid == space.isValid &&
                Objects.equals(piece, space.piece);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cellIdx, isValid, piece);
    }

    @Override
    public String toString() {
        return "Space{" +
                "cellIdx=" + cellIdx +
                ", isValid=" + isValid +
                ", piece=" + piece +
                '}';
    }
}
