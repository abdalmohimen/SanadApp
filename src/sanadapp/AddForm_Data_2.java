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
public class AddForm_Data_2 {
    private int formid;
    private int sectorid;
    private String fname;
    private String lname;
    private String sex;
    private String bdate;
    private String job;
    private String type;

    
    
    public AddForm_Data_2(int formid,int sectorid,String fname, String lname,
            String sex, String bdate, String job, String type) {
        
        this.formid=formid;
        this.sectorid=sectorid;
        this.fname = fname;
        this.lname = lname;
        this.sex = sex;
        this.bdate = bdate;
        this.job = job;
        this.type = type;
    }

    public void setFormid(int formid) {
        this.formid = formid;
    }

    public void setSectorid(int sectorid) {
        this.sectorid = sectorid;
    }

    public int getFormid() {
        return formid;
    }

    public int getSectorid() {
        return sectorid;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public void setBdate(String bdate) {
        this.bdate = bdate;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getFname() {
       
        return fname;
    }

    public String getLname() {
        return lname;
    }

    public String getSex() {
        return sex;
    }

    public String getBdate() {
        return bdate;
    }

    public String getJob() {
        return job;
    }

    public String getType() {
        return type;
    }
    
    
}
