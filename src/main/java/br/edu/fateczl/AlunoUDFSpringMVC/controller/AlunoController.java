package br.edu.fateczl.AlunoUDFSpringMVC.controller;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import br.edu.fateczl.AlunoUDFSpringMVC.model.Aluno;
import br.edu.fateczl.AlunoUDFSpringMVC.persistence.AlunoDao;

@Controller
public class AlunoController {

	@Autowired
	private AlunoDao aDao;
	
	@RequestMapping(name = "aluno", value = "/aluno",
			method = RequestMethod.GET)
	public ModelAndView init(ModelMap model) {
		return new ModelAndView("aluno");
	}

	@RequestMapping(name = "aluno", value = "/aluno",
			method = RequestMethod.POST)
	public ModelAndView alunos(@RequestParam Map<String, String> params, 
			ModelMap model) {
		String cod = params.get("cod");
		String botao = params.get("botao");
		
		Aluno aluno = new Aluno();
		List<Aluno> alunos = new ArrayList<>();
		String erro = "";
		
		try {
			if (botao.equalsIgnoreCase("listar")) {
				alunos = aDao.findAlunosSituacao();
				aluno = null;
			} else {
				aluno.setCod(Integer.parseInt(cod));
				aluno = aDao.findAlunoSituacao(aluno);
			}
		} catch (SQLException | ClassNotFoundException e) {
			erro = e.getMessage();
		} finally {
			model.addAttribute("aluno", aluno);
			model.addAttribute("alunos", alunos);
			model.addAttribute("erro", erro);
		}
		return new ModelAndView("aluno");
	}
	
}
