package com.learning;

import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class HashSetMapTest {
    @Test
    public void testAdd() {
        final Set<String> s = new HashSetMap<>();
        s.add("2");
        s.add("3");
        assertEquals(2, s.size(), "set size is not returning the correct size");
        assertTrue(s.contains("2"), "set contains is not working correctly");
        assertTrue(s.contains("3"), "set contains is not working correctly");
    }

    @Test
    public void testAddDuplicated() {
        final Set<String> s = new HashSetMap<>();
        s.add("1");
        s.add("1");
        assertEquals(1, s.size(), "set size does not return 1 after adding same element twice");
        assertTrue(s.contains("1"), "set contains do not return true for the element that was added twice");
    }

    @Test
    public void testAddAll() {
        final Set<String> s = new HashSetMap<>();
        s.add("2");
        s.add("3");

        final Set<String> s2 = new HashSetMap<>();
        s2.add("1");

        s.addAll(s2);
        assertEquals(3, s.size(), "addAll is not working correctly");
        assertTrue(s.contains("1"), "addAll is not working correctly");
    }

    @Test
    public void testRemove() {
        final Set<String> s = new HashSetMap<>();
        s.add("1");

        s.remove("1");
        assertEquals(0, s.size(), "set size does not return 0 when all elements are removed from the set");
        assertTrue(s.isEmpty(), "set isEmpty do not return true when the set size is 0");
    }
}