package nucom.module.visualizer.call;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;

import nucom.module.visualizer.utility.EnumHelper.CallData;
import nucom.module.visualizer.utility.Log;
import nucom.module.visualizer.utility.LogHelper;

public class CallStepAdapter extends TypeAdapter<CallStep> 
{
	private Log log = null;
	public CallStepAdapter() 
	{
		log = new Log(this.getClass());
	}
	
	@Override
	public CallStep read(JsonReader JR) throws IOException 
	{
		CallStep CS = new CallStep();
		CallData CD = null;
		JR.beginObject();
		List<Peer> Peers = new ArrayList<Peer>();
		GsonBuilder GB = new GsonBuilder();
		GB.registerTypeAdapter(Peer.class, new PeerAdapter());
		Gson GS = GB.create();
		Peer P = null;
		
		while(JR.hasNext())
		{
			CD = CallData.valueOf(JR.nextName());
			log.debug(CD.toString());
			switch(CD)
			{
			case CallState:
				CS.setCallState(JR.nextString());
				break;
			case DateTime:
				CS.setDateTime(JR.nextString());
				break;
			case Peers:
				String S = JR.nextString();
				JsonParser JP = new JsonParser();
				JsonElement JE = JP.parse(S);
				JsonArray JA = JE.getAsJsonArray();
				
				log.debug(S);
				try
				{
					for(JsonElement Peer: JA)
					{
						P = GS.fromJson(Peer, Peer.class);
						Peers.add(P);
					}
				}
				catch(Exception e)
				{
					LogHelper.EtoStringLog(log, e);
				}
				CS.setPeers(Peers);
				
				break;
			}
		}
		JR.endObject();
		return CS;
	}

	@Override
	public void write(JsonWriter arg0, CallStep arg1) throws IOException
	{
		
	}

}
