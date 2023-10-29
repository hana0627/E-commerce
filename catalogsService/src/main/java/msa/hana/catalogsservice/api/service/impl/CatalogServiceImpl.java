package msa.hana.catalogsservice.api.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import msa.hana.catalogsservice.api.dto.response.CatalogResponse;
import msa.hana.catalogsservice.api.repository.CatalogRepository;
import msa.hana.catalogsservice.api.service.CatalogService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Service
public class CatalogServiceImpl implements CatalogService {

    private final CatalogRepository catalogRepository;
    @Override
    public List<CatalogResponse> getCatalogs() {
        return catalogRepository.findAll()
                .stream()
                .map(CatalogResponse::of).toList();

    }
}
