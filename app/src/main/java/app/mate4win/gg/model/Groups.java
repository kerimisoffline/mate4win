/*
 * Created by Abdulkerim Yıldırım (kermmyldrm@gmail.com)
 */

package app.mate4win.gg.model;

import java.util.List;

public class Groups {
    private String id;
    private String title;
    private String sub_title;
    private String creator_id;
    private String description;
    private String email;
    private String country;
    private String city;
    private String telegram;
    private String dc_adress;
    private String insta_adress;
    private String ts_adress;
    private String skype_adress;
    private String group_platform;
    private String categories;
    private String member_count;
    private List<Member> members;
    private List<Member> pending;

    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getSub_title() {
        return sub_title;
    }

    public String getDescription() {
        return description;
    }

    public String getEmail() {
        return email;
    }

    public String getCountry() {
        return country;
    }

    public String getCity() {
        return city;
    }

    public String getTelegram() {
        return telegram;
    }

    public String getDc_adress() {
        return dc_adress;
    }

    public String getInsta_adress() {
        return insta_adress;
    }

    public String getTs_adress() {
        return ts_adress;
    }

    public String getSkype_adress() {
        return skype_adress;
    }

    public String getGroup_platform() {
        return group_platform;
    }

    public String getCategories() {
        return categories;
    }

    public String getCreator_id() { return creator_id; }

    public String getMember_count() { return member_count; }

    public List<Member> getMembers() {
        return members;
    }

    public List<Member> getPending() {
        return pending;
    }
}
