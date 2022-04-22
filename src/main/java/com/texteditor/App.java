package com.texteditor;

import javafx.application.Application;
import javafx.geometry.Bounds;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.beans.value.*;
import javafx.event.ActionEvent;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.function.DoubleBinaryOperator;

/**
 * JavaFX App
 */
public class App extends Application {

    private static Scene scene;
    private MenuBar mainMenu = new MenuBar();
    private TextArea textArea = new TextArea();
    private VBox mainLayout = new VBox();
    private BorderPane mainPane = new BorderPane();
    private Stage primaryStage;
    
    private Menu fileMenu = new Menu("File");
    private String fileOptions[] = {
        "New",
        "Open",
        "Save"
    };
    FileChooser fileChooser = new FileChooser();

    private double minWidth = 150;
    private double minHeight = 100;
    private String coolTitle = "SuperEditor";

    @Override
    public void start(Stage stage) throws IOException {
        for (String m : this.fileOptions) {
            MenuItem item = new MenuItem(m);
            this.fileMenu.getItems().add(item);
            item.setOnAction(event->{
                this.menuClicked(((MenuItem) event.getSource()).getText());
            });
        }
        this.mainMenu.getMenus().add(this.fileMenu);

        this.mainLayout.getChildren().addAll(this.mainMenu, this.textArea);
        this.mainPane.setCenter(this.mainLayout);
        scene = new Scene(this.mainPane);
        stage.setMinWidth(this.minWidth);
        stage.setMinHeight(this.minHeight);
        this.primaryStage = stage;
        stage.heightProperty().addListener((obs, oldVal, newVal) -> {
            this.textArea.setMinHeight(newVal.doubleValue() - this.textArea.getLayoutY() * 2);
        });
        stage.setScene(scene);
        stage.setTitle(this.coolTitle);
        stage.show();
    }

    public void warnUser(String message) {
        Alert alert = new Alert(AlertType.WARNING);
        alert.setTitle("Warning");
        alert.setHeaderText(message);
        alert.showAndWait();
    }

    public static void main(String[] args) {
        launch();
    }

    // handlers
    private void menuClicked(String menuTxt) {
        switch (menuTxt) {
            case "New":
                this.warnUser("New");
                break;
            case "Open":
                File selected = this.fileChooser.showOpenDialog(this.primaryStage);
                // System.out.println(selected.getAbsolutePath());
                break;
            case "Save":
                this.warnUser("Save");
                break;
        }
    }
}