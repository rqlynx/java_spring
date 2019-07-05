package jp.abc.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import jp.abc.PlayList;

public interface PlayListRepository extends JpaRepository<PlayList, Long> {

}