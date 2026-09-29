package com.edupulse.attendance;
import com.edupulse.attendance.config.FeignSecurityConfig;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;
@FeignClient(name="STUDENT-SERVICE",configuration=FeignSecurityConfig.class)
public interface StudentClient {
 @GetMapping("/students/{id}/exists") StudentExists exists(@PathVariable Long id);
 @GetMapping("/students/{id}/summary") Object summary(@PathVariable Long id);
 record StudentExists(Long studentId,boolean exists){}
}
