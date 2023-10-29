package msa.hana.catalogsservice.api.repository;

import msa.hana.catalogsservice.api.domain.Catalog;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CatalogRepository extends JpaRepository<Catalog, Long> {
}
