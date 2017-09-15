package bot.model.entity;

public class TimeTableEntity {

    private Integer id;
    private String lesson;

    public TimeTableEntity() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLesson() {
        return lesson;
    }

    public void setLesson(String lesson) {
        this.lesson = lesson;
    }
}
