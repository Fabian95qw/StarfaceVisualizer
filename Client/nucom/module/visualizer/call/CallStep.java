package nucom.module.visualizer.call;

import java.util.List;

import nucom.module.visualizer.utility.Log;

public class CallStep 
{
	private String CallState = "";
	private String DateTime ="";
	private List<Peer> Peers = null;
	private Log log = null;
	
	public CallStep()
	{
		log = new Log(this.getClass());
	}
	
	public void setCallState(String CallState)
	{
		this.CallState=CallState;
		log.debug("CallState: " + CallState);
	}

	public void setDateTime(String DateTime) 
	{
		this.DateTime=DateTime;
		log.debug("DateTime: " + DateTime);
	}

	public void setPeers(List<Peer> Peers) 
	{
		log.debug("Peers: " + Peers.size());
		this.Peers=Peers;		
	}

	public String getCallState() {
		return CallState;
	}

	public String getDateTime() {
		return DateTime;
	}

	public List<Peer> getPeers() {
		return Peers;
	}
	
	

}
