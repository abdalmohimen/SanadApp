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
public class AddForm_Data_1 {

    private int form_id;
    private int sector_num;
    private String current_city;
    private String current_suberb;
    private String prev_city;
    private String prev_suberb;
    private String phone;
    private String mobile; 
    private String house_type;
    private String rent;
    private String social_support;       
    private String aid_type;       
    private String financial_state;
    private String father_fname;
    private String father_lname;
    private String father_birth_date;
    private String father_current_job;
    private String father_prev_job;
    private String mother_fname;
    private String mother_lname;
    private String mother_birth_date;
    private String mother_current_job;
    private String mother_prev_job;
    private String form_add_date;
    private String vol_creator_name;
    private String vol_evaluate;
    private int vol_eval;

    

    
    
    public  AddForm_Data_1(int a,int b,String father_fname,String father_lname,String father_current_job,
            String father_prev_job,String father_birth_date,String mother_fname,String mother_lname,String mother_current_job
            ,String mother_prev_job,String mother_birth_date,
            String current_city,String current_suberb,String prev_city,String prev_suberb,
            String phone,String mobile,String house_type,String aid_type,boolean social_support,
            String financial_state,String rent,String form_add_date,String vol_name,String vol_evaluate,
            int Vol_Eval)
    {
        this.form_id=a;                                 
        this.sector_num=b;
        //father data
        this.father_fname=father_fname;
        this.father_lname=father_lname;
        this.father_current_job=father_current_job;
        this.father_prev_job=father_prev_job;
        this.father_birth_date=father_birth_date;
        //mother data
        this.mother_fname=mother_fname;
        this.mother_lname=mother_lname;
        this.mother_current_job=mother_current_job;
        this.mother_prev_job=mother_prev_job;
        this.mother_birth_date=mother_birth_date;
        //house
        this.current_city=current_city;
        this.current_suberb=current_suberb;
        this.prev_city=prev_city;
        this.prev_suberb=prev_suberb;
        this.phone=phone;
        this.mobile=mobile;
        this.house_type=house_type;
        this.rent=rent;
        this.aid_type=aid_type;
        if(social_support){
        this.social_support="yes";}
        else{
        this.social_support="no";
        }
        this.financial_state=financial_state;
        this.form_add_date=form_add_date;
        this.vol_creator_name=vol_name;
        this.vol_evaluate=vol_evaluate;
        this.vol_eval=Vol_Eval;
        
        
        
    }

    public int getVol_eval() {
        return vol_eval;
    }

    public void setVol_eval(int vol_eval) {
        this.vol_eval = vol_eval;
    }


    public String getVol_creator_name() {
        return vol_creator_name;
    }

    public String getVol_evaluate() {
        return vol_evaluate;
    }

    public void setVol_creator_name(String vol_creator_name) {
        this.vol_creator_name = vol_creator_name;
    }

    public void setVol_evaluate(String vol_evaluate) {
        this.vol_evaluate = vol_evaluate;
    }
    
    
    
    
    public void setForm_add_date(String form_add_date) {
        this.form_add_date = form_add_date;
    }

    public String getForm_add_date() {
        return form_add_date;
    }
    
    public void setForm_id(int form_id) {
        this.form_id = form_id;
    }

    public void setSector_num(int sector_num) {
        this.sector_num = sector_num;
    }

    public void setCurrent_city(String current_city) {
        this.current_city = current_city;
    }

    public void setRent(String rent) {
        this.rent = rent;
    }

    public String getRent() {
        return rent;
    }

    public void setCurrent_suberb(String current_suberb) {
        this.current_suberb = current_suberb;
    }

    public void setPrev_city(String prev_city) {
        this.prev_city = prev_city;
    }

    public void setPrev_suberb(String prev_suberb) {
        this.prev_suberb = prev_suberb;
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

    public void setSocial_support(boolean social_support) {
        if(social_support){
        this.social_support="A";}
        else{
        this.social_support="B";
        }
    }

    public void setAid_type(String aid_type) {
        this.aid_type = aid_type;
    }

    public void setFinancial_state(String financial_state) {
        this.financial_state = financial_state;
    }

    public void setFather_fname(String father_fname) {
        this.father_fname = father_fname;
    }

    public void setFather_lname(String father_lname) {
        this.father_lname = father_lname;
    }

    public void setFather_birth_date(String father_birth_date) {
        this.father_birth_date = father_birth_date;
    }

    public void setFather_current_job(String father_current_job) {
        this.father_current_job = father_current_job;
    }

    public void setFather_prev_job(String father_prev_job) {
        this.father_prev_job = father_prev_job;
    }

    public void setMother_fname(String mother_fname) {
        this.mother_fname = mother_fname;
    }

    public void setMother_lname(String mother_lname) {
        this.mother_lname = mother_lname;
    }

    public void setMother_birth_date(String mother_birth_date) {
        this.mother_birth_date = mother_birth_date;
    }

    public void setMother_current_job(String mother_current_job) {
        this.mother_current_job = mother_current_job;
    }

    public void setMother_prev_job(String mother_prev_job) {
        this.mother_prev_job = mother_prev_job;
    }

    public long getForm_id() {
        return form_id;
    }

    public long getSector_num() {
        return sector_num;
    }

    public String getCurrent_city() {
        return current_city;
    }

    public String getCurrent_suberb() {
        return current_suberb;
    }

    public String getPrev_city() {
        return prev_city;
    }

    public String getPrev_suberb() {
        return prev_suberb;
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

    public String getSocial_support() {
        //if()
        return social_support;
    }

    public String getAid_type() {
        return aid_type;
    }

    public String getFinancial_state() {
        return financial_state;
    }

    public String getFather_fname() {
        return father_fname;
    }

    public String getFather_lname() {
        return father_lname;
    }

    public String getFather_birth_date() {
        return father_birth_date;
    }

    public String getFather_current_job() {
        return father_current_job;
    }

    public String getFather_prev_job() {
        return father_prev_job;
    }

    public String getMother_fname() {
        return mother_fname;
    }

    public String getMother_lname() {
        return mother_lname;
    }

    public String getMother_birth_date() {
        return mother_birth_date;
    }

    public String getMother_current_job() {
        return mother_current_job;
    }

    public  String getMother_prev_job() {
        return mother_prev_job;
    }

















}
