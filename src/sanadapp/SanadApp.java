/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package sanadapp;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Dialog;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

/**
 *
 * @author Bassam
 */
public class SanadApp extends Application {
    
    public static Boolean isSplashLoaded = false;
    
    @Override
    public void start(Stage stage) throws Exception {
        
        Parent root = FXMLLoader.load(getClass().getResource("MainInterface.fxml"));
       
       // AquaFx.style();
        Scene scene = new Scene(root);
        
        // set icon on stage
        Image icon = new Image(getClass().getResourceAsStream("/images/logo.png"));
        stage.getIcons().add(icon);
        
        scene.getStylesheets().add("/CSS/Color.css");
        stage.setScene(scene);
        stage.setX(350);
        stage.setY(150);
        stage.setTitle("الواجهة الرئيسية");
        stage.show();
        
        
         stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
        @Override
        public void handle(final WindowEvent event) {
            //Stage init
            event.consume();
            System.out.println("handle() close object");
            stage.close();
            Platform.exit();
        }});
        
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        //read connection string from file and save it in
        // connString varialbe in Operation.Class
        File f = new File("connString.txt");
        try {
            BufferedReader br = new BufferedReader(new FileReader(f));
            String data = br.readLine();
            Operation.connSting=data;
            //System.out.println(data);
            
        } catch (FileNotFoundException ex) {
            Logger.getLogger(SanadApp.class.getName()).log(Level.SEVERE, null, ex);
            Dialog d3 = new Alert(Alert.AlertType.INFORMATION);
            d3.setContentText("connection String File error .. please check it");
            d3.show();
        } catch (IOException ex) {
            Logger.getLogger(SanadApp.class.getName()).log(Level.SEVERE, null, ex);
        }
        launch(args);
    }
    
}
