package msa.hana.catalogsservice.api.controller;

import msa.hana.catalogsservice.api.dto.response.CatalogResponse;
import msa.hana.catalogsservice.api.service.CatalogService;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequestMapping("/catalog-service")
@RestController
public class CatalogController {

    private final Environment env;
    private final CatalogService catalogService;

    public CatalogController(Environment env, CatalogService catalogService) {
        this.env = env;
        this.catalogService = catalogService;
    }

    @GetMapping("/health_check")
    public String status() {
        return "It's Working in User Service on PORT " + env.getProperty("local.server.port");
    }

    @GetMapping("/catalogs")
    public ResponseEntity<List<CatalogResponse>> users() {

        List<CatalogResponse> result = catalogService.getCatalogs();

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(result);
    }

}
