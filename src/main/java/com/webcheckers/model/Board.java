package com.webcheckers.model;

import java.util.Iterator;
import java.util.List;

/**
 *  Class to represent the Board (made up of Rows)
 *  Author: <a href="mailto:mfabbu@rit.edu">Matt Arlauckas</a>
 *  Date: 2017-10-25
 */
public class Board implements Iterable<Row> {

    //
    //  Constants
    //

    //
    //  Attributes
    //
    private int index = 0;
    private List<Row> rows;

    //
    //  Constructors
    //

    //
    //  Methods
    //

    @Override
    public Iterator<Row> iterator() {
        return rows.iterator();
    }

}
