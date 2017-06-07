package pl.java.recruitmenttask.app.sort;

import jdk.internal.util.xml.impl.Input;

import javax.xml.crypto.Data;
import java.io.*;
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
    public Sorter(boolean IsDebugActive, Integer [] NewData) {
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

    private Integer [] DataArray;
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
                    Integer temp = DataArray[RangeStart];
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
    public int trigger(Integer [] Array)
    {
        DataArray = Array;
        if (DataArray.length < 1) {
            wrappedPrintln("ERROR Array length is less than 1.");
            return -1;
        }

        qsort(0, Array.length - 1);

        return 0;
    }

    public static void main(String [] args) {
        String Filename = "";

        // Read filename from command line
        if ((args.length > 0) || (args.length < 2) ) {
            try {
                Filename = args[1];
            }
            catch(Exception e) {
                System.out.println("Cannot parse the filename from commandline arguments.");
            }
        }

        String InputLine = "";
        String [] StringInput = {"6", "2", "3", "5", "1"};
        Integer [] IntegerInput = {};
/*
        // Parse numbers to String array
        try (BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(Filename), "UTF-8"))) {
            while ((InputLine = br.readLine()) != null) {
                StringInput = InputLine.split("\\s");
            }
        }
        catch (FileNotFoundException e) {
            System.out.println("File was not found.");
        }
        catch(UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        catch(IOException e) {
            System.out.println("General IO exception encountered.");
            e.printStackTrace();
        }

        // Parse String array into numbers
        if (StringInput.length > 0) {
            IntegerInput = new Integer[StringInput.length];
            for (int i = 0; i < StringInput.length; ++i) {
                IntegerInput[i] = new Integer(StringInput[i]);
            }
        }
*/
        // Trigger sorting
        Sorter sorter = new Sorter(true, IntegerInput);

        // Write back to file
    }

}
