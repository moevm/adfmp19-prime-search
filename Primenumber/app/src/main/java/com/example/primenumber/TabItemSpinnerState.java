package com.example.primenumber;

public class TabItemSpinnerState {
    private static final TabItemSpinnerState ourInstance = new TabItemSpinnerState();
    public String level = "easy";

    public static TabItemSpinnerState getInstance() {
        return ourInstance;
    }

    private TabItemSpinnerState() {
    }
}
