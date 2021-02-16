package mx.ipn.cic.controlescolar.demo.servicesimp;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import exceptions.CICException;
import mx.ipn.cic.controlescolar.demo.models.UserModel;
import mx.ipn.cic.controlescolar.demo.repositories.UserRepository;
import mx.ipn.cic.controlescolar.demo.services.IUserService;

@Service
@Qualifier("REAL")
public class UserRealService implements IUserService {

	@Autowired
	private UserRepository repository;
	
	
	@Override
	public UserModel create(UserModel user) {
		user = repository.save(user);
		return user;
	}

	@Override
	public UserModel update(UserModel user) throws CICException{
		if(user.getAge()<=18) {
			throw new CICException("Se ha superado la edad límite");
		}
		user = repository.save(user);
		return user;
	}

	@Override
	public UserModel findById(int id) throws CICException {
		Optional<UserModel> userOptional = repository.findById(id);
		if (userOptional.isPresent()) {
			return userOptional.get();
		}else {
			throw new CICException("No se encontró el usuario");
		}

	}

	@Override
	public List<UserModel> findAll() {
		return repository.findAll();
	}
	
	@Override
	public boolean delete(UserModel user) {
		repository.delete(user);
		return true;
	}

	@Override
	public UserModel findByName(String name) {
		UserModel user = repository.findByName(name);
		return user;
	}

	@Override
	public UserModel findByNameContains(String name) {
		UserModel user = repository.findByNameContains(name);
		return user;
	}

}
