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
public class User {
	
	private String id;
	private String userName;
	private String password;
	private String email;
	
	public User(String id, String userName, String password, String email){
		super();
		this.id = id;
		this.userName = userName;
		this.password = password;
		this.email = email;
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
	
	public static ArrayList<User> getUserAll(){
		
		ArrayList<User> c = new ArrayList<User>();
		
		try {
			BufferedReader in = new BufferedReader(new FileReader("D:\\AIJavaProgramming\\lab\\20220509\\01.Java\\java_chat\\chat_search\\src\\database\\user.json"));
			String readData = in.readLine();
			StringBuilder sb = new StringBuilder();
			
			while(readData != null) {
				sb.append(readData);
				readData = in.readLine();
				}
			
			JSONObject o = (JSONObject)stringToJson(sb.toString());
			JSONObject now = null;
			
			for(int i = 1; i < o.size() + 1; i ++){
				now = (JSONObject)o.get("" + i);
				c.add(new User(now.get("id").toString(), now.get("userName").toString(), now.get("password").toString(), now.get("email").toString()));
			}
			return c;
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return c;
		
	}
	
	public static void setUserAll(ArrayList<User> users) throws IOException{
		BufferedWriter out = new BufferedWriter(new FileWriter(".\\src\\database\\user.json"));
		
		int usersSize = users.size();
		
		out.write("{");
		
		for(User user:users) {
			out.write("  \"" + user.getId() + "\": {\n");
			out.write("\"id\": \"" + user.getId() + "\",\n");
			out.write("\"userName\": \"" + user.getUserName() + "\",\n");
			out.write("\"password\": \"" + user.getPassword() + "\",\n");
			out.write("\"email\": \"" + user.getEmail() + "\"\n  }");
			
			usersSize -= 1;
			if(usersSize != 0) {
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
