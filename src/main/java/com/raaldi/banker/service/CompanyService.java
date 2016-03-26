package com.raaldi.banker.service;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.raaldi.banker.dao.CompanyDAO;
import com.raaldi.banker.dao.ModelDAO;
import com.raaldi.banker.model.Company;

@Service("companyService")
@Transactional
public class CompanyService implements ModelService<Company> {

	@PersistenceContext
	private EntityManager em;

	private ModelDAO<Company, Long> entityDAO;

	@PostConstruct
	public void postConstruct() {
		entityDAO = new CompanyDAO(Company.class, em);
	}

	@Override
	public void save(Company model) {
		entityDAO.save(model);
	}

	@Override
	public Company findOne(Long id) {
		return entityDAO.findOne(id);
	}

	@Override
	public List<Company> findAll() {
		return entityDAO.findAll();
	}

	@Override
	public boolean exists(Company model) {
		return this.exists(model.getId());
	}

	@Override
	public boolean exists(Long id) {
		return entityDAO.exists(id);
	}

}
