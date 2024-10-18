package com.banking.digital_signature_demo.Controller;

import com.banking.digital_signature_demo.config.DigitalSignatureUtil;
import com.banking.digital_signature_demo.config.KeyPairGeneratorUtil;
import com.banking.digital_signature_demo.dto.TransferRequest;
import com.banking.digital_signature_demo.dto.TransferVerificationRequest;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.KeyPair;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

@RestController
@RequestMapping("/api")
public class FundTransferController {

    private final KeyPair keyPair;
    public FundTransferController() throws NoSuchAlgorithmException {
        KeyPairGeneratorUtil keyPairGenUtil = new KeyPairGeneratorUtil();
        this.keyPair = new KeyPair(keyPairGenUtil.getPublickey(), keyPairGenUtil.getPrivateKey());
    }

    @PostMapping("/sign-transfer")
    public String signTransfer(@RequestBody TransferRequest request) throws Exception {
        String data = request.toString();
        byte[] signature = DigitalSignatureUtil.signData(data.getBytes(), keyPair.getPrivate());
        return Base64.getEncoder().encodeToString(signature);
    }
    @PostMapping("/verify-transfer")
    public boolean verifyTransfer(@RequestBody TransferVerificationRequest request) throws Exception {
        byte[] signature = Base64.getDecoder().decode(request.getSignature());
        return DigitalSignatureUtil.verifyData(request.getTransferRequest().toString().getBytes(), signature, keyPair.getPublic());
    }
}

