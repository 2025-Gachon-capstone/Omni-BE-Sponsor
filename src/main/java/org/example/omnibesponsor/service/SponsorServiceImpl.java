package org.example.omnibesponsor.service;

import org.example.omnibesponsor.common.apiPayload.code.status.ErrorStatus;
import org.example.omnibesponsor.common.apiPayload.exception.GeneralException;
import org.example.omnibesponsor.converter.SponsorConverter;
import org.example.omnibesponsor.dto.SponsorReqDto;
import org.example.omnibesponsor.dto.SponsorResDto;
import org.example.omnibesponsor.entity.Category;
import org.example.omnibesponsor.entity.Sponsor;
import org.example.omnibesponsor.repository.CategoryRepository;
import org.example.omnibesponsor.repository.SponsorRepository;
import org.springframework.stereotype.Service;

@Service
public class SponsorServiceImpl implements SponsorService {

    private final SponsorRepository sponsorRepository;
    private final CategoryRepository categoryRepository;

    public SponsorServiceImpl(SponsorRepository sponsorRepository, CategoryRepository categoryRepository) {
        this.sponsorRepository = sponsorRepository;
        this.categoryRepository = categoryRepository;
    }

    @Override
    public SponsorResDto.CreateSponsor createSponsor(SponsorReqDto.CreateSponsor createSponsorDto) {

        if (sponsorRepository.existsByMemberId(createSponsorDto.getMemberId())) {
            throw new GeneralException(ErrorStatus._ALREADY_EXIST_SPONSOR);
        }

        Category category = categoryRepository.findById(createSponsorDto.getCategoryId())
                .orElseThrow(() -> new GeneralException(ErrorStatus._CATEGORY_NOT_FOUND));

        Sponsor sponsor = SponsorConverter.toSponsor(createSponsorDto,category);

        Sponsor savedSponsor = sponsorRepository.save(sponsor);

        return SponsorResDto.CreateSponsor.from(savedSponsor);
    }

    @Override
    public SponsorResDto.GetSponsorId getSponsorId(Long memberId) {

        Sponsor sponsor = sponsorRepository.findByMemberId(memberId)
                .orElseThrow(()-> new GeneralException(ErrorStatus._NOT_FOUND_SPONSOR));

        return new SponsorResDto.GetSponsorId(sponsor.getSponsorId());
    }


}
