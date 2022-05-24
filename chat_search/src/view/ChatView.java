package view;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import controller.Controller;
import exception.NotFoundDayException;
import model.dto.Chat;
import model.dto.User;




public class ChatView {
	
	public static void printChatAll(String id) throws IOException {
		
		try {
			ArrayList<User> users = Controller.getUsers();
			ArrayList<Chat> chats = Controller.getChats();
			
			for(Chat chat:chats) {
				
				System.out.println(users.get(Integer.parseInt(chat.getWriter()) - 1).getUserName() + " " + chat.getCreation() 
				+ "\n" + "\n" + chat.getContent() + "\n----------------------");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void printChatById(String id) throws IOException {
		
		try {
			ArrayList<User> users = Controller.getUsers();
			ArrayList<Chat> chats = Controller.getChats();
			
			for(Chat chat:chats) {
				if(chat.getWriter().equals(id)) {
					
					System.out.println(users.get(Integer.parseInt(chat.getWriter()) - 1).getUserName() + " " + chat.getCreation() 
					+ "\n" + "\n" + chat.getContent() + "\n----------------------");
					
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void printChatByDate(String id, String day) throws IOException, NotFoundDayException {
		
		try {
			ArrayList<User> users = Controller.getUsers();
			ArrayList<Chat> chats = Controller.getChats();
			
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy년 MM월 dd일 HH시 mm분");
			Date date;
			int chatSize = chats.size();

			
			for(Chat chat:chats) {
				
				date = formatter.parse(chat.getCreation());
				
				if(date.getDate() == Integer.parseInt(day)) {
					
					System.out.println(users.get(Integer.parseInt(chat.getWriter()) - 1).getUserName() + " " + chat.getCreation() 
					+ "\n" + "\n" + chat.getContent() + "\n----------------------");
					chatSize -= 1;
					
				}
			}

			if(chatSize == chats.size()) {
				throw new NotFoundDayException("입력하신 날짜의 채팅이 없습니다.");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
		
		


}
