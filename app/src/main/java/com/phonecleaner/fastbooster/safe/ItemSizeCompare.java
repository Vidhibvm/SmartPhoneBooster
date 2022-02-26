package com.phonecleaner.fastbooster.safe;

import java.util.Comparator;

public class ItemSizeCompare implements Comparator<RunningItem> {
    public int compare(RunningItem runningItem, RunningItem runningItem2) {
        if (runningItem.getS() == runningItem2.getS()) {
            return 0;
        }
        return runningItem.getS() < runningItem2.getS() ? 1 : -1;
    }
}
