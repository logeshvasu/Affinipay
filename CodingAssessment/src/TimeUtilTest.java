import com.time.util.TimeUtil;

/**
 * Class to test TimeUtil Class utility methods
 */
public class TimeUtilTest {

    /**
     * Invalid Time Format error description
     */
    private static final String INVALID_TIME_FORMAT = "Invalid time format";

    /**
     * PASS string
     */
    private static final String PASS = "PASS";

    /**
     * FAIL string
     */
    private static final String FAIL = "FAIL";

    /**
     * Main method to start the execution of the program
     * @param args command line arguments
     */
    public static void main(String[] args) {
        runTests();
    }

    /**
     * Run Test Suite
     */
    public static void runTests() {

        // Test case 1
        // Valid time format
        System.out.println("Test case 1: "+ (("12:33 PM".equals(runTest("9:13 AM", 200))) ?
                PASS : FAIL));

        // Test case 2
        // Valid time format
        System.out.println("Test case 2: "+ (("12:33 AM".equals(runTest("9:13 PM", 200))) ?
                PASS : FAIL));

        // Test case 3
        // Invalid time format , null string
        System.out.println("Test case 3: "+ ((INVALID_TIME_FORMAT.equals(runTest(null, 200))) ?
                PASS : FAIL));

        // Test case 4
        // Invalid time format, empty string
        System.out.println("Test case 4: "+ ((INVALID_TIME_FORMAT.equals(runTest("", 200))) ?
                PASS : FAIL));

        // Test case 5
        // Invalid time format, blank string
        System.out.println("Test case 5: "+ ((INVALID_TIME_FORMAT.equals(runTest(" ", 200))) ?
                PASS : FAIL));

        // Test case 6
        // Invalid time format
        System.out.println("Test case 6: "+ ((INVALID_TIME_FORMAT.equals(runTest("0:13 AM", 200))) ?
                PASS : FAIL));

        // Test case 7
        // Invalid time format
        System.out.println("Test case 7: "+ ((INVALID_TIME_FORMAT.equals(runTest("23:13 PM", 200))) ?
                PASS : FAIL));

        // Test case 8
        // Invalid time format
        System.out.println("Test case 8: "+ ((INVALID_TIME_FORMAT.equals(runTest("23:13", 200))) ?
                PASS : FAIL));

        // Test case 9
        // Invalid time format
        System.out.println("Test case 9: "+ ((INVALID_TIME_FORMAT.equals(runTest("+12:13 AM", 200))) ?
                PASS : FAIL));

        // Test case 10
        // Invalid time format
        System.out.println("Test case 10: "+ ((INVALID_TIME_FORMAT.equals(runTest("9:13   pm", 200))) ?
                PASS : FAIL));

        // Test case 11
        // Invalid time format
        System.out.println("Test case 11: "+ ((INVALID_TIME_FORMAT.equals(runTest("9:13*&^&()pm", 200))) ?
                PASS : FAIL));

        // Test case 12
        // Invalid time format
        System.out.println("Test case 12: "+ ((INVALID_TIME_FORMAT.equals(runTest("^^^^9:13 PM", 200))) ?
                PASS : FAIL));

        // Test case 13
        // Valid time format
        System.out.println("Test case 13: "+ (("12:33 PM".equals(runTest("9:13 am", 200))) ?
                PASS : FAIL));

        // Test case 14
        // Valid time format
        System.out.println("Test case 14: "+ (("1:53 PM".equals(runTest("9:13 PM", 1000))) ?
                PASS : FAIL));

        // Test case 15
        // Valid time format
        System.out.println("Test case 15: "+ (("6:33 PM".equals(runTest("9:13 am", 2000))) ?
                PASS : FAIL));

        // Test case 16
        // Valid time format
        System.out.println("Test case 16: "+ (("5:53 AM".equals(runTest("9:13 am", -200))) ?
                PASS : FAIL));

        // Test case 17
        // Valid time format
        System.out.println("Test case 17: "+ (("5:53 PM".equals(runTest("9:13 pm", -200))) ?
                PASS : FAIL));

        // Test case 18
        // Valid time format
        System.out.println("Test case 18: "+ (("4:33 AM".equals(runTest("9:13 PM", -1000))) ?
                PASS : FAIL));

        // Test case 19
        // Valid time format
        System.out.println("Test case 19: "+ (("9:13 PM".equals(runTest("9:13 PM", 0))) ?
                PASS : FAIL));

        // Test case 20
        // Valid time format
        System.out.println("Test case 20: "+ (("9:13 PM".equals(runTest("9:13 PM", -1440))) ?
                PASS : FAIL));

        // Test case 21
        // Valid time format
        System.out.println("Test case 21: "+ (("4:33 PM".equals(runTest("9:13 AM", -1000))) ?
                PASS : FAIL));

        // Test case 22
        // Valid time format
        System.out.println("Test case 22: "+ (("12:33 PM".equals(runTest("9:13AM", 200))) ?
                PASS : FAIL));

        //Test case 23
        // Valid time format , Integer MAX value
        System.out.println("Test case 23: "+ (("2:07 AM".equals(runTest("12:00 AM", Integer.MAX_VALUE))) ?
                PASS : FAIL));

        // Test case 24
        // Valid time format, Integer MIN value
        System.out.println("Test case 24: "+ (("9:52 PM".equals(runTest("12:00 AM", Integer.MIN_VALUE))) ?
                PASS : FAIL));

    }

    /**
     * Run single test
     * @param time expects String hh:mm [AM|PM] format
     * @param addMinutes unsigned int minutes to be added
     * @return time after adding minutes to given time in hh:mm [AM|PM] format
     */
    public static String runTest(String time, int addMinutes) {
        return calculateTime(time, addMinutes);
    }

    /**
     * Call TimeUtil calculate method to calculate the result
     * @param time expects String hh:mm [AM|PM] format
     * @param addMinutes unsigned int minutes to be added
     * @return result time String hh:mm [AM|PM] format
     */
    public static String calculateTime(String time, int addMinutes) {

        // validate the time format, if valid calculate result
        // else return invalid error message
        return TimeUtil.validateTimeFormat(time) ?
                TimeUtil.addMinutesToTime(time, addMinutes) :
                INVALID_TIME_FORMAT;
    }
}
