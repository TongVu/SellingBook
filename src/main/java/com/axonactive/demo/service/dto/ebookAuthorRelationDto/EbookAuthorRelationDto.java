package com.axonactive.demo.service.dto.ebookAuthorRelationDto;

import com.axonactive.demo.entity.Gender;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EbookAuthorRelationDto {

    private String ebookTitle;

    private Integer ebookPage;

    private String ebookIntroduction;

    private String ebookPublisherName;

    private String authorFullName;

    private String authorEmail;

    private Gender authorGender;

}
