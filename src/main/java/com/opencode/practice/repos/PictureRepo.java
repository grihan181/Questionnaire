package com.opencode.practice.repos;

import com.opencode.practice.model.Picture;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PictureRepo extends JpaRepository<Picture,Long> {

}
