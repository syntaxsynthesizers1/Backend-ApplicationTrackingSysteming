package com.group4.ApplicationTrackingSytem.repositories;

import com.group4.ApplicationTrackingSytem.entity.Resume;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ResumeRepository extends JpaRepository<Resume,Long > {
}
