package sust.tv_shows.models;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface CanalRepository  extends JpaRepository<Canal,Long>{

}
