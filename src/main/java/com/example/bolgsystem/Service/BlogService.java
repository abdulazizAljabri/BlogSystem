package com.example.bolgsystem.Service;

import com.example.bolgsystem.Api.ApiException;
import com.example.bolgsystem.Model.User;
import com.example.bolgsystem.Model.Blog;
import com.example.bolgsystem.Repository.BlogRepository;
import com.example.bolgsystem.Repository.AuthRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class BlogService {
    private final BlogRepository blogRepository;
    private final AuthRepository authRepository;

    public List<Blog> getBlogList() {
        return blogRepository.findAll();
    }

    public List<Blog> getBlogsByUser(Integer id) {
        User user = authRepository.findUserById(id);
        return blogRepository.findAllByUser(user);
    }

    public void addBlog(Integer user_id, Blog blog) {
        User user = authRepository.findUserById(user_id);
        blog.setUser(user);
        blogRepository.save(blog);
    }

    public void removeBlog(Integer user_id, Integer blog_id) {
        User user = authRepository.findUserById(user_id);
        Blog blogs = blogRepository.findBlogById(blog_id);
        if (blogs == null) {
            throw new ApiException("Could not find blog");
        } else if (!blogs.getUser().getId().equals(user.getId())) {
            throw new ApiException(" not found : " +blogs.getTitle() );

        } else {
            blogRepository.delete(blogs);
        }
    }

    public void updateBlog(Integer user_id, Integer blog_id, Blog blog) {
        User user = authRepository.findUserById(user_id);
        Blog blog1 = blogRepository.findBlogById(blog_id);
        if (blog1 == null) {
            throw new ApiException("Could not find blog");
        } else if (blog1.getUser().getId().equals(user.getId())) {
            blog1.setTitle(blog.getTitle());
            blog1.setBody(blog.getBody());
            blogRepository.save(blog1);
        } else {
            throw new ApiException(" not found : ");
        }
    }

    public Blog SearchByTitle(Integer user_id, String title) {
        User user = authRepository.findUserById(user_id);
        Blog blog1 = blogRepository.findBlogByTitle(title);
        if (blog1 == null) {
            throw new ApiException("Could not find blog");
        } else if (!blog1.getUser().getId().equals(user.getId())) {
            throw new ApiException("not found : ");
        }
        return blog1;
    }

    public Blog searchById(Integer user_id,Integer blog_id) {
        User user = authRepository.findUserById(user_id);
        Blog blog1 = blogRepository.findBlogById(blog_id);
        if (blog1 == null) {
            throw new ApiException("Could not find blog");
        } else if (!blog1.getUser().getId().equals(user.getId())) {
            throw new ApiException(" not found : ");
        }
        return blog1;
    }
}
