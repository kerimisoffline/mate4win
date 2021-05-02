/*
 * Created by Abdulkerim Yıldırım (kermmyldrm@gmail.com)
 */

package app.mate4win.gg.model;

import java.util.List;

public class Post {
    private String post_id;
    private String group_id;
    private String creator_id;
    private String category;
    private String platform;
    private String seek_date;
    private String title;
    private String sub_title;
    private String member_count;
    private String situation;

    public String getPost_id() {
        return post_id;
    }

    public String getGroup_id() {
        return group_id;
    }

    public String getCategory() {
        return category;
    }

    public String getPlatform() {
        return platform;
    }

    public String getSeek_date() {
        return seek_date;
    }

    public String getTitle() {
        return title;
    }

    public String getSub_title() {
        return sub_title;
    }

    public String getMember_count() {
        return member_count;
    }

    public String getCreator_id() {
        return creator_id;
    }

    public String getSituation() {
        return situation;
    }
}
