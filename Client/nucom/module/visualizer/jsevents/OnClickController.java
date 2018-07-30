package nucom.module.visualizer.jsevents;

import java.util.ArrayList;
import java.util.List;

import netscape.javascript.JSObject;
import nucom.module.visualizer.gui.GUIController;
import nucom.module.visualizer.utility.Log;

public class OnClickController 
{
	private Log log =null;
	private GUIController GC = null;
	
	public OnClickController(GUIController GC)
	{
		log = new Log(this.getClass());
		this.GC=GC;
	}
	
	public void Submit(Object Nodes, Object Edges)
	{		

		try
		{
			List<String>NodeList = toNodeList(Nodes);
			List<String>EdgeList = toEdgesList(Edges);
			GC.showInfo(NodeList, EdgeList);
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}

	private List<String> toNodeList(Object Nodes)
	{
		JSObject JSNodes = (JSObject) Nodes;
		Integer I = 0;
		String S = "";
		List<String> IDS = new ArrayList<String>();
		
		while(!S.equals("undefined"))
		{
			S = NodeToString(JSNodes.getSlot(I));
			if(!S.equals("undefined"))
			{
				IDS.add(S);
			}
			I++;
		}
		
		log.debug("Found a Total of: " + IDS.size() +" Nodes: " + IDS.toString());
		
		return IDS;
	}
	
	private String NodeToString(Object O)
	{
		String S ="";
		
		try
		{
			S = (String) O;
			return S;
		}
		catch(ClassCastException e)
		{
		}
		
		try
		{
			Integer I = (Integer) O;
			S = String.valueOf(I);
			return S;
		}
		catch(ClassCastException e)
		{
		}
		
		return "CLASSCASTEXCEPTION";
	}
	
	private List<String> toEdgesList(Object Edges)
	{
		
		JSObject JSEdges = (JSObject) Edges;
		Integer I = 0;
		String S = "";
		List<String> IDS = new ArrayList<String>();
		
		while(!S.equals("undefined"))
		{
			S = (String) JSEdges.getSlot(I);
			if(!S.equals("undefined"))
			{
				IDS.add(S);
			}
			I++;
		}
		
		log.debug("Found a Total of: " + IDS.size() +" Edges: " + IDS.toString());
		
		return IDS;
	}


	
}
