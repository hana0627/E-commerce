package msa.hana.catalogsservice.api.dto.response;

import msa.hana.catalogsservice.api.domain.Catalog;

import java.time.LocalDateTime;

public record CatalogResponse(
        String productId,
        String productName,
        Integer unitPrice,
        Integer stock,
        LocalDateTime createdAt

) {

    public static CatalogResponse of(Catalog catalog) {
        return new CatalogResponse(catalog.getProductId(), catalog.getProductName(), catalog.getUnitPrice(),catalog.getStock(),catalog.getCreatedAt());
    }

    public CatalogResponse(String productId, String productName, Integer unitPrice, Integer stock, LocalDateTime createdAt) {
        this.productId = productId;
        this.productName = productName;
        this.unitPrice = unitPrice;
        this.stock = stock;
        this.createdAt = createdAt;
    }
}
