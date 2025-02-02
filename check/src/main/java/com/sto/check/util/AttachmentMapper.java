package com.sto.check.util;

import com.sto.check.model.Attachment;
import com.sto.check.model.entity.AttachmentEntity;

public class AttachmentMapper {
    public static Attachment entityToDto(AttachmentEntity attachmentEntity) {
        return Attachment.builder()
                .id(attachmentEntity.getId())
                .fileName(attachmentEntity.getFileName())
                .fileType(attachmentEntity.getFileType())
                .url(attachmentEntity.getUrl()).build();
    }

    public static AttachmentEntity dtoToEntity(Attachment attachment) {
        return AttachmentEntity.builder()
                .id(attachment.getId())
                .fileName(attachment.getFileName())
                .fileType(attachment.getFileType())
                .url(attachment.getUrl()).build();
    }
}
