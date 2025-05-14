package com.example;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class LookupServiceTests {
    
    @Test
    public void searchForXShouldReturnExpectedResults() {
        // arrange
        LookupService s = new LookupService();
        List<String> expected = new ArrayList<>();
        expected.add("ZZZ");

        // act
        List<String> r = s.getAllStartingWith("Z");

        // assert
        Assertions.assertEquals(expected, r);
    }
}
