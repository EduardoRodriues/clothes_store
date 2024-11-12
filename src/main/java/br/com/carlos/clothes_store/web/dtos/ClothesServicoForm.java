package br.com.carlos.clothes_store.web.dtos;

import java.math.BigDecimal;
import br.com.carlos.clothes_store.core.enums.Tamanho;
import io.micrometer.common.lang.NonNull;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ClothesServicoForm {


	@Size(min = 20, max = 50)
	@NonNull
    private String categoria;

	@NonNull
    private String produto;

	@NonNull
	@PositiveOrZero
    private BigDecimal valor;
    
	@Email
	@NonNull
    private String email;

	@NonNull
    private String tecido;

	@NonNull
    private String cor;

	@NonNull
    private String marca;

	@NonNull
    private String tipoDeAviamento;

	@NonNull
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