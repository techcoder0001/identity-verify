package com.forgot.app.identityverify.service;

import com.forgot.app.identityverify.dao.ForgotBackendService;
import com.forgot.app.identityverify.exception.ApiException;
import com.forgot.app.identityverify.exception.CustomerNotFoundException;
import com.forgot.app.identityverify.model.CustomerDetails;
import com.forgot.app.identityverify.model.IdentityVerifyRequest;
import com.forgot.app.identityverify.model.IdentityVerifyResponse;
import com.forgot.app.identityverify.model.ProfileReference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CustomerVerifyService {

    @Autowired
    private ForgotBackendService forgotBackendService;

    public IdentityVerifyResponse getIdentityVerifyResponse(IdentityVerifyRequest identityVerifyRequest)
            throws Exception {

        Optional<ProfileReference> optionalProfileReference = forgotBackendService
                .getProfileReferenceId(identityVerifyRequest);

        if(!optionalProfileReference.isPresent()){
            throw new CustomerNotFoundException("User not found");
        }

        Optional<CustomerDetails> customerDetails = forgotBackendService
                .getCustomerDetails(optionalProfileReference.get().getProfileRefId());

        if(!customerDetails.isPresent()){
            throw new ApiException("User Details not found");
        }

        IdentityVerifyResponse idVerifyResponse = new IdentityVerifyResponse();
        idVerifyResponse.setProfileRefId(optionalProfileReference.get().getProfileRefId());
        idVerifyResponse.setFullName(customerDetails.get().getFullName());
        idVerifyResponse.setAddress(customerDetails.get().getAddress());
        idVerifyResponse.setPhoneNumber(customerDetails.get().getPhoneNumber());

        return idVerifyResponse;
    }
}
