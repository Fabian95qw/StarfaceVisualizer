package nucom.module.visualizer.call;

public class Peer 
{
	private String Callednumber ="";
	private String Calleduser ="";
	private String Peer ="";
	
	public void setCallednumber(String Callednumber) 
	{
		this.Callednumber=Callednumber;
	}

	public void setCalleduser(String Calleduser) 
	{
		this.Calleduser=Calleduser;
	}

	public void setPeer(String Peer) 
	{
		this.Peer=Peer;
	}

	public String getCallednumber() {
		return Callednumber;
	}

	public String getCalleduser() {
		return Calleduser;
	}

	public String getPeer() {
		return Peer;
	}

	
}
