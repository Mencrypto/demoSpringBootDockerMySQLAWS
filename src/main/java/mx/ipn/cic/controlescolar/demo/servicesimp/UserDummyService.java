package mx.ipn.cic.controlescolar.demo.servicesimp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import mx.ipn.cic.controlescolar.demo.models.UserModel;
import mx.ipn.cic.controlescolar.demo.repositories.userDummyRepository;
import mx.ipn.cic.controlescolar.demo.services.IUserService;

@Service
@Qualifier("DUMMY")
public class UserDummyService implements IUserService {
	
	@Autowired
//	@Qualifier("DUMMY")
	private userDummyRepository userRepository;

	@Override
	public UserModel create(UserModel user) {
		UserModel u = userRepository.save(user);
		return u;
	}

	@Override
	public UserModel update(UserModel user) {
		UserModel u = userRepository.update(user);
		return u;
	}

	@Override
	public UserModel findById(int id) {
		UserModel found = userRepository.findByID(id);
		// TODO Hacer manejo de excepciones
		return found;
	}

	@Override
	public List<UserModel> findAll() {
		return userRepository.all();
	}

	@Override
	public boolean delete(UserModel user) {
		//TODO Hacer manejo de excepciones (No puedes eliminar algo que no exista)
		boolean d = userRepository.delete(user);
		return d;
	}

	@Override
	public UserModel findByName(String name) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public UserModel findByNameContains(String name) {
		// TODO Auto-generated method stub
		return null;
	}

}
