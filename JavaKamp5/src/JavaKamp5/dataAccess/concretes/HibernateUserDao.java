package JavaKamp5.dataAccess.concretes;

import java.util.ArrayList;
import java.util.List;

import JavaKamp5.dataAccess.abstracts.UserDao;
import JavaKamp5.entities.concretes.User;

public class HibernateUserDao implements UserDao {

	List<User> userList = new ArrayList<User>();

	@Override
	public void add(User user) {
		userList.add(user);
		System.out.println("Kisi eklendi: " + user.getFirstName());

	}

	@Override
	public void update(User user) {
		System.out.println("Kisi guncellendi: " + user.getFirstName());

	}

	@Override
	public void delete(User user) {
		userList.remove(user.getId());
		System.out.println("Kisi silindi: " + user.getFirstName());

	}

	@Override
	public User get(int id) {
		return userList.get(id);
	}

	@Override
	public List<User> getAll() {
		return userList;
	}

}
