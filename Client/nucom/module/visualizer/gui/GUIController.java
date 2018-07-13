package nucom.module.visualizer.gui;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import graph.VisGraph;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.concurrent.Worker;
import javafx.concurrent.Worker.State;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.FileChooser;
import netscape.javascript.JSObject;
import nucom.module.visualizer.call.CallStep;
import nucom.module.visualizer.call.CallStepAdapter;
import nucom.module.visualizer.jsevents.OnClickController;
import nucom.module.visualizer.utility.Log;
import nucom.module.visualizer.utility.LogHelper;

public class GUIController
{	
	private Log log = null;
	
	@FXML WebView BROWSER;	
	@FXML Button BUTTON_LOAD;
	@FXML AnchorPane CONTENT_PANE;
	@FXML VBox INFO_PANE;
			
	private WebEngine WE = null;
	private VisGraph Graph = null;
	
	public GUIController()
	{}

	@FXML
	protected void initialize() 
	{		
		log = new Log(this.getClass());
		log.debug("Initialized GUIController");
		InitGraph();
	}

	private void InitGraph()
	{
		log.debug("Init Graph");
		WE = BROWSER.getEngine();
		Graph = new VisGraph();
		
		try
		{
			
			URL S= GUIController.class.getResource("../ui/baseGraph.html");
			log.debug(S.toExternalForm());
			
			WE.load(S.toExternalForm());			
		}
		catch(Exception e)
		{
			log.debug("Error while loading baseGraph");
			e.printStackTrace();
		}
		OnClickController OCC = new OnClickController();
		WE.getLoadWorker().stateProperty().addListener(new ChangeListener<Worker.State>() 
		{
			@Override
			public void changed(ObservableValue<? extends State> Ov, State old, State newstate) 
			{
				if(newstate == State.SUCCEEDED)
				{
					JSObject Window = (JSObject) WE.executeScript("window");
					Window.setMember("OnClickController", OCC);
					log.debug("Click Controller Listener added!");
				}
			}
		});
	}
	
	@FXML
	public void ON_LOAD_ACTION(ActionEvent AE)
	{
		log.debug("LOAD_ACTION");		
		
		FileChooser FC = new FileChooser();
		File F =FC.showOpenDialog(null);
		if (F != null)
		{
			log.debug("Loading File: " + F.getAbsolutePath());
			
			List<CallStep> CallSteps = new ArrayList<CallStep>();
			
			try
			{
				BufferedReader BR = new BufferedReader(new FileReader(F));
				
				String Line="";
				GsonBuilder GB = new GsonBuilder();
				GB.registerTypeAdapter(CallStep.class, new CallStepAdapter());
				Gson GS = GB.create();
				CallStep CS = null;
				while((Line = BR.readLine()) != null)
				{
					log.debug(Line);
					CS = GS.fromJson(Line, CallStep.class);
					CallSteps.add(CS);
				}
				
				BR.close();
				log.debug("Loading Completed. Result");
				log.debug("CallSteps: " + CallSteps.size());
				
				
				
			}
			catch(Exception e)
			{
				LogHelper.EtoStringLog(log, e);
			}
			
		}
	}
	
	

	
}
