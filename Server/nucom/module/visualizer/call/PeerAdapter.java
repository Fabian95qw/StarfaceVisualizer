package nucom.module.visualizer.call;

import java.lang.reflect.Type;
import java.util.Optional;

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
		JO.addProperty(PeerData.Accountid.toString(), Optional.ofNullable(PI.getAccountId().toString()).orElse(""));
		JO.addProperty(PeerData.Calleduser.toString(), Optional.ofNullable(PI.getCallerId().getCallerName()).orElse(""));
		JO.addProperty(PeerData.Callednumber.toString(),  Optional.ofNullable(PI.getCallerId().getCallerNumber()).orElse(""));
		JO.addProperty(PeerData.Peer.toString(), Optional.ofNullable(PI.getPeerName()).orElse(""));
		return JO;
	}

	public JsonElement asJsonObject(ParticipationInfo PI) 
	{
		JsonObject JO = new JsonObject();
		
		JO.addProperty(PeerData.Accountid.toString(), Optional.ofNullable(PI.getAccountId().toString()).orElse(""));
		JO.addProperty(PeerData.Calleduser.toString(), Optional.ofNullable(PI.getCallerId().getCallerName()).orElse(""));
		JO.addProperty(PeerData.Callednumber.toString(),  Optional.ofNullable(PI.getCallerId().getCallerNumber()).orElse(""));
		JO.addProperty(PeerData.Peer.toString(), Optional.ofNullable(PI.getPeerName()).orElse(""));
		return JO;
	}

}
