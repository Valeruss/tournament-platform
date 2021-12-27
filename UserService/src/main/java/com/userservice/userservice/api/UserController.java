package com.userservice.userservice.api;


import com.userservice.userservice.repo.model.User;
import com.userservice.userservice.api.dto.UserDTO;
import com.userservice.userservice.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/users")
public final class UserController {

    public final UserService userService;

    @GetMapping
    public ResponseEntity<List<User>> index() {
        final List<User> users = userService.fetchAll();
        return ResponseEntity.ok(users);
    }

    @GetMapping("/{id}")
    public ResponseEntity<com.userservice.userservice.repo.model.User> show(@PathVariable long id) {
        try {
            final User user = userService.fetchById(id);
            return ResponseEntity.ok(user);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/{id}/username")
    public ResponseEntity showName(@PathVariable long id) {
        final User user = userService.fetchById(id);
        final String name = user.getName();
        return ResponseEntity.ok(name);
    }

    @PostMapping
    public ResponseEntity<Void> create(@RequestBody UserDTO user) {
        final String name = user.getName();
        final String password = user.getPassword();
        final Integer tournament_played = user.getTournament_played();

        final long id = userService.create(name, password, tournament_played);
        final String location = String.format("/users/%d", id);

        return ResponseEntity.created(URI.create(location)).build();
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Void> update(@PathVariable long id, @RequestBody UserDTO user) {
        final String name = user.getName();
        final String password = user.getPassword();
        final Integer tournament_played = user.getTournament_played();

        try {
            userService.update(id, name, password, tournament_played);
            return ResponseEntity.notFound().build();
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable long id) {
        userService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
