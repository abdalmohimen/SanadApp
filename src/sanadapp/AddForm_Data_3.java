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
public class AddForm_Data_3 {
    private int form_id;
    private int sector_id;
    private String person_name;
    private String type;
    private String state_name;
    private String case_note;
    private String date;
    private String case_add_date;
    private String stating_center;
    
    
    public AddForm_Data_3(int form_id, int sector_id, String person_name, String type,
            String state_name,String note,String date,String case_add_date ,String stat_center) {
        this.form_id = form_id;
        this.sector_id = sector_id;
        this.person_name = person_name;
        this.type = type;
        this.state_name = state_name;
        this.case_note=note;
        this.date=date;
        this.case_add_date=case_add_date;
        this.stating_center=stat_center;
        
    }

    public String getStating_center() {
        return stating_center;
    }

    public String getDate() {
        return date;
    }

    public String getCase_add_date() {
        return case_add_date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setCase_add_date(String case_add_date) {
        this.case_add_date = case_add_date;
    }
    
    

    public void setCase_note(String case_note) {
        this.case_note = case_note;
    }

    public String getCase_note() {
        return case_note;
    }

    public int getForm_id() {
        return form_id;
    }

    public int getSector_id() {
        return sector_id;
    }

    public String getPerson_name() {
        return person_name;
    }

    public String getType() {
        return type;
    }

    public String getState_name() {
        return state_name;
    }

    public void setForm_id(int form_id) {
        this.form_id = form_id;
    }

    public void setSector_id(int sector_id) {
        this.sector_id = sector_id;
    }

    public void setPerson_name(String person_name) {
        this.person_name = person_name;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setState_name(String state_name) {
        this.state_name = state_name;
    }
    
    
      
}
