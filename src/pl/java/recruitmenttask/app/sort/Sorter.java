package pl.java.recruitmenttask.app.sort;

import java.io.*;

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

    private Integer [] DataArray;

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

    private void printDebugData(int RangeCount, int RangeStart, int RangeEnd) {
        wrappedPrintln("Loop number: "+ CallCount);
        wrappedPrintln("Range: "+RangeCount+", from: "+RangeStart+", to: "+RangeEnd);
        wrappedPrintln("Current Data:");
        for (int i = 0; i < DataArray.length; ++i) {
            System.out.printf("\t%d", DataArray[i]);
        }
        wrappedPrintln("");
    }

    public Integer[] getData() {
        return DataArray;
    }

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
        printDebugData(RangeCount, RangeStart, RangeEnd);
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
/*
                while (!PivotAlocated) {
                    if ((Pivot + 1) <= RangeEnd) {*/
                for (int i = RangeStart + 1; i < RangeStart + RangeCount; ++i) {
                    if (DataArray[i] < DataArray[Pivot]) {
                        Integer Clipboard = DataArray[Pivot];
                        DataArray[Pivot] = DataArray[i];
                        int j;
                        for (j = i; j > Pivot + 1; j--) {
                            DataArray[j] = DataArray[j - 1];
                        }
                        DataArray[j] = Clipboard;
                    }
                }
                        /*
                        Pivot++;
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
                    }*/
                //}
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
        boolean isVerbose = false;

        // Read filename from command line
        if ((args.length > 0) || (args.length < 3) ) {
            try {
                Filename = args[0];
            }
            catch(Exception e) {
                System.out.println("Cannot parse the filename from commandline arguments.");
            }
            if (args[1] == "-v") {
                isVerbose = true;
            }
        }

        String InputLine = "";
        String [] StringInput = {/*"6", "3", "2", "5", "1", "4"*/};
        Integer [] IntegerInput = {};

        // Parse numbers to String array
        try (BufferedReader reader = new BufferedReader(new FileReader(Filename))) {
            while ((InputLine = reader.readLine()) != null) {
                StringInput = InputLine.split("\\s");
            }
            reader.close();
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

        // Trigger sorting
        Sorter sorter = new Sorter(isVerbose, IntegerInput);

        String outputLine = "";
        Integer [] sortedArray = sorter.getData();
        Filename = "Output.txt";

        // Write back to file
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(Filename))) {
            if (sortedArray.length > 0) {
                for (int i = 0; i < sortedArray.length; ++i) {
                    writer.write(" "+sortedArray[i]);
                }
            }
            writer.close();
        }
        catch (FileNotFoundException e) {
            System.out.println("File was not found.");
            e.printStackTrace();
        }
        catch(UnsupportedEncodingException e) {
            System.out.println("This encoding is not supported.");
            e.printStackTrace();
        }
        catch(IOException e) {
            System.out.println("General IO exception encountered.");
            e.printStackTrace();
        }
    }
}
