package sn.isi.mappings;

import org.mapstruct.Mapper;
import sn.isi.dto.IEFUser;
import sn.isi.entities.IEFUserEntity;

/**
 * @author Ibrahima
 *
 */
@Mapper
public interface IEFUserMapper {
    IEFUser toIEFUser(IEFUserEntity iefUserEntity);
    IEFUserEntity fromIEFUser(IEFUser iefUser);
}
