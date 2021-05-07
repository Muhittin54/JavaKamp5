package JavaKamp5.business.concretes;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import JavaKamp5.business.abstracts.EmailValidationService;
import JavaKamp5.business.abstracts.UserService;
import JavaKamp5.core.sendMail.abstracts.SendEmailService;
import JavaKamp5.core.signUpGoogle.abstracts.AccountService;
import JavaKamp5.dataAccess.abstracts.UserDao;
import JavaKamp5.entities.concretes.User;

public class UserManager implements UserService, EmailValidationService {

	private UserDao userDao;
	private SendEmailService sendEmailService;
	private AccountService googleService;

	public UserManager(UserDao userDao, SendEmailService sendEmailService, AccountService googleService) {
		super();
		this.userDao = userDao;
		this.sendEmailService = sendEmailService;
		this.googleService = googleService;
	}

	@Override
	public void add(User user) {
		if (emailValidation(user.getEmail().trim()) && checkEmail(user.getEmail())
				&& user.getPassword().trim().length() > 5 && user.getFirstName().trim().length() > 1
				&& user.getLastName().trim().length() > 1) {

			this.userDao.add(user);
			user.setEmailActivation(false);
			if (this.sendEmailService.sendEmail(user.getEmail().trim())) {
				confirmMail(user.getEmail().trim());
			}
			return;
		} else if (!(emailValidation(user.getEmail().trim()))) {
			System.out.println("Lutfen gecerli bir eposta adresi giriniz: " + user.getEmail());
		} else if (!(checkEmail(user.getEmail().trim()))) {
			System.out.println("Eposta adresi sistemde kayitli lutfen giris yapiniz: " + user.getEmail());
		} else if (user.getFirstName().trim().length() <= 1) {
			System.out.println("Isminiz 1 karakterden buyuk olmali");
		} else if (user.getLastName().trim().length() <= 1) {
			System.out.println("Soyisminiz 1 karakterden buyuk olmali");
		} else if (user.getPassword().trim().length() <= 5) {
			System.out.println("Sifreniz minimum 6 karakterden olusmalidir");
		} else {
			System.out.println("Lutfen ilgili alanlari bos birakmayiniz!");
		}

	}

	@Override
	public void update(User user) {
		this.userDao.update(user);
	}

	@Override
	public void delete(User user) {
		this.userDao.delete(user);

	}

	@Override
	public boolean emailValidation(String email) {
		String regex = "^[\\w]+(?:\\.[\\w]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$";
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(email);

		return matcher.matches();
	}

	@Override
	public boolean checkEmail(String email) {
		for (User user : getAll()) {
			if (email.equals(user.getEmail())) {
				return false;
			} else
				return true;
		}

		return true;
	}

	@Override
	public void confirmMail(String email) {
		for (User user : getAll()) {
			if (email.equals(user.getEmail())) {
				user.setEmailActivation(true);
				System.out.println(email + " adresiniz dogrulandi. Giris yapabilirsiniz");
			}
		}
	}

	@Override
	public void loginWithGoogle(User user) {
		if (this.googleService.register(user))
			this.googleService.login(user);
		else
			System.out.println("Google ile giris yapilamadi");
	}

	@Override
	public void Login(String email, String password) {
		if (email.trim() != null && password.trim() != null) {
			for (User user : getAll()) {
				if (email.trim().equals(user.getEmail()) && password.trim().equals(user.getPassword())
						&& user.isEmailActivation()) {
					System.out.println("Hosgeldin " + user.getFirstName());
					return;
				} else if (!(user.isEmailActivation()) && email.trim().equals(user.getEmail())) {
					System.out.println("Eposta adresinizi onaylamadiginiz icin giris isleminiz basarisiz.");
					return;
				} else {
					System.out.println("Sisteme kaydiniz yok ya da email veya sifre yanlis");
					return;
				}
			}
		}
	}

	@Override
	public User get(int id) {
		return this.userDao.get(id);
	}

	@Override
	public List<User> getAll() {
		return this.userDao.getAll();
	}
}
