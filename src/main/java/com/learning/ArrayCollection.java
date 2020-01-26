package com.learning;

import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;

public class ArrayCollection<T> implements Collection<T> {

    private T[] m = (T[]) new Object[1];

    private int size = 0;

    @Override
    public int size() {
        return this.size;
    }

    @Override
    public boolean isEmpty() {
        return this.size() == 0;
    }

    @Override
    public boolean contains(final Object o) {
        for (int i = 0; i < size; i++) {
            if (m[i].equals(o)) return true;
        }
        return false;
    }

    @Override
    public Iterator<T> iterator() {
        return new ElementsIterator();
    }

    @Override
    public Object[] toArray() {
        final Object[] newM = new Object[this.size()];
        System.arraycopy(m, 0, newM, 0, this.size());
        return newM;
    }

    @Override
    /*This method may prove to be too difficult.
    he test is not covered.*/
    public <T1> T1[] toArray(T1[] a) {
        if (a.length >= this.size()) {
            System.arraycopy(m, 0, a, 0, size());
            return a;
        } else {
            return (T1[]) Arrays.copyOf(this.m, this.size(), a.getClass());
        }
    }

    @Override
    public boolean add(final T t) {
        if (size == m.length) {
            m = Arrays.copyOf(m, m.length * 2);
        }
        m[size++] = t;
        return true;
    }

    @Override
    public boolean remove(final Object o) {
        if (m[size - 1].equals(o)) {
            size--;
            return true;
        }

        for (int i = 0; i < size(); i++) {
            if (m[i].equals(o)) {
                System.arraycopy(m, i + 1, m, i, this.size() - i - 1);
                size--;
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean containsAll(final Collection<?> c) {
        for (final Object item : c) {
            if (!this.contains(item)) return false;
        }
        return true;
    }

    @Override
    public boolean addAll(final Collection<? extends T> c) {
        for (final T item : c) {
            add(item);
        }
        return true;
    }

    @Override
    public boolean removeAll(final Collection<?> c) {
        for (final Object item : c) {
            remove(item);
        }
        return true;
    }

    @Override
    public boolean retainAll(final Collection<?> c) {
        for (int i = 0; i < size(); i++) {
            if (!c.contains(m[i])) {
                remove(m[i--]);
            }
        }
        return true;

    }

    @Override
    public void clear() {
        m = (T[]) new Object[1];
        size = 0;
    }

    private class ElementsIterator implements Iterator<T> {
        private int size;

        @Override
        public boolean hasNext() {
            return ArrayCollection.this.size() > size;
        }

        @Override
        public T next() {
            return ArrayCollection.this.m[size++];
        }
    }
}
