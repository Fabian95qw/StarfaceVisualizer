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
import nucom.module.visualizer.call.Peer;
import nucom.module.visualizer.edges.Edge;
import nucom.module.visualizer.jsevents.OnClickController;
import nucom.module.visualizer.nodes.EntryPointNode;
import nucom.module.visualizer.nodes.GroupNode;
import nucom.module.visualizer.nodes.Node;
import nucom.module.visualizer.nodes.PhoneNode;
import nucom.module.visualizer.nodes.UnknownNode;
import nucom.module.visualizer.nodes.UserNode;
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
	
	private List<Node> Nodes= null;
	private List<Edge> Edges = null;
	
	public GUIController()
	{}

	@FXML
	protected void initialize() 
	{		
		log = new Log(this.getClass());
		log.debug("Initialized GUIController");
		Nodes= new ArrayList<Node>();
		Edges = new ArrayList<Edge>();
		
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
				
				setData(CallSteps);
				refreshgraph();
								
			}
			catch(Exception e)
			{
				LogHelper.EtoStringLog(log, e);
			}
			
		}
	}
	
	private void setData(List<CallStep> CallSteps)
	{
		Nodes= new ArrayList<Node>();
		Edges = new ArrayList<Edge>();
		CallStep LastStep = new CallStep();
		LastStep.setCallState("NEW");
		Node LastNode = new EntryPointNode("EntryPoint");
		
		Node N = null;
		Edge E = null;
		Nodes.add(LastNode);
		for(CallStep CS: CallSteps)
		{
			log.debug(CS.getCallState()+" "+CS.getPeers().size());
			if(CS.getPeers().size() > 1)
			{
				N = new GroupNode(CS.getPeers().get(0).getCalleduser());
				E = new Edge(LastNode, N, LastStep.getCallState());
				Nodes.add(N);
				Edges.add(E);
			}
			else if(CS.getPeers().size() == 1)
			{
				N = new UserNode(CS.getPeers().get(0).getCalleduser());
				E = new Edge(LastNode, N, LastStep.getCallState());
				Nodes.add(N);
				Edges.add(E);				
			}
			else
			{
				N = new UnknownNode(CS.getCallState());
				E = new Edge(LastNode , N, LastStep.getCallState());
				Nodes.add(N);
				Edges.add(E);
			}
			
			for(Peer P : CS.getPeers())
			{
				Node UN = new PhoneNode(P.getPeer());
				Nodes.add(UN);
				Edge E2 = new Edge(N, UN, CS.getCallState());
				E2.getVE().setColor("red");
				Edges.add(E2);
			}
			
			LastNode = N;
			LastStep = CS;
		}
		
	}
	
	private void refreshgraph()
	{
		Graph.clear();
		for(Node N : Nodes)
		{
			Graph.addNodes(N.getVN());
			
			log.debug(N.getVN().toJson());
		}
		
		
		for(Edge E : Edges)
		{
			Graph.addEdges(E.getVE());
		
			log.debug(E.getVE().toJson());
		}
		
		 String script = "setTheData(" + Graph.getNodesJson() +  "," + Graph.getEdgesJson() + ")";
		 WE.executeScript(script);
		
	}
	
	

	
}
