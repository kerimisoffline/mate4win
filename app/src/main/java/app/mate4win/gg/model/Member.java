/*
 * Created by Abdulkerim Yıldırım (kermmyldrm@gmail.com)
 */

package app.mate4win.gg.model;

import java.util.List;

public class Member {
    private String id;
    private String first_name;
    private String last_name;
    private String nick_name;
    private String gender;
    private String country;
    private String city;
    private String bio;
    private String mobile_number;
    private String email;
    private String dc_adress;
    public String has_approved_terms;

    public String getId() {
        return id;
    }

    public String getFirst_name() {
        return first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public String getNick_name() {
        return nick_name;
    }

    public String getEmail() {
        return email;
    }

    public String getGender() {
        return gender;
    }

    public String getCountry() {
        return country;
    }

    public String getCity() {
        return city;
    }

    public String getMobile_number() {
        return mobile_number;
    }

    public String getHas_approved_terms() {
        return has_approved_terms;
    }

    public String getBio() {
        return bio;
    }

    public String getDc_adress() {
        return dc_adress;
    }
}
