package com.edupulse.student;
import java.util.*;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
@RestController
@RequestMapping("/students")
public class StudentController {
 private final StudentRepository repo; private final ResultClient results;
 public StudentController(StudentRepository repo,ResultClient results){this.repo=repo;this.results=results;}
 @GetMapping public List<Student> all(){return repo.findAll();}
 @GetMapping("/{id}") public Student one(@PathVariable Long id){return repo.findById(id).orElseThrow();}
 @GetMapping("/{id}/exists") public Map<String,Object> exists(@PathVariable Long id){
   return Map.of("studentId",id,"exists",repo.existsById(id));
 }
 @PostMapping @PreAuthorize("hasAnyRole('ADMIN','FACULTY')")
 public Student create(@RequestBody Student s){return repo.save(s);}
 @PutMapping("/{id}") @PreAuthorize("hasAnyRole('ADMIN','FACULTY')")
 public Student update(@PathVariable Long id,@RequestBody Student s){
   Student x=repo.findById(id).orElseThrow(); x.setName(s.getName());x.setStudentClass(s.getStudentClass());x.setSection(s.getSection());return repo.save(x);
 }
 @DeleteMapping("/{id}") @PreAuthorize("hasRole('ADMIN')")
 public void delete(@PathVariable Long id){repo.deleteById(id);}
 @GetMapping("/{id}/summary")
 public Map<String,Object> summary(@PathVariable Long id){
   Student s=repo.findById(id).orElseThrow();
   return Map.of("student",s,"results",results.getResults(id));
 }
}
