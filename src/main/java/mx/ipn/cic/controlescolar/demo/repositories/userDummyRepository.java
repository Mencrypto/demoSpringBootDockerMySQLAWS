package mx.ipn.cic.controlescolar.demo.repositories;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import mx.ipn.cic.controlescolar.demo.models.UserModel;

@Repository
public class userDummyRepository {
	
	private int count = 0;
	private List <UserModel> list = new ArrayList<UserModel>();
	
	
	public userDummyRepository() {
		list = new ArrayList<>();
		list.add(new UserModel(1,"Manuel","Perez","Santos",20));
		list.add(new UserModel(2,"Manuel","Sanchez","Santos",30));
		count = 2;
	}
	
	public UserModel save(UserModel user) {
		user.setId(++count);
		this.list.add(user);
		return user;
	}
	
	public UserModel update(UserModel user) {
		UserModel originalUser = this.findByID(user.getId());
		originalUser.setAge(user.getAge());
		originalUser.setName((user.getName()));
		originalUser.setLastname(user.getLastname());
		originalUser.setSurname(user.getSurname());
//		user.setName(String.format("%s", user.getName()));
		return originalUser;
	}
	
	public List<UserModel> all(){
		return this.list;
	}
	
	public boolean delete(UserModel user) {
		if(list.contains(user)) {
			return list.remove(user);
		}else {
			return false;
		}
	}
	
	public UserModel findByID(int id) {
		for(UserModel user: this.list) {
			if(user.getId() ==id) {
				return user;
			}
		}
		return null;
	}
	

}
