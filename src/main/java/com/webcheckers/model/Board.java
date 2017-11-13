package com.webcheckers.model;

import java.util.ArrayList;
import java.util.List;

/**
 *  Class to represent the Board (made up of Rows)
 *  Author: <a href="mailto:nd7896@rit.edu">Matt Arlauckas</a>
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

    private boolean isAKing=false;
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

    public boolean isMoveValidKing(Move move){

        System.out.println("isMoveValidKing");
        Position start = move.getStart();
        Position end = move.getEnd();

        //start = in row, there's a cell that has this piece
        int diagonalStartSpace = start.getRow() + start.getCell();
        int diagonalEndSpace = end.getRow() + end.getCell();
        int startSpace = diagonalStartSpace % 2;

        //non-capturing move
        switch(currentTurn){
            case WHITE:
                Row checkPieceN = null;
                if (end.getRow() + 1 > 7){ checkPieceN = rows.get(7); }
                else { checkPieceN = rows.get(end.getRow()+1); }

                List<Space> rowSpacesN = checkPieceN.getSpaces();
                Piece currentOccupantN = null;

                if ((end.getRow() + end.getCell()) % 2 == 1) {

                    boolean moveUp=((end.getRow() - 1) == start.getRow());
                    boolean moveDown=((end.getRow() + 1) == start.getRow());

                    boolean moveLeft=(start.getCell() == (end.getCell() - 1));
                    boolean moveRight=(start.getCell() == (end.getCell() + 1));

                    if ((moveUp||moveDown)&& (moveLeft||moveRight)) {
                        // The piece should be able to move
                        System.out.println("IT MUST MOVE");
                        return true;
                    }

                }
                else{
                    return false;
                }
                break;
            case RED:
                Row checkPiece = null;
                if (end.getRow() + 1 > 7){ checkPiece = rows.get(7); }
                else { checkPiece = rows.get(end.getRow()+1); }

                List<Space> rowSpaces = checkPiece.getSpaces();
                Piece currentOccupant = null;

                if ((end.getRow() + end.getCell()) % 2 == 1) {

                    boolean moveUp=((end.getRow() - 1) == start.getRow());
                    boolean moveDown=((end.getRow() + 1) == start.getRow());

                    boolean moveLeft=(start.getCell() == (end.getCell() - 1));
                    boolean moveRight=(start.getCell() == (end.getCell() + 1));

                    if ((moveUp||moveDown)&& (moveLeft||moveRight)) {
                        // The piece should be able to move
                        System.out.println("IT MUST MOVE");
                        return true;
                    }

                }
                else{
                    return false;
                }
                break;
            default:
                break;
        }

        return false;
    }
    /**
     * Queries whether the Move is valid.
     *
     * @param move
     *          The Player's Move.
     *
     * @return true if the Move is valid, otherwise, false
     */
    public boolean isMoveValid(Move move) {

        System.out.println("Well, I'm inside the function");

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
                else { System.out.println("Invalid move RED");return false; }
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
                    System.out.println("Invalid move RED");
                    return false;
                }
            default:
                //invalid
                System.out.println("In default");
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
            System.out.println("validateMove");
            return new Message(VALID_MOVE_MESSAGE, MessageType.INFO);
        }
        else if(isMoveValidKing(move)){
            System.out.println("validateMove for King");
            return new Message(VALID_MOVE_MESSAGE, MessageType.INFO);

        }
        else {
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
        Piece movedPiece=null;
        Space startSpace = getSpaceByPosition(move.getStart()); // get the origin position
        Piece currentPiece = startSpace.getPiece();
        System.out.println(currentPiece.getType());
        System.out.println(currentPiece);

        if (isMoveValid(move) ) {
            System.out.println("from makeMove");
            startSpace = getSpaceByPosition(move.getStart());  // get the origin position
            movedPiece = startSpace.getPiece();               // get the piece about to be moved
            move.getEnd().getRow();

            //check if the piece is eligible for crowning
            boolean isRedKing = movedPiece.getColor() == Color.RED && move.getEnd().getRow()== 7 && movedPiece.getType() == Type.SINGLE;
            boolean isWhiteKing = movedPiece.getColor() == Color.WHITE && move.getEnd().getRow()== 0 && movedPiece.getType()== Type.SINGLE;
            if (isRedKing || isWhiteKing ) {
                System.out.println("SETTING TYPE to KING");
                movedPiece.setType(Type.KING);
                System.out.println(movedPiece);
            }
            if((movedPiece.getType()==Type.SINGLE)){
                setPieceByPosition(move.getEnd(), movedPiece);          // put the piece in its new position
                startSpace.setPiece(null);                              // remove the piece from the origin position
                isAValidMove = true;
            }
            else if((movedPiece.getType()==Type.KING)){



            }
            return;

        }
        else if ( isMoveValidKing(move) && currentPiece.getType()==Type.KING) {

            System.out.println("Make move for King");

            startSpace = getSpaceByPosition(move.getStart()); // get the origin position
            movedPiece = startSpace.getPiece();               // get the piece about to be moved
            move.getEnd().getRow();

            setPieceByPosition(move.getEnd(), movedPiece);          // put the piece in its new position
            System.out.println("Setting the piece to its end position");
            startSpace.setPiece(null);                              // remove the piece from the origin position
            isAValidMove = true;
        }


        if (capturedTrue && isAValidMove) {
            //removes captured pieces from the board.
            setPieceByPosition(capturedNew,null);
            capturedTrue = false;
        }
    }
}
