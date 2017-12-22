package repositories;

import entities.Rote;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoteRepository extends JpaRepository<Rote, Long> {

}
