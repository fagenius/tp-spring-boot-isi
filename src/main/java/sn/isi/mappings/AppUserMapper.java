/**
 * 
 */
package sn.isi.mappings;

import org.mapstruct.Mapper;
import sn.isi.dto.AppUser;
import sn.isi.entities.AppUserEntity;

/**
 * @author Ibrahima
 *	Permet de transformer les dto en entités ou vis versa
 */
@Mapper
public interface AppUserMapper {
	AppUser toAppUser(AppUserEntity appUserEntity);
	AppUserEntity fromAppUser(AppUser appUser);
}
