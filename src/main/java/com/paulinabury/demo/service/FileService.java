package com.paulinabury.demo.service;

import com.paulinabury.demo.model.Student;

import java.io.File;
import java.util.List;

public interface FileService {
    File saveToFile(List<Student> students, File file);
}
