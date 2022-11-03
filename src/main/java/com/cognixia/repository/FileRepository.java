package com.cognixia.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cognixia.model.File;

@Repository
public interface FileRepository extends JpaRepository<File, String> {

}
