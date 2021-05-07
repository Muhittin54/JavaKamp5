package JavaKamp5.core.signUpGoogle.concretes;

import JavaKamp5.core.signUpGoogle.abstracts.AccountService;
import JavaKamp5.entities.concretes.User;

public class GoogleManager implements AccountService {

	@Override
	public void login(User user) {
		System.out.println(user.getEmail() + " Google girisi basarili");

	}

	@Override
	public boolean register(User user) {
		if (user.getEmail().trim() != null) {
			System.out.println(user.getEmail() + " Google ile kayit basarili");
			return true;
		}
		return false;
	}

}
