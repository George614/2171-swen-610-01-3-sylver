package com.webcheckers.model;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 *  Class to represent the Board (made up of Rows)
 *  Author: <a href="mailto:mfabbu@rit.edu">Matt Arlauckas</a>
 *  Date: 2017-10-25
 */
public class Board {

    //
    //  Constants
    //

    //
    //  Attributes
    //
    private List<Row> rows;

    //
    //  Constructors
    //

    public Board() {
        this.rows = new ArrayList<>();
        for (int i = 0; i < 8; i++) {
            rows.add(new Row(i));
        }
    }

    //
    //  Methods
    //

    /**
     * Queries whether the Move is valid.
     *
     * @param move
     *          The Player's Move.
     *
     * @return true if the Move is valid, otherwise, false
     */
    private boolean isMoveValid(Move move) {
        // TODO: Implement this
        return true;
    }

    /**
     * Returns the Space occupied by the given Position
     *
     * @param position
     *          The Move's Position.
     *
     * @return the Space object
     */
    private Space getSpaceByPosition(Position position) {
        int rowIndex = position.getRow();
        int cellIndex = position.getCell();

        //Look for the row object
        Row rowObjStart = rows.stream().filter(x -> x.getIndex() == rowIndex).findFirst().get();
        //Look for the space object
        return rowObjStart.getSpaces().stream().filter(x -> x.getCellIdx() == cellIndex).findFirst().get();
    }

    /**
     * Sets the Piece occupied by the given Position
     *
     * @param position
     *          The Move's Position
     * @param piece
     *          The Piece
     */
    private void setPieceByPosition(Position position, Piece piece) {
        Space space = getSpaceByPosition(position);
        space.setPiece(piece);
    }

    /**
     * Get the list of Rows inside the Board.
     *
     * @return
     *   The list of rows.
     */
    public List<Row> getRows() {
        return rows;
    }

    /**
     * Validates a Move inside the Board.
     *
     * @param move
     *    The {@link Move} from the user.
     * @return
     *    The Message object.
     */
    public Message validateMove(Move move) {
        if (isMoveValid(move)) {
            // TODO: Implement this
        } else {
            // TODO: Implement this
        }
        return new Message();
    }

    /**
     * Makes a Move inside the Board.
     *
     * @param move
     *    The {@link Move} from the user.
     */
    public void makeMove(Move move) {
        if (isMoveValid(move)) {
            Space startSpace = getSpaceByPosition(move.getStart()); // get the origin position
            Piece movedPiece = startSpace.getPiece();               // get the piece about to be moved

            setPieceByPosition(move.getEnd(), movedPiece);          // put the piece in its new position
            startSpace.setPiece(null);                              // remove the piece from the origin position
        }
    }
}
