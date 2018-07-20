package nucom.module.visualizer.utility;

public class EnumHelper
{
	public enum CallData
	{
		DateTime,
		CallState,
		Peers
	}

	public enum PeerData
	{
		Accountid,
		Calleduser,
		Callednumber,
		Peer
	}
	
	public enum NodeType
	{
		NONE,
		ENTRYPOINT,
		USER,
		GROUP,
		MODULE,
		VOICEMAIL,
		NUMBER,
		EDGE, 
		PHONE, UNKNOWN
	}
	
}

