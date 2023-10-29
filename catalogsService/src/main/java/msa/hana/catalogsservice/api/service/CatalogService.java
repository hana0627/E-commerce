package msa.hana.catalogsservice.api.service;

import msa.hana.catalogsservice.api.dto.response.CatalogResponse;

import java.util.List;

public interface CatalogService {

    List<CatalogResponse> getCatalogs();
}
