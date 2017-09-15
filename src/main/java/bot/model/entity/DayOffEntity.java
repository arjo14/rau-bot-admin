package bot.model.entity;

import java.util.Date;

public class DayOffEntity {

    private Integer id;
    private String name;
    private Date date;

    public DayOffEntity() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
