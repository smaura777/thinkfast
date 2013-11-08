package com.thinkfast.app;
import java.util.*;
import java.io.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.*;



/**
 * Servlet implementation class AccountManagerServlet
 */
@WebServlet({ "/AccountManagerServlet", "/account/*" })
public class AccountManagerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AccountManagerServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		PrintWriter out = response.getWriter();
		response.setContentType("application/json");
		/**
		StringBuilder json = new StringBuilder();
		json.append("{");
		json.append("\"status_code\":");
		json.append("\"201\"");
		json.append("}");
		out.print(json.toString());
	**/
	
		
		
		AppJSONResponse details = new AppJSONResponse();
		details.setKeyValue("query_string", request.getQueryString());
		details.setKeyValue("remote_host", request.getRemoteHost());
		details.setKeyValue("rquest_uri", request.getRequestURI());
		details.setKeyValue("path_info", request.getPathInfo());
		details.setKeyValue("encoding", request.getCharacterEncoding());
		details.setKeyValue("address", request.getRemoteAddr());
		
		AppJSONResponse details2 = new AppJSONResponse();
		details2.setKeyValue("query_string", request.getQueryString());
		details2.setKeyValue("remote_host", request.getRemoteHost());
		details2.setKeyValue("rquest_uri", request.getRequestURI());
		details2.setKeyValue("path_info", request.getPathInfo());
		details2.setKeyValue("encoding", request.getCharacterEncoding());
		details2.setKeyValue("address", request.getRemoteAddr());
		
		AppJSONResponse resp = new AppJSONResponse();
		
		resp.setKeyValue("request", details);
		
		resp.setKeyValue("request2", details2);
		resp.setKeyValue("status_code", "200");
		
		List<String> items = new ArrayList<String>();
		items.add("7186290750" );
		items.add("6465781471");
		items.add("7185687811" );
		items.add("6465781771");
		resp.setKeyValue("AddressBook", items);
		
		List<AppJSONResponse> items2 = new ArrayList<AppJSONResponse>();
		AppJSONResponse user1 = new AppJSONResponse();
		user1.setKeyValue("id", "df4332");
		user1.setKeyValue("name", "duck912");
		user1.setKeyValue("email", "sean@gmail.com");
		user1.setKeyValue("savings", "$10,000");
		items2.add(user1);
		
		AppJSONResponse user2 = new AppJSONResponse();
		user2.setKeyValue("id", "ac1032");
		user2.setKeyValue("name", "kicker912");
		user2.setKeyValue("email", "paul@gmail.com");
		user2.setKeyValue("savings", "$20,000");
		items2.add(user2);
		
		AppJSONResponse user3 = new AppJSONResponse();
		user3.setKeyValue("id", "ws1454");
		user3.setKeyValue("name", "doormat234");
		user3.setKeyValue("email", "chin@gmail.com");
		user3.setKeyValue("savings", "$40,000");
		items2.add(user3);
		
		resp.setKeyValue("clients",items2, true);
		
		
		out.print(resp.getJSON());
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPut(HttpServletRequest, HttpServletResponse)
	 */
	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doDelete(HttpServletRequest, HttpServletResponse)
	 */
	protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doHead(HttpServletRequest, HttpServletResponse)
	 */
	protected void doHead(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}

 class AppJSONResponse implements Serializable {
	private final static long serialVersionUID = 1993432432L;
	private Map<String,String> nodes;
	private Character open = '{';
	private Character close = '}';
	private Character period = ',';
	private Character colon = ':';
	private Character quote = '"';
	
	public AppJSONResponse(){
		nodes = new HashMap<String,String>();
	}
	
	public void setKeyValue(String key, String value){
		nodes.put(key, ((value == null)? "": value) );
	}
	
	public void setKeyValue(String key,AppJSONResponse value ){
		nodes.put(key, value.getJSON());
	}
	
	public void setKeyValue(String key,List<String> value ){
		StringBuilder tmp = new StringBuilder();
		tmp.append('[');
		int counter = 0;
		for (String item: value	){
			if (counter > 0){
				tmp.append(',');
			}
			
			tmp.append(quote);
			tmp.append(item);
			tmp.append(quote);
			++counter;
		}
		
		tmp.append(']');
		nodes.put(key, tmp.toString());
	}
	
	
	public void setKeyValue(String key,List<AppJSONResponse> value,Boolean dummy ){
		StringBuilder tmp = new StringBuilder();
		tmp.append('[');
		int counter = 0;
		for (AppJSONResponse item: value){
			if (counter > 0){
				tmp.append(',');
			}
	
			tmp.append(item.getJSON());
			++counter;
		}
		
		tmp.append(']');
		nodes.put(key, tmp.toString());
	}
	
	
	
	public String  getJSON(){
		StringBuilder json = new StringBuilder();
		json.append(open);
		int counter = 0;
		
		for (Map.Entry<String,String> entry: nodes.entrySet() ){
			
			if (counter > 0)
				json.append(period);
			
			json.append(quote);
			json.append(entry.getKey());
			json.append(quote);
			json.append(colon);
			String theval = entry.getValue();
			
			if ( (!theval.startsWith("{")) && (!theval.startsWith("[")) )
				json.append(quote);
			
			json.append(theval);
			
			if ( (!theval.startsWith("{")) && (!theval.startsWith("[")) )
				json.append(quote);
			
			++counter;
		}
		
		json.append(close);
		
		
		return json.toString();
	}
	
}
