package model.dto;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import lombok.Data;

@Data
public class Chat{
	
	private String id;
	private String writer;
	private String content;
	private String creation;
	private String status;
	
	public Chat(String id, String writer, String content, String creation, String status) {
		super();
		this.id = id;
		this.writer = writer;
		this.content = content;
		this.creation = creation;
		this.status = status;
	}
	
	
	public static Object stringToJson(String v) {
			
			// JSON 포멧과 관련된 작업 - 문법 검증 및 변환
			JSONParser jsonParser = new JSONParser();
	         
	        try {
	        	// 문자열 타입을 JSON 구조의 객체로 변환
	        	// JSONObject : key로 value 구분해서 활용 가능하게 하는 API
				JSONObject jsonObj = (JSONObject)jsonParser.parse(v);	
				return jsonObj;
				
			} catch (ParseException e) {
				return e;
			}
		}
	
	
	public static ArrayList<Chat> getChatAll(){
		
		ArrayList<Chat> c = new ArrayList<Chat>();
		
		try {
			BufferedReader in = new BufferedReader(new FileReader("D:\\AIJavaProgramming\\lab\\20220509\\01.Java\\java_chat\\chat_search\\src\\database\\chat.json"));
			StringBuilder sb = new StringBuilder();
			String readData = in.readLine();
			
			while(readData != null) {
				sb.append(readData);
				readData = in.readLine();
			}
			
			JSONObject o = (JSONObject)stringToJson(sb.toString());
			JSONObject now = null;
			
			for(int i = 1; i < o.size() + 1; i ++){
				now = (JSONObject)o.get("" + i);
				c.add(new Chat(now.get("id").toString(), now.get("writer").toString(), now.get("content").toString(), now.get("creation").toString(), now.get("status").toString()));
			}
			return c;
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return c;
	}
	
	public static void setChatAll(ArrayList<Chat> chats) throws IOException{
		BufferedWriter out = new BufferedWriter(new FileWriter(".\\src\\database\\chat.json"));
		
		int chatsSize = chats.size();
		
		out.write("{");
		
		for(Chat chat:chats) {
			out.write("  \"" + chat.getId() + "\": {\n");
			out.write("\"id\": \"" + chat.getId() + "\",\n");
			out.write("\"writer\": \"" + chat.getWriter() + "\",\n");
			out.write("\"content\": \"" + chat.getContent() + "\",\n");
			out.write("\"creation\": \"" + chat.getCreation() + "\",\n");
			out.write("\"status\": \"" + chat.getStatus() + "\"\n  }");
			
			chatsSize -= 1;
			if(chatsSize != 0) {
				out.write(",\n");
			}else {
				out.write("\n");
			}
		}
		
		out.write("}");
		
		if(out != null) {
			out.close();
			out = null;
		}
	
	}
}
