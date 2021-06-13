package edu.scholar_app.domain;

public class Student extends User {

    private float av1Grade;
    private float av2Grade;

    public Student() {
        super();
    }

    @Override
    public String getStatus() {
        if (getAverage() >= 7) {
            return "Approved";
        } else if (getAverage() < 4) {
            return "Disapproved";
        } else {
            return "Final Test";
        }
    }

    @Override
    public void print() {
        super.print();
        System.out.printf(" - AV1(%.2f) + AV2(%.2f) = Average(%.2f) - Status (%s)\n",
                av1Grade,
                av2Grade,
                getAverage(),
                getStatus()
        );
    }

    public float getAverage() {
        return (getAv1Grade() + getAv2Grade()) / 2;
    }

    public float getAv1Grade() {
        return av1Grade;
    }

    public void setAv1Grade(float av1Grade) {
        this.av1Grade = av1Grade;
    }

    public float getAv2Grade() {
        return av2Grade;
    }

    public void setAv2Grade(float av2Grade) {
        this.av2Grade = av2Grade;
    }
}
