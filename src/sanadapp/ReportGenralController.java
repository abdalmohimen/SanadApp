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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;
import javafx.util.Callback;

/**
 * FXML Controller class
 *
 * @author Bassam
 */
public class ReportGenralController implements Initializable {

    @FXML
    private Button bt_search;

    @FXML
    private Button bt_print;

    @FXML
    private TableView<?> table_result;

    @FXML
    private Button bt_back;

    @FXML
    private TextArea textArea;
    
    String query=null; 
    Connection connection = null;
    private ObservableList<ObservableList> data;
    
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
        query = textArea.getText();
        boolean isValidQuery = false;
        if(!(query.toLowerCase().contains("update")
                ||query.toLowerCase().contains("delete")
                ||query.toLowerCase().contains("insert")))
            isValidQuery=true;
        else{
            textArea.setText("InValid Query String ... update,delete or insert not allowed");
            query=null;
        }
            
        if(isValidQuery){
            show(table_result, query);
            bt_print.setDisable(false);
            isValidQuery=false;
        }
        
        
    }

   
    
    @FXML
    public void btPrintHandler(ActionEvent event) throws IOException {
        try {
            if(query!=null)
                Excel.print_as_excel(query,"تقرير  عام");
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
                //lb_totalnum.setText("عدد الاستمارات  :  "+Integer.toString(count));
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
        // TODO
        connection = Operation.conn_local;
        bt_print.setDisable(true);
    }    
    
}
