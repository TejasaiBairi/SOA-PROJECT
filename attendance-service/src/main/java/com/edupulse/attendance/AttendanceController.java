package com.edupulse.attendance;
import java.time.LocalDate;
import java.util.*;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
@RestController
@RequestMapping("/attendance")
public class AttendanceController {
 private final AttendanceRepository repo; private final StudentClient students;
 public AttendanceController(AttendanceRepository repo,StudentClient students){this.repo=repo;this.students=students;}
 @GetMapping("/student/{studentId}") public List<Attendance> byStudent(@PathVariable Long studentId){return repo.findByStudentId(studentId);}
 @PostMapping @PreAuthorize("hasAnyRole('ADMIN','FACULTY')")
 public Attendance mark(@RequestBody Attendance a){
   var check=students.exists(a.getStudentId());
   if(!check.exists()) throw new IllegalArgumentException("Student does not exist");
   if(a.getDate()==null)a.setDate(LocalDate.now());
   if(a.getStatus()==null)a.setStatus("PRESENT");
   return repo.save(a);
 }
 @GetMapping("/student/{studentId}/overview")
 public Map<String,Object> overview(@PathVariable Long studentId){
   var attendance=repo.findByStudentId(studentId);
   var studentSummary=students.summary(studentId);
   return Map.of("attendance",attendance,"studentAndResults",studentSummary);
 }
}
