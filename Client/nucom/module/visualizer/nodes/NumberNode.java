package nucom.module.visualizer.nodes;

import nucom.module.visualizer.utility.EnumHelper.NodeType;

public class NumberNode extends Node
{
	public NumberNode(String label) 
	{
		super(label, "icons/number.png", NodeType.NUMBER);
	}	
}
