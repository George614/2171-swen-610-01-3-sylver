package com.webcheckers.model;

import java.util.ArrayList;
import java.util.List;
import sun.plugin.dom.exception.InvalidStateException;

/**
 *  Class to represent the Board (made up of Rows)
 *  Author: <a href="mailto:mfabbu@rit.edu">Matt Arlauckas</a>
 *  Date: 2017-10-25
 */
public class Board {

    //
    //  Constants
    //
    static final String VALID_MOVE_MESSAGE = "Good move!";
    static final String INVALID_MOVE_MESSAGE = "Sorry, that play is invalid. Please try again.";

    //
    //  Attributes
    //
    private List<Row> rows;
    public Color currentTurn;

    private Position capturedNew = null;
    private boolean capturedTrue = false;

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
    public boolean isMoveValid(Move move) {

        Position start = move.getStart();
        Position end = move.getEnd();

        //start = in row, there's a cell that has this piece
        int diagonalStartSpace = start.getRow() + start.getCell();
        int diagonalEndSpace = end.getRow() + end.getCell();
        int startSpace = diagonalStartSpace % 2;



        switch (currentTurn) {
            case WHITE:

                Row checkPieceN = null;
                if (end.getRow() + 1 > 7){ checkPieceN = rows.get(7); }
                else { checkPieceN = rows.get(end.getRow()+1); }

                List<Space> rowSpacesN = checkPieceN.getSpaces();
                Piece currentOccupantN = null;
                if ((end.getRow() + end.getCell()) % 2 == 1) {
                    //if a red piece is in the way, capturing move, return true

                    if (start.getCell() > end.getCell()){ // Going left
                        currentOccupantN = rowSpacesN.get(end.getCell() + 1).getPiece();
                    } else if (start.getCell() < end.getCell()) { // Going right
                        currentOccupantN = rowSpacesN.get(end.getCell() - 1).getPiece();
                    }

                    if (currentOccupantN != null && currentOccupantN.getColor() == Color.RED) {

                        int y = java.lang.Math.abs(end.getRow() - start.getRow());

                        if (y <= 1 || y > 2) {

                            return false;
                        }
                        if (start.getCell() > end.getCell()) { // Going left

                            capturedNew = new Position(end.getRow() + 1,end.getCell() + 1);
                        } else if (start.getCell() < end.getCell()) { // Going right

                            capturedNew = new Position(end.getRow() + 1,end.getCell() - 1);
                        }
                        capturedTrue = true;
                        return true;
                    }
                    if (end.getRow() > start.getRow()) { return false; }
                    else {
                        if ((end.getRow() + 1) == start.getRow() && (start.getCell() == (end.getCell() + 1) || start.getCell() == (end.getCell() - 1))) {
                            // The piece should be able to move
                            return true;
                        } else {
                            return false;
                        }
                    }
                }
                else { return false; }
            case RED:
                Row checkPiece = null;
                if (end.getRow() - 1 < 0) { checkPiece = rows.get(0); }
                else { checkPiece = rows.get(end.getRow() - 1); }

                List<Space> rowSpaces = checkPiece.getSpaces();
                Piece currentOccupant = null;

                if ((end .getRow() + end.getCell() )% 2 == 1) {
                    //if a white piece is in the way, capturing move, return true
                    //Checks if it is a diagonal

                    if (start.getCell() > end.getCell()) { // Going left
                        currentOccupant = rowSpaces.get(end.getCell() + 1).getPiece();
                    } else if (start.getCell() < end.getCell()) { // Going right
                        currentOccupant = rowSpaces.get(end.getCell() - 1).getPiece();
                    }

                    if (currentOccupant != null && currentOccupant.getColor() == Color.WHITE) {
                        //capture move

                        int y = java.lang.Math.abs(end.getRow() - start.getRow());

                        if (y <= 1 || y > 2) {

                            return false;
                        }
                        if (start.getCell() > end.getCell()) { // Going left
                            capturedNew = new Position(end.getRow() - 1,end.getCell() + 1);
                        } else if (start.getCell() < end.getCell()) { // Going right
                            capturedNew = (new Position(end.getRow() - 1,end.getCell() - 1));
                        }
                        capturedTrue = true;
                        return true;
                    }
                    if (end.getRow() < start.getRow()) { return false; }
                    else {

                        //only move forward, there is no occupant
                        if ((end.getRow() - 1) == start.getRow() && (start.getCell() == (end.getCell() + 1) || start.getCell() == (end.getCell() - 1))) {
                            //The piece should be able to move;
                            return true;
                        } else { return false; }
                    }
                }
                else {
                    //Invalid move
                    return false;
                }
            default:
                //invalid
                return false;
        }
    }

    /**
     * Returns the Space occupied by the given Position
     *
     * @param position
     *          The Move's Position.
     *
     * @return the Space object
     */
    public Space getSpaceByPosition(Position position) {
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
    public void setPieceByPosition(Position position, Piece piece) {
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
            return new Message(VALID_MOVE_MESSAGE, MessageType.INFO);
        } else {
            return new Message(INVALID_MOVE_MESSAGE, MessageType.ERROR);
        }
    }

    /**
     * Makes a Move inside the Board.
     *
     * @param move
     *    The {@link Move} from the user.
     */
    public void makeMove(Move move) {

        boolean isAValidMove = false;

        if (isMoveValid(move)) {

            Space startSpace = getSpaceByPosition(move.getStart()); // get the origin position
            Piece movedPiece = startSpace.getPiece();               // get the piece about to be moved
            move.getEnd().getRow();
            //check if the piece is eligible for crowning
            boolean isRedKing = movedPiece.getColor() == Color.RED && move.getEnd().getRow()== 7 && movedPiece.getType() == Type.SINGLE;
            boolean isWhiteKing = movedPiece.getColor() == Color.WHITE && move.getEnd().getRow()== 0 && movedPiece.getType()== Type.SINGLE;
            if (isRedKing || isWhiteKing ) {

                movedPiece.setType(Type.KING);
            }
            setPieceByPosition(move.getEnd(), movedPiece);          // put the piece in its new position
            startSpace.setPiece(null);                              // remove the piece from the origin position
            isAValidMove = true;
        }

        if (capturedTrue && isAValidMove) {
            //removes captured pieces from the board.
            setPieceByPosition(capturedNew,null);
            capturedTrue = false;
        }
    }

    /**
     * Queries whether the {@link Piece}'s present on the given {@link Position} can move or not
     *
     * @param position
     *    The {@link Board}'s {@link Position}.
     *
     * @return true if the {@link Piece} present in the {@link Position} can move, false if it can't or if there's no {@link Piece}
     */
    public boolean canMove(Position position) {
        // First thing, check if there's actually a Piece on the given Position
        Piece observedPiece = this.getSpaceByPosition(position).getPiece();
        if (observedPiece == null) { return false; }

        // Create a list to hold the allowed Positions that the piece
        List<Position> allowedPositions = new ArrayList<>();
        List<Position> tempPositions = new ArrayList<>();

        // Check if the Piece is SINGLE or KING, in order to calculate the valid moves around it
        switch (observedPiece.getType()) {
            case KING:
                // STEP 1: Get non-capturing positions
                // can get non-capturing moves in both directions, no matter the color
                Position upperLeftPosition = new Position(position.getRow() - 1, position.getCell() - 1);
                Position upperRightPosition = new Position( position.getRow() - 1, position.getCell() + 1);
                Position lowerLeftPosition = new Position(position.getRow() + 1, position.getCell() - 1);
                Position lowerRightPosition = new Position(position.getRow() + 1, position.getCell() + 1);

                if (upperLeftPosition.isValid()) { tempPositions.add(upperLeftPosition); }
                if (upperRightPosition.isValid()) { tempPositions.add(upperRightPosition); }
                if (lowerLeftPosition.isValid()) { tempPositions.add(lowerLeftPosition); }
                if (lowerRightPosition.isValid()) { tempPositions.add(lowerRightPosition); }
                break;
            case SINGLE:
                // STEP 1: Get non-capturing positions
                // can get non-capturing moves either downwards or upwards, depending of the color
                // Check it the Piece's color is RED or WHITE, in order to calculate the valid moves around it
                switch (observedPiece.getColor()) {
                    case RED:
                        Position lowerLeftRedPosition = new Position(position.getRow() + 1, position.getCell() - 1);
                        Position lowerRightRedPosition = new Position(position.getRow() + 1, position.getCell() + 1);

                        if (lowerLeftRedPosition.isValid()) { tempPositions.add(lowerLeftRedPosition); }
                        if (lowerRightRedPosition.isValid()) { tempPositions.add(lowerRightRedPosition); }
                        break;
                    case WHITE:
                        Position upperLeftWhitePosition = new Position(position.getRow() - 1, position.getCell() - 1);
                        Position upperRightWhitePosition = new Position( position.getRow() - 1, position.getCell() + 1);

                        if (upperLeftWhitePosition.isValid()) { tempPositions.add(upperLeftWhitePosition); }
                        if (upperRightWhitePosition.isValid()) { tempPositions.add(upperRightWhitePosition); }
                        break;
                    default:
                        //throw new InvalidStateException("The Piece should have a Color defined.");
                }
                break;
            default:
                //throw new InvalidStateException("The Piece should have a Type defined.");
        }

        // Check the available capturing and non-capturing moves (no need to check multiple capture moves) and move them to the allowedPositions list
        // Check if the allowed non-capturing positions have any Piece on them: if they don't, the position is valid
        for (Position item : tempPositions) {
            Piece tempPiece = getSpaceByPosition(item).getPiece();
            if (tempPiece == null) {
                // If there's no Piece there, we can add it as a non-capturing landing position
                allowedPositions.add(item);
            } else {
                // STEP 2: Get capturing positions
                // If there's an opposing Piece, we can at least check for the possibility of a capturing move by going over it
                if (observedPiece.getColor() != tempPiece.getColor()) {
                    // Check if the jump end position is empty and valid
                    Position jumpEndPosition = position.getJumpEndPosition(item);
                    if (jumpEndPosition != null && getSpaceByPosition(jumpEndPosition).getPiece() == null) {
                        allowedPositions.add(jumpEndPosition);
                    }
                }
            }
        }
        // If the total is more than 0, return true. Otherwise return
        if (allowedPositions.size() > 0) { return true; }
        else { return false; }
    }

    /**
     * Queries whether the {@link Piece}'s present on the given {@link Position} can make a capture or not
     *
     * @param position
     *          The {@link Board}'s {@link Position}.
     *
     * @return true if the {@link Piece} present in the {@link Position} can make a capture, false if it can't or if there's no {@link Piece}
     */
    public boolean canCapture(Position position) { return true; }

    /**
     * Queries whether the {@link Color}'s associated user is blocked from moving any pieces
     *
     * @param color
     *          The {@link Player}'s {@link Color}.
     *
     * @return true if the user associated with the {@link Color} can't move any pieces, otherwise false
     */
    public boolean isColorBlocked(Color color) {
        // Loop through every black Space
        for (int row = 0; row < 8; row++) {
            for (int cell = 0; cell < 8; cell++) {
                Position tempPosition = new Position(row, cell);
                Space tempSpace = getSpaceByPosition(tempPosition);
                // Skip white pieces
                if (!tempSpace.isBlackSquare()) { continue; }
                // Skip empty Spaces
                if (tempSpace.getPiece() == null) { continue; }
                // Skip pieces from the opposite color
                if (tempSpace.getPiece().getColor() != color) { continue; }
                // If the Piece in that position can Move, no need to check the rest: return false
                if (canMove(tempPosition)) { return false; }
            }
        }
        // If we actually went through the entire loop and none of the Pieces could move, return true
        return true;
    }
}
