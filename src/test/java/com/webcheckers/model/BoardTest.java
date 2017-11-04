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
}