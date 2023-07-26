package com.example.pharmacyRecommend.pharmacy.service;

import com.example.pharmacyRecommend.api.dto.DocumentDto;
import com.example.pharmacyRecommend.api.dto.KakaoApiResponseDto;
import com.example.pharmacyRecommend.api.service.KakaoAddressSearchService;
import com.example.pharmacyRecommend.direction.entity.Direction;
import com.example.pharmacyRecommend.direction.service.DirectionService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import java.util.List;
import java.util.Objects;

@Slf4j
@Service
@RequiredArgsConstructor
public class PharmacyRecommendationService {

    private static final String ROAD_VIEW_BASE_URL = "https://map.kakao.com/link/roadview/";

    private final KakaoAddressSearchService kakaoAddressSearchService;
    private final DirectionService directionService;

    public void recommendPharmacyList(String address) {
        KakaoApiResponseDto kakaoApiResponseDto = kakaoAddressSearchService.requestAddressSearch(address);

        if (Objects.isNull(kakaoApiResponseDto) || CollectionUtils.isEmpty(kakaoApiResponseDto.getDocumentList())) {
            log.error("[PharmacyRecommendationService.recommendPharmacyList fail] Input address: {}", address);
        }

        DocumentDto documentDto = kakaoApiResponseDto.getDocumentList().get(0);

        List<Direction> directionList = directionService.buildDirectionList(documentDto);
        directionService.saveAll(directionList);
    }
}
