package app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;

import controller.ViewState;
import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ListChangeListener;
import javafx.scene.Scene;
import javafx.stage.Stage;

@SpringBootApplication
@ComponentScan({"controller","service","dao"})
@EntityScan( basePackages = {"model"})
@Import({ SpringConfig.class})
public class Main extends Application{
	
	private ApplicationContext ctx;

	public static void main(String[] args) {
		launch();
	}

	@Override
	public void start(Stage stage) throws Exception {
		ctx = SpringApplication.run(Main.class);
		ViewState stateView = ctx.getBean(ViewState.class);
		stateView.displayLogInView();
		Scene scene = new Scene(stateView.getCurrentState());
		stage.setScene(scene);
		scene.rootProperty().addListener((arg, oldVal, newVal) -> {
			stage.sizeToScene();
			stage.centerOnScreen();
		});
		scene.rootProperty().bind(stateView.getReadOnlyObjectWrapperCurrentState());
		stage.show();

		
		
	}
	
	

}
