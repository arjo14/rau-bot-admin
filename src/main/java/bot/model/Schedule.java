package bot.model;

import java.util.Date;

public class Schedule {

    private long id;
    private long groupId;
    private long subId;
    private Date day;
    private long lessonId;
    private long roomId;
    private long lessonType;

    public Schedule() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getGroupId() {
        return groupId;
    }

    public void setGroupId(long groupId) {
        this.groupId = groupId;
    }

    public long getSubId() {
        return subId;
    }

    public void setSubId(long subId) {
        this.subId = subId;
    }

    public Date getDay() {
        return day;
    }

    public void setDay(Date day) {
        this.day = day;
    }

    public long getLessonId() {
        return lessonId;
    }

    public void setLessonId(long lessonId) {
        this.lessonId = lessonId;
    }

    public long getRoomId() {
        return roomId;
    }

    public void setRoomId(long roomId) {
        this.roomId = roomId;
    }

    public long getLessonType() {
        return lessonType;
    }

    public void setLessonType(long lessonType) {
        this.lessonType = lessonType;
    }
}
