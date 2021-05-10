package client;

import java.util.List;

import webService.IUserWS;
import webService.User;
import webService.UserWSService;

public class UsersClient {
	
	static UserWSService service = new UserWSService();
	static IUserWS usersService = service.getUserWSPort();
	
	public static List<User> getUsers()
	{		
		return usersService.readAllUsers();
	}
	
	public static User authenticate(String username, String password)
	{	
		List<User> users = usersService.findByUsernamePassword(username, password);
		if (users.isEmpty()) {
			return null;
		} else {
			return users.get(0);
		}
	}
	
	public static boolean exists(String username)
	{	
		List<User> users = usersService.findByUsername(username);
		if (users.isEmpty()) {
			return false;
		} else {
			return true;
		}
	}
	
	public static User getUserByUsername(String username)
	{	
		List<User> users = usersService.findByUsername(username);
		return users.get(0);
	}
	
	public static boolean create(User user)
	{	
		return usersService.createUser(user);
	}

	public static User getUser(String id) {		
		return usersService.readUser(id);
	}

	public static boolean removeUser(String id) {
		return usersService.deleteUser(id);
		
	}

	public static void modify(User user) {
		usersService.updateUser(user);
		
	}
}
