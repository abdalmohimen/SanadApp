/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sanadapp;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;
import javafx.util.Callback;
import static sanadapp.Operation.conn_local;

/**
 * FXML Controller class
 *
 * @author Bassam
 */
public class ReportChildAgeController implements Initializable {

    @FXML
    private Button bt_search;

    @FXML
    private Button bt_print;

    @FXML
    private TableView<?> table_result;

    @FXML
    private Button bt_back;

    @FXML
    private DatePicker dp_endDate;

    @FXML
    private DatePicker dp_startDate;

    @FXML
    private Label lb_totalnum;
    @FXML
    private ComboBox cb_Status,cb_currentJob;
    
    Connection connection = null;
    private ObservableList<ObservableList> data;
    
    private String CURRENTJOB_STR="أي عمل حالي";
    private String CASES_STR="كل الحالات";

    String query=null;
    @FXML
    public void btBackHandler(ActionEvent event) throws IOException {
        Stage stage = (Stage) bt_back.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("ReportMainInterface.fxml"));

        Scene scene = new Scene(root);
        scene.getStylesheets().add("/CSS/Color.css");
        stage.setScene(scene);
        root = null;
        scene = null;
        stage.setTitle("واجهة التقارير");
        stage.show();
    }

    @FXML
    public void btSearchHandler(ActionEvent event) throws IOException {
        query = " select p.form_id,p.sector,fname+' '+lname [الاسم],"
                + "DATEDIFF(YEAR,birth_date,GETDATE())[ العمر],birth_date,p.type"
                + ",(select mobile from form f where p.form_id=f.form_id) as mobile"
                + ",(select phone from form f where p.form_id=f.form_id) as phone"
                + ",current_job,c.status  "
                + " from person p join cases c on p.form_id=c.form_id "
                + "and p.sector = c.sector and p.fname = c.person_name "
                + "where ";
        //get number of forms query
        //String q2="select COUNT(*) from form where ";
        
        //check dates
        if (dp_startDate.getValue()==null && dp_endDate.getValue()==null){
            ;
        }else if (dp_endDate.getValue()==null){
            query += " DATEDIFF(DAY,birth_date,'"+dp_startDate.getValue() +"')<=0 ";
            //q2+= " DATEDIFF(DAY,form_add_date,'"+dp_startDate.getValue() +"')<=0 ";
        }else if (dp_startDate.getValue()==null){
            query += " DATEDIFF(DAY,birth_date,'"+dp_endDate.getValue() +"')>=0 ";
            //q2 += " DATEDIFF(DAY,form_add_date,'"+dp_endDate.getValue() +"')>=0 ";
        }else{
             query += " DATEDIFF(DAY,birth_date,'"+dp_startDate.getValue() +"')<=0 ";
             query += " and DATEDIFF(DAY,birth_date,'"+dp_endDate.getValue() +"')>=0 ";
             //q2 += " DATEDIFF(DAY,form_add_date,'"+dp_startDate.getValue() +"')<=0 ";
             //q2 += " and DATEDIFF(DAY,form_add_date,'"+dp_endDate.getValue() +"')>=0 ";
        }
        
        //check case and current job
        if(cb_Status.getValue()!=null 
                &&(!cb_Status.getValue().toString().equals(CASES_STR))){
            query += "and c.status like '"+cb_Status.getValue() +"' ";
        }
        
        if(cb_currentJob.getValue()!=null
                &&(!cb_currentJob.getValue().toString().equals(CURRENTJOB_STR))){
            query += "and current_job like '"+cb_currentJob.getValue() +"' ";
        }    
        
        show(table_result, query);
        
        //get number of forms
//        Statement st;
//        try {
//            st = connection.createStatement();
//            ResultSet rs = st.executeQuery(q2);
//            rs.next();
//            int count = rs.getInt(1);
//            lb_totalnum.setText("عدد الاستمارات  ... "+count);
//        } catch (SQLException ex) {
//            Logger.getLogger(ReportWeeklyServicesController.class.getName()).log(Level.SEVERE, null, ex);
//        }
    }

   
    
    @FXML
    public void btPrintHandler(ActionEvent event) throws IOException {
        try {
            if(query!=null)
                Excel.print_as_excel(query,"الأشخاص حسب العمر");
        } catch (SQLException ex) {
            Logger.getLogger(ReportHomeOrPhoneController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void show(TableView myTable, String Query) {
        //to count the result nums
        int count = 0;
        myTable.getItems().clear();
        myTable.getColumns().clear();

        String selectSt = Query;
        data = FXCollections.observableArrayList();
        if (connection != null) {
            try {
                Statement stmt = connection.createStatement();
                ResultSet rs = stmt.executeQuery(selectSt);
                
                /**
                 * ********************************
                 * TABLE COLUMN ADDED DYNAMICALLY *
                 * ********************************
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
                    count++;
                }
                //FINALLY ADDED TO TableView
                myTable.setItems(data);
                lb_totalnum.setText("العدد الكلي:  "+Integer.toString(count));
                rs.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            //connection not established correctly
        }

    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        //this line should called once whene app start
        //Operation.create_connection();
        dp_endDate.setValue(LocalDate.now());
        //long millisInWeek=7*24*60*60*1000;
        //long currDays = System.currentTimeMillis()/1000/60/60/24;
        dp_startDate.setValue(LocalDate.now().minusDays(7));
        connection = Operation.conn_local;
        show_cases();
        show_currentJob();
    }
    //modify for suitable table name
    public void show_currentJob() {
        String query = "select * from  careers order by name ";
        //add default selection
        cb_currentJob.getItems().add(CURRENTJOB_STR);
        try (Statement st = conn_local.createStatement()) {
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
                cb_currentJob.getItems().add(rs.getString(1));
            }
        } catch (SQLException ex) {
            Dialog d = new Alert(Alert.AlertType.ERROR);
            d.setContentText(ex.getMessage());
            d.show();
        }
    }
    public void show_cases() {
        String query = "select * from cases_name order by cases_name ";
        cb_Status.getItems().add(CASES_STR);
        try (Statement st = conn_local.createStatement()) {
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
                cb_Status.getItems().add(rs.getString(1));
            }
        } catch (SQLException ex) {
            Dialog d = new Alert(Alert.AlertType.ERROR);
            d.setContentText(ex.getMessage());
            d.show();
        }
    }

}
