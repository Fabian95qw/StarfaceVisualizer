package nucom.module.visualizer.call;

import java.lang.reflect.Type;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

import de.starface.ch.processing.bo.api.types.ParticipationInfo;
import nucom.module.visualizer.utility.EnumHelper.CallData;

public class CallStepAdapter implements JsonSerializer<CallStep>
{
	public CallStepAdapter(){}
	
	@Override
	public JsonElement serialize(CallStep CS, Type type, JsonSerializationContext context) 
	{
		JsonObject JO = new JsonObject();
		JO.addProperty(CallData.DateTime.toString(), CS.getDate());
		JO.addProperty(CallData.CallState.toString(), CS.getCallState().toString());
		JsonArray JA = new JsonArray();
		
		for(ParticipationInfo PI : CS.getCalled())
		{
			JA.add(new PeerAdapter().asJsonObject(PI));
		}
		JO.addProperty(CallData.Peers.toString(), JA.toString());
		return JO;
	}
}
