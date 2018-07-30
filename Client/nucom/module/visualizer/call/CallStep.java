package nucom.module.visualizer.call;

import java.util.List;

import nucom.module.visualizer.utility.Log;
import nucom.module.visualizer.utility.EnumHelper.CallState;
import nucom.module.visualizer.utility.EnumHelper.TypeData;

public class CallStep 
{
	private CallState State = CallState.UNKNOWN;
	private String DateTime ="";
	private TypeData T= TypeData.UNKNOWN;
	private List<Peer> Peers = null;
	private Log log = null;
	
	public CallStep()
	{
		log = new Log(this.getClass());
	}
	
	public void setCallState(CallState State)
	{
		this.State=State;
		log.debug("CallState: " + State.toString());
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

	public CallState getCallState() {
		return State;
	}

	public String getDateTime() {
		return DateTime;
	}

	public List<Peer> getPeers() {
		return Peers;
	}

	public void setType(TypeData T) 
	{
		log.debug("Type: " +T.toString());
		this.T=T;
	}

	public TypeData getT() {
		return T;
	}
	
	

}
