package com.learning;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Collection;
import java.util.List;
import java.util.ListIterator;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;

class ArrayListTest {
    private ArrayList <Integer> testInstance;
    private ListIterator<Integer> listIterator;

    @BeforeEach
    public void setUp() {
        testInstance = new ArrayList<>();
        listIterator = testInstance.listIterator();
    }

    @Test
    public void testSizeWhenSizeIs0() {
        assertEquals(0, testInstance.size());
    }

    @Test
    public void testIsEmptyWhenEmpty() {
        assertTrue(testInstance.isEmpty());
    }

    @Test
    public void testToArrayWhenInputArrayHaveSizeOne() {
        testInstance.add(1);
        testInstance.add(2);
        testInstance.add(3);

        final Integer[] input = new Integer[1];

        final Integer[] result = testInstance.toArray(input);
        assertNotEquals(input, result);
        assertEquals((Integer)1, result[0]);
        assertEquals((Integer)2, result[1]);
        assertEquals((Integer)3, result[2]);
        assertEquals(3, result.length);
    }

    @Test
    public void testToArrayWhenInputArrayHaveCorrectSize() {
        testInstance.add(1);
        testInstance.add(2);
        testInstance.add(3);

        final Integer[] input = new Integer[3];

        final Integer[] result = testInstance.toArray(input);
        assertArrayEquals(input, result);
    }

    @Test
    public void testContains() {
        testInstance.add(1);
        testInstance.add(2);
        testInstance.add(3);
        testInstance.add(4);

        assertTrue(testInstance.contains(3));
        assertFalse(testInstance.contains(0));
    }

    @Test
    public void testAdd() {
        testInstance.add(1);
        testInstance.add(1);

        assertEquals(2, testInstance.size());
        assertFalse(testInstance.isEmpty());
    }

    @Test
    public void testRemoveObjectFirstElement() {
        testInstance.add(1);
        testInstance.add(2);
        Object a = 1;
        testInstance.remove(a);

        assertEquals(1, testInstance.size());
        assertEquals((Integer)2, (Integer)testInstance.get(0), "Method remove(final Object o) is wrong");
        assertFalse(testInstance.isEmpty());
    }

    @Test
    public void testRemoveObjectLastElement() {
        testInstance.add(1);
        testInstance.add(2);
        Object a = 2;
        testInstance.remove(a);

        assertEquals(1, testInstance.size());
        assertEquals((Integer)1, (Integer)testInstance.get(0), "Method remove(final Object o) is wrong");
        assertFalse(testInstance.isEmpty());
    }

    @Test
    public void testContainsAll() {
        final Collection<Integer> testInstance2 = new ArrayList<>();
        testInstance.add(1);
        testInstance.add(2);

        testInstance2.add(2);
        testInstance2.add(1);

        assertTrue(testInstance.containsAll(testInstance2));
    }

    @Test
    public void testAddAll() {
        testInstance.add(1);
        testInstance.add(2);

        testInstance.add(3);
        testInstance.add(4);

        assertTrue(testInstance.contains(3));
        assertTrue(testInstance.contains(4));
    }

    @Test
    public void testRemoveAll() {
        final Collection<Integer> testInstance2 = new ArrayList<>();
        testInstance.add(1);
        testInstance.add(2);

        testInstance2.add(2);
        testInstance2.add(3);

        testInstance.removeAll(testInstance2);

        assertEquals(1, testInstance.size());
        assertTrue(testInstance.contains(1));
    }

    @Test
    public void testRetainAll() {
        final Collection<Integer> testInstance2 = new ArrayList<>();
        testInstance.add(1);
        testInstance.add(2);

        testInstance2.add(2);
        testInstance2.add(3);

        testInstance.retainAll(testInstance2);

        assertEquals(1, testInstance.size());
        assertTrue(testInstance.contains(2));
    }

    @Test
    public void testClear() {
        testInstance.add(1);
        testInstance.add(1);

        testInstance.clear();

        assertTrue(testInstance.isEmpty());
        assertEquals(0, testInstance.size());
    }

    @Test
    public void testRemoveIndexFirst() {
        testInstance.add(1);
        testInstance.add(2);

        testInstance.remove(0);

        assertEquals(1, testInstance.size());
        assertEquals((Integer)2, (Integer)testInstance.get(0), "Method remove(final int index) is wrong");
        assertFalse(testInstance.isEmpty());

    }

    @Test
    public void testRemoveIndexLast() {
        testInstance.add(1);
        testInstance.add(2);

        testInstance.remove(1);

        assertEquals(1, testInstance.size());
        assertEquals((Integer)1, (Integer)testInstance.get(0), "Method remove(final int index) is wrong");
        assertFalse(testInstance.isEmpty());
    }

    @Test
    public void testHasNextWhenEmptyCollection() {
        assertFalse(listIterator.hasNext(), "Your hasPrevious() is wrong.");
    }

    @Test
    public void testHasNextWhenIteratorAtTheEndOfTheCollection() {
        testInstance.add(1);
        testInstance.add(2);
        testInstance.add(20);

        listIterator = testInstance.listIterator(3);
        assertFalse(listIterator.hasNext(), "The ArrayList  has no previous items! Your previousIndex() is wrong.");
        listIterator = testInstance.listIterator(1);
        assertTrue(listIterator.hasNext(), "The ArrayList has the previous elements! Your previousIndex() is wrong.");
    }

    @Test
    public void testNextOnEmptyCollection() {
        try {
            listIterator.next();
            fail("next do not throw the Exception when no more elements");
        } catch (final NoSuchElementException e) {}
    }

    @Test
    public void testNext() {
        List<Character> testInstance2 = new ArrayList<>();
        ListIterator<Character> listIterator2 = testInstance2.listIterator();

        testInstance2.add('a');
        testInstance2.add('b');
        testInstance2.add('c');
        testInstance2.add('d');

        assertSame('a', listIterator2.next(), "next() returns wrong element!");
        assertSame('b', listIterator2.next(), "next() returns wrong element!");
        assertSame('c', listIterator2.next(), "next() returns wrong element!");
        assertSame('d', listIterator2.next(), "next() returns wrong element!");

        try {
            listIterator2.next();
            fail("next() does not throw the Exception when no more elements");
        } catch (final NoSuchElementException e) {}
    }

    @Test
    public void testHasPreviousWhenEmptyCollection() {
        assertFalse(listIterator.hasPrevious(), "Your hasPrevious() is wrong.");
    }

    @Test
    public void testHasPreviousWhenIteratorAtTheEndOfTheCollection() {
        testInstance.add(1);
        testInstance.add(2);
        assertFalse(listIterator.hasPrevious(), "The ArrayList  has no previous items! Your previousIndex() is wrong.");
        listIterator = testInstance.listIterator(1);
        assertTrue(listIterator.hasPrevious(), "The ArrayList has the previous elements! Your previousIndex() is wrong.");
    }

    @Test
    public void testPreviousAfterNextWithOneElement() {
        testInstance.add(1);
        testInstance.add(2);
        testInstance.add(3);
        listIterator.next();

        final Integer next = listIterator.next();
        final Integer previous = listIterator.previous();
        assertEquals(next, previous, "From the documentation: \n" +
                "Note that alternating calls to next() and previous()" +
                " will return the same element repeatedly ");

        assertEquals(listIterator.next(), listIterator.previous(), "From the documentation: \n" +
                "Note that alternating calls to next() and previous()" +
                " will return the same element repeatedly ");
    }

    @Test
    public void testPreviousAfterNextMoreElements() {
        testInstance.add(0);
        testInstance.add(1);
        testInstance.add(2);
        testInstance.add(3);
        testInstance.add(4);

        listIterator.next();
        listIterator.next();
        assertEquals((Integer) 2, listIterator.next(), "From the documentation: \n" +
                "Note that alternating calls to next() and previous()" +
                " will return the same element repeatedly ");
        assertEquals(3, listIterator.nextIndex(), "After next() index should be greater");

        assertEquals((Integer) 2, listIterator.previous(), "From the documentation: \n" +
                "Note that alternating calls to next() and previous()" +
                " will return the same element repeatedly");
        assertEquals(2, listIterator.nextIndex(), "After previous() index should be less");

        assertEquals((Integer) 2, listIterator.next(), "From the documentation: \n" +
                "Note that alternating calls to next() and previous()" +
                " will return the same element repeatedly");
        assertEquals(3, listIterator.nextIndex(), "After next() index should be greater");

        assertEquals((Integer) 2, listIterator.previous(), "From the documentation: \n" +
                "Note that alternating calls to next() and previous()" +
                " will return the same element repeatedly");
        assertEquals(2, listIterator.nextIndex(), "After previous() index should be less");
    }

    @Test
    public void testPreviousIndex() {
        testInstance.add(1);
        listIterator.next();
        assertEquals(0, listIterator.previousIndex(), "Your previousIndex() is wrong.");
    }

    @Test
    public void testPreviousIndexWhenItEqualsTo1() {
        testInstance.add(1);
        testInstance.add(1);
        listIterator.next();
        listIterator.next();

        assertEquals(1, listIterator.previousIndex(), "Your previousIndex() is wrong.");
    }

    @Test
    public void testPreviousIndexWhenEmptyCollection() {
        assertEquals(-1, listIterator.previousIndex(), "In an empty collection, previousIndex() must return -1!");
    }

    @Test
    public void testPreviousWhenEmptyCollection() {
        try {
            listIterator.previous();
            fail("list iterator do not throw the Exception when called previous method on empty collection");
        } catch (final java.util.NoSuchElementException e) {}
    }

    @Test
    public void testAddInIteratorAfterNext() {
        List<Character> testInstance2 = new ArrayList<>();
        ListIterator<Character> listIterator2 = testInstance2.listIterator();

        testInstance2.add('a');
        testInstance2.add('b');
        testInstance2.add('c');
        testInstance2.add('d');

        assertSame('a', listIterator2.next(), "next() returns wrong element!");
        assertSame('b', listIterator2.next(), "next() returns wrong element!");

        assertSame(1, listIterator2.previousIndex(), "previousIndex is wrong");
        assertSame(2, listIterator2.nextIndex(), "nextIndex is wrong");

        assertSame('b', listIterator2.previous(), "previous element is");
        assertSame(0, listIterator2.previousIndex(), "previousIndex is wrong");
        assertSame(1, listIterator2.nextIndex(), "nextIndex is wrong");

        assertEquals(4, testInstance2.size(), "Wrong size!");

        listIterator2.add('y');

        assertSame(1, listIterator2.previousIndex(), "previousIndex is wrong");
        assertSame(2, listIterator2.nextIndex(), "nextIndex is wrong");

        assertSame('y', listIterator2.previous(), "previous element is");
        assertSame(0, listIterator2.previousIndex(), "previousIndex is wrong");
        assertSame(1, listIterator2.nextIndex(), "nextIndex is wrong");

        listIterator2.add('z');

        assertEquals(6, testInstance2.size(), "size");

        assertSame(1, listIterator2.previousIndex(), "previousIndex is wrong");
        assertSame(2, listIterator2.nextIndex(), "nextIndex is wrong");

        assertSame('z', listIterator2.previous(), "previous element is");
        assertSame(0, listIterator2.previousIndex(), "previousIndex is wrong");
        assertSame(1, listIterator2.nextIndex(), "nextIndex is wrong");
    }

    @Test
    public void testAddInIteratorWhenEmptyList() {
        listIterator.add(1);
        listIterator.add(2);
        assertSame(1, listIterator.previousIndex(), "previousIndex");
        assertSame(2, listIterator.previous(), "previous element");
        assertSame(1, testInstance.get(0), "First element");
        assertEquals(2, testInstance.size(), "size");
    }

    @Test
    public void testAddInIteratorWhenIsNotEmptyListToTheBeginning() {
        testInstance.add(0);
        testInstance.add(0);
        testInstance.add(0);
        listIterator.add(1);
        assertSame(0, listIterator.previousIndex(), "previousIndex");
        assertSame(1, listIterator.nextIndex(), "nextIndex");
        assertSame(1, listIterator.previous(), "previous element");
        assertSame(1, testInstance.get(0), "Get first element");
        assertEquals(4, testInstance.size(), "size");
    }

    @Test
    public void testAddInIteratorLastIsNotSet() {
        listIterator.add(1);
        listIterator.add(2);
        listIterator.add(3);
        try {
            listIterator.set(222);
            fail("set method can not be called after add (E). Wrong last element.");
        } catch (final IllegalStateException e){}

        listIterator.add(4);
    }

    @Test
    public void testSetWhenNeitherNextNorPreviousHaveBeenCalled() {
        testInstance.add(1);
        try {
            listIterator.set(null);
            fail("set method do not throw IllegalStateException the if neither next nor previous have been called");
        } catch (final IllegalStateException e){}
        listIterator.add(2);
        try {
            listIterator.set(null);
            fail("set method do not throw IllegalStateException the if neither next nor previous have been called");
        } catch (final IllegalStateException e){}
    }

    @Test
    public void testSetAfterNext() {
        testInstance.add(1);
        testInstance.add(3);
        listIterator.next();
        listIterator.next();
        listIterator.set(2);
        assertEquals((Integer)2, testInstance.get(1), "set() should replaces the last element returned by next() or previous()");
    }

    @Test
    public void testSetAfterPrevious() {
        testInstance.add(1);
        testInstance.add(3);
        testInstance.add(4);
        listIterator.next();
        listIterator.next();
        listIterator.next();
        listIterator.previous();
        listIterator.set(3);
        listIterator.previous();
        listIterator.set(2);

        assertEquals((Integer)1, testInstance.get(0), "set() should replaces the last element returned by next() or previous()");
        assertEquals((Integer)2, testInstance.get(1), "set() should replaces the last element returned by next() or previous()");
        assertEquals((Integer)3, testInstance.get(2), "set() should replaces the last element returned by next() or previous()");
    }

    @Test
    public void testRemoveBeforeNext() {
        testInstance.add(2);
        try {
            listIterator.remove();
            fail("remove do not throw the Exception when called before next");
        } catch (final IllegalStateException e) {}
    }

    @Test
    public void testRemoveTwoTimeInTheRow() {
        testInstance.add(1);
        testInstance.add(2);
        testInstance.add(3);
        testInstance.add(4);
        testInstance.add(5);

        listIterator.next();

        listIterator.remove();
        assertEquals(4, testInstance.size(), "Expected collection size is 4, however actual is not");
        try {
            listIterator.remove();
            fail("remove do not throw the Exception when called twice");
        } catch (final IllegalStateException e) {}
    }

    @Test
    public void testRemoveAfterThePrevious() {
        listIterator.add(0);
        listIterator.add(1);
        listIterator.add(2);
        listIterator.add(3);
        listIterator.add(4);

        listIterator.previous();
        listIterator.previous();
        try {
            listIterator.remove();
        } catch (final IllegalStateException e) {
            throw new RuntimeException("remove() call can only be made once per call to next() or previous(). " +
                    "It can be made only if add(E) has not been called after the last call to next() or previous().");
        }

        assertEquals(4, testInstance.size(), "Expected collection size is 4, however actual is not");
        assertEquals((Integer)4, testInstance.get(3), "Expected element [3] == 4, however actual is not");
    }
}