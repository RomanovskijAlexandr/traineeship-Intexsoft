package services;

import entities.Rote;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import repositories.RoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Service
public class RoteService {
    private static final Logger logger = LoggerFactory.getLogger(Rote.class);

    @Autowired
    private RoteRepository roteRepository;

    @PersistenceContext
    private EntityManager entityManager;

    @Transactional
    public List<Rote> getRotes() {
        logger.info("do getRotes() in RoteService");
        Query query = entityManager.createNativeQuery("SELECT ID, NAME , NUMBER FROM rote", Rote.class);
        System.out.println(query.getResultList());
        System.out.println(roteRepository.getOne( 2L));
        return query.getResultList();
    }
}
