package sn.isi.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;


/**
 * @author Ibrahima
 *
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class IAUser {
	private int id;
	@NotNull(message = "Le nom ne doit pas être vide")
	private String nom;
}
