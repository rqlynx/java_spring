package jp.abc.repositories;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import jp.abc.MyData;

@Repository
public interface MyDataRepository extends JpaRepository<MyData, Long> {

}
