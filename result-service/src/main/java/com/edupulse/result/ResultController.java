package com.edupulse.result;
import java.util.List;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
@RestController
@RequestMapping("/results")
public class ResultController {
 private final ResultRepository repo;
 public ResultController(ResultRepository repo){this.repo=repo;}
 @GetMapping("/student/{studentId}") public List<Result> byStudent(@PathVariable Long studentId){return repo.findByStudentId(studentId);}
 @GetMapping public List<Result> all(){return repo.findAll();}
 @PostMapping @PreAuthorize("hasAnyRole('ADMIN','FACULTY')")
 public Result create(@RequestBody Result r){
   if(r.getMarks()==null||r.getMarks()<0||r.getMarks()>100) throw new IllegalArgumentException("Marks must be 0-100");
   r.setGrade(calculateGrade(r.getMarks())); return repo.save(r);
 }
 @PutMapping("/{id}") @PreAuthorize("hasAnyRole('ADMIN','FACULTY')")
 public Result update(@PathVariable Long id,@RequestBody Result r){
   Result x=repo.findById(id).orElseThrow();x.setSubject(r.getSubject());x.setMarks(r.getMarks());x.setGrade(calculateGrade(r.getMarks()));return repo.save(x);
 }
 @DeleteMapping("/{id}") @PreAuthorize("hasRole('ADMIN')")
 public void delete(@PathVariable Long id){repo.deleteById(id);}
 private String calculateGrade(int m){if(m>=90)return"A+";if(m>=80)return"A";if(m>=70)return"B";if(m>=60)return"C";if(m>=50)return"D";return"F";}
}
