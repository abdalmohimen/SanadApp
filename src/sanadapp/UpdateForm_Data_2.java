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
public class UpdateForm_Data_2 {
    
    private String fname;
    private String fname_mod;
    private String type;
    private String lname;
    private String sex;
    private String birth_date;
    private String current_job;
    private String prev_job;
    
    
    
      public UpdateForm_Data_2(String fname, String type, String lname, String sex,
            String birth_date, String current_job, String prev_job) {
        this.fname = fname;
        this.type = type;
        this.lname = lname;
        this.sex = sex;
        this.birth_date = birth_date;
        this.current_job = current_job;
        this.prev_job = prev_job;
     
    }


    

    public UpdateForm_Data_2(String fname, String type, String lname, String sex,
            String birth_date, String current_job, String prev_job,String fname_modf) {
        this.fname = fname;
        this.type = type;
        this.lname = lname;
        this.sex = sex;
        this.birth_date = birth_date;
        this.current_job = current_job;
        this.prev_job = prev_job;
        this.fname_mod=fname_modf;
    }


    
    
    
    
    
    
    public void setFname(String fname) {
        this.fname = fname;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public void setBirth_date(String birth_date) {
        this.birth_date = birth_date;
    }

    public void setCurrent_job(String current_job) {
        this.current_job = current_job;
    }

    public void setPrev_job(String prev_job) {
        this.prev_job = prev_job;
    }    
    
    
    
    public String getFname() {
        return fname;
    }

    public String getType() {
        return type;
    }

    public String getLname() {
        return lname;
    }

    public String getSex() {
        return sex;
    }

    public String getBirth_date() {
        return birth_date;
    }

    public String getCurrent_job() {
        return current_job;
    }

    public String getPrev_job() {
        return prev_job;
    }

    public String getFname_mod() {
        return fname_mod;
    }

    
    
            
            
    
}
