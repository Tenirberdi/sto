package com.sto.repair.repository;

import com.sto.repair.model.constant.IssueCode;
import com.sto.repair.model.entity.IssueCodeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface IssueCodeRepository extends JpaRepository<IssueCodeEntity, IssueCode> {
}
