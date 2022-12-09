package sn.isi.controllers;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import sn.isi.dto.AppUser;
import sn.isi.dto.IEFUser;
import sn.isi.services.AppUserService;
import sn.isi.services.IEFUserService;

import javax.validation.Valid;
import java.util.List;

/**
 * @author Ibrahima
 *
 */
@RestController
@RequestMapping("/users")
@AllArgsConstructor
public class IEFUserController {

    private IEFUserService iefUserService;

    @GetMapping
    public List<IEFUser> getIEFUsers() {
        return iefUserService.getIEFUsers();
    }

    @GetMapping("/{id}")
    public IEFUser getIEFUser(@PathVariable("id") int id) {
        return iefUserService.getIEFUser(id);
    }

    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    //@IsAdmin
    public IEFUser createIEFUser(@Valid @RequestBody IEFUser iefUser) {
        return iefUserService.createIEFUser(iefUser);
    }

    @PutMapping("/{id}")
    //@IsAdmin
    public IEFUser updateIEFUser(@PathVariable("id") int id, @Valid @RequestBody IEFUser iefUser) {
        return iefUserService.updateIEFUser(id, iefUser);
    }

    @DeleteMapping("/{id}")
    public void deleteIEFUser(@PathVariable("id") int id) {
        iefUserService.deleteIEFUser(id);
    }

}
