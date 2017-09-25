/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sanadapp;

import java.io.IOException;
import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Control;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.util.Callback;
import org.controlsfx.control.textfield.TextFields;
import static sanadapp.Operation.conn_local;

/**
 * FXML Controller class
 *
 * @author abd almohimen
 */
public class UpdateFormController implements Initializable {
    public String type=null;
    public String father_name;
    public String mother_name;

    @FXML
    Button Bringdata_1, Edit_tab_1, Add_2_son, Delete_2_son, Edit_2_son, Add_2_m, Delete_2_m, Edit_2_m,
            Addstate_tab_3, Deletestate_tab_3, Showstate_tab_3,Delete_tab_1;

    @FXML
    TextField Formid_1, Sectornum_1, Ffname_1, Flname_1, Fcurr_job_1, Fprev_job_1, Mfname_1, Mlname_1,
            Mcurr_job_1, Mprev_job_1, Curr_city_1, Curr_sub_1, Prev_city_1, Prev_sub_1, Phone_1, Mobile_1,
            Fname_2_son, Lname_2_son, Fname_2_m, Lname_2_m,Statenote_tab_3,Rent_1;
    @FXML
    TextArea Vol_eva_name;

    @FXML
    ComboBox House_type_1, Aid_type_1, Fin_state_1, Job_2_son
            , State_tab_3, Gstate_tab_3,Vol_cre_name,Vol_Eval;

    @FXML
    CheckBox Social_1, Male_2_son, Female_2_son, Male_2_m, Female_2_m, Type_2_son, Type_2_m,Addstate_2
            ,Deletestate_2,Editstate_2;

    @FXML
    DatePicker Fbdate_1, Mbdate_1, Bdate_2_son, Bdate_2_m,Form_add_date,Adding_state_tab_3;

    @FXML
    Label State_tab_2, Person_tab_3, Type_tab_3_1,Job_2_son_label;

    @FXML
    private TableView<?> Table_tab_2, Table_tab_3;

    @FXML
    Tab Tab_1, Tab_2, Tab_3;

    private ObservableList<ObservableList> data;

    @FXML
    @SuppressWarnings("empty-statement")
    public void Bringdata_1_handler(ActionEvent event) {
        if (Formid_1.getText().trim().isEmpty() && Sectornum_1.getText().trim().isEmpty()) {

            setErrorAlert(Formid_1);
            setErrorAlert(Sectornum_1);
            State_tab_2.setText("أدخل رقم الاستمارة ورقم القطاع");
        } else if (Formid_1.getText().trim().isEmpty()) {
            setErrorAlert(Formid_1);
            State_tab_2.setText("أدخل رقم الاستمارة");
        } else if (Sectornum_1.getText().trim().isEmpty()) {
            setErrorAlert(Sectornum_1);
            State_tab_2.setText("أدخل رقم القطاع");

        } else {
            int form = Integer.parseInt(Formid_1.getText());
            int sect = Integer.parseInt(Sectornum_1.getText());

            try{
                UpdateForm_Data_1 up = Operation.Bringdata_update_1(form, sect);
                Curr_city_1.setText(up.getCurr_city());
                Curr_sub_1.setText(up.getCurr_sub());
                Prev_city_1.setText(up.getPrev_city());
                Prev_sub_1.setText(up.getPrev_sub());
                Phone_1.setText(up.getPhone());
                Mobile_1.setText(up.getMobile());
                House_type_1.setValue(up.getHouse_type());
              
           if(up.getHouse_type()==null)
           {}
           else if (up.getHouse_type().trim().equals("إيجار"))
            {   
                Rent_1.setDisable(false);
                Rent_1.setText(up.getRent());
            }
            Aid_type_1.setValue(up.getAid_type());
            if(up.getSocial_supp()==null)
            {}
            else if (up.getSocial_supp().trim().equals("yes")) {
                Social_1.setSelected(true);
            }
            if(Vol_Eval == null)
            {}
            else
            {
                Vol_Eval.setValue(up.getVol_eval());
            }
            Fin_state_1.setValue(up.getFin_state());
            Form_add_date.setValue(LocalDate.parse(up.getForm_add_date().trim()));
            Vol_cre_name.setValue(up.getVol_cre_name().toString().trim());
            Vol_eva_name.setText(up.getVol_eva_name());

            UpdateForm_Data_2 up2;
            up2 = Operation.Bringdata_update_2(form, sect);
            //System.out.println(sect);
            father_name=up2.getFname().trim();
            Ffname_1.setText(up2.getFname().trim());
            Flname_1.setText(up2.getLname());
            Fcurr_job_1.setText(up2.getCurrent_job());
            Fprev_job_1.setText(up2.getPrev_job());
            Fbdate_1.setValue(LocalDate.parse(up2.getBirth_date().trim()));

            UpdateForm_Data_2 up3 = Operation.Bringdata_update_3(form, sect);
            mother_name=up3.getFname().trim();
            Mfname_1.setText(up3.getFname().trim());
            Mlname_1.setText(up3.getLname());
            Mcurr_job_1.setText(up3.getCurrent_job());
            Mprev_job_1.setText(up3.getPrev_job());
            Mbdate_1.setValue(LocalDate.parse(up3.getBirth_date().trim()));
            //bring from person table
            Formid_1.setDisable(true);
            Sectornum_1.setDisable(true);
            Tab_1.setDisable(false);
            Tab_2.setDisable(false);
            Tab_3.setDisable(false);

            show_table2();
            show_table3();

            }catch(NullPointerException e)
            {
                e.printStackTrace();
                Dialog alert=new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("notification");
                alert.setContentText("لا يوجد استمارة تحمل هذا الرقم");
                alert.show();
            }
        }
    }

    
    @FXML
    public void Edit_tab_1_handler(ActionEvent event) {
       //try{
           
        String rent = "";
        String social = "";
        if (House_type_1.getValue()!=null&& House_type_1.getValue().toString().trim().equals("إيجار"))
        {
            rent = Rent_1.getText();
        }

        if (Social_1.isSelected()) {
            social = "yes";
        } else {
            social = "no";
        }
        
            String str_house = "";
            String str_aid = "";
            String str_fin = "";
            if (House_type_1.getValue() == null) {

            } else 
            {
                str_house = House_type_1.getValue().toString();
            }
            if (Aid_type_1.getValue() == null) {
            } else 
            {
                str_aid = Aid_type_1.getValue().toString();
            }
            if (Fin_state_1.getValue() == null) {
            } else 
            {
                str_fin = Fin_state_1.getValue().toString();
            }
        

        UpdateForm_Data_1 up = new UpdateForm_Data_1(Curr_city_1.getText(), Curr_sub_1.getText(),
                Prev_city_1.getText(),
                Prev_sub_1.getText(),
                Phone_1.getText(),
                Mobile_1.getText(),
                str_house,
                social,
                str_aid,
                str_fin,
                rent,
                Form_add_date.getValue().toString().trim(),
                Vol_cre_name.getValue().toString().trim(),
                Vol_eva_name.getText(),
                Integer.parseInt(Vol_Eval.getValue().toString()));

           
        //father
        if(Ffname_1.getText().equals("")|Ffname_1.getText().equals(" ")|Ffname_1.getText() == null)
        { 
            show_dialog("لا يمكن تعبئة اسم الأب بفراغ");
            return;
            //throw new SQLException();   
        }
        UpdateForm_Data_2 up1 = new UpdateForm_Data_2(father_name,
                "Father", Flname_1.getText(),
                "Male", Fbdate_1.getValue().toString().trim(), Fcurr_job_1.getText(),
                Fprev_job_1.getText(),Ffname_1.getText());
        
        //mother Mfname_1
        if(Mfname_1.getText().equals("")|Mfname_1.getText().equals(" ")|Mfname_1.equals("  ")|Mfname_1.getText() == null)
        { 
            show_dialog("لا يمكن تعبئة اسم الأم بفراغ");
            return;
            //throw new SQLException();   
        }else if(Mfname_1.getText().trim().matches("[A-Za-z]"))
        {
            show_dialog("اسم خاطئ يرجى الإدخال بشكل صحيح");
        }
        UpdateForm_Data_2 up2 = new UpdateForm_Data_2(mother_name,
                "Mother", Mlname_1.getText(),
                "Female", Mbdate_1.getValue().toString().trim(),
                Mcurr_job_1.getText(), Mprev_job_1.getText(),Mfname_1.getText());
                System.out.println(Mfname_1.getText());
        
        Operation.firstpage_update(up, up1, up2, Integer.parseInt(Formid_1.getText().trim()),
                Integer.parseInt(Sectornum_1.getText()));
            
        
            show_table2();
            show_table3();
        show_dialog("تم التعديل");
//       }
//    catch(SQLException e)
//    {
//        show_dialog("الرجاء التأكد من البيانات المدخلة"+"\n"+e.getMessage());
//    }
    }
    
    
    
    @FXML
    public void Delete_tab_1_handler(ActionEvent event)
    {
        String query="delete from  form where form.form_id="+Integer.parseInt(Formid_1.getText().trim())+
                "  and form.sector_num="+Integer.parseInt(Sectornum_1.getText());
        
        Dialog alert=new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("رسالة تأكيد");
        alert.setContentText("هل تريد حذف هذه الاستمارة");
        Optional<ButtonType> result=alert.showAndWait();
        if(result.get()==ButtonType.OK)
        {
            try(PreparedStatement pr =conn_local.prepareStatement(query))
            {
                pr.executeUpdate();
                pr.close();
                Stage stage = (Stage) Delete_tab_1.getScene().getWindow();
                Parent root = FXMLLoader.load(getClass().getResource("MainInterface.fxml"));
                Scene scene = new Scene(root);
                stage.setScene(scene);
                root = null;
                scene = null;
                stage.setResizable(false);
                stage.setX(15);
                stage.setY(2);
                stage.setTitle("إنشاء استمارة جديدة");
                stage.show();
            }
            catch(SQLException ex)
            {
                Dialog d=new Alert(Alert.AlertType.INFORMATION);
                   d.setContentText("مشكلة في حذف الاستمارة");
                   d.show();
            } catch (IOException ex) {
                Logger.getLogger(UpdateFormController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    
    

    @FXML
    public void show_dialog(String s)
    {
        Dialog d2 = new Alert(Alert.AlertType.INFORMATION);
        d2.setContentText(s);
        d2.show();
    }
    
    
    @FXML
    public String null_tester(String s) {
        if (s.trim().isEmpty()) {
            return null;
        } else {
            return s;
        }
    }
    
    
    @FXML
    public void show_table2() {
        String table_2_query = "select fname,lname,sex,type,current_job,birth_date   from person where person.form_id="
                + Integer.parseInt(Formid_1.getText().trim())
                + "  and person.sector=" + Integer.parseInt(Sectornum_1.getText())
                + " and person.type in ('Son','M')";

        show(Table_tab_2, table_2_query);
    }

    @FXML
    public void show_table3() {
        String query2 = "select  fname,type  from person where form_id="
                + Integer.parseInt(Formid_1.getText().trim())
                + " and sector=" + Integer.parseInt(Sectornum_1.getText());

        show(Table_tab_3, query2);

    }
    
    
    
    

    @FXML
    public void show_tab3_combo() {
        State_tab_3.getItems().clear();
        String query3 = "select status from cases where form_id=" + Integer.parseInt(Formid_1.getText().trim())
                + "  and sector=" + Integer.parseInt(Sectornum_1.getText()) + " and person_name='" + Person_tab_3.getText() + "'"+
                "  and type='"+type.trim()+"'";

        try (Statement st = conn_local.createStatement()) {
            ResultSet rs = st.executeQuery(query3);
            while (rs.next()) {
                State_tab_3.getItems().add(rs.getString(1));
            }
        } catch (SQLException ex) {
            Logger.getLogger(UpdateFormController.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("here");
        }
    }
    
    
    
    
    @FXML
    public void addnote_tab_3_handler()
    {
         if(!Statenote_tab_3.getText().isEmpty()){
        String query3 = "update  cases set case_note='"+ Statenote_tab_3.getText().trim()+"'  where form_id="
                + Integer.parseInt(Formid_1.getText().trim())
                + "  and sector=" + Integer.parseInt(Sectornum_1.getText()) + " and person_name='" +
                Person_tab_3.getText() + "'"+" and type='"+type.trim()+"'"+" and status='"+
                State_tab_3.getValue().toString().trim()+"'";
        try (Statement st = conn_local.createStatement()) {
            int rs = st.executeUpdate(query3);
            
        } catch (SQLException ex) {
            Logger.getLogger(UpdateFormController.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("here");
        }catch(NullPointerException e)
        {
            show_dialog("الرجاء اختيار الحالة التي تريد ادخال هذه الملاحظة لها");
        }

        }
    }
    
    
    
    
    @FXML
    public void show_tab3_statenote()
    {
        if(State_tab_3.getValue()!=null ){
        String query3 = "select case_note from cases where form_id=" + Integer.parseInt(Formid_1.getText().trim())
                + "  and sector=" + Integer.parseInt(Sectornum_1.getText()) + " and person_name='" +
                Person_tab_3.getText() + "'"+" and type='"+type.trim()+"'"+" and status='"+
                State_tab_3.getValue().toString().trim()+"'";
        try (Statement st = conn_local.createStatement()) {
            ResultSet rs = st.executeQuery(query3);
            while (rs.next()) {
                Statenote_tab_3.setText(rs.getString(1));
            }
        } catch (SQLException ex) {
            Logger.getLogger(UpdateFormController.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("here");
        }

        }
    }
    
    @FXML
    public void State_tab_3_handler(ActionEvent event)
    {
        show_tab3_statenote();
    }

    @FXML
    public void Showstate_tab_3_handler(ActionEvent event) {

        show_tab3_combo();
        show_tab3_statenote();
    }

    @FXML
    public void Deletestate_tab_3_handler(ActionEvent event) {
        String query = "delete from cases where form_id=" + Integer.parseInt(Formid_1.getText().trim())
                + " and sector=" + Integer.parseInt(Sectornum_1.getText()) + " and person_name='"
                + Person_tab_3.getText().trim() + "' and type='" + Type_tab_3_1.getText() + "'"
                + "  and status='" + State_tab_3.getValue().toString().trim() + "'";

        try (Statement st = conn_local.createStatement()) {
            st.executeUpdate(query);

        } catch (SQLException e) {
            System.out.println("in delete from cases");
        }

        show_table3();
        show_tab3_combo();
    }

    @FXML
    public void Addstate_tab_3_handler(ActionEvent event) {

        PreparedStatement pr;
        try {
            pr = conn_local.prepareStatement("insert into cases(form_id,sector,person_name,type,status,case_add_date,case_note)"
                    + " values (?,?,?,?,?,?,?) ");
            pr.setInt(1, Integer.parseInt(Formid_1.getText().trim()));
            pr.setInt(2, Integer.parseInt(Sectornum_1.getText()));
            pr.setString(3, Person_tab_3.getText().trim());
            pr.setString(4, Type_tab_3_1.getText().trim());
            pr.setString(5, Gstate_tab_3.getValue().toString().trim());
            pr.setString(6, Adding_state_tab_3.getValue().toString().trim());
            pr.setString(7, Statenote_tab_3.getText().trim());
            pr.executeUpdate();
            pr.close();
        } catch (SQLException ex) {
            Logger.getLogger(Operation.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("problem in add data to the second table in tab 2");
        }catch(NullPointerException e)
        {
            show_dialog("الرجاء اختيار الشخص الذي تريد إضافة الحالة له");
        }
        show_table3();
        show_tab3_combo();

    }

    @FXML
    public void Add_2_son_handler(ActionEvent event) {
        if(Addstate_2.isSelected())
        {
            String se = null;

        if (Type_2_son.isSelected()) {
            se = "Son";
        } else if (Type_2_m.isSelected()) {
            se = "M";
        } else if (Type_2_son.isSelected() && Type_2_m.isSelected()) {
            setErrorAlert(Type_2_m);
            setErrorAlert(Type_2_son);
            State_tab_2.setText("اختر واحد فقط");
            return;
        } else {
            setErrorAlert(Type_2_m);
            setErrorAlert(Type_2_son);
            State_tab_2.setText("الرجاء اختيار النوع");
            return;
        }

        if (Fname_2_son.getText().trim().isEmpty()) {
            setErrorAlert(Fname_2_son);
            setErrorAlert(State_tab_2);
            State_tab_2.setText("الرجاء ادخال اسم الولد");
        } else {

            String s = "";
            if (Male_2_son.isSelected() && Female_2_son.isSelected()) {
                setErrorAlert(Male_2_son);
                setErrorAlert(Female_2_son);

            } else if (Male_2_son.isSelected()) {
                s = "Male";
                String str_job;
                if(!(Job_2_son.getValue()==null))
                { str_job = Job_2_son.getValue().toString().trim();}
                else
                {str_job="";}

                AddForm_Data_2 ad = new AddForm_Data_2(Integer.parseInt(Formid_1.getText().trim()),
                        Integer.parseInt(Sectornum_1.getText()),
                        Fname_2_son.getText(),
                        Lname_2_son.getText(),
                        s,
                        Bdate_2_son.getValue().toString(),
                        str_job,
                        se);

                Operation.secondpage_insert(ad);
               show_dialog("تم الإضافة");
                show_table2();
                show_table3();
            } else if (Female_2_son.isSelected()) {
                s = "Female";
                //se="Son";
                String str_job = "";
                str_job = "";
                AddForm_Data_2 ad = new AddForm_Data_2(Integer.parseInt(Formid_1.getText().trim()),
                        Integer.parseInt(Sectornum_1.getText()),
                        Fname_2_son.getText(),
                        Lname_2_son.getText(),
                        s,
                        Bdate_2_son.getValue().toString(),
                        str_job,
                        se);

                Operation.secondpage_insert(ad);
                show_dialog("تم الإضافة");
                show_table2();
                show_table3();
            } else {
                setErrorAlert(Male_2_son);
                setErrorAlert(Female_2_son);
                State_tab_2.setText("رجاء اختر الجنس");
            }
        }
    }else
        {
            show_dialog("الرجاء اختيار وضع الإضافة");
        }
        
    }

    @FXML
    public void Edit_2_son_handler(ActionEvent event) {
        
        if(Editstate_2.isSelected())
        {
            String se = null;

            if (Type_2_son.isSelected()) {
                se = "Son";
            } else if (Type_2_m.isSelected()) {
                se = "M";
            } else if (Type_2_son.isSelected() && Type_2_m.isSelected()) {
                setErrorAlert(Type_2_m);
                setErrorAlert(Type_2_son);
                State_tab_2.setText("اختر واحد فقط");
                return;
            } else {
                setErrorAlert(Type_2_m);
                setErrorAlert(Type_2_son);
                State_tab_2.setText("الرجاء اختيار النوع");
                return;
            }

            if (Fname_2_son.getText().trim().isEmpty()) {
                setErrorAlert(Fname_2_son);
                setErrorAlert(State_tab_2);
                State_tab_2.setText("الرجاء ادخال اسم الولد");
            } else {

                String s = "";
                if (Male_2_son.isSelected() && Female_2_son.isSelected()) {
                    setErrorAlert(Male_2_son);
                    setErrorAlert(Female_2_son);

                } else if (Male_2_son.isSelected()) {
                    s = "Male";
                    String str_job;
                if(!(Job_2_son.getValue()==null))
                { str_job = Job_2_son.getValue().toString().trim();}
                else
                {str_job="";}

                AddForm_Data_2 ad = new AddForm_Data_2(Integer.parseInt(Formid_1.getText().trim()),
                        Integer.parseInt(Sectornum_1.getText()),
                        Fname_2_son.getText().trim(),
                        Lname_2_son.getText(),
                        s,
                        Bdate_2_son.getValue().toString(),
                        str_job,
                        se);

                Operation.secondpage_update(ad, Integer.parseInt(Formid_1.getText().trim()));
                show_dialog("تم التعديل");
                show_table2();
                show_table3();
            } else if (Female_2_son.isSelected()) {
                s = "Female";
                //se="Son";
                String str_job;
                if(!(Job_2_son.getValue()==null))
                { str_job = Job_2_son.getValue().toString().trim();}
                else
                {str_job="";}

                AddForm_Data_2 ad = new AddForm_Data_2(Integer.parseInt(Formid_1.getText().trim()),
                        Integer.parseInt(Sectornum_1.getText()),
                        Fname_2_son.getText(),
                        Lname_2_son.getText(),
                        s,
                        Bdate_2_son.getValue().toString(),
                        str_job,
                        se);

                Operation.secondpage_update(ad, Integer.parseInt(Formid_1.getText().trim()));
                show_dialog("تم التعديل");
                show_table2();
                show_table3();
            } else {
                setErrorAlert(Male_2_son);
                setErrorAlert(Female_2_son);
                State_tab_2.setText("رجاء اختر الجنس");
            }
        }
        }else
        {
             show_dialog("الرجاء اختيار وضع التعديل");
        }
    }
    

    @FXML
    public void Delete_2_son_handler(ActionEvent event) {
        
        if(Deletestate_2.isSelected())
        {
         String se = null;

        if (Type_2_son.isSelected()) {
            se = "Son";
        } else if (Type_2_m.isSelected()) {
            se = "M";
        } else if (Type_2_son.isSelected() && Type_2_m.isSelected()) {
            setErrorAlert(Type_2_m);
            setErrorAlert(Type_2_son);
            State_tab_2.setText("اختر واحد فقط");
            return;
        } else {
            setErrorAlert(Type_2_m);
            setErrorAlert(Type_2_son);
            State_tab_2.setText("الرجاء اختيار النوع");
            return;
        }

        String query2 = "delete from cases where form_id=" + Integer.parseInt(Formid_1.getText().trim()) + "  and sector="
                + Integer.parseInt(Sectornum_1.getText()) + " and person_name='"
                + Fname_2_son.getText().trim() + "'" + " and type='" + se + "'";

        String query3 = "delete from  person where form_id=" + Integer.parseInt(Formid_1.getText().trim()) + "  and sector="
                + Integer.parseInt(Sectornum_1.getText()) + " and fname='"
                + Fname_2_son.getText().trim() + "'";
        try (Statement st = conn_local.createStatement()) {
            st.executeUpdate(query2);

            st.executeUpdate(query3);

        } catch (SQLException e) {
            Logger.getLogger(UpdateFormController.class.getName()).log(Level.SEVERE, null, e);
        }
        show_dialog("تم الحذف");
        show_table2();
        show_table3();
        Fname_2_son.setText("");
        Lname_2_son.setText("");
        Bdate_2_son.setValue(LocalDate.of(1990, Month.JANUARY, 01));
        }else
        {
            show_dialog("الرجاء اختيار وضع الحذف");
        }
        
    }
    
    
    @FXML
    public void Male_2_son_handler()
    {
        Female_2_son.setSelected(false);
    }
    
    
    
    @FXML
    public void Female_2_son_handler()
    {
        Male_2_son.setSelected(false);
    }
    
    
    
    @FXML
    public void Type_2_son_handler()
    {
        Type_2_m.setSelected(false);
        Job_2_son.setVisible(true);
        Job_2_son_label.setVisible(true);
    }
    
    
    @FXML
    public void Type_2_m_handler()
    {
        Type_2_son.setSelected(false);
        Job_2_son.setVisible(false);
        Job_2_son_label.setVisible(false);
    }
    

    @FXML
    public void setErrorAlert(Control c) {
        c.setStyle("-fx-border-color: red ; -fx-border-width: 2px ;");
    }

    @FXML
    public void setNormalAlert(Control c) {
        c.setStyle("-fx-border-color: blue ; -fx-border-width: 0px ;");
    }
    
    
@FXML
    public void House_type_Handler(ActionEvent event) {
        String s = "إيجار";
        if (House_type_1.getValue().toString().trim().equals(s)) 
        {
            Rent_1.setDisable(false);
            
        }
        else 
        {
            Rent_1.setDisable(true);
        }
    }   
    
    

    public void show(TableView myTable, String Query) {
        myTable.getItems().clear();
        myTable.getColumns().clear();

        String selectSt = Query;
        data = FXCollections.observableArrayList();
        String connectionUrl = "jdbc:sqlserver://localhost:1434;"
                + "databaseName=sanad_programm;user=wasbas;password=P@ssw0rd;";

        try (Statement stmt = conn_local.createStatement()) {

            ResultSet rs = stmt.executeQuery(selectSt);

            /**
             * ********************************
             * TABLE COLUMN ADDED DYNAMICALLY *
                 *********************************
             */
            for (int i = 0; i < rs.getMetaData().getColumnCount(); i++) {
                //We are using non property style for making dynamic table
                final int j = i;
                //set column name
                String columnName = rs.getMetaData().getColumnName(i + 1);
                columnName = ClassesNames.getArabicCol(columnName);
                TableColumn col = new TableColumn(columnName);
                col.setCellValueFactory(
                        new Callback<TableColumn.CellDataFeatures<ObservableList, String>, ObservableValue<String>>() {
                            public ObservableValue<String> call(TableColumn.CellDataFeatures<ObservableList, String> param) {
                                //set every cell value
                                if (param.getValue().get(j) == null) {
                                    return new SimpleStringProperty(" ");
                                } else {
                                    return new SimpleStringProperty(param.getValue().get(j).toString());
                                }
                            }
                        });

                myTable.getColumns().addAll(col);
                //System.out.println("Column ["+i+"] ");
            }
            /**
             * ******************************
             * Data added to ObservableList *
                 *******************************
             */
            while (rs.next()) {
                //Iterate Row
                ObservableList<String> row = FXCollections.observableArrayList();
                for (int i = 1; i <= rs.getMetaData().getColumnCount(); i++) {
                    //Iterate Column
                    row.add(rs.getString(i));
                }
                //System.out.println("Row [1] added "+row );
                data.add(row);
            }
            //FINALLY ADDED TO TableView
            myTable.setItems(data);
            rs.close();
        } catch (SQLException ex) {
            Logger.getLogger(AddFormController.class.getName()).log(Level.SEVERE, null, ex);

        }

    }

    @FXML
    public void btBackHandler(ActionEvent event) throws IOException {
        Stage stage = (Stage) Add_2_son.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("MainInterface.fxml"));

        Scene scene = new Scene(root);
        scene.getStylesheets().add("/CSS/Color.css");
        stage.setScene(scene);
        root = null;
        scene = null;
        stage.setX(15);
        stage.setY(2);
        stage.setTitle("الواجهة الرئيسية");
        stage.show();
    }
    
    
    
    @FXML
    public void House_type_1_handler()
    {
        
    }

    @FXML
    public void Addstate_2_handler()
    {
       
            Type_2_son.setDisable(false);
            Type_2_m.setDisable(false);
            Fname_2_son.setDisable(false);
            Deletestate_2.setSelected(false);
            Editstate_2.setSelected(false);
    }
    
    
    
    @FXML 
    public void Deletestate_2_handler()
    {
            Type_2_son.setDisable(true);
            Type_2_m.setDisable(true);
            Fname_2_son.setDisable(true);
            Editstate_2.setSelected(false);
            Addstate_2.setSelected(false);
    }
    
    
    @FXML
    public void Editstate_2_handler()
    {
      
            Type_2_son.setDisable(true);
            Type_2_m.setDisable(true);
            Fname_2_son.setDisable(true);
   
           Deletestate_2.setSelected(false);
           Addstate_2.setSelected(false);
           //State_tab_2.setText("الرجاء اختيار حالة واحدة فقط");
       
    }
    
    
    
     @FXML
    public void show_vol_name()
    {
        String query = "select * from vol order by volunteer_name ";
        try (Statement st = conn_local.createStatement()) {
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
                Vol_cre_name.getItems().add(rs.getString(1));
            }
        } catch (SQLException ex) {
            Logger.getLogger(UpdateFormController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    
    
    @FXML
    public void showcse_name()
    {
        String query="select * from cases_name order by cases_name ";
                try (Statement st = conn_local.createStatement()) {
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
                Gstate_tab_3.getItems().add(rs.getString(1));
            }
        } catch (SQLException ex) {
            Logger.getLogger(UpdateFormController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    
    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        //Operation.create_connection();
        Sectornum_1.setText(String.valueOf(ClassesNames.sectorCode));
        Sectornum_1.setDisable(true);
        show_vol_name();
        Rent_1.setDisable(true);
        Bdate_2_son.setValue(LocalDate.of(1990, Month.JANUARY, 01));
        Adding_state_tab_3.setValue(LocalDate.now());
        Type_2_son.setDisable(true);
        Type_2_m.setDisable(true);
        Tab_1.setDisable(true);
        Tab_2.setDisable(true);
        Tab_3.setDisable(true);
        Type_2_son.isSelected();
        
        String query3="select * from careers";
        ArrayList<String> names=new ArrayList<String>();
        try {
            //add prediction to jobs textfeilds
            Statement st=Operation.conn_local.createStatement();
            ResultSet rs=st.executeQuery(query3);
            while(rs.next())
                {
                    names.add(rs.getString(1));
                }
            
        } catch (SQLException ex) {
            Logger.getLogger(AddFormController.class.getName()).log(Level.SEVERE, null, ex);
        }
        TextFields.bindAutoCompletion(Fcurr_job_1, names);
        TextFields.bindAutoCompletion(Fprev_job_1, names);
        TextFields.bindAutoCompletion(Mcurr_job_1, names);
        TextFields.bindAutoCompletion(Mprev_job_1, names);
        
        
        Job_2_son.getItems().addAll("طالب", "لا يعمل", "أعمال حرة","موظف","مسافر");
        House_type_1.getItems().addAll("خيمة","ملك", "إعارة", "إيجار", "إيواء");
        Aid_type_1.getItems().addAll("بدون", "بر", "هلال", "شباب الخير", "عون", "آخر");
        Fin_state_1.getItems().addAll("سيئ جدا", "سيئ", "مقبول", "جيد", "ممتاز");
        Vol_Eval.getItems().addAll("0","5","10","15","20","25","30","35","40","45"
                ,"50","55","60","65","70","75","80","85","90","95","100");
        //State_tab_3.setPromptText("الحالات الخاصة بهذا الشخص");
        showcse_name();
        
        
        
        
        Table_tab_2.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if (event.isPrimaryButtonDown() && event.getClickCount() == 1) {
                    Node node = ((Node) event.getTarget()).getParent();
                    TableRow row = null;
                    if (node instanceof TableRow) {
                        row = (TableRow) node;
                    } else {
                        // clicking on text part
                        row = (TableRow) node.getParent();
                    }

                    if (row != null && row.getItem() != null) {
                        try {
                            Male_2_son.setSelected(false);
                            Female_2_son.setSelected(false);
                            Type_2_son.setSelected(false);
                            Type_2_m.setSelected(false);
                            //Job_2_son.setVisible(false);
                            //Job_2_son_label.setVisible(false);
                            
                            //String s = row.getItem().toString().split(",")[0].substring(1).trim();
                            //     System.out.println(row.getItem().toString().split(",")[0].substring(1).trim());
                            //      System.out.println(row.getItem().toString());

                            //get teacher by id
                            //ED-teacher
                            //settext(getValue)
                            Fname_2_son.setText(row.getItem().toString().split(",")[0].substring(1).trim());
                            Lname_2_son.setText(row.getItem().toString().split(",")[1].substring(1).trim());
                            String sex = row.getItem().toString().split(",")[2].substring(1).trim();
                            if (sex.trim().equals("Male")) {
                                Male_2_son.setSelected(true);
                            } else {
                                Female_2_son.setSelected(true);
                            }
                            String type = row.getItem().toString().split(",")[3].substring(1).trim();
                            if (type.trim().equals("Son")) {
                                Type_2_son.setSelected(true);
                                //System.out.println("aaaaaaaaaaaaaaaaaa");
                                Job_2_son_label.setVisible(true);
                                Job_2_son.setVisible(true);
                                Job_2_son.setValue(row.getItem().toString().split(",")[4].substring(1).trim());
                                
                            } else {
                                Type_2_m.setSelected(true);
                                //System.out.println("bbbbbbbbbbbbbbbb");
                            }
                            
                            String date = row.getItem().toString().split(",")[5].substring(1).trim();
                            String[] d = date.split("]");
                            System.out.println(d[0]);
                            Bdate_2_son.setValue(LocalDate.parse(d[0]));
                           // Type_tab_3.setText(str1[0]);
                            // motherName_et2.setText(row.getItem().toString().split(",")[4].split("]")[0].substring(1).trim());
                        } catch (Exception ex) {
                            ex.printStackTrace();
                            //System.out.println("nothing to display");
                        }

                    }
                }

            }
        });

        Table_tab_3.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if (event.isPrimaryButtonDown() && event.getClickCount() == 1) {
                    Node node = ((Node) event.getTarget()).getParent();
                    TableRow row = null;
                    if (node instanceof TableRow) {
                        row = (TableRow) node;
                    } else {
                        // clicking on text part
                        row = (TableRow) node.getParent();
                    }

                    if (row != null && row.getItem() != null) {
                        try {
                            State_tab_3.getItems().clear();
                            //String s = row.getItem().toString().split(",")[0].substring(1).trim();
                            //     System.out.println(row.getItem().toString().split(",")[0].substring(1).trim());
                            //      System.out.println(row.getItem().toString());

                            //get teacher by id
                            //ED-teacher
                            //settext(getValue)
                            String str = row.getItem().toString().split(",")[1].substring(1).trim();
                            String[] str1 = str.split("]");
                            type=str1[0];
                            Person_tab_3.setText(row.getItem().toString().split(",")[0].substring(1).trim());

                            Type_tab_3_1.setText(str1[0]);
                            // motherName_et2.setText(row.getItem().toString().split(",")[4].split("]")[0].substring(1).trim());
                        } catch (Exception ex) {
                            ex.printStackTrace();
                            //System.out.println("nothing to display");
                        }

                    }
                }

            }
        });

    }

}
