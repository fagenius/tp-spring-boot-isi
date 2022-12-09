package sn.isi.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import sn.isi.entities.IAUserEntity;

public interface IAUserRepository extends JpaRepository<IAUserEntity, Integer> {
}
