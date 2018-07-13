package nucom.module.visualizer.entrypoint;

import org.apache.commons.logging.Log;

import de.starface.core.component.StarfaceComponentProvider;
import de.vertico.starface.module.core.model.Visibility;
import de.vertico.starface.module.core.runtime.IAGIJavaExecutable;
import de.vertico.starface.module.core.runtime.IAGIRuntimeEnvironment;
import de.vertico.starface.module.core.runtime.annotations.Function;
import nucom.module.visualizer.call.CallFollower;


@Function(name="Visualizer.Incoming",visibility=Visibility.Private, rookieFunction=false, description="")
public class IncomingCallEventEntry implements IAGIJavaExecutable
{
	//##########################################################################################
	
    StarfaceComponentProvider componentProvider = StarfaceComponentProvider.getInstance(); 
    //##########################################################################################
	
	//###################			Code Execution			############################	
	@Override
	public void execute(IAGIRuntimeEnvironment context) throws Exception 
	{
		Log log = context.getLog();
		log.debug("Incoming Call...");
		
		CallFollower CF = new CallFollower(context);
		Thread T = new Thread(CF);
		T.start();
		
		Thread.sleep(100);

	}//END OF EXECUTION

	
}
