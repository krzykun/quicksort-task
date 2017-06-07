package pl.java.recruitmenttask.app.sort;

import javax.xml.crypto.Data;
import java.sql.Wrapper;

/**
 * This class contains a quicksort implementation.
 * It has two constructors: one, if you don't want to pass the data immediately, and the other, if you can pass it.
 *
 * Set IsDebugActive property of sorter to make Sorter verbose.
 *
 * Created by Krzysztof Kundera on 07 - 06 - 2017.
 */
public class Sorter {

    /**
     * Dedicated for automatic tests
     * @param IsDebugActive
     */
    public Sorter(boolean IsDebugActive) {
        DebugActive = IsDebugActive;
    }

    /**
     * Dedicated for user use
     * @param IsDebugActive
     * @param NewData
     */
    public Sorter(boolean IsDebugActive, int [] NewData) {
        DebugActive = IsDebugActive;
        DataArray = NewData;
        trigger(DataArray);
    }

    private boolean DebugActive;

    private static long CallCount = 0L;

    /**
     * Wrapped console debug print
     * It is used instead of "System.out.println".
     * @param message
     */
    void wrappedPrintln(String message) {
        if (DebugActive) {
            System.out.println(message);
        }
    }

    private void printData(int RangeCount, int RangeStart, int RangeEnd) {
        wrappedPrintln("Loop number: "+ CallCount);
        wrappedPrintln("Range: "+RangeCount+", from: "+RangeStart+", to: "+RangeEnd);
        wrappedPrintln("Current Data:");
        for (int i = 0; i < DataArray.length; ++i) {
            System.out.printf("\t%d", DataArray[i]);
        }
        wrappedPrintln("");
    }

    private int [] DataArray;
    /**
     * Function that implements quicksort algorithm.
     * This function assumes C-style numbering (from 0 to n - 1 where n is the array length).
     *
     * @param RangeStart    First argument in the range (0 through n - 1)
     * @param RangeEnd      Last argument in the range
     */
    private void qsort(int RangeStart, int RangeEnd)
    {
        CallCount++;
        int RangeCount = RangeEnd - RangeStart + 1;
        printData(RangeCount, RangeStart, RangeEnd);
        switch (RangeCount) {
            case 0: {
                wrappedPrintln("No elements.");
                return;
            }
            case 1: {
                wrappedPrintln("One element.");
                return;
            }
            case 2: {
                wrappedPrintln("Two elements.");
                if (DataArray[RangeStart] > DataArray[RangeEnd]) {
                    int temp = DataArray[RangeStart];
                    DataArray[RangeStart] = DataArray[RangeEnd];
                    DataArray[RangeEnd] = temp;

                    wrappedPrintln("They were not sorted!");
                }
                return;
            }
            default: {

                //finding pivot
                boolean PivotAlocated = false;
                int Pivot = RangeStart;

                //qsort recurence
                while (!PivotAlocated) {
                    if ((Pivot + 1) <= RangeEnd) {
                        if (DataArray[Pivot + 1] < DataArray[Pivot]) { //continue search
                            int Clipboard = DataArray[Pivot];
                            DataArray[Pivot] = DataArray[Pivot + 1];
                            Pivot++;
                            DataArray[Pivot] = Clipboard;
                        }
                        else {
                            PivotAlocated = true;
                        }
                    }
                    else {
                        PivotAlocated = true;
                    }
                }
                //finding pivot end

                //recurence
                wrappedPrintln("Many elements.");
                wrappedPrintln("Placed pivot in position "+Pivot+" and of value "+DataArray[Pivot]);
                wrappedPrintln("");
                if (Pivot != RangeStart) {
                    qsort(RangeStart, Pivot - 1);
                }
                if (Pivot < RangeEnd) {
                    qsort(Pivot + 1, RangeEnd); //troublesome line
                }

            }
        }


    }

    /**
     * This is the public function that can be used to trigger sorting.
     *
     * @param Array     Data to be sorted
     */
    public int trigger(int [] Array)
    {
        DataArray = Array;
        if (DataArray.length < 1) {
            wrappedPrintln("ERROR Array length is less than 1.");
            return -1;
        }

        qsort(0, Array.length - 1);

        return 0;
    }

}
