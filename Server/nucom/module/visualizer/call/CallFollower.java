package nucom.module.visualizer.call;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;

import de.starface.ch.processing.bo.api.pojo.data.PojoCall;
import de.starface.ch.processing.bo.impl.CallServiceImpl;
import de.starface.ch.processing.model.enums.CallState;
import de.vertico.starface.module.core.runtime.IAGIRuntimeEnvironment;
import nucom.module.visualizer.utility.LogHelper;


public class CallFollower implements Runnable
{
	private IAGIRuntimeEnvironment context = null;
	private Log log = null;
	private CallMatrix CM = null;
	
	public CallFollower(IAGIRuntimeEnvironment context)
	{
		this.context=context;
		log = context.getLog();
		CM = new CallMatrix(log);
	}

	@Override
	public void run() 
	{
		log.debug("[CF] Follwing Call!");
		
		PojoCall PC = context.getModelCall();
		
		CallServiceImpl CIMP = (CallServiceImpl)context.provider().fetch(CallServiceImpl.class);
		
		log.debug(PC.getCallState());
		if(!PC.getCallState().equals(CallState.NEW))
		{
			log.debug("Ignoring Call.");
			return;
		}
		
		CM.addStep(new CallStep(CIMP.getCallById(PC.getCaller().getCallId()), log));
		
		while(CIMP.getCallById(PC.getCaller().getCallId()) != null)
		{			 
			CM.addStep(new CallStep(CIMP.getCallById(PC.getCaller().getCallId()), log));
			
			try 
			{
				Thread.sleep(100);
			} 
			catch (InterruptedException e) 
			{
				LogHelper.EtoStringLog(log, e);
			}
		}
		
		/*
		log.debug("[CF]Result:" );
		
		for(CallStep CS : CM.getSteps())
		{
			log.debug(CS);
		}
		*/
		CM.addStep(new CallStep(null, log));
		
		log.debug("-#-#-#-#-#-#-#-#-#-#-#-#-#-#-#-#-#-#-#-#-#-#");
		
		List<String> GSONs = new ArrayList<String>();
		
		StringBuilder SB = new StringBuilder();
		SB.append(System.lineSeparator());
		
		for(CallStep CS : CM.getSteps())
		{
			SB.append(CS.toJson(context));
			SB.append(System.lineSeparator());
			GSONs.add(CS.toJson(context));
		}
		
		log.debug(SB.toString());
				
	}
}
