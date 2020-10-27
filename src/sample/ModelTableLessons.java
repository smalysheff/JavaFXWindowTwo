package sample;

public class ModelTableLessons {
    String id, teacher, course, room, lessonDate;

    public ModelTableLessons(String id, String teacher, String course, String room, String lessonDate) {
        this.id = id;
        this.teacher = teacher;
        this.course = course;
        this.room = room;
        this.lessonDate = lessonDate;
    }

    public String getId() {
        return id;
    }

    public String getTeacher() {
        return teacher;
    }

    public String getCourse() {
        return course;
    }

    public String getRoom() {
        return room;
    }

    public String getLessonDate() {
        return lessonDate;
    }
}
