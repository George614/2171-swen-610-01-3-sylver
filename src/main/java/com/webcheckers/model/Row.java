package com.webcheckers.model;

import java.util.Iterator;
import java.util.List;

/**
 *  Class to represent Row (made up of Spaces)
 *  Author: <a href="mailto:mfabbu@rit.edu">Matt Arlauckas</a>
 *  Date: 2017-10-25
 */
public class Row implements Iterable<Space> {

    //
    //  Constants
    //


    //
    //  Attributes
    //

    private int index = 0;
    private List<Space> spaces;

    //
    //  Constructors
    //

    //
    //  Methods
    //

    @Override
    public Iterator<Space> iterator() {
        return spaces.iterator();
    }

    @Override
    public boolean equals(Object obj) {
        return true;    // placeholder
    }

    @Override
    public int hashCode() {
        return 1;   // placeholder
    }
}
