/**
 * Created by [fbiliboh] on 06.03.2016.
 * Created by ::Tyler the human Compiler::  on 06.03.2016.
 */

import javafx.animation.Animation;
import javafx.animation.Transition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

/** Animates a node on and off screen to the left. */
public class SideBar extends VBox {
    /** @return a control button to hide and show the sidebar */
    public Button getControlButton() { return controlButton; }
    private final Button controlButton;
    private Label label;

    /** creates a sidebar containing a vertical alignment of the given nodes */
    SideBar(final double expandedWidth,final double expandedHeight, Node... nodes) {
        getStyleClass().add("sidebar");
        this.setPrefWidth(expandedWidth);
        this.setMinWidth(0);
        this.setPrefHeight(expandedHeight);
        this.setMinHeight(0);

        // create a bar to hide and show.
        setAlignment(Pos.CENTER);
        getChildren().addAll(nodes);

        Image icon = new Image("file:src/bilder/doublearrowforward.png");
        ImageView sidebarControlButton = new ImageView(icon);

        label  = new Label();
        label.setStyle("-fx-text-fill: black");
        label.setText(">>");
        label.setPrefSize(20,10);

        // create a button to hide and show the sidebar.
        controlButton = new Button();
        controlButton.setGraphic(new Group(label));
        //controlButton.getStyleClass().add("hide-left");

        // apply the animations when the button is pressed.
        controlButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent actionEvent) {
                // create an animation to hide sidebar.
                final Animation hideSidebar = new Transition() {
                    { setCycleDuration(Duration.millis(700)); }
                    protected void interpolate(double frac) {
                        final double curWidth = expandedWidth * (1 - frac);
                        setPrefWidth(curWidth);
                        setTranslateZ(-expandedWidth + curWidth);
                    }
                };
                hideSidebar.onFinishedProperty().set(new EventHandler<ActionEvent>() {
                    @Override public void handle(ActionEvent actionEvent) {
                        setVisible(false);
                        label.setText("<<");
                        //controlButton.setGraphic(label);
                        //controlButton.setText("<");
                        //controlButton.getStyleClass().remove("hide-left");
                        //controlButton.getStyleClass().add("show-right");
                    }
                });

                // create an animation to show a sidebar.
                final Animation showSidebar = new Transition() {
                    { setCycleDuration(Duration.millis(700)); }
                    protected void interpolate(double frac) {
                        final double curWidth = expandedWidth * frac;
                        setPrefWidth(curWidth);
                        setTranslateZ(-expandedWidth + curWidth);
                    }
                };
                showSidebar.onFinishedProperty().set(new EventHandler<ActionEvent>() {
                    @Override public void handle(ActionEvent actionEvent) {
                        label.setText(">>");
                        //controlButton.setText(">");
                        //controlButton.getStyleClass().add("hide-left");
                        //controlButton.getStyleClass().remove("show-right");
                    }
                });

                if (showSidebar.statusProperty().get() == Animation.Status.STOPPED && hideSidebar.statusProperty().get() == Animation.Status.STOPPED) {
                    if (isVisible()) {
                        hideSidebar.play();
                    } else {
                        setVisible(true);
                        showSidebar.play();
                    }
                }
            }
        });
    }
}
