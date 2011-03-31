package mobile.team4.game;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;

import android.util.Log;

/**
 * Server will handle all communication with the server. Here you
 * will make requests to send, update, receive information from
 * the server. This includes player information, game information,
 * coordinates and sources of shots that were fired, and wall
 * information.
 *  
 * @author Martin Brown
 *
 */
public class Server 
{	
	/**
	 * This is the base URL for the server
	 */
	private static final String BASE_URL = "http://droidelicious.com/";
	
	/**
	* Source: http://moazzam-khan.com/blog/?p=446
	* HttpGet - doesn't read cookies
	*
	* @param sUrl	
	* @return
	*/
	private String getUrlData(String sUrl) throws Exception
	{
		String str;
		StringBuffer buff = new StringBuffer();
		URL url = new URL(sUrl);
	
		BufferedReader in = new BufferedReader(new InputStreamReader(url.openStream()));
		
		Log.i("getUrl","url = " + sUrl);
		while ((str = in.readLine()) != null) 
		{
			Log.i("getUrl",str);
			buff.append(str);
		}
		
		return buff.toString();
	}
}
