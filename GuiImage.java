import java.io.*;
import javafx.application.*;
import javafx.scene.control.*;
import javafx.scene.paint.*;
import javafx.scene.*;
import javafx.scene.shape.*;
import javafx.scene.layout.*;
import javafx.stage.*;
import javafx.event.*;
import javafx.scene.input.*;
import javafx.scene.text.*;
import javafx.geometry.*;
import java.util.*;


//class GuiImage extends Application{
public class GuiImage{

  public static void main(String[] args){
    Application.launch(GuiImages.class, args);
  }

  public static class GuiImages extends Application{
    private static final int PADDING = 10;
    public GridPane pane;

  @Override
  public void start(Stage primaryStage){

    //start the GuiImage
    pane = new GridPane();
    pane.setAlignment(Pos.CENTER);
    pane.setPadding(new Insets(PADDING,PADDING,PADDING,PADDING));
    //pane.setStyle("-fx-background-color: rgb(169,169,169)");
    pane.setStyle("-fx-background-color: rgb(100,100,100)");
    pane.setVgap(5);
    pane.setHgap(5);

    //top level title pane
    BorderPane titlePane = new BorderPane();
    titlePane.setCenter(pane);

    //properties of Stage
    primaryStage.setTitle("iTrade GUI");
    primaryStage.setWidth(500);
    primaryStage.setHeight(500);

    //title
    //Label header = new Label("iTrade Food Classifier");
    Label header = new Label("iTrade");
    header.setTextFill(Color.WHITE);
    Label header1 = new Label("Food Classifier");
    header.setFont(Font.font("Arial", 40));
    header1.setFont(Font.font("Arial", 40));
    header1.setTextFill(Color.BLACK);

    //HBox for title
    HBox titleBox= new HBox(100);
    titleBox.setAlignment(Pos.CENTER);
    titleBox.setStyle("-fx-background-color: rgb(236, 148, 89)");
    //titleBox.setStyle("-fx-background-color: rgb(255, 255, 255)");
    titleBox.getChildren().addAll(header);
    header.setAlignment(Pos.CENTER);
    titleBox.getChildren().addAll(header1);
    header1.setAlignment(Pos.CENTER);
    titlePane.setTop(titleBox);

    //TextBox for Url
    Text urlLabel = new Text("URL: ");
    urlLabel.setFont(Font.font("Arial", 20));
    TextField urlText = new TextField();
    urlText.setPromptText("Enter URL here...");
    pane.add(urlLabel, 0, 0);
    pane.add(urlText, 1, 0);
    urlLabel.setFill(Color.WHITE);

    //button to enter the URL
    Button enter = new Button("Enter");
    pane.add(enter, 2, 0);

    TextField descriptionBox = new TextField();
    Text desc = new Text("Identification: ");
    desc.setFont(Font.font("Arial", 20));
    pane.add(desc, 0, 4);
    pane.add(descriptionBox, 1, 4);
    desc.setFill(Color.WHITE);

    //Action for Enter KEY
    urlText.setOnAction(new EventHandler<ActionEvent>(){
      @Override
      public void handle(ActionEvent e){
        if (urlText.getText() != null && !urlText.getText().isEmpty()){
          String inputUrl = urlText.getText();
          try{
            String description = findDescription(inputUrl);
            descriptionBox.setText(description);
          }
          catch(Exception x){
            System.err.println("Oopsie");
          }

        }
      }
    });

    //Action for Enter Button
    enter.setOnAction( new EventHandler<ActionEvent>(){
      @Override
      public void handle(ActionEvent e){
        if (urlText.getText() != null && !urlText.getText().isEmpty()){
          String inputUrl = urlText.getText();
          try{
            String description = findDescription(inputUrl);
            descriptionBox.setText(description);
          }
          catch(Exception x){
            System.err.println("Oopsie");
          }

        }
      }
    });


    //Clear box
    Button clear = new Button("Clear");
    pane.add(clear, 1, 5);

    //Action for Clear Button
    clear.setOnAction( new EventHandler<ActionEvent>(){
      @Override
      public void handle(ActionEvent e){
        urlText.setText(null);
        descriptionBox.setText(null);
      }
    });
    Scene paneScene = new Scene(titlePane);
    primaryStage.setScene(paneScene);
    primaryStage.show();

  }

  // Find the identification/description of an image url
  public static String findDescription(String url) throws IOException, InterruptedException{

     // Command to execute python
     String pyCommand = "python image_visionAPI.py ";
     Process p = Runtime.getRuntime().exec(pyCommand + url);

     InputStream isr = p.getInputStream();
     BufferedReader in  = new BufferedReader(new InputStreamReader(isr));

     String reeed = in.readLine();

     return reeed;
  }
}
}
