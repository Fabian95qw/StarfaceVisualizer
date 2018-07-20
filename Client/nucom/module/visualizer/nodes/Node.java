package nucom.module.visualizer.nodes;

import graph.VisNode;
import nucom.module.visualizer.utility.EnumHelper.NodeType;

public abstract class Node 
{	
    private long id;
	private VisNode VN = null;	
	private NodeType NT = NodeType.NONE;
	
	private static long nextID=0;
	
	public Node(String label, String image, NodeType NT)
	{
		id=nextID;
		nextID++;
		this.NT=NT;
		VN = new VisNode(id, label, image);
	}
	
	public NodeType getNT() {
		return NT;
	}
	
	public long getId() {
		return id;
	}

	public String getIdasString()
	{
		return String.valueOf(id);
	}
	
	public VisNode getVN() {
		return VN;
	}

	public void setLabel(String label)
	{
		VN.setLabel(label);
	}
}
