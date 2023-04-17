package com.devsuperior.bds04.resources;

import com.devsuperior.bds04.dto.EventDTO;
import com.devsuperior.bds04.services.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/events")
public class EventResource {
    @Autowired
    private EventService eventService;

    @GetMapping
    public ResponseEntity<Page<EventDTO>> findAll(Pageable pageable) {
        return ResponseEntity.ok(eventService.findAll(pageable));
    }

    @PostMapping
    public ResponseEntity<EventDTO> insert(@RequestBody @Valid EventDTO eventDTO) {
        eventDTO = eventService.insert(eventDTO);
        return new ResponseEntity<EventDTO>(eventDTO, HttpStatus.CREATED);
    }
}
