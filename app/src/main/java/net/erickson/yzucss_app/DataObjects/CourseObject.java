package net.erickson.yzucss_app.DataObjects;

/**
 * Created by Erickson on 2014/12/22.
 */
public class CourseObject {
    private long id;
    private CharSequence name;
    private CharSequence code;
    private CharSequence teacher;
    private CharSequence time;
    private CharSequence degree;
    private CharSequence year;

    public CourseObject(long id, CharSequence name, CharSequence code,
                        CharSequence teacher, CharSequence time,
                        CharSequence degree, CharSequence year) {
        this.id = id;
        this.name = name;
        this.code = code;
        this.teacher = teacher;
        this.time = time;
        this.degree = degree;
        this.year = year;
    }

    public CourseObject()
    {
    }

    public long getId() {
        return  id;
    }

    public CharSequence getName() {
        return name;
    }

    public CharSequence getCode() {
        return code;
    }

    public CharSequence getTeacher() {
        return teacher;
    }

    public CharSequence getTime() {
        return time;
    }

    public CharSequence getDegree() {
        return degree;
    }

    public CharSequence getYear() {
        return year;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setName(CharSequence name) {
        this.name = name;
    }

    public void setCode(CharSequence code) {
        this.code = code;
    }

    public void setTeacher(CharSequence teacher) {
        this.teacher = teacher;
    }

    public void setTime(CharSequence time) {
        this.time = time;
    }

    public void setDegree(CharSequence degree) {
        this.degree = degree;
    }

    public void setYear(CharSequence year) {
        this.year = year;
    }
}
