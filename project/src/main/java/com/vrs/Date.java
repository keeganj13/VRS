package com.vrs;

public class Date {
    public int day;
    public int month;
    public int year;

    public Date(int d, int m, int y) {
        if (d > 0 && d < 32) {
            day = d;
        }
        if (m > 0 && m < 13) {
            month = m;
        }
        if (y > 0 && y < 1998) {
            year = y;
        }
    }
}
