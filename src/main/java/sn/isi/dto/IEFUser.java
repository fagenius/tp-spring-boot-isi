package sn.isi.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class IEFUser {
    private int id;
    @NotNull(message = "Le nom ne doit pas être vide")
    private String nom;
}
