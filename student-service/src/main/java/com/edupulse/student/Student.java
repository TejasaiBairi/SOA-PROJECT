package com.edupulse.student;
import jakarta.persistence.*;
@Entity
public class Student {
 @Id @GeneratedValue(strategy=GenerationType.IDENTITY) private Long studentId;
 private String name; private String studentClass; private String section;
 public Student(){} public Student(String name,String studentClass,String section){this.name=name;this.studentClass=studentClass;this.section=section;}
 public Long getStudentId(){return studentId;} public String getName(){return name;} public String getStudentClass(){return studentClass;} public String getSection(){return section;}
 public void setName(String v){name=v;} public void setStudentClass(String v){studentClass=v;} public void setSection(String v){section=v;}
}
