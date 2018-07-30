package nucom.module.visualizer.call;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;


import de.starface.ch.processing.bo.api.types.ParticipationInfo;
import de.vertico.starface.module.core.runtime.IAGIRuntimeEnvironment;
import de.vertico.starface.module.core.runtime.functions.entities.IsGroup;
import nucom.module.visualizer.utility.EnumHelper.CallData;
import nucom.module.visualizer.utility.EnumHelper.TypeData;
import nucom.module.visualizer.utility.LogHelper;

public class CallStepAdapter implements JsonSerializer<CallStep>
{
	private IAGIRuntimeEnvironment Icontext = null;
	public CallStepAdapter(IAGIRuntimeEnvironment context){this.Icontext=context;}
	
	@Override
	public JsonElement serialize(CallStep CS, Type type, JsonSerializationContext context) 
	{
		Log log = Icontext.getLog();
		JsonObject JO = new JsonObject();
		JO.addProperty(CallData.DateTime.toString(), CS.getDate());
		JO.addProperty(CallData.CallState.toString(), CS.getCallState().toString());
				
		JsonArray JA = new JsonArray();
		
		List<Integer> IDs = new ArrayList<Integer>();
		
		for(ParticipationInfo PI : CS.getCalled())
		{
			JA.add(new PeerAdapter().asJsonObject(PI));
			if(!IDs.contains(PI.getAccountId()))
			{
				IDs.add(PI.getAccountId());
			}
		}
		
		log.debug("Total ID's: " + IDs.size());
		
		if(IDs.size() > 1)
		{
			JO.addProperty(CallData.Type.toString(), TypeData.GROUP.toString());
		}
		else if(IDs.size() == 1)
		{
			IsGroup IG = new IsGroup();
			IG.account = new Long(IDs.get(0));
			log.debug("ID: " + IDs.get(0));
			try 
			{
				IG.execute(Icontext);
			} 
			catch (Exception e) 
			{
				LogHelper.EtoStringLog(log, e);
			}
			log.debug("isGroup: "+ IG.isGroup);
			if(IG.isGroup)
			{
				JO.addProperty(CallData.Type.toString(), TypeData.GROUP.toString());
			}
			else
			{
				JO.addProperty(CallData.Type.toString(), TypeData.USER.toString());
			}
		}
		else
		{
			JO.addProperty(CallData.Type.toString(), TypeData.UNKNOWN.toString());
		}
		
		JO.addProperty(CallData.Peers.toString(), JA.toString());
		return JO;
	}
}
