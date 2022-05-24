package controller;

import java.io.IOException;
import java.util.ArrayList;

import exception.NotFoundUserNameException;
import model.dto.Chat;
import model.dto.User;
import service.Model;
import view.ChatView;

public class Controller {
	
	// view에서 받아서 Model로 보내기
	
	private static Model service = Model.getInstance();
	
	public static int request(String id, String in) throws IOException {
		if(in.equals("e")) {
			return -1;			
		}else if(in.equals("users")) {
			getMyChat(id);
			return 1;
		}else if(in.equals("chats")) {
			return 2;
		}
		else if(in.equals("c")) {
			return 3;
		}
		return 0;
	}
	
	public static ArrayList<User> getUsers() {
		return service.getUsers();
	}
	
	// 모든 chatting 검색
	public static ArrayList<Chat> getChats() {
		return service.getChats();
	}
	
	// 채팅 추가
	public static void addChat(String writer, String content) throws IOException {
		service.addChat(writer, content);
	}
	
	// 유저 추가
	public static void addUser(String userName, String password, String email) throws IOException {
		service.addUser(userName, password, email);
	}

	public static int chatRequest(String writer, String content) throws IOException {
		ChatView.printChatAll(writer);
		if(content.equals("EndChat")) {
			return request("", "e");
		}else {
			addChat(writer, content);
			ChatView.printChatAll(writer);
		}
		return 0;
	}
	
	public static String getSession(String userName, String password){
		try {
			return service.getSession(userName, password);
		} catch (NotFoundUserNameException e) {
			e.printStackTrace();
			return e.getMessage();
		}
	}
	
	public static void getMyChat(String id) throws IOException {
		service.getMyChat(id);
	}
	
	public static void getChatByDay(String id, String day) throws IOException {
		service.getChatByDay(id, day);
	}
	

}
