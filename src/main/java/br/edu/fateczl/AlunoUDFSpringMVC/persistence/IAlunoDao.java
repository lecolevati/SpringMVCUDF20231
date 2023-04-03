package br.edu.fateczl.AlunoUDFSpringMVC.persistence;

import java.sql.SQLException;
import java.util.List;

import br.edu.fateczl.AlunoUDFSpringMVC.model.Aluno;

public interface IAlunoDao {

	public List<Aluno> findAlunosSituacao() throws SQLException, ClassNotFoundException;
	public Aluno findAlunoSituacao(Aluno al) throws SQLException, ClassNotFoundException;

}