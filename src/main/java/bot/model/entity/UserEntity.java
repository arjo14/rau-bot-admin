package bot.model.entity;

public class UserEntity {

    private Integer id;
    private String name;
    private Integer groupId;
    private Integer chatId;

    public UserEntity() {
    }

    public UserEntity(Integer id, String name, Integer groupId, Integer chatId) {
        this.id = id;
        this.name = name;
        this.groupId = groupId;
        this.chatId = chatId;
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

    public Integer getGroupId() {
        return groupId;
    }

    public void setGroupId(Integer groupId) {
        this.groupId = groupId;
    }

    public Integer getChatId() {
        return chatId;
    }

    public void setChatId(Integer chatId) {
        this.chatId = chatId;
    }
}
