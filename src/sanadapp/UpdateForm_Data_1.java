/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package sanadapp;

/**
 *
 * @author abd almohimen
 */
public class UpdateForm_Data_1 {
    private String curr_city;
    private String curr_sub;
    private String prev_city;
    private String prev_sub;
    private String phone;
    private String mobile;
    private String house_type;
    private String social_supp;
    private String aid_type;
    private String fin_state;
    private String rent;
    private String form_add_date;
    private String vol_cre_name;
    private String Vol_eva_name;
    private int vol_eval;

    
    
    public UpdateForm_Data_1(String curr_city, String curr_sub, String prev_city,
            String prev_sub, String phone, String mobile, String house_type, 
            String social_supp, String aid_type, String fin_state, String rent,String form_add_date,
            String volname,String voleva,int Vol_Eval) {
        this.curr_city = curr_city;
        this.curr_sub = curr_sub;
        this.prev_city = prev_city;
        this.prev_sub = prev_sub;
        this.phone = phone;
        this.mobile = mobile;
        this.house_type = house_type;
        this.social_supp = social_supp;
        this.aid_type = aid_type;
        this.fin_state = fin_state;
        this.rent = rent;
        this.form_add_date=form_add_date;
        this.vol_cre_name=volname;
        this.Vol_eva_name=voleva;
        this.vol_eval=Vol_Eval;
    }

    public int getVol_eval() {
        return vol_eval;
    }

    public void setVol_eval(int vol_eval) {
        this.vol_eval = vol_eval;
    }
 
    
    
    
    
    public String getVol_cre_name() {
        return vol_cre_name;
    }

    public String getVol_eva_name() {
        return Vol_eva_name;
    }

    
    
    
    
    
    
    public void setForm_add_date(String form_add_date) {
        this.form_add_date = form_add_date;
    }

    public String getForm_add_date() {
        return form_add_date;
    }

    public void setCurr_city(String curr_city) {
        this.curr_city = curr_city;
    }

    public void setCurr_sub(String curr_sub) {
        this.curr_sub = curr_sub;
    }

    public void setPrev_city(String prev_city) {
        this.prev_city = prev_city;
    }

    public void setPrev_sub(String prev_sub) {
        this.prev_sub = prev_sub;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public void setHouse_type(String house_type) {
        this.house_type = house_type;
    }

    public void setSocial_supp(String social_supp) {
        this.social_supp = social_supp;
    }

    public void setAid_type(String aid_type) {
        this.aid_type = aid_type;
    }

    public void setFin_state(String fin_state) {
        this.fin_state = fin_state;
    }

    public void setRent(String rent) {
        this.rent = rent;
    }

    public UpdateForm_Data_1(String curr_city, String curr_sub, String prev_city,
            String prev_sub, String phone, String mobile, String house_type, 
            String social_supp, String aid_type, String fin_state, String rent) {
        this.curr_city = curr_city;
        this.curr_sub = curr_sub;
        this.prev_city = prev_city;
        this.prev_sub = prev_sub;
        this.phone = phone;
        this.mobile = mobile;
        this.house_type = house_type;
        this.social_supp = social_supp;
        this.aid_type = aid_type;
        this.fin_state = fin_state;
        this.rent = rent;
    }

    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    public String getCurr_city() {
        return curr_city;
    }

    public String getCurr_sub() {
        return curr_sub;
    }

    public String getPrev_city() {
        return prev_city;
    }

    public String getPrev_sub() {
        return prev_sub;
    }

    public String getPhone() {
        return phone;
    }

    public String getMobile() {
        return mobile;
    }

    public String getHouse_type() {
        return house_type;
    }

    public String getSocial_supp() {
        return social_supp;
    }

    public String getAid_type() {
        return aid_type;
    }

    public String getFin_state() {
        return fin_state;
    }

    public String getRent() {
        return rent;
    }
    
    
    
    
}
