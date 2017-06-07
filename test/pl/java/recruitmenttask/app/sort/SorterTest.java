package pl.java.recruitmenttask.app.sort;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.*;

/**
 * Test class for Sorter
 *
 * Created by Krzysztof Kundera on 07 - 06 - 2017.
 */
class SorterTest {
    /**
     * Set IsDebugActive property of sorter to make Sorter verbose.
     */
    Sorter sorter = new Sorter(false);

    @Test
    void dataArrayNullTest() {
        Integer [] EmptyArray = {};
        if (sorter.trigger(EmptyArray) == -1) {

        }
        else {
            fail("Data array null test failure.");
        }
    }

    @Test
    void onlyOneElementTest() {
        Integer [] DataArray = {42};
        Integer [] ResultArray = {42};
        sorter.trigger(DataArray);
        if (!(DataArray.length == ResultArray.length)) {
            fail("Arrays that should be equal have different amount of elements.");
        }
        for (int i = 0; i < DataArray.length; ++i) {
            if (DataArray[i] == ResultArray[i]) {
            }
            else {
                fail("Data array elements are not sorted correctly (they are not in order).");
            }
        }
    }

    @Test
    void twoElementsTest() {
        Integer [] DataArray = {1337, 42};
        Integer [] ResultArray = {42, 1337};
        sorter.trigger(DataArray);
        if (!(DataArray.length == ResultArray.length)) {
            fail("Arrays that should be equal have different amount of elements.");
        }
        for (int i = 0; i < DataArray.length; ++i) {
            if (DataArray[i] == ResultArray[i]) {
            }
            else {
                fail("Data array elements are not sorted correctly (they are not in order).");
            }
        }
    }

    /**
     *  Test case for an array with uneven amount of elements.
     */
    @Test
    void unevenAmountTest() {
        Integer [] DataArray = {5, 0, 3, 1, 2, 4};
        Integer [] ResultArray = {0, 1, 2, 3, 4, 5};
        sorter.trigger(DataArray);
        if (!(DataArray.length == ResultArray.length)) {
            fail("Arrays that should be equal have different amount of elements.");
        }
        for (int i = 0; i < DataArray.length; ++i) {
            if (DataArray[i] == ResultArray[i]) {
            }
            else {
                fail("Data array elements are not sorted correctly (they are not in order).");
            }
        }
    }

    /**
     * Test case for an already sorted array with 10 elements of values 0 through 9.
     */
    @Test
    void alreadySortedArrayTest() {
        Integer [] DataArray = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9};
        Integer [] ResultArray = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9};
        sorter.trigger(DataArray);
        if (!(DataArray.length == ResultArray.length)) {
            fail("Arrays that should be equal have different amount of elements.");
        }
        for (int i = 0; i < DataArray.length; ++i) {
            if (DataArray[i] == ResultArray[i]) {
            }
            else {
                fail("Data array elements are not sorted correctly (they are not in order).");
            }
        }
    }

    /**
     * Test case for a worst case scenario to test, a reverse sorted array with 10 elements of values 9 through 0.
     */
    @Test
    void worstCaseArrayTest() {
        Integer [] DataArray = {9, 8, 7, 6, 5, 4, 3, 2, 1, 0};
        Integer [] ResultArray = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9};
        sorter.trigger(DataArray);
        if (!(DataArray.length == ResultArray.length)) {
            fail("Arrays that should be equal have different amount of elements.");
        }
        for (int i = 0; i < DataArray.length; ++i) {
            if (DataArray[i] == ResultArray[i]) {
            }
            else {
                fail("Data array elements are not sorted correctly (they are not in order).");
            }
        }
    }
}