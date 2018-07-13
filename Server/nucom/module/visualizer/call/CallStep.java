package nucom.module.visualizer.call;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import org.apache.commons.logging.Log;

import com.google.gson.GsonBuilder;

import de.starface.ch.processing.bo.api.types.Call;
import de.starface.ch.processing.bo.api.types.CallState;
import de.starface.ch.processing.bo.api.types.ParticipationInfo;

public class CallStep 
{
	private Call C = null;
	private CallState State = null;
	private Set<ParticipationInfo> Called=null;
	private Log log = null;
	private Date D = null;
	
	public CallStep(Call C, Log log)
	{
		this.log=log;
		this.D = new Date();
		if(C != null)
		{
			this.C=C;
			this.State=C.getState();
			Called = C.getCalledInfo();
		}
		else
		{
			State = CallState.COMPLETED;
			Called = new HashSet<ParticipationInfo>();
		}
		
	}
	
	@Override 
	public boolean equals(Object o)
	{
		if(o == null)
		{
			return false;
		}
		CallStep CS= null;
		try
		{
			CS = (CallStep) o;
		}
		catch(Exception e)
		{
			return false;
		}
		
		if(!State.equals(CS.getCallState()))
		{
			return false;
		}
		
		
		if(Called.size() == 0 && CS.getCalled().size() != 0)
		{
			return false;
		}
		
		for(ParticipationInfo PC1 : Called)
		{
			boolean Found =false;
			for(ParticipationInfo PC2 : CS.getCalled())
			{
				if(PC1.getSipCallId().equals(PC2.getSipCallId()))
				{
					Found = true;
					break;
				}
			}
			if(!Found)
			{
				log.debug("CallLeg not Found!");
				return false;
			}
		}
		
		for(ParticipationInfo PC1 : Called)
		{
			boolean Found =false;
			for(ParticipationInfo PC2 : CS.getCalled())
			{
				if(PC1.getCallerId().getCallerName().equals(PC2.getCallerId().getCallerName()))
				{
					Found = true;
					break;
				}
			}
			if(!Found)
			{
				log.debug("CallParticipant not Found!");
				return false;
			}
		}
		
		return true;
	}
	
	@Override
	public String toString()
	{
		StringBuilder SB = new StringBuilder();
		
		SB.append(System.lineSeparator());
		SB.append("############# CALL-INFO ##############");
		SB.append(System.lineSeparator());
		
		SB.append(C.getId().toString());
		SB.append(System.lineSeparator());
		
		SB.append("State:" + State.toString());
		SB.append(System.lineSeparator());
		
		for(ParticipationInfo PC : Called)
		{
			SB.append("------- Participant -------");
			SB.append(System.lineSeparator());
			
			SB.append("ID:"  + PC.getSipCallId());
			SB.append(System.lineSeparator());

			SB.append("Participant: " + PC.getCallerId().getCallerName());
			SB.append(System.lineSeparator());
			
			SB.append("Number: " + PC.getCallerId().getCallerNumber());
			SB.append(System.lineSeparator());
			
			SB.append("Peer: " + PC.getPeerName());
			SB.append(System.lineSeparator());
			SB.append("---------------------------");
			SB.append(System.lineSeparator());
		}
			
		SB.append("######### END-CALL-INFO ##############");
		return SB.toString();
	}
	
	public Set<ParticipationInfo> getCalled() 
	{
		return Called;
	}

	public CallState getCallState() 
	{
		return State;
	}
	
	public String getDate()
	{
		SimpleDateFormat SDF = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss:SSSS");
		return SDF.format(D);	
	}
	
	public String toJson()
	{
		GsonBuilder GB = new GsonBuilder();
		GB.registerTypeAdapter(CallStep.class, new CallStepAdapter());
		return GB.create().toJson(this);
	}
	
}
