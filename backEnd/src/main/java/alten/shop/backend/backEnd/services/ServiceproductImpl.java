package alten.shop.backend.backEnd.services;


import alten.shop.backend.backEnd.utils.dto.input.InputProductDTO;
import alten.shop.backend.backEnd.utils.dto.output.OutputProductDTO;
import alten.shop.backend.backEnd.utils.entities.Product;
import alten.shop.backend.backEnd.repositories.ProductRepository;
import alten.shop.backend.backEnd.utils.exceptions.ExistProductException;
import alten.shop.backend.backEnd.utils.exceptions.ProductNotFoundException;

import alten.shop.backend.backEnd.utils.mappers.IMapper;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class ServiceproductImpl implements IServiceProducts {

    private ProductRepository productRepository;
    private IMapper productMapper;
    private static final Logger logger = LoggerFactory.getLogger(ServiceproductImpl.class);


    /**
     *  Send All products
     * @return
     * @throws ProductNotFoundException
     */
    @Override
    public Map<String, List<OutputProductDTO>> productsList() throws ProductNotFoundException {
        List<Product> products = this.productRepository.findAll();
        if(products.isEmpty()) throw  new ProductNotFoundException("No products found");
        List<OutputProductDTO> productDTOs = products
                .stream()
                .map( (product) -> this.productMapper.fromProduct(product))
                .collect(Collectors.toList());

        Map<String, List<OutputProductDTO>> response = new HashMap<>();
        response.put("data", productDTOs);
        return response;

    }

    /**
     * Send One product filtered by id
     *
     * @param productID
     * @return
     * @throws ProductNotFoundException
     */
    @Override
    public OutputProductDTO productFiltredById(Long productID) throws ProductNotFoundException {
        return this.productMapper
                .fromProduct(this.productRepository.findById(productID)
                .orElseThrow( () -> new ProductNotFoundException("Product not found")));
    }

    @Override
    public OutputProductDTO saveNewProduct(InputProductDTO inputProductDTO) throws Exception, ExistProductException {
        Product existingProduct = this.productRepository.findByCode(inputProductDTO.getCode());
        if(existingProduct != null){
            throw new ExistProductException("This product already exists");
        }
        try {
            return this.productMapper.fromProduct(this.productRepository.save(this.productMapper.fromInputProductDTO(inputProductDTO)));
        }catch (Exception e){
            logger.error("Error saving product", e);
            throw new Exception("Error Saving product"+ e.getMessage());
        }
    }



    @SuppressWarnings("unlikely-arg-type")
    @Override
    public OutputProductDTO updateProduct(Long productID, InputProductDTO inputProductDTO) throws  Exception, ProductNotFoundException {
        Product existingProduct = this.productRepository.findById(productID)
                .orElseThrow(() ->  new ProductNotFoundException("Product not found") );
                
        if(existingProduct.getCode() != inputProductDTO.getCode() ){
            if(existingProduct.equals(inputProductDTO)){
                Product isExistProduct = this.productRepository.findByCode(inputProductDTO.getCode());
                if(isExistProduct != null){
                    throw new ExistProductException("This code product already exists");
                }
            }
            System.out.println("nn eg");
        }

        BeanUtils.copyProperties(inputProductDTO, existingProduct);
        try {
            Product updatedProduct = this.productRepository.save(existingProduct);
            return this.productMapper.fromProduct(updatedProduct);
        } catch (Exception e) {
            logger.error("Error updating product", e);
            throw new Exception("Error updating product with ID: " + productID+" "+e.getMessage());
        }
    }

    @Override
    public void deleteproductfiltredBy(Long productId) throws Exception, ProductNotFoundException {
        this.productRepository.findById(productId)
                .orElseThrow( () -> new ProductNotFoundException(" Product not found"));
        try {
            this.productRepository.deleteById(productId);
        }catch (Exception e){
            logger.error("Error Deleting product", e);
            throw new Exception("Error deleting product with id"+ productId+" "+e.getMessage());
        }
    }

    @Override
    public void deleteAllProducts() throws Exception {
        try{
            this.productRepository.deleteAll();
        }catch (Exception e){
            logger.error("Error Deleting all product", e);
            throw new Exception("Error deleting all product "+e.getMessage());
        }
    }


}


