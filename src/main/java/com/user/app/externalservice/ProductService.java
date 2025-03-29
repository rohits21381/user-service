package com.user.app.externalservice;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.user.app.entity.Product;

@FeignClient(name = "PRODUCTSERVICE", path ="/product/api/v1")
public interface ProductService {

	@GetMapping("/getallproductbyuserid/{userId}")
	public ResponseEntity<List<Product>> findProductByUserId(@PathVariable("userId") Long userId);
}
