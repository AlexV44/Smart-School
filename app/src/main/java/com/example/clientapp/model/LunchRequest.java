package com.example.clientapp.model;

public class LunchRequest {
    private int id;
    private String className;
    private int studentsQuantity;
    private int freeStudentsQuantity;
    private String requestTime;
    private int schoolId;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public int getStudentsQuantity() {
        return studentsQuantity;
    }

    public void setStudentsQuantity(int studentsQuantity) {
        this.studentsQuantity = studentsQuantity;
    }

    public int getSchoolId() {
        return schoolId;
    }

    public void setSchoolId(int schoolId) {
        this.schoolId = schoolId;
    }

    public String getRequestTime() {
        return requestTime;
    }

    public void setRequestTime(String requestTime) {
        this.requestTime = requestTime;
    }

    public int getFreeStudentsQuantity() {
        return freeStudentsQuantity;
    }

    public void setFreeStudentsQuantity(int freeStudentsQuantity) {
        this.freeStudentsQuantity = freeStudentsQuantity;
    }
}
