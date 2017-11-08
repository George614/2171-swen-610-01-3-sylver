package com.webcheckers.model;

import java.util.ArrayList;
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
    static final String VALID_MOVE_MESSAGE = "Movement valid!";
    static final String INVALID_MOVE_MESSAGE = "Sorry, that play is invalid.";

    //
    //  Attributes
    //
    private List<Row> rows;
    public Color currentTurn;
    //private List<Position> captured=new ArrayList<Position>();

    private Position capturedNew=null;
    private boolean capturedTrue=false;

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
        System.out.println("isMoveValid being called.");
        Position start = move.getStart();
        Position end = move.getEnd();
        //Position intermediate=end;


        //start = in row, there's a cell that has this piece
        int diagonalStartSpace = start.getRow() + start.getCell();
        int diagonalEndSpace = end.getRow() + end.getCell();
        int startSpace = diagonalStartSpace % 2;
        System.out.println(diagonalStartSpace);
        System.out.println("StartSpace"+startSpace%2);

        //currentTurn=Game.getCurrentTurn();
        switch (currentTurn) {
            case WHITE:

                //this is a white piece moving
                System.out.println("WHITE PIECE");
                Row checkPieceN=null;
                if(end.getRow()+1>7){
                    checkPieceN = rows.get(7);
                }
                else{
                    checkPieceN = rows.get(end.getRow()+1);
                }

                //Row checkPieceN = rows.get(end.getRow()+1);
                List<Space> rowSpacesN = checkPieceN.getSpaces();
                Piece currentOccupantN = null;
                if ((end.getRow() + end.getCell()) % 2 == 1) {
                    //if a red piece is in the way, capturing move, return true

                    if(start.getCell()>end.getCell()){
                        //goung left
                        currentOccupantN = rowSpacesN.get(end.getCell()+1).getPiece();
                    }

                    else if(start.getCell()<end.getCell()){
                        //goung right
                        currentOccupantN = rowSpacesN.get(end.getCell()-1).getPiece();
                    }


                    if (currentOccupantN!=null && currentOccupantN.getColor() == Color.RED) {
                        System.out.println("There is an occupant");
                        //captured=new Position(end.getRow()-1,end.getCell()+1);
                        //setPieceByPosition(captured,null);
                        int y=java.lang.Math.abs(end.getRow()-start.getRow());
                        System.out.println("Y"+y);
                        if(y<=1 || y>2){
                            System.out.println("CASE 000000000000");

                            return false;

                        }
                        if(start.getCell()>end.getCell()){
                            //going left
                            System.out.println("CAPTURED!");
                            //captured.add(new Position(end.getRow()+1,end.getCell()+1));
                            capturedNew=new Position(end.getRow()+1,end.getCell()+1);
                        }

                        else if(start.getCell()<end.getCell()){
                            //goung right
                            System.out.println("CAPTURED!");
                            //captured.add(new Position(end.getRow()+1,end.getCell()-1));
                            capturedNew=new Position(end.getRow()+1,end.getCell()-1);
                        }

                        capturedTrue=true;

                        return true;
                    }
                    if(end.getRow()>start.getRow()){
                        //DO NOT MOVE
                        return false;
                    }
                    else{
                        System.out.println("No occupant WHITE");
                        System.out.println((end.getRow()+1)==start.getRow());
                        System.out.println((start.getCell()==(end.getCell()+1)));
                        System.out.println(start.getCell()==(end.getCell()-1));
                        if((end.getRow()+1)==start.getRow() && (start.getCell()==(end.getCell()+1) || start.getCell()==(end.getCell()-1))){
                            System.out.println("It should be able to move.");
                            return true;
                        }
                        else{
                            return false;
                        }
                    }
                }
                else {
                    return false;
                }

            case RED:

                System.out.println("RED PIECE");
                Row checkPiece=null;
                //this is a red piece
                if(end.getRow()-1<0){
                    checkPiece = rows.get(0);
                }
                else{
                    checkPiece = rows.get(end.getRow()-1);
                }

                List<Space> rowSpaces = checkPiece.getSpaces();
                Piece currentOccupant=null;

                if ((end .getRow() + end.getCell() )% 2 == 1) {
                    //if a white piece is in the way, capturing move, return true
                    System.out.println("Yes, it is a diagonal");


                    if(start.getCell()>end.getCell()){
                        //going left
                        currentOccupant = rowSpaces.get(end.getCell()+1).getPiece();
                    }

                    else if(start.getCell()<end.getCell()){
                        //going right
                        currentOccupant = rowSpaces.get(end.getCell()-1).getPiece();
                    }

                    if (currentOccupant!=null && currentOccupant.getColor() == Color.WHITE) {
                        //capture move
                        System.out.println("There is an occupant");
                        int y=java.lang.Math.abs(end.getRow()-start.getRow());
                        System.out.println("Y"+y);
                        if(y<=1 || y>2){
                            System.out.println("CASE 11111");
                            return false;

                        }
                        if(start.getCell()>end.getCell()){
                            //going left
                            //captured.add(new Position(end.getRow()-1,end.getCell()+1));
                            capturedNew=new Position(end.getRow()-1,end.getCell()+1);
                        }

                        else if(start.getCell()<end.getCell()){
                            //going right
                            //captured.add(new Position(end.getRow()-1,end.getCell()-1));
                            capturedNew=(new Position(end.getRow()-1,end.getCell()-1));
                        }
                        capturedTrue=true;
                        return true;
                    }

                    if(end.getRow()<start.getRow()){
                        //DO NOT MOVE
                        return false;
                    }

                    else{
                        System.out.println("No occupant RED");
                        //only move forward

                        if((end.getRow()-1)==start.getRow() && (start.getCell()==(end.getCell()+1) || start.getCell()==(end.getCell()-1))){
                            System.out.println("It should be able to move.");
                            return true;

                        }
                        else{
                            return false;
                        }
                    }
                }
                else {
                    System.out.println("Why is this executing");
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
        int index=0;
        boolean isAValidMove=false;
        System.out.println("makeMove CALLING");
        if (isMoveValid(move)) {
            System.out.println("Valid Move");
            Space startSpace = getSpaceByPosition(move.getStart()); // get the origin position
            Piece movedPiece = startSpace.getPiece();               // get the piece about to be moved
            move.getEnd().getRow();
            //check if the piece is eligible for crowning
            boolean isRedKing = movedPiece.getColor() == Color.RED && move.getEnd().getRow()==7 && movedPiece.getType() == Type.SINGLE;
            boolean isWhiteKing = movedPiece.getColor()==Color.WHITE && move.getEnd().getRow()==0 && movedPiece.getType()== Type.SINGLE;
            if (isRedKing || isWhiteKing ) {

                movedPiece.setType(Type.KING);
            }
            setPieceByPosition(move.getEnd(), movedPiece);          // put the piece in its new position
            startSpace.setPiece(null);                              // remove the piece from the origin position
            isAValidMove=true;
        }
        else{
            System.out.println("Invalid Move from Make Move");
        }
        if(capturedTrue ){
           // while(!captured.isEmpty()){
                //setPieceByPosition(captured.remove(index),null);
            setPieceByPosition(capturedNew,null);
            //}
            capturedTrue=false;
        }
    }
}
