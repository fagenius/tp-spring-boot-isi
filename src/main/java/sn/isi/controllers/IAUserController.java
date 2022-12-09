package sn.isi.controllers;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import sn.isi.dto.IAUser;
import sn.isi.services.IAUserService;

import javax.validation.Valid;
import java.util.List;

/**
 * @author Ibrahima
 *
 */
@RestController
@RequestMapping("/iausers")
@AllArgsConstructor
public class IAUserController {
    private IAUserService iaUserService;

    @GetMapping
    public List<IAUser> getIAUsers() {
        return iaUserService.getIAUsers();
    }

    @GetMapping("/{id}")
    public IAUser getIAUser(@PathVariable("id") int id) {
        return iaUserService.getIAUser(id);
    }

    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    //@IsAdmin
    public IAUser createIAUser(@Valid @RequestBody IAUser iaUser) {
        return iaUserService.createIAUser(iaUser);
    }

    @PutMapping("/{id}")
    //@IsAdmin
    public IAUser updateIAUser(@PathVariable("id") int id, @Valid @RequestBody IAUser iaUser) {
        return iaUserService.updateIAUser(id, iaUser);
    }

    @DeleteMapping("/{id}")
    public void deleteIAUser(@PathVariable("id") int id) {
        iaUserService.deleteIAUser(id);
    }

}
