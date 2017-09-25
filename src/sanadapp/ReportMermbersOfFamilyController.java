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
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import javafx.util.Callback;

/**
 * FXML Controller class
 *
 * @author Bassam
 */
public class ReportMermbersOfFamilyController implements Initializable {

    @FXML
    private Button bt_search;

    @FXML
    private Button bt_print;

    @FXML
    private TableView<?> table_result;

    @FXML
    private Button bt_back;

    @FXML
    private TextField et_search;
    @FXML
    private Label lb_totalnum;
    
    Connection connection = null;
    private ObservableList<ObservableList> data;

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
        String search = et_search.getText();
        if (!search.isEmpty()) {
            try {
                int num = Integer.parseInt(search);
                query = "select f.form_id ,f.sector_num,fname [اسم الأب],lname,f.mobile,f.phone,f.current_city,f.current_suberb from person per "
                            + " join form f on per.form_id = f.form_id and per.sector = f.sector_num "
                            + " where f.form_id = any(select distinct form_id  from person p "
                            + "where " + num + "=(select count(form_id) "
                            + "from person group by form_id,sector having form_id= p.form_id and sector =p.sector)) "
                            + " and per.type like 'father' ";
                show(table_result, query);

                if (table_result.getItems().size() == 0) {
                    //setErrorAlert(etSearch);
                }
            } catch (NumberFormatException e) {
                //handle this exception 
                // by setErrorAlert(etSearch);
            }

        }
    }

    @FXML
    private void etSearchHanlder(KeyEvent event) {
        if (event.getCode() == KeyCode.ENTER) {
            String search = et_search.getText();
            if (!search.isEmpty()) {
                try {
                    int num = Integer.parseInt(search);
                    query = "select f.form_id,f.sector_num,fname [اسم الأب],lname,f.mobile,f.phone,f.current_city,f.current_suberb from person per "
                            + " join form f on per.form_id = f.form_id and per.sector = f.sector_num "
                            + " where f.form_id = any(select distinct form_id  from person p "
                            + "where " + num + "=(select count(form_id) "
                            + "from person group by form_id,sector having form_id= p.form_id and sector =p.sector)) "
                            + " and per.type like 'father' ";
                    show(table_result, query);

                    if (table_result.getItems().size() == 0) {
                        //setErrorAlert(etSearch);
                    }
                } catch (NumberFormatException e) {
                //handle this exception 
                    e.printStackTrace();
                    // by setErrorAlert(etSearch);
                }

            }

        }
    }
    
    @FXML
    public void btPrintHandler(ActionEvent event) throws IOException {
        try {
            if(query!=null)
                Excel.print_as_excel(query,"تقرير حسب عدد أفراد العائلة");
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
                lb_totalnum.setText("عدد الاستمارات المطابقة :  "+Integer.toString(count));
                rs.close();
            } catch (Exception e) {
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
        connection = Operation.conn_local;
    }

}
