package mx.ipn.cic.controlescolar.demo.services;

import java.util.List;
import mx.ipn.cic.controlescolar.demo.models.UserModel;

public interface IUserService {
	
	UserModel create(UserModel user);
	UserModel update(UserModel user);
	UserModel findById(int id);
	
	List<UserModel> findAll();
	boolean delete(UserModel user);
	
	UserModel findByName(String name);
	UserModel findByNameContains(String name);
	

}
