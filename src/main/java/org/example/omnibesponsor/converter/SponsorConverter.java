package org.example.omnibesponsor.converter;

import org.example.omnibesponsor.dto.SponsorReqDto;
import org.example.omnibesponsor.entity.Category;
import org.example.omnibesponsor.entity.Sponsor;

public class SponsorConverter {

    public static Sponsor toSponsor(SponsorReqDto.CreateSponsor dto, Category category) {
        return Sponsor.builder()
                .memberId(dto.getMemberId())
                .sponsorName(dto.getSponsorName())
                .sponsorNumber(dto.getSponsorNumber())
                .category(category)
                .build();
    }

}
