package com.ecommerce.ecommerce.service;

import com.ecommerce.ecommerce.model.Analytics;
import com.ecommerce.ecommerce.repository.AnalyticsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AnalyticsService {

    private final AnalyticsRepository analyticsRepository;

    /**
     * CREATE analytics record manually or via scheduled cron job
     */
    public Analytics createAnalytics(Analytics analytics) {
        return analyticsRepository.save(analytics);
    }

    /**
     * GET ALL analytics records
     */
    public List<Analytics> getAllAnalytics() {
        return analyticsRepository.findAll();
    }

    /**
     * GET a single analytics record
     */
    public Analytics getAnalyticsById(Long id) {
        return analyticsRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Analytics record not found"));
    }

    /**
     * UPDATE analytics
     */
    public Analytics updateAnalytics(Long id, Analytics newData) {

        Analytics analytics = getAnalyticsById(id);

        analytics.setTotalUsers(newData.getTotalUsers());
        analytics.setTotalOrders(newData.getTotalOrders());
        analytics.setTotalRevenue(newData.getTotalRevenue());
        analytics.setTopSellingProducts(newData.getTopSellingProducts());

        return analyticsRepository.save(analytics);
    }

    /**
     * DELETE analytics
     */
    public void deleteAnalytics(Long id) {
        analyticsRepository.deleteById(id);
    }
}

