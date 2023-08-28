package com.example.bolgsystem.Controller;


import com.example.bolgsystem.Api.ApiResponse;
import com.example.bolgsystem.Model.User;
import com.example.bolgsystem.Model.Blog;
import com.example.bolgsystem.Service.BlogService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/blog")
@RequiredArgsConstructor
public class BlogController {
    private final BlogService blogService;

    @GetMapping("/")
    public ResponseEntity getAllBlog() {
        return ResponseEntity.status(HttpStatus.OK).body(blogService.getBlogList());
    }

    @GetMapping("/myblog")
    public ResponseEntity getMyBlog(@AuthenticationPrincipal User user) {
        return ResponseEntity.status(HttpStatus.OK).body(blogService.getBlogsByUser(user.getId()));
    }

    @PostMapping("/add")
    public ResponseEntity addBlog(@AuthenticationPrincipal User user, @RequestBody @Valid Blog blog) {
        blogService.addBlog(user.getId(), blog);
        return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse("Blog added"));
    }

    @DeleteMapping("/{blog_id}")
    public ResponseEntity deleteBlog(@AuthenticationPrincipal User user, @PathVariable Integer blog_id) {
        blogService.removeBlog(user.getId(), blog_id);
        return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse("Blog deleted"));
    }

    @PutMapping("/{blog_id}")
    public ResponseEntity updateBlog(@AuthenticationPrincipal User user, @PathVariable Integer blog_id, @RequestBody @Valid Blog blog) {
        blogService.updateBlog(user.getId(), blog_id, blog);
        return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse("Blog updated"));
    }

    @GetMapping("/search-by-title/{title}")
    public ResponseEntity searchByTitle(@AuthenticationPrincipal User user, @PathVariable String title) {
        return ResponseEntity.status(HttpStatus.OK).body(blogService.SearchByTitle(user.getId(), title));

    }

    @GetMapping("/search-by-id/{id}")
    public ResponseEntity searhcById(@AuthenticationPrincipal User user, @PathVariable Integer id) {
        return ResponseEntity.status(HttpStatus.OK).body(blogService.searchById(user.getId(), id));

    }


}
