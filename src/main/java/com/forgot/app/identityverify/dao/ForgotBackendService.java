package com.forgot.app.identityverify.dao;

import com.forgot.app.identityverify.exception.ApiException;
import com.forgot.app.identityverify.model.CustomerDetails;
import com.forgot.app.identityverify.model.IdentityVerifyRequest;
import com.forgot.app.identityverify.model.ProfileReference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.Optional;

@Component
public class ForgotBackendService {

    @Autowired
    private WebClient webClient;

    public Optional<ProfileReference> getProfileReferenceId(IdentityVerifyRequest identityVerifyRequest)
            throws Exception{
        try {
            Mono<ProfileReference> monoRef = webClient.post()
                    .uri("/forgot-backend-web/identity-profile")
                    .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                    .body(Mono.just(identityVerifyRequest), IdentityVerifyRequest.class)
                    .retrieve()
                    .bodyToMono(ProfileReference.class);
            ProfileReference profileReference = monoRef.block();
            return Optional.ofNullable(profileReference);
        } catch (Exception e){
            throw new ApiException("Internal Server Error");
        }
    }

    public Optional<CustomerDetails> getCustomerDetails(String profileReferenceId)
            throws Exception{
        try{
            Mono<CustomerDetails> monoDet = webClient.post()
                    .uri("/forgot-backend-web/customer-detail/" + profileReferenceId)
                    .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                    .retrieve()
                    .bodyToMono(CustomerDetails.class);
            CustomerDetails customerDetails =  monoDet.block();
            return Optional.ofNullable(customerDetails);
        } catch (Exception e){
            throw new ApiException("Internal Server Error");
        }
    }

}
