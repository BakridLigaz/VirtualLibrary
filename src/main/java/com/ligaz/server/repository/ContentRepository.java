package com.ligaz.server.repository;

import com.ligaz.server.entity.Content;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContentRepository extends JpaRepository<Content, Long> {
    Content getContentByBookName(String bookName);
};
