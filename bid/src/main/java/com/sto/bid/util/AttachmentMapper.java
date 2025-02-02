package com.sto.bid.util;

import com.sto.bid.model.entity.AttachmentEntity;
import com.sto.bid.model.rest.Attachment;
import com.sto.bid.model.rest.request.AttachmentRequest;

public class AttachmentMapper {
    public static Attachment entityToDto(AttachmentEntity attachmentEntity) {
        return Attachment.builder()
                .id(attachmentEntity.getId())
                .fileName(attachmentEntity.getFileName())
                .fileType(attachmentEntity.getFileType())
                .url(attachmentEntity.getUrl()).build();
    }

    public static AttachmentEntity dtoToEntity(AttachmentRequest attachmentRequest) {
        return AttachmentEntity.builder()
                .fileName(attachmentRequest.getFileName())
                .fileType(attachmentRequest.getFileType())
                .url(attachmentRequest.getUrl()).build();
    }
}
