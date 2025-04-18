package org.example.omnibesponsor.service;

import org.example.omnibesponsor.dto.SponsorReqDto;
import org.example.omnibesponsor.dto.SponsorResDto;
import org.example.omnibesponsor.entity.Sponsor;

public interface SponsorService {

    SponsorResDto.CreateSponsor createSponsor(SponsorReqDto.CreateSponsor CreateSponsorDto);
    SponsorResDto.GetSponsorId getSponsorId(Long memberId);
}
