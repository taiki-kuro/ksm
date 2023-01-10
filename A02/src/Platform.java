

import java.util.ArrayList;
import java.util.List;

public class Platform {

	private List<User> userList = new ArrayList<User>();

	private List<Work> workList = new ArrayList<Work>();

    public void addUser(User user) {
		this.userList.add(user);
	}

    public User getUserById(String id) {
		for(User user: this.userList) {
			if(user.getStudentId().equals(id)) {
				return user;
			}
		}
		return null;
	}

	public List<User> getUserList() {
		return this.userList;
	}

    public void addWork(Work work) {
		this.workList.add(work);
	}

	public List<Work> getWorkList() {
		return this.workList;
	}

	
}
