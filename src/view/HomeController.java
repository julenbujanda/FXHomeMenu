package view;

import com.jfoenix.controls.JFXButton;
import javafx.animation.Animation;
import javafx.animation.FadeTransition;
import javafx.animation.TranslateTransition;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.util.Duration;

/**
 * Clase controlador de Home.fxml
 * @author Julen Bujanda
 */
public class HomeController {

    private boolean drawerOpened;
    private boolean opacityOn;
    private TranslateTransition translateTransition;
    private FadeTransition fadeTransition;

    @FXML
    private JFXButton option1, option2;

    @FXML
    private Pane pane1, pane2, pane3, pane4, opacityPane, drawerPane;

    @FXML
    private GridPane gridPane;

    /**
     * Inicializar ventana, asignar imágenes a cada panel,
     * crear botones y comenzar las transiciones
     */
    public void initialize() {
        pane1.setStyle("-fx-background-image: url(img/fade1.jpg);");
        pane2.setStyle("-fx-background-image: url(img/fade2.jpg);");
        pane3.setStyle("-fx-background-image: url(img/fade3.jpg);");
        pane4.setStyle("-fx-background-image: url(img/fade4.jpg);");
        createButtons();
        animacionFondo();
        opacityOn = true;
        translateTransition = new TranslateTransition(Duration.seconds(0.5), drawerPane);
        fadeTransition = new FadeTransition(Duration.seconds(0.5), opacityPane);
        fadeOpacityPane();
        translateTransition.setByX(-300);
        translateTransition.play();
    }

    /**
     * Añade el texto a cada botón del GridPane
     */
    private void createButtons() {
        int i = 1;
        for (Node button : gridPane.getChildren()) {
            if (button instanceof JFXButton) {
                Label label = new Label("SECTION " + i);
                label.setStyle("-fx-effect: dropshadow(gaussian, black, 5, 0.5, 0.0, 0.0); -fx-text-fill: #FFFFFF");
                ((JFXButton) button).setGraphic(label);
                i++;
            }
        }
    }

    /**
     * Abrir o cerrar el drawer según su posición,
     * y comenzar la transición del OpacityPane
     */
    @FXML
    private void switchDrawer() {
        /*
         * Se asegura de que la transición no se está ejecutando para evitar
         * fallos al mostrar el menú
         */
        if (translateTransition.getStatus() != Animation.Status.RUNNING) {
            if (drawerOpened) {
                translateTransition.setByX(-300);
            } else {
                translateTransition.setByX(300);
            }
            drawerOpened = !drawerOpened;
            fadeOpacityPane();
            translateTransition.play();
        }
    }

    /**
     * Transición para mostrar el OpacityPane
     */
    private void fadeOpacityPane() {
        if (opacityOn) {
            opacityPane.setVisible(false);
            fadeTransition.setFromValue(1);
            fadeTransition.setToValue(0);
        } else {
            opacityPane.setVisible(true);
            fadeTransition.setFromValue(0);
            fadeTransition.setToValue(1);
        }
        opacityOn = !opacityOn;
        fadeTransition.play();
    }

    /**
     * Selecciona la opción 1 del menú
     */
    @FXML
    private void changeOption1() {
        option2.setStyle("-fx-text-fill: #aaaaaa; -fx-font-weight: normal;");
        option1.setStyle("-fx-text-fill: #FF4081; -fx-font-weight: bold;");
        switchDrawer();
    }

    /**
     * Selecciona la opción 2 del menú
     */
    @FXML
    private void changeOption2() {
        option1.setStyle("-fx-text-fill: #aaaaaa; -fx-font-weight: normal;");
        option2.setStyle("-fx-text-fill: #FF4081; -fx-font-weight: bold;");
        switchDrawer();
    }

    /**
     * Comienza la transición entre las imágenes de fondo
     */
    private void animacionFondo() {
        FadeTransition fadeTransition = new FadeTransition(Duration.seconds(5),
                pane4);
        fadeTransition.setFromValue(1);
        fadeTransition.setToValue(0);
        fadeTransition.play();
        fadeTransition
                .setOnFinished(event -> {

                    FadeTransition fadeTransition1 = new FadeTransition(
                            Duration.seconds(3), pane3);
                    fadeTransition1.setFromValue(1);
                    fadeTransition1.setToValue(0);
                    fadeTransition1.play();

                    fadeTransition1.setOnFinished(event1 -> {
                        FadeTransition fadeTransition2 = new FadeTransition(
                                Duration.seconds(3), pane2);
                        fadeTransition2.setFromValue(1);
                        fadeTransition2.setToValue(0);
                        fadeTransition2.play();

                        fadeTransition2.setOnFinished(event2 -> {

                            FadeTransition fadeTransition0 = new FadeTransition(
                                    Duration.seconds(3), pane2);
                            fadeTransition0.setFromValue(0);
                            fadeTransition0.setToValue(1);
                            fadeTransition0.play();

                            fadeTransition0.setOnFinished(event3 -> {

                                FadeTransition fadeTransition11 = new FadeTransition(
                                        Duration.seconds(3), pane3);

                                fadeTransition11.setFromValue(0);
                                fadeTransition11.setToValue(1);
                                fadeTransition11.play();

                                fadeTransition11.setOnFinished(event4 -> {

                                    FadeTransition fadeTransition22 = new FadeTransition(
                                            Duration.seconds(3), pane4);

                                    fadeTransition22.setFromValue(0);
                                    fadeTransition22.setToValue(1);
                                    fadeTransition22.play();

                                    fadeTransition22.setOnFinished(event5 -> {

                                        animacionFondo();
                                    });

                                });

                            });

                        });
                    });

                });
    }


}
