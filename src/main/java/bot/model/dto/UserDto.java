package bot.model.dto;

public class UserDto {

    private String name;
    private String institute;
    private String department;
    private Integer group;
    private String lastQuestionTopic;

    public UserDto() {
    }

    public UserDto(String name, String institute, String department, Integer group, String lastQuestionTopic) {
        this.name = name;
        this.institute = institute;
        this.department = department;
        this.group = group;
        this.lastQuestionTopic = lastQuestionTopic;
    }

    public UserDto(String name, String institute, String lastQuestionTopic) {
        this.name = name;
        this.institute = institute;
        this.lastQuestionTopic = lastQuestionTopic;
    }

    public UserDto(String name, String institute, String department, String lastQuestionTopic) {
        this.name = name;
        this.institute = institute;
        this.department = department;
        this.lastQuestionTopic = lastQuestionTopic;
    }

    public UserDto(String name, String lastQuestionTopic) {
        this.name = name;
        this.lastQuestionTopic = lastQuestionTopic;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getInstitute() {
        return institute;
    }

    public void setInstitute(String institute) {
        this.institute = institute;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public Integer getGroup() {
        return group;
    }

    public void setGroup(Integer group) {
        this.group = group;
    }

    public String getLastQuestionTopic() {
        return lastQuestionTopic;
    }

    public void setLastQuestionTopic(String lastQuestionTopic) {
        this.lastQuestionTopic = lastQuestionTopic;
    }
}
