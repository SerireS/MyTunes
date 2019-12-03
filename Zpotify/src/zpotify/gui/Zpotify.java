/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package zpotify.gui;


import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * @author Peter, Jonas, Kasper og Niklas
 */
public class Zpotify extends Application
{
    private double xOffset = 0;
    private double yOffset = 0;

    //Launch app
    public static void main(String[] args)
    {
        launch(args);
    }

    @Override
    public void start(Stage stage) 
    {
        //Make top bar transparent and drag application
        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource("FXMLDocument.fxml"));
        } catch (IOException ex) {
            Logger.getLogger(Zpotify.class.getName()).log(Level.SEVERE, null, ex);
        }
        stage.initStyle(StageStyle.TRANSPARENT);

        root.setOnMousePressed(new EventHandler<MouseEvent>()
        {
            @Override
            public void handle(MouseEvent event)
            {
                xOffset = event.getSceneX();
                yOffset = event.getSceneY();
            }
        });
        root.setOnMouseDragged(new EventHandler<MouseEvent>()
        {
            @Override
            public void handle(MouseEvent event)
            {
                stage.setX(event.getScreenX() - xOffset);
                stage.setY(event.getScreenY() - yOffset);
            }
        });

        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
        stage.setTitle("Zpotify");
    }

}
