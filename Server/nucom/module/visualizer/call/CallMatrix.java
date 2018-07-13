package nucom.module.visualizer.call;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;

public class CallMatrix 
{	
	private Log log = null;
	private List<CallStep> CallSteps = null;
	
	public CallMatrix(Log log)
	{
		this.log=log;
		log.debug("[CM] Ready");
		CallSteps= new ArrayList<CallStep>();
	}

	public void addStep(CallStep CS) 
	{
		if(CallSteps.size() >0)
		{
			if(!CS.equals(CallSteps.get(CallSteps.size()-1)))
			{
				log.debug("Added new CallStep");
				CallSteps.add(CS);
			}
		}
		else
		{
				log.debug("Added new CallStep");
				CallSteps.add(CS);
		}
	}

	public List<CallStep> getSteps() 
	{
		return CallSteps;
	}
}
