package com.edupulse.student;
import com.edupulse.student.config.FeignSecurityConfig;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import java.util.List;
@FeignClient(name="RESULT-SERVICE",configuration=FeignSecurityConfig.class)
public interface ResultClient {
 @GetMapping("/results/student/{studentId}")
 List<ResultDto> getResults(@PathVariable Long studentId);
 record ResultDto(Long resultId,Long studentId,String subject,Integer marks,String grade){}
}
