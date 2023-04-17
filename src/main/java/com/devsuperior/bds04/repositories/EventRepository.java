package com.devsuperior.bds04.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.devsuperior.bds04.entities.Event;
import org.springframework.data.jpa.repository.Query;

public interface EventRepository extends JpaRepository<Event, Long> {
}
