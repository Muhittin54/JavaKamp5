package JavaKamp5.core.signUpGoogle.abstracts;

import JavaKamp5.entities.concretes.User;

public interface AccountService {
	void login(User user);
	boolean register(User user);
}
