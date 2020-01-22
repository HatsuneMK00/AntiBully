package xyz.makise.antibully.backend.entity;

public class Course {
    private int chapter;
    /*
    * 非自增
    * 和chapter一起表示是第几章节的第几节课
    * */
    private int courseId;
    private String uri;
    private double duration;

    public int getChapter() {
        return chapter;
    }

    public void setChapter(int chapter) {
        this.chapter = chapter;
    }

    public int getCourseId() {
        return courseId;
    }

    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    public double getDuration() {
        return duration;
    }

    public void setDuration(double duration) {
        this.duration = duration;
    }
}
