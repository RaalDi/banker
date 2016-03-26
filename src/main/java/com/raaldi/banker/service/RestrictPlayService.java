package com.raaldi.banker.service;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.raaldi.banker.dao.ModelDAO;
import com.raaldi.banker.dao.RestrictPlayDAO;
import com.raaldi.banker.model.RestrictPlay;

@Service("restrictPlayService")
@Transactional
public class RestrictPlayService implements ModelService<RestrictPlay> {

	@PersistenceContext
	private EntityManager em;

	private ModelDAO<RestrictPlay, Long> entityDAO;

	@PostConstruct
	public void postConstruct() {
		entityDAO = new RestrictPlayDAO(RestrictPlay.class, em);
	}

	@Override
	public void save(RestrictPlay model) {
		entityDAO.save(model);
	}

	@Override
	public RestrictPlay findOne(Long id) {
		return entityDAO.findOne(id);
	}

	@Override
	public List<RestrictPlay> findAll() {
		return entityDAO.findAll();
	}

	@Override
	public boolean exists(RestrictPlay model) {
		return this.exists(model.getId());
	}

	@Override
	public boolean exists(Long id) {
		return entityDAO.exists(id);
	}

}
