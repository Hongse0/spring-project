package com.seyeong.seyeongmidterm;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

@Controller
public class resultController {

    @PostMapping("result")
    public String saveStudentInfo(@ModelAttribute("student") StudentDTO studentDTO ,@RequestParam("photo") MultipartFile photo) {
        String root = "src/main/resources/static";
        String filePath = root + "/uploadFiles";
        File dir = new File(filePath);

        if (!dir.exists()) {
            dir.mkdirs();
        }

        String fileName = studentDTO.getName() + ".csv";
        String fileFullPath = filePath + "/" + fileName;

        try (FileWriter fileWriter = new FileWriter(fileFullPath)) {
            fileWriter.write("이름,나이,전화번호,대학교,거주지,상세사항\n");
            fileWriter.write(studentDTO.getName() + ",");
            fileWriter.write(studentDTO.getAge() + ",");
            fileWriter.write(studentDTO.getPhone() + ",");
            fileWriter.write(studentDTO.getUniversity() + ",");
            fileWriter.write(studentDTO.getResidence() + ",");
            fileWriter.write(studentDTO.getDetail() + "\n");
            fileWriter.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (!photo.isEmpty()) {
            String photoFileName = studentDTO.getName() + "_" + photo.getOriginalFilename();
            String photoFilePath = filePath + "/" + photoFileName;
            try {
                photo.transferTo(new File(photoFilePath));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }


        return "result2";
    }


}
