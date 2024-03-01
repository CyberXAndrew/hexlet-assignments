package exercise.controller;

import exercise.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import exercise.service.UserService;


@RestController
@RequestMapping("/users")
public class UsersController {

    @Autowired
    private UserService userService;

    @GetMapping(path = "")
    public Flux<User> getUsers() {
        return userService.findAll();
    }

    // BEGIN
//    создание нового пользователя, просмотр, редактирование и удаление пользователя
    @PostMapping(path = "")
    public Mono<User> createUser(@RequestBody User user) {
        return userService.save(user);
    }

    @GetMapping(path = "/{id}")
    public Mono<User> getUser(@PathVariable Long id) {
        return userService.show(id);
    }

    @PatchMapping(path = "/{id}")
    public Mono<User> updateUser(@RequestBody User user, @PathVariable Long id) {
//        Mono<User> userMono = userService.show(id);
//        User userFromDb = userMono.block(); // застрял тут, метод блок() лишний? Как вынуть сущность из репозитория?
//        userFromDb.setFirstName(user.getFirstName());
//        userFromDb.setLastName(user.getLastName());
//        userFromDb.setEmail(user.getEmail());
//
        return userService.update(user, id);
    }

    @DeleteMapping(path = "/{id}")
    public Mono<Void> deleteUser(@PathVariable Long id) {
        return userService.delete(id);
    }
    // END
}
