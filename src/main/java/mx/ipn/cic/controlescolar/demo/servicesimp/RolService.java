package mx.ipn.cic.controlescolar.demo.servicesimp;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import exceptions.CICException;
import mx.ipn.cic.controlescolar.demo.models.RolModel;
import mx.ipn.cic.controlescolar.demo.repositories.RolRepository;
import mx.ipn.cic.controlescolar.demo.services.IRolService;

@Service
@Qualifier("Rol")
public class RolService implements IRolService{

	
	@Autowired
	private RolRepository repository;

	@Override
	public RolModel create(RolModel rol) {
		rol = repository.save(rol);
		return rol;
	}

	@Override
	public RolModel update(RolModel rol) {
		rol = repository.save(rol);
		return rol;
	}

	@Override
	public RolModel findById(int id) {
		Optional<RolModel> rolOptional = repository.findById(id);
		
		if (rolOptional.isPresent()) {
			return rolOptional.get();
		}else {
			throw new CICException("No se encontró el rol");
		}
	}

	@Override
	public List<RolModel> findAll() {
		return repository.findAll();
	}

	@Override
	public boolean delete(RolModel rol) {
		repository.delete(rol);
		return true;
	}

	@Override
	public RolModel findById(Integer id) {
		Optional<RolModel> rolOptional = repository.findById(id);
		
		if (rolOptional.isPresent()) {
			return rolOptional.get();
		}else {
			throw new CICException("No se encontró el rol");
		}
	}

	


}
