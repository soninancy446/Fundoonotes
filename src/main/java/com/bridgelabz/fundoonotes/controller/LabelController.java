package com.bridgelabz.fundoonotes.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bridgelabz.fundoonotes.dto.LabelDto;
import com.bridgelabz.fundoonotes.entity.Label;
import com.bridgelabz.fundoonotes.entity.Note;
import com.bridgelabz.fundoonotes.exceptions.ErrorResponse;
import com.bridgelabz.fundoonotes.response.Response;
import com.bridgelabz.fundoonotes.service.LabelService;

@RestController
@RequestMapping("/label")
@CrossOrigin(allowedHeaders = "*", origins = "*", exposedHeaders = { "token" })
public class LabelController {
	@Autowired
	private LabelService labelService;

	@PostMapping("/create")

	public ResponseEntity<ErrorResponse> createLabel(@RequestBody LabelDto labeldto,
			@RequestHeader("token") String token) {
		System.out.println(labeldto.getLabelName());
		Label label=labelService.createLabel(labeldto, token);

		return new ResponseEntity<>(new ErrorResponse(HttpStatus.OK.value(), "label Created"), HttpStatus.OK);
	}

	@PutMapping("/update")
	public ResponseEntity<ErrorResponse> updateLabel(@RequestBody LabelDto labeldto,
			@RequestHeader("token") String token) {
		labelService.updateLabel(labeldto, token);
		return new ResponseEntity<>(new ErrorResponse(HttpStatus.OK.value(), "label updated"), HttpStatus.OK);
	}

	@PostMapping("/delete")
	public ResponseEntity<ErrorResponse> delete(@RequestBody LabelDto labelDto, @RequestHeader("token") String token) {
		labelService.deleteLabel(labelDto, token);
		return new ResponseEntity<>(new ErrorResponse(HttpStatus.OK.value(), "label deleted"), HttpStatus.OK);
	}

	// ResponseEntity.status(HttpStatus.OK).body(new Response("label added", 200));
	@PostMapping("/addlabel")
	public ResponseEntity<ErrorResponse> addLabel(@RequestParam("labelId") Long labelId,
			@RequestHeader("token") String token, @RequestParam("noteId") Long id) {
		System.out.println(labelId);
		labelService.addLabel(labelId, id, token);
		return new ResponseEntity<>(new ErrorResponse(HttpStatus.OK.value(), "label added"), HttpStatus.OK);

	}

	@PostMapping("removelabel")
	public ResponseEntity<ErrorResponse> removeLabel(@RequestParam("labelId") Long labelId,
			@RequestHeader("token") String token, @RequestParam("id") Long id) {
		System.out.println(labelId);
		labelService.removeLabel(labelId, id, token);
		return new ResponseEntity<>(new ErrorResponse(HttpStatus.OK.value(), "label removed"), HttpStatus.OK);
	}

	@GetMapping("/getAllLabel")
	public Response get(@RequestHeader("token") String token) {
		List<Label> labels = labelService.getLabel(token);
		return new Response(HttpStatus.OK.value(), "got all labels", labels);
	}

	@GetMapping("/getLabelNotes")
	public Response getNotes(@RequestHeader("token") String token,
			@RequestParam("id") Long labelId) {
		List<Note> list = labelService.getAllNotes(token, labelId);
		return new Response(HttpStatus.OK.value(), "List of Labels", list);
//		return new ResponseEntity<>(new ErrorResponse(HttpStatus.OK.value(), "label removed"),list);
	}

	@PostMapping("/createLabelAndMap")
	public ResponseEntity<ErrorResponse> createLabelAndMap(@RequestBody LabelDto labelDto,
			@RequestHeader("token") String token, @RequestParam("id") Long id) {

		System.out.println("label is........." + labelDto.getLabelName());
		System.out.println("note id is" + id);
		labelService.createLabelAndMap(labelDto, token, id);

		return new ResponseEntity<>(new ErrorResponse(HttpStatus.OK.value(), "label mapped"), HttpStatus.OK);
	}

}
