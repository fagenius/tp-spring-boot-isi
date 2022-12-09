/**
 * 
 */
package sn.isi.mappings;
import org.mapstruct.Mapper;
import sn.isi.dto.IAUser;
import sn.isi.entities.IAUserEntity;

/**
 * @author Ibrahima
 *
 */
@Mapper
public interface IAUserMapper {
	IAUser toIAUser(IAUserEntity iaUserEntity);
	IAUserEntity fromIAUser(IAUser iaUser);
}
