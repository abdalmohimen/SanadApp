/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sanadapp;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Bassam
 */
public class ReportMainInterfaceController implements Initializable {

    @FXML
    private Button bt_searchByName;

    @FXML
    public void searchByNameHandler(ActionEvent event) throws IOException {
        Stage stage = (Stage) bt_searchByName.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("ReportSearchByName.fxml"));

        Scene scene = new Scene(root);

        stage.setScene(scene);
        root = null;
        scene = null;

        stage.setX(15);
        stage.setY(2);
        stage.setTitle("البحث حسب اسم المستفيد");
        stage.show();
    }

    @FXML
    public void weeklyStatusHandler(ActionEvent event) throws IOException {
        Stage stage = (Stage) bt_searchByName.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("ReportWeeklyStatus.fxml"));

        Scene scene = new Scene(root);

        stage.setScene(scene);
        root = null;
        scene = null;

        stage.setX(15);
        stage.setY(2);
        stage.setTitle("التقرير الأسبوعي لعدد الحالات المدخلة");
        stage.show();
    }

    @FXML
    public void weeklyServicesHandler(ActionEvent event) throws IOException {
        Stage stage = (Stage) bt_searchByName.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("ReportWeeklyServices.fxml"));

        Scene scene = new Scene(root);

        stage.setScene(scene);
        root = null;
        scene = null;

        stage.setX(15);
        stage.setY(2);
        stage.setTitle("التقرير الأسبوعي لعدد الإحالات");
        stage.show();
    }

    @FXML
    public void searchByFamilyMem(ActionEvent event) throws IOException {
        Stage stage = (Stage) bt_searchByName.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("ReportMermbersOfFamily.fxml"));

        Scene scene = new Scene(root);

        stage.setScene(scene);
        root = null;
        scene = null;

        stage.setX(15);
        stage.setY(2);
        stage.setTitle("البحث حسب عدد أفراد العائلة");
        stage.show();
    }
    
    @FXML
    public void childAge(ActionEvent event) throws IOException {
        Stage stage = (Stage) bt_searchByName.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("ReportChildAge.fxml"));

        Scene scene = new Scene(root);

        stage.setScene(scene);
        root = null;
        scene = null;

        stage.setX(15);
        stage.setY(2);
        stage.setTitle("البحث حسب تاريخ الميلاد");
        stage.show();
    }

    @FXML
    public void searchByHome(ActionEvent event) throws IOException {
        Stage stage = (Stage) bt_searchByName.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("ReportHomeOrPhone.fxml"));

        Scene scene = new Scene(root);

        stage.setScene(scene);
        root = null;
        scene = null;
        stage.setResizable(false);
        stage.setX(15);
        stage.setY(2);
        stage.setTitle("البحث بالعنوان أو برقم الهاتف");
        stage.show();
    }

    @FXML
    public void btBackHandler(ActionEvent event) throws IOException {
        Stage stage = (Stage) bt_searchByName.getScene().getWindow();
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
    public void btComplex1handler(ActionEvent event) throws IOException {
        Stage stage = (Stage) bt_searchByName.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("ReportComplex1.fxml"));

        Scene scene = new Scene(root);

        stage.setScene(scene);
        root = null;
        scene = null;
        stage.setX(15);
        stage.setY(2);
        stage.setTitle("الحالات و الإحالة والوضع المادي ");
        stage.show();
    }

    @FXML
    public void btGeneralhandler(ActionEvent event) throws IOException {
        Stage stage = (Stage) bt_searchByName.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("ReportGenral.fxml"));

        Scene scene = new Scene(root);

        stage.setScene(scene);
        root = null;
        scene = null;
        stage.setX(15);
        stage.setY(2);
        stage.setTitle("استعلام عام  ");
        stage.show();
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

}
