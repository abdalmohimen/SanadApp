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
public class ClassesNames {
    
    //sector Names
    public static final String sector_Alhamra="الحمراء";
    public static final String sector_AlMedan="الميدان";
    public static final String sector_AlHamediea="الحميدية";
    public static final String sector_Hasiaa="حسياء";
    public static final String sector_other="غير ذلك";
    public static int sectorCode=-1;
    
    //Fields Names
    public static final String form_id = "رقم الاستمارة";
    public static final String sector_num = "رقم القطاع";
    public static final String current_city = "المدينة الحالية";
    public static final String current_suberb = "الحي الحالي";
    public static final String prev_city = "المدينة السابقة";
    public static final String prev_suberb = "الحي السابق";
    public static final String phone = "رقم الهاتف";
    public static final String mobile = "رقم الموبايل";
    public static final String house_type = "نوع السكن";
    public static final String aid_type = "نوع الإغاثة";
    public static final String fin_state = "الوضع المالي";
    public static final String rent = "مبلغ الإيجار";
    public static final String fname = "الاسم";
    public static final String lname = "الكنية";
    public static final String sex = "الجنس";
    public static final String birth_date = "تاريخ الميلاد";
    public static final String curr_job = "العمل الحالي";
    public static final String prev_job = "العمل السابق";
    public static final String type = "نوع الشخص";
    public static final String service_center = "مركز الخدمة";
    public static final String date = "تاريخ آخر متابعة";
    public static final String num_serv = "عدد مرات المتابعة";
    public static final String vol_name = "اسم المتطوع";
    public static final String notes = "ملاحظات المتابعة";
    public static final String is_Ok = "الاستفادة من الخدمة";
    public static final String case_note = "ملاحظات الحالة";
    public static final String sector = "القطاع";
    public static final String status = "الحالة";
    public static final String state_center = "الإحالة";
    public static final String current_job="العمل";
    

    public static String getArabicCol(String name) {
        switch (name.toLowerCase()) {
            case "form_id":
                return form_id;
            case "sector_num":
                return sector_num;
            case "current_city":
                return current_city;
            case "stating_center":
                return state_center;
            case "case_add_date":
                return "تاريخ إضافة الحالة";
            case "status":
                return status;
            case "prev_city":
                return prev_city;
            case "current_suberb":
                return current_suberb;
            case "prev_suberb":
                return prev_suberb;
            case "phone":
                return phone;
            case "mobile":
                return mobile;
            case "house_type":
                return house_type;
            case "social_support":
                return "الدعم الاجتماعي";
            case "aid_type":
                return aid_type;
            case "financial_state":
                return fin_state;
            case "rent":
                return rent;
            case "fname":
                return fname;
            case "person_name":
                return fname;
            case "lname":
                return lname;
            case "sex":
                return sex;
            case "birth_date":
                return birth_date;
            case "current_job":
                return curr_job;
            case "prev_job":
                return prev_job;
            case "type":
                return type;
            case "service_center":
                return service_center;
            case "date":
                return date;
            case "num_of_service":
                return num_serv;
            case "voulnteer_name":
                return vol_name;
            case "notes":
                return notes;
            case "is_ok":
                return is_Ok;
            case "case_note":
                return case_note;
            case "sector":
                return sector;

            default:
                return name;
        }
    }
    public static int getSectorCode(String sectorName){
        switch(sectorName){
            case sector_AlMedan:{
                sectorCode=1;
                return 1;
            }
            case sector_Alhamra:{
                sectorCode=2;
                return 2;
            }
            case sector_AlHamediea:{
                sectorCode=3;
                return 3;
            }
            case sector_Hasiaa:{
                sectorCode=4;
                return 4;
            }
            case sector_other:{
                sectorCode=5;
                return 5;
            }
                
        }
        return -1;
    }
    public static String getSectorName(int s){
        switch(s){
            case 1 :
                return sector_AlMedan;
            case 2 : 
                return sector_Alhamra;
            case 3 :
                return sector_AlHamediea;
            case 4 : 
                return sector_Hasiaa;
            case 5 :
                return sector_other;
        }
        return null;
    }
}
