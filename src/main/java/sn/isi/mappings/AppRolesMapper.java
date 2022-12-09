package sn.isi.mappings;

import org.mapstruct.Mapper;
import sn.isi.dto.AppRoles;
import sn.isi.entities.AppRolesEntity;

/**
 * @author Ibrahima
 *
 */
@Mapper
public interface AppRolesMapper {
	AppRoles toAppRoles(AppRolesEntity appRolesEntity);
	AppRolesEntity fromAppRoles(AppRoles appRoles);
}
