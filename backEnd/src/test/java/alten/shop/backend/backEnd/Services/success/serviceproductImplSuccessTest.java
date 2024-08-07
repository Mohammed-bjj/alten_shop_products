package alten.shop.backend.backEnd.Services.success;

import alten.shop.backend.backEnd.repositories.ProductRepository;
import alten.shop.backend.backEnd.services.ServiceproductImpl;
import alten.shop.backend.backEnd.utils.dto.input.InputProductDTO;
import alten.shop.backend.backEnd.utils.dto.output.OutputProductDTO;
import alten.shop.backend.backEnd.utils.entities.Product;
import alten.shop.backend.backEnd.utils.enums.Category;
import alten.shop.backend.backEnd.utils.enums.StatusStock;
import alten.shop.backend.backEnd.utils.mappers.IMapper;
import alten.shop.backend.backEnd.utils.exceptions.ProductNotFoundException;
import static org.junit.jupiter.api.Assertions.*;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.slf4j.Logger;
import org.mockito.InjectMocks;
import static org.mockito.Mockito.when;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class serviceproductImplSuccessTest {


    @Mock
    private ProductRepository productRepository;
    @Mock
    private IMapper productMapper;
    @Mock
    private Logger logger;

    @InjectMocks
    private ServiceproductImpl serviceProduct;

    private Product product;
    private OutputProductDTO outputProductDTO;
    private InputProductDTO inputProductDTO;
    private Long productId;
    private String productCode;



    @BeforeEach
    public void setUp() {
        product = new Product();
        productCode = "f230fh0g3";
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


        this.productId = 1L;
    }



    @Test
    public void shouldReturnListOfOutputProductDTO() throws ProductNotFoundException {
        when(productRepository.findAll()).thenReturn(Collections.singletonList(product));
        when(productMapper.fromProduct(product)).thenReturn(outputProductDTO);

        Map<String, List<OutputProductDTO>> result = serviceProduct.productsList();

        assertNotNull(result, "The result should not be null");
        assertEquals(result.size(), 1);
    }


    @Test
    public void shouldReturnOutPutProductDtoFiltredByID() throws ProductNotFoundException {

        when(this.productRepository.findById(productId)).thenReturn(Optional.of(product));
        when(this.productMapper.fromProduct(product)).thenReturn(outputProductDTO);


        OutputProductDTO productDTO = serviceProduct.productFiltredById(productId);
        assertNotNull(productDTO);
        assertEquals(1L, productDTO.getId());
    }




    @Test
    public void shouldReturnOuputProductWhenAddingToDbisSuccessful() throws Exception {}



}


/**
 *
 *    when(productRepository.findByCode(productCode)).thenReturn(product);
 *         when(productMapper.fromInputProductDTO(inputProductDTO)).thenReturn(product);
 *         when(productRepository.save(product)).thenReturn(product);
 *         when(productMapper.fromProduct(product)).thenReturn(outputProductDTO);
 *
 *         OutputProductDTO productSaved = serviceProduct.saveNewProduct(inputProductDTO);
 *
 *        assertNotNull(productSaved);
 *
 *
 *
 *
 *
 *
 */




