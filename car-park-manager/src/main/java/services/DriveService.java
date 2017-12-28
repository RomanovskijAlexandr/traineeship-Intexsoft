package services;

import entities.Driver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import repositories.DriverRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.Query;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Service
public class DriveService {
    private static final Logger logger = LoggerFactory.getLogger(DriveService.class);

    @Autowired
    private DriverRepository driverRepository;

    @PersistenceContext
    private EntityManager entityManager;

    /**
     * Get all drivers from DB.
     * @return List of drivers
     */
    @Transactional
    public List<Driver> getDrivers() {
        logger.info("do getDrivers() in DriveService");
        Query query = entityManager.createNativeQuery("SELECT ID, AGE , NAME FROM driver", Driver.class);
        System.out.println(query.getResultList());
        System.out.println(driverRepository.getOne( 1L));
        return query.getResultList();
    }

}
