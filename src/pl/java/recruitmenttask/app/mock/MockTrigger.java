package pl.java.recruitmenttask.app.mock;

import pl.java.recruitmenttask.app.sort.Sorter;

/**
 * This class is used for test purposes only.
 *
 * Created by Krzysztof Kundera on 07 - 06 - 2017.
 */
public class MockTrigger {

    /**
     * Main function, for test purposes only.
     */
    public static void main(String [] args) {

        //initialize calculations
        int [] data = {5,4,3,2,1};

        //Change parameter argument to make Sorter verbose
        Sorter sorter = new Sorter(false);

        sorter.trigger(data);

        //show results
        System.out.printf("Results:");
        for (int i = 0; i < data.length; ++i) {
            System.out.printf("\t%d", data[i]);
        }
        System.out.println("");
    }
}
