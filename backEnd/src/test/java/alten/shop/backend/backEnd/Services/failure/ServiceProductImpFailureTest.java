package alten.shop.backend.backEnd.Services.failure;


import alten.shop.backend.backEnd.repositories.ProductRepository;
import alten.shop.backend.backEnd.services.ServiceproductImpl;
import alten.shop.backend.backEnd.utils.dto.input.InputProductDTO;
import alten.shop.backend.backEnd.utils.dto.output.OutputProductDTO;
import alten.shop.backend.backEnd.utils.entities.Product;
import alten.shop.backend.backEnd.utils.enums.Category;
import alten.shop.backend.backEnd.utils.enums.StatusStock;
import alten.shop.backend.backEnd.utils.exceptions.ProductNotFoundException;

import alten.shop.backend.backEnd.utils.mappers.IMapper;
import org.jboss.logging.Logger;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;


import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class ServiceProductImpFailureTest {


    @Mock
    ProductRepository productRepository;

    @Mock
    IMapper productMapper;

    @Mock
    Logger logger;


    @InjectMocks
    ServiceproductImpl serviceproduct;


    private Product product;
    private OutputProductDTO outputProductDTO;
    private InputProductDTO  inputProductDTO;
    private Long productID;


    @BeforeEach
    public void setup(){
        productID =1L;
        product = new Product();
        product.setId(1L);
        product.setCode("f230fh0g3");
        product.setName("Bamboo Watch");
        product.setDescription("Product Description");
        product.setPrice(65L);
        product.setCategory(Category.ACCESSORIES);
        product.setQuantity(24L);
        product.setInventoryStatus(StatusStock.INSTOCK);
        product.setRating(5L);
        product.setImage("black-watch.jpg");

        outputProductDTO = new OutputProductDTO();
        outputProductDTO.setId(1L);
        outputProductDTO.setCode("f230fh0g3");
        outputProductDTO.setName("Bamboo Watch");
        outputProductDTO.setDescription("Product Description");
        outputProductDTO.setPrice(65L);
        outputProductDTO.setCategory(Category.ACCESSORIES);
        outputProductDTO.setQuantity(24L);
        outputProductDTO.setInventoryStatus(StatusStock.INSTOCK);
        outputProductDTO.setRating(5L);
        outputProductDTO.setImage("black-watch.jpg");

        inputProductDTO = new InputProductDTO();
        inputProductDTO.setCode("f230fh0g3");
        inputProductDTO.setName("Bamboo Watch");
        inputProductDTO.setDescription("Product Description");
        inputProductDTO.setPrice(65L);
        inputProductDTO.setCategory(Category.ACCESSORIES);
        inputProductDTO.setQuantity(24L);
        inputProductDTO.setInventoryStatus(StatusStock.INSTOCK);
        inputProductDTO.setRating(5L);
        inputProductDTO.setImage("black-watch.jpg");
    }


    @Test
    @Tag(" ** Should return an exception when db is empty")
    public void shouldReturnExceptionWhenDataBaseIsEmpty()  {

        when(productRepository.findAll()).thenReturn(Collections.emptyList());
        assertThrows(ProductNotFoundException.class, () ->  this.serviceproduct.productsList() );
    }

    @Test
    public void shouldReturnExceptionWhenProductFiltredByIdNotFound(){
        when(productRepository.findById(productID)).thenReturn(Optional.empty());
        assertThrows( ProductNotFoundException.class, () -> serviceproduct.productFiltredById(productID));
    }












}
