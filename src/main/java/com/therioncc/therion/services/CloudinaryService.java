package com.therioncc.therion.services;

import com.cloudinary.Cloudinary;
import com.cloudinary.Transformation;
import com.cloudinary.utils.ObjectUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Map;

@Service
public class CloudinaryService {

    @Value("${cloudinary.cloud-name}")
    private String cloudName;

    @Value("${cloudinary.api-key}")
    private String apiKey;

    @Value("${cloudinary.api-secret}")
    private String apiSecret;

    public String uploadImage(MultipartFile file) {
        try {
            Cloudinary cloudinary = new Cloudinary(ObjectUtils.asMap(
                    "cloud_name", cloudName,
                    "api_key", apiKey,
                    "api_secret", apiSecret
            ));

            Map<?, ?> uploadResult = cloudinary.uploader().upload(file.getBytes(), ObjectUtils.emptyMap());

            return (String) uploadResult.get("secure_url");
        } catch (IOException e) {
            e.printStackTrace();

            // Manejar la excepción apropiadamente
            return null;
        }
    }

    public String generateImageUrl(String publicId) {
        Cloudinary cloudinary = new Cloudinary(ObjectUtils.asMap(
                "cloud_name", cloudName,
                "api_key", apiKey,
                "api_secret", apiSecret
        ));

        return cloudinary.url().transformation(new Transformation().width(300).height(300).crop("fill")).generate(publicId);
    }
}