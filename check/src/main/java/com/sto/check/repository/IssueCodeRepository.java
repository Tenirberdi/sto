package com.sto.check.repository;

import com.sto.check.model.constant.IssueCode;
import com.sto.check.model.entity.IssueCodeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IssueCodeRepository extends JpaRepository<IssueCodeEntity, IssueCode> {
}
