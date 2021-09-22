package com.github;

import java.util.Enumeration;
import java.util.Iterator;

/**
 * @author 康盼Java开发工程师
 */
public class EnumerationIter<E> implements Iterator<E>, Iterable<E> {

    private final Enumeration<E> enumeration;

    public EnumerationIter(Enumeration<E> enumeration) {
        this.enumeration = enumeration;
    }

    @Override
    public void remove() {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean hasNext() {
        return this.enumeration.hasMoreElements();
    }

    @Override
    public E next() {
        return enumeration.nextElement();
    }

    @Override
    public Iterator<E> iterator() {
        return this;
    }
}
