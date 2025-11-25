package com.example.usermanager.controller;

import com.example.usermanager.entity.User;
import com.example.usermanager.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

//    @GetMapping
//    public List<User> list() {
//        return userService.findAll();
//    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getById(@PathVariable Long id) {
        User u = userService.findById(id);
        if (u == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(u);
    }

    @PostMapping
    public ResponseEntity<User> create(@RequestBody User user) {
        userService.create(user);
        return ResponseEntity.ok(user);
    }

    @PutMapping("/{id}")
    public ResponseEntity<User> update(@PathVariable Long id, @RequestBody User user) {
        user.setId(id);
        boolean ok = userService.update(user);
        if (!ok) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(user);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        boolean ok = userService.delete(id);
        if (!ok) return ResponseEntity.notFound().build();
        return ResponseEntity.ok().build();
    }

//    @GetMapping
//public List<User> list(@RequestParam(required=false) String keyword,
//                       @RequestParam(required=false) String tag) {
//    if ((keyword != null && !keyword.isEmpty()) || (tag != null && !tag.isEmpty())) {
//        return userService.search(keyword, tag);
//    }
//    return userService.findAll();
//}

    // TODO：新增查询和更新
     @GetMapping
     public List<User> list(@RequestParam(required=false) String keyword,
                         @RequestParam(required=false) String tag) {
         if ((keyword != null && !keyword.isEmpty()) || (tag != null && !tag.isEmpty())) {
             return userService.search(keyword, tag);
         }
         return userService.findAll();
     }
     @PutMapping("/{id}/tags")
     public ResponseEntity<Void> updateTags(@PathVariable Long id, @RequestBody Map<String, Object> body) {
         String tags = (String) body.get("tags"); // 逗号分隔字符串
         boolean ok = userService.updateTags(id, tags);
         if (!ok) return ResponseEntity.notFound().build();
         return ResponseEntity.ok().build();
     }

}