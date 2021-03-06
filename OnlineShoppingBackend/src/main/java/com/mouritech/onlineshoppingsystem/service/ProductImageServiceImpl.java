package com.mouritech.onlineshoppingsystem.service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.zip.DataFormatException;
import java.util.zip.Deflater;
import java.util.zip.Inflater;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.mouritech.onlineshoppingsystem.entity.Product;
import com.mouritech.onlineshoppingsystem.entity.ProductImage;
import com.mouritech.onlineshoppingsystem.exception.ResourceNotFoundException;
import com.mouritech.onlineshoppingsystem.mapper.ProductMapper;
import com.mouritech.onlineshoppingsystem.repository.ProductImageRepository;
import com.mouritech.onlineshoppingsystem.repository.ProductRepository;
import com.mouritech.onlineshoppingsystem.util.Constants;

@Service
public class ProductImageServiceImpl implements ProductImageService {

	@Autowired
	private ProductImageRepository productimageRepository;

	@Autowired
	private ProductRepository productRepository;

	@Autowired
	ProductMapper productMapper;
	@Autowired
	private Constants constants;
	
	/**
	 * add image to the product
	 */
	@Override

	public Optional<ProductImage> addImage(String prodId, MultipartFile file) throws ResourceNotFoundException {
		ProductImage pimg = new ProductImage();

		return productRepository.findByProdId(prodId).map(item -> {
			pimg.setProduct(item);

			try {
				pimg.setPicByte(compressBytes(file.getBytes()));
			} catch (IOException e) {
		
				e.printStackTrace();
			}
			System.out.println(pimg);
			return productimageRepository.save(pimg);
		});
	}

	/**
	 * decompress the image into bytes
	 * @param compressed image data 
	 * @return decompressed image data
	 */
	private byte[] decompressByte(byte[] data) {


		Inflater inflater = new Inflater();
		inflater.setInput(data);
		ByteArrayOutputStream outputStream = new ByteArrayOutputStream(data.length);
		byte[] buffer = new byte[1024];
		try {
			while (!inflater.finished()) {
				int count = inflater.inflate(buffer);
				outputStream.write(buffer, 0, count);
			}
			outputStream.close();
		} catch (IOException ioe) {
		} catch (DataFormatException e) {
		}
		return outputStream.toByteArray();

	}

	/** 
	 * compress the  image data in byte
	 * @param original image data in byte
	 * @return compressed image data in byte
	 */
	private byte[] compressBytes(byte[] data) {
		Deflater deflater = new Deflater();
		deflater.setInput(data);
		deflater.finish();
		ByteArrayOutputStream outputStream = new ByteArrayOutputStream(data.length);
		byte[] buffer = new byte[1024];
		while (!deflater.finished()) {
			int count = deflater.deflate(buffer);
			outputStream.write(buffer, 0, count);
		}
		try {
			outputStream.close();
		} catch (IOException e) {
		}
		System.out.println("Compressed Image Byte Size - " + outputStream.toByteArray().length);
		return outputStream.toByteArray();
	}
  
	/**
	 * Get image by product id
	 */
	@Override
	public ProductImage getImageByProdId(String prodId) {
		final Optional<ProductImage> retrievedImage = productimageRepository.findByProduct_ProdId(prodId);
		ProductImage img = new ProductImage(//retrievedImage.get().getImageName(), retrievedImage.get().getImageType(),
				decompressByte(retrievedImage.get().getPicByte()));
		img.setImageId(retrievedImage.get().getImageId());
		img.setProduct(retrievedImage.get().getProduct());
		return img;
	}

	/**
	 * Get all images
	 */
	@Override
	public List<ProductImage> getAllImages() {
		List<ProductImage> images = productimageRepository.findAll();

		for (ProductImage image : images) {
			ProductImage newImage = image;
			image.setPicByte(decompressByte(newImage.getPicByte()));
		}

		return images;
	}

	@Override
	public Optional<Object> updateImage(String prodId, MultipartFile file) throws ResourceNotFoundException {
		ProductImage img = productimageRepository.findByProduct_ProdId(prodId).get();
//		img.setImageName(file.getOriginalFilename());
//		img.setImageType(file.getContentType());
		try {
			img.setPicByte(compressBytes(file.getBytes()));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return productRepository.findByProdId(prodId).map(item -> {
			img.setProduct(item);
			return productimageRepository.save(img);
		});
	}

	/**
	 * Delete image by id
	 */
	@Override
	public ResponseEntity<?> deleteImage(String prodId, Long imageId) throws ResourceNotFoundException {
		return productimageRepository.findByImageIdAndProduct_ProdId(imageId, prodId).map(image -> {
			productimageRepository.delete(image);
			return ResponseEntity.ok().build();
		}).orElseThrow(() -> new ResourceNotFoundException(constants.PRODUCT + prodId));

	}

	/**
	 * Get image by id
	 */
	@Override
	public ProductImage getImageById(Long imageId) throws ResourceNotFoundException {
		return productimageRepository.findById(imageId)
				.orElseThrow(() -> new ResourceNotFoundException(constants.PRODUCT_IMAGE+ imageId));

	}

}
