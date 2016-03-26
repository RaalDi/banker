package com.raaldi.banker.service;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.raaldi.banker.dao.CashRegisterDAO;
import com.raaldi.banker.dao.ModelDAO;
import com.raaldi.banker.model.CashRegister;

@Service("cashRegisterService")
@Transactional
public class CashRegisterService implements ModelService<CashRegister> {

	@PersistenceContext
	private EntityManager em;

	private ModelDAO<CashRegister, Long> entityDAO;

	@PostConstruct
	public void postConstruct() {
		entityDAO = new CashRegisterDAO(CashRegister.class, em);
	}

	@Override
	public void save(CashRegister model) {
		entityDAO.save(model);
	}

	@Override
	public CashRegister findOne(Long id) {
		return entityDAO.findOne(id);
	}

	@Override
	public List<CashRegister> findAll() {
		return entityDAO.findAll();
	}

	@Override
	public boolean exists(CashRegister model) {
		return this.exists(model.getId());
	}

	@Override
	public boolean exists(Long id) {
		return entityDAO.exists(id);
	}

}
