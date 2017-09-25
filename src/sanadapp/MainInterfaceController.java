/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sanadapp;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.FadeTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Dialog;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 * FXML Controller class
 *
 * @author Bassam
 */
public class MainInterfaceController implements Initializable {

     @FXML
    private AnchorPane root;
    
    @FXML
    private Button bt_newForm;

    @FXML
    private Button bt_editForm,about_handler;

    @FXML
    private Button bt_cases;

    @FXML
    private Button bt_reports;
    @FXML
    private ComboBox<String> cb_sector;

    
    @FXML
    public void sectorSelectionHandler(ActionEvent event) throws IOException {
        //active buttons after sector selection
        bt_newForm.setDisable(false);
        bt_editForm.setDisable(false);
        bt_cases.setDisable(false);
        bt_reports.setDisable(false);
        
        //set the sector number in ClassesNames class
        
        ClassesNames.getSectorCode(cb_sector.getValue());
        //System.out.println("the sector is " + ClassesNames.sectorCode);
    }
    
    @FXML
    public void btNewFormHandler(ActionEvent event) throws IOException {
        Stage stage = (Stage) bt_cases.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("AddForm.fxml"));
        Scene scene = new Scene(root);
        scene.getStylesheets().add("/CSS/Color.css");
        
        stage.setScene(scene);
        root = null;
        scene = null;
        stage.setResizable(false);
        stage.setX(15);
        stage.setY(2);
        stage.setTitle("إنشاء استمارة جديدة");
        stage.show();
    }
    @FXML
    public void btEditFormHandler(ActionEvent event) throws IOException {
        Stage stage = (Stage) bt_cases.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("UpdateForm1.fxml"));
        Scene scene = new Scene(root);
        scene.getStylesheets().add("/CSS/Color.css");
        stage.setScene(scene);
        root = null;
        scene = null;
        stage.setResizable(false);
        stage.setX(15);
        stage.setY(2);
        stage.setTitle("تعديل استمارة");
        stage.show();
    }
    @FXML
    public void btCasesHandler(ActionEvent event) throws IOException {
        Stage stage = (Stage) bt_cases.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("Cases.fxml"));
        Scene scene = new Scene(root);
        scene.getStylesheets().add("/CSS/Color.css");
        stage.setScene(scene);
        root = null;
        scene = null;
        //stage.setResizable(false);
        stage.setX(15);
        stage.setY(2);
        stage.setTitle("متابعة الحالات");
        stage.show();
    }
    @FXML
    public void btReportsHandler(ActionEvent event) throws IOException {
        Stage stage = (Stage) bt_cases.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("ReportMainInterface.fxml"));
        Scene scene = new Scene(root);
        scene.getStylesheets().add("/CSS/Color.css");
        stage.setScene(scene);
        root = null;
        scene = null;
        stage.setResizable(false);
        stage.setX(15);
        stage.setY(2);
        stage.setTitle("واجهة التقارير الرئيسية");
        stage.show();
    }

    
    @FXML
    public void about_handler()
    {
        Dialog d2 = new Alert(Alert.AlertType.INFORMATION);
        d2.setContentText(" بسام بسمار &&&&عبد المهيمن مندو");
        d2.show();
    }
    
    
     private void loadSplashScreen() {
        try {
            SanadApp.isSplashLoaded = true;
            
            System.out.println("Loading Splash");
            StackPane pane = FXMLLoader.load(getClass().getResource("SplashFXML.fxml"));
            root.getChildren().setAll(pane);

            FadeTransition transition = new FadeTransition(Duration.seconds(3), pane);
            transition.setFromValue(0);
            transition.setToValue(1);
            transition.setCycleCount(1);

            FadeTransition transition1 = new FadeTransition(Duration.seconds(3), pane);
            transition1.setFromValue(1);
            transition1.setToValue(0);
            transition1.setCycleCount(1);

            transition.setOnFinished((ActionEvent event) -> {
                transition1.play();
                System.out.println("Transition 1 complete");
            });

            transition1.setOnFinished((ActionEvent event) -> {
                System.out.println("Transition 2 complete");
                try {
                    FXMLLoader loader = new FXMLLoader();
                    AnchorPane pane1 = FXMLLoader.load(getClass().getResource("MainInterface.fxml"));
                    MainInterfaceController controller = loader.getController();
                    root.getChildren().setAll(pane1);
                    return;
                } catch (IOException ex) {
                    Logger.getLogger(MainInterfaceController.class.getName()).log(Level.SEVERE, null, ex);
                }
            });

            transition.play();

        } catch (IOException ex) {
            Logger.getLogger(MainInterfaceController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    
    
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        
        if (!SanadApp.isSplashLoaded) 
        {
            loadSplashScreen();
        }
        //no sector is selected
        if(ClassesNames.sectorCode==-1){
            bt_newForm.setDisable(true);
            bt_editForm.setDisable(true);
            bt_cases.setDisable(true);
            bt_reports.setDisable(true);
            Operation.create_connection();
            
        }else{
            //set combobox value to correct sector
            cb_sector.setValue(ClassesNames.getSectorName(ClassesNames.sectorCode));
        }
        
        //select sector
        cb_sector.getItems().addAll(ClassesNames.sector_AlMedan,
                ClassesNames.sector_Alhamra,ClassesNames.sector_AlHamediea,ClassesNames.sector_Hasiaa,
                ClassesNames.sector_other);
        
        //create the connection to database
        
        
    }

}
