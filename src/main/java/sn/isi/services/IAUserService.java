/**
 * 
 */
package sn.isi.services;

import org.springframework.context.MessageSource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sn.isi.dao.IAUserRepository;
import sn.isi.dto.IAUser;
import sn.isi.exceptions.RequestException;
import sn.isi.mappings.IAUserMapper;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

/**
 * @author Ibrahima
 *
 */
@Service
public class IAUserService {
	
	private IAUserRepository iaUserRepository;
	private MessageSource messageSource;
	private IAUserMapper iaUserMapper;
	
	public IAUserService(IAUserRepository iaUserRepository, MessageSource messageSource,
						 IAUserMapper produitMapper) {
		super();
		this.iaUserRepository = iaUserRepository;
		this.messageSource = messageSource;
		this.iaUserMapper = iaUserMapper;
	}
	
	@Transactional(readOnly = true)
	public List<IAUser> getIAUsers() {
		return StreamSupport.stream(iaUserRepository.findAll().spliterator(), false)
                .map(iaUserMapper::toIAUser)
                .collect(Collectors.toList());
	}
	
	@Transactional(readOnly = true)
    public Page<IAUser> getIAUsers(Pageable pageable) {
        return iaUserRepository.findAll(pageable).map(iaUserMapper::toIAUser);
    }
	
	@Transactional(readOnly = true)
    public IAUser getIAUser(int id) {
        return iaUserMapper.toIAUser(iaUserRepository.findById(id).orElseThrow(() ->
                new EntityNotFoundException(messageSource.getMessage("iauser.notfound", new Object[]{id},
                Locale.getDefault()))));
    }
	
	@Transactional
    public IAUser createIAUser(IAUser iaUser) {
        return iaUserMapper.toIAUser(iaUserRepository.save(iaUserMapper.fromIAUser(iaUser)));
    }
	
	@Transactional
    public IAUser updateIAUser(int id, IAUser iaUser){
        return iaUserRepository.findById(id)
                .map(entity -> {
                	iaUser.setId(id);
                    return iaUserMapper.toIAUser(iaUserRepository.save(iaUserMapper.fromIAUser(iaUser)));
                }).orElseThrow(() -> new EntityNotFoundException(messageSource.getMessage("iauser.notfound",
                new Object[]{id},
                Locale.getDefault())));
    }
	
	@Transactional
    public void deleteIAUser(int id) {
        try {
        	iaUserRepository.deleteById(id);
        } catch (Exception e) {
            throw new RequestException(messageSource.getMessage("iauser.errordeletion", new Object[]{id},
                    Locale.getDefault()),
                    HttpStatus.CONFLICT);
        }
    }

}
