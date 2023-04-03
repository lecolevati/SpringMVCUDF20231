package br.edu.fateczl.AlunoUDFSpringMVC.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Aluno {

	private int cod;
	private String nome;
	private String situacao;
	private float altura;
	private float peso;
	private float imc;

}
