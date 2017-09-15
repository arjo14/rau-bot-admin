package bot.model.dto;

public class UserDetailsDto {

    private String fullName;
    private String instName;
    private String deptName;
    private Integer groupNum;

    public UserDetailsDto() {
    }

    public UserDetailsDto(String fullName, String instName, String deptName, Integer groupNum) {
        this.fullName = fullName;
        this.instName = instName;
        this.deptName = deptName;
        this.groupNum = groupNum;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getInstName() {
        return instName;
    }

    public void setInstName(String instName) {
        this.instName = instName;
    }

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

    public Integer getGroupNum() {
        return groupNum;
    }

    public void setGroupNum(Integer groupNum) {
        this.groupNum = groupNum;
    }
}
