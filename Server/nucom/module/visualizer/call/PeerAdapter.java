package nucom.module.visualizer.call;

import java.lang.reflect.Type;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

import de.starface.ch.processing.bo.api.types.ParticipationInfo;
import nucom.module.visualizer.utility.EnumHelper.PeerData;

public class PeerAdapter implements JsonSerializer<ParticipationInfo>
{

	@Override
	public JsonElement serialize(ParticipationInfo PI, Type type, JsonSerializationContext context) 
	{
		JsonObject JO = new JsonObject();
		
		JO.addProperty(PeerData.Calleduser.toString(), PI.getCallerId().getCallerName());
		JO.addProperty(PeerData.Callednumber.toString(),  PI.getCallerId().getCallerNumber());
		JO.addProperty(PeerData.Peer.toString(), PI.getPeerName());
		return JO;
	}

	public JsonElement asJsonObject(ParticipationInfo PI) 
	{
		JsonObject JO = new JsonObject();
		
		JO.addProperty(PeerData.Calleduser.toString(), PI.getCallerId().getCallerName());
		JO.addProperty(PeerData.Callednumber.toString(),  PI.getCallerId().getCallerNumber());
		JO.addProperty(PeerData.Peer.toString(), PI.getPeerName());
		return JO;
	}

}
