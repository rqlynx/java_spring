package jp.abc.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import jp.abc.Music;

@Repository
public interface MusicRepository extends JpaRepository<Music, Long> {
    public Optional<Music> findById(Long id);
}