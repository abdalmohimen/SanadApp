/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package sanadapp;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Dialog;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author Abd Almohimmen
 */
public class ConnectionController implements Initializable {

    
    @FXML
    CheckBox Remotely,Locally;
    
    
    @FXML
    TextField Locally_text,Remotely_text;
    
    
    
    @FXML
    public void Locally_handler(ActionEvent event)
    {
        Remotely.setSelected(false);
    }
    
    
    
    
    @FXML
    public void Remotely_handler(ActionEvent event)
    {
        Locally.setSelected(false);
        Remotely_text.setDisable(false);
        try{
        
  
            Remotely_text.getText().toString().trim();
        

        }catch(NullPointerException e)
        {
            Dialog d=new Alert(Alert.AlertType.INFORMATION);
            d.setTitle("Notification");
            d.setContentText("enter valid url");
            d.show();
            
        }
    }
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
        Locally_text.setDisable(true);
        Remotely_text.setDisable(true);
    }    
    
}
