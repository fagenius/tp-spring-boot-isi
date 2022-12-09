package sn.isi.controllers;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import sn.isi.dto.AppUser;
import sn.isi.services.AppUserService;
import javax.validation.Valid;
import java.util.List;

/**
 * @author Ibrahima
 *
 */
@RestController
@RequestMapping("/users")
@AllArgsConstructor
public class AppUserController {

    private AppUserService appUserService;

    @GetMapping
    public List<AppUser> getAppUsers() {
        return appUserService.getAppUsers();
    }

    @GetMapping("/{id}")
    public AppUser getAppUser(@PathVariable("id") int id) {
        return appUserService.getAppUser(id);
    }

    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    //@IsAdmin
    public AppUser createAppUser(@Valid @RequestBody AppUser appUser) {
        return appUserService.createAppUser(appUser);
    }

    @PutMapping("/{id}")
    //@IsAdmin
    public AppUser updateAppUser(@PathVariable("id") int id, @Valid @RequestBody AppUser appUser) {
        return appUserService.updateAppUser(id, appUser);
    }

    @DeleteMapping("/{id}")
    public void deleteAppUser(@PathVariable("id") int id) {
        appUserService.deleteAppUser(id);
    }

}
