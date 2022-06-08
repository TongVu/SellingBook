package com.example.demo.service.dto.ebookDto;

import com.example.demo.entity.Publisher;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EbookDto {
    private Integer id;

    private Integer page;

    private String title;

    private Integer rating;

    private String introduction;

    private Boolean purchased;

    private String viewLinkStatus;

    private Publisher publisher;
}
