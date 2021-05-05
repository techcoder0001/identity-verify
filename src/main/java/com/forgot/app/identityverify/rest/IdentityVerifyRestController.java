package com.forgot.app.identityverify.rest;

import com.forgot.app.identityverify.exception.ApiException;
import com.forgot.app.identityverify.model.IdentityVerifyRequest;
import com.forgot.app.identityverify.model.IdentityVerifyResponse;
import com.forgot.app.identityverify.service.CustomerVerifyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

@RestController("/forgot/identity-verify")
public class IdentityVerifyRestController {

    @Autowired
    private CustomerVerifyService customerVerifyService;

    @PostMapping("/get-customer")
    public IdentityVerifyResponse verifyCustomer(
            @RequestHeader RequestHeader requestHeader,
            @RequestBody IdentityVerifyRequest identityVerifyRequest) throws ApiException {
        try {
            return customerVerifyService.getIdentityVerifyResponse(identityVerifyRequest);
        } catch (Exception e) {
            throw new ApiException("Internal Server Error");
        }
    }

}
