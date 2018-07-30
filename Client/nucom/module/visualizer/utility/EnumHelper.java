package nucom.module.visualizer.utility;

public class EnumHelper
{
	public enum CallData
	{
		DateTime,
		CallState,
		Type,
		Peers
	}

	public enum PeerData
	{
		Accountid,
		Calleduser,
		Callednumber,
		Peer
	}
	
	public enum TypeData
	{
		USER,
		GROUP,
		UNKNOWN
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
	
	
	public enum CallState
	{
		UNKNOWN,
		 ATXFER,
		  BUSY,
		  CCBS_ACTIVE,
		  CCBS_AVAILIBLE,
		  CCBS_INACTIVE,
		  COMPLETED,
		  CONFERENCE,
		  CONFERENCE_INACTIVE,
		  CONSULTATION_CALL,
		  CONSULTATION_CALL_CONFERENCE,
		  FORWARDING,
		  LINKED,
		  LOGIN_LOGOUT,
		  MUSICONHOLD_TEST,
		  NEW,
		  PARKED,
		  PROCEEDING,
		  REDIRECTED,
		  RINGING,
		  STANDBY,
		  STANDBY_REDIRECTED,
		  UNPARKED,
		  UP,
		  VOICEMAILBOXLINKED,
		  VOICEMAILMAIN
	}
}

