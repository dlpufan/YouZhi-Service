package com.txx.springboot.entity;

/**
 * @author 淘米水浇花
 */
public class WorkNeedCount {
    private Integer workNeedPeople;

    public WorkNeedCount() {

    }

    public WorkNeedCount(Integer workNeedPeople) {
        this.workNeedPeople = workNeedPeople;
    }

    public Integer getWorkNeedPeople() {
        return workNeedPeople;
    }

    public void setWorkNeedPeople(Integer workNeedPeople) {
        this.workNeedPeople = workNeedPeople;
    }

    @Override
    public String toString() {
        return "WorkNeedCount{" +
                "workNeedPeople=" + workNeedPeople +
                '}';
    }
}
