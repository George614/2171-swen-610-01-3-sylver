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
}