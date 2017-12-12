package alliance.eaglesbyte.com.eaglesbytealliance.models;

/**
 * Created by kennymore on 12/12/2017.
 */

public class Course {
    private String title;

    private String code;

    private String ects_credits;

    private String user_id;

    private String prerequisite;

    public Course(String title, String code, String ects_credits, String user_id, String prerequisite) {
        this.title = title;
        this.code = code;
        this.ects_credits = ects_credits;
        this.user_id = user_id;
        this.prerequisite = prerequisite;
    }
    public Course() {

    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getEcts_credits() {
        return ects_credits;
    }

    public void setEcts_credits(String ects_credits) {
        this.ects_credits = ects_credits;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getPrerequisite() {
        return prerequisite;
    }

    public void setPrerequisite(String prerequisite) {
        this.prerequisite = prerequisite;
    }

    @Override
    public String toString() {
        return "Course{" +
                "title='" + title + '\'' +
                ", code='" + code + '\'' +
                ", ects_credits='" + ects_credits + '\'' +
                ", user_id='" + user_id + '\'' +
                ", prerequisite='" + prerequisite + '\'' +
                '}';
    }
}
