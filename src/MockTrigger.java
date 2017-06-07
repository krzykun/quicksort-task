/**
 * This class is used for test purposes only.
 *
 * Created by Krzysztof Kundera on 07 - 06 - 2017.
 */
public class MockTrigger {

    /**
     * Main function, for test purposes only.
     */
    public static void main() {

        //initialize calculations
        Sorter sorter = new Sorter();
        int [] data = {3,2,1,5,4,8,6};
        sorter.trigger(data);

        //show results
        System.out.printf("Results:");
        for (int i = 0; i < data.length; ++i) {
            System.out.printf("\t%d", data[i]);
        }
        System.out.println("");
    }
}
