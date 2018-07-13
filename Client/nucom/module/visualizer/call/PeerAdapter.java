package nucom.module.visualizer.call;

import java.io.IOException;

import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;

import nucom.module.visualizer.utility.EnumHelper.PeerData;

public class PeerAdapter extends TypeAdapter<Peer>
{

	@Override
	public Peer read(JsonReader JR) throws IOException 
	{
		Peer P = new Peer();
		JR.beginObject();
		while(JR.hasNext())
		{
			PeerData PD = PeerData.valueOf(JR.nextName());
			switch(PD)
			{
			case Callednumber:
				P.setCallednumber(JR.nextString());
				break;
			case Calleduser:
				P.setCalleduser(JR.nextString());
				break;
			case Peer:
				P.setPeer(JR.nextString());
				break;
			default:
				break;
			}
		}
		JR.endObject();
		return P;
	}

	@Override
	public void write(JsonWriter arg0, Peer arg1) throws IOException {}

}
