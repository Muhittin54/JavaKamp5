package JavaKamp5.business.abstracts;

import java.util.List;

import JavaKamp5.entities.concretes.User;

public interface UserService{
	void add(User user);
	void update(User user);
	void delete(User user);
	void loginWithGoogle(User user);
	void Login(String email, String password);
	User get(int id);
	List<User> getAll();
}
