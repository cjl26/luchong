package cloudPayAdmin.admin.service.hicatcard.transaction;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cloudPayAdmin.admin.dbapp.repo.hicatcard.transaction.TransactionRepo;

@Service
@Transactional
public class TransactionService {
	
	@PersistenceContext(unitName = "entityManagerFactory")
	EntityManager entityManager;
	
	@Autowired
	TransactionRepo transactionRepo;
}
