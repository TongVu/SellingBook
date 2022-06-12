package com.example.demo.controller.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EbookRequest {
    private Integer id;

    private Integer page;

    private String title;

    private Double rating;

    private String introduction;

    private Boolean purchased;

    private String viewLinkStatus;

    private Integer publisherId;

}
