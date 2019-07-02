package jp.abc.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import jp.abc.MsgData;

public interface MsgDataRepository extends JpaRepository<MsgData, Long> {

}