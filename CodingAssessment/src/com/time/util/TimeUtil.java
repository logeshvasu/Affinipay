package com.time.util;

/**
 * Time Util class to validate the time format
 * calculate time with minutes added
 */
public final class TimeUtil {

    /**
     * Regex to validate for hh:mm [AM|PM] time format
     */
    public static final String TIME_FORMAT_REGEX = "(1[012]|[1-9]):[0-5][0-9](\\s)?(?i)(am|pm)";

    /**
     * Empty String
     */
    public static final String EMPTY = "";

    /**
     * Blank String
     */
    public static final String SPACE = " ";

    /**
     * COLON string
     */
    public static final String COLON = ":";

    /**
     * AM Time Format String
     */
    public static final String AM = "AM";

    /**
     * PM Time Format String
     */
    public static final String PM = "PM";

    /**
     * MAX HR IN CLOCK
     */
    public static final int MAX_HR = 12;

    /**
     * MINUTES PER HOUR
     */
    public static final int MINUTES_PER_HOUR = 60;

    /**
     * TOTAL MINUTES PER DAY (24 HOURS)
     */
    public static final int TOTAL_MINUTES_PER_DAY = 24 * 60;

    /**
     * ZERO String to append to Time minutes
     */
    public static final String ZERO = "0";

    /**
     * DOUBLE ZERO String to append to Time minutes
     */
    public static final String DOUBLE_ZERO = "00";

    /**
     * private constructor
     */
    private TimeUtil() {
        throw new IllegalStateException("Time Util class");
    }

    /**
     * Validate time format hh:mm [AM|PM]
     * @param time input time String
     * @return boolean indicating valid time format
     * Example: 00:00 AM - Invalid, 12:00 AM Valid
     * 24:00 PM Invalid, 12:00 PM  Valid
     */
    public static boolean validateTimeFormat(String time){
        if(null == time || time.trim().isEmpty()) {
            return false;
        }
        time = time.trim();
        return time.matches(TIME_FORMAT_REGEX);
    }

    /**
     * Add minutes to current time
     * @param time input time string
     * @param addMinutes unsigned integer minutes to be added
     * @return result time string with minutes added
     * Example addMinutesToTime(12:00 AM, 60) = 1:00 AM
     * add MinutesToTime(12:00 AM, -60) = 11:00 PM
     */
    public static String addMinutesToTime(String time, int addMinutes) {
        // convert the time hrs to minutes
        int currentMinutes = convertTimeToMinutes(time);
        // add current time and input minutes
        int totalMinutes = addMinutes(currentMinutes, addMinutes);
        // convert result minutes into AM|PM time format
        return convertToTimeFormat(totalMinutes);
    }

    /**
     * Convert given time format to minutes
     * @param time input time string hh:mm [AM|PM] format
     * @return minutes parsed from the time format
     * Example: convertTimeToMinutes(12:00 AM) = 0
     * convertTimeToMinutes(12:00 PM) = 720
     */
    public static int convertTimeToMinutes(String time) {
        // split the string by colon, gives Hours, minutes + AM|PM
        String[] splitByColon = time.split(COLON);
        // get the hours value
        int hoursValue = Integer.parseInt(splitByColon[0]);
        // split the minutes portion to minutes and AM|PM
        String minutesHand = splitByColon[1];
        String[] splitForMinutes;
        // if minutes portion contains space between minutes and AM|PM
        // else parse the string
        if(minutesHand.contains(SPACE)){
            splitForMinutes = minutesHand.split(SPACE);
        }
        else {
            String minutes = minutesHand.substring(0,minutesHand.length()-2);
            splitForMinutes = new String[2];
            splitForMinutes[0] = minutes.trim();
            splitForMinutes[1] = minutesHand.substring(minutes.length());
        }
        // convert hours value to fall into [0,11] range
        hoursValue = hoursValue % MAX_HR;
        // if PM convert to [12, 23] range
        if(PM.equalsIgnoreCase(splitForMinutes[1])) {
            hoursValue = hoursValue + MAX_HR;
        }
        // get minutes value
        int minutesValue = Integer.parseInt(splitForMinutes[0]);
        // calculate total minutes (60*hoursValue + minutesValue)
        return MINUTES_PER_HOUR * hoursValue + minutesValue;
    }

    /**
     *
     * @param currentMinutes currentMinutes input
     * @param addMinutes minutesToBeAdded input
     * @return output total minutes
     * Example: addMinutes(720, 120) = 840
     * addMinutes(720 - 120) = 600
     */
    public static int addMinutes(int currentMinutes, int addMinutes) {
        return currentMinutes + addMinutes;
    }

    /**
     * Convert minutes to hh:mm [AM|PM] format
     * @param minutes input minutes value
     * @return output string time format hh:mm [AM|PM]
     * Example: convertToTimeFormat(0) = 12:00 AM
     * convertToTime(-120) = 10:00 PM
     * convertToTime(120) = 2:00 AM
     * convertToTime(1440) = 12:00 AM
     * convertToTime(-1440) = 12:00 AM
     */
    public static String convertToTimeFormat(int minutes) {
        // convert minutes to fall between [-1440 to 1440]
        minutes = minutes % TOTAL_MINUTES_PER_DAY;
        // if negative minutes wrap around
        if (minutes < 0){
            minutes = TOTAL_MINUTES_PER_DAY + minutes;
        }
        // convert minutes to hours
        int hours = minutes / MINUTES_PER_HOUR;
        // get actual hours to display between [1-12]
        int hoursToDisplay = hours;
        if (hours > MAX_HR) {
            hoursToDisplay = hoursToDisplay - MAX_HR;
        }
        else if(hours == 0){
            hoursToDisplay = hoursToDisplay + MAX_HR;
        }
        // get minutes to display
        int minutesToDisplay = minutes - (hours * MINUTES_PER_HOUR);
        // format minutes to be displayed string
        String minToDisplay;
        if(minutesToDisplay == 0 ) {
            minToDisplay = DOUBLE_ZERO; // if 0 minutes, add 00 to minutes portion
        }
        else if( minutesToDisplay < 10 ) { // if less than 10, add 0 prefix to minutes
            minToDisplay = ZERO + minutesToDisplay ;
        }
        else {
            minToDisplay = EMPTY + minutesToDisplay ; // else add actual minutes
        }
        // return value hours:minutes
        String retValue = hoursToDisplay + COLON + minToDisplay;
        // format return value with AM|PM suffix
        return hours < MAX_HR ? retValue + SPACE + AM : retValue + SPACE + PM;
    }

}
