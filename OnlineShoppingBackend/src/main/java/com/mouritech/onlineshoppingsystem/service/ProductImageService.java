package com.mouritech.onlineshoppingsystem.service;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import com.mouritech.onlineshoppingsystem.entity.ProductImage;
import com.mouritech.onlineshoppingsystem.exception.ResourceNotFoundException;

public interface ProductImageService {

	Optional<ProductImage> addImage(String prodId, MultipartFile file) throws ResourceNotFoundException, IOException;

	ProductImage getImageByProdId(String prodId);

	List<ProductImage> getAllImages();

	Optional<Object> updateImage(String prodId, MultipartFile file) throws IOException;

	ResponseEntity<?> deleteImage(String prodId, Long imageId) throws ResourceNotFoundException;

	ProductImage getImageById(Long imageId) throws ResourceNotFoundException;

}
