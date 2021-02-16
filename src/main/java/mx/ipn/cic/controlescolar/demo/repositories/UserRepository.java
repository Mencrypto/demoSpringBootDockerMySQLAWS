package mx.ipn.cic.controlescolar.demo.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import mx.ipn.cic.controlescolar.demo.models.UserModel;

@Repository
public interface UserRepository extends JpaRepository<UserModel, Integer>{
	
	UserModel findByName(String name);
	//Select * from user where name=?
	UserModel findByNameContains(String name);

//	List<UserModel> findByRol(RolModel rol);
	List<UserModel> findByRol_Name(String rolName);
	//Select * from user u, rol r where u.rol_id=r.id and r.name=?
}

