package jp.abc.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import jp.abc.Bookshelf;

@Repository
public interface BookshelfRepository extends JpaRepository<Bookshelf, Long> {
    public Optional<Bookshelf> findById(Long id);
}