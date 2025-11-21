package com.ecommerce.ecommerce.controller;

import com.ecommerce.ecommerce.model.Analytics;
import com.ecommerce.ecommerce.service.AnalyticsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@SuppressWarnings("unused")
@RestController
@RequestMapping("/api/analytics")
@RequiredArgsConstructor
public class AnalyticsController {

    private final AnalyticsService analyticsService;

    /**
     * CREATE analytics entry (manual or automated)
     */
    @PostMapping
    public ResponseEntity<Analytics> createAnalytics(@RequestBody Analytics analytics) {
        return ResponseEntity.ok(analyticsService.createAnalytics(analytics));
    }

    /**
     * GET ALL analytics entries
     */
    @GetMapping
    public ResponseEntity<List<Analytics>> getAllAnalytics() {
        return ResponseEntity.ok(analyticsService.getAllAnalytics());
    }

    /**
     * GET analytics by ID
     */
    @GetMapping("/{id}")
    public ResponseEntity<Analytics> getAnalyticsById(@PathVariable Long id) {
        return ResponseEntity.ok(analyticsService.getAnalyticsById(id));
    }

    /**
     * UPDATE analytics
     */
    @PutMapping("/{id}")
    public ResponseEntity<Analytics> updateAnalytics(
            @PathVariable Long id,
            @RequestBody Analytics analytics
    ) {
        return ResponseEntity.ok(analyticsService.updateAnalytics(id, analytics));
    }

    /**
     * DELETE analytics
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAnalytics(@PathVariable Long id) {
        analyticsService.deleteAnalytics(id);
        return ResponseEntity.noContent().build();
    }
}
