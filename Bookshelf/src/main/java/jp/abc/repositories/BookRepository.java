package jp.abc.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import jp.abc.Book;

public interface BookRepository extends JpaRepository<Book, Long> {

}