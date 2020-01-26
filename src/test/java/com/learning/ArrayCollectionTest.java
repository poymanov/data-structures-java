package com.learning;

import org.junit.jupiter.api.Test;

import java.util.Collection;

import static org.junit.jupiter.api.Assertions.*;

class ArrayCollectionTest {
    @Test
    public void testSizeWhenSizeIs0() {
        final Collection<Integer> testInstance = new ArrayCollection<>();
        testInstance.add(1);
        testInstance.remove(1);
        assertEquals(0, testInstance.size());
    }

    @Test
    public void testSizeWhenSizeIs1() {
        final Collection<Integer> testInstance = new ArrayCollection<>();
        testInstance.add(10);
        assertEquals(1, testInstance.size());
    }

    @Test
    public void testIsEmptyWhenEmpty() {
        final Collection<Integer> testInstance = new ArrayCollection<>();
        testInstance.add(1);
        testInstance.remove(1);
        assertTrue(testInstance.isEmpty());
    }

    @Test
    public void testIsEmptyWhenIsNoEmpty() {
        final Collection<Integer> testInstance = new ArrayCollection<>();
        testInstance.add(10);
        assertFalse(testInstance.isEmpty());
    }

    @Test
    public void testContains() {
        final Collection<Integer> testInstance = new ArrayCollection<>();
        testInstance.add(1);
        testInstance.add(2);

        assertTrue(testInstance.contains(1));
        assertFalse(testInstance.contains(0));
    }
    @Test
    public void testIterator() {
        final Collection<Integer> testInstance = new ArrayCollection<>();
        for (int i = 3; i < 15; i++) {
            testInstance.add(i);
        }
        int i = 0;
        System.out.println("\n testIterator");
        System.out.format("Size: %s\n", testInstance.size());
        for (final Integer iT : testInstance) {
            System.out.format("Element %s: %s \n", i++, iT);
        }
        if (i != testInstance.size()) {
            throw new RuntimeException("ElementsIterator don't working!!!!");
        }
    }

    @Test
    public void testToArray() {
        final Collection<Integer> testInstance = new ArrayCollection<>();
        for (int i = 1; i < 15; i++) {
            testInstance.add(i);
        }
        testInstance.remove(10);
        testInstance.remove(8);
        if (testInstance.toArray().length != testInstance.size()) {
            throw new RuntimeException("The returned array does not match the length of the collection.");
        }
    }

    @Test
    public void testAdd() {
        final Collection<Integer> testInstance = new ArrayCollection<>();
        for (int i = -10; i < 15; i++) {
            testInstance.add(i);
        }

        assertEquals(25, testInstance.size());
        assertFalse(testInstance.isEmpty());

    }

    @Test
    public void testRemoveFirstElement() {
        final Collection<Integer> testInstance = new ArrayCollection<>();
        testInstance.add(1);
        testInstance.add(2);
        testInstance.remove(1);

        assertEquals(1, testInstance.size());
        assertFalse(testInstance.isEmpty());
    }

    @Test
    public void testRemoveCenterElement() {
        final Collection<Integer> testInstance = new ArrayCollection<>();
        testInstance.add(1);
        testInstance.add(2);
        testInstance.add(3);
        testInstance.add(4);
        testInstance.add(5);
        testInstance.remove(3);

        assertEquals(4, testInstance.size());
        assertFalse(testInstance.isEmpty());
    }

    @Test
    public void testRemoveLastElement() {
        final Collection<Integer> testInstance = new ArrayCollection<>();
        testInstance.add(1);
        testInstance.add(2);
        testInstance.add(3);
        testInstance.add(4);
        testInstance.remove(4);

        assertEquals(3, testInstance.size());
        assertFalse(testInstance.isEmpty());
    }

    @Test
    public void testContainsAll() {
        final Collection<Integer> testInstance = new ArrayCollection<>();
        final Collection<Integer> testInstance2 = new ArrayCollection<>();
        testInstance.add(1);
        testInstance.add(2);

        testInstance2.add(2);
        testInstance2.add(1);

        assertTrue(testInstance.containsAll(testInstance2));
    }

    @Test
    public void testAddAll() {
        final Collection<Integer> testInstance = new ArrayCollection<>();
        final Collection<Integer> testInstance2 = new ArrayCollection<>();
        testInstance.add(1);
        testInstance.add(2);

        testInstance2.add(3);
        testInstance2.add(4);
        testInstance.addAll(testInstance2);

        assertTrue(testInstance.contains(3));
        assertTrue(testInstance.contains(4));
    }

    @Test
    public void testRemoveAll() {
        final Collection<Integer> testInstance = new ArrayCollection<>();
        final Collection<Integer> testInstance2 = new ArrayCollection<>();
        testInstance.add(1);
        testInstance.add(2);
        testInstance.add(3);
        testInstance.add(4);

        testInstance2.add(2);
        testInstance2.add(3);

        testInstance.removeAll(testInstance2);

        assertEquals(2, testInstance.size());
        assertTrue(testInstance.contains(1));
        assertFalse(testInstance.contains(2));
    }

    @Test
    public void testRetainAll3() {
        final Collection<Integer> testInstance = new ArrayCollection<>();
        final Collection<Integer> testInstance2 = new ArrayCollection<>();
        testInstance.add(1);
        testInstance.add(2);
        testInstance.add(3);
        testInstance.add(6);
        testInstance.add(7);
        testInstance.add(8);
        testInstance.add(9);

        testInstance2.add(3); //0 size1
        testInstance2.add(4); //1 size2
        testInstance2.add(5); //2 size3
        testInstance2.add(6); //3 size4

        testInstance.retainAll(testInstance2);

        assertEquals(2, testInstance.size());
        assertTrue(testInstance.contains(3));
        assertFalse(testInstance.contains(4));
        assertFalse(testInstance.contains(5));
        assertTrue(testInstance.contains(6));
    }

    @Test
    public void testRetainAll2() {
        final Collection<Integer> testInstance = new ArrayCollection<>();
        final Collection<Integer> testInstance2 = new ArrayCollection<>();
        testInstance.add(1);
        testInstance.add(2);
        testInstance.add(3);
        testInstance.add(4);
        testInstance.add(5);
        testInstance.add(6);
        testInstance.add(7);
        testInstance.add(8);
        testInstance.add(9);

        testInstance2.add(3); //0 size1
        testInstance2.add(4); //1 size2
        testInstance2.add(5); //2 size3
        testInstance2.add(6); //3 size4

        testInstance.retainAll(testInstance2);

        assertEquals(4, testInstance.size());
        assertTrue(testInstance.contains(3));
        assertTrue(testInstance.contains(4));
        assertTrue(testInstance.contains(5));
        assertTrue(testInstance.contains(6));
    }

    @Test
    public void testRetainAll1() {
        final Collection<Integer> testInstance = new ArrayCollection<>();
        final Collection<Integer> testInstance2 = new ArrayCollection<>();
        testInstance.add(1);
        testInstance.add(2);
        testInstance.add(3);
        testInstance.add(3);
        testInstance.add(3);
        testInstance.add(6);
        testInstance.add(7);
        testInstance.add(8);
        testInstance.add(9);

        testInstance2.add(3); //0 size1
        testInstance2.add(4); //1 size2
        testInstance2.add(5); //2 size3
        testInstance2.add(6); //3 size4

        testInstance.retainAll(testInstance2);

        assertEquals(4, testInstance.size());
        assertTrue(testInstance.contains(3));
        assertTrue(testInstance.contains(6));
    }

    @Test
    public void testClear() {
        final Collection<Integer> testInstance = new ArrayCollection<>();
        testInstance.add(1);
        testInstance.add(1);

        testInstance.clear();

        assertTrue(testInstance.isEmpty());
        assertEquals(0, testInstance.size());
    }
}