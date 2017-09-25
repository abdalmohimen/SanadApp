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
import javafx.scene.control.DatePicker;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.util.Callback;
import static sanadapp.Operation.conn_local;

/**
 * FXML Controller class
 *
 * @author Bassam
 */
public class ReportComplex1Controller implements Initializable {

    @FXML
    private Button bt_back;
    @FXML
    private Button bt_search;

    @FXML
    private Button bt_print;

    @FXML
    private ComboBox<String> cb_status;

    @FXML
    private DatePicker dp_endDate;

    @FXML
    private CheckBox cb_IsOk;

    @FXML
    private TableView<?> table_result;

    @FXML
    private DatePicker dp_startDate;

    @FXML
    private ComboBox<String> cb_servCenter;

    @FXML
    private Label lb_totalnum, label_volName;

    Connection connection = null;
    private ObservableList<ObservableList> data;

    String query = null;
    String q = null;

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
        query = "select distinct p.form_id,p.sector,p.fname,p.lname,c.status"
                + ",c.case_note,c.stating_center,c.is_ok,c.date"
                + ",(select top 1 mobile from form f where p.form_id=f.form_id) as mobile"
                + ",(select top 1 phone from form f where p.form_id=f.form_id) as phone "
                + " from person p join cases c on p.form_id=c.form_id "
                + "and p.sector = c.sector and p.fname = c.person_name ";
        q = "select distinct p.form_id,p.sector"
                + " from person p join cases c on p.form_id=c.form_id "
                + "and p.sector = c.sector and p.fname = c.person_name ";

        //check isOk checked
        if (cb_IsOk.isSelected()) {
            query += " where c.is_ok = 'yes' ";
            q += " where c.is_ok = 'yes' ";
        } else {
            query += " where c.is_ok = 'no' ";
            q += " where c.is_ok = 'no' ";
        }

        //check status is checked
        if (cb_status.getValue() != null && !cb_status.getValue().equals("بدون")) {
            query += " and c.status = '" + cb_status.getValue() + "' ";
            q += " and c.status = '" + cb_status.getValue() + "' ";
        }

        //check Service Center is checked
        if (cb_servCenter.getValue() != null && !cb_servCenter.getValue().equals("بدون")) {
            query += " and c.stating_center = '" + cb_servCenter.getValue() + "' ";
            q += " and c.stating_center = '" + cb_servCenter.getValue() + "' ";
        }

        //check dates
        if (dp_startDate.getValue() == null && dp_endDate.getValue() == null) {
            ;
        } else if (dp_endDate.getValue() == null) {
            query += " and DATEDIFF(DAY,c.date,'" + dp_startDate.getValue() + "')<=0 ";
            q += " and DATEDIFF(DAY,c.date,'" + dp_startDate.getValue() + "')<=0 ";

        } else if (dp_startDate.getValue() == null) {
            query += " and DATEDIFF(DAY,c.date,'" + dp_endDate.getValue() + "')>=0 ";
            q += " and DATEDIFF(DAY,c.date,'" + dp_endDate.getValue() + "')>=0 ";
        } else {
            query += " and DATEDIFF(DAY,c.date,'" + dp_startDate.getValue() + "')<=0 ";
            query += " and DATEDIFF(DAY,c.date,'" + dp_endDate.getValue() + "')>=0 ";
            q += " and DATEDIFF(DAY,c.date,'" + dp_startDate.getValue() + "')<=0 ";
            q += " and DATEDIFF(DAY,c.date,'" + dp_endDate.getValue() + "')>=0 ";
        }

        show(table_result, query);
        showFormNumber();
        label_volName.setText("اسم المتطوع : ");

    }

    @FXML
    public void btHelpHandler(ActionEvent event) throws IOException {
        Dialog d2 = new Alert(Alert.AlertType.INFORMATION);
        d2.setContentText("* بشكل افتراضي يتم عرض كل الحالات التي لم تستفد من الخدمة\n"
                + "* عند اختيار (الاستفادة) يتم عرض جميع الحالات التي استفادت من الخدمة\n"
                + "* عند اختيار حالة معينة يتم عرض جميع الحالات التي استتفادت او لم تستفد حسب (الاستفادة)\n"
                + "* عند اختيار الحالات يتم العرض بنفس طريقة الحالات\n"
                + "* عند اختيار الحالة مع الاحالة يتم عرض الحالات المطابقة للاختيارين بشكل مركب مع (الاستفادة)");
        //d2.setResizable(true);
        d2.setWidth(500.0);
        d2.show();
    }

    @FXML
    public void btPrintHandler(ActionEvent event) throws IOException {
        try {
            if (query != null) {
                Excel.print_as_excel(query, "تقرير عام");
            }
        } catch (SQLException ex) {
            Dialog d3 = new Alert(Alert.AlertType.ERROR);
            d3.setContentText(ex.getMessage());
            d3.show();
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
                lb_totalnum.setText("العدد الكلي  :  " + Integer.toString(count));
                rs.close();
            } catch (Exception e) {
                Dialog d3 = new Alert(Alert.AlertType.ERROR);
                d3.setContentText(e.getMessage());
                d3.show();
            }
        } else {
            Dialog d3 = new Alert(Alert.AlertType.ERROR);
            d3.setContentText("connection is null");
            d3.show();
        }

    }

    @FXML
    public void show_stating_center() {
        String query = "select * from stating_center order by state ";
        try (Statement st = conn_local.createStatement()) {
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
                cb_servCenter.getItems().add(rs.getString(1));
            }
        } catch (SQLException ex) {
            Dialog d3 = new Alert(Alert.AlertType.ERROR);
            d3.setContentText(ex.getMessage());
            d3.show();
        }
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        //this line should called once whene app start
        //Operation.create_connection();
        connection = Operation.conn_local;

        //fill status comboBox
        String query2 = "select * from cases_name order by cases_name ";
        try (Statement st = conn_local.createStatement()) {
            ResultSet rs = st.executeQuery(query2);
            cb_status.getItems().add("بدون");
            while (rs.next()) {
                cb_status.getItems().add(rs.getString(1));
            }

        } catch (SQLException ex) {
            Dialog d3 = new Alert(Alert.AlertType.ERROR);
            d3.setContentText(ex.getMessage());
            d3.show();
        }
        show_stating_center();

        table_result.setOnMousePressed(new EventHandler<MouseEvent>() {
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
                            //get vol name
                            String str = row.getItem().toString().split(",")[0].substring(1).trim();
                            String[] str1 = str.split("]");
                            Statement st;
                            try {
                                st = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
                                ResultSet rs;
                                q = "select vol_creator_name from form where form_id= " + str.trim();
                                rs = st.executeQuery(q);
                                rs.next();
                                String name = rs.getString(1);
                                label_volName.setText("اسم المتطوع : " + name);
                            } catch (SQLException ex) {
                                Dialog d3 = new Alert(Alert.AlertType.ERROR);
                                d3.setContentText(ex.getMessage());
                                d3.show();
                                ex.printStackTrace();
                            }

                        } catch (Exception ex) {
                            ex.printStackTrace();
                            //System.out.println("nothing to display");
                        }
                    }
                }
            }
        });
    }

    private void showFormNumber() {
        //get number of forms
        Statement st;
        try {
            st = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            ResultSet rs;
            rs = st.executeQuery(q);
            rs.last();
            //rs.getRow();
            //rs.next();
            int count = rs.getRow();
            //lb_totalnum.setText(lb_totalnum.getText()+"عدد الاستمارات  ... " + count);
            lb_totalnum.setText(lb_totalnum.getText() + "  عدد الاستمارات الكلي " + count);
        } catch (SQLException ex) {
            Dialog d3 = new Alert(Alert.AlertType.ERROR);
            d3.setContentText(ex.getMessage());
            d3.show();
            ex.printStackTrace();
        }
    }

}
