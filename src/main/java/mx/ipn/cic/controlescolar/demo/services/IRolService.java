package mx.ipn.cic.controlescolar.demo.services;

import java.util.List;

import mx.ipn.cic.controlescolar.demo.models.RolModel;

public interface IRolService {
	
	RolModel create(RolModel rol);
	RolModel update(RolModel rol);
	RolModel findById(int id);
	
	List<RolModel> findAll();
	boolean delete(RolModel rol);
	
	RolModel findById(Integer id);
//	RolModel findByNameContains(String name);

}
