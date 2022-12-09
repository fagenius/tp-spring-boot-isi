package sn.isi.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import sn.isi.entities.AppUserEntity;

/**
 * @author Ibrahima
 *
 */
public interface IAppUserRepository extends JpaRepository<AppUserEntity, Integer>{
	AppUserEntity findByEmail(String email);
}
