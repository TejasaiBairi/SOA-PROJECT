package com.edupulse.attendance;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
public interface AttendanceRepository extends JpaRepository<Attendance,Long>{List<Attendance> findByStudentId(Long studentId);}
