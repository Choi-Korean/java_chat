package view;

import java.io.IOException;

import model.dto.Chat;
import model.dto.User;

public class StartView {

	public static void main(String[] args) {
		
		try {
			User.getUserAll();
			Chat.getChatAll();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
