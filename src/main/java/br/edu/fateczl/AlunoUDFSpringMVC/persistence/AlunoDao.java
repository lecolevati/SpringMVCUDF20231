package br.edu.fateczl.AlunoUDFSpringMVC.persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import br.edu.fateczl.AlunoUDFSpringMVC.model.Aluno;

@Repository
public class AlunoDao implements IAlunoDao {

	@Autowired
	private GenericDao gDao;

	@Override
	public List<Aluno> findAlunosSituacao() throws SQLException, ClassNotFoundException {
		List<Aluno> alunos = new ArrayList<Aluno>();

		Connection c = gDao.getConnection();
		String sql = "SELECT cod, nome, altura, peso, imc, descricao FROM fn_tabelaimc()";
		PreparedStatement ps = c.prepareStatement(sql);
		ResultSet rs = ps.executeQuery();
		while (rs.next()) {
			Aluno al = new Aluno();
			al.setCod(rs.getInt("cod"));
			al.setNome(rs.getString("nome"));
			al.setAltura(rs.getFloat("altura"));
			al.setPeso(rs.getFloat("peso"));
			al.setImc(rs.getFloat("imc"));
			al.setSituacao(rs.getString("descricao"));

			alunos.add(al);
		}
		rs.close();
		ps.close();
		c.close();

		return alunos;
	}

	@Override
	public Aluno findAlunoSituacao(Aluno al) throws SQLException, ClassNotFoundException {
		Connection c = gDao.getConnection();
		String sql = "SELECT cod, nome, altura, peso, imc, descricao FROM fn_tabelaimc() WHERE cod = ?";
		PreparedStatement ps = c.prepareStatement(sql);
		ps.setInt(1, al.getCod());
		ResultSet rs = ps.executeQuery();
		while (rs.next()) {
			al.setCod(rs.getInt("cod"));
			al.setNome(rs.getString("nome"));
			al.setAltura(rs.getFloat("altura"));
			al.setPeso(rs.getFloat("peso"));
			al.setImc(rs.getFloat("imc"));
			al.setSituacao(rs.getString("descricao"));
		}
		rs.close();
		ps.close();
		c.close();

		return al;
	}
}
