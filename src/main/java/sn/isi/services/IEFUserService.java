package sn.isi.services;

import org.springframework.context.MessageSource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sn.isi.dao.IAUserRepository;
import sn.isi.dao.IEFUserRepository;
import sn.isi.dto.IAUser;
import sn.isi.dto.IEFUser;
import sn.isi.exceptions.RequestException;
import sn.isi.mappings.IAUserMapper;
import sn.isi.mappings.IEFUserMapper;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class IEFUserService {
    private IEFUserRepository iefUserRepository;
    private MessageSource messageSource;
    private IEFUserMapper iefUserMapper;

    public IEFUserService(IAUserRepository iaUserRepository, MessageSource messageSource,
                         IAUserMapper produitMapper) {
        super();
        this.iefUserRepository = iefUserRepository;
        this.messageSource = messageSource;
        this.iefUserMapper = iefUserMapper;
    }

    @Transactional(readOnly = true)
    public List<IEFUser> getIEFUsers() {
        return StreamSupport.stream(iefUserRepository.findAll().spliterator(), false)
                .map(iefUserMapper::toIEFUser)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public Page<IEFUser> getIEFUsers(Pageable pageable) {
        return iefUserRepository.findAll(pageable).map(iefUserMapper::toIEFUser);
    }

    @Transactional(readOnly = true)
    public IEFUser getIEFUser(int id) {
        return iefUserMapper.toIEFUser(iefUserRepository.findById(id).orElseThrow(() ->
                new EntityNotFoundException(messageSource.getMessage("iefauser.notfound", new Object[]{id},
                        Locale.getDefault()))));
    }

    @Transactional
    public IEFUser createIEFUser(IEFUser iefUser) {
        return iefUserMapper.toIEFUser(iefUserRepository.save(iefUserMapper.fromIEFUser(iefUser)));
    }

    @Transactional
    public IEFUser updateIEFUser(int id, IEFUser iefUser){
        return iefUserRepository.findById(id)
                .map(entity -> {
                    iefUser.setId(id);
                    return iefUserMapper.toIEFUser(iefUserRepository.save(iefUserMapper.fromIEFUser(iefUser)));
                }).orElseThrow(() -> new EntityNotFoundException(messageSource.getMessage("iefauser.notfound",
                        new Object[]{id},
                        Locale.getDefault())));
    }

    @Transactional
    public void deleteIEFUser(int id) {
        try {
            iefUserRepository.deleteById(id);
        } catch (Exception e) {
            throw new RequestException(messageSource.getMessage("iefauser.errordeletion", new Object[]{id},
                    Locale.getDefault()),
                    HttpStatus.CONFLICT);
        }
    }
}
