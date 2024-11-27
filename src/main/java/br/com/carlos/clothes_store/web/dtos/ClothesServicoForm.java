package br.com.carlos.clothes_store.web.dtos;

import java.math.BigDecimal;
import org.springframework.format.annotation.NumberFormat;
import org.springframework.format.annotation.NumberFormat.Style;
import br.com.carlos.clothes_store.core.enums.Tamanho;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ClothesServicoForm {


	@Size(min = 3, max = 50, message = "campo deve conter de 3 a 50 caracteres")
	@NotNull
    private String categoria;

	@NotNull
	@NotEmpty(message = "o campo deve ser preenchido")
    private String produto;

	@NotNull(message = "o campo deve ser preenchido")
	@PositiveOrZero
	@NumberFormat(style = Style.CURRENCY, pattern = "#,##0.00")
    private BigDecimal valor;
    
	@Email(message = "o campo deve ser preenchido com um email")
	@NotNull
	@NotEmpty(message = "o campo deve ser preenchido")
    private String email;

	@NotNull
	@NotEmpty(message = "o campo deve ser preenchido")
    private String tecido;

	@NotNull
	@NotEmpty(message = "o campo deve ser preenchido")
    private String cor;

	@NotNull
	@NotEmpty(message = "o campo deve ser preenchido")
    private String marca;

	@NotNull
	@NotEmpty(message = "o campo deve ser preenchido")
    private String tipoDeAviamento;

	@NotNull
    private Tamanho tamanho;

	public String getCategoria() {
		return categoria;
	}

	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}

	public String getProduto() {
		return produto;
	}

	public void setProduto(String produto) {
		this.produto = produto;
	}

	public BigDecimal getValor() {
		return valor;
	}

	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}

	public String getTecido() {
		return tecido;
	}

	public void setTecido(String tecido) {
		this.tecido = tecido;
	}

	public String getCor() {
		return cor;
	}

	public void setCor(String cor) {
		this.cor = cor;
	}

	public String getMarca() {
		return marca;
	}

	public void setMarca(String marca) {
		this.marca = marca;
	}

	public String getTipoDeAviamento() {
		return tipoDeAviamento;
	}

	public void setTipoDeAviamento(String tipoDeAviamento) {
		this.tipoDeAviamento = tipoDeAviamento;
	}

	public Tamanho getTamanho() {
		return tamanho;
	}

	public void setTamanho(Tamanho tamanho) {
		this.tamanho = tamanho;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
    
}