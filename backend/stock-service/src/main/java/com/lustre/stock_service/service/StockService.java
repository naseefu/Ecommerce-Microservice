package com.lustre.stock_service.service;

import com.lustre.stock_service.dto.StockDTO;
import com.lustre.stock_service.entity.Stocks;
import com.lustre.stock_service.repo.StockRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class StockService {

    @Autowired
    private StockRepo stockRepo;

    public String saveStock(Stocks stocks) {

        stockRepo.save(stocks);
        return "success";

    }

    public Stocks getStocksOfProduct(String id) {

        return stockRepo.findByProductId(id);

    }

    public boolean isStockAvailable(StockDTO stockDTO) {

        Stocks s =stockRepo.findByProductId(stockDTO.getProductId());

        if(stockDTO.getSize().equals("xsmall")){
            if(s.getXsmall()>=stockDTO.getQuantity()){
                s.setXsmall(s.getXsmall()-stockDTO.getQuantity());
                stockRepo.save(s);
                return true;
            }
            else{
                return false;
            }
        }
        else if(stockDTO.getSize().equals("small")){
            if(s.getSmall()>=stockDTO.getQuantity()){
                s.setSmall(s.getSmall()-stockDTO.getQuantity());
                stockRepo.save(s);
                return true;
            }
            else{
                return false;
            }
        }
        else if(stockDTO.getSize().equals("medium")){
            if(s.getMedium()>=stockDTO.getQuantity()){
                s.setMedium(s.getMedium()-stockDTO.getQuantity());
                stockRepo.save(s);
                return true;
            }
            else{
                return false;
            }
        }
        else if(stockDTO.getSize().equals("large")){
            if(s.getLarge()>=stockDTO.getQuantity()){
                s.setLarge(s.getLarge()-stockDTO.getQuantity());
                stockRepo.save(s);
                return true;
            }
            else{
                return false;
            }
        }
        else if(stockDTO.getSize().equals("xlarge")){
            if(s.getXlarge()>=stockDTO.getQuantity()){
                s.setXlarge(s.getXlarge()-stockDTO.getQuantity());
                stockRepo.save(s);
                return true;
            }
            else{
                return false;
            }
        }
        else{
            return false;
        }

    }

    public boolean updateStock(StockDTO stockDTO) {

        Stocks s =stockRepo.findByProductId(stockDTO.getProductId());

        if(s!=null) {

            if (stockDTO.getSize().equals("xsmall")) {
                    s.setXsmall(s.getXsmall() + stockDTO.getQuantity());
                    stockRepo.save(s);
                    return true;
            } else if (stockDTO.getSize().equals("small")) {
                    s.setSmall(s.getSmall() + stockDTO.getQuantity());
                    stockRepo.save(s);
                    return true;

            } else if (stockDTO.getSize().equals("medium")) {
                    s.setMedium(s.getMedium() + stockDTO.getQuantity());
                    stockRepo.save(s);
                    return true;
            } else if (stockDTO.getSize().equals("large")) {
                    s.setLarge(s.getLarge() + stockDTO.getQuantity());
                    stockRepo.save(s);
                    return true;
            } else if (stockDTO.getSize().equals("xlarge")) {
                    s.setXlarge(s.getXlarge() + stockDTO.getQuantity());
                    stockRepo.save(s);
                    return true;
            } else {
                return false;
            }
        }
        else{
            return false;
        }


    }
}
