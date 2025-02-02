package com.sto.repair.util;

import com.sto.repair.model.Attachment;
import com.sto.repair.model.entity.AttachmentEntity;

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
