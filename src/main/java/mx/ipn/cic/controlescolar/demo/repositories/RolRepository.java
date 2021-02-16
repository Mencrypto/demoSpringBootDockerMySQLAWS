package mx.ipn.cic.controlescolar.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import mx.ipn.cic.controlescolar.demo.models.RolModel;

@Repository
public interface RolRepository extends JpaRepository<RolModel, Integer>{ 

}
