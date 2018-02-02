package com.whgtf.sportsbook.pom.utils;

import org.junit.Assert;

import java.util.ArrayList;
import java.util.List;


public abstract class Assertions {

    public static void assertEquals(String text, String text2)
    {
        Assert.assertTrue("Assertion failed, it was: '" + text + "' and it should be '" + text2 + "'" , text.equals(text2));
    }

    public static void assertTrue(boolean value)
    {
        Assert.assertTrue("Assertion failed, it was: 'false' and it should be 'true'",value);
    }

    public static void assertTrue(String message, boolean value)
    {
        Assert.assertTrue(message,value);
    }

    /**
     * Check if textA contains textB
     *
     * @param textA the first string
     * @param textB the second string
     */

    public static void assertContains(String textA, String textB)
    {
        Assert.assertTrue("Assertion failed, text: '" + textA + "' doesn't contain '" + textB + "'", textA.contains(textB));
    }

    public static void assertArrayContains(final String text, final List<String> list) {
        List<String> wrongList = new ArrayList<>();
        for (String element: list) {
            if(!element.contains(text)){
                wrongList.add(element);
            }
        }
        if(wrongList.isEmpty()){
            assertTrue(true);
        } else {
            Assert.assertTrue("Some elements in the array doesn't contain '" + text + "' --> " + wrongList.toString(),false);
        }
    }

    public static void assertArrayContainsRegex(final String regex, final List<String> list) {
        List<String> wrongList = new ArrayList<>();
        for (String element: list) {
            if(!element.matches(regex)){
                wrongList.add(element);
            }
        }
        if(wrongList.isEmpty()){
            assertTrue(true);
        } else {
            Assert.assertTrue("Some elements in the array doesn't contain '" + regex + "' --> " + wrongList.toString(),false);
        }
    }

}
