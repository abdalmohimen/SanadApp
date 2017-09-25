/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sanadapp;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
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
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.util.Callback;
import static sanadapp.Operation.conn_local;

/**
 * FXML Controller class
 *
 * @author Bassam
 */
public class CasesController implements Initializable {

    @FXML
    private CheckBox cb_haveServ;

    @FXML
    private Button bt_back;

    @FXML
    private TableView<?> table_cases;

    @FXML
    private ComboBox<String> cb_servCenter;

    @FXML
    private TextField et_counts;

    @FXML
    private DatePicker dp_date;

    @FXML
    private TextField et_formId;

    @FXML
    private TextField et_notes;

    @FXML
    private TextField et_sectorId;

    @FXML
    private Label lb_case, lb_volName;

    @FXML
    private Button bt_update;

    @FXML
    private Label lb_name;
    @FXML
    private CheckBox cb_houseIsOk;
    @FXML
    private TextField et_Housenotes;

    Connection connection = null;
    private ObservableList<ObservableList> data;
    String formId = null, sectorId = null;

    //upadte button hadler
    @FXML
    public void updateBtHandler(ActionEvent event) {
        String query = "update cases set num_of_service= ? , notes = ? ,stating_center = ? ,"
                + " date  = ?  ,is_ok = ? "
                + "where form_id = ? and sector = ? and person_name =? and status = ? ";
        try {
            PreparedStatement pr = connection.prepareStatement(query);
            pr.setString(1, et_counts.getText());
            pr.setString(2, et_notes.getText());
            pr.setString(3, cb_servCenter.getValue());
            //System.out.println("..."+dp_date.getValue()+"...");
            if (dp_date.getValue() != null && !dp_date.getValue().toString().equals("null")) {
                pr.setString(4, dp_date.getValue().toString());
            }
            //pr.setString(5, et_volunterName.getValue().toString().trim());
            pr.setString(6, formId);
            pr.setString(7, sectorId);
            pr.setString(8, lb_name.getText());
            pr.setString(9, lb_case.getText());
            if (cb_haveServ.isSelected()) {
                pr.setString(5, "yes");
            } else {
                pr.setString(5, "no");
            }

            pr.executeUpdate();
            Dialog d2 = new Alert(Alert.AlertType.INFORMATION);
            d2.setContentText("تم تعديل الحالة بنجاح");
            d2.show();
            if ((!formId.equals("null")) && (!sectorId.equals("null"))) {
                String q = " select * from cases where "
                        + "form_id = " + formId
                        + " and sector = " + sectorId;
                show(table_cases, q);

                //deactivate the button after updete the date 
                bt_update.setDisable(true);
            }
        } catch (SQLException ex) {
            // should handle some error
            Dialog d2 = new Alert(Alert.AlertType.ERROR);
            d2.setContentText(ex.getMessage());
            d2.show();
        }

    }

    //update house state in form table
    @FXML
    public void HouseBtHandler(ActionEvent event) {

        String query = "update form set house_state=? ,house_state_note=? "
                + "where form_id=? and sector_num=?";
        try {
            PreparedStatement pr = connection.prepareStatement(query);
            pr.setString(2, et_Housenotes.getText());
            pr.setString(3, formId);
            pr.setString(4, sectorId);

            if (cb_houseIsOk.isSelected()) {
                pr.setString(1, "yes");
            } else {
                pr.setString(1, "no");
            }

            pr.executeUpdate();
            Dialog d2 = new Alert(Alert.AlertType.INFORMATION);
            d2.setContentText("تم تعديل كشف المنزل بنجاح");
            d2.show();

        } catch (SQLException ex) {
            // should handle some error
            Dialog d2 = new Alert(Alert.AlertType.ERROR);
            d2.setContentText(ex.getMessage());
            d2.show();
            ex.printStackTrace();
        }
    }

    //getData  button hadler
    @FXML
    public void getDataBtHandler(ActionEvent event) {
        formId = et_formId.getText().trim();
        sectorId = et_sectorId.getText().trim();
        //System.out.println("..."+formId+"...");

        if ((!"".equals(formId)) && (!"".equals(sectorId))) {
            String q = " select * from cases where "
                    + "form_id = " + formId
                    + " and sector = " + sectorId;
            show(table_cases, q);

            //display volunter name
            String q2 = "select vol_creator_name,house_state,house_state_note from form "
                    + " where form_id= " + formId + " and sector_num= " + sectorId;
            Statement stmt;
            try {
                stmt = connection.createStatement();
                ResultSet rs = stmt.executeQuery(q2);
                rs.next();
                lb_volName.setText(rs.getString(1));
                if (rs.getString(3) != null && !rs.getString(3).equals("null")) {
                    et_Housenotes.setText(rs.getString(3));
                } else {
                    et_Housenotes.clear();
                }
                System.out.println("*******************");
                System.out.println(rs.getString(2));
                System.out.println("*******************");
                if (rs.getString(2) != null && rs.getString(2).trim().equals("yes")) {
                    cb_houseIsOk.setSelected(true);
                } else {
                    cb_houseIsOk.setSelected(false);
                }

            } catch (SQLException ex) {
                Logger.getLogger(CasesController.class.getName()).log(Level.SEVERE, null, ex);
            }

        } else {
            // error handling
            System.err.println("WTF");
        }
    }

    //show cases' table
    public void show(TableView myTable, String Query) {
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
                }
                //FINALLY ADDED TO TableView
                myTable.setItems(data);
                rs.close();
            } catch (Exception e) {
            }
        } else {
            //connection not established correctly
        }

    }

    //
    //handle Table click event
    //
    @FXML
    private void casesTableHandler(MouseEvent event) {
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
                    //ger data from table
                    formId = row.getItem().toString().split(",")[0].substring(1).trim();
                    sectorId = row.getItem().toString().split(",")[1].substring(1).trim();
                    String fname = row.getItem().toString().split(",")[2].substring(1).trim();
                    String status = row.getItem().toString().split(",")[4].substring(1).trim();
                    String servCenter = row.getItem().toString().split(",")[5].substring(1).trim();
                    String date = row.getItem().toString().split(",")[6].substring(1).trim();
                    String count = row.getItem().toString().split(",")[7].substring(1).trim();
                    //String volName =  row.getItem().toString().split(",")[8].substring(1).trim();
                    String notes = row.getItem().toString().split(",")[8].substring(1).trim();
                    String isOk = row.getItem().toString().split(",")[10].subSequence(1, 4).toString().trim();
                    System.out.println("..." + isOk + "...");
                    //fill fields with data
                    et_formId.setText(formId);
                    et_sectorId.setText(sectorId);
                    if (!fname.equals("null")) {
                        lb_name.setText(fname);
                    }
                    if (!status.equals("null")) {
                        lb_case.setText(status);
                    }
                    if (date != null && !date.equals("null")) {
                        dp_date.setValue(LocalDate.parse(date));
                    } else {
                        dp_date.setValue(LocalDate.now());
                    }
                    if (!count.equals("null")) {
                        et_counts.setText(String.valueOf(Integer.parseInt(count) + 1));
                    } else {
                        et_counts.setText(String.valueOf(1));
                    }

                    if (isOk.equals("yes")) {
                        cb_haveServ.setSelected(true);
                    } else {
                        cb_haveServ.setSelected(false);
                    }

                    if (!notes.equals("null")) {
                        et_notes.setText(notes);
                    } else {
                        et_notes.clear();
                    }
                    if (!servCenter.equals("null")) {
                        cb_servCenter.setValue(servCenter);
                    }

                    //active the button after select from the table
                    bt_update.setDisable(false);

//                    selectedCoureID = row.getItem().toString().split(",")[0].substring(1).trim();
//                    String courseType = row.getItem().toString().split(",")[1].substring(1).trim();
//                    course_label.setText("الرقم : "+selectedCoureID + "... النوع : "+courseType);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        }
    }

    public void show_stating_center() {
        String query = "select * from stating_center order by state ";
        try (Statement st = conn_local.createStatement()) {
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
                cb_servCenter.getItems().add(rs.getString(1));
            }
        } catch (SQLException ex) {
            Logger.getLogger(UpdateFormController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    public void btBackHandler(ActionEvent event) throws IOException {
        Stage stage = (Stage) bt_back.getScene().getWindow();
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

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //this line should called once whene app start
        //Operation.create_connection();
        et_sectorId.setText(String.valueOf(ClassesNames.sectorCode));
        et_sectorId.setDisable(true);

        connection = Operation.conn_local;

        String query = "select * from cases ";
        //load data to cases' table
        //show(table_cases,query);
        show_stating_center();
        //cb_servCenter.getItems().addAll("بدون", "بر", "هلال", "شباب الخير", "عون", "آخر");

        //dp_date.setValue(LocalDate.now());
    }

}
