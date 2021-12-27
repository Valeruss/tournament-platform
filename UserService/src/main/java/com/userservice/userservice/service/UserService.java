package com.userservice.userservice.service;

import com.userservice.userservice.repo.UserRepo;
import com.userservice.userservice.repo.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class UserService {
    private final UserRepo userRepo;

    public List<User> fetchAll() { return userRepo.findAll(); }

    public User fetchById(long id) throws IllegalArgumentException {
        final Optional<User> maybeUser = userRepo.findById(id);

        if(maybeUser.isEmpty()) throw new IllegalArgumentException("User not found");
        else return maybeUser.get();
    }

//    public User fetchNameById(long id) throws IllegalArgumentException {
//        final Optional<User> user = userRepo.findById(id);
//        return user.get();
//    }

    public long create(String name, String password, Integer tournament_played) {
        final User user = new User(name, password, tournament_played);
        final User savedUser = userRepo.save(user);

        return savedUser.getId();
    }

    public void update(long id, String name, String password, Integer tournament_played) throws IllegalArgumentException {
        final Optional<User> maybeUser = userRepo.findById(id);

        if (maybeUser.isEmpty()) throw new IllegalArgumentException("User not found");

        final User user = maybeUser.get();
        if (name != null && !name.isBlank()) user.setName(name);
        if (password != null && !password.isBlank()) user.setPassword(password);
        if (tournament_played != null) user.setTournament_played(tournament_played);

        userRepo.save(user);
    }

    public void delete(long id) { userRepo.deleteById(id); }
}
