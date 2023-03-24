package ru.skillbox.diplom.group35.microservice.post.resource.post;

import java.util.UUID;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.skillbox.diplom.group35.library.core.controller.BaseController;
import ru.skillbox.diplom.group35.microservice.post.dto.comment.CommentDto;
import ru.skillbox.diplom.group35.microservice.post.dto.post.PostDto;
import ru.skillbox.diplom.group35.microservice.post.dto.post.PostSearchDto;
@RequestMapping(value = "/api/v1/post")
public interface PostController extends BaseController<PostDto, PostSearchDto> {

  @Override
  @GetMapping
  ResponseEntity<Page<PostDto>> getAll(PostSearchDto postSearchDto, Pageable pageable);

  @Override
  @GetMapping("/{id}")
  ResponseEntity<PostDto> getById(@PathVariable(name = "id") UUID id);

  @Override
  @PostMapping
  ResponseEntity<PostDto> create(@RequestBody PostDto postDto);

  @Override
  @PutMapping
  ResponseEntity<PostDto> update(@RequestBody PostDto postDto);

  @Override
  @DeleteMapping(value = "/{id}")
  void deleteById(@PathVariable(name = "id") UUID id);

  @PutMapping(value = "/{id}/comment/{commentId}")
  ResponseEntity<CommentDto> createSubComment(@RequestBody CommentDto commentDto,
                                              @PathVariable UUID id,
                                              @PathVariable UUID commentId);

  @DeleteMapping(value = "/{id}/comment/{commentId}")
  ResponseEntity<CommentDto> deleteComment(@PathVariable UUID id, @PathVariable UUID commentId);

  @GetMapping(value = "/{id}/comment")
  ResponseEntity<Page<CommentDto>> getComment(@PathVariable UUID id, Pageable page);

  @PostMapping(value = "/{id}/comment")
  ResponseEntity<CommentDto> createComment(@PathVariable UUID id, @RequestBody CommentDto commentDto);

  @GetMapping(value = "/{id}/comment/{commentId}/subcomment")
  ResponseEntity<Page<CommentDto>> getSubComment(@PathVariable UUID id, @PathVariable UUID commentId, Pageable page);
}
