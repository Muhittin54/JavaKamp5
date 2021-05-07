package JavaKamp5;

import java.util.Scanner;

import JavaKamp5.business.abstracts.UserService;
import JavaKamp5.business.concretes.UserManager;
import JavaKamp5.core.sendMail.concretes.SendEmailManager;
import JavaKamp5.core.signUpGoogle.concretes.GoogleManager;
import JavaKamp5.dataAccess.concretes.HibernateUserDao;
import JavaKamp5.entities.concretes.User;

public class Main {

	public static void main(String[] args) {

		UserService userService = new UserManager(new HibernateUserDao(), new SendEmailManager(), new GoogleManager());
		User user = new User(1, "Salih", "Salih", "salih@salih.com", "123456");
		User user1 = new User(2, "Halis", "Halis", "halis@halis.com", "123456");
		User user2 = new User(3, "Ayse", "Ayse", "salih@salih.com", "123456");

		// Giris Ekrani
		userService.add(user);
		System.out.println();
		System.out.print("Eposta adresinizi giriniz: ");
		Scanner email = new Scanner(System.in);
		String userEmail = email.next();
		System.out.print("Sifrenizi giriniz: ");
		Scanner password = new Scanner(System.in);
		String userPassword = password.next();

		userService.Login(userEmail, userPassword);

		// Google Giris
		System.out.println();
		userService.loginWithGoogle(user1);
		
		// Ayni eposta ile giris
		System.out.println();
		userService.add(user2);

	}

}
