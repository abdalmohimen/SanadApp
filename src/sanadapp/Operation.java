/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sanadapp;

import com.microsoft.sqlserver.jdbc.SQLServerException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.control.Alert;
import javafx.scene.control.Dialog;

/**
 *
 * @author abd almohimen
 */
public class Operation {

    public static Connection conn_local = null;
    
    //connection string read from file 
    public static String connSting = null;

    public Operation() {
    }

    public static void create_connection() {
        try {
//            String connectionUrl = "jdbc:sqlserver://localhost:1433;"
//                    + "databaseName=sanad_programm;integratedSecurity=false;";
            String connectionUrl=connSting;
            conn_local = DriverManager.getConnection(connectionUrl, "wasbas", "P@ssw0rd");

        } catch (SQLException e) {
            Dialog d3 = new Alert(Alert.AlertType.INFORMATION);
            d3.setContentText(e.getMessage());
            d3.show();
        }
        //return conn_local;
    }

    public static int get_id_value() {

        String query = "select max(form_id)  from form ";
        int id = 0;
        try (PreparedStatement st = conn_local.prepareStatement(query)) {
            ResultSet rs = st.executeQuery();

            if (rs.next()) {
                id = rs.getInt(1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Operation.class.getName()).log(Level.SEVERE, null, ex);
        }
        return id;
    }

    public static void firstpage_insert(AddForm_Data_1 ad) throws SQLException {

        
//            Statement st = conn_local.createStatement();
//            String insert1 = "insert into form values(" + ad.getForm_id() + "," + ad.getSector_num() + ",'" + ad.getCurrent_city()
//                    + "','" + ad.getCurrent_suberb() + "','" + ad.getPrev_city() + "','" + ad.getPrev_suberb() + "','" + ad.getPhone() + "','"
//                    + ad.getMobile() + "','" + ad.getHouse_type() + "','" + ad.getSocial_support() + "','" + ad.getAid_type() + "','"
//                    + ad.getFinancial_state() + "','" + ad.getRent() + "','"+ad.getForm_add_date()+"','"+
//                    ad.getVol_creator_name()+"','"+ad.getVol_evaluate()+"',"+"NULL"+","+"NULL"+" )";
//            int ret1 = st.executeUpdate(insert1);
//            
//            if (ret1 == 0) {
//                System.out.println("error in insert data in form table");
//            }


        PreparedStatement form=null;
        PreparedStatement father=null;
        PreparedStatement mother=null;
        
        String insert1="insert into form values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
        String insert2="insert into person values (?,?,?,?,?,?,?,?,?)";
        String insert3="insert into person values (?,?,?,?,?,?,?,?,?)";
        
        //mother data
        try {
            conn_local.setAutoCommit(false);
            form=conn_local.prepareStatement(insert1);
            father=conn_local.prepareStatement(insert2);
            mother=conn_local.prepareStatement(insert3);
            
            form.setLong(1, ad.getForm_id());
            form.setLong(2, ad.getSector_num());
            form.setString(3, ad.getCurrent_city());
            form.setString(4, ad.getCurrent_suberb());
            form.setString(5, ad.getPrev_city());
            form.setString(6, ad.getPrev_suberb());
            form.setString(7, ad.getPhone());
            form.setString(8, ad.getMobile());
            form.setString(9, ad.getHouse_type());
            form.setString(10, ad.getSocial_support());
            form.setString(11, ad.getAid_type());
            form.setString(12, ad.getFinancial_state());
            form.setString(13, ad.getRent());
            form.setString(14, ad.getForm_add_date());
            form.setString(15, ad.getVol_creator_name());
            form.setString(16, ad.getVol_evaluate());
            form.setString(17, "NULL");
            form.setString(18, "NULL");
            form.setInt(19, ad.getVol_eval());
            form.executeUpdate();
            
            mother.setLong(1, ad.getForm_id());
            mother.setLong(2, ad.getSector_num());
            mother.setString(3, ad.getMother_fname());
            mother.setString(4, "Mother");
            mother.setString(5, ad.getMother_lname());
            mother.setString(6, "Female");
            mother.setString(7, ad.getMother_birth_date());
            mother.setString(8, ad.getMother_current_job());
            mother.setString(9, ad.getMother_prev_job());
            mother.executeUpdate();
            
            father.setLong(1, ad.getForm_id());
            father.setLong(2, ad.getSector_num());
            father.setString(3, ad.getFather_fname());
            father.setString(4, "Father");
            father.setString(5, ad.getFather_lname());
            father.setString(6, "Male");
            father.setString(7, ad.getFather_birth_date());
            father.setString(8, ad.getFather_current_job());
            father.setString(9, ad.getFather_prev_job());
            father.executeUpdate();
            
            conn_local.commit();
        }catch(SQLServerException e){
            e.printStackTrace();
            Dialog alert=new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("notification");
                alert.setContentText(e.getLocalizedMessage());
                e.getMessage();
                alert.show();
                if (conn_local != null) {
                    
                        System.err.print("Transaction is being rolled back");
                        conn_local.rollback();}
                    
            }catch(SQLException sq){
                    sq.printStackTrace();
                    Logger.getLogger(AddFormController.class.getName()).log(Level.SEVERE, null, sq);
                    Dialog d = new Alert(Alert.AlertType.INFORMATION);
                    d.setContentText(sq.getMessage());
                    d.show();
                    if (conn_local != null) {
                    try {
                        System.err.print("Transaction is being rolled back");
                        conn_local.rollback();
                    } catch(SQLException excep) {
                            excep.printStackTrace();
                            }
            
        }

        
    }
        finally {
        if (form != null) {
            form.close();
        }
        if (father != null) {
            father.close();
        }
        if (mother != null) {
            mother.close();
        }
        
        conn_local.setAutoCommit(true);
    }
    }

    public static void secondpage_insert(AddForm_Data_2 ad) {
        try (PreparedStatement pr = conn_local.prepareStatement("insert into person values (?,?,?,?,?,?,?,?,?)");) {
            pr.setLong(1, ad.getFormid());
            pr.setLong(2, ad.getSectorid());
            pr.setString(3, ad.getFname());
            pr.setString(4, ad.getType());
            pr.setString(5, ad.getLname());
            pr.setString(6, ad.getSex());
            pr.setString(7, ad.getBdate());
            pr.setString(8, ad.getJob());
            pr.setString(9, "");

            pr.executeUpdate();
            pr.close();
        } catch (SQLException ex) {
            Logger.getLogger(Operation.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("problem in add data to the second table in tab 2");
        }

    }

    public static void firstpage_update(UpdateForm_Data_1 up, UpdateForm_Data_2 up1, UpdateForm_Data_2 up2,
            int a, int b)  {
        String update1="update  form set  current_city=?,"
                + "current_suberb=?,prev_city=? ,prev_suberb=?,phone=?,mobile=?,house_type=?,social_support=?,"
                + "aid_type=?,financial_state=?,rent=?,form_add_date=?,vol_creator_name=?,vol_evaluate=?,vol_eval=?"
                + " where form_id=?  and sector_num=?";
        
        String update2="update  person set  type=?,"
                + "lname=?,sex=? ,birth_date=?,current_job=?,prev_job=?,fname=?"
                + " where form_id=?  and sector=?  and fname like ?";
        
        String update3="update  person set  type=?,"
                + "lname=?,sex=? ,birth_date=?,current_job=?,prev_job=?,fname=?"
                + " where form_id=?  and sector=?  and fname like ?";
        
        PreparedStatement form=null;
        PreparedStatement father=null;
        PreparedStatement mother=null;
        
        try{   
            conn_local.setAutoCommit(false);
            form=conn_local.prepareStatement(update1);
            form.setString(1, up.getCurr_city());
            form.setString(2, up.getCurr_sub());
            form.setString(3, up.getPrev_city());
            form.setString(4, up.getPrev_sub());
            form.setString(5, up.getPhone());
            form.setString(6, up.getMobile());
            form.setString(7, up.getHouse_type());
            form.setString(8, up.getSocial_supp());
            form.setString(9, up.getAid_type());
            form.setString(10, up.getFin_state());
            form.setString(11, up.getRent());
            form.setString(12, up.getForm_add_date());
            form.setString(13, up.getVol_cre_name());
            form.setString(14, up.getVol_eva_name());
            form.setInt(15, up.getVol_eval());
            form.setInt(16, a);
            form.setInt(17, b);
            

            form.executeUpdate();

            father=conn_local.prepareStatement(update2);
            father.setString(1, up1.getType());
            father.setString(2, up1.getLname());
            father.setString(3, up1.getSex());
            father.setString(4, up1.getBirth_date());
            father.setString(5, up1.getCurrent_job());
            father.setString(6, up1.getPrev_job());
            father.setString(7, up1.getFname_mod());
            father.setInt(8, a);
            father.setInt(9, b);
            father.setString(10, up1.getFname());

            father.executeUpdate();
            
            mother=conn_local.prepareStatement(update3);
            mother.setString(1, up2.getType());
            mother.setString(2, up2.getLname());
            mother.setString(3, up2.getSex());
            mother.setString(4, up2.getBirth_date());
            mother.setString(5, up2.getCurrent_job());
            mother.setString(6, up2.getPrev_job());
            mother.setString(7, up2.getFname_mod());
            mother.setInt(8, a);
            mother.setInt(9, b);
            mother.setString(10, up2.getFname());

            mother.executeUpdate();
            
            conn_local.commit();
            
            if (form != null) {
            form.close();
            }
            if (father != null) {
                father.close();
            }
            if (mother != null) {
                mother.close();
            }
        
            conn_local.setAutoCommit(true);

        }catch(SQLException e)
        {
             if (conn_local != null) {
            try {
                    System.err.print("Transaction is being rolled back");
                    conn_local.rollback();
                } catch(SQLException excep) {
                    excep.printStackTrace();
                }
            
                                    }
        }
        finally {
                }
    }

    public static void secondpage_update(AddForm_Data_2 ad, int s) {
        try (PreparedStatement pr = conn_local.prepareStatement("update  person set  lname=?,sex=?,birth_date=? "
                + ",current_job=?  where form_id=?  and sector=? and fname=? and type=?")) {
            pr.setString(1, ad.getLname());
            pr.setString(2, ad.getSex());
            pr.setString(3, ad.getBdate());
            pr.setString(4, ad.getJob());
            pr.setInt(5, s);
            pr.setInt(6, ad.getSectorid());
            pr.setString(7, ad.getFname());
            pr.setString(8, ad.getType());

            pr.executeUpdate();
            pr.close();
        } catch (SQLException ex) {
            Logger.getLogger(Operation.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("problem in add data to the second table in tab 2");
        }

    }

    public static void thirddpage_insert(AddForm_Data_3 ad) {
        PreparedStatement pr;
        try {
            pr = conn_local.prepareStatement("insert into cases(form_id,sector,person_name,type,status,case_note,"
                    + "date,case_add_date,stating_center)"
                    + " values (?,?,?,?,?,?,?,?,?) ");
            pr.setInt(1, ad.getForm_id());
            pr.setInt(2, ad.getSector_id());
            pr.setString(3, ad.getPerson_name());
            pr.setString(4, ad.getType());
            pr.setString(5, ad.getState_name());
            pr.setString(6, ad.getCase_note());
            pr.setString(7, ad.getDate());
            pr.setString(8, ad.getCase_add_date());
            pr.setString(9, ad.getStating_center());
            pr.executeUpdate();
            pr.close();
        } catch (SQLException ex) {
            Dialog d3 = new Alert(Alert.AlertType.ERROR);
            d3.setContentText(ex.getMessage());
            d3.show();
        }
    }

    public static UpdateForm_Data_1 Bringdata_update_1(int a, int b) {
        String query = "select * from form where form_id=" + a + "and sector_num=" + b;
        UpdateForm_Data_1 up1 = null;
        try (Statement pr = conn_local.createStatement()) {
            ResultSet rs = pr.executeQuery(query);
            if (rs.next()) {
                //System.out.println("ok");
               up1 = new UpdateForm_Data_1(rs.getString("current_city"),
                                           rs.getString("current_suberb"),
                                           rs.getString("prev_city"),
                                           rs.getString("prev_suberb"),
                                           rs.getString("phone"),
                                           rs.getString("mobile"),
                                           rs.getString("house_type"),
                                           rs.getString("social_support"),
                                           rs.getString("aid_type"),
                                           rs.getString("financial_state"),
                                           rs.getString("rent"),
                                           rs.getString("form_add_date"),
                                           rs.getString("vol_creator_name"),
                                           rs.getString("vol_evaluate"),
                                           rs.getInt("vol_eval"));

                            }
        } catch (SQLException ex) {
            Logger.getLogger(Operation.class.getName()).log(Level.SEVERE, null, ex);
        }

        return up1;
    }
    
    

    public static UpdateForm_Data_2 Bringdata_update_2(int a, int b) {
        String query = "select * from person where form_id=" + a + " and sector=" + b + " and type='Father'";

        UpdateForm_Data_2 up2 = null;
        try (Statement st = conn_local.createStatement()) {
            ResultSet rs = st.executeQuery(query);
           // System.out.println("before");
            if (rs.next()) {
                //System.out.println("ok1");
                up2 = new UpdateForm_Data_2(rs.getString(3),
                        rs.getString(4),
                        rs.getString(5),
                        rs.getString(6),
                        rs.getString(7),
                        rs.getString(8),
                        rs.getString(9));
            }
        } catch (SQLException ex) {
            Logger.getLogger(Operation.class.getName()).log(Level.SEVERE, null, ex);

        }
        return up2;
    }

    public static UpdateForm_Data_2 Bringdata_update_3(int a, int b) {
        String query = "select * from person where form_id=" + a + " and sector=" + b + " and type='Mother'";

        UpdateForm_Data_2 up2 = null;
        try (Statement st = conn_local.createStatement()) {
            ResultSet rs = st.executeQuery(query);
            if (rs.next()) {
                System.out.println("ok1");
                up2 = new UpdateForm_Data_2(rs.getString(3),
                        rs.getString(4),
                        rs.getString(5),
                        rs.getString(6),
                        rs.getString(7),
                        rs.getString(8),
                        rs.getString(9));
            }
        } catch (SQLException ex) {
            Logger.getLogger(Operation.class.getName()).log(Level.SEVERE, null, ex);

        }
        return up2;
    }

}
