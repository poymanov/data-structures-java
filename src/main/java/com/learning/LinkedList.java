package com.learning;

import java.util.*;

public class LinkedList<T> implements List<T> {

    private Item<T> firstInList = null;
    private Item<T> lastInList = null;

    private int size;

    @Override
    public int size() {
        return this.size;
    }

    @Override
    public boolean isEmpty() {
        return this.size() == 0;
    }

    @Override
    public Iterator<T> iterator() {
        return new ElementsIterator(0);
    }

    @Override
    public Object[] toArray() {
        T[] array = (T[])new Object[size()];
        for(int i = 0; i < size(); i ++) {
            array[i] = get(i);
        }
        return array;
    }

    @Override
    public <T1> T1[] toArray(T1[] a) {
        if (a.length < size)
            a = (T1[])java.lang.reflect.Array.newInstance(
                    a.getClass().getComponentType(), size);
        int i = 0;
        Object[] result = a;
        for (Item<T> x = firstInList; x != null; x = x.nextItem)
            result[i++] = x.element;

        if (a.length > size)
            a[size] = null;

        return a;
    }

    //--
    @Override
    public boolean contains(final Object o) {
        Item<T> currentItem = firstInList;
        while (currentItem != null) {
            if(currentItem.element == o) return true;
            currentItem = currentItem.nextItem;
        }

        return false;
    }

    //--
    @Override
    public boolean add(final T newElement) {
        final Item<T> item = new Item<>(newElement, lastInList, null);

        if(size() == 0) {
            firstInList = item;
        }

        if(lastInList != null) lastInList.nextItem = item;
        lastInList = item;

        size ++;

        return true;
    }

    //---
    @Override
    public void add(final int index, final T element) {
        if(index < 0 || index > size()) throw new IndexOutOfBoundsException();

        final Item<T> targetItem = getItemByIndex(index);

        if(targetItem == null) {
            final Item<T> insertingItem = new Item<>(element, lastInList, null);
            lastInList.nextItem = insertingItem;
            return;
        }

        final Item<T> prev = targetItem.prevItem;
        final Item<T> insertingItem = new Item<>(element, prev, targetItem);

        if(prev != null) prev.nextItem = insertingItem; else firstInList = insertingItem;
        targetItem.prevItem = insertingItem;
    }

    //--
    @Override
    public boolean retainAll(final Collection<?> c) {
        final List<T> toRemove = new LinkedList<>(); //!

        for (final T element : this) {
            if (!c.contains(element)) toRemove.add(element);
        }

        for(final T element : toRemove) {
            remove(element);
        }

        return true;
    }

    @Override
    public boolean remove(final Object o) {
        if(firstInList.element == o) {
            firstInList.nextItem.prevItem = null;
            firstInList = firstInList.nextItem;
            size --;
            return true;
        }

        if(lastInList.element == o) {
            lastInList.prevItem.nextItem = null;
            lastInList = lastInList.prevItem;
            size --;
            return true;
        }

        Item<T> current = firstInList;

        while(current != null) {
            if(current.element == o) {
                remove(current);
                return true;
            }
            current = current.nextItem;
        }

        return false;
    }

    //--
    @Override
    public T remove(final int index) throws IndexOutOfBoundsException{
        if(index < 0 || index >= size()) throw new IndexOutOfBoundsException();

        int i = 0;
        Item<T> current = firstInList;

        while(current != null) {
            if(i == index) {
                if(index == 0) {
                    firstInList = current.nextItem;
                }
                remove(current);
                return current.element;
            }
            current = current.nextItem;
            i++;
        }

        return null;
    }

    //--
    private void remove(final Item<T> current) {
        final Item<T> next = current.nextItem;
        final Item<T> prev = current.prevItem;

        if(prev != null) prev.nextItem = next;
        if(next != null) next.prevItem = prev;

        size --;
    }

    //--
    @Override
    public void clear() {
        firstInList.nextItem = null;
        lastInList.prevItem = null;
        size = 0;
    }

    //--
    @Override
    public int indexOf(final Object o) {
        int i = 0;
        Item<T> current = firstInList;

        while(current != null) {

            if(current.element == o) {
                return i;
            }

            current = current.nextItem;
            i++;
        }

        return -1;
    }

    //--
    @Override
    public T set(final int index, final T element) {
        if(index < 0 || index >= size()) {
            throw new IndexOutOfBoundsException();
        }

        final Item<T> item = getItemByIndex(index);
        if(item != null) {
            T toReturn = item.element;
            item.element = element;
            return toReturn;
        }

        throw new IndexOutOfBoundsException();
    }

    //--
    @Override
    public T get(final int index) {
        final Item<T> item = getItemByIndex(index);
        if(item != null) return item.element;

        throw new IndexOutOfBoundsException();
    }

    //--
    private Item<T> getItemByIndex(final int index) {
        int i = 0;
        Item<T> current = firstInList;
        while(current != null) {

            if(i == index) {
                return current;
            }

            current = current.nextItem;
            i++;
        }

        return null;
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
    public List<T> subList(final int start, final int end) {
        final List<T> sublist = new LinkedList<>();

        int i = 0;
        Item<T> current = firstInList;
        while(current != null) {

            if(i >= start) {
                sublist.add(current.element);
            }
            if(i >= end) {
                break;
            }

            current = current.nextItem;
            i++;
        }

        return sublist;
    }

    @Override
    public ListIterator<T> listIterator() {
        return new ElementsIterator(0);
    }

    @Override
    public ListIterator<T> listIterator(final int index) {
        return new ElementsIterator(index);
    }

    //---
    @Override
    public int lastIndexOf(final Object target) {
        int i = size - 1;
        Item<T> current = lastInList;
        while(current != null) {

            if(current.element == target) {
                return i;
            }

            current = current.prevItem;
            i--;
        }

        return -1;
    }


    @Override
    public boolean addAll(final int index, final Collection elements) {
        for(final Object element : elements) add((T)element);
        return true;
    }

    private class ElementsIterator implements ListIterator<T> {

        private Item<T> currentItemInIterator;
        private Item<T> lastReturnedItemFromIterator;

        private int index;
        private boolean canNotRemove;

        ElementsIterator(final int index) {
            this.index = index;
            currentItemInIterator = getItemByIndex(index);
            canNotRemove = true;
        }

        @Override
        public boolean hasNext() {
            if(currentItemInIterator == null) return false;

            if(index == 0) return true;

            return currentItemInIterator.nextItem != null;
        }

        @Override
        public T next() {
            if(isEmpty()) throw new NoSuchElementException();

            currentItemInIterator = getItemByIndex(index);
            lastReturnedItemFromIterator = currentItemInIterator;
            index++;

            canNotRemove = false;

            return lastReturnedItemFromIterator.element;
        }

        @Override
        public boolean hasPrevious() {
            if(currentItemInIterator == null || index == 0) return false;

            return true;
        }

        @Override
        public T previous() {
            if(isEmpty()) throw new NoSuchElementException();

            index --;
            currentItemInIterator = getItemByIndex(index);
            lastReturnedItemFromIterator = currentItemInIterator;

            canNotRemove = false;

            return lastReturnedItemFromIterator.element;
        }

        @Override
        public void add(final T element) {
            throw new UnsupportedOperationException();
        }

        @Override
        public void set(final T element) {
            if(currentItemInIterator == null || lastReturnedItemFromIterator == null) {
                throw new IllegalStateException();
            }

            currentItemInIterator.element = element;
        }

        @Override
        public int previousIndex(){
            return index - 1;
        }

        @Override
        public int nextIndex() {
            return index;
        }

        @Override
        public void remove() {
            if(canNotRemove) throw new IllegalStateException();

            canNotRemove = true;
            LinkedList.this.remove(lastReturnedItemFromIterator);
            index--;
        }
    }

    private static class Item<T> {

        private T element;

        private Item<T> nextItem;

        private Item<T> prevItem;

        Item(final T element, final Item<T> prevItem, final Item<T> nextItem) {
            this.element = element;
            this.nextItem = nextItem;
            this.prevItem = prevItem;
        }

        public Item<T> getNextItem() {
            return nextItem;
        }

        public Item<T> getPrevItem() {
            return prevItem;
        }
    }
}
