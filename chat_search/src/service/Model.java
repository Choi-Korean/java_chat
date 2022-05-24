package service;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import exception.NotFoundDayException;
import exception.NotFoundUserNameException;
import model.dto.Chat;
import model.dto.User;
import view.ChatView;


public class Model {

		private static Model instance = new Model();
		
		private ArrayList<User> users = User.getUserAll();
		private ArrayList<Chat> chats = Chat.getChatAll();
		
		private Model() {}
		
		public static Model getInstance() {
			return instance;
		}
		
		// 모든 user 검색
		public ArrayList<User> getUsers() {
			return Model.getInstance().users;
		}
		
		// 모든 chatting 검색
		public ArrayList<Chat> getChats() {
			return Model.getInstance().chats;
		}
		
		// 채팅 추가
		public void addChat(String writer, String content) throws IOException {
			LocalDateTime now = LocalDateTime.now();
			String formatedNow = now.format(DateTimeFormatter.ofPattern("yyyy년 MM월 dd일 HH시 mm분"));

			Chat c1 = Model.getInstance().chats.get(Model.getInstance().chats.size() - 1);
			Chat c2 = new Chat((Integer.parseInt(c1.getId()) + 1) + "", writer, content, formatedNow, "1");
			Model.getInstance().chats.add(c2);
			Chat.setChatAll(Model.getInstance().chats);	
		}
		
		// 유저 추가
		public void addUser(String userName, String password, String email) throws IOException {
			User c1 = Model.getInstance().users.get(Model.getInstance().users.size() - 1);
			User c2 = new User((Integer.parseInt(c1.getId()) + 1) + "", userName, password, email);
			Model.getInstance().users.add(c2);
			User.setUserAll(Model.getInstance().users);	
		}
		
		
		// 로그인 session 부여
		public String getSession(String userName, String password) throws NotFoundUserNameException{
			for(User user:users) {
				if(user.getUserName().equals(userName) && user.getPassword().equals(password)) {
					return user.getId();
				}
			}
			throw new NotFoundUserNameException("아이디 혹은 비밀번호가 틀립니다.");
		}

		public void getMyChat(String id) throws IOException {
			ChatView.printChatById(id);
		}

		public void getChatByDay(String id, String day) throws IOException {
			
			try {
				ChatView.printChatByDate(id, day);
			} catch (IOException e) {
				e.printStackTrace();
			} catch (NotFoundDayException e) {
				e.printStackTrace();
				System.out.println(e.getMessage());
			}
		}

}
