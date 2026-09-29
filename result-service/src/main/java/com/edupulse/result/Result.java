package com.edupulse.result;
import jakarta.persistence.*;
@Entity
public class Result {
 @Id @GeneratedValue(strategy=GenerationType.IDENTITY) private Long resultId;
 private Long studentId; private String subject; private Integer marks; private String grade;
 public Result(){} public Long getResultId(){return resultId;} public Long getStudentId(){return studentId;} public String getSubject(){return subject;} public Integer getMarks(){return marks;} public String getGrade(){return grade;}
 public void setStudentId(Long v){studentId=v;} public void setSubject(String v){subject=v;} public void setMarks(Integer v){marks=v;} public void setGrade(String v){grade=v;}
}
