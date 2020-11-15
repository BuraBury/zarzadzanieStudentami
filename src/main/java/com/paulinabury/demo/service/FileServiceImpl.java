package com.paulinabury.demo.service;

import com.paulinabury.demo.model.Student;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

@Service
@Scope("singleton")
public class FileServiceImpl implements FileService {


    @Override
    public File saveToFile(List<Student> students, File file) {
        try {
            FileWriter writer = new FileWriter(file);
            writer.write(students.toString());
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return file;
    }


}
