package com.example.fileUpload.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.fileUpload.entity.FileUpload;

public interface FileUploadRepository extends JpaRepository<FileUpload, Long> {

	Optional<FileUpload> findByName(String fileName);

}
