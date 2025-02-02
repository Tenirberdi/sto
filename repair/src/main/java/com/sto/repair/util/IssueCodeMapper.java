package com.sto.repair.util;

import com.sto.repair.model.Issue;
import com.sto.repair.model.entity.IssueCodeEntity;

public class IssueCodeMapper {
    public static IssueCodeEntity dtoToEntity(Issue issue) {
        return IssueCodeEntity.builder()
                .code(issue.getCode())
                .description(issue.getDescription())
                .repairTimeInHours(issue.getRepairTimeInHours()).build();
    }

    public static Issue entityToDto(IssueCodeEntity issueCodeEntity) {
        return Issue.builder()
                .code(issueCodeEntity.getCode())
                .description(issueCodeEntity.getDescription())
                .repairTimeInHours(issueCodeEntity.getRepairTimeInHours()).build();
    }
}
