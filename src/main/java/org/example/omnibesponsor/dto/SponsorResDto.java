package org.example.omnibesponsor.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.example.omnibesponsor.entity.Sponsor;

public class SponsorResDto {

    @Getter
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class CreateSponsor {

        private Long memberId;
        private Long sponsorId;
        private String sponsorName;
        private Long categoryId;


        public static CreateSponsor from(Sponsor sponsor) {
            return new CreateSponsor(
                    sponsor.getMemberId(),
                    sponsor.getSponsorId(),
                    sponsor.getSponsorName(),
                    sponsor.getCategory().getCategoryId()
            );
        }

    }

    @Getter
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class GetSponsorId {

        private Long SponsorId;

    }

}
