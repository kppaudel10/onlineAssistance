package com.online_dtie_tracker.authorizeduser;


public class UserTask {
    private static Integer totalTask;

    public static Integer getTotalTask() {
        return totalTask;
    }

    public static void setTotalTask(Integer totalTask) {
        UserTask.totalTask = totalTask;
    }
}
