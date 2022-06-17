package com.axonactive.demo.service.dto.ebookDto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EbookPurchasedDto {

    private String title;

    private Double rating;

    private String introduction;

    private Double price;

    private String viewLinkStatus;

}
