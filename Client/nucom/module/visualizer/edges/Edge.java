package nucom.module.visualizer.edges;

import java.util.List;
import java.util.UUID;

import graph.VisEdge;
import nucom.module.visualizer.nodes.Node;

public class Edge 
{
	private String ID = null;
	private Node NFrom=null;
	private Node NTo = null;
	private VisEdge VE = null;
	private List<String> SelectedNumbers = null;
	private String label ="";
	
	public Edge(Node NFrom, Node NTo, String label)
	{
		this.NFrom=NFrom;
		this.NTo=NTo;
		this.label=label;
		ID = UUID.randomUUID().toString();
		if(NTo != null)
		{
			VE = new VisEdge(ID, NFrom.getVN(), NTo.getVN(), "to", label);
		}	
	}
	
	public Node getNFrom() {
		return NFrom;
	}
	
	public void setNFrom(Node NFrom) 
	{
		this.NFrom=NFrom;
		VE.setFrom(NFrom.getVN());
	}

	public Node getNTo() {
		return NTo;
	}

	public void setNTo(Node NTo)
	{
		this.NTo=NTo;
		if(VE == null)
		{
			VE = new VisEdge(ID, NFrom.getVN(), NTo.getVN(), "to", label);
		}
		else
		{
			VE.setTo(NTo.getVN());
		}
	}
	public VisEdge getVE() {
		return VE;
	}

	public String getID() {
		return ID;
	}
	
	@Override
	public  String toString()
	{
		if(NTo == null)
		{
			return NFrom.getVN().getLabel() +" == "+ label+" ==> " + "???";
		}
		else
		{
			return NFrom.getVN().getLabel() +" == "+ label +" ==> " + NTo.getVN().getLabel();
		}
		
	}

	public boolean isValid() 
	{
		if(VE == null)
		{
			return false;
		}
		return true;
	}
	
	public List<String> getSelectedNumbers()
	{
		return SelectedNumbers;
	}

	public void setSelectedNumbers(List<String> SelectedNumbers) 
	{
		this.SelectedNumbers=SelectedNumbers;
	}
	
}
