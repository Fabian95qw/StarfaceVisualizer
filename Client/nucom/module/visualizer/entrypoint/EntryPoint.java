package nucom.module.visualizer.entrypoint;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import nucom.module.visualizer.utility.WebViewLogger;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;


public class EntryPoint extends Application
{
	BorderPane BP_ROOT = null;
	FXMLLoader GUILOADER = null;
	
	public static boolean Shutdown = false;
	public static Stage ROOT_STAGE = null;
	
	@Override
	public void start(Stage Root_Stage)
	{
		try 
		{	
			WebViewLogger WVL = new WebViewLogger();
			WVL.equals(null);
			
			Root_Stage.setWidth(1700);
			Root_Stage.setHeight(700);
				
			Root_Stage.setMaximized(true);
						
			FXMLLoader GUILOADER = new FXMLLoader();
			//
			BP_ROOT = GUILOADER.load(EntryPoint.class.getResourceAsStream("/nucom/module/visualizer/gui/GUI.fxml"));
			
			GUILOADER.getController();
			
			Scene S = new Scene(BP_ROOT);
			Root_Stage.setScene(S);
						
			//Root_Stage.setFullScreen(true);
			
			Root_Stage.show();
		} 
		catch(Exception e) 
		{
			e.printStackTrace();
		}
		ROOT_STAGE = Root_Stage;
		
		ROOT_STAGE.setOnCloseRequest( event -> 
		{
			Shutdown = true;
			System.exit(0);
		});
	}
	
	
	public static void main(String[] args) {
		launch(args);
	}
}
