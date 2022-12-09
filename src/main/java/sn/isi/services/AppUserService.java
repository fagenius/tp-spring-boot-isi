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
import sn.isi.dao.IAppUserRepository;
import sn.isi.dto.AppUser;
import sn.isi.exceptions.RequestException;
import sn.isi.mappings.AppUserMapper;

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
public class AppUserService {
	private IAppUserRepository iAppUserRepository;
	private AppUserMapper appUserMapper;
	private MessageSource messageSource;
	
	public AppUserService(IAppUserRepository iAppUserRepository, AppUserMapper appUserMapper,
			MessageSource messageSource) {
		super();
		this.iAppUserRepository = iAppUserRepository;
		this.appUserMapper = appUserMapper;
		this.messageSource = messageSource;
	}
	
	@Transactional(readOnly = true)
	public List<AppUser> getAppUsers() {
		return StreamSupport.stream(iAppUserRepository.findAll().spliterator(), false)
                .map(appUserMapper::toAppUser)
                .collect(Collectors.toList());
	}
	
	@Transactional(readOnly = true)
    public Page<AppUser> getUsers(Pageable pageable) {
        return iAppUserRepository.findAll(pageable).map(appUserMapper::toAppUser);
    }
	
	@Transactional(readOnly = true)
    public AppUser getAppUser(int id) {
        return appUserMapper.toAppUser(iAppUserRepository.findById(id).orElseThrow(() ->
                new EntityNotFoundException(messageSource.getMessage("user.notfound", new Object[]{id},
                Locale.getDefault()))));
    }
	
	@Transactional
    public AppUser createAppUser(AppUser appUser) {
        return appUserMapper.toAppUser(iAppUserRepository.save(appUserMapper.fromAppUser(appUser)));
    }
	
	@Transactional
    public AppUser updateAppUser(int id, AppUser appUser){
        return iAppUserRepository.findById(id)
                .map(entity -> {
                	appUser.setId(id);
                    return appUserMapper.toAppUser(iAppUserRepository.save(appUserMapper.fromAppUser(appUser)));
                }).orElseThrow(() -> new EntityNotFoundException(messageSource.getMessage("user.notfound",
                        new Object[]{id},
                        Locale.getDefault())));
    }
	
	@Transactional
    public void deleteAppUser(int id) {
        try {
        	iAppUserRepository.deleteById(id);
        } catch (Exception e) {
            throw new RequestException(messageSource.getMessage("user.errordeletion", new Object[]{id},
                    Locale.getDefault()),
                    HttpStatus.CONFLICT);
        }
    }
	
}
