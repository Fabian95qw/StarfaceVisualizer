package nucom.module.visualizer.nodes;

import nucom.module.visualizer.utility.EnumHelper.NodeType;

public class GroupNode extends Node
{
	public GroupNode(String label) 
	{
		super(label, "icons/group.png", NodeType.GROUP);
	}

}
