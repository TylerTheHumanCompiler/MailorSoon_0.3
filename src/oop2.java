import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.beans.InvalidationListener;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.NodeOrientation;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.web.HTMLEditor;
import javafx.scene.web.WebView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;

import javax.mail.Folder;
import javax.mail.MessagingException;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;
import java.sql.Array;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.*;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.logging.Level;
import java.util.logging.Logger;

import static java.awt.SystemColor.desktop;
import static org.apache.commons.lang3.StringEscapeUtils.escapeJava;


/**
 * Created by [fbiliboh] on 06.03.2016.
 * Created by ::Tyler the human Compiler::  on 06.03.2016.
 */
public class oop2 extends Application {

    public static TextArea mailTextArea;
    private Button sendButton, colorButton, newMailButton;
    public static TextField mailAdress, mailSubject, ccAdress, bccAdress;
    private SideBar sidebar;
    public int hxv = 0;
    public static StackPane webStack;
    public static HTMLEddy2 editor;
    protected static WebView view;
    private VBox controlBox,headerVBox, ccAdressBox;
    private HBox headerBox, attachmentBox;
    protected static String content;
    private VBox buttonBox;
    private Label editToggleButtonLabel, getNewMailButtonLabel,getLabelToggleButton;
    private ToggleButton editToggleButton, ccButton;
    private Image image;
    private BackgroundSize backgroundSize;
    private BackgroundImage backgroundImage;
    private Background background;
    public static Scene scene;
    public static List<String> filename = new ArrayList<>();
    public static List<String> knt = new ArrayList<>();

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {

        primaryStage.initStyle(StageStyle.UNIFIED); //
        Statement statement = Userdata.conSQL();
        List<String> varlist = new ArrayList<>();
        varlist.add("integer");
        varlist.add("string");
        varlist.add("string");
        List<String> valname = new ArrayList<>();
        valname.add("id");
        valname.add("name");
        valname.add("email");
        Userdata.createSQLTable(statement, "contacts", varlist, valname);
        String[] strarray = new String[2];
        strarray[0] = "Tyler the human Compiler";
        strarray[1] = "mc.twist@hotmail.com";
        Userdata.addEntry(statement, "contacts", strarray);
        strarray[0] = "Taylor Swift";
        strarray[1] = "swift-heil@4ever-taylor.com";
        Userdata.addEntry(statement, "contacts", strarray);
        strarray[0] = "Phbips \"the Phitler\" Bader";
        strarray[1] = "phil.bad@gmx.ch";
        Userdata.addEntry(statement, "contacts", strarray);


        knt = Userdata.getResults(statement);



        //Sidebar...
        sidebar = new SideBar(400,10,createSidebarContent2()); // als eigene Methode??
        //sidebar.setStyle("-fx-background-color: red");
        //sidebar.getChildren().addAll(background);
        //sidebar.setMaxSize(600, 400);
        //sidebar.setStyle("-fx-background-image: url(darkgradient.jpg)");
        //VBox.setVgrow(lyricPane, Priority.ALWAYS);
        //...Sidebar

        BorderPane borderPane = new BorderPane();
                   borderPane.setCenter(allContent());
                   borderPane.setRight(sidebar);
                   borderPane.setPadding(new Insets(0,0,0,0));
                   //borderPane.setStyle("-fx-opacity: 0.7");

        StackPane root = new StackPane();
                  root.setBackground(background());
                  root.getChildren().addAll(borderPane);

        scene = new Scene(root, 1000, 600);
        scene.getStylesheets().add(oop2.class.getResource("styling2.css").toExternalForm());

        primaryStage.setTitle("");
        primaryStage.setScene(scene);
        primaryStage.show();

        Label labelObservable = new Label(); //oben deklarieren??
        Label labelOldvalue = new Label();
        Label labelNewvalue = new Label();

        Model1 model1 = new Model1();

        model1.stringProperty.addListener(new ChangeListener() {
                                                      @Override
                                                      public void changed (ObservableValue observable, Object oldValue, Object newValue){
                                                          labelObservable.setText((String) observable.getValue());
                                                          labelOldvalue.setText((String) oldValue);
                                                          labelNewvalue.setText((String) newValue);

                                                          if (oldValue.equals(newValue) == false) {
                                                              Platform.runLater(new Runnable() {
                                                                  @Override
                                                                  public void run() {
                                                                      String incoming = newValue.toString();
                                                                      System.out.println(incoming);
                                                              if ((hxv < 127) == true && (incoming.contains("-fx-border-color: #D") == false && incoming.contains("-fx-border-color: #d") == false
                                                                      && incoming.contains("-fx-border-color: #E") == false && incoming.contains("-fx-border-color: #e") == false
                                                                      && incoming.contains("-fx-border-color: #F") == false && incoming.contains("-fx-border-color: #f") == false)) {
                                                                          String redhex;
                                                                          String bluehex;
                                                                  if (Integer.toHexString(hxv * 2).length() == 1) {
                                                                      redhex = new String("0" + Integer.toHexString(hxv * 2));
                                                                          } else {
                                                                      redhex = Integer.toHexString(hxv * 2);

                                                                          }

                                                                  bluehex = Integer.toHexString(255 - ((hxv - (hxv % 4)) / 4));



                                                                  String hexborder = new String("-fx-border-color: #" + redhex + bluehex + redhex + "; -fx-border-width: 3px;");
                                                                  String edistyle = new String("-fx-alignment: center; -fx-text-fill: #" + redhex + bluehex + redhex + "; -fx-fill-width: true;");
                                                                          //editor.setStyle("-fx-border-color: #000; -fx-border-width: 1px; -fx-text-alignment: center; " + edistyle);

                                                                          webStack.setStyle(hexborder);
                                                                          editor.setStyle(edistyle);
                                                                          hxv++;

                                                                      } else {
                                                                            Model1.sleeptime = 2^18;

                                                                            editor.setHtmlText("");
                                                                            webStack.setStyle("-fx-border-color: #d8d8d8; -fx-border-width: 1px; -fx-text-alignment: left; -fx-alignment: left; -fx-text-fill: #000000; -fx-fill-width: false;");
                                                                          //webStack.setStyle("-fx-border-color: black; -fx-border-width: 3px;");
                                                                            hxv = 0;
                                                                          }

                                                                  }

                                                              });
                                                          }
                                                      }
                                                  }
        );
        model1.start();
    }

    private Background background() {

        image = new Image("file:src/bilder/epicsmiley.gif"); // test bild
        backgroundSize = new BackgroundSize(BackgroundSize.AUTO, BackgroundSize.AUTO, false, false, false, true);
        //backgroundSize.isCover();
        backgroundImage = new BackgroundImage(image, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, backgroundSize);
        background = new Background(backgroundImage);

        return background;
    }

    private StackPane createSidebarContent2() throws Exception{

        StackPane sidebarAllContent = new StackPane();
        sidebarAllContent.getChildren().addAll(tabPane());
        sidebarAllContent.setPadding(new Insets(0,0,0,10));

        sidebarAllContent.setStyle("-fx-background-color: black; -fx-opacity: 0.8");

        return sidebarAllContent;
    }

    private TabPane tabPane() throws Exception {

        TabPane tabPane = new TabPane();

        tabPane.getTabs().addAll(postfach(),contacts());
        tabPane.setPadding(new Insets(25,10,20,0));

        return tabPane;
    }

    private Tab postfach() throws Exception {

        Separator separator = new Separator();
        separator.setValignment(VPos.CENTER);
        separator.setPrefWidth(Double.MAX_EXPONENT);

        VBox blankBoxSeperator = new VBox();
        //blankBoxSeperator.setPrefHeight(Double.MAX_EXPONENT);
        blankBoxSeperator.setStyle("-fx-border-color: white; -fx-border-style: hidden hidden solid hidden");

        Label welcomeLabel = new Label("hello and hava a good day");
        welcomeLabel.setFont(Font.font(null,null,null,16));
        welcomeLabel.setPadding(new Insets(10,0,0,0));

        TitledPane t1 = createTitledPanewithContent(titledPaneLabel("Postfach", "inbox.png"), "INBOX");
        t1.setExpanded(true);
        TitledPane t2 = createTitledPanewithContent(titledPaneLabel("Postausgang", "sent4.png"), "Sent Items");
        TitledPane t3 = createTitledPanewithContent(titledPaneLabel("Drafts", "draft.png"), "Drafts");
        TitledPane t4 = createTitledPanewithContent(titledPaneLabel("Junk E-Mail","testJunk2.png"), "Junk E-Mail");
        TitledPane t5 = createTitledPanewithContent(titledPaneLabel("Trash", "trash.png"), "Deleted Items");

        VBox postfachContent = new VBox();
        postfachContent.getChildren().addAll(separator,t1,t2,t3,t4,t5,blankBoxSeperator,welcomeLabel);
        postfachContent.setPadding(new Insets(10,0,0,0));

        Tab postfachTab = new Tab("Postfach");
        postfachTab.setClosable(false);
        postfachTab.setContent(postfachContent);

        return postfachTab;
    }

    private Label titledPaneLabel(String string, String filepath) {

        Image icon = new Image("file:src/bilder/" + filepath);
        ImageView imageView = new ImageView(icon);
        imageView.setFitHeight(30);
        imageView.setFitWidth(30);

        Label test = new Label(string);
        test.setFont(Font.font(null,null,null,16));
        test.setUnderline(false);
        test.setTextFill(Color.WHITE);
        test.setGraphic(imageView);
        test.setPadding(new Insets(0,0,0,-36));
        return test;
    }


    private Tab contacts() {

        TableView<Person> table = new TableView<Person>();





        TableColumn<Person,String> firstNameCol = new TableColumn<Person,String>("Vorname");
        firstNameCol.setCellValueFactory(new PropertyValueFactory("firstName"));
        TableColumn<Person,String> lastNameCol = new TableColumn<Person,String>("Nachname");
        lastNameCol.setCellValueFactory(new PropertyValueFactory("lastName"));
        TableColumn<Person,String> emailaddyCol = new TableColumn<Person,String>("E-Mail");
        emailaddyCol.setCellValueFactory(new PropertyValueFactory("emailaddy"));
        table.getColumns().setAll(firstNameCol, lastNameCol, emailaddyCol);


        ObservableList<Person> kontakts = FXCollections.observableArrayList(

        );

        int ks = knt.size();
        String listentry55;
        for(int i = 0; i < ks; i = i + 2) {
            listentry55 = knt.get(i);
            System.out.println(listentry55);
            int tt34 = listentry55.indexOf(" ");

            Person p1 = new Person();
            p1.setFirstName(listentry55.substring(0, tt34));
            p1.setLastName(listentry55.substring(tt34 + 1));

            listentry55 = knt.get(i + 1);
            System.out.println(listentry55);
            p1.setEmailaddy(listentry55);
            kontakts.add(p1);

        }
        table.setItems(kontakts);


        table.setRowFactory( tv -> {
            TableRow<Person> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (event.getClickCount() == 2 && (! row.isEmpty()) ) {
                    Person rowData = row.getItem();
                    oop2.webStack.getChildren().clear();
                    oop2.mailSubject.clear();
                    oop2.view.getEngine().loadContent(oop2.content);
                    oop2.webStack.getChildren().addAll(oop2.editor);
                    oop2.webStack.setStyle("-fx-border-color: transparent");
                    oop2.mailAdress.setText("\"" + rowData.getFirstName() + rowData.getLastName() + "\" <" + rowData.getEmailaddy() + ">");
                }
            });
            return row;
        });


        Tab contactsTab = new Tab("Kontakte");
        contactsTab.setClosable(false);
        contactsTab.setContent(table);

        return contactsTab;
    }

   /* private StackPane createSidebarContent() throws Exception {
        // create some content to put in the sidebar.

        Button btn4 = new Button();
        Button btn5 = new Button();
        Button btn3 = new Button();

        Button btn8 = new Button();

        final Pane pane = new Pane();
        final VBox vboxx = new VBox(2);
        vboxx.setStyle("-fx-control-inner-background: green; -fx-background-color: green; -fx-border-color: green");
        //pane.getChildren().addAll(vboxx);

        final Text lyric = new Text();
        lyric.getStyleClass().add("lyric-text");
        Button changeLyric = new Button("New Song");
        //changeLyric.getStyleClass().add("change-lyric");
        //changeLyric.setMaxWidth(Double.MAX_VALUE);
        changeLyric.fire();
        final BorderPane lyricPane = new BorderPane();
        lyricPane.setCenter(lyric);
        lyricPane.setBottom(changeLyric);
        vboxx.getChildren().addAll(btn4,btn5,btn3);
        vboxx.setPrefSize(Double.MAX_EXPONENT,Double.MAX_EXPONENT);


        ScrollPane sppp = new ScrollPane();
        sppp.setContent(vboxx);
        sppp.setStyle("-fx-control-inner-background: yellow");
        sppp.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER); // Horizontal
        sppp.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        sppp.setPadding(new Insets(0,0,0,35));

        VBox vboxxx = new VBox();
        vboxxx.setBackground(new Background(new BackgroundFill(Color.BLACK,CornerRadii.EMPTY, Insets.EMPTY)));
        ScrollPane sp = new ScrollPane();
        sp.setStyle("-fx-background-color: red; -fx-control-inner-background: red;");
        sp.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER); // Horizontal
        sp.setVbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);
        sp.setContent(vboxxx);


        Image icon = new Image("file:src/bilder/inbox.png");
        ImageView imageView = new ImageView(icon);
        imageView.setFitHeight(30);
        imageView.setFitWidth(30);

        Image icon2 = new Image("file:src/bilder/sent4.png");
        ImageView imageView2 = new ImageView(icon2);
        imageView2.setFitHeight(30);
        imageView2.setFitWidth(30);

        Image icon3 = new Image("file:src/bilder/trash.png");
        ImageView imageView3 = new ImageView(icon3);
        imageView3.setFitHeight(30);
        imageView3.setFitWidth(30);

        Image icon4 = new Image("file:src/bilder/testJunk2.png");
        ImageView imageView4 = new ImageView(icon4);
        imageView4.setFitHeight(30);
        imageView4.setFitWidth(30);

        Label test = new Label("  Posteingang");
        test.setFont(Font.font("", 25));
        test.setUnderline(false);
        test.setTextFill(Color.WHITE);
        test.setGraphic(imageView);
        test.setPadding(new Insets(0,0,0,-36));

        Label test2 = new Label("  Sent");
        test2.setFont(Font.font("", 25));
        test2.setUnderline(false);
        test2.setTextFill(Color.WHITE);
        test2.setGraphic(imageView2);
        test2.setPadding(new Insets(0,0,0,-33));

        Label test3 = new Label("  Trash");
        test3.setFont(Font.font("", 25));
        test3.setUnderline(false);
        test3.setTextFill(Color.WHITE);
        test3.setGraphic(imageView3);
        //test3.setPadding(new Insets(10,10,10,10));


        VBox vboxPosteingang = new VBox();
        vboxPosteingang.setPrefSize(Double.MAX_EXPONENT,Double.MAX_EXPONENT);
        vboxPosteingang.setPadding(new Insets(10,10,10,10));
        vboxPosteingang.getChildren().addAll(btn8,btn3,btn4);
        vboxPosteingang.setStyle("-fx-background-color: black");
        ScrollPane scrollPane = new ScrollPane();
        scrollPane.setContent(vboxPosteingang);
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER); // Horizontal
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
   /*     TitledPane t1 = new TitledPane();
        t1.setGraphic(test);
        t1.setExpanded(true);
        t1.setUnderline(false);
        t1.setCollapsible(true);
        t1.setAnimated(true);
        t1.setContent(scrollPane);
        t1.setPadding(new Insets(10,0,0,0)); //insets

*/
   /*     TitledPane t2 = new TitledPane();
        Button btn2 = new Button("B2");
        t2.setUnderline(true);
        t2.setExpanded(false);
        t2.setGraphic(test2);
        //t2.setContent(sppp);
        t2.setStyle("-fx-control-inner-background: black");
       // t2.setPadding(new Insets(0,0,0,-33)); //insets
*/

/*

        TitledPane t1 = createTitledPanewithContent(test, "INBOX");
        TitledPane t2 = createTitledPanewithContent(test2, "Outbox");
       // TitledPane t3 = createTitledPanewithContent(test5, "Drafts");
        TitledPane t4 = createTitledPanewithContent(test3, "Junk E-Mail");
        TitledPane t5 = createTitledPanewithContent(test3, "Deleted Items");



        ScrollPane spp = new ScrollPane();

        StackPane stpp = new StackPane();
        stpp.setStyle("-fx-background-color: #293dff; -fx-border-color: black; -fx-opacity: 0.5");
        stpp.getChildren().addAll(spp);

        VBox vb = new VBox();
        vb.getChildren().add(spp);
        vb.setStyle("-fx-background-color: #3decff");


        Label junkLabel = new Label("  Junk E-Mail");
       /* TitledPane t4 = new TitledPane();
        t4.setText("  Junk E-Mail");
        t4.setGraphic(imageView4);
        t4.setContent(sppp);
        t4.setPadding(new Insets(0,0,0,-35)); //insets
*/ /*
        TitledPane t3 = new TitledPane();
        t3.setText("  Trash");
        t3.setGraphic(imageView3);
        t3.setContent(stpp);
        t3.setPadding(new Insets(0,0,0,-35)); //insets

        final Separator separator = new Separator();
        separator.setValignment(VPos.CENTER);
        separator.setPrefWidth(Double.MAX_EXPONENT);
        //separator.setStyle("-fx-background-color: white;");

        final Separator separatorBottom = new Separator();
        //separatorBottom.setValignment(VPos.CENTER);
        //separatorBottom.setPrefWidth(Double.MAX_EXPONENT);
        separatorBottom.setStyle("-fx-background-color: red");
        VBox blankBoxSeperator = new VBox();
        //blankBoxSeperator.setPrefHeight(Double.MAX_EXPONENT);
        blankBoxSeperator.setStyle("-fx-border-color: white; -fx-border-style: hidden hidden solid hidden");


/^






        ToggleGroup toggroup = new ToggleGroup();
        ToggleButton tb1 = new ToggleButton("Toggle Folder");
        tb1.setUserData("INBOX");
        tb1.setToggleGroup(toggroup);
        tb1.setSelected(true);
        tb1.getStyleClass().add(".combo-box");
        tb1.setPrefWidth(Double.MAX_VALUE);
        tb1.setStyle("-fx-background-color: linear-gradient(#f0ff35, #a9ff00); -fx-border-color: black; -fx-border-width: 1px; -fx-background-radius: 6, 5; -fx-background-insets: 0, 1; -fx-text-fill: black; -fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.6) , 5, 0.0 , 0 , 1 ); -fx-opacity: 1;");

        ToggleButton tb2 = new ToggleButton("Toggle Folder");
        tb2.setUserData("OUTBOX");
        tb2.setToggleGroup(toggroup);
        tb2.getStyleClass().add(".combo-box");
        tb2.setPrefWidth(Double.MAX_VALUE);

        ToggleButton tb3 = new ToggleButton("Toggle Folder");
        tb3.setToggleGroup(toggroup);
        tb3.setUserData("DRAFTS");
        tb3.getStyleClass().add(".combo-box");
        tb3.setPrefWidth(Double.MAX_VALUE);

        ToggleButton tb4 = new ToggleButton("Toggle Folder");
        tb4.setToggleGroup(toggroup);
        tb4.setUserData("JUNK");
        tb4.getStyleClass().add(".combo-box");
        tb4.setPrefWidth(Double.MAX_VALUE);

        ToggleButton tb5 = new ToggleButton("Toggle Folder");
        tb5.setToggleGroup(toggroup);
        tb5.setUserData("DEL");
        tb5.getStyleClass().add(".combo-box");
        tb5.setPrefWidth(Double.MAX_VALUE);

        tb1.setFont(Font.font(null,null,null,12));
        tb1.setPadding(new Insets(10,10,10,10));
        tb2.setFont(Font.font(null,null,null,12));
        tb2.setPadding(new Insets(10,10,10,10));
        tb3.setFont(Font.font(null,null,null,12));
        tb3.setPadding(new Insets(10,10,10,10));
        tb4.setFont(Font.font(null,null,null,12));
        tb4.setPadding(new Insets(10,10,10,10));
        tb5.setFont(Font.font(null,null,null,12));
        tb5.setPadding(new Insets(10,10,10,10));

        VBox boxi = new VBox();
        boxi.getChildren().addAll(t1,tb2);



*/
/*        editToggleButtonLabel  = new Label("Toggle Folders");

        editToggleButton = new ToggleButton();
        editToggleButton.setGraphic(new Group(editToggleButtonLabel)); //group needed if in use with label and label rotated
        editToggleButton.setSelected(true);*//*






        toggroup.selectedToggleProperty().addListener(new ChangeListener<Toggle>() {
            public void changed(ObservableValue<? extends Toggle> ov, Toggle toggle,Toggle new_toggle) {
                switch (toggroup.getSelectedToggle().getUserData().toString()) {
                    case "INBOX": boxi.getChildren().clear();
                        boxi.getChildren().addAll(t1,tb2);
                        break;
                    case "OUTBOX": boxi.getChildren().clear();
                        boxi.getChildren().addAll(t2,tb3);
                        break;
                    case "DRAFTS": boxi.getChildren().clear();
                        boxi.getChildren().addAll(t3,tb4);
                        break;
                    case "JUNK": boxi.getChildren().clear();
                        boxi.getChildren().addAll(t4,tb5);
                        break;
                    case "DEL": boxi.getChildren().clear();
                        boxi.getChildren().addAll(t5,tb1);
                        break;
                    default: boxi.getChildren().clear();
                        boxi.getChildren().addAll(t1,tb2);
                        break;
                }
            }
        });




*/



/*
        boxi.setPadding(new Insets(10,0,0,0));

        TabPane tabPane = new TabPane();
        Tab t = new Tab("Postfach");
        t.setClosable(false);

        t.setContent(boxi);
        Tab tt = new Tab("Kontakte");
        tt.setClosable(false);
        VBox kntkt = new VBox();









        ScrollPane sckont = new ScrollPane();
        sckont.setContent(table);
        tt.setContent(sckont);



        tabPane.getTabs().addAll(t,tt);
        tabPane.setPadding(new Insets(25,10,20,0));*/

        //double tabWidth = tabPane.getWidth() / tabPane.getTabs().size();
        //tabPane.setTabMinWidth(tabWidth);
        //tabPane.setTabMaxWidth(tabWidth);

/*
        StackPane stp = new StackPane();
        stp.getChildren().addAll(tabPane);
        stp.setPadding(new Insets(0,0,0,10));

        stp.setStyle("-fx-background-color: black; -fx-opacity: 0.8");

        return stp;
    }
*/

    private TitledPane createTitledPanewithContent(Label test, String foldername) throws Exception {
        VBox vboxPosteingang = new VBox();
        vboxPosteingang.setPrefSize(Double.MAX_EXPONENT,Double.MAX_EXPONENT);
        vboxPosteingang.setPadding(new Insets(10,10,10,10));
        //vboxPosteingang.getChildren().addAll(btn8,btn3,btn4);
        vboxPosteingang.setStyle("-fx-background-color: white");

        BFH_EmailSender.receive(UserVariables.user, UserVariables.passwd.toCharArray());
        System.out.println(BFH_EmailSender.getFolderList().toString());
        int msgcount = BFH_EmailSender.getEmailCount(foldername);
        if(msgcount > 9) {msgcount = 9;}
        Folder inbox = BFH_EmailSender.store.getFolder(foldername);
        inbox.open(Folder.READ_ONLY);
        for (int j = 1; j <= msgcount; j++) {
            vboxPosteingang.getChildren().add(Functions.createEmailEntry(j, inbox));
        }
        inbox.close(true);
        ScrollPane scrollPane = new ScrollPane();
        scrollPane.setContent(vboxPosteingang);
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS); // Horizontal
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);
        TitledPane t1 = new TitledPane();
        t1.setGraphic(test);
        t1.setExpanded(false);
        t1.setUnderline(false);
        t1.setCollapsible(true);
        t1.setAnimated(true);
        t1.setContent(scrollPane);
        t1.setPadding(new Insets(10,0,0,0)); //insets



        return t1;

    }

    private HBox allContent() throws InterruptedException, ExecutionException, MessagingException {

        HBox allContent = new HBox(8);
             allContent.getChildren().addAll(createCenterGroup(),
                                             createSideMenu());
             allContent.setPadding(new Insets(20,10,20,20));
             allContent.setStyle("-fx-opacity: 1");
        HBox.setHgrow(controlBox, Priority.ALWAYS);

        return allContent;
    }

    private VBox createCenterGroup() throws MessagingException, ExecutionException, InterruptedException {

        controlBox = new VBox(8, createHeaderBox(),attachmentBox(),
                                 createStackPaneHtmlTextArea());

        ///controlBox.setStyle("-fx-background-color: rgba(255, 255, 255, 0); -fx-border-color: black"); // test
        controlBox.setPadding(new Insets(5,10,10,10));
        controlBox.getStyleClass().add("control-box2");
        //controlBox.setStyle("-fx-opacity: 0.7");

        HBox.setHgrow(headerBox,Priority.ALWAYS);

        return controlBox;
    }

    private HBox attachmentBox() {

        attachmentBox = new HBox(10);

        return attachmentBox;
    }

    private StackPane createStackPaneHtmlTextArea() throws InterruptedException, ExecutionException, MessagingException {

        content = "< contenteditable='true' style='font-family: fantasy'/>"; //important! //arial not working grmpf!

        editor = new HTMLEddy2();
        editor.setPadding(new Insets(-2,0,0,-2));

        //editor.setHtmlText("jhjhhjhj");
        //editor.setStyle("-fx-opacity: 1; -fx-control-inner-background: white; -fx-text-fill: black; -fx-border-color: #4f4f4f; -fx-border-width: 1px"); // test
        //editor.setPrefSize(Double.MAX_EXPONENT, Double.MAX_EXPONENT);
        //VBox.setVgrow(editor, Priority.ALWAYS);
        //((ToolBar) editor.lookup(".bottom-toolbar")).getItems().addAll(FXCollections.observableArrayList(((ToolBar)editor.lookup(".top-toolbar")).getItems()));
        //editor.setPadding(new Insets(20,10,10,10));
        //editor.lookup(".bottom-toolbar").setVisible(false);
   /*     final Map map = new HashMap();
        for (Node candidate: (editor.lookupAll("ToolBar"))) {
            List list = ((ToolBar) candidate).getItems();
            for (int i = 0; i < list.size(); i++) {
                Node b = (Node) list.get(i);
                map.put(map.size() + 1, b);
            }
        }

        map.remove(18); // Removes font-menu-button
        map.remove(25);
        map.remove(1);
*/
        Node node = editor.lookup(".top-toolbar");
        if (node instanceof ToolBar) {
            ToolBar bar = (ToolBar) node;
            Separator blabli = new Separator();
            bar.getItems().addAll(createColorButton(),blabli);
        }

        view = new WebView();

        //view.setBlendMode(BlendMode.DARKEN);
        //view.setStyle("-fx-border-color: black; -fx-border-insets: 5px 5px 5px 5px;");
        //view.setPrefSize(Double.MAX_EXPONENT, Double.MAX_EXPONENT);

        webStack = new StackPane();
        //webStack.setStyle("-fx-border-color: red;  -fx-background-color: white; -fx-opacity: 1"); // test
        webStack.getChildren().addAll(editor);


        return webStack;
    }

    private VBox createSideMenu() throws InterruptedException, ExecutionException, MessagingException {

        Image newMailImage = new Image("file:src/bilder/plusSign2.png"); // oben deklarieren?
        ImageView newMailImageView = new ImageView(newMailImage);
                  newMailImageView.setFitWidth(20);
                  newMailImageView.setFitHeight(20);

        Image editImage = new Image("file:src/bilder/browser-stars.png",20,20,true,true); // oben deklarieren?
        ImageView editImageView = new ImageView(editImage);
        //editImageView.setFitWidth(40);
        //editImageView.setFitHeight(40);

        Tooltip newMailTooltip = new Tooltip("New Mail");
        //hackTooltipStartTiming(newMailTooltip);

        newMailButton = new Button();
        newMailButton.setGraphic(newMailImageView);
        newMailButton.setTooltip(newMailTooltip);
        newMailButton.setOnAction(event -> {
            oop2.webStack.getChildren().clear();
            oop2.mailSubject.clear();
            oop2.view.getEngine().loadContent(oop2.content);
            oop2.webStack.getChildren().addAll(oop2.editor);
            oop2.webStack.setStyle("-fx-border-color: #d8d8d8");
            oop2.mailAdress.clear();
        });




        editToggleButtonLabel  = new Label("Edit"); //only use label if rotate of button is needed
        //editToggleButtonLabel.setRotate(-90);
        editToggleButtonLabel.setStyle("-fx-text-fill: black");
        editToggleButtonLabel.setPrefHeight(20);

        editToggleButton = new ToggleButton();
        editToggleButton.setGraphic(new Group(editToggleButtonLabel)); //group needed if in use with label and label rotated
        editToggleButton.setSelected(false);

        editToggleButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent arg0) {

                if (editToggleButton.isSelected()) {

                    HTMLEddy2Skin.hideEddyToolBar();
                } else {
                    HTMLEddy2Skin.showEddyToolBar();
                }
            }
        });

        editToggleButton.fire();




        Button attachButton = new Button();
        final FileChooser fileChooser = new FileChooser();
        final Stage stage = new Stage();


        attachButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent arg0) {
                List<File> list =
                        fileChooser.showOpenMultipleDialog(stage);
                if (list != null) {
                    for (File file : list) {

                        filename.add(file.getAbsolutePath());
                        System.out.print("file: " + file.getAbsolutePath());
                    }}}    }
        );

        VBox sideMenuButtons = new VBox(5); //oben deklarieren?
        sideMenuButtons.getChildren().addAll(sidebar.getControlButton(), newMailButton, editToggleButton, attachButton);
        sideMenuButtons.setPadding(new Insets(10, 5, 10, 5));

        return sideMenuButtons;
    }

    private TextArea createTextArea() { //not used, as html editor was added

        mailTextArea = new TextArea();
        mailTextArea.setPrefSize(Double.MAX_EXPONENT, Double.MAX_EXPONENT);
        mailTextArea.setFocusTraversable(true);
        mailTextArea.setWrapText(true);
        mailTextArea.setStyle("-fx-control-inner-background: white; -fx-text-fill: black; -fx-border-color: #e0e0e0; -fx-border-width: 0px;"); //set as ID with styling.css!
        //mailTextArea.requestFocus();
        mailTextArea.getStyleClass().add("vbox");
        return mailTextArea;
    }


    private Desktop desktop = Desktop.getDesktop();
    private void openFile(File file) {
        try {
            desktop.open(file);
        } catch (IOException ex) {
            Logger.getLogger(
                    oop2.class.getName()).log(
                    Level.SEVERE, null, ex
            );}}

    private VBox createHeaderTextFields() {

        mailAdress = new TextField();
        mailAdress.setPromptText("Type in E-Mail-Adress");
        mailAdress.setStyle("-fx-opacity: 1"); // test
        mailAdress.setPrefWidth(Double.MAX_EXPONENT);

        mailSubject = new TextField();
        mailSubject.setPromptText("Subject:");
        mailSubject.setStyle("-fx-opacity: 1"); // test
        mailSubject.setPrefWidth(Double.MAX_EXPONENT);

        headerVBox = new VBox(2);
        headerVBox.getChildren().addAll(mailAdress,ccAdressBox(),mailSubject);
        //headerVBox.setStyle("-fx-opacity: 0.7");

        return headerVBox;
    }

    private VBox ccAdressBox() {

        ccAdress = new TextField();
        ccAdress.setPromptText("Type cc-adress");
        bccAdress = new TextField();
        bccAdress.setPromptText("Type bcc-adress");

        ccAdressBox = new VBox();

        return ccAdressBox;
    }

    private HBox createHeaderBox() throws MessagingException, ExecutionException, InterruptedException {

        headerBox = new HBox(10);
        headerBox.setPadding(new Insets(0,0,0,0));        // test
        headerBox.getChildren().addAll(createHeaderTextFields(),
                                       createButtonBox());

        HBox.setHgrow(headerVBox,Priority.ALWAYS);

        return headerBox;
    }

    private VBox createButtonBox() throws InterruptedException, ExecutionException, MessagingException {

        buttonBox = new VBox(10);
        buttonBox.getChildren().addAll(createSendButton(),ccAdressAndAttachButtonBox());
        buttonBox.setPadding(new Insets(5,0,0,0));

        return buttonBox;
    }

    private HBox ccAdressAndAttachButtonBox() {

        HBox saveAndAttachBox = new HBox(-5);
             saveAndAttachBox.getChildren().addAll(ccBccButton(),attachmentButton());
             saveAndAttachBox.setPadding(new Insets(0,0,0,0));
        return saveAndAttachBox;
    }

    private ToggleButton ccBccButton() {

        Image saveImage = new Image("file:src/bilder/cc.png"); //soll man diese oben als private deklarieren?
        ImagePattern imagePattern = new ImagePattern(saveImage,0, 0, 1, 1, true);

        Rectangle ccIcon = new Rectangle(20,20);
                  ccIcon.setFill(imagePattern);

        ccButton = new ToggleButton();
        ccButton.setGraphic(ccIcon);
        ccButton.setStyle("-fx-background-color: transparent; -fx-border-color: transparent");

        ccButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent arg0) {
                //webStack.getChildren().clear();
                if (ccButton.isSelected()) {
                    ccAdressBox.getChildren().addAll(ccAdress,bccAdress);
                } else {
                    ccAdressBox.getChildren().removeAll(ccAdress,bccAdress);
                }
            }
        });

        return ccButton;

    }

    private Rectangle attachmentButton() {

        Image attachmentImage = new Image("file:src/bilder/attach2.png"); //soll man diese oben als private deklarieren?
        ImagePattern imagePattern = new ImagePattern(attachmentImage,0, 0, 1, 1, true);

        Rectangle attachmentIcon = new Rectangle(30,30);
                  attachmentIcon.setFill(imagePattern);

        attachmentIcon.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                attachmentBox.getChildren().addAll(attachmentIcon());
                System.out.println("rectangle hie!!!!!!!!!!");
            }
            private Button attachmentIcon(){
                Button attachmentIcon = new Button();
                attachmentIcon.setOnAction(event -> attachmentBox.getChildren().remove(attachmentIcon));
                return attachmentIcon;
            }
        });

        return attachmentIcon;
    }


    private Button createColorButton() throws MessagingException, ExecutionException, InterruptedException {

        Image rainbow = new Image("file:src/bilder/rainbow.jpg"); //soll man diese oben als private deklarieren?
        ImageView imageView = new ImageView(rainbow);
        imageView.setFitHeight(20);
        imageView.setFitWidth(72);

        Label rainbowLabel = new Label("Rainbow Hex"); // oben deklarieren?

        colorButton = new Button();
        //colorButton.setText("Rainbow Hex!");
        //colorButton.setStyle("-fx-text-fill: black");
        colorButton.setGraphic(new Group(imageView,rainbowLabel));

        colorButton.setOnAction(event -> {
            int len = editor.getHtmlText().length();
            Functions m1 = new Functions();
            String seltxt = editor.getSelectedText();
            if(seltxt.contains("\n")) {
                int ln1 = seltxt.indexOf("\n");
                int ln2 = seltxt.lastIndexOf("\n");
                String alphastr = seltxt.substring(0, ln1);
                String omegastr = seltxt.substring(ln2 + 1);
                String hts = editor.getHtmlText();
                int p1 = hts.indexOf(alphastr);
                int p2 = hts.indexOf(omegastr) + omegastr.length();
                System.out.println("HTS::::: " + hts.substring(p1, p2));
                m1.makeTextColorAgain(p1, p2, "xxxxxx", seltxt);

            } else {

            int p1 = editor.getHtmlText().indexOf(seltxt);
            m1.makeTextColorAgain(p1, p1 + seltxt.length(), "xxxxxx", seltxt);
            }
        });
        return colorButton;
    }

    private Rectangle createSendButton() throws MessagingException, ExecutionException, InterruptedException {

        Image sendImage = new Image("file:src/bilder/send2.png"); //soll man diese oben als private deklarieren?
        ImagePattern imagePattern = new ImagePattern(sendImage,0, 0, 1, 1, true);

        Rectangle sendIcon = new Rectangle(50,35);
        sendIcon.setFill(imagePattern);

        sendButton = new Button();
        sendButton.setText("Send");
        sendButton.setMinSize(50, 30);

        char[] passwd = UserVariables.passwd.toCharArray();

        Tooltip tooltip = new Tooltip();
        tooltip.setText("Send E-Mail");
        hackTooltipStartTiming(tooltip);
        sendButton.setTooltip(tooltip);

        sendIcon.setOnMouseClicked(event -> {
            String emailaddress = oop2.mailAdress.getText() + " ";
            List<String> addresslist = new ArrayList<>();


                do {
                addresslist.add(emailaddress.substring(0, emailaddress.indexOf(";")));
                emailaddress = emailaddress.substring(emailaddress.indexOf(";") + 2);
                } while (emailaddress.contains(";") == true);


            //if (Functions.checkEmailAddress(emailaddress)) {

            if (mailSubject.getText().length() == 0) {
                mailSubject.setText("No Subject");
            }
            if (mailSubject.getText().length() > 77) {
                System.out.println("ERROR: Subject contains too many characters");
                mailSubject.setText(mailSubject.getText().substring(1, 77));
                mailSubject.setStyle("-fx-border-color: red; -fx-border-width: 2px;");

            } else {
                for(String emailx : addresslist) {
                    System.out.println("emailx: " + emailx);
                try {
                    BFH_EmailSender.send(UserVariables.user, passwd, emailx, mailSubject.getText(), filename);

                } catch (MessagingException e) {
                    e.printStackTrace();
                }}

                webStack.setStyle("-fx-text-alignment: center; -fx-alignment: center; -fx-text-fill: green; -fx-fill-width: true;");
                webStack.setStyle("-fx-border-color: #00FF00; -fx-border-width: 3px; ");
                //System.out.println("Style: " + mailTextArea.getStyle());
                Text sctext = new Text("<span style=\"margin-left: auto; margin-right: auto;\">e-Mail sent.</span>");
                //sctext.setStyle("-fx-alignment: center; -fx-effect: dropshadow(gaussian, black, 10, 1.0, 100, 100); -fx-fill-width: true;" +
                //" -fx-opacity: 2; -fx-text-alignment: center; -fx-text-fill: green");
                editor.setHtmlText(sctext.getText());
                mailAdress.clear();
                mailSubject.clear();
                if (mailSubject.getStyle().contains("-fx-border-color: red;")) {
                    mailSubject.setStyle("-fx-border-color: gray; -fx-border-width: 0px;");
                }

            }
            oop2.filename.clear();
            Model1.sleeptime = 64;
        });

        return sendIcon;
    }

    public static void hackTooltipStartTiming(Tooltip tooltip) { //needed? yes eventually for the +(new mail) icon!
        try {
            Field fieldBehavior = tooltip.getClass().getDeclaredField("BEHAVIOR");
            fieldBehavior.setAccessible(true);
            Object objBehavior = fieldBehavior.get(tooltip);

            Field fieldTimer = objBehavior.getClass().getDeclaredField("activationTimer");
            fieldTimer.setAccessible(true);
            Timeline objTimer = (Timeline) fieldTimer.get(objBehavior);

            objTimer.getKeyFrames().clear();
            objTimer.getKeyFrames().add(new KeyFrame(new Duration(100)));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
