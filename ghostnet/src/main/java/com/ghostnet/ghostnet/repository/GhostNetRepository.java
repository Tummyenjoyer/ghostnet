package com.ghostnet.ghostnet.repository;

import com.ghostnet.ghostnet.model.GhostNet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GhostNetRepository extends JpaRepository<GhostNet, Long> {
}