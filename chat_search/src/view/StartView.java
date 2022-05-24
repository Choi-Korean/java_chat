package view;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import controller.Controller;
import model.dto.User;


public class StartView {

	public static void main(String[] args) throws IOException{
		
		// 받기만
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		BufferedReader chat = new BufferedReader(new InputStreamReader(System.in));
		
		int response;
		int chatResponse;
		String session = "-1";
		String userName;
		String password;
		
		while(session.equals("-1")) {
			
			System.out.println("----------로그인 해주세요. 끝내실 경우 e ------------");
			System.out.print("ID : ");
			userName = in.readLine();
			System.out.println();
			System.out.print("PASSWORD : ");
			password = in.readLine();
			System.out.println();
			
			if(userName.equals("e") || password.equals("e")) {
				break;
			}else {
				session = Controller.getSession(userName, password);
			}
			
		}
		
		// login된 경우에만 진행
		if(!session.equals("-1")) {
			User user = Controller.getUsers().get(Integer.parseInt(session) - 1);
			System.out.println("----------------------");
			System.out.println(user.getUserName() + "님 환영합니다.");
			
			while(true) {
				System.out.println("----------------------");
				System.out.println("(1) 내 채팅 검색 : users\n"
						+ "(2) 일별 채팅 검색 : chats\n"
						+ "(3) 채팅하기 : c\n"
						+ "(4) 끝내기 : e\n");
				
				response = Controller.request(user.getId(), in.readLine());
				
				if(response == -1) {
					break;
					
				}else if(response == 2) {
					System.out.println("검색하실 날짜(일)을 입력하세요.");
					Controller.getChatByDay(user.getId(), in.readLine());
					
				}else if(response == 3) {
					System.out.println("채팅방에 입장하였습니다. 채팅을 끝내려면 \"EndChat\"을 입력해주세요.");
					chatResponse = Controller.chatRequest(user.getId(), in.readLine());
					
					while(true) {
						
						if(chatResponse == -1) {
							break;
						}
						chatResponse = Controller.chatRequest(user.getId(), in.readLine());
					}
				}
				
				in = new BufferedReader(new InputStreamReader(System.in));
			}
			
		}
		
		
		

	}

}
