package com.seyeong.seyeongmidterm;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class StudentDTO {

    private String name;
    private String age;
    private String phone;
    private String university;
    private String residence;
    private String detail;
    private MultipartFile photo;
}
