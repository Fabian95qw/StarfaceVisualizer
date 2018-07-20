package nucom.module.visualizer.nodes;

import nucom.module.visualizer.utility.EnumHelper.NodeType;

public class UnknownNode extends Node
{
	public UnknownNode(String label) 
	{
		super(label, "icons/unknown.png", NodeType.UNKNOWN);
	}	
}
