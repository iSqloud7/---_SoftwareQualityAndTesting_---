package com.example.laboratoryexercises.JUnit.LaboratoryExercies01;

import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

class ComputeValueDifferencesTest {

    @Test
    void testCommonKeysExist() {
        Map<String, Integer> map1 = Map.of("A", 2, "B", 3, "C", 5);
        Map<String, Integer> map2 = Map.of("B", 6, "C", 2, "D", 8);
        Map<String, Integer> expected = Map.of("B", 3, "C", 3);

        assertEquals(expected, ComputeValDiffs.computeValueDifferences(map1, map2));
    }

    @Test
    void testNoCommonKeys() {
        Map<String, Integer> map1 = Map.of("X", 1, "Y", 2, "Z", 3);
        Map<String, Integer> map2 = Map.of("A", 4, "B", 5, "C", 6);

        assertTrue(ComputeValDiffs.computeValueDifferences(map1, map2).isEmpty());
    }

    @Test
    void testOneMapEmpty() {
        Map<String, Integer> map1 = Collections.emptyMap();
        Map<String, Integer> map2 = Map.of("A", 1, "B", 2, "C", 3);

        assertTrue(ComputeValDiffs.computeValueDifferences(map1, map2).isEmpty());
    }

    @Test
    void testBothMapsEmpty() {
        Map<String, Integer> map1 = Collections.emptyMap();
        Map<String, Integer> map2 = Collections.emptyMap();

        assertTrue(ComputeValDiffs.computeValueDifferences(map1, map2).isEmpty());
    }

    @Test
    void testMap1Null() {
        Map<String, Integer> map2 = Map.of("A", 1, "B", 2);

        assertThrows(NullPointerException.class, () -> ComputeValDiffs.computeValueDifferences(null, map2));
    }

    @Test
    void testMap2Null() {
        Map<String, Integer> map1 = Map.of("A", 3, "B", 4);

        assertThrows(NullPointerException.class, () -> ComputeValDiffs.computeValueDifferences(map1, null));
    }
}

