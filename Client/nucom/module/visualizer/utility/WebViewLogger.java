package nucom.module.visualizer.utility;

import com.sun.javafx.webkit.WebConsoleListener;

@SuppressWarnings("restriction")
public class WebViewLogger 
{
	private Log log = null;
	public WebViewLogger()
	{
		log = new Log(this.getClass());
		
		WebConsoleListener.setDefaultListener((webView, message, lineNumber, sourceId) -> 
		{
            log.debug("Message: " + message +" at Line: " + lineNumber);
        });
	}
	
}
