package com.webcheckers.model;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 *  The unit test suite for the {@link Board} component.
 *
 *  @author <a href="mailto:dl8006@g.rit.edu">Diosdavi Lara</a>
 */
public class BoardTest {

  @Test
  public void getRows() throws Exception {
    final Board CuT = new Board();
    assertTrue(CuT.getRows().size() == 8);
  }

  @Test
  public void testCrowning() throws Exception {
    final Board CuT = new Board();
    CuT.currentTurn = Color.RED;

    // setting up the board pieces
    Position whitePieceToDelete1 = new Position(6, 1);
    Position whitePieceToDelete2 = new Position(7, 0);

    Position redPieceStart = new Position(6, 1);
    Position redPieceEnd = new Position(7, 0);
    Move crowningMove = new Move(redPieceStart, redPieceEnd);
    Piece pieceToBeTested = new Piece(Type.SINGLE, Color.RED);

    CuT.setPieceByPosition(whitePieceToDelete1, null);
    CuT.setPieceByPosition(whitePieceToDelete2, null);
    CuT.setPieceByPosition(redPieceStart, pieceToBeTested);
    CuT.makeMove(crowningMove);

    Piece pieceAfterMove = CuT.getSpaceByPosition(redPieceEnd).getPiece();
    assertTrue(pieceAfterMove.getType() == Type.KING);
  }

  @Test
  public void testCrowningWhite() throws Exception {
    final Board CuT = new Board();
    CuT.currentTurn = Color.WHITE;

    // setting up the board pieces
    Position redPieceToDelete1 = new Position(1, 6);
    Position redPieceToDelete2 = new Position(0, 7);

    Position whitePieceStart = new Position(1, 6);
    Position whitePieceEnd = new Position(0, 7);
    Move crowningMove = new Move(whitePieceStart, whitePieceEnd);
    Piece pieceToBeTested = new Piece(Type.SINGLE, Color.WHITE);

    CuT.setPieceByPosition(redPieceToDelete1, null);
    CuT.setPieceByPosition(redPieceToDelete2, null);
    CuT.setPieceByPosition(whitePieceStart, pieceToBeTested);
    CuT.makeMove(crowningMove);

    Piece pieceAfterMove = CuT.getSpaceByPosition(whitePieceEnd).getPiece();
    assertTrue(pieceAfterMove.getType() == Type.KING);

    //capturing move test
  }


  @Test
  public void testSingleCapturingWhite() throws Exception {
    final Board CuT = new Board();
    CuT.currentTurn = Color.WHITE;

    // setting up the board pieces
    Position redPieceToDelete = new Position(1, 2);

    Position whitePieceStart = new Position(3, 0);
    Position whitePieceEnd = new Position(1, 2);
    Move capturingMove = new Move(whitePieceStart, whitePieceEnd);
    Piece pieceToBeTested = new Piece(Type.SINGLE, Color.WHITE);

    CuT.setPieceByPosition(redPieceToDelete, null);
    CuT.setPieceByPosition(whitePieceStart, pieceToBeTested);
    CuT.makeMove(capturingMove);


    assertTrue( (CuT.getRows().get(1).getSpaces().get(2).getPiece()!=null )&& (CuT.getRows().get(3).getSpaces().get(0).getPiece() ==null) && (CuT.getRows().get(2).getSpaces().get(1).getPiece()==null));

    //move left capture

  }

  @Test
  public void testSingleCapturingWhiteKing() throws Exception {
    final Board CuT = new Board();
    CuT.currentTurn = Color.WHITE;

    // setting up the board pieces
    Position redPieceToDelete = new Position(1, 2);

    Position whitePieceStart = new Position(3, 0);
    Position whitePieceEnd = new Position(1, 2);
    Move capturingMove = new Move(whitePieceStart, whitePieceEnd);
    Piece pieceToBeTested = new Piece(Type.KING, Color.WHITE);

    CuT.setPieceByPosition(redPieceToDelete, null);
    CuT.setPieceByPosition(whitePieceStart, pieceToBeTested);
    CuT.makeMove(capturingMove);


    assertTrue( (CuT.getRows().get(1).getSpaces().get(2).getPiece()!=null )&& (CuT.getRows().get(3).getSpaces().get(0).getPiece() ==null) && (CuT.getRows().get(2).getSpaces().get(1).getPiece()==null));

    //move left capture

  }


  @Test
  public void testSingleCapturingWhiteLeft() throws Exception {
    final Board CuT = new Board();
    CuT.currentTurn = Color.WHITE;
    Position redPieceToDelete=new Position(1,2);

    Position whitePieceStart = new Position(3, 4);
    Position whitePieceEnd = new Position(1, 2);
    Piece pieceToBeTested = new Piece(Type.SINGLE, Color.WHITE);
    CuT.setPieceByPosition(redPieceToDelete, null);
    //CuT.setPieceByPosition(redPieceToDelete2, null);
    CuT.setPieceByPosition(whitePieceStart, pieceToBeTested);
    Move capturingMove = new Move(whitePieceStart, whitePieceEnd);
    CuT.makeMove(capturingMove);

    assertTrue( (CuT.getRows().get(1).getSpaces().get(2).getPiece()!=null )&& (CuT.getRows().get(3).getSpaces().get(4).getPiece() ==null) && (CuT.getRows().get(2).getSpaces().get(3).getPiece()==null));


  }


  @Test
  public void testSingleCapturingWhiteLeftRed() throws Exception {
    final Board CuT = new Board();
    CuT.currentTurn = Color.WHITE;
    Position redPieceToDelete=new Position(1,2);

    Position whitePieceStart = new Position(3, 4);
    Position whitePieceEnd = new Position(1, 2);
    Piece pieceToBeTested = new Piece(Type.KING, Color.WHITE);
    CuT.setPieceByPosition(redPieceToDelete, null);
    //CuT.setPieceByPosition(redPieceToDelete2, null);
    CuT.setPieceByPosition(whitePieceStart, pieceToBeTested);
    Move capturingMove = new Move(whitePieceStart, whitePieceEnd);
    CuT.makeMove(capturingMove);

    assertTrue( (CuT.getRows().get(1).getSpaces().get(2).getPiece()!=null )&& (CuT.getRows().get(3).getSpaces().get(4).getPiece() ==null) && (CuT.getRows().get(2).getSpaces().get(3).getPiece()==null));


  }

  @Test
  public void testRedCapturingWhite() throws Exception {
    final Board CuT = new Board();
    CuT.currentTurn = Color.RED;

    // setting up the board pieces
    Position whitePieceToDelete = new Position(6, 1);

    Position redPieceStart = new Position(4, 3);
    Position redPieceEnd = new Position(6, 1);
    Move capturingMove = new Move(redPieceStart, redPieceEnd);
    Piece pieceToBeTested = new Piece(Type.SINGLE, Color.RED);

    CuT.setPieceByPosition(whitePieceToDelete, null);
    CuT.setPieceByPosition(redPieceStart, pieceToBeTested);
    CuT.makeMove(capturingMove);


    assertTrue( (CuT.getRows().get(6).getSpaces().get(1).getPiece()!=null )&& (CuT.getRows().get(5).getSpaces().get(2).getPiece() ==null) && (CuT.getRows().get(4).getSpaces().get(3).getPiece()==null));

    //capturing move test
  }


  @Test
  public void testRedCapturingWhiteKing() throws Exception {
    final Board CuT = new Board();
    CuT.currentTurn = Color.RED;

    // setting up the board pieces
    Position whitePieceToDelete = new Position(6, 1);

    Position redPieceStart = new Position(4, 3);
    Position redPieceEnd = new Position(6, 1);
    Move capturingMove = new Move(redPieceStart, redPieceEnd);
    Piece pieceToBeTested = new Piece(Type.KING, Color.RED);

    CuT.setPieceByPosition(whitePieceToDelete, null);
    CuT.setPieceByPosition(redPieceStart, pieceToBeTested);
    CuT.makeMove(capturingMove);


    assertTrue( (CuT.getRows().get(6).getSpaces().get(1).getPiece()!=null )&& (CuT.getRows().get(5).getSpaces().get(2).getPiece() ==null) && (CuT.getRows().get(4).getSpaces().get(3).getPiece()==null));

    //capturing move test
  }

  @Test
  public void invalidMoveWhite() throws Exception {
    final Board CuT = new Board();
    CuT.currentTurn = Color.WHITE;


    Position whitePieceStart = new Position(4, 1);
    Position whitePieceEnd = new Position(4, 3);
    Move invalidMove = new Move(whitePieceStart, whitePieceEnd);
    Piece pieceToBeTested = new Piece(Type.SINGLE, Color.WHITE);

    CuT.setPieceByPosition(whitePieceStart, pieceToBeTested);
    CuT.makeMove(invalidMove);

    assertTrue((CuT.getRows().get(4).getSpaces().get(3).getPiece()==null ));

  }

  @Test
  public void invalidMoveWhiteRed() throws Exception {
    final Board CuT = new Board();
    CuT.currentTurn = Color.WHITE;


    Position whitePieceStart = new Position(4, 1);
    Position whitePieceEnd = new Position(4, 3);
    Move invalidMove = new Move(whitePieceStart, whitePieceEnd);
    Piece pieceToBeTested = new Piece(Type.KING, Color.WHITE);

    CuT.setPieceByPosition(whitePieceStart, pieceToBeTested);
    CuT.makeMove(invalidMove);

    assertTrue((CuT.getRows().get(4).getSpaces().get(3).getPiece()==null ));

  }


  @Test
  public void invalidMoveRed() throws Exception {
    final Board CuT = new Board();
    CuT.currentTurn = Color.WHITE;


    Position whitePieceStart = new Position(3, 0);
    Position whitePieceEnd = new Position(3, 2);
    Move invalidMove = new Move(whitePieceStart, whitePieceEnd);
    Piece pieceToBeTested = new Piece(Type.SINGLE, Color.WHITE);


    CuT.setPieceByPosition(whitePieceStart, pieceToBeTested);
    CuT.makeMove(invalidMove);

    assertTrue((CuT.getRows().get(3).getSpaces().get(2).getPiece()==null ));

  }

  @Test
  public void invalidMoveRedKing() throws Exception {
    final Board CuT = new Board();
    CuT.currentTurn = Color.WHITE;


    Position whitePieceStart = new Position(3, 0);
    Position whitePieceEnd = new Position(3, 2);
    Move invalidMove = new Move(whitePieceStart, whitePieceEnd);
    Piece pieceToBeTested = new Piece(Type.KING, Color.WHITE);


    CuT.setPieceByPosition(whitePieceStart, pieceToBeTested);
    CuT.makeMove(invalidMove);

    assertTrue((CuT.getRows().get(3).getSpaces().get(2).getPiece()==null ));

  }

  @Test
  public void imaginaryPiece() throws Exception {
    final Board CuT = new Board();
    CuT.currentTurn = Color.BLACK;



    Position whitePieceStart = new Position(3, 0);
    Position whitePieceEnd = new Position(3, 2);
    Move imaginaryMove = new Move(whitePieceStart, whitePieceEnd);
    Piece pieceToBeTested = new Piece(Type.SINGLE, Color.BLACK);


    CuT.setPieceByPosition(whitePieceStart, pieceToBeTested);
    CuT.makeMove(imaginaryMove);

    assertTrue((CuT.getRows().get(3).getSpaces().get(2).getPiece()==null ));

  }

  @Test
  public void notDiagonalMoveRed() throws Exception {
    final Board CuT = new Board();
    CuT.currentTurn = Color.RED;

    // setting up the board pieces
    //Position whitePieceToDelete = new Position(6, 1);
    //Position redPieceToDelete2 = new Position(0, 7);

    Position whitePieceStart = new Position(3, 0);
    Position whitePieceEnd = new Position(4, 0);
    Move invalidMove = new Move(whitePieceStart, whitePieceEnd);
    Piece pieceToBeTested = new Piece(Type.SINGLE, Color.RED);

    //CuT.setPieceByPosition(whitePieceToDelete, null);
    //CuT.setPieceByPosition(redPieceToDelete2, null);
    CuT.setPieceByPosition(whitePieceStart, pieceToBeTested);
    CuT.makeMove(invalidMove);

    assertTrue((CuT.getRows().get(4).getSpaces().get(0).getPiece()==null ));

  }

  @Test
  public void notDiagonalMoveRedKing() throws Exception {
    final Board CuT = new Board();
    CuT.currentTurn = Color.RED;

    // setting up the board pieces
    //Position whitePieceToDelete = new Position(6, 1);
    //Position redPieceToDelete2 = new Position(0, 7);

    Position whitePieceStart = new Position(3, 0);
    Position whitePieceEnd = new Position(4, 0);
    Move invalidMove = new Move(whitePieceStart, whitePieceEnd);
    Piece pieceToBeTested = new Piece(Type.KING, Color.RED);

    //CuT.setPieceByPosition(whitePieceToDelete, null);
    //CuT.setPieceByPosition(redPieceToDelete2, null);
    CuT.setPieceByPosition(whitePieceStart, pieceToBeTested);
    CuT.makeMove(invalidMove);

    assertTrue((CuT.getRows().get(4).getSpaces().get(0).getPiece()==null ));

  }

  @Test
  public void notDiagonalMoveWhite() throws Exception {
    final Board CuT = new Board();
    CuT.currentTurn = Color.WHITE;


    Position whitePieceStart = new Position(5, 0);
    Position whitePieceEnd = new Position(4, 0);
    Move invalidMove = new Move(whitePieceStart, whitePieceEnd);
    Piece pieceToBeTested = new Piece(Type.SINGLE, Color.WHITE);

    CuT.setPieceByPosition(whitePieceStart, pieceToBeTested);
    CuT.makeMove(invalidMove);

    assertTrue((CuT.getRows().get(4).getSpaces().get(0).getPiece()==null ));


  }

  @Test
  public void notDiagonalMoveWhiteKing() throws Exception {
    final Board CuT = new Board();
    CuT.currentTurn = Color.WHITE;


    Position whitePieceStart = new Position(5, 0);
    Position whitePieceEnd = new Position(4, 0);
    Move invalidMove = new Move(whitePieceStart, whitePieceEnd);
    Piece pieceToBeTested = new Piece(Type.KING, Color.WHITE);

    CuT.setPieceByPosition(whitePieceStart, pieceToBeTested);
    CuT.makeMove(invalidMove);

    assertTrue((CuT.getRows().get(4).getSpaces().get(0).getPiece()==null ));


  }

  @Test
  public void testMessage() throws Exception{
    final Board CuT = new Board();
    CuT.currentTurn = Color.WHITE;

    Position whitePieceStart = new Position(5, 0);
    Position whitePieceEnd = new Position(4, 0);
    Move invalidMove = new Move(whitePieceStart, whitePieceEnd);
    Piece pieceToBeTested = new Piece(Type.SINGLE, Color.WHITE);

    //CuT.setPieceByPosition(whitePieceToDelete, null);
    //CuT.setPieceByPosition(redPieceToDelete2, null);
    CuT.setPieceByPosition(whitePieceStart, pieceToBeTested);
    Message receivedInvalid=CuT.validateMove(invalidMove);

    assertTrue(receivedInvalid.getType()==MessageType.ERROR);


    whitePieceEnd=new Position(4,1);

    Move validMove = new Move(whitePieceStart, whitePieceEnd);

    CuT.setPieceByPosition(whitePieceStart, pieceToBeTested);
    Message receivedValid=CuT.validateMove(validMove);

    assertTrue(receivedValid.getType()==MessageType.INFO);



  }

  @Test
  public void moveRedRight() throws Exception {
    final Board CuT = new Board();
    CuT.currentTurn = Color.RED;

    // setting up the board pieces
    Position whitePieceToDelete = new Position(6, 7);

    Position redPieceStart = new Position(4, 5);
    Position redPieceEnd = new Position(6, 7);
    Move invalidMove = new Move(redPieceStart, redPieceEnd);
    Piece pieceToBeTested = new Piece(Type.SINGLE, Color.RED);

    CuT.setPieceByPosition(whitePieceToDelete, null);
    CuT.setPieceByPosition(redPieceStart, pieceToBeTested);
    CuT.makeMove(invalidMove);

    assertTrue((CuT.getRows().get(4).getSpaces().get(5).getPiece()==null ) && ((CuT.getRows().get(5).getSpaces().get(6).getPiece()==null)) && ((CuT.getRows().get(6).getSpaces().get(7).getPiece()!=null)) );

    final Board CuTK = new Board();
    CuTK.currentTurn = Color.RED;
    Piece kingPieceToBeTested = new Piece(Type.KING, Color.RED);

    CuTK.setPieceByPosition(whitePieceToDelete, null);
    CuTK.setPieceByPosition(redPieceStart, kingPieceToBeTested);
    CuTK.makeMove(invalidMove);

    assertTrue((CuTK.getRows().get(4).getSpaces().get(5).getPiece()==null ) && ((CuTK.getRows().get(5).getSpaces().get(6).getPiece()==null)) && ((CuTK.getRows().get(6).getSpaces().get(7).getPiece()!=null)) );

  }

}