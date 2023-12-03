package com.swiggy.controllers;

import com.razorpay.Order;
import com.razorpay.RazorpayClient;
import com.razorpay.RazorpayException;
import com.razorpay.Utils;
import com.swiggy.dtos.PaymentDto;
import com.swiggy.dtos.PaymentVerification;
import com.swiggy.dtos.ProductDto;
import com.swiggy.entities.Payment;
import com.swiggy.service.PaymentService;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/***
 * Main Payment RestController
 */
@CrossOrigin
@RestController
@RequestMapping("/api/v1")
public class PaymentController {

    @Value("${payment.id}")
    private String id;

    @Value("${payment.secret}")
    private String secret;

    @Autowired
    private PaymentService paymentService;


    /***
     * create an order on rayzorpay
     * @param product is product with amount
     * @return Response with message
     */
    @PostMapping("/payment/createOrder")
    public ResponseEntity<?> createOrder(@RequestBody ProductDto product) {
        try {
            System.out.println(product.getAmount());
            System.out.println(id + " " + secret);
            Double amount = Double.parseDouble(product.getAmount());
            RazorpayClient client = new RazorpayClient(id, secret);
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("amount", amount * 100);
            jsonObject.put("currency", "INR");
            jsonObject.put("receipt", "txn_232485");

            Order order = client.orders.create(jsonObject);
            System.out.println(HttpStatus.OK);
            System.out.println("data " + order.toString());
            return new ResponseEntity<String>(order.toString(), HttpStatus.OK);
        } catch (Exception e) {
            System.out.println(e);
            return new ResponseEntity<>("Something Went Wrong", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /***
     * saves payments in the db
     * @param payment is object to be saved
     * @return response with message
     */
    @PostMapping("/addPaymentDetails")
    public ResponseEntity<?> addPaymentDetails(@RequestBody Payment payment) {
        System.out.println("...." + payment);
        try {
            payment.setPaymentStatus(true);
            payment.setDate(LocalDate.now());
            payment.setTimestamp(new Timestamp(System.currentTimeMillis()));
            Payment savedPayment = paymentService.addPayment(payment);

            return new ResponseEntity<>(savedPayment, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Something Went Wrong", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /***
     * returns payment by id
     * @param id is payment id {id}
     * @return a payment dto with payment info
     */
    @GetMapping("/getPaymentById/{id}")
    public ResponseEntity<?> getPaymentById(@PathVariable("id") Long id) {
        try {
            Payment paymentById = paymentService.getPaymentById(id);
            PaymentDto paymentDto = new PaymentDto();
            paymentDto.setPaymentId(paymentById.getPaymentId());
            paymentDto.setOrderId(paymentById.getOrderId());
            paymentDto.setSignature(paymentById.getSignature());
            paymentDto.setDate(paymentById.getDate());
            paymentDto.setTimestamp(paymentById.getTimestamp());
            return new ResponseEntity<>(paymentDto, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Something Went Wrong", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    /***
     * returns all the payments present in db
     * @return all the payments present in db
     */
    @GetMapping("/getAllPayments")
    public ResponseEntity<?> getAllPayments() {
        try {
            List<Payment> payments = paymentService.getAllPayments();
            List<PaymentDto> allPayments = new ArrayList<>();
            for (Payment paymentById : payments) {
                PaymentDto paymentDto = new PaymentDto();
                paymentDto.setId(paymentById.getId());
                paymentDto.setPaymentId(paymentById.getPaymentId());
                paymentDto.setOrderId(paymentById.getOrderId());
                paymentDto.setSignature(paymentById.getSignature());
                paymentDto.setDate(paymentById.getDate());
                paymentDto.setTimestamp(paymentById.getTimestamp());
                allPayments.add(paymentDto);
            }
            return new ResponseEntity<>(allPayments, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Something Went Wrong", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    /***
     * delete payment by id
     * @param id is payment id {id}
     * @return response with message
     */
    @DeleteMapping("/deletePaymentById/{id}")
    public ResponseEntity<?> deletePaymentById(@PathVariable("id") Long id) {
        try {
            paymentService.deletePaymentById(id);
            return new ResponseEntity<>("Payment Deleted Successfully", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Something Went Wrong", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/payment/success")
    public ResponseEntity<String> getResult(@RequestBody PaymentVerification paymentVerification) throws RazorpayException {
        RazorpayClient razorpay = new RazorpayClient(id, secret);
        String secret = this.secret;

        JSONObject options = new JSONObject();
        options.put("razorpay_order_id", paymentVerification.getOrderCreationId());
        options.put("razorpay_payment_id", paymentVerification.getRazorpayPaymentId());
        options.put("razorpay_signature", paymentVerification.getRazorpaySignature());

        System.out.println("options \n"+options);

        boolean status =  Utils.verifyPaymentSignature(options, secret);
        if(status)
            return new ResponseEntity<>("Success", HttpStatus.OK);
        return new ResponseEntity<>("Failed", HttpStatus.OK);
    }
}