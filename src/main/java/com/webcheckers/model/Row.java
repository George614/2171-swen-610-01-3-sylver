package com.webcheckers.model;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;

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

    public Row(int index) {
        this.index = index;
        spaces = new ArrayList<>();
        for (int i = 0; i < 8; i++) {
            spaces.add(new Space(i,true));  // for testing
        }
    }

    //
    //  Methods
    //

    public List<Space> getSpaces() { return spaces; }

    @Override
    public Iterator<Space> iterator() {
        return spaces.iterator();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Row spaces1 = (Row) obj;
        return index == spaces1.index &&
                Objects.equals(spaces, spaces1.spaces);
    }

    @Override
    public int hashCode() {
        return Objects.hash(index, spaces);
    }

    @Override
    public String toString() {
        return "Row{" +
                "index=" + index +
                ", spaces=" + spaces +
                '}';
    }
}
