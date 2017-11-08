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
    //Position redPieceToDelete2 = new Position(0, 7);

    Position whitePieceStart = new Position(3, 0);
    Position whitePieceEnd = new Position(1, 2);
    Move capturingMove = new Move(whitePieceStart, whitePieceEnd);
    Piece pieceToBeTested = new Piece(Type.SINGLE, Color.WHITE);

    CuT.setPieceByPosition(redPieceToDelete, null);
    //CuT.setPieceByPosition(redPieceToDelete2, null);
    CuT.setPieceByPosition(whitePieceStart, pieceToBeTested);
    CuT.makeMove(capturingMove);


    //Piece pieceAfterMove = CuT.getSpaceByPosition(whitePieceEnd).getPiece();
    assertTrue( (CuT.getRows().get(1).getSpaces().get(2).getPiece()!=null )&& (CuT.getRows().get(3).getSpaces().get(0).getPiece() ==null) && (CuT.getRows().get(2).getSpaces().get(1).getPiece()==null));

    //capturing move test
  }


  @Test
  public void testRedCapturingWhite() throws Exception {
    final Board CuT = new Board();
    CuT.currentTurn = Color.RED;

    // setting up the board pieces
    Position whitePieceToDelete = new Position(6, 1);
    //Position redPieceToDelete2 = new Position(0, 7);

    Position redPieceStart = new Position(4, 3);
    Position redPieceEnd = new Position(6, 1);
    Move capturingMove = new Move(redPieceStart, redPieceEnd);
    Piece pieceToBeTested = new Piece(Type.SINGLE, Color.RED);

    CuT.setPieceByPosition(whitePieceToDelete, null);
    //CuT.setPieceByPosition(redPieceToDelete2, null);
    CuT.setPieceByPosition(redPieceStart, pieceToBeTested);
    CuT.makeMove(capturingMove);


    //Piece pieceAfterMove = CuT.getSpaceByPosition(whitePieceEnd).getPiece();
    assertTrue( (CuT.getRows().get(6).getSpaces().get(1).getPiece()!=null )&& (CuT.getRows().get(5).getSpaces().get(2).getPiece() ==null) && (CuT.getRows().get(4).getSpaces().get(3).getPiece()==null));

    //capturing move test
  }

  @Test
  public void invalidMoveWhite() throws Exception {
    final Board CuT = new Board();
    CuT.currentTurn = Color.RED;

    // setting up the board pieces
    //Position whitePieceToDelete = new Position(6, 1);
    //Position redPieceToDelete2 = new Position(0, 7);

    Position whitePieceStart = new Position(4, 1);
    Position whitePieceEnd = new Position(4, 3);
    Move invalidMove = new Move(whitePieceStart, whitePieceEnd);
    Piece pieceToBeTested = new Piece(Type.SINGLE, Color.RED);

    //CuT.setPieceByPosition(whitePieceToDelete, null);
    //CuT.setPieceByPosition(redPieceToDelete2, null);
    CuT.setPieceByPosition(whitePieceStart, pieceToBeTested);
    CuT.makeMove(invalidMove);

    assertTrue((CuT.getRows().get(4).getSpaces().get(3).getPiece()==null ));
    //Piece pieceAfterMove = CuT.getSpaceByPosition(whitePieceEnd).getPiece();
    //assertTrue( (CuT.getRows().get(6).getSpaces().get(1).getPiece()!=null )&& (CuT.getRows().get(5).getSpaces().get(2).getPiece() ==null) && (CuT.getRows().get(4).getSpaces().get(3).getPiece()==null));

    //capturing move test
  }


  @Test
  public void invalidMoveRed() throws Exception {
    final Board CuT = new Board();
    CuT.currentTurn = Color.WHITE;

    // setting up the board pieces
    //Position whitePieceToDelete = new Position(6, 1);
    //Position redPieceToDelete2 = new Position(0, 7);

    Position whitePieceStart = new Position(3, 0);
    Position whitePieceEnd = new Position(3, 2);
    Move invalidMove = new Move(whitePieceStart, whitePieceEnd);
    Piece pieceToBeTested = new Piece(Type.SINGLE, Color.WHITE);

    //CuT.setPieceByPosition(whitePieceToDelete, null);
    //CuT.setPieceByPosition(redPieceToDelete2, null);
    CuT.setPieceByPosition(whitePieceStart, pieceToBeTested);
    CuT.makeMove(invalidMove);

    assertTrue((CuT.getRows().get(3).getSpaces().get(2).getPiece()==null ));
    //Piece pieceAfterMove = CuT.getSpaceByPosition(whitePieceEnd).getPiece();
    //assertTrue( (CuT.getRows().get(6).getSpaces().get(1).getPiece()!=null )&& (CuT.getRows().get(5).getSpaces().get(2).getPiece() ==null) && (CuT.getRows().get(4).getSpaces().get(3).getPiece()==null));

    //capturing move test
  }

}