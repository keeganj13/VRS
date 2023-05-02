package com.vrs;

public class Date {
    public int day;
    public int month;
    public int year;
    private static String[] months = { "January", "February", "March", "April", "May", "June", "July", "August",
            "September", "October", "November", "December" };

    public Date(int d, int m, int y) {
        if (d > 0 && d < 32) {
            day = d;
        }
        if (m > 0 && m < 13) {
            month = m;
        }
        if (y < 0) {
            year = y;
        }
    }

    public String toString() {
        String out = String.format("%s %d, %d", months[month - 1], day, year);
        return out;
    }
}
