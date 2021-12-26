package com.example.msitian.ebook;

public class PlacementData {
    private String placementPdfUrl, placementYear;

    public PlacementData() {
    }

    public PlacementData(String placementPdfUrl, String placementYear) {
        this.placementPdfUrl = placementPdfUrl;
        this.placementYear = placementYear;
    }

    public String getPlacementPdfUrl() {
        return placementPdfUrl;
    }

    public void setPlacementPdfUrl(String placementPdfUrl) {
        this.placementPdfUrl = placementPdfUrl;
    }

    public String getPlacementYear() {
        return placementYear;
    }

    public void setPlacementYear(String placementYear) {
        this.placementYear = placementYear;
    }
}
