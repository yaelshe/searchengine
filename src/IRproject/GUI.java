package IRproject;

import javafx.application.Application;
import javafx.beans.binding.Bindings;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;

import javafx.geometry.Insets;
import javafx.scene.layout.GridPane;
import jdk.nashorn.internal.runtime.regexp.joni.constants.CCSTATE;

import java.io.File;
import java.util.List;


public class GUI extends Application {

    Stage window;
    //Scene dictionaryScene, cacheScene;
    TableView <DictionaryTermGui>dictionary;
    TableView <CacheTermGui>cache;
    private boolean doStemming=true;
    TextField postingInput;
    String s="";
    String pathToPosting="";
    String pathToCorpus="";


    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        window = primaryStage;
        window.setTitle("Welcome to our search engine! ");
        GridPane grid = new GridPane();
        grid.setPadding(new Insets(10, 10, 10, 10));
        grid.setVgap(8);
        grid.setHgap(10);

        //table for dictionary
        TableColumn<DictionaryTermGui,String>termCol=new TableColumn<>("Term");
        termCol.setMinWidth(200);
        termCol.setCellValueFactory(new PropertyValueFactory<>("term"));

        TableColumn<DictionaryTermGui,String>amountCol=new TableColumn<>("Quantity");
        amountCol.setMinWidth(100);
        amountCol.setCellValueFactory(new PropertyValueFactory<>("amount"));

        dictionary = new TableView<>();
        dictionary.setItems(getDictionaryTermGui());
        dictionary.getColumns().addAll(termCol, amountCol);

        //table for cache
        TableColumn<CacheTermGui,String>termColC=new TableColumn<>("Term");
        termColC.setMinWidth(200);
        termColC.setCellValueFactory(new PropertyValueFactory<>("term"));

        TableColumn<CacheTermGui,String>amountColc=new TableColumn<>("Quantity");
        amountColc.setMinWidth(100);
        amountColc.setCellValueFactory(new PropertyValueFactory<>("amount"));

        cache = new TableView<>();
        cache.setItems(getCacheTermGui());
        cache.getColumns().addAll(termColC,amountColc);

        //corpus Label - constrains use (child, column, row)
        Label corpusLabel = new Label("Enter path to corpus:");
        GridPane.setConstraints(corpusLabel, 0, 0);

        //corpos path Input
        TextField corpusInput = new TextField();
        corpusInput.setPromptText("corpus path here");
        GridPane.setConstraints(corpusInput, 1, 0);

        //posting Label
        Label postingLabel = new Label("Enter path to posting files:");
        GridPane.setConstraints(postingLabel, 0, 1);

        //posting path Input
        TextField postingInput = new TextField();
        postingInput.setPromptText("posting path here");
        GridPane.setConstraints(postingInput, 1, 1);

        //browse button
        Button browseButton = new Button("browse");
        GridPane.setConstraints(browseButton, 2, 1);
        browseButton.setOnAction(e-> browser());

        //Stemming
        Label stemmLabel = new Label("Do you want to preform Stemming?");
        GridPane.setConstraints(stemmLabel, 1, 2);
        ToggleGroup stemming = new ToggleGroup();

        //checkbox fields
        RadioButton yesStem= new RadioButton("yes");
        GridPane.setConstraints(yesStem, 2, 2);
        yesStem.setToggleGroup(stemming);
        yesStem.setSelected(true);

        RadioButton noStem= new RadioButton("no");
        GridPane.setConstraints(noStem, 3, 2);
        noStem.setToggleGroup(stemming);
        //Start
        Button startButton = new Button("START");
        GridPane.setConstraints(startButton, 1, 3);
        startButton.setOnAction(e->StartButton(postingInput.getText(),corpusInput.getText(),stemming.getSelectedToggle()));
        startButton.disableProperty().bind(Bindings.createBooleanBinding( () -> !((postingInput.getText()!=null && corpusInput.getText()!=null)),
                postingInput.textProperty(), corpusInput.textProperty()));

        //option 2 for disable with listner
        /**startButton.setDisable(true); // Initally text box was empty so button was disable

        postingInput.textProperty().addListener(new ChangeListener<String>() {

            @Override
            public void changed(ObservableValue<? extends String> ov, String t, String t1) {
                //System.out.println(t+"====="+t1);
                if(postingInput.equals("")&&corpusInput.equals(("")))
                {
                    startButton.setDisable(true);
                    System.out.println("what the fuck");
                }

                else
                    startButton.setDisable(false);
            }
        });
        //end of listner
         */

        //RESET
        Button resetButton = new Button("RESET");
        GridPane.setConstraints(resetButton, 2, 4);
        //reset Label
        Label resetLabel = new Label("To reset the posting and dictionary:");
        GridPane.setConstraints(resetLabel, 1, 4);

        //Display cache
        Button cacheDisplayButton = new Button("Cache");
        GridPane.setConstraints(cacheDisplayButton, 2, 5);
        Label displayCacheLabel = new Label("To display the Cache:");
        GridPane.setConstraints(displayCacheLabel, 1, 5);
        cacheDisplayButton.setOnAction(e->displayCacheTable());

        //Display dictionary
        Button dictionaryDisplayButton = new Button("Dictionary");
        GridPane.setConstraints(dictionaryDisplayButton, 2, 6);
        Label displayDictionaryLabel = new Label("To display the Dictionary:");
        GridPane.setConstraints(displayDictionaryLabel, 1, 6);
        dictionaryDisplayButton.setOnAction(e->displayDictTable());

        //save the created files
        Button saveButton = new Button("SAVE");
        GridPane.setConstraints(saveButton, 2, 7);
        Label saveLabel = new Label("To save the files:");
        GridPane.setConstraints(saveLabel, 1, 7);


        //load the created files
        Button loadButton = new Button("LOAD");
        GridPane.setConstraints(loadButton, 2, 8);
        Label loadLabel = new Label("To load the files:");
        GridPane.setConstraints(loadLabel, 1, 8);
        //Add everything to grid
        grid.getChildren().addAll(corpusLabel, corpusInput, postingLabel, postingInput,browseButton, startButton
                ,noStem,yesStem,stemmLabel,resetButton,resetLabel,cacheDisplayButton,displayCacheLabel,saveButton,saveLabel,
                loadButton,loadLabel,dictionaryDisplayButton,displayDictionaryLabel);

        Scene scene = new Scene(grid, 500, 300);
        window.setScene(scene);
        window.show();
    }
    //When button is clicked, handle() gets called
    //Button click is an ActionEvent (also MouseEvents, TouchEvents, etc...)

    public void StartButton (String s1, String s2, Toggle box1)
    {
        if(s1.length()>0&&s2.length()>0)
        {//the fields are filled
            pathToCorpus=s1;
            pathToPosting=s2;
            //change secene to alert and back to the main window to let write again
            RadioButton chk = (RadioButton)box1.getToggleGroup().getSelectedToggle(); // Cast object to radio button
           if(chk.getText().equalsIgnoreCase("yes")) {
               System.out.println("i checked yes");
               doStemming=true;
           }
            else {
               doStemming=false;
               System.out.println("i checked no");
           }

        }
        else{// the fields are missing
            //change scene to alert and back to the main window to let write again
            AlertBox.display("Missing Input", "Error: no paths had been written!");
        }
    }
    public ObservableList<DictionaryTermGui>getDictionaryTermGui()
    {//get the items for the dictionary

        ObservableList<DictionaryTermGui> termsDictionary= FXCollections.observableArrayList();
        //**********need to add the words from the dictionary**************
        return termsDictionary;
    }

    public ObservableList<CacheTermGui>getCacheTermGui()
    {//get the items for the dictionary
        ObservableList<CacheTermGui> termsCache= FXCollections.observableArrayList();
        //**********need to add the words from the dictionary**************
        return termsCache;
    }


    public void displayDictTable()
    {//opens another window with the dictionary table display
        //if not working well try listView
        //https://stackoverflow.com/questions/27414689/a-java-advanced-text-logging-pane-for-large-output
        VBox vBox = new VBox();
        vBox.getChildren().addAll(dictionary);
        Scene dictionaryScene=new Scene(vBox);
        Stage dicwindow = new Stage();

        //Block events to other windows
        dicwindow.initModality(Modality.APPLICATION_MODAL);
        dicwindow.setTitle("THE DICTIONARY");
        dicwindow.setMinWidth(250);
        dicwindow.setScene(dictionaryScene);
        dicwindow.show();
    }
    public void displayCacheTable()
    {//opens another window with the dictionary table display
        VBox vBox = new VBox();
        vBox.getChildren().addAll(cache);
        Scene cacheScene=new Scene(vBox);
        Stage cachewindow = new Stage();

        //Block events to other windows
        cachewindow.initModality(Modality.APPLICATION_MODAL);
        cachewindow.setTitle("THE CACHE");
        cachewindow.setMinWidth(250);
        cachewindow.setScene(cacheScene);
        cachewindow.show();
    }
    public void loadFiles(){
        FileChooser fc=new FileChooser();
        fc.setInitialDirectory((new File("C:\\")));
        fc.getExtensionFilters().add(new FileChooser.ExtensionFilter("*.txt"));
        File selectedFile=fc.showOpenDialog(null);
        /**List<File> selectedFiles=fc.showOpenMultipleDialog(null);
          if(selectedFiles!=null)
         {//https://www.youtube.com/watch?v=hNz8Xf4tMI4
            for(int i=0; i<selectedFiles.size();i++)
         {
         listview.getItems().add(selectedFiles.getAbsolutePath());
         }
         */
    }
    public void browser(){
        FileChooser fc=new FileChooser();
        fc.setInitialDirectory((new File("C:\\")));
        File selectedFile=fc.showOpenDialog(null);
        s=selectedFile.getAbsolutePath();
        if(pathToPosting.isEmpty())
            pathToPosting=s;
        System.out.println(s);

    }
    public void deleteReset()
    {
        //https://docs.oracle.com/javase/tutorial/essential/io/delete.html
    }
    public void finishData()
    {//present all of the Data that is needed aout the program
        AlertBox.display("Program Information",
                "time of running: no paths had been written!"+
                        "number of files indexed:"+
               "size of index in Bytes:"+
                       "size of cache in Bytes:");

    }

}