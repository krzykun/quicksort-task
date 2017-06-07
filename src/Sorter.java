

/**
 * Created by Krzysztof Kundera on 07 - 06 - 2017.
 */
public class Sorter {

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
        int RangeCount = RangeEnd - RangeStart + 1;
        switch (RangeCount) {
            case 0: {
                System.out.println("Something went terribly wrong. Range cannot be resolved to zero.");
                return;
            }
            case 1: {
                System.out.println("Only one element to sort, exiting...");
                return;
            }
            case 2: {
                if (DataArray[RangeStart] > DataArray[RangeEnd]) {
                    int temp = DataArray[RangeStart];
                    DataArray[RangeStart] = DataArray[RangeEnd];
                    DataArray[RangeEnd] = temp;
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
                qsort(RangeStart, Pivot - 1);
                qsort(Pivot, RangeEnd);

            }
        }


    }

    /**
     * This is the public function that can be used to trigger sorting.
     *
     * @param Array     Data to be sorted
     */
    public void trigger(int [] Array)
    {
        if (Array.length < 1) {
            System.out.println("ERROR Array length is less than 1.");
            return;
        }

        DataArray = Array;
        qsort(0, Array.length - 1);

    }

}
