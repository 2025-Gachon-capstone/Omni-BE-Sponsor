package org.example.omnibesponsor.dto;

import lombok.Getter;

public class SponsorReqDto {

    @Getter
    public static class CreateSponsor{

        private Long memberId;
        private String sponsorName;
        private String sponsorNumber;
        private Long categoryId;

    }

}
