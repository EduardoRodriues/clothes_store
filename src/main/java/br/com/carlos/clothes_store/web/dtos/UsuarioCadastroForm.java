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
public class UsuarioCadastroForm {

    @Size(min = 3, max = 50, message = "campo deve conter de 3 a 50 caracteres")
    @NotNull
	@NotEmpty(message = "o campo deve ser preenchido")
    private String nome;

    @NotNull
    @Email(message = "o campo deve ser preenchido com um email")
	@NotEmpty(message = "o campo deve ser preenchido")
    private String email;

    @NotNull
	@NotEmpty(message = "o campo deve ser preenchido")
    private String senha;

    @NotNull
	@NotEmpty(message = "o campo deve ser preenchido")
    private String confirmacaoSenha;

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

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getConfirmacaoSenha() {
		return confirmacaoSenha;
	}

	public void setConfirmacaoSenha(String confirmacaoSenha) {
		this.confirmacaoSenha = confirmacaoSenha;
	}
}