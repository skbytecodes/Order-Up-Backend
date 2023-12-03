package com.swiggy.service;

import com.swiggy.entities.Payment;
import com.swiggy.repo.PaymentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


/***
 * Main Payment Service
 */
@Service
public class PaymentService {
    @Autowired
    private PaymentRepo paymentRepo;


    /***
     * saves payment details in db
     * @param payment is an entity to be saved
     * @return saved payment
     */
    public Payment addPayment(Payment payment){
        return paymentRepo.save(payment);
    }


    /***
     *
     * @return a list of payments of an institute
     */
    public List<Payment> getAllPayments(){
        return paymentRepo.findAll();
    }


    /***
     * returns a payment by id
     * @param id is payment id
     * @return payment by id
     */
    public Payment getPaymentById(Long id){
        return paymentRepo.findById(id).get();
    }


    /***
     * deletes a payment by id
     * @param id is payment id
     */
    public void deletePaymentById(Long id){
        paymentRepo.deleteById(id);
    }

}
