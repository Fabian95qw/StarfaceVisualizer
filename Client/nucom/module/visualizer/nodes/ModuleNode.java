package nucom.module.visualizer.nodes;

import nucom.module.visualizer.utility.EnumHelper.NodeType;

public class ModuleNode extends Node
{
	public ModuleNode(String label) 
	{
		super(label, "icons/module.png", NodeType.MODULE);
	}	
}
