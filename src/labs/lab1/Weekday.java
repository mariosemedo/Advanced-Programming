package labs.lab1;

import com.sun.tools.internal.ws.wsdl.document.soap.SOAPUse;

import java.util.Arrays;

import static labs.lab1.Weekday.*;

/**
 * Created by mariooliveira on 11/10/2017.
 */

public enum Weekday {
    MONDAY("Mon"), TUESDAY("Tue"), WEDNESDAY("Wed"), THURSDAY("Thur"), FRIDAY("Fri"), SATURDAY("Sat"), SUNDAY("Sun");


    private String abbrev;

    Weekday (String weekday) {
        this.abbrev = weekday;
    }

    public Weekday next() {
        switch (this) {
            case MONDAY:
                return TUESDAY;
            case TUESDAY:
                return WEDNESDAY;
            case WEDNESDAY:
                return THURSDAY;
            case THURSDAY:
                return FRIDAY;
            case FRIDAY:
                return SATURDAY;
            case SATURDAY:
                return SUNDAY;
            case SUNDAY:
                return MONDAY;
            default:
                return null;
        }

    }

    public Weekday previous() {
        //System.out.println(weekday.ordinal());
        /*
        Weekday[] v = Weekday.values();
        int i = Weekday.ordinal();
        if (i - 1 < 0) {
            i = v.length;
        }
        return weekday = v[i - 1];
        //System.out.println("Previous "+Arrays.asList(weekday));
        //System.out.println(weekday.ordinal());

        */

        return Weekday.values()[(ordinal() + 6) % 7];

    }

    public static Weekday parse(String weekdays) {
        /*
        weekdays = weekdays.toUpperCase();
        Weekday day = new Weekday(weekdays);
        return day;
        //System.out.println(Weekday.valueOf(weekdays));
        */

        return Weekday.valueOf(weekdays.toUpperCase());
    }


    public static void main(String[] args) {

        Weekday day = Weekday.SUNDAY;
        day.next();
        day.previous();
        Weekday day2 = parse("tuesday");
        day2.next();
    }
 }
