package com.axonactive.demo.controller.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EbookRequest {

    @Min(1)
    private Integer page;

    @NotBlank
    private String title;

    @Min(0)
    @Max(5)
    private Double rating;

    @NotBlank
    private String introduction;

    private Boolean purchased;

    private String viewLinkStatus;

    private Integer publisherId;

}
