package services;

import controllers.web.MainController;
import entities.Bus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import repositories.BusRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Service
public class BusService {
    private static final Logger logger = LoggerFactory.getLogger(MainController.class);

    @Autowired
    BusRepository busRepository;

    @PersistenceContext
    private EntityManager entityManager;

    @Transactional
    public List<Bus> getBuses() {
        logger.info("do getBuses() in BusService");
        Query query = entityManager.createNativeQuery("SELECT ID, NUMBER , Rote_ID FROM bus", Bus.class);
        System.out.println(query.getResultList());
        System.out.println(busRepository.getOne( 2L));
        return query.getResultList();
    }
}
