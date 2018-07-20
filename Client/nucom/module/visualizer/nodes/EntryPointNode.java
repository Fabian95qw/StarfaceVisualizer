package nucom.module.visualizer.nodes;

import nucom.module.visualizer.utility.EnumHelper.NodeType;

public class EntryPointNode extends Node
{
	public EntryPointNode(String label) 
	{
		super(label, "icons/entrypoint.png", NodeType.ENTRYPOINT);
	}	
}
