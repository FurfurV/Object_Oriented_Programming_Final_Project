/**
 * 🦄
 *
 * @author Viktoria Cseke
 * R00180598 11/04/2020
 */
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import view.Management;

public class ProjectMain extends Application {
    private Scene scene;
    private Management m = new Management();

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("My Soccer League application");
        scene = m.management(primaryStage);

        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
