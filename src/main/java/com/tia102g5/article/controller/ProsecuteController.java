package com.tia102g5.article.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tia102g5.prosecute.model.Prosecute;
import com.tia102g5.prosecute.model.ProsecuteService;

@RestController
@RequestMapping("/prosecutes")
public class ProsecuteController {

	@Autowired
	ProsecuteService ProsecuteSvc;

	@PostMapping
	public ResponseEntity<?> prosecuteContent(@RequestBody Prosecute prosecute) {
		ProsecuteSvc.prosecuteContent(prosecute);
		return ResponseEntity.ok().build();
	}

    @GetMapping("/article/{id}")
    public ResponseEntity<Boolean> isArticleReported(@PathVariable Integer id) {
        return ResponseEntity.ok(ProsecuteSvc.isArticleReported(id));
    }

    @GetMapping("/message/{id}")
    public ResponseEntity<Boolean> isMessageReported(@PathVariable Integer id) {
        return ResponseEntity.ok(ProsecuteSvc.isMessageReported(id));
    }

}
