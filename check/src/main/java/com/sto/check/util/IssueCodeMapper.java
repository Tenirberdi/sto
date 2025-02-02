package com.sto.check.util;

import com.sto.check.model.Issue;
import com.sto.check.model.entity.IssueCodeEntity;

public class IssueCodeMapper {
    public static IssueCodeEntity dtoToEntity(Issue issue) {
        return IssueCodeEntity.builder()
                .code(issue.getCode())
                .description(issue.getDescription())
                .repairTimeInHours(issue.getRepairTimeInHours())
                .price(issue.getPrice()).build();
    }

    public static Issue entityToDto(IssueCodeEntity issueCodeEntity) {
        return Issue.builder()
                .code(issueCodeEntity.getCode())
                .description(issueCodeEntity.getDescription())
                .repairTimeInHours(issueCodeEntity.getRepairTimeInHours())
                .price(issueCodeEntity.getPrice()).build();
    }
}
