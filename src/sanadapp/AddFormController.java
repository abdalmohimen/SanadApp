/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sanadapp;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
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
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Control;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.SelectionMode;
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
 * @author Bassam
 */
public class AddFormController implements Initializable {

    @FXML
    Button Make_Form, Addson_2, Add_m_2, Addstate_tab_3, Showtable_tab_3;

    @FXML
    TextField Formid_1, Sectornum_1, Ffname_1, Flname_1, Fcurrjob_1, Fprevjob_1, Mfname_1,
            Mlname_1, Mcurrjob_1, Mprevjob_1, Curr_city_1, Curr_sub_1, Prev_city_1, Prev_sub_1,
            Phone_1, Mobile_1, Fname_2_son, lname_2_son, Fname_2_m, Lname_2_m, Rent_1, Notes_tab_2;

    @FXML
    ComboBox House_type_1, Aid_type_1, Fin_sit_1, Job_2_son, State_tab_3, 
            Vol_creater_name, State_tab_31,Vol_Eval;
    @FXML
    TextArea Vol_evaluate;

    @FXML
    DatePicker Fbdate_1, Mbdate_1, Bdate_2_son, Bdate_2_m, Form_add_date, Addingstatedate_tab_3, Statingdate_tab_3;

    @FXML
    CheckBox Social_1, Male_2_son, Female_2_son, Male_2_m, Female_2_m;

    @FXML
    Tab Tab_1, Tab_2, Tab_3;

    @FXML
    Label State_tab_2, Person_tab_3, Type_tab_3;

    @FXML
    private TableView<?> Table_tab_2, Persontable_tab_3, Casestable_tab_3;

    private ObservableList<ObservableList> data;

    @FXML
    public void makeform_handler(ActionEvent event) {

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
            String s = "إيجار";
            String rent_value = "";
            if (House_type_1.getValue() == null) {
            } else {
                if (House_type_1.getValue().toString().trim().equals(s)) {
                    rent_value = Rent_1.getText();

                }
            }
            
            String str_house = "";
            String str_aid = "";
            String str_fin = "";
            if (House_type_1.getValue() == null) {

            } else {
                str_house = House_type_1.getValue().toString();
            }
            if (Aid_type_1.getValue() == null) {
            } else {
                str_aid = Aid_type_1.getValue().toString();
            }
            if (Fin_sit_1.getValue() == null) {
            } else {
                str_fin = Fin_sit_1.getValue().toString();

            }

            if (Ffname_1.getText().isEmpty() && Mfname_1.getText().isEmpty()) {
                setErrorAlert(Ffname_1);
                setErrorAlert(Mfname_1);
                State_tab_2.setText("لا يمكن الادخال بدون اسم الأب والأم");
                return;
            } else if (Ffname_1.getText().isEmpty()) {
                setErrorAlert(Ffname_1);
                //setErrorAlert(Mfname_1);
                State_tab_2.setText(" لا يمكن الادخال بدون اسم الأب والأم");
                return;

            } else if (Mfname_1.getText().isEmpty()) {
                //setErrorAlert(Ffname_1);
                setErrorAlert(Mfname_1);
                State_tab_2.setText("لا يمكن الادخال بدون اسم الأب والأم");
                return;
            }else if(Vol_Eval.getValue() == null)
            {
                 setErrorAlert(Vol_Eval);
                 State_tab_2.setText("الرجاء إدخال التقييم");
            }else {
                try {
                    setNormalAlert(Ffname_1);
                    setNormalAlert(Mfname_1);
                    AddForm_Data_1 ad;
                    ad = new AddForm_Data_1(
                            Integer.parseInt(Formid_1.getText()),
                            Integer.parseInt(Sectornum_1.getText()),
                            null_tester(Ffname_1.getText()),
                            null_tester(Flname_1.getText()),
                            null_tester(Fcurrjob_1.getText()),
                            null_tester(Fprevjob_1.getText()),
                            null_tester(Fbdate_1.getValue().toString()),
                            null_tester(Mfname_1.getText()),
                            null_tester(Mlname_1.getText()),
                            null_tester(Mcurrjob_1.getText()),
                            null_tester(Mprevjob_1.getText()),
                            null_tester(Mbdate_1.getValue().toString()),
                            null_tester(Curr_city_1.getText()),
                            null_tester(Curr_sub_1.getText()),
                            null_tester(Prev_city_1.getText()),
                            null_tester(Prev_sub_1.getText()),
                            null_tester(Phone_1.getText()),
                            null_tester(Mobile_1.getText()),
                            null_tester(str_house),
                            null_tester(str_aid),
                            Social_1.isSelected(),
                            null_tester(str_fin),
                            rent_value,
                            null_tester(Form_add_date.getValue().toString()),
                            null_tester(Vol_creater_name.getValue().toString().trim()),
                            null_tester(Vol_evaluate.getText().toString().trim()),
                            Integer.parseInt(Vol_Eval.getValue().toString())
                        );
                        Operation.firstpage_insert(ad);
                        Formid_1.setDisable(true);
                        Sectornum_1.setDisable(true);
                        Tab_2.setDisable(false);
                        Tab_3.setDisable(false);
                        Make_Form.setDisable(true);
                        lname_2_son.setText(null_tester(Flname_1.getText()));

                        String query = "select person.fname,person.lname,person.type "
                                + " from person where person.form_id=" + Formid_1.getText() + "  and person.sector=" + Sectornum_1.getText();
                        show(Persontable_tab_3, query);
                        Addingstatedate_tab_3.setValue(LocalDate.parse(Form_add_date.getValue().toString().trim()));
                        Statingdate_tab_3.setValue(LocalDate.parse(Form_add_date.getValue().toString().trim()));
                } catch (NullPointerException e) {
                    Dialog d = new Alert(Alert.AlertType.INFORMATION);
                    d.setContentText("الرجاء اختيار اسم المتطوع");
                    d.show();
                } catch (SQLException ex) {
                    
                    Logger.getLogger(AddFormController.class.getName()).log(Level.SEVERE, null, ex);
                } 
            }

        }

    }

    @FXML
    public void add_son_handler(ActionEvent event) {
        String query = "select fname,lname,sex   from person where person.form_id=" + Integer.parseInt(Formid_1.getText())
                + "  and person.sector=" + Integer.parseInt(Sectornum_1.getText())
                + " and person.type in ('Son','M')";

        String se = "Son";
        if (Fname_2_m.getText() == null) {
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
                String str_job = "";
                if (Job_2_son.getValue() == null) {
                } else {
                    str_job = Job_2_son.getValue().toString().trim();
                }
                AddForm_Data_2 ad = new AddForm_Data_2(Integer.parseInt(Formid_1.getText()),
                        Integer.parseInt(Sectornum_1.getText()),
                        Fname_2_son.getText(),
                        lname_2_son.getText(),
                        s,
                        Bdate_2_son.getValue().toString(),
                        str_job,
                        se);

                Operation.secondpage_insert(ad);
                show(Table_tab_2, query);
            } else if (Female_2_son.isSelected()) {
                s = "Female";
                //se="Son";
                String str_job = "";
                if (Job_2_son.getValue() == null) {
                } else {
                    str_job = Job_2_son.getValue().toString().trim();
                }
                AddForm_Data_2 ad = new AddForm_Data_2(Integer.parseInt(Formid_1.getText()),
                        Integer.parseInt(Sectornum_1.getText()),
                        Fname_2_son.getText(),
                        lname_2_son.getText(),
                        s,
                        Bdate_2_son.getValue().toString(),
                        str_job,
                        se);

                Operation.secondpage_insert(ad);
                show(Table_tab_2, query);
            } else {
                setErrorAlert(Male_2_son);
                setErrorAlert(Female_2_son);
                State_tab_2.setText("رجاء اختر الجنس");
            }
        }
        query = "select person.fname,person.lname,person.type "
                + " from person where person.form_id=" + Formid_1.getText() + "  and person.sector=" + Sectornum_1.getText();
        show(Persontable_tab_3, query);
    }

    @FXML
    public void add_m_handler(ActionEvent event) {

        String query = "select fname,lname,sex   from person where person.form_id=" + Integer.parseInt(Formid_1.getText())
                + "  and person.sector=" + Integer.parseInt(Sectornum_1.getText())
                + " and person.type in ('Son','M')";

        String se = "M";
        String j = "";
        if (Fname_2_m.getText() == null) {
            setErrorAlert(Fname_2_m);
            setErrorAlert(State_tab_2);
            State_tab_2.setText("الرجاء إدخال اسم المضاف");
        } else {
            String s = "";
            if (Male_2_m.isSelected() && Female_2_m.isSelected()) {
                setErrorAlert(Male_2_m);
                setErrorAlert(Female_2_m);

            } else if (Male_2_m.isSelected()) {
                s = "Male";
                AddForm_Data_2 ad = new AddForm_Data_2(Integer.parseInt(Formid_1.getText()),
                        Integer.parseInt(Sectornum_1.getText()),
                        Fname_2_m.getText(),
                        Lname_2_m.getText(),
                        s,
                        Bdate_2_m.getValue().toString(),
                        j,
                        se);
                Operation.secondpage_insert(ad);
                show(Table_tab_2, query);
            } else if (Female_2_m.isSelected()) {
                s = "Female";
                AddForm_Data_2 ad = new AddForm_Data_2(Integer.parseInt(Formid_1.getText()),
                        Integer.parseInt(Sectornum_1.getText()),
                        Fname_2_m.getText(),
                        Lname_2_m.getText(),
                        s,
                        Bdate_2_m.getValue().toString(),
                        j,
                        se);

                Operation.secondpage_insert(ad);
                show(Table_tab_2, query);
            } else {
                setErrorAlert(Male_2_m);
                setErrorAlert(Female_2_m);
                State_tab_2.setText("رجاء اختر الجنس");
            }
        }
        query = "select person.fname,person.lname,person.type "
                + " from person where person.form_id=" + Formid_1.getText() + "  and person.sector=" + Sectornum_1.getText();
        show(Persontable_tab_3, query);
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
    public void Male_2_son_handler() {
        Female_2_son.setSelected(false);
    }

    @FXML
    public void Female_2_son_handler() {
        Male_2_son.setSelected(false);
    }

    @FXML
    public void Male_2_m_handler() {
        Female_2_m.setSelected(false);

    }

    @FXML
    public void Female_2_m_handler() {
        Male_2_m.setSelected(false);
    }

    @FXML
    public void Formid_1_handler(ActionEvent event) {
        setNormalAlert(Formid_1);
        System.out.println("jjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjj");
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
    private void getNormalInput(MouseEvent event) {
        Control c = (Control) event.getTarget();
        c.setStyle("-fx-border-color: blue ; -fx-border-width: 0px ;");
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
             * TABLE COLUMN ADDED DYNAMICALLY * ********************************
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
             * Data added to ObservableList * ******************************
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
            Dialog d3 = new Alert(Alert.AlertType.ERROR);
            d3.setContentText(ex.getMessage());
            d3.show();

        }

    }

    @FXML
    public void Showtable_tab_3_handler(ActionEvent event) {
        String query = "select person.fname,person.lname,person.type "
                + " from person where person.form_id=" + Formid_1.getText() + "  and person.sector=" + Sectornum_1.getText();
        show(Persontable_tab_3, query);

    }

    @FXML
    public void Addstate_tab_3_handler(ActionEvent event) {
        if (State_tab_3.getValue() == null) {
            setErrorAlert(State_tab_3);
            setErrorAlert(State_tab_2);
            State_tab_2.setText("الرجاء اختيار الحالة لادخالها");
            return;
        } else {
            String state = "";
            if (State_tab_31.getValue() == null) {
            } else {
                state = State_tab_31.getValue().toString().trim();
            }
            AddForm_Data_3 add = new AddForm_Data_3(Integer.parseInt(Formid_1.getText()),
                    Integer.parseInt(Sectornum_1.getText()),
                    Person_tab_3.getText(),
                    Type_tab_3.getText(),
                    State_tab_3.getValue().toString().trim(),
                    Notes_tab_2.getText(),
                    Statingdate_tab_3.getValue().toString().trim(),
                    Addingstatedate_tab_3.getValue().toString().trim(),
                    state
            );

            Operation.thirddpage_insert(add);
            State_tab_31.setValue(null);
            State_tab_3.setValue(null);
            String ss = "select person_name,status,stating_center,case_note  from cases where form_id="
                    + Integer.parseInt(Formid_1.getText()) + " and sector=" + Integer.parseInt(Sectornum_1.getText());
            show(Casestable_tab_3, ss);

        }

    }

    @FXML
    public void house_type_handler(ActionEvent event) {
        String s = "إيجار";
        if (House_type_1.getValue().toString().trim().equals(s)) {
            Rent_1.setDisable(false);
        } else {
            Rent_1.setDisable(true);
        }
    }

    @FXML
    public void vol_name_handler(MouseEvent event) {
        if (Vol_creater_name.getItems().isEmpty()) {
            show_vol_name();
            //System.out.print(event.getSource().toString());
        }
    }

    // reset cotroller to enter new form
    @FXML
    public void btResetHandler(ActionEvent event) throws IOException {

        Make_Form.setDisable(false);
        //clear TextField
        Formid_1.setDisable(false);
        int id = Operation.get_id_value() + 1;
        Formid_1.setPromptText(String.valueOf(id));
        Ffname_1.setText("لا يوجد");
        Flname_1.clear();
        Fcurrjob_1.clear();
        Fprevjob_1.clear();
        Mfname_1.setText("لا يوجد");
        Mlname_1.clear();
        Mcurrjob_1.clear();
        Mprevjob_1.clear();
        Curr_city_1.clear();
        Curr_sub_1.clear();
        Prev_city_1.clear();
        Prev_sub_1.clear();
        Phone_1.clear();
        Mobile_1.clear();
        Fname_2_son.clear();
        lname_2_son.clear();
        Fname_2_m.clear();
        Lname_2_m.clear();
        Rent_1.clear();
        Notes_tab_2.clear();

        //clear combobox
        House_type_1.setValue("");
        Aid_type_1.setValue("");
        Fin_sit_1.setValue("");
        Job_2_son.setValue("");
        State_tab_3.setValue("");

        //clear datePicker and tabes
        Rent_1.setDisable(true);
        Tab_2.setDisable(true);
        Tab_3.setDisable(true);
        Form_add_date.setValue(LocalDate.now());
        Bdate_2_son.setValue(LocalDate.of(1990, Month.JANUARY, 01));
        Bdate_2_m.setValue(LocalDate.of(1990, Month.JANUARY, 01));
        Fbdate_1.setValue(LocalDate.of(1970, Month.JANUARY, 01));
        Mbdate_1.setValue(LocalDate.of(1970, Month.JANUARY, 01));

        //clear checkBox
        Social_1.setSelected(false);
        Male_2_son.setSelected(false);
        Female_2_son.setSelected(false);
        Male_2_m.setSelected(false);
        Female_2_m.setSelected(false);

        Persontable_tab_3.getItems().clear();
        Persontable_tab_3.getColumns().clear();

        Casestable_tab_3.getItems().clear();
        Casestable_tab_3.getColumns().clear();

        Table_tab_2.getItems().clear();
        Table_tab_2.getColumns().clear();

        Vol_evaluate.clear();
        Vol_creater_name.setValue(null);
    }

    @FXML
    public void btBackHandler(ActionEvent event) throws IOException {
        Stage stage = (Stage) Add_m_2.getScene().getWindow();
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

    public void show_vol_name() {
        String query = "select * from vol order by volunteer_name ";
        try (Statement st = conn_local.createStatement()) {
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
                Vol_creater_name.getItems().add(rs.getString(1));
            }
        } catch (SQLException ex) {
            Dialog d = new Alert(Alert.AlertType.ERROR);
            d.setContentText(ex.getMessage());
            d.show();
        }
    }

    public void show_stating_center() {
        String query = "select * from stating_center order by state ";
        try (Statement st = conn_local.createStatement()) {
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
                State_tab_31.getItems().add(rs.getString(1));
            }
        } catch (SQLException ex) {
            Dialog d = new Alert(Alert.AlertType.ERROR);
            d.setContentText(ex.getMessage());
            d.show();
        }
    }

    public void showcse_name() {
        String query = "select * from cases_name order by cases_name ";
        try (Statement st = conn_local.createStatement()) {
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
                State_tab_3.getItems().add(rs.getString(1));
            }
        } catch (SQLException ex) {
            Dialog d = new Alert(Alert.AlertType.ERROR);
            d.setContentText(ex.getMessage());
            d.show();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //create connection
        //Operation.create_connection();
        int id = Operation.get_id_value() + 1;
        //id++;
        //Vol_evaluate.getStylesheets().add("/CSS/textarea.css");
        System.out.println(id);
        Sectornum_1.setText(String.valueOf(ClassesNames.sectorCode));
        Sectornum_1.setDisable(true);
        Formid_1.setPromptText(Integer.toString(id));
        show_stating_center();
        //Why abo naser ??? Whyyyyyy??
        //Social_1.isSelected();
        Rent_1.setDisable(true);
        Tab_2.setDisable(true);
        Tab_3.setDisable(true);
        showcse_name();
        Form_add_date.setValue(LocalDate.now());
        Bdate_2_son.setValue(LocalDate.of(1990, Month.JANUARY, 01));
        Bdate_2_m.setValue(LocalDate.of(1990, Month.JANUARY, 01));
        Fbdate_1.setValue(LocalDate.of(1800, Month.JANUARY, 01));
        Mbdate_1.setValue(LocalDate.of(1800, Month.JANUARY, 01));
        Job_2_son.getItems().addAll("موقوف","طالب", "لا يعمل", "أعمال حرة", "موظف", "مسافر");
        House_type_1.getItems().addAll("خيمة","ملك", "إعارة", "إيجار", "إيواء");
        Vol_Eval.getItems().addAll("0","5","10","15","20","25","30","35","40","45"
                ,"50","55","60","65","70","75","80","85","90","95","100");
        Aid_type_1.getItems().addAll("بدون", "بر", "هلال", "شباب الخير", "عون", "آخر");
        Fin_sit_1.getItems().addAll("سيئ جدا", "سيئ", "مقبول", "جيد", "ممتاز");
        show_vol_name();
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
        TextFields.bindAutoCompletion(Fcurrjob_1, names);
        TextFields.bindAutoCompletion(Fprevjob_1, names);
        TextFields.bindAutoCompletion(Mcurrjob_1, names);
        TextFields.bindAutoCompletion(Mprevjob_1, names);
        
        
        
        //table in tab 3
        Persontable_tab_3.setOnMousePressed(new EventHandler<MouseEvent>() {
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
                            //String s = row.getItem().toString().split(",")[0].substring(1).trim();
                            //     System.out.println(row.getItem().toString().split(",")[0].substring(1).trim());
                            //      System.out.println(row.getItem().toString());

                            //get teacher by id
                            //ED-teacher
                            //settext(getValue)
                            String str = row.getItem().toString().split(",")[2].substring(1).trim();
                            String[] str1 = str.split("]");
                            Person_tab_3.setText(row.getItem().toString().split(",")[0].substring(1).trim());
                            Type_tab_3.setText(str1[0]);
                            // motherName_et2.setText(row.getItem().toString().split(",")[4].split("]")[0].substring(1).trim());
                            State_tab_31.setValue(null);
                            State_tab_3.setValue(null);
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
