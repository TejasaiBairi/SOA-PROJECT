package com.edupulse.attendance;
import jakarta.persistence.*;
import java.time.LocalDate;
@Entity
public class Attendance {
 @Id @GeneratedValue(strategy=GenerationType.IDENTITY) private Long attendanceId;
 private Long studentId; private LocalDate date; private String status;
 public Attendance(){} public Long getAttendanceId(){return attendanceId;} public Long getStudentId(){return studentId;} public LocalDate getDate(){return date;} public String getStatus(){return status;}
 public void setStudentId(Long v){studentId=v;} public void setDate(LocalDate v){date=v;} public void setStatus(String v){status=v;}
}
