package com.rest.repository;
import com.rest.models.payment;
import java.util.List;
import java.util.ArrayList;
public class paymentrepo {
    private final List<payment> payments = new ArrayList<>();
    public void save(payment payment){
        payments.add(payment);
    }
    public List<payment> findAll(){
        return new ArrayList<>(payments);
    }
    public payment findById(int id){
        for(payment payment : payments){
            if(payment.getId() == id){
                return payment;
            }
        }
        return null;
    }

    
    
}