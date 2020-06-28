package com.shashank.reactive.servicegateway.auth.s3;

import com.shashank.reactive.servicegateway.utils.BinaryUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;

import java.net.URI;
import java.util.Date;
import java.util.Map;


@Slf4j
public class S3SignerForAuthorizationHeader extends AWS4SignerBase {

    public S3SignerForAuthorizationHeader(URI endpointUrl, String httpMethod, String serviceName, String regionName) {
        super(endpointUrl, httpMethod, serviceName, regionName);
    }

    public HttpHeaders computeSignature(Map<String, String> queryParameters,
                                   String bodyHash,
                                   String awsAccessKey,
                                   String awsSecretKey) {

        HttpHeaders headers = new HttpHeaders();
        Date now = new Date();
        String dateTimeStamp = dateTimeFormat.format(now);



        // update the headers with required 'x-amz-date' and 'host' values
        headers.add("x-amz-date", dateTimeStamp);

        String hostHeader = endpointUrl.getHost();
        int port = endpointUrl.getPort();
        if ( port > -1 ) {
            hostHeader.concat(":" + port);
        }
        headers.add("Host", hostHeader);

        // canonicalize the headers; we need the set of header names as well as the
        // names and values to go into the signature process
        String canonicalizedHeaderNames = getCanonicalizeHeaderNames(headers);
        String canonicalizedHeaders = getCanonicalizedHeaderString(headers);

        // if any query string parameters have been supplied, canonicalize them
        String canonicalizedQueryParameters = getCanonicalizedQueryString(queryParameters);

        // canonicalize the various components of the request
        String canonicalRequest = getCanonicalRequest(endpointUrl, httpMethod,
                canonicalizedQueryParameters, canonicalizedHeaderNames,
                canonicalizedHeaders, bodyHash);
        log.debug("--------- Canonical request --------");
        log.debug(canonicalRequest);
        log.debug("------------------------------------");

        // construct the string to be signed
        String dateStamp = dateStampFormat.format(now);
        String scope =  dateStamp + "/" + regionName + "/" + serviceName + "/" + TERMINATOR;
        String stringToSign = getStringToSign(SCHEME, ALGORITHM, dateTimeStamp, scope, canonicalRequest);
        log.debug("--------- String to sign -----------");
        log.debug(stringToSign);
        log.debug("------------------------------------");

        // compute the signing key
        byte[] kSecret = (SCHEME + awsSecretKey).getBytes();
        byte[] kDate = sign(dateStamp, kSecret, SIGNATURE_ALGORITHM);
        byte[] kRegion = sign(regionName, kDate, SIGNATURE_ALGORITHM);
        byte[] kService = sign(serviceName, kRegion, SIGNATURE_ALGORITHM);
        byte[] kSigning = sign(TERMINATOR, kService, SIGNATURE_ALGORITHM);
        byte[] signature = sign(stringToSign, kSigning, SIGNATURE_ALGORITHM);

        String credentialsAuthorizationHeader =
                "Credential=" + awsAccessKey + "/" + scope;
        String signedHeadersAuthorizationHeader =
                "SignedHeaders=" + canonicalizedHeaderNames;
        String signatureAuthorizationHeader =
                "Signature=" + BinaryUtils.toHex(signature);

        String authorizationHeader = SCHEME + "-" + ALGORITHM + " "
                + credentialsAuthorizationHeader + ", "
                + signedHeadersAuthorizationHeader + ", "
                + signatureAuthorizationHeader;

        headers.add(HttpHeaders.AUTHORIZATION, authorizationHeader);

        return headers;
    }
}
