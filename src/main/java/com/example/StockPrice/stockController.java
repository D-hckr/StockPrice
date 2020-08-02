package com.example.StockPrice;

import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.io.ByteArrayInputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class stockController {

    @Autowired
    public processClass processClass;

    @Autowired
    public fetchStock fetchStock;

    @Autowired
    public stockService stockService;

    @GetMapping(value = "/{stockName}")
    public ModelAndView getStockName(@PathVariable String stockName) throws Exception{
        List<String>  companiesList = processClass.getStockList(stockName);
        Map<String,fetchStock> finalMap = new HashMap<>();
        finalMap = fetchStock.getStockDetails(companiesList);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("StockList",finalMap.values());
        modelAndView.setViewName("home1");
        companiesList.clear();
        return modelAndView;
    }

    @GetMapping("/getTable")
    public ModelAndView getFinalTable(){
        List<stockDetails> stockDetailsList = new ArrayList<>();
        stockDetailsList = stockService.getTable();
        ModelAndView modelAndView = new ModelAndView("printTable");
        modelAndView.addObject("finalTable", stockDetailsList);
        return modelAndView;
    }

    @GetMapping("/count")
    public ModelAndView getCount(){
        ModelAndView modelAndView = new ModelAndView("tableCount");
        modelAndView.addObject("count",stockService.count());
        return modelAndView;
    }

    /*@GetMapping(value = "/pdf/getTable",produces = MediaType.APPLICATION_PDF_VALUE)
    public ResponseEntity<InputStreamResource> getFinalTablePDF(){
        List<stockDetails> stockDetailsList = new ArrayList<>();
        stockDetailsList = stockService.getTable();
        ByteArrayInputStream bis = GeneratePdfReports.citiesReport(stockDetailsList);
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "inline; filename=stockData.pdf");
        headers.setContentType(MediaType.APPLICATION_PDF);

        return ResponseEntity
                .ok()
                .headers(headers)
                .contentType(MediaType.APPLICATION_PDF)
                .body(new InputStreamResource(bis));
    }*/

    @GetMapping(value = "/pdf/getTable",produces = MediaType.APPLICATION_PDF_VALUE)
    public ResponseEntity<InputStreamResource> getFinalTablePDF(){
        List<stockDetails> stockDetailsList = new ArrayList<>();
        stockDetailsList = stockService.getTable();
        ByteArrayInputStream bis = GeneratePdfReports.citiesReport(stockDetailsList);
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "inline; filename=stockData.pdf");
        headers.setContentType(MediaType.APPLICATION_PDF);
        //download pdf
        headers.setContentDispositionFormData("attachment", "stockData.pdf");
        return ResponseEntity
                .ok()
                .headers(headers)
                .contentType(MediaType.APPLICATION_PDF)
                .body(new InputStreamResource(bis));
    }
}
