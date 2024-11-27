package br.com.carlos.clothes_store.web.dtos;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UsuarioEdicaoForm {

    @Size(min = 3, max = 50, message = "campo deve conter de 3 a 50 caracteres")
    @NotNull
	@NotEmpty(message = "o campo deve estar preenchido")
    private String nome;

    @Email(message = "o campo deve ser preenchido com um email")
    @NotNull
	@NotEmpty(message = "o campo deve estar preenchido")
    private String email;

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
    
}